package net.Limbo.landfallmagic.blocks;

import net.Limbo.landfallmagic.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ResearchTablePartBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // Hitboxes flipped 180 degrees from the previous rotation
    // NORTH facing - flipped 180°
    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(8, 12, 2, 16, 14, 14),    // Table top (half)
            Block.box(10, 10, 2, 16, 12, 3),    // Back support (was front)
            Block.box(10, 10, 13, 16, 12, 14),  // Front support (was back)
            Block.box(8, 7, 3, 9, 12, 13),      // Cross beam
            Block.box(8, 0, 12, 10, 12, 14),    // Back left leg (was front right)
            Block.box(8, 0, 2, 10, 12, 4)       // Front left leg (was back right)
    ).optimize();

    // EAST facing - flipped 180°
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(2, 12, 8, 14, 14, 16),    // Table top (half)
            Block.box(2, 10, 10, 3, 12, 16),    // Left side support (was right)
            Block.box(13, 10, 10, 14, 12, 16),  // Right side support (was left)
            Block.box(3, 7, 8, 13, 12, 9),      // Cross beam
            Block.box(12, 0, 8, 14, 12, 10),    // Right front leg (was left back)
            Block.box(2, 0, 8, 4, 12, 10)       // Left front leg (was right back)
    ).optimize();

    // SOUTH facing - flipped 180°
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(0, 12, 2, 8, 14, 14),     // Table top (half)
            Block.box(0, 10, 13, 6, 12, 14),    // Front support (was back)
            Block.box(0, 10, 2, 6, 12, 3),      // Back support (was front)
            Block.box(7, 7, 3, 8, 12, 13),      // Cross beam
            Block.box(6, 0, 2, 8, 12, 4),       // Front right leg (was back left)
            Block.box(6, 0, 12, 8, 12, 14)      // Back right leg (was front left)
    ).optimize();

    // WEST facing - flipped 180°
    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(2, 12, 0, 14, 14, 8),     // Table top (half)
            Block.box(13, 10, 0, 14, 12, 6),    // Right side support (was left)
            Block.box(2, 10, 0, 3, 12, 6),      // Left side support (was right)
            Block.box(3, 7, 7, 13, 12, 8),      // Cross beam
            Block.box(2, 0, 6, 4, 12, 8),       // Left back leg (was right front)
            Block.box(12, 0, 6, 14, 12, 8)      // Right back leg (was left front)
    ).optimize();

    public ResearchTablePartBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(3.0F, 6.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .pushReaction(PushReaction.BLOCK)); // Prevent pistons from moving this
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction facing = state.getValue(FACING);
        BlockPos mainPos = pos.relative(facing.getClockWise());

        // Check if the main block is still there
        if (neighborPos.equals(mainPos) && !neighborState.is(ModBlocks.RESEARCH_TABLE.get())) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock()) && state.hasProperty(FACING)) {
            Direction facing = state.getValue(FACING);
            BlockPos mainPos = pos.relative(facing.getClockWise());

            // Remove the main block if it exists
            if (level.getBlockState(mainPos).is(ModBlocks.RESEARCH_TABLE.get())) {
                level.setBlock(mainPos, Blocks.AIR.defaultBlockState(), 35);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        // Redirect interaction to the main table block
        Direction facing = state.getValue(FACING);
        BlockPos mainPos = pos.relative(facing.getClockWise());
        BlockState mainState = level.getBlockState(mainPos);

        if (mainState.is(ModBlocks.RESEARCH_TABLE.get())) {
            return mainState.useWithoutItem(level, player, hit.withPosition(mainPos));
        }

        return InteractionResult.PASS;
    }

    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        // Return the main table item when pick-blocked
        return new ItemStack(ModBlocks.RESEARCH_TABLE_ITEM.get());
    }
}
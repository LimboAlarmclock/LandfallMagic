package net.Limbo.landfallmagic.blocks;

import net.Limbo.landfallmagic.ModBlocks;
import net.Limbo.landfallmagic.ResearchTableScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ResearchTableBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // Hitboxes flipped 180 degrees from the previous rotation
    // NORTH facing - flipped 180°
    private static final VoxelShape SHAPE_NORTH = Shapes.or(
            Block.box(0, 12, 2, 16, 14, 14),    // Table top
            Block.box(0, 10, 2, 14, 12, 3),     // Back support (was front)
            Block.box(0, 10, 13, 14, 12, 14),   // Front support (was back)
            Block.box(15, 7, 4, 16, 12, 12),    // Right panel (was left)
            Block.box(14, 0, 2, 16, 12, 4),     // Right front leg (was left)
            Block.box(14, 0, 12, 16, 12, 14)    // Right back leg (was left)
    ).optimize();

    // EAST facing - flipped 180°
    private static final VoxelShape SHAPE_EAST = Shapes.or(
            Block.box(2, 12, 0, 14, 14, 16),    // Table top
            Block.box(2, 10, 0, 3, 12, 14),     // Left side support (was right)
            Block.box(13, 10, 0, 14, 12, 14),   // Right side support (was left)
            Block.box(4, 7, 15, 12, 12, 16),    // Back panel (was front)
            Block.box(2, 0, 14, 4, 12, 16),     // Left back leg (was front)
            Block.box(12, 0, 14, 14, 12, 16)    // Right back leg (was front)
    ).optimize();

    // SOUTH facing - flipped 180°
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(
            Block.box(0, 12, 2, 16, 14, 14),    // Table top
            Block.box(2, 10, 13, 16, 12, 14),   // Front support (was back)
            Block.box(2, 10, 2, 16, 12, 3),     // Back support (was front)
            Block.box(0, 7, 4, 1, 12, 12),      // Left panel (was right)
            Block.box(0, 0, 12, 2, 12, 14),     // Left back leg (was front)
            Block.box(0, 0, 2, 2, 12, 4)        // Left front leg (was back)
    ).optimize();

    // WEST facing - flipped 180°
    private static final VoxelShape SHAPE_WEST = Shapes.or(
            Block.box(2, 12, 0, 14, 14, 16),    // Table top
            Block.box(13, 10, 2, 14, 12, 16),   // Right side support (was left)
            Block.box(2, 10, 2, 3, 12, 16),     // Left side support (was right)
            Block.box(4, 7, 0, 12, 12, 1),      // Front panel (was back)
            Block.box(12, 0, 0, 14, 12, 2),     // Right front leg (was back)
            Block.box(2, 0, 0, 4, 12, 2)        // Left front leg (was back)
    ).optimize();

    public ResearchTableBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BROWN)
                .strength(3.0F, 6.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops()
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = context.getHorizontalDirection().getOpposite();
        BlockPos pos = context.getClickedPos();
        BlockPos partPos = pos.relative(facing.getCounterClockWise());

        // Check if there's space for the second part
        if (context.getLevel().getBlockState(partPos).canBeReplaced(context)) {
            return this.defaultBlockState().setValue(FACING, facing);
        }

        return null; // Can't place if there's no space
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            Direction facing = state.getValue(FACING);
            BlockPos partPos = pos.relative(facing.getCounterClockWise());

            // Place the part block
            BlockState partState = ModBlocks.RESEARCH_TABLE_PART.get().defaultBlockState().setValue(FACING, facing);
            level.setBlock(partPos, partState, 3);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        Direction facing = state.getValue(FACING);
        BlockPos partPos = pos.relative(facing.getCounterClockWise());

        // Check if the part block is still there
        if (neighborPos.equals(partPos) && !neighborState.is(ModBlocks.RESEARCH_TABLE_PART.get())) {
            return Blocks.AIR.defaultBlockState();
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock()) && state.hasProperty(FACING)) {
            Direction facing = state.getValue(FACING);
            BlockPos partPos = pos.relative(facing.getCounterClockWise());

            // Remove the part block if it exists
            if (level.getBlockState(partPos).is(ModBlocks.RESEARCH_TABLE_PART.get())) {
                level.setBlock(partPos, Blocks.AIR.defaultBlockState(), 35);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
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
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            Minecraft.getInstance().setScreen(new ResearchTableScreen());
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
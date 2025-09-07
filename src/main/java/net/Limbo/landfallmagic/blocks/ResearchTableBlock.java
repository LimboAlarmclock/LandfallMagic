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
import net.minecraft.world.level.block.Block;
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

    // Correct hitboxes for the "right" half of the table for all directions
    private static final VoxelShape SHAPE_NORTH = Shapes.or(Block.box(0, 12, 2, 16, 14, 14), Block.box(0, 10, 2, 1, 12, 14), Block.box(0, 7, 4, 1, 12, 12), Block.box(0, 0, 2, 2, 12, 4), Block.box(0, 0, 12, 2, 12, 14)).optimize();
    private static final VoxelShape SHAPE_EAST = Shapes.or(Block.box(2, 12, 0, 14, 14, 16), Block.box(2, 10, 0, 14, 12, 1), Block.box(4, 7, 0, 12, 12, 1), Block.box(2, 0, 0, 4, 12, 2), Block.box(12, 0, 0, 14, 12, 2)).optimize();
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(Block.box(0, 12, 2, 16, 14, 14), Block.box(15, 10, 2, 16, 12, 14), Block.box(15, 7, 4, 16, 12, 12), Block.box(14, 0, 2, 16, 12, 4), Block.box(14, 0, 12, 16, 12, 14)).optimize();
    private static final VoxelShape SHAPE_WEST = Shapes.or(Block.box(2, 12, 0, 14, 14, 16), Block.box(2, 10, 15, 14, 12, 16), Block.box(4, 7, 15, 12, 12, 16), Block.box(2, 0, 14, 4, 12, 16), Block.box(12, 0, 14, 14, 12, 16)).optimize();

    public ResearchTableBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(3.0F, 6.0F).sound(SoundType.WOOD).requiresCorrectToolForDrops().noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            Direction facing = state.getValue(FACING);
            BlockPos partPos = pos.relative(facing.getCounterClockWise());
            level.setBlock(partPos, ModBlocks.RESEARCH_TABLE_PART.get().defaultBlockState().setValue(FACING, facing), 3);
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasProperty(FACING) && !state.is(newState.getBlock())) {
            Direction facing = state.getValue(FACING);
            BlockPos partPos = pos.relative(facing.getCounterClockWise());
            if (level.getBlockState(partPos).is(ModBlocks.RESEARCH_TABLE_PART.get())) {
                level.removeBlock(partPos, false);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case EAST: return SHAPE_EAST;
            case SOUTH: return SHAPE_SOUTH;
            case WEST: return SHAPE_WEST;
            default: return SHAPE_NORTH;
        }
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            Minecraft.getInstance().setScreen(new ResearchTableScreen());
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
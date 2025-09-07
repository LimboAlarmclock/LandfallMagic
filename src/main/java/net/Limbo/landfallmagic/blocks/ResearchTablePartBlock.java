package net.Limbo.landfallmagic.blocks;

import net.Limbo.landfallmagic.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ResearchTablePartBlock extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    // Correct hitboxes for the "left" half of the table for all directions
    private static final VoxelShape SHAPE_NORTH = Shapes.or(Block.box(0, 12, 2, 16, 14, 14), Block.box(15, 10, 2, 16, 12, 14), Block.box(15, 7, 3, 16, 12, 13), Block.box(14, 0, 2, 16, 12, 4), Block.box(14, 0, 12, 16, 12, 14)).optimize();
    private static final VoxelShape SHAPE_EAST = Shapes.or(Block.box(2, 12, 0, 14, 14, 16), Block.box(2, 10, 15, 14, 12, 16), Block.box(3, 7, 15, 13, 12, 16), Block.box(2, 0, 14, 4, 12, 16), Block.box(12, 0, 14, 14, 12, 16)).optimize();
    private static final VoxelShape SHAPE_SOUTH = Shapes.or(Block.box(0, 12, 2, 16, 14, 14), Block.box(0, 10, 2, 1, 12, 14), Block.box(0, 7, 3, 1, 12, 13), Block.box(0, 0, 2, 2, 12, 4), Block.box(0, 0, 12, 2, 12, 14)).optimize();
    private static final VoxelShape SHAPE_WEST = Shapes.or(Block.box(2, 12, 0, 14, 14, 16), Block.box(2, 10, 0, 14, 12, 1), Block.box(3, 7, 0, 13, 12, 1), Block.box(2, 0, 0, 4, 12, 2), Block.box(12, 0, 0, 14, 12, 2)).optimize();

    public ResearchTablePartBlock() {
        super(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(3.0F, 6.0F).sound(SoundType.WOOD).noOcclusion().pushReaction(PushReaction.BLOCK));
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
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
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasProperty(FACING) && !state.is(newState.getBlock())) {
            Direction facing = state.getValue(FACING);
            BlockPos mainBlockPos = pos.relative(facing.getClockWise());
            if (level.getBlockState(mainBlockPos).is(ModBlocks.RESEARCH_TABLE.get())) {
                level.removeBlock(mainBlockPos, false);
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }
}
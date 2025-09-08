package net.Limbo.landfallmagic.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class ResearchTableBlock extends Block {

    // Define the complex research table shape based on your model
    // This shape assumes the block is facing a certain direction.
    // We will now treat it as always facing "north" for simplicity.
    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(2, 0, -4, 14, 12, -2),   // northeastleg
            Block.box(12, 0, 18, 14, 12, 20), // southwestleg
            Block.box(2, 0, 18, 4, 12, 20),  // southeastleg
            Block.box(12, 0, -4, 14, 12, -2),   // Other northeastleg
            Block.box(2, 10, -2, 3, 12, 18),   // westrunnerboard
            Block.box(13, 10, -2, 14, 12, 18),  // eastrunnerboard
            Block.box(2, 12, -4, 14, 14, 20), // tabletop
            Block.box(4, 7, 19, 12, 12, 20), // southtableflap
            Block.box(4, 7, -4, 12, 12, -3)    // northtableflap
    );

    public ResearchTableBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_RED)
                .strength(3.0F, 6.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops()
                .noOcclusion());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
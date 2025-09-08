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
    private static final VoxelShape RESEARCH_TABLE_SHAPE = Shapes.or(
            // Main tabletop (extends beyond normal block bounds)
            Block.box(-8, 12, 0, 24, 13, 16),

            // Table legs (front)
            Block.box(20, 0, 1, 22, 12, 2),   // Front right leg
            Block.box(22, 0, 1, 23, 12, 2),   // Front right support
            Block.box(22, 0, 2, 23, 12, 4),   // Front right corner

            // Table legs (back left)
            Block.box(-7, 0, 1, -6, 12, 2),   // Back left leg
            Block.box(-6, 0, 1, -4, 12, 2),   // Back left support
            Block.box(-7, 0, 2, -6, 12, 4),   // Back left corner

            // Table legs (back right)
            Block.box(-7, 0, 12, -6, 12, 14), // Back right corner posts
            Block.box(-7, 0, 14, -6, 12, 15), // Back right edge
            Block.box(-6, 0, 14, -4, 12, 15), // Back right support
            Block.box(21, 0, 12, 22, 12, 14), // Front right back corner
            Block.box(21, 0, 14, 22, 12, 15), // Front right back edge
            Block.box(19, 0, 14, 21, 12, 15), // Front right back support

            // Books and scrolls on the table surface
            Block.box(11.3, 12.75, 2, 15.8, 13.75, 13), // Right side book stack
            Block.box(0.2, 12.75, 2, 4.7, 13.75, 13),   // Left side book stack

            // Angled books (approximated as regular boxes for collision)
            Block.box(8.5, 14, 2, 12, 15.5, 13),        // Right angled book
            Block.box(4, 14, 2, 7.5, 15.5, 13),         // Left angled book

            // Central scroll/book
            Block.box(7, 13, 2, 9, 14.75, 13)
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
        return RESEARCH_TABLE_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // For collision, we might want a simpler shape to avoid getting stuck
        // You can return the full shape or create a simplified version
        return RESEARCH_TABLE_SHAPE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return RESEARCH_TABLE_SHAPE;
    }
}
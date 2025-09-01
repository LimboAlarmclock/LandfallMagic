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

public class KarmaCondenserBlock extends Block {
    
    // Define the complex shape based on your model
    private static final VoxelShape KARMA_CONDENSER_SHAPE = Shapes.or(
        // Base platform
        Block.box(2, 0, 2, 14, 1, 14),
        
        // Central inner chamber
        Block.box(3, 1, 5, 13, 20, 11),
        
        // Pillars around the edges (rotated elements from your model)
        Block.box(2, 1, 3, 5, 20, 5),     // Front-left pillar
        Block.box(5, 1, 3, 11, 20, 5),    // Front center pillar
        Block.box(11, 1, 2, 13, 20, 5),   // Front-right pillar (rotated)
        Block.box(11, 1, 11, 14, 20, 13), // Back-right pillar (rotated)
        Block.box(5, 1, 11, 11, 20, 13),  // Back center pillar
        Block.box(3, 1, 11, 5, 20, 14),   // Back-left pillar (rotated)
        
        // Top crystal formation (multiple overlapping cubes for the complex crystal)
        Block.box(4.5, 23, 4.5, 11.5, 30, 11.5)
    );

    public KarmaCondenserBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GRAY)
                .strength(7.0F, 14.0F)
                .sound(SoundType.NETHERITE_BLOCK)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .lightLevel(state -> 8)); // Magical glow
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return KARMA_CONDENSER_SHAPE;
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return KARMA_CONDENSER_SHAPE;
    }
    
    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return KARMA_CONDENSER_SHAPE;
    }
}
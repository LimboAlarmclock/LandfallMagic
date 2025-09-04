package net.Limbo.landfallmagic.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class CrystalClusterBlock extends Block {
    public CrystalClusterBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_PURPLE)
                .strength(1.5F, 1.5F)
                .sound(SoundType.AMETHYST_CLUSTER)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> 5)); // Gives off a faint magical glow
    }
}
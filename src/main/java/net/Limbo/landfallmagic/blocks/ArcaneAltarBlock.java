package net.Limbo.landfallmagic.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class ArcaneAltarBlock extends Block {
    public ArcaneAltarBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_PURPLE)
                .strength(3.0F, 6.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops());
    }
}
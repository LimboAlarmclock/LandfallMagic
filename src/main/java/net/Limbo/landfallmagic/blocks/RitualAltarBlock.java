package net.Limbo.landfallmagic.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class RitualAltarBlock extends Block {
    public RitualAltarBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_RED)
                .strength(4.0F, 8.0F)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
    }
}
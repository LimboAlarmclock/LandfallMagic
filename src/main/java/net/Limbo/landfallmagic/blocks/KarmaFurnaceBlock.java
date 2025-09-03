package net.Limbo.landfallmagic.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class KarmaFurnaceBlock extends Block {
    public KarmaFurnaceBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GRAY)
                .strength(5.0F, 10.0F)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops());
    }
}
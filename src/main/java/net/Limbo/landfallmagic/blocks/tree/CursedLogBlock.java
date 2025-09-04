package net.Limbo.landfallmagic.blocks.tree;

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class CursedLogBlock extends RotatedPillarBlock {
    public CursedLogBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLACK)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops());
    }
}
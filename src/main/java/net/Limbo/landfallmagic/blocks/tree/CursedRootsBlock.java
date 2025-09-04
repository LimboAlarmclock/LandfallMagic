package net.Limbo.landfallmagic.blocks.tree;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class CursedRootsBlock extends Block {
    public CursedRootsBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_BLACK)
                .strength(0.7F)
                .sound(SoundType.ROOTS)
                .noOcclusion());
    }
}
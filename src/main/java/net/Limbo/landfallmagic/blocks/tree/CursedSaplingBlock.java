package net.Limbo.landfallmagic.blocks.tree;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class CursedSaplingBlock extends SaplingBlock {
    // We pass the TreeGrower in the constructor now, which is a cleaner approach.
    public CursedSaplingBlock(TreeGrower treeGrower) {
        super(treeGrower, BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS));
    }
}
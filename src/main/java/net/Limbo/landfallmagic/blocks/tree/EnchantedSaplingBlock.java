package net.Limbo.landfallmagic.blocks.tree;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class EnchantedSaplingBlock extends SaplingBlock {
    public EnchantedSaplingBlock(TreeGrower treeGrower) {
        super(treeGrower, BlockBehaviour.Properties.of()
                .mapColor(MapColor.PLANT)
                .noCollission()
                .randomTicks()
                .instabreak()
                .sound(SoundType.GRASS));
    }
}
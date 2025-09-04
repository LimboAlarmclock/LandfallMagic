package net.Limbo.landfallmagic.blocks.tree; // <-- This line is updated

import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

public class EnchantedLogBlock extends RotatedPillarBlock {
    public EnchantedLogBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_CYAN)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops());
    }
}
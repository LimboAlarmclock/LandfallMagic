package net.Limbo.landfallmagic.worldgen;

import net.minecraft.world.level.block.grower.TreeGrower;
import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower ENCHANTED_TREE = new TreeGrower(
            "enchanted",
            Optional.of(BiomeModifications.ENCHANTED_TREE_CONFIGURED),
            Optional.empty(),
            Optional.empty()
    );

    public static final TreeGrower CURSED_TREE = new TreeGrower(
            "cursed",
            Optional.of(BiomeModifications.CURSED_TREE_CONFIGURED),
            Optional.empty(),
            Optional.empty()
    );
}
package net.Limbo.landfallmagic.worldgen;

import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, landfallmagic.MODID);

    public static final DeferredHolder<Feature<?>, KarmaNodeFeature> KARMA_NODE_FEATURE =
            FEATURES.register("karma_node", () -> new KarmaNodeFeature(NoneFeatureConfiguration.CODEC));
}
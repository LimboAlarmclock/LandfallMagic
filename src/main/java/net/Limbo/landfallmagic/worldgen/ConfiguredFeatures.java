package net.Limbo.landfallmagic.worldgen;

import net.Limbo.landfallmagic.Config;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> KARMA_NODE_CONFIGURED = BiomeModifications.createKey("karma_node");
    public static final ResourceKey<PlacedFeature> KARMA_NODE_PLACED = BiomeModifications.createPlacedKey("karma_node");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(KARMA_NODE_CONFIGURED, new ConfiguredFeature<>(ModFeatures.KARMA_NODE_FEATURE.get(),
                new NoneFeatureConfiguration()));
    }
}
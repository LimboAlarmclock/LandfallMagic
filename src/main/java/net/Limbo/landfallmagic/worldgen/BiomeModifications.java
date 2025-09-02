package net.Limbo.landfallmagic.worldgen;

import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import java.util.List;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class BiomeModifications {

    public static final ResourceKey<ConfiguredFeature<?, ?>> KARMA_NODE_CONFIGURED = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "karma_node"));
    public static final ResourceKey<PlacedFeature> KARMA_NODE_PLACED = ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "karma_node"));

    public static final ResourceKey<BiomeModifier> ADD_KARMA_NODES_OVERWORLD = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "add_karma_nodes_overworld"));
    public static final ResourceKey<BiomeModifier> ADD_KARMA_NODES_NETHER = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "add_karma_nodes_nether"));
    public static final ResourceKey<BiomeModifier> ADD_KARMA_NODES_END = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "add_karma_nodes_end"));

    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(KARMA_NODE_CONFIGURED, new ConfiguredFeature<>(ModFeatures.KARMA_NODE_FEATURE.get(), NoneFeatureConfiguration.INSTANCE));
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(KARMA_NODE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(KARMA_NODE_CONFIGURED),
                List.of(
                        // Tries to place in every chunk for easy testing. Change this value to make them rarer.
                        RarityFilter.onAverageOnceEvery(1),
                        InSquarePlacement.spread(),
                        // Gives the feature a wide range of Y-levels to start its search.
                        HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()),
                        BiomeFilter.biome()
                )
        ));
    }

    public static void bootstrapBiomeModifiers(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderSet<PlacedFeature> featureSet = HolderSet.direct(placedFeatures.getOrThrow(KARMA_NODE_PLACED));

        context.register(ADD_KARMA_NODES_OVERWORLD, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), featureSet, GenerationStep.Decoration.UNDERGROUND_DECORATION));
        context.register(ADD_KARMA_NODES_NETHER, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_NETHER), featureSet, GenerationStep.Decoration.UNDERGROUND_DECORATION));
        context.register(ADD_KARMA_NODES_END, new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_END), featureSet, GenerationStep.Decoration.UNDERGROUND_DECORATION));
    }
}
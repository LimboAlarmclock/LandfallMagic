package net.Limbo.landfallmagic.worldgen;

import net.Limbo.landfallmagic.ModBlocks;
import net.Limbo.landfallmagic.ModEntities;
import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class BiomeModifications {

    // Helper methods
    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, name));
    }
    private static ResourceKey<PlacedFeature> createPlacedKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, name));
    }
    private static ResourceKey<BiomeModifier> createModifierKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, name));
    }

    // Field Definitions
    public static final ResourceKey<ConfiguredFeature<?, ?>> KARMA_NODE_CONFIGURED = createKey("karma_node");
    public static final ResourceKey<PlacedFeature> KARMA_NODE_PLACED = createPlacedKey("karma_node");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CRYSTAL_CLUSTER_CONFIGURED = createKey("crystal_cluster");
    public static final ResourceKey<PlacedFeature> CRYSTAL_CLUSTER_PLACED = createPlacedKey("crystal_cluster");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ENCHANTED_TREE_CONFIGURED = createKey("enchanted_tree");
    public static final ResourceKey<PlacedFeature> ENCHANTED_TREE_PLACED = createPlacedKey("enchanted_tree");

    public static final ResourceKey<ConfiguredFeature<?, ?>> CURSED_TREE_CONFIGURED = createKey("cursed_tree");
    public static final ResourceKey<PlacedFeature> CURSED_TREE_PLACED = createPlacedKey("cursed_tree");

    public static final ResourceKey<BiomeModifier> ADD_DIRE_WOLF_SPAWNS = createModifierKey("add_dire_wolf_spawns");


    public static void bootstrapConfiguredFeatures(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        context.register(KARMA_NODE_CONFIGURED, new ConfiguredFeature<>(ModFeatures.KARMA_NODE_FEATURE.get(), NoneFeatureConfiguration.INSTANCE));

        context.register(CRYSTAL_CLUSTER_CONFIGURED, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(
                List.of(OreConfiguration.target(new BlockMatchTest(net.minecraft.world.level.block.Blocks.STONE), ModBlocks.CRYSTAL_CLUSTER.get().defaultBlockState())),
                8
        )));

        context.register(ENCHANTED_TREE_CONFIGURED, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.ENCHANTED_LOG.get()),
                new StraightTrunkPlacer(5, 2, 0),
                BlockStateProvider.simple(ModBlocks.ENCHANTED_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1)
        ).build()));

        context.register(CURSED_TREE_CONFIGURED, new ConfiguredFeature<>(Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.CURSED_LOG.get()),
                new FancyTrunkPlacer(6, 3, 2),
                BlockStateProvider.simple(ModBlocks.CURSED_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2),
                new TwoLayersFeatureSize(1, 0, 2)
        ).build()));
    }

    public static void bootstrapPlacedFeatures(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        context.register(KARMA_NODE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(KARMA_NODE_CONFIGURED),
                List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.top()), BiomeFilter.biome())
        ));

        context.register(CRYSTAL_CLUSTER_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(CRYSTAL_CLUSTER_CONFIGURED),
                List.of(CountPlacement.of(4), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64)), BiomeFilter.biome())
        ));

        context.register(ENCHANTED_TREE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(ENCHANTED_TREE_CONFIGURED),
                List.of(
                        RarityFilter.onAverageOnceEvery(10),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        ));

        context.register(CURSED_TREE_PLACED, new PlacedFeature(
                configuredFeatures.getOrThrow(CURSED_TREE_CONFIGURED),
                List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome())
        ));
    }

    public static void bootstrapBiomeModifiers(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(createModifierKey("add_karma_nodes_overworld"), new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(KARMA_NODE_PLACED)), GenerationStep.Decoration.UNDERGROUND_DECORATION));
        context.register(createModifierKey("add_karma_nodes_nether"), new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_NETHER), HolderSet.direct(placedFeatures.getOrThrow(KARMA_NODE_PLACED)), GenerationStep.Decoration.UNDERGROUND_DECORATION));
        context.register(createModifierKey("add_karma_nodes_end"), new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_END), HolderSet.direct(placedFeatures.getOrThrow(KARMA_NODE_PLACED)), GenerationStep.Decoration.UNDERGROUND_DECORATION));

        context.register(createModifierKey("add_crystal_clusters"), new BiomeModifiers.AddFeaturesBiomeModifier(biomes.getOrThrow(BiomeTags.IS_OVERWORLD), HolderSet.direct(placedFeatures.getOrThrow(CRYSTAL_CLUSTER_PLACED)), GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(createModifierKey("add_enchanted_trees"), new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_FOREST),
                HolderSet.direct(placedFeatures.getOrThrow(ENCHANTED_TREE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(createModifierKey("add_cursed_trees"), new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                HolderSet.direct(placedFeatures.getOrThrow(CURSED_TREE_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));

        context.register(ADD_DIRE_WOLF_SPAWNS, new BiomeModifiers.AddSpawnsBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_TAIGA),
                List.of(new MobSpawnSettings.SpawnerData(ModEntities.DIRE_WOLF.get(), 10, 2, 4))
        ));
    }
}
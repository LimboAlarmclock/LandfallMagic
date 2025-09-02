package net.Limbo.landfallmagic.worldgen;

import com.mojang.serialization.Codec;
import net.Limbo.landfallmagic.ModBlocks;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.List;

public class KarmaNodeFeature extends Feature<NoneFeatureConfiguration> {

    public KarmaNodeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        // Check if world generation is enabled
        if (!net.Limbo.landfallmagic.Config.ENABLE_KARMA_NODE_GENERATION.get()) {
            return false;
        }

        // Get biome at this location
        ResourceKey<Biome> biomeKey = level.getBiome(origin).unwrapKey().orElse(null);
        if (biomeKey == null) {
            return false;
        }

        // Get spawn info for this biome
        KarmaNodeSpawnInfo spawnInfo = getSpawnInfoForBiome(biomeKey, level, random);
        if (spawnInfo == null) {
            return false;
        }

        // Find a suitable placement location
        BlockPos potentialPos = findSuitableY(level, origin, random, spawnInfo);

        if (potentialPos != null) {
            Block nodeBlock = spawnInfo.getNodeBlock();
            if (nodeBlock != null) {
                level.setBlock(potentialPos, nodeBlock.defaultBlockState(), 2);
                net.Limbo.landfallmagic.landfallmagic.LOGGER.info("Placed {} karma node at {}", spawnInfo.karmaType, potentialPos);
                return true;
            }
        }

        return false;
    }

    private BlockPos findSuitableY(WorldGenLevel level, BlockPos pos, RandomSource random, KarmaNodeSpawnInfo spawnInfo) {
        double surfaceBias = net.Limbo.landfallmagic.Config.SURFACE_NODE_BIAS.get();
        boolean preferSurface = random.nextDouble() < surfaceBias || spawnInfo.prefersSurface;

        if (preferSurface) {
            BlockPos surfacePos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
            if (surfacePos.getY() >= spawnInfo.minY && surfacePos.getY() <= spawnInfo.maxY) {
                if (level.isEmptyBlock(surfacePos) && !level.isEmptyBlock(surfacePos.below())) {
                    return surfacePos;
                }
            }
        }

        // Try underground placement
        for (int y = spawnInfo.maxY; y >= spawnInfo.minY; y--) {
            BlockPos currentPos = new BlockPos(pos.getX(), y, pos.getZ());
            if (level.isEmptyBlock(currentPos) && !level.isEmptyBlock(currentPos.below())) {
                return currentPos;
            }
        }

        return null;
    }
    private boolean isNodeCategoryEnabled(NodeRarity rarity) {
        return switch (rarity) {
            case COMMON -> net.Limbo.landfallmagic.Config.ENABLE_COMMON_NODES.get();
            case UNCOMMON -> net.Limbo.landfallmagic.Config.ENABLE_UNCOMMON_NODES.get();
            case RARE -> net.Limbo.landfallmagic.Config.ENABLE_RARE_NODES.get();
            case MYTHIC -> net.Limbo.landfallmagic.Config.ENABLE_MYTHIC_NODES.get();
        };
    }

    private KarmaNodeSpawnInfo getSpawnInfoForBiome(ResourceKey<Biome> biome, WorldGenLevel level, RandomSource random) {
        String biomeName = biome.location().getPath();
        boolean isNether = level.getLevel().dimension() == net.minecraft.world.level.Level.NETHER;
        boolean isEnd = level.getLevel().dimension() == net.minecraft.world.level.Level.END;
        boolean isOverworld = level.getLevel().dimension() == net.minecraft.world.level.Level.OVERWORLD;

        List<KarmaNodeSpawnInfo> possibleSpawns = new ArrayList<>();

        if ((isOverworld && (biomeName.contains("desert") || biomeName.contains("badlands"))) || isNether) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.FIRE, NodeRarity.COMMON, isNether ? 0 : 50, isNether ? 128 : 120, true, false));
        }
        if (isOverworld && (biomeName.contains("ocean") || biomeName.contains("river") || biomeName.contains("swamp"))) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.WATER, NodeRarity.COMMON, 30, 60, false, false));
        }
        if (isOverworld && (biomeName.contains("forest") || biomeName.contains("jungle") || biomeName.contains("taiga") || biomeName.contains("hills") || biomeName.contains("cave"))) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.EARTH, NodeRarity.COMMON, 0, 80, false, false));
        }
        if (isOverworld && (biomeName.contains("mountain") || biomeName.contains("windswept") || biomeName.contains("plains"))) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.AIR, NodeRarity.COMMON, 100, 320, true, false));
        }
        if ((isOverworld && (biomeName.contains("sunflower") || biomeName.contains("cherry") || biomeName.contains("mushroom"))) || (isEnd && biomeName.contains("highlands"))) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.LIGHT, NodeRarity.UNCOMMON, 150, 320, true, false));
        }
        if ((isOverworld && (biomeName.contains("deep_dark") || biomeName.contains("dark_forest"))) || (isNether && biomeName.contains("soul_sand_valley"))) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.DARK, NodeRarity.UNCOMMON, isOverworld ? -64 : 0, isOverworld ? 0 : 128, false, false));
        }
        if (isEnd && biomeName.contains("end_highlands")) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.ORDER, NodeRarity.RARE, 0, 128, true, false));
        }
        if (isNether && (biomeName.contains("crimson") || biomeName.contains("warped"))) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.CHAOS, NodeRarity.RARE, 0, 128, false, false));
        }
        if (isEnd && biomeName.contains("end_barrens")) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.CREATION, NodeRarity.MYTHIC, 0, 128, false, true));
        }
        if (isEnd && biomeName.contains("small_end_islands")) {
            possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.DESTRUCTION, NodeRarity.MYTHIC, 0, 128, false, true));
        }

        if (possibleSpawns.isEmpty()) {
            return null;
        }

        return possibleSpawns.get(random.nextInt(possibleSpawns.size()));
    }
    public enum NodeRarity {
        COMMON, UNCOMMON, RARE, MYTHIC
    }

    private static class KarmaNodeSpawnInfo {
        public final KarmaType karmaType;
        public final NodeRarity rarity;
        public final int minY;
        public final int maxY;
        public final boolean prefersSurface;
        public final boolean requiresDistanceFromSpawn;

        public KarmaNodeSpawnInfo(KarmaType karmaType, NodeRarity rarity, int minY, int maxY, boolean prefersSurface, boolean requiresDistanceFromSpawn) {
            this.karmaType = karmaType;
            this.rarity = rarity;
            this.minY = minY;
            this.maxY = maxY;
            this.prefersSurface = prefersSurface;
            this.requiresDistanceFromSpawn = requiresDistanceFromSpawn;
        }

        public Block getNodeBlock() {
            return switch (karmaType) {
                case FIRE -> ModBlocks.FIRE_NODE.get();
                case WATER -> ModBlocks.WATER_NODE.get();
                case EARTH -> ModBlocks.EARTH_NODE.get();
                case AIR -> ModBlocks.AIR_NODE.get();
                case LIGHT -> ModBlocks.LIGHT_NODE.get();
                case DARK -> ModBlocks.DARK_NODE.get();
                case ORDER -> ModBlocks.ORDER_NODE.get();
                case CHAOS -> ModBlocks.CHAOS_NODE.get();
                case CREATION -> ModBlocks.CREATION_NODE.get();
                case DESTRUCTION -> ModBlocks.DESTRUCTION_NODE.get();
            };
        }
    }
}
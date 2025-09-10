package net.Limbo.landfallmagic.worldgen;

import com.mojang.serialization.Codec;
import net.Limbo.landfallmagic.ModBlocks;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
        if (!(level.getLevel() instanceof ServerLevel serverLevel)) {
            return false;
        }

        RandomSource random = context.random();

        if (!net.Limbo.landfallmagic.Config.ENABLE_KARMA_NODE_GENERATION.get()) {
            return false;
        }

        KarmaNodeSpawnInfo spawnInfo = getSpawnInfoForBiome(context, random);
        if (spawnInfo == null) {
            return false;
        }

        BlockPos potentialPos;
        if (spawnInfo.karmaType == KarmaType.WATER) {
            potentialPos = findWaterY(level, context.origin());
        } else {
            potentialPos = findSuitableY(level, context.origin(), random, spawnInfo);
        }

        if (potentialPos != null) {
            KarmaNodePositions nodePositions = KarmaNodePositions.get(serverLevel);
            KarmaType currentType = spawnInfo.karmaType;
            boolean canPlace = false;

            synchronized (nodePositions) {
                boolean tooClose;
                if (currentType == KarmaType.CREATION || currentType == KarmaType.DESTRUCTION) {
                    final int specialDistanceSq = 5000 * 5000;
                    tooClose = nodePositions.getNodePositions().entrySet().stream()
                            .anyMatch(entry ->
                                    (entry.getValue() == KarmaType.CREATION || entry.getValue() == KarmaType.DESTRUCTION) &&
                                            entry.getKey().distSqr(potentialPos) < specialDistanceSq
                            );
                } else {
                    final int minDistanceSq = net.Limbo.landfallmagic.Config.MIN_NODE_DISTANCE.get() * net.Limbo.landfallmagic.Config.MIN_NODE_DISTANCE.get();
                    tooClose = nodePositions.getNodePositions().entrySet().stream()
                            .anyMatch(entry -> entry.getValue() == currentType && entry.getKey().distSqr(potentialPos) < minDistanceSq);
                }

                if (!tooClose) {
                    nodePositions.addPosition(potentialPos.immutable(), currentType);
                    canPlace = true;
                }
            }

            if (canPlace) {
                Block nodeBlock = spawnInfo.getNodeBlock();
                if (nodeBlock != null) {
                    level.setBlock(potentialPos, nodeBlock.defaultBlockState(), 2);
                    level.scheduleTick(potentialPos, nodeBlock, net.Limbo.landfallmagic.Config.NODE_TICK_DELAY.get());
                    net.Limbo.landfallmagic.landfallmagic.LOGGER.info("Placed {} karma node at {}", spawnInfo.karmaType, potentialPos);
                    return true;
                }
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
                if (level.isEmptyBlock(surfacePos) && !level.getBlockState(surfacePos.below()).isAir() && !level.getBlockState(surfacePos.below()).getFluidState().isSource()) {
                    return surfacePos;
                }
            }
        }

        for (int y = spawnInfo.maxY; y >= spawnInfo.minY; y--) {
            BlockPos currentPos = new BlockPos(pos.getX(), y, pos.getZ());
            if (level.isEmptyBlock(currentPos) && !level.getBlockState(currentPos.below()).isAir()) {
                return currentPos;
            }
        }
        return null;
    }

    private BlockPos findWaterY(WorldGenLevel level, BlockPos pos) {
        BlockPos waterSurfacePos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
        BlockPos oceanFloorPos = level.getHeightmapPos(Heightmap.Types.OCEAN_FLOOR_WG, pos);

        int waterSurfaceY = waterSurfacePos.getY();
        int oceanFloorY = oceanFloorPos.getY();

        if (waterSurfaceY - oceanFloorY < 6) {
            return null;
        }

        int targetY = oceanFloorY + (waterSurfaceY - oceanFloorY) / 2;
        BlockPos potentialPos = new BlockPos(pos.getX(), targetY, pos.getZ());

        if (level.getBlockState(potentialPos).is(Blocks.WATER)) {
            return potentialPos;
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

    // --- REWRITTEN METHOD WITH IMPROVED LOGIC ---
    private KarmaNodeSpawnInfo getSpawnInfoForBiome(FeaturePlaceContext<NoneFeatureConfiguration> context, RandomSource random) {
        var biomeHolder = context.level().getBiome(context.origin());

        List<KarmaNodeSpawnInfo> possibleSpawns = new ArrayList<>();

        // Nether
        if (biomeHolder.is(BiomeTags.IS_NETHER)) {
            if (isNodeCategoryEnabled(NodeRarity.RARE))
                possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.CHAOS, NodeRarity.RARE, 0, 128, false, false));
            if (isNodeCategoryEnabled(NodeRarity.COMMON))
                possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.FIRE, NodeRarity.COMMON, 0, 128, true, false));
        }
        // End
        else if (biomeHolder.is(BiomeTags.IS_END)) {
            if (isNodeCategoryEnabled(NodeRarity.MYTHIC)) {
                possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.CREATION, NodeRarity.MYTHIC, 0, 128, false, true));
                possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.DESTRUCTION, NodeRarity.MYTHIC, 0, 128, false, true));
            }
            if (isNodeCategoryEnabled(NodeRarity.RARE))
                possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.ORDER, NodeRarity.RARE, 0, 128, true, false));
        }
        // Overworld
        else {
            if (isNodeCategoryEnabled(NodeRarity.UNCOMMON)) {
                if (biomeHolder.is(Biomes.FLOWER_FOREST) || biomeHolder.is(Biomes.CHERRY_GROVE)) {
                    possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.LIGHT, NodeRarity.UNCOMMON, 150, 320, true, false));
                } else if (biomeHolder.is(Biomes.DEEP_DARK)) { // Made this check more specific
                    possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.DARK, NodeRarity.UNCOMMON, -64, 0, false, false));
                }
            }
            if (isNodeCategoryEnabled(NodeRarity.COMMON)) {
                if (biomeHolder.is(BiomeTags.IS_BADLANDS) || biomeHolder.is(BiomeTags.HAS_VILLAGE_DESERT))
                    possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.FIRE, NodeRarity.COMMON, 50, 128, true, false));
                else if (biomeHolder.is(BiomeTags.IS_OCEAN) || biomeHolder.is(BiomeTags.IS_RIVER) || biomeHolder.is(Biomes.SWAMP) || biomeHolder.is(Biomes.MANGROVE_SWAMP))
                    possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.WATER, NodeRarity.COMMON, 30, 60, false, false));
                else if (biomeHolder.is(BiomeTags.IS_MOUNTAIN))
                    possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.AIR, NodeRarity.COMMON, 100, 320, true, false));
                else if (biomeHolder.is(BiomeTags.IS_FOREST) || biomeHolder.is(BiomeTags.IS_JUNGLE) || biomeHolder.is(BiomeTags.IS_TAIGA))
                    possibleSpawns.add(new KarmaNodeSpawnInfo(KarmaType.EARTH, NodeRarity.COMMON, 0, 80, false, false));
            }
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
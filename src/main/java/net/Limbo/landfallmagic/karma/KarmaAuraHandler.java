package net.Limbo.landfallmagic.karma;

import net.Limbo.landfallmagic.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.phys.Vec3;

public class KarmaAuraHandler {

    /**
     * Spread a specific type of karma from a node to surrounding chunks.
     *
     * @param level The server level
     * @param nodePos The position of the karma node
     * @param type The type of karma to spread
     * @param amount The amount of karma to add
     */
    public static void spreadKarma(ServerLevel level, BlockPos nodePos, KarmaType type, int amount) {
        ChunkPos nodeChunk = new ChunkPos(nodePos);

        // Get saved karma data for the world
        KarmaCapability.KarmaSavedData karmaData = level.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(KarmaCapability.KarmaSavedData::new, KarmaCapability.KarmaSavedData::load),
                "landfallmagic_karma"
        );

        // Spread karma to surrounding chunks using the radius from the config
        int radius = Config.NODE_SPREAD_RADIUS.get();
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                ChunkPos targetChunk = new ChunkPos(nodeChunk.x + dx, nodeChunk.z + dz);
                IKarma karma = karmaData.getKarma(targetChunk);
                karma.addKarma(type, amount);
            }
        }

        // Mark the saved data dirty so changes persist
        karmaData.markDirty();

        // Generate particles at the node chunk based on karma of this type
        if (Config.ENABLE_KARMA_PARTICLES.get()) {
            IKarma nodeKarma = karmaData.getKarma(nodeChunk);
            int karmaLevel = nodeKarma.getKarma(type);
            int maxKarmaValue = Config.MAX_KARMA_VALUE.get();
            double intensityMultiplier = Config.PARTICLE_INTENSITY_MULTIPLIER.get();

            int baseParticleCount = (int) Math.ceil((karmaLevel / (double) maxKarmaValue) * 20);
            int particleCount = Math.min(100, (int) (baseParticleCount * intensityMultiplier));

            if (particleCount <= 0) {
                return;
            }

            RandomSource rand = level.getRandom();
            for (int i = 0; i < particleCount; i++) {
                double offsetX = rand.nextGaussian() * 1.25;
                double offsetY = rand.nextDouble() * 1.5;
                double offsetZ = rand.nextGaussian() * 1.25;

                Vec3 particlePos = Vec3.atCenterOf(nodePos).add(offsetX, offsetY, offsetZ);

                // TODO: Customize particles per karma type
                level.sendParticles(
                        ParticleTypes.SCULK_SOUL,
                        particlePos.x, particlePos.y, particlePos.z,
                        1, 0, 0, 0, 0
                );
            }
        }
    }
}

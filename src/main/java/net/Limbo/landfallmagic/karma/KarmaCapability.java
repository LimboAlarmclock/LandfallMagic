package net.Limbo.landfallmagic.karma;

import net.Limbo.landfallmagic.network.S2CKarmaUpdatePacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;

public class KarmaCapability {
    private static final String KARMA_KEY = "landfallmagic_karma";

    public static IKarma getKarma(LevelChunk chunk) {
        return getKarmaData(chunk).getKarma(new ChunkPos(chunk.getPos().x, chunk.getPos().z));
    }

    private static KarmaSavedData getKarmaData(LevelChunk chunk) {
        if (chunk.getLevel() instanceof ServerLevel serverLevel) {
            return serverLevel.getDataStorage().computeIfAbsent(
                    new SavedData.Factory<>(
                            KarmaSavedData::new,
                            KarmaSavedData::load
                    ),
                    KARMA_KEY
            );
        }
        throw new IllegalStateException("Cannot access karma data on client side");
    }

    /**
     * Sends the karma data of a specific chunk to a player.
     * @param player The player to send the data to.
     * @param pos The position of the chunk to get karma data from.
     */
    public static void sendKarmaToPlayer(ServerPlayer player, ChunkPos pos) {
        if (player.serverLevel() instanceof ServerLevel serverLevel) {
            KarmaSavedData karmaData = serverLevel.getDataStorage().computeIfAbsent(
                    new SavedData.Factory<>(KarmaSavedData::new, KarmaSavedData::load),
                    KARMA_KEY
            );
            IKarma karma = karmaData.getKarma(pos);
            PacketDistributor.sendToPlayer(player, new S2CKarmaUpdatePacket(pos, karma.serializeNBT()));
        }
    }

    public static class KarmaSavedData extends SavedData {
        private final Map<ChunkPos, Karma> karmaMap = new HashMap<>();

        public KarmaSavedData() {
            super();
        }

        public IKarma getKarma(ChunkPos chunkPos) {
            return karmaMap.computeIfAbsent(chunkPos, pos -> new Karma());
        }

        @Override
        public CompoundTag save(CompoundTag compound, HolderLookup.Provider provider) {
            CompoundTag karmaData = new CompoundTag();
            for (Map.Entry<ChunkPos, Karma> entry : karmaMap.entrySet()) {
                ChunkPos pos = entry.getKey();
                Karma karma = entry.getValue();
                String key = pos.x + "," + pos.z;
                karmaData.put(key, karma.serializeNBT());
            }
            compound.put("karma_data", karmaData);
            return compound;
        }

        public static KarmaSavedData load(CompoundTag compound, HolderLookup.Provider provider) {
            KarmaSavedData data = new KarmaSavedData();
            if (compound.contains("karma_data")) {
                CompoundTag karmaData = compound.getCompound("karma_data");
                for (String key : karmaData.getAllKeys()) {
                    try {
                        String[] coords = key.split(",");
                        int x = Integer.parseInt(coords[0]);
                        int z = Integer.parseInt(coords[1]);
                        ChunkPos pos = new ChunkPos(x, z);

                        Karma karma = new Karma();
                        karma.deserializeNBT(karmaData.getCompound(key));
                        data.karmaMap.put(pos, karma);
                    } catch (Exception e) {
                        // Skip invalid entries
                    }
                }
            }
            return data;
        }

        public void markDirty() {
            this.setDirty();
        }
    }
}
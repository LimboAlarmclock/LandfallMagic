package net.Limbo.landfallmagic.karma.client;

import net.Limbo.landfallmagic.network.C2SKarmaRequestPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Client-side karma manager for handling karma data received from server
 */
public class ClientKarmaManager {
    private static final Map<ChunkPos, CompoundTag> clientKarmaData = new HashMap<>();

    /**
     * Update karma data for a specific chunk position
     * Called when receiving S2CKarmaUpdatePacket from server
     */
    public static void updateKarma(ChunkPos pos, CompoundTag karmaData) {
        clientKarmaData.put(pos, karmaData);
        System.out.println("Client: Updated karma for chunk " + pos);

        // You can add additional client-side logic here, such as:
        // - Updating GUI displays
        // - Triggering visual effects
        // - Playing sounds
        // - Updating particle effects
    }

    /**
     * Get karma data for a specific chunk position
     */
    public static CompoundTag getKarma(ChunkPos pos) {
        return clientKarmaData.getOrDefault(pos, new CompoundTag());
    }

    /**
     * Check if we have karma data for a specific chunk
     */
    public static boolean hasKarmaData(ChunkPos pos) {
        return clientKarmaData.containsKey(pos);
    }

    /**
     * Request karma data from the server for a specific chunk
     */
    public static void requestKarmaData(ChunkPos pos) {
        PacketDistributor.sendToServer(new C2SKarmaRequestPacket(pos));
        System.out.println("Client: Requested karma data for chunk " + pos);
    }

    /**
     * Request karma data for the chunk the player is currently in
     */
    public static void requestCurrentChunkKarma() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            ChunkPos playerChunk = new ChunkPos(mc.player.blockPosition());
            requestKarmaData(playerChunk);
        }
    }

    /**
     * Request karma data for a range of chunks around the player
     */
    public static void requestKarmaDataAroundPlayer(int range) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            ChunkPos playerChunk = new ChunkPos(mc.player.blockPosition());
            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    ChunkPos chunkPos = new ChunkPos(playerChunk.x + x, playerChunk.z + z);
                    requestKarmaData(chunkPos);
                }
            }
        }
    }

    /**
     * Clear all karma data (called when disconnecting from world)
     */
    public static void clearAll() {
        clientKarmaData.clear();
    }

    /**
     * Get all loaded karma positions (useful for debugging)
     */
    public static Set<ChunkPos> getLoadedPositions() {
        return new HashSet<>(clientKarmaData.keySet());
    }
}
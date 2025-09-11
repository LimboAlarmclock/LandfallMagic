package net.Limbo.landfallmagic.magic;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.PacketDistributor;

public class PlayerMagicHelper {

    /**
     * Gets the PlayerMagic attachment from a player
     */
    public static PlayerMagic getPlayerMagic(Player player) {
        return player.getData(ModAttachments.PLAYER_MAGIC.get());
    }

    /**
     * Sets a player's magic school
     */
    public static void setPlayerMagicSchool(Player player, MagicSchool school) {
        PlayerMagic playerMagic = getPlayerMagic(player);
        playerMagic.setMagicSchool(school);

        // Mark the attachment as dirty for saving
        player.setData(ModAttachments.PLAYER_MAGIC.get(), playerMagic);

        // Sync to client if on server
        if (player instanceof ServerPlayer serverPlayer) {
            syncMagicToClient(serverPlayer);
        }
    }

    /**
     * Gets a player's current magic school
     */
    public static MagicSchool getPlayerMagicSchool(Player player) {
        return getPlayerMagic(player).getMagicSchool();
    }

    /**
     * Checks if a player has a specific magic school
     */
    public static boolean hasSchool(Player player, MagicSchool school) {
        return getPlayerMagicSchool(player) == school;
    }

    /**
     * Checks if a player has any magic school (not NONE)
     */
    public static boolean hasMagicSchool(Player player) {
        return getPlayerMagicSchool(player) != MagicSchool.NONE;
    }

    private static void syncMagicToClient(ServerPlayer player) {
        // For now, we'll rely on automatic sync
        // You can implement custom packets later if needed
    }
}
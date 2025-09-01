package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.karma.client.ClientKarmaManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

@Mod(value = landfallmagic.MODID, dist = Dist.CLIENT)
// This annotation now correctly targets the MOD bus for the setup methods in this outer class.
@EventBusSubscriber(modid = landfallmagic.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    public ClientEvents(ModContainer container) {
        // Registers the config screen factory. This is correct.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        // **SOLUTION**: Instead of registering this class, we register our new inner class
        // to handle in-game (Forge bus) events separately.
        NeoForge.EVENT_BUS.register(new ForgeBusEvents());
    }

    // This is a MOD bus event, so it stays in this class and works correctly.
    // It is now non-static.
    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        // **UPDATE**: Reference the key mappings from the new inner class.
        event.register(ForgeBusEvents.OPEN_GRIMOIRE_KEY);
        event.register(ForgeBusEvents.KARMA_OVERLAY_KEY);
    }

    // This is also a MOD bus event, so it stays here.
    // It is now non-static.
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        landfallmagic.LOGGER.info("HELLO FROM CLIENT SETUP");
        landfallmagic.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        landfallmagic.LOGGER.info("Initializing Holographic GUI System...");
        landfallmagic.LOGGER.info("  Hologram Stability Time: {}ms", Config.HOLOGRAM_STABILITY_TIME.get());
        landfallmagic.LOGGER.info("  Hologram Particle Count: {}", Config.HOLOGRAM_PARTICLE_COUNT.get());
        landfallmagic.LOGGER.info("  Flicker Intensity: {}", Config.HOLOGRAM_FLICKER_INTENSITY.get());
        landfallmagic.LOGGER.info("  Sound Effects: {}", Config.ENABLE_HOLOGRAM_SOUND.get());
        landfallmagic.LOGGER.info("  Scanning Lines: {}", Config.ENABLE_SCANNING_LINES.get());
        landfallmagic.LOGGER.info("  Alpha Level: {}", Config.HOLOGRAM_ALPHA_LEVEL.get());
    }

    // --- Static Helper Methods for Config Access (can be accessed from anywhere) ---

    public static long getHologramStabilityTime() {
        return Config.HOLOGRAM_STABILITY_TIME.get();
    }

    public static int getHologramParticleCount() {
        return Config.HOLOGRAM_PARTICLE_COUNT.get();
    }

    public static float getHologramFlickerIntensity() {
        return Config.HOLOGRAM_FLICKER_INTENSITY.get().floatValue();
    }

    public static int getHologramAlpha() {
        return Config.HOLOGRAM_ALPHA_LEVEL.get();
    }

    public static boolean isScanningLinesEnabled() {
        return Config.ENABLE_SCANNING_LINES.get();
    }

    public static boolean isKarmaFluctuationEnabled() {
        return Config.ENABLE_KARMA_FLUCTUATION.get();
    }

    public static int getKarmaFluctuationRange() {
        return Config.KARMA_FLUCTUATION_RANGE.get();
    }

    public static boolean isFloatingRunesEnabled() {
        return Config.ENABLE_FLOATING_RUNES.get();
    }

    public static double getRuneOrbitSpeed() {
        return Config.RUNE_ORBIT_SPEED.get();
    }

    public static boolean isTypewriterEffectEnabled() {
        return Config.ENABLE_TYPEWRITER_EFFECT.get();
    }


    // **SOLUTION**: A new inner class dedicated to FORGE bus (in-game) events.
    public static class ForgeBusEvents {

        // Keybindings are moved here.
        public static final KeyMapping OPEN_GRIMOIRE_KEY = new KeyMapping(
                "key.landfallmagic.open_grimoire",
                GLFW.GLFW_KEY_G,
                "key.categories.landfallmagic"
        );

        public static final KeyMapping KARMA_OVERLAY_KEY = new KeyMapping(
                "key.landfallmagic.karma_overlay",
                GLFW.GLFW_KEY_K,
                "key.categories.landfallmagic"
        );

        // **NEW**: Handle client disconnect to clear karma data
        @SubscribeEvent
        public void onClientDisconnect(ClientPlayerNetworkEvent.LoggingOut event) {
            // Clear all karma data when disconnecting from server
            ClientKarmaManager.clearAll();
            landfallmagic.LOGGER.info("Cleared client karma data on disconnect");
        }

        // This method handles in-game key presses and belongs on the FORGE bus.
        @SubscribeEvent
        public void onKeyInput(InputEvent.Key event) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.screen != null || mc.level == null || mc.player == null) {
                return;
            }

            if (OPEN_GRIMOIRE_KEY.consumeClick()) {
                if (hasGrimoireAccess(mc.player)) {
                    // Request karma data from server when opening Grimoire
                    ClientKarmaManager.requestKarmaDataAroundPlayer(2); // Request 5x5 chunk area

                    mc.setScreen(new GrimoireScreen()); // Uncomment when you have GrimoireScreen
                    mc.player.displayClientMessage(Component.translatable("message.landfallmagic.grimoire_opened"), true);
                    playHologramActivationSound(mc);
                } else {
                    mc.player.displayClientMessage(Component.translatable("message.landfallmagic.grimoire_required"), true);
                }
            }

            if (KARMA_OVERLAY_KEY.consumeClick()) {
                // Request current chunk karma data if we don't have it
                net.minecraft.world.level.ChunkPos playerChunk = new net.minecraft.world.level.ChunkPos(mc.player.blockPosition());
                if (!ClientKarmaManager.hasKarmaData(playerChunk)) {
                    ClientKarmaManager.requestCurrentChunkKarma();
                }
                toggleKarmaOverlay();
            }
        }

        // Helper methods used by onKeyInput are also moved here.
        private boolean hasGrimoireAccess(net.minecraft.world.entity.player.Player player) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                if (player.getInventory().getItem(i).getItem() == ModBlocks.GRIMOIRE_BOOK_ITEM.get()) {
                    return true;
                }
            }
            net.minecraft.core.BlockPos playerPos = player.blockPosition();
            for (int x = -5; x <= 5; x++) {
                for (int y = -3; y <= 3; y++) {
                    for (int z = -5; z <= 5; z++) {
                        if (player.level().getBlockState(playerPos.offset(x, y, z)).getBlock() == ModBlocks.GRIMOIRE_BOOK.get()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private void playHologramActivationSound(Minecraft mc) {
            if (Config.ENABLE_HOLOGRAM_SOUND.get()) {
                mc.getSoundManager().play(net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                        net.minecraft.sounds.SoundEvents.BEACON_ACTIVATE,
                        1.0f + (mc.level.random.nextFloat() - 0.5f) * 0.2f,
                        0.3f
                ));
            }
        }

        private void toggleKarmaOverlay() {
            // **ENHANCED**: Show actual karma data if available
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                net.minecraft.world.level.ChunkPos playerChunk = new net.minecraft.world.level.ChunkPos(mc.player.blockPosition());

                if (ClientKarmaManager.hasKarmaData(playerChunk)) {
                    net.minecraft.nbt.CompoundTag karmaData = ClientKarmaManager.getKarma(playerChunk);
                    mc.player.displayClientMessage(Component.literal("Karma Overlay - Chunk " + playerChunk + ": " + karmaData), false);
                } else {
                    mc.player.displayClientMessage(Component.translatable("message.landfallmagic.karma_overlay_no_data"), true);
                }
            }
        }
    }
}
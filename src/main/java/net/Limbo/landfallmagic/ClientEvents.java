package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.client.model.DireWolfModel;
import net.Limbo.landfallmagic.client.model.ModModelLayers;
import net.Limbo.landfallmagic.client.renderer.DireWolfRenderer;
import net.Limbo.landfallmagic.karma.client.ClientKarmaManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.Limbo.landfallmagic.client.renderer.KarmaCondenserRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = landfallmagic.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ForgeBusEvents.OPEN_GRIMOIRE_KEY);
        event.register(ForgeBusEvents.KARMA_OVERLAY_KEY);
    }

    // --- NEW METHODS FOR REGISTERING ENTITY MODEL AND RENDERER ---
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DIRE_WOLF.get(), DireWolfRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DIRE_WOLF_LAYER, DireWolfModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.KARMA_CONDENSER_BE.get(), KarmaCondenserRenderer::new);
    }
    // -----------------------------------------------------------

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RESEARCH_TABLE.get(), RenderType.cutout());
        landfallmagic.LOGGER.info("HELLO FROM CLIENT SETUP");
        landfallmagic.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        landfallmagic.LOGGER.info("Initializing Holographic GUI System...");
        landfallmagic.LOGGER.info("  Hologram Stability Time: {}ms", Config.HOLOGRAM_STABILITY_TIME.get());
        landfallmagic.LOGGER.info("  Hologram Particle Count: {}", Config.HOLOGRAM_PARTICLE_COUNT.get());
        landfallmagic.LOGGER.info("  Flicker Intensity: {}", Config.HOLOGRAM_FLICKER_INTENSITY.get());
        landfallmagic.LOGGER.info("  Sound Effects: {}", Config.ENABLE_HOLOGRAM_SOUND.get());
        landfallmagic.LOGGER.info("  Scanning Lines: {}", Config.ENABLE_SCANNING_LINES.get());
        landfallmagic.LOGGER.info("  Alpha Level: {}", Config.HOLOGRAM_ALPHA_LEVEL.get());

        // We need to register the ForgeBusEvents manually since we removed the constructor
        NeoForge.EVENT_BUS.register(new ForgeBusEvents());
    }

    public static class ForgeBusEvents {

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

        @SubscribeEvent
        public void onClientDisconnect(ClientPlayerNetworkEvent.LoggingOut event) {
            ClientKarmaManager.clearAll();
            landfallmagic.LOGGER.info("Cleared client karma data on disconnect");
        }

        @SubscribeEvent
        public void onKeyInput(InputEvent.Key event) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.screen != null || mc.level == null || mc.player == null) {
                return;
            }

            if (OPEN_GRIMOIRE_KEY.consumeClick()) {
                if (hasGrimoireAccess(mc.player)) {
                    ClientKarmaManager.requestKarmaDataAroundPlayer(2);
                    mc.setScreen(new GrimoireScreen());
                    // This message was missing a key in your lang file, so I'm using a literal for now
                    mc.player.displayClientMessage(Component.literal("Grimoire Opened"), true);
                    playHologramActivationSound(mc);
                } else {
                    mc.player.displayClientMessage(Component.translatable("message.landfallmagic.grimoire_required"), true);
                }
            }

            if (KARMA_OVERLAY_KEY.consumeClick()) {
                net.minecraft.world.level.ChunkPos playerChunk = new net.minecraft.world.level.ChunkPos(mc.player.blockPosition());
                if (!ClientKarmaManager.hasKarmaData(playerChunk)) {
                    ClientKarmaManager.requestCurrentChunkKarma();
                }
                toggleKarmaOverlay();
            }
        }

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
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                net.minecraft.world.level.ChunkPos playerChunk = new net.minecraft.world.level.ChunkPos(mc.player.blockPosition());

                if (ClientKarmaManager.hasKarmaData(playerChunk)) {
                    net.minecraft.nbt.CompoundTag karmaData = ClientKarmaManager.getKarma(playerChunk);
                    mc.player.displayClientMessage(Component.literal("Karma Overlay - Chunk " + playerChunk + ": " + karmaData), false);
                } else {
                    // This was also missing a key, so using a literal
                    mc.player.displayClientMessage(Component.literal("No karma data for this chunk. Press 'K' again to request."), true);
                }
            }
        }
    }
}
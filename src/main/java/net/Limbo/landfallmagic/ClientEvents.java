package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.client.model.DireWolfModel;
import net.Limbo.landfallmagic.client.model.ModModelLayers;
import net.Limbo.landfallmagic.client.renderer.DireWolfRenderer;
import net.Limbo.landfallmagic.client.renderer.KarmaCondenserRenderer;
import net.Limbo.landfallmagic.client.screen.GrimoireScreen;
import net.Limbo.landfallmagic.client.screen.ResearchTableScreen;
import net.Limbo.landfallmagic.karma.client.ClientKarmaManager;
import net.Limbo.landfallmagic.menu.ModMenuTypes;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = landfallmagic.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ForgeBusEvents.KARMA_OVERLAY_KEY);
    }

    @SubscribeEvent
    public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.RESEARCH_TABLE_MENU.get(), ResearchTableScreen::new);
        event.register(ModMenuTypes.GRIMOIRE_MENU.get(), GrimoireScreen::new);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.DIRE_WOLF.get(), DireWolfRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.KARMA_CONDENSER_BE.get(), KarmaCondenserRenderer::new);
        event.registerEntityRenderer(ModEntities.IGNITION_BOLT.get(), ThrownItemRenderer::new);

    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DIRE_WOLF_LAYER, DireWolfModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RESEARCH_TABLE.get(), RenderType.cutout());
        NeoForge.EVENT_BUS.register(new ForgeBusEvents());
    }

    // Inner class for events on the Forge event bus
    public static class ForgeBusEvents {

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

            if (KARMA_OVERLAY_KEY.consumeClick()) {
                net.minecraft.world.level.ChunkPos playerChunk = new net.minecraft.world.level.ChunkPos(mc.player.blockPosition());
                if (!ClientKarmaManager.hasKarmaData(playerChunk)) {
                    ClientKarmaManager.requestCurrentChunkKarma();
                }
                toggleKarmaOverlay();
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
                    mc.player.displayClientMessage(Component.translatable("message.landfallmagic.karma_overlay_no_data"), true);
                }
            }
        }
    }
}
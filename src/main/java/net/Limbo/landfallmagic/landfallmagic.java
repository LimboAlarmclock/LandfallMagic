package net.Limbo.landfallmagic;

import com.mojang.logging.LogUtils;
import net.Limbo.landfallmagic.karma.KarmaCapability;
import net.Limbo.landfallmagic.karma.KarmaCommands;
import net.Limbo.landfallmagic.network.C2SKarmaRequestPacket;
import net.Limbo.landfallmagic.network.S2CKarmaUpdatePacket;
import net.Limbo.landfallmagic.worldgen.ModFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The @Mod annotation stays on the main class
@Mod(landfallmagic.MODID)
public class landfallmagic {
    public static final String MODID = "landfallmagic";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LANDFALL_MAGIC_TAB =
            CREATIVE_MODE_TABS.register("landfall_magic_tab", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.landfallmagic"))
                            .withTabsBefore(CreativeModeTabs.COMBAT)
                            .icon(() -> ModBlocks.DARK_NODE_ITEM.get().getDefaultInstance())
                            .displayItems((parameters, output) -> {
                                // Active nodes
                                output.accept(ModBlocks.FIRE_NODE_ITEM.get());
                                output.accept(ModBlocks.WATER_NODE_ITEM.get());
                                output.accept(ModBlocks.EARTH_NODE_ITEM.get());
                                output.accept(ModBlocks.AIR_NODE_ITEM.get());
                                output.accept(ModBlocks.LIGHT_NODE_ITEM.get());
                                output.accept(ModBlocks.DARK_NODE_ITEM.get());
                                output.accept(ModBlocks.ORDER_NODE_ITEM.get());
                                output.accept(ModBlocks.CHAOS_NODE_ITEM.get());
                                output.accept(ModBlocks.CREATION_NODE_ITEM.get());
                                output.accept(ModBlocks.DESTRUCTION_NODE_ITEM.get());

                                // Dormant nodes
                                output.accept(ModBlocks.DORMANT_FIRE_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_WATER_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_EARTH_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_AIR_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_LIGHT_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_DARK_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_ORDER_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_CHAOS_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_CREATION_NODE_ITEM.get());
                                output.accept(ModBlocks.DORMANT_DESTRUCTION_NODE_ITEM.get());

                                // Equipment blocks
                                output.accept(ModBlocks.RESEARCH_TABLE_ITEM.get());
                                output.accept(ModBlocks.KARMA_CONDENSER_ITEM.get());
                                output.accept(ModBlocks.GRIMOIRE_BOOK_ITEM.get());
                            }).build());

    public landfallmagic(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register deferred registries
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus); // Register world generation features
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register the packet handler on the mod event bus
        modEventBus.addListener(this::registerPackets);

        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM LANDFALL MAGIC COMMON SETUP");
        LOGGER.info("Karma Node World Generation initialized!");
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        KarmaCommands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            // Send karma data for all loaded chunks around the player
            ChunkPos playerPos = new ChunkPos(player.blockPosition());
            int range = 3; // Send karma for 3x3 chunk area around player

            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    ChunkPos chunkPos = new ChunkPos(playerPos.x + x, playerPos.z + z);
                    KarmaCapability.sendKarmaToPlayer(player, chunkPos);
                }
            }

            LOGGER.info("Player {} logged in, sent karma data for {} chunks", player.getName().getString(), (range * 2 + 1) * (range * 2 + 1));
        }
    }

    @SubscribeEvent
    public void onChunkLoad(ChunkEvent.Load event) {
        // Send karma data when chunks are loaded for nearby players
        if (!event.getLevel().isClientSide()) {
            ChunkPos pos = event.getChunk().getPos();
            // Find nearby players and send them the karma data for this chunk
            event.getLevel().players().forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer) {
                    ChunkPos playerChunk = new ChunkPos(serverPlayer.blockPosition());
                    // Send if within reasonable distance
                    if (Math.abs(pos.x - playerChunk.x) <= 5 && Math.abs(pos.z - playerChunk.z) <= 5) {
                        KarmaCapability.sendKarmaToPlayer(serverPlayer, pos);
                    }
                }
            });
        }
    }

    private void registerPackets(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(MODID).versioned("1");

        // Server to Client packets
        registrar.playToClient(
                S2CKarmaUpdatePacket.TYPE,
                S2CKarmaUpdatePacket.STREAM_CODEC,
                S2CKarmaUpdatePacket.Handler::handle
        );

        // Client to Server packets
        registrar.playToServer(
                C2SKarmaRequestPacket.TYPE,
                C2SKarmaRequestPacket.STREAM_CODEC,
                C2SKarmaRequestPacket.Handler::handle
        );
    }
}
package net.Limbo.landfallmagic;

import com.mojang.logging.LogUtils;
import net.Limbo.landfallmagic.karma.KarmaCapability;
import net.Limbo.landfallmagic.karma.KarmaCommands;
import net.Limbo.landfallmagic.karma.KarmaNodeBlock;
import net.Limbo.landfallmagic.network.C2SKarmaRequestPacket;
import net.Limbo.landfallmagic.network.S2CKarmaUpdatePacket;
import net.Limbo.landfallmagic.worldgen.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
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

@Mod(landfallmagic.MODID)
public class landfallmagic {
    public static final String MODID = "landfallmagic";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> LANDFALL_MAGIC_TAB = CREATIVE_MODE_TABS.register("landfall_magic_tab", () ->
            CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.landfallmagic"))
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .icon(() -> ModBlocks.DARK_NODE_ITEM.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        // Utility & Functional Blocks
                        output.accept(ModBlocks.KARMA_FURNACE_ITEM.get());
                        output.accept(ModBlocks.ARCANE_ALTAR_ITEM.get());
                        output.accept(ModBlocks.RITUAL_ALTAR_ITEM.get());
                        output.accept(ModBlocks.RESEARCH_TABLE_ITEM.get());
                        output.accept(ModBlocks.KARMA_CONDENSER_ITEM.get());
                        output.accept(ModBlocks.GRIMOIRE_BOOK_ITEM.get());
                        // Karma Nodes
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
                        // Generation Blocks - Nodes
                        output.accept(ModBlocks.CRYSTAL_CLUSTER_ITEM.get());
                        output.accept(ModBlocks.ENCHANTED_LOG_ITEM.get());
                        output.accept(ModBlocks.ENCHANTED_LEAVES_ITEM.get());
                        output.accept(ModBlocks.ENCHANTED_SAPLING_ITEM.get());
                        output.accept(ModBlocks.CURSED_LOG_ITEM.get());
                        output.accept(ModBlocks.CURSED_LEAVES_ITEM.get());
                        output.accept(ModBlocks.CURSED_ROOTS_ITEM.get());
                        output.accept(ModBlocks.CURSED_SAPLING_ITEM.get());
                        // Add all the new items from ModItems to the tab
                        ModItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
                    }).build());

    public landfallmagic(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // --- ADDED ModItems REGISTRATION ---
        ModItems.ITEMS.register(modEventBus);

        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        modEventBus.addListener(this::registerPackets);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM LANDFALL MAGIC COMMON SETUP");
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        KarmaCommands.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            ChunkPos playerPos = new ChunkPos(player.blockPosition());
            int range = 3;
            for (int x = -range; x <= range; x++) {
                for (int z = -range; z <= range; z++) {
                    ChunkPos chunkPos = new ChunkPos(playerPos.x + x, playerPos.z + z);
                    KarmaCapability.sendKarmaToPlayer(player, chunkPos);
                }
            }
        }
    }

    @SubscribeEvent
    public void onChunkLoadUpdatePlayers(ChunkEvent.Load event) {
        if (!event.getLevel().isClientSide()) {
            ChunkPos pos = event.getChunk().getPos();
            event.getLevel().players().forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer) {
                    ChunkPos playerChunk = new ChunkPos(serverPlayer.blockPosition());
                    if (Math.abs(pos.x - playerChunk.x) <= 5 && Math.abs(pos.z - playerChunk.z) <= 5) {
                        KarmaCapability.sendKarmaToPlayer(serverPlayer, pos);
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public void onChunkLoadActivateNodes(ChunkEvent.Load event) {
        if (event.getLevel() instanceof ServerLevel serverLevel) {
            final ChunkPos chunkPos = event.getChunk().getPos();
            new Thread(() -> {
                for (BlockPos pos : BlockPos.betweenClosed(
                        chunkPos.getMinBlockX(), serverLevel.getMinBuildHeight(), chunkPos.getMinBlockZ(),
                        chunkPos.getMaxBlockX(), serverLevel.getMaxBuildHeight(), chunkPos.getMaxBlockZ()
                )) {
                    BlockState state = event.getChunk().getBlockState(pos);
                    if (state.getBlock() instanceof KarmaNodeBlock && !state.getValue(KarmaNodeBlock.INITIALIZED)) {
                        serverLevel.scheduleTick(new BlockPos(pos), state.getBlock(), Config.NODE_TICK_DELAY.get());
                        serverLevel.setBlock(pos, state.setValue(KarmaNodeBlock.INITIALIZED, true), 3);
                        LOGGER.info("Activated naturally spawned Karma Node at {}", pos);
                    }
                }
            }).start();
        }
    }

    private void registerPackets(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(MODID).versioned("1");
        registrar.playToClient(S2CKarmaUpdatePacket.TYPE, S2CKarmaUpdatePacket.STREAM_CODEC, S2CKarmaUpdatePacket.Handler::handle);
        registrar.playToServer(C2SKarmaRequestPacket.TYPE, C2SKarmaRequestPacket.STREAM_CODEC, C2SKarmaRequestPacket.Handler::handle);
    }
}
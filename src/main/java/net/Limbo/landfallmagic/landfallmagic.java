package net.Limbo.landfallmagic;

import com.mojang.logging.LogUtils;
import net.Limbo.landfallmagic.entity.DireWolfEntity;
import net.Limbo.landfallmagic.karma.KarmaCapability;
import net.Limbo.landfallmagic.karma.KarmaCommands;
import net.Limbo.landfallmagic.karma.KarmaNodeBlock;
import net.Limbo.landfallmagic.magic.ModAttachments;
import net.Limbo.landfallmagic.network.C2SKarmaRequestPacket;
import net.Limbo.landfallmagic.network.S2CKarmaUpdatePacket;
import net.Limbo.landfallmagic.spell.effect.SpellEffectRegistry;
import net.Limbo.landfallmagic.worldgen.ModFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.ChunkEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import net.Limbo.landfallmagic.spell.SpellRecipeRegistry;
import net.Limbo.landfallmagic.datagen.ModDataComponents;

@Mod(landfallmagic.MODID)
@EventBusSubscriber(modid = landfallmagic.MODID, bus = EventBusSubscriber.Bus.MOD)
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
                        ModItems.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
                        ModBlocks.ITEMS.getEntries().forEach(item -> output.accept(item.get()));
                    }).build());

    public landfallmagic(IEventBus modEventBus, ModContainer modContainer) {
        ModArmorMaterials.VERDANT.getDelegate();

        ModParticles.PARTICLE_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlocks.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ModRegistries.ARMOR_MATERIALS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModAttachments.ATTACHMENT_TYPES.register(modEventBus);
        ModDataComponents.DATA_COMPONENTS.register(modEventBus);
        net.Limbo.landfallmagic.menu.ModMenuTypes.MENUS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerPackets);
        modEventBus.addListener(this::registerSpawnPlacements);

        NeoForge.EVENT_BUS.register(new ServerEvents());

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    public static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModDataSerializers.register();
            SpellRecipeRegistry.registerRecipes();
            SpellEffectRegistry.registerSpellEffects();
        });
        LOGGER.info("HELLO FROM LANDFALL MAGIC COMMON SETUP");
    }

    private void registerPackets(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(MODID).versioned("1");
        registrar.playToClient(S2CKarmaUpdatePacket.TYPE, S2CKarmaUpdatePacket.STREAM_CODEC, S2CKarmaUpdatePacket.Handler::handle);
        registrar.playToServer(C2SKarmaRequestPacket.TYPE, C2SKarmaRequestPacket.STREAM_CODEC, C2SKarmaRequestPacket.Handler::handle);
    }

    private void registerSpawnPlacements(final RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.DIRE_WOLF.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DIRE_WOLF.get(), DireWolfEntity.createAttributes().build());
    }

    public static class ServerEvents {
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
    }
}
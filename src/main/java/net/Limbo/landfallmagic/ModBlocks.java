package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.blocks.*;
import net.Limbo.landfallmagic.blocks.tree.*;
import net.Limbo.landfallmagic.karma.KarmaNodeBlock;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.Limbo.landfallmagic.worldgen.BiomeModifications;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(landfallmagic.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(landfallmagic.MODID);

    // --- ACTIVE KARMA NODES ---
    public static final DeferredBlock<Block> FIRE_NODE = BLOCKS.register("fire_node",
            () -> new KarmaNodeBlock(KarmaType.FIRE,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_RED)
                            .strength(4.0F, 8.0F)
                            .sound(SoundType.STONE)
                            .lightLevel(state -> 12)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> FIRE_NODE_ITEM = ITEMS.registerSimpleBlockItem("fire_node", FIRE_NODE);

    public static final DeferredBlock<Block> WATER_NODE = BLOCKS.register("water_node",
            () -> new KarmaNodeBlock(KarmaType.WATER,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLUE)
                            .strength(3.5F, 7.0F)
                            .sound(SoundType.STONE)
                            .lightLevel(state -> 8)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> WATER_NODE_ITEM = ITEMS.registerSimpleBlockItem("water_node", WATER_NODE);

    public static final DeferredBlock<Block> EARTH_NODE = BLOCKS.register("earth_node",
            () -> new KarmaNodeBlock(KarmaType.EARTH,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BROWN)
                            .strength(6.0F, 12.0F)
                            .sound(SoundType.STONE)
                            .lightLevel(state -> 4)
                            .requiresCorrectToolForDrops()));
    public static final DeferredItem<BlockItem> EARTH_NODE_ITEM = ITEMS.registerSimpleBlockItem("earth_node", EARTH_NODE);

    public static final DeferredBlock<Block> AIR_NODE = BLOCKS.register("air_node",
            () -> new KarmaNodeBlock(KarmaType.AIR,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_LIGHT_BLUE)
                            .strength(2.0F, 4.0F)
                            .sound(SoundType.GLASS)
                            .lightLevel(state -> 6)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> AIR_NODE_ITEM = ITEMS.registerSimpleBlockItem("air_node", AIR_NODE);

    public static final DeferredBlock<Block> DARK_NODE = BLOCKS.register("dark_node",
            () -> new KarmaNodeBlock(KarmaType.DARK,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_BLACK)
                            .strength(5.0F, 10.0F)
                            .sound(SoundType.SOUL_SOIL)
                            .lightLevel(state -> 2)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> DARK_NODE_ITEM = ITEMS.registerSimpleBlockItem("dark_node", DARK_NODE);

    public static final DeferredBlock<Block> LIGHT_NODE = BLOCKS.register("light_node",
            () -> new KarmaNodeBlock(KarmaType.LIGHT,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_YELLOW)
                            .strength(3.0F, 6.0F)
                            .sound(SoundType.AMETHYST)
                            .lightLevel(state -> 15)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> LIGHT_NODE_ITEM = ITEMS.registerSimpleBlockItem("light_node", LIGHT_NODE);

    public static final DeferredBlock<Block> CHAOS_NODE = BLOCKS.register("chaos_node",
            () -> new KarmaNodeBlock(KarmaType.CHAOS,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_PURPLE)
                            .strength(4.5F, 9.0F)
                            .sound(SoundType.NETHER_WART)
                            .lightLevel(state -> 10)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> CHAOS_NODE_ITEM = ITEMS.registerSimpleBlockItem("chaos_node", CHAOS_NODE);

    public static final DeferredBlock<Block> ORDER_NODE = BLOCKS.register("order_node",
            () -> new KarmaNodeBlock(KarmaType.ORDER,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_CYAN)
                            .strength(7.0F, 14.0F)
                            .sound(SoundType.METAL)
                            .lightLevel(state -> 11)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> ORDER_NODE_ITEM = ITEMS.registerSimpleBlockItem("order_node", ORDER_NODE);

    public static final DeferredBlock<Block> CREATION_NODE = BLOCKS.register("creation_node",
            () -> new KarmaNodeBlock(KarmaType.CREATION,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_GREEN)
                            .strength(3.5F, 7.0F)
                            .sound(SoundType.MOSS)
                            .lightLevel(state -> 9)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> CREATION_NODE_ITEM = ITEMS.registerSimpleBlockItem("creation_node", CREATION_NODE);

    public static final DeferredBlock<Block> DESTRUCTION_NODE = BLOCKS.register("destruction_node",
            () -> new KarmaNodeBlock(KarmaType.DESTRUCTION,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_GRAY)
                            .strength(8.0F, 16.0F)
                            .sound(SoundType.NETHERITE_BLOCK)
                            .lightLevel(state -> 3)
                            .requiresCorrectToolForDrops()
                            .noOcclusion()));
    public static final DeferredItem<BlockItem> DESTRUCTION_NODE_ITEM = ITEMS.registerSimpleBlockItem("destruction_node", DESTRUCTION_NODE);

    // --- OTHER BLOCKS ---
    public static final DeferredBlock<Block> RESEARCH_TABLE = BLOCKS.register("research_table", ResearchTableBlock::new);
    public static final DeferredItem<BlockItem> RESEARCH_TABLE_ITEM = ITEMS.registerSimpleBlockItem("research_table", RESEARCH_TABLE);

    public static final DeferredBlock<Block> KARMA_CONDENSER = BLOCKS.register("karma_condenser", KarmaCondenserBlock::new);
    public static final DeferredItem<BlockItem> KARMA_CONDENSER_ITEM = ITEMS.registerSimpleBlockItem("karma_condenser", KARMA_CONDENSER);

    public static final DeferredBlock<Block> GRIMOIRE_BOOK = BLOCKS.register("grimoire_book", GrimoireBookBlock::new);
    public static final DeferredItem<BlockItem> GRIMOIRE_BOOK_ITEM = ITEMS.registerSimpleBlockItem("grimoire_book", GRIMOIRE_BOOK);

    public static final DeferredBlock<Block> KARMA_FURNACE = BLOCKS.register("karma_furnace", KarmaFurnaceBlock::new);
    public static final DeferredItem<BlockItem> KARMA_FURNACE_ITEM = ITEMS.registerSimpleBlockItem("karma_furnace", KARMA_FURNACE);

    public static final DeferredBlock<Block> ARCANE_ALTAR = BLOCKS.register("arcane_altar", ArcaneAltarBlock::new);
    public static final DeferredItem<BlockItem> ARCANE_ALTAR_ITEM = ITEMS.registerSimpleBlockItem("arcane_altar", ARCANE_ALTAR);

    public static final DeferredBlock<Block> RITUAL_ALTAR = BLOCKS.register("ritual_altar", RitualAltarBlock::new);
    public static final DeferredItem<BlockItem> RITUAL_ALTAR_ITEM = ITEMS.registerSimpleBlockItem("ritual_altar", RITUAL_ALTAR);

    public static final DeferredBlock<Block> CRYSTAL_CLUSTER = BLOCKS.register("crystal_cluster", CrystalClusterBlock::new);
    public static final DeferredItem<BlockItem> CRYSTAL_CLUSTER_ITEM = ITEMS.registerSimpleBlockItem("crystal_cluster", CRYSTAL_CLUSTER);

    // --- TREE BLOCKS ---
    public static final DeferredBlock<Block> ENCHANTED_LOG = BLOCKS.register("enchanted_log", EnchantedLogBlock::new);
    public static final DeferredItem<BlockItem> ENCHANTED_LOG_ITEM = ITEMS.registerSimpleBlockItem("enchanted_log", ENCHANTED_LOG);
    public static final DeferredBlock<Block> ENCHANTED_LEAVES = BLOCKS.register("enchanted_leaves", EnchantedLeavesBlock::new);
    public static final DeferredItem<BlockItem> ENCHANTED_LEAVES_ITEM = ITEMS.registerSimpleBlockItem("enchanted_leaves", ENCHANTED_LEAVES);
    public static final DeferredBlock<Block> ENCHANTED_SAPLING = BLOCKS.register("enchanted_sapling",
            () -> new EnchantedSaplingBlock(new TreeGrower("enchanted", Optional.of(BiomeModifications.ENCHANTED_TREE_CONFIGURED), Optional.empty(), Optional.empty())));
    public static final DeferredItem<BlockItem> ENCHANTED_SAPLING_ITEM = ITEMS.registerSimpleBlockItem("enchanted_sapling", ENCHANTED_SAPLING);

    public static final DeferredBlock<Block> CURSED_LOG = BLOCKS.register("cursed_log", CursedLogBlock::new);
    public static final DeferredItem<BlockItem> CURSED_LOG_ITEM = ITEMS.registerSimpleBlockItem("cursed_log", CURSED_LOG);
    public static final DeferredBlock<Block> CURSED_LEAVES = BLOCKS.register("cursed_leaves", CursedLeavesBlock::new);
    public static final DeferredItem<BlockItem> CURSED_LEAVES_ITEM = ITEMS.registerSimpleBlockItem("cursed_leaves", CURSED_LEAVES);
    public static final DeferredBlock<Block> CURSED_ROOTS = BLOCKS.register("cursed_roots", CursedRootsBlock::new);
    public static final DeferredItem<BlockItem> CURSED_ROOTS_ITEM = ITEMS.registerSimpleBlockItem("cursed_roots", CURSED_ROOTS);

    public static final DeferredBlock<Block> CURSED_SAPLING = BLOCKS.register("cursed_sapling",
            () -> new CursedSaplingBlock(new TreeGrower("cursed", Optional.of(BiomeModifications.CURSED_TREE_CONFIGURED), Optional.empty(), Optional.empty())));
    public static final DeferredItem<BlockItem> CURSED_SAPLING_ITEM = ITEMS.registerSimpleBlockItem("cursed_sapling", CURSED_SAPLING);
}
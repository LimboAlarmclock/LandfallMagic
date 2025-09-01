package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.blocks.GrimoireBookBlock;
import net.Limbo.landfallmagic.blocks.KarmaCondenserBlock;
import net.Limbo.landfallmagic.blocks.ResearchTableBlock;
import net.Limbo.landfallmagic.karma.KarmaNodeBlock;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(landfallmagic.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(landfallmagic.MODID);

    // --- ACTIVE KARMA NODES ---
    // Each node is now a KarmaNodeBlock with its specific KarmaType.
    // The amount of karma generated per tick is pulled from the config file.
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
                            .noOcclusion()
                            .randomTicks()));
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

    // --- DORMANT VERSIONS ---
    // These are left as simple Blocks since they have no karma logic.
    public static final DeferredBlock<Block> DORMANT_FIRE_NODE = BLOCKS.registerSimpleBlock("dormant_fire_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).strength(3.0F, 6.0F).sound(SoundType.STONE).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_FIRE_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_fire_node", DORMANT_FIRE_NODE);

    public static final DeferredBlock<Block> DORMANT_WATER_NODE = BLOCKS.registerSimpleBlock("dormant_water_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLUE).strength(2.5F, 5.0F).sound(SoundType.STONE).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_WATER_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_water_node", DORMANT_WATER_NODE);

    public static final DeferredBlock<Block> DORMANT_EARTH_NODE = BLOCKS.registerSimpleBlock("dormant_earth_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(5.0F, 10.0F).sound(SoundType.STONE).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_EARTH_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_earth_node", DORMANT_EARTH_NODE);

    public static final DeferredBlock<Block> DORMANT_AIR_NODE = BLOCKS.registerSimpleBlock("dormant_air_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5F, 3.0F).sound(SoundType.GLASS).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_AIR_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_air_node", DORMANT_AIR_NODE);

    public static final DeferredBlock<Block> DORMANT_DARK_NODE = BLOCKS.registerSimpleBlock("dormant_dark_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(4.0F, 8.0F).sound(SoundType.SOUL_SOIL).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_DARK_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_dark_node", DORMANT_DARK_NODE);

    public static final DeferredBlock<Block> DORMANT_LIGHT_NODE = BLOCKS.registerSimpleBlock("dormant_light_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(2.0F, 4.0F).sound(SoundType.AMETHYST).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_LIGHT_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_light_node", DORMANT_LIGHT_NODE);

    public static final DeferredBlock<Block> DORMANT_CHAOS_NODE = BLOCKS.registerSimpleBlock("dormant_chaos_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(3.5F, 7.0F).sound(SoundType.NETHER_WART).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_CHAOS_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_chaos_node", DORMANT_CHAOS_NODE);

    public static final DeferredBlock<Block> DORMANT_ORDER_NODE = BLOCKS.registerSimpleBlock("dormant_order_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(6.0F, 12.0F).sound(SoundType.METAL).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_ORDER_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_order_node", DORMANT_ORDER_NODE);

    public static final DeferredBlock<Block> DORMANT_CREATION_NODE = BLOCKS.registerSimpleBlock("dormant_creation_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.5F, 5.0F).sound(SoundType.MOSS).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_CREATION_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_creation_node", DORMANT_CREATION_NODE);

    public static final DeferredBlock<Block> DORMANT_DESTRUCTION_NODE = BLOCKS.registerSimpleBlock("dormant_destruction_node", BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(7.0F, 14.0F).sound(SoundType.NETHERITE_BLOCK).requiresCorrectToolForDrops());
    public static final DeferredItem<BlockItem> DORMANT_DESTRUCTION_NODE_ITEM = ITEMS.registerSimpleBlockItem("dormant_destruction_node", DORMANT_DESTRUCTION_NODE);

    public static final DeferredBlock<Block> RESEARCH_TABLE = BLOCKS.register("research_table",
            () -> new ResearchTableBlock());
    public static final DeferredItem<BlockItem> RESEARCH_TABLE_ITEM = ITEMS.registerSimpleBlockItem("research_table", RESEARCH_TABLE);

    public static final DeferredBlock<Block> KARMA_CONDENSER = BLOCKS.register("karma_condenser",
            () -> new KarmaCondenserBlock());
    public static final DeferredItem<BlockItem> KARMA_CONDENSER_ITEM = ITEMS.registerSimpleBlockItem("karma_condenser", KARMA_CONDENSER);

    public static final DeferredBlock<Block> GRIMOIRE_BOOK = BLOCKS.register("grimoire_book", () -> new GrimoireBookBlock());
    public static final DeferredItem<BlockItem> GRIMOIRE_BOOK_ITEM = ITEMS.registerSimpleBlockItem("grimoire_book", GRIMOIRE_BOOK);

}

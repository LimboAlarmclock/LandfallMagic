package net.Limbo.landfallmagic;

import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(landfallmagic.MODID);

    // --- ESSENCES ---
    public static final DeferredItem<Item> FIRE_ESSENCE = ITEMS.register("fire_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WATER_ESSENCE = ITEMS.register("water_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> EARTH_ESSENCE = ITEMS.register("earth_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AIR_ESSENCE = ITEMS.register("air_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LIGHT_ESSENCE = ITEMS.register("light_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DARK_ESSENCE = ITEMS.register("dark_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ORDER_ESSENCE = ITEMS.register("order_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHAOS_ESSENCE = ITEMS.register("chaos_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CREATION_ESSENCE = ITEMS.register("creation_essence", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DESTRUCTION_ESSENCE = ITEMS.register("destruction_essence", () -> new Item(new Item.Properties()));

    // --- ORGANIC & MOB DROPS ---
    public static final DeferredItem<Item> LIVING_BARK = ITEMS.register("living_bark", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VERDANT_FIBERS = ITEMS.register("verdant_fibers", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WILDHIDE = ITEMS.register("wildhide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WOLF_FANG = ITEMS.register("wolf_fang", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ARCANE_HIDE = ITEMS.register("arcane_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BONE_SHARDS = ITEMS.register("bone_shards", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OCCULT_HIDE = ITEMS.register("occult_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CURSED_FANG = ITEMS.register("cursed_fang", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DARK_ASH = ITEMS.register("dark_ash", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MAGMA_HIDE = ITEMS.register("magma_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SCALEHIDE = ITEMS.register("scalehide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BURROW_HIDE = ITEMS.register("burrow_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> FEATHERED_HIDE = ITEMS.register("feathered_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ELYTRA_FRAGMENT = ITEMS.register("elytra_fragment", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RADIANT_HIDE = ITEMS.register("radiant_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> UMBRAL_HIDE = ITEMS.register("umbral_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WITHER_BONE = ITEMS.register("wither_bone", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> PATTERNED_HIDE = ITEMS.register("patterned_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHAOTIC_HIDE = ITEMS.register("chaotic_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> AETHER_SILK = ITEMS.register("aether_silk", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MYTHIC_BEAST_HIDE = ITEMS.register("mythic_beast_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUIN_HIDE = ITEMS.register("ruin_hide", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BEACON_SHARD = ITEMS.register("beacon_shard", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BEACON_CORE = ITEMS.register("beacon_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHIELD_CORE = ITEMS.register("shield_core", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OBLIVION_SHARD = ITEMS.register("oblivion_shard", () -> new Item(new Item.Properties()));

    // --- CRAFTED & INFUSED MATERIALS ---
    public static final DeferredItem<Item> ROOTSTEEL_INGOT = ITEMS.register("rootsteel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ARCANE_DUST = ITEMS.register("arcane_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUNIC_THREAD = ITEMS.register("runic_thread", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MANA_PEARL = ITEMS.register("mana_pearl", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RUNEFOrged_ALLOY = ITEMS.register("runeforged_alloy", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MANA_CRYSTAL = ITEMS.register("mana_crystal", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLOODSTAINED_CLOTH = ITEMS.register("bloodstained_cloth", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SACRIFICIAL_STEEL = ITEMS.register("sacrificial_steel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLAZEFIBER_CLOTH = ITEMS.register("blazefiber_cloth", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> MOLTEN_IRON = ITEMS.register("molten_iron", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> TIDECLOTH = ITEMS.register("tidecloth", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CORALSTEEL_INGOT = ITEMS.register("coralsteel_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STONEWOVEN_CLOTH = ITEMS.register("stonewoven_cloth", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ORE_DUST = ITEMS.register("ore_dust", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEO_ALLOY = ITEMS.register("geo_alloy", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CLOUDSILK = ITEMS.register("cloudsilk", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CHARGED_QUARTZ = ITEMS.register("charged_quartz", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SKY_ALLOY = ITEMS.register("sky_alloy", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SUNTHREAD = ITEMS.register("sunthread", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> BLESSED_GOLD_INGOT = ITEMS.register("blessed_gold_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SHADOWCLOTH = ITEMS.register("shadowcloth", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> OBSIDIAN_STEEL = ITEMS.register("obsidian_steel", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> LATTICE_THREAD = ITEMS.register("lattice_thread", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> GEOMETRIC_INGOT = ITEMS.register("geometric_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> WARPCLOTH = ITEMS.register("warpcloth", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> DISCORD_ALLOY = ITEMS.register("discord_alloy", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> STAR_ALLOY = ITEMS.register("star_alloy", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> ASHWEAVE = ITEMS.register("ashweave", () -> new Item(new Item.Properties()));
}
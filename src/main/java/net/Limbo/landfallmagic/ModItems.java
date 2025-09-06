package net.Limbo.landfallmagic;

import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.ArmorItem;
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

    // --- ARMOR ---
    // Verdant Mantle
    public static final DeferredItem<Item> VERDANT_HELMET = ITEMS.register("verdant_helmet",
            () -> new ArmorItem(ModArmorMaterials.VERDANT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> VERDANT_CHESTPLATE = ITEMS.register("verdant_chestplate",
            () -> new ArmorItem(ModArmorMaterials.VERDANT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> VERDANT_LEGGINGS = ITEMS.register("verdant_leggings",
            () -> new ArmorItem(ModArmorMaterials.VERDANT, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> VERDANT_BOOTS = ITEMS.register("verdant_boots",
            () -> new ArmorItem(ModArmorMaterials.VERDANT, ArmorItem.Type.BOOTS, new Item.Properties()));


    // Wildhide Armor
    public static final DeferredItem<Item> WILDHIDE_HELMET = ITEMS.register("wildhide_helmet",
            () -> new ArmorItem(ModArmorMaterials.WILDHIDE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> WILDHIDE_CHESTPLATE = ITEMS.register("wildhide_chestplate",
            () -> new ArmorItem(ModArmorMaterials.WILDHIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> WILDHIDE_LEGGINGS = ITEMS.register("wildhide_leggings",
            () -> new ArmorItem(ModArmorMaterials.WILDHIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> WILDHIDE_BOOTS = ITEMS.register("wildhide_boots",
            () -> new ArmorItem(ModArmorMaterials.WILDHIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Stonebark Guard
    public static final DeferredItem<Item> STONEBARK_HELMET = ITEMS.register("stonebark_helmet",
            () -> new ArmorItem(ModArmorMaterials.STONEBARK, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> STONEBARK_CHESTPLATE = ITEMS.register("stonebark_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STONEBARK, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> STONEBARK_LEGGINGS = ITEMS.register("stonebark_leggings",
            () -> new ArmorItem(ModArmorMaterials.STONEBARK, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> STONEBARK_BOOTS = ITEMS.register("stonebark_boots",
            () -> new ArmorItem(ModArmorMaterials.STONEBARK, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Arcanist's Vestments
    public static final DeferredItem<Item> ARCANIST_HELMET = ITEMS.register("arcanist_helmet",
            () -> new ArmorItem(ModArmorMaterials.ARCANIST, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> ARCANIST_CHESTPLATE = ITEMS.register("arcanist_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ARCANIST, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> ARCANIST_LEGGINGS = ITEMS.register("arcanist_leggings",
            () -> new ArmorItem(ModArmorMaterials.ARCANIST, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> ARCANIST_BOOTS = ITEMS.register("arcanist_boots",
            () -> new ArmorItem(ModArmorMaterials.ARCANIST, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Spellbinder's Wraps
    public static final DeferredItem<Item> SPELLBINDER_HELMET = ITEMS.register("spellbinder_helmet",
            () -> new ArmorItem(ModArmorMaterials.SPELLBINDER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> SPELLBINDER_CHESTPLATE = ITEMS.register("spellbinder_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SPELLBINDER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> SPELLBINDER_LEGGINGS = ITEMS.register("spellbinder_leggings",
            () -> new ArmorItem(ModArmorMaterials.SPELLBINDER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> SPELLBINDER_BOOTS = ITEMS.register("spellbinder_boots",
            () -> new ArmorItem(ModArmorMaterials.SPELLBINDER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Runeforged Aegis
    public static final DeferredItem<Item> RUNEFORGED_HELMET = ITEMS.register("runeforged_helmet",
            () -> new ArmorItem(ModArmorMaterials.RUNEFORGED, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> RUNEFORGED_CHESTPLATE = ITEMS.register("runeforged_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RUNEFORGED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> RUNEFORGED_LEGGINGS = ITEMS.register("runeforged_leggings",
            () -> new ArmorItem(ModArmorMaterials.RUNEFORGED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> RUNEFORGED_BOOTS = ITEMS.register("runeforged_boots",
            () -> new ArmorItem(ModArmorMaterials.RUNEFORGED, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Occult Shroud
    public static final DeferredItem<Item> OCCULT_HELMET = ITEMS.register("occult_helmet",
            () -> new ArmorItem(ModArmorMaterials.OCCULT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> OCCULT_CHESTPLATE = ITEMS.register("occult_chestplate",
            () -> new ArmorItem(ModArmorMaterials.OCCULT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> OCCULT_LEGGINGS = ITEMS.register("occult_leggings",
            () -> new ArmorItem(ModArmorMaterials.OCCULT, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> OCCULT_BOOTS = ITEMS.register("occult_boots",
            () -> new ArmorItem(ModArmorMaterials.OCCULT, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Hexskin
    public static final DeferredItem<Item> HEXSKIN_HELMET = ITEMS.register("hexskin_helmet",
            () -> new ArmorItem(ModArmorMaterials.HEXSKIN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> HEXSKIN_CHESTPLATE = ITEMS.register("hexskin_chestplate",
            () -> new ArmorItem(ModArmorMaterials.HEXSKIN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> HEXSKIN_LEGGINGS = ITEMS.register("hexskin_leggings",
            () -> new ArmorItem(ModArmorMaterials.HEXSKIN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> HEXSKIN_BOOTS = ITEMS.register("hexskin_boots",
            () -> new ArmorItem(ModArmorMaterials.HEXSKIN, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Bloodforged Mail
    public static final DeferredItem<Item> BLOODFORGED_HELMET = ITEMS.register("bloodforged_helmet",
            () -> new ArmorItem(ModArmorMaterials.BLOODFORGED, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> BLOODFORGED_CHESTPLATE = ITEMS.register("bloodforged_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BLOODFORGED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> BLOODFORGED_LEGGINGS = ITEMS.register("bloodforged_leggings",
            () -> new ArmorItem(ModArmorMaterials.BLOODFORGED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> BLOODFORGED_BOOTS = ITEMS.register("bloodforged_boots",
            () -> new ArmorItem(ModArmorMaterials.BLOODFORGED, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Emberweave
    public static final DeferredItem<Item> EMBERWEAVE_HELMET = ITEMS.register("emberweave_helmet",
            () -> new ArmorItem(ModArmorMaterials.EMBERWEAVE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> EMBERWEAVE_CHESTPLATE = ITEMS.register("emberweave_chestplate",
            () -> new ArmorItem(ModArmorMaterials.EMBERWEAVE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> EMBERWEAVE_LEGGINGS = ITEMS.register("emberweave_leggings",
            () -> new ArmorItem(ModArmorMaterials.EMBERWEAVE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> EMBERWEAVE_BOOTS = ITEMS.register("emberweave_boots",
            () -> new ArmorItem(ModArmorMaterials.EMBERWEAVE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Ashenhide
    public static final DeferredItem<Item> ASHENHIDE_HELMET = ITEMS.register("ashenhide_helmet",
            () -> new ArmorItem(ModArmorMaterials.ASHENHIDE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> ASHENHIDE_CHESTPLATE = ITEMS.register("ashenhide_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ASHENHIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> ASHENHIDE_LEGGINGS = ITEMS.register("ashenhide_leggings",
            () -> new ArmorItem(ModArmorMaterials.ASHENHIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> ASHENHIDE_BOOTS = ITEMS.register("ashenhide_boots",
            () -> new ArmorItem(ModArmorMaterials.ASHENHIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Inferno Guard
    public static final DeferredItem<Item> INFERNO_HELMET = ITEMS.register("inferno_helmet",
            () -> new ArmorItem(ModArmorMaterials.INFERNO, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> INFERNO_CHESTPLATE = ITEMS.register("inferno_chestplate",
            () -> new ArmorItem(ModArmorMaterials.INFERNO, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> INFERNO_LEGGINGS = ITEMS.register("inferno_leggings",
            () -> new ArmorItem(ModArmorMaterials.INFERNO, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> INFERNO_BOOTS = ITEMS.register("inferno_boots",
            () -> new ArmorItem(ModArmorMaterials.INFERNO, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Tidecaller
    public static final DeferredItem<Item> TIDECALLER_HELMET = ITEMS.register("tidecaller_helmet",
            () -> new ArmorItem(ModArmorMaterials.TIDECALLER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> TIDECALLER_CHESTPLATE = ITEMS.register("tidecaller_chestplate",
            () -> new ArmorItem(ModArmorMaterials.TIDECALLER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> TIDECALLER_LEGGINGS = ITEMS.register("tidecaller_leggings",
            () -> new ArmorItem(ModArmorMaterials.TIDECALLER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> TIDECALLER_BOOTS = ITEMS.register("tidecaller_boots",
            () -> new ArmorItem(ModArmorMaterials.TIDECALLER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Brinehide
    public static final DeferredItem<Item> BRINEHIDE_HELMET = ITEMS.register("brinehide_helmet",
            () -> new ArmorItem(ModArmorMaterials.BRINEHIDE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> BRINEHIDE_CHESTPLATE = ITEMS.register("brinehide_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BRINEHIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> BRINEHIDE_LEGGINGS = ITEMS.register("brinehide_leggings",
            () -> new ArmorItem(ModArmorMaterials.BRINEHIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> BRINEHIDE_BOOTS = ITEMS.register("brinehide_boots",
            () -> new ArmorItem(ModArmorMaterials.BRINEHIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Leviathan Shell
    public static final DeferredItem<Item> LEVIATHAN_HELMET = ITEMS.register("leviathan_helmet",
            () -> new ArmorItem(ModArmorMaterials.LEVIATHAN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> LEVIATHAN_CHESTPLATE = ITEMS.register("leviathan_chestplate",
            () -> new ArmorItem(ModArmorMaterials.LEVIATHAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> LEVIATHAN_LEGGINGS = ITEMS.register("leviathan_leggings",
            () -> new ArmorItem(ModArmorMaterials.LEVIATHAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> LEVIATHAN_BOOTS = ITEMS.register("leviathan_boots",
            () -> new ArmorItem(ModArmorMaterials.LEVIATHAN, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Stoneveil
    public static final DeferredItem<Item> STONEVEIL_HELMET = ITEMS.register("stoneveil_helmet",
            () -> new ArmorItem(ModArmorMaterials.STONEVEIL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> STONEVEIL_CHESTPLATE = ITEMS.register("stoneveil_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STONEVEIL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> STONEVEIL_LEGGINGS = ITEMS.register("stoneveil_leggings",
            () -> new ArmorItem(ModArmorMaterials.STONEVEIL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> STONEVEIL_BOOTS = ITEMS.register("stoneveil_boots",
            () -> new ArmorItem(ModArmorMaterials.STONEVEIL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Burrowhide
    public static final DeferredItem<Item> BURROWHIDE_HELMET = ITEMS.register("burrowhide_helmet",
            () -> new ArmorItem(ModArmorMaterials.BURROWHIDE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> BURROWHIDE_CHESTPLATE = ITEMS.register("burrowhide_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BURROWHIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> BURROWHIDE_LEGGINGS = ITEMS.register("burrowhide_leggings",
            () -> new ArmorItem(ModArmorMaterials.BURROWHIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> BURROWHIDE_BOOTS = ITEMS.register("burrowhide_boots",
            () -> new ArmorItem(ModArmorMaterials.BURROWHIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Mountainbreaker
    public static final DeferredItem<Item> MOUNTAINBREAKER_HELMET = ITEMS.register("mountainbreaker_helmet",
            () -> new ArmorItem(ModArmorMaterials.MOUNTAINBREAKER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> MOUNTAINBREAKER_CHESTPLATE = ITEMS.register("mountainbreaker_chestplate",
            () -> new ArmorItem(ModArmorMaterials.MOUNTAINBREAKER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> MOUNTAINBREAKER_LEGGINGS = ITEMS.register("mountainbreaker_leggings",
            () -> new ArmorItem(ModArmorMaterials.MOUNTAINBREAKER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> MOUNTAINBREAKER_BOOTS = ITEMS.register("mountainbreaker_boots",
            () -> new ArmorItem(ModArmorMaterials.MOUNTAINBREAKER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Skyshroud
    public static final DeferredItem<Item> SKYSHROUD_HELMET = ITEMS.register("skyshroud_helmet",
            () -> new ArmorItem(ModArmorMaterials.SKYSHROUD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> SKYSHROUD_CHESTPLATE = ITEMS.register("skyshroud_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SKYSHROUD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> SKYSHROUD_LEGGINGS = ITEMS.register("skyshroud_leggings",
            () -> new ArmorItem(ModArmorMaterials.SKYSHROUD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> SKYSHROUD_BOOTS = ITEMS.register("skyshroud_boots",
            () -> new ArmorItem(ModArmorMaterials.SKYSHROUD, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Galehide
    public static final DeferredItem<Item> GALEHIDE_HELMET = ITEMS.register("galehide_helmet",
            () -> new ArmorItem(ModArmorMaterials.GALEHIDE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> GALEHIDE_CHESTPLATE = ITEMS.register("galehide_chestplate",
            () -> new ArmorItem(ModArmorMaterials.GALEHIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> GALEHIDE_LEGGINGS = ITEMS.register("galehide_leggings",
            () -> new ArmorItem(ModArmorMaterials.GALEHIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> GALEHIDE_BOOTS = ITEMS.register("galehide_boots",
            () -> new ArmorItem(ModArmorMaterials.GALEHIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Stormforged
    public static final DeferredItem<Item> STORMFORGED_HELMET = ITEMS.register("stormforged_helmet",
            () -> new ArmorItem(ModArmorMaterials.STORMFORGED, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> STORMFORGED_CHESTPLATE = ITEMS.register("stormforged_chestplate",
            () -> new ArmorItem(ModArmorMaterials.STORMFORGED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> STORMFORGED_LEGGINGS = ITEMS.register("stormforged_leggings",
            () -> new ArmorItem(ModArmorMaterials.STORMFORGED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> STORMFORGED_BOOTS = ITEMS.register("stormforged_boots",
            () -> new ArmorItem(ModArmorMaterials.STORMFORGED, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Sunblessed
    public static final DeferredItem<Item> SUNBLESSED_HELMET = ITEMS.register("sunblessed_helmet",
            () -> new ArmorItem(ModArmorMaterials.SUNBLESSED, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> SUNBLESSED_CHESTPLATE = ITEMS.register("sunblessed_chestplate",
            () -> new ArmorItem(ModArmorMaterials.SUNBLESSED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> SUNBLESSED_LEGGINGS = ITEMS.register("sunblessed_leggings",
            () -> new ArmorItem(ModArmorMaterials.SUNBLESSED, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> SUNBLESSED_BOOTS = ITEMS.register("sunblessed_boots",
            () -> new ArmorItem(ModArmorMaterials.SUNBLESSED, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Dawnhide
    public static final DeferredItem<Item> DAWNHIDE_HELMET = ITEMS.register("dawnhide_helmet",
            () -> new ArmorItem(ModArmorMaterials.DAWNHIDE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> DAWNHIDE_CHESTPLATE = ITEMS.register("dawnhide_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DAWNHIDE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> DAWNHIDE_LEGGINGS = ITEMS.register("dawnhide_leggings",
            () -> new ArmorItem(ModArmorMaterials.DAWNHIDE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> DAWNHIDE_BOOTS = ITEMS.register("dawnhide_boots",
            () -> new ArmorItem(ModArmorMaterials.DAWNHIDE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Radiant Sentinel
    public static final DeferredItem<Item> RADIANT_HELMET = ITEMS.register("radiant_helmet",
            () -> new ArmorItem(ModArmorMaterials.RADIANT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> RADIANT_CHESTPLATE = ITEMS.register("radiant_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RADIANT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> RADIANT_LEGGINGS = ITEMS.register("radiant_leggings",
            () -> new ArmorItem(ModArmorMaterials.RADIANT, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> RADIANT_BOOTS = ITEMS.register("radiant_boots",
            () -> new ArmorItem(ModArmorMaterials.RADIANT, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Umbral Shroud
    public static final DeferredItem<Item> UMBRAL_HELMET = ITEMS.register("umbral_helmet",
            () -> new ArmorItem(ModArmorMaterials.UMBRAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> UMBRAL_CHESTPLATE = ITEMS.register("umbral_chestplate",
            () -> new ArmorItem(ModArmorMaterials.UMBRAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> UMBRAL_LEGGINGS = ITEMS.register("umbral_leggings",
            () -> new ArmorItem(ModArmorMaterials.UMBRAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> UMBRAL_BOOTS = ITEMS.register("umbral_boots",
            () -> new ArmorItem(ModArmorMaterials.UMBRAL, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Duskfang
    public static final DeferredItem<Item> DUSKFANG_HELMET = ITEMS.register("duskfang_helmet",
            () -> new ArmorItem(ModArmorMaterials.DUSKFANG, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> DUSKFANG_CHESTPLATE = ITEMS.register("duskfang_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DUSKFANG, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> DUSKFANG_LEGGINGS = ITEMS.register("duskfang_leggings",
            () -> new ArmorItem(ModArmorMaterials.DUSKFANG, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> DUSKFANG_BOOTS = ITEMS.register("duskfang_boots",
            () -> new ArmorItem(ModArmorMaterials.DUSKFANG, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Abyssguard
    public static final DeferredItem<Item> ABYSSGUARD_HELMET = ITEMS.register("abyssguard_helmet",
            () -> new ArmorItem(ModArmorMaterials.ABYSSGUARD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> ABYSSGUARD_CHESTPLATE = ITEMS.register("abyssguard_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ABYSSGUARD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> ABYSSGUARD_LEGGINGS = ITEMS.register("abyssguard_leggings",
            () -> new ArmorItem(ModArmorMaterials.ABYSSGUARD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> ABYSSGUARD_BOOTS = ITEMS.register("abyssguard_boots",
            () -> new ArmorItem(ModArmorMaterials.ABYSSGUARD, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Geometer's Vestments
    public static final DeferredItem<Item> GEOMETER_HELMET = ITEMS.register("geometer_helmet",
            () -> new ArmorItem(ModArmorMaterials.GEOMETER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> GEOMETER_CHESTPLATE = ITEMS.register("geometer_chestplate",
            () -> new ArmorItem(ModArmorMaterials.GEOMETER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> GEOMETER_LEGGINGS = ITEMS.register("geometer_leggings",
            () -> new ArmorItem(ModArmorMaterials.GEOMETER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> GEOMETER_BOOTS = ITEMS.register("geometer_boots",
            () -> new ArmorItem(ModArmorMaterials.GEOMETER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Rulebound Harness
    public static final DeferredItem<Item> RULEBOUND_HELMET = ITEMS.register("rulebound_helmet",
            () -> new ArmorItem(ModArmorMaterials.RULEBOUND, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> RULEBOUND_CHESTPLATE = ITEMS.register("rulebound_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RULEBOUND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> RULEBOUND_LEGGINGS = ITEMS.register("rulebound_leggings",
            () -> new ArmorItem(ModArmorMaterials.RULEBOUND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> RULEBOUND_BOOTS = ITEMS.register("rulebound_boots",
            () -> new ArmorItem(ModArmorMaterials.RULEBOUND, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Lawkeeper's Bulwark
    public static final DeferredItem<Item> LAWKEEPER_HELMET = ITEMS.register("lawkeeper_helmet",
            () -> new ArmorItem(ModArmorMaterials.LAWKEEPER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> LAWKEEPER_CHESTPLATE = ITEMS.register("lawkeeper_chestplate",
            () -> new ArmorItem(ModArmorMaterials.LAWKEEPER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> LAWKEEPER_LEGGINGS = ITEMS.register("lawkeeper_leggings",
            () -> new ArmorItem(ModArmorMaterials.LAWKEEPER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> LAWKEEPER_BOOTS = ITEMS.register("lawkeeper_boots",
            () -> new ArmorItem(ModArmorMaterials.LAWKEEPER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Wildmantle
    public static final DeferredItem<Item> WILDMANTLE_HELMET = ITEMS.register("wildmantle_helmet",
            () -> new ArmorItem(ModArmorMaterials.WILDMANTLE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> WILDMANTLE_CHESTPLATE = ITEMS.register("wildmantle_chestplate",
            () -> new ArmorItem(ModArmorMaterials.WILDMANTLE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> WILDMANTLE_LEGGINGS = ITEMS.register("wildmantle_leggings",
            () -> new ArmorItem(ModArmorMaterials.WILDMANTLE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> WILDMANTLE_BOOTS = ITEMS.register("wildmantle_boots",
            () -> new ArmorItem(ModArmorMaterials.WILDMANTLE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Trickster's Wrap
    public static final DeferredItem<Item> TRICKSTER_HELMET = ITEMS.register("trickster_helmet",
            () -> new ArmorItem(ModArmorMaterials.TRICKSTER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> TRICKSTER_CHESTPLATE = ITEMS.register("trickster_chestplate",
            () -> new ArmorItem(ModArmorMaterials.TRICKSTER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> TRICKSTER_LEGGINGS = ITEMS.register("trickster_leggings",
            () -> new ArmorItem(ModArmorMaterials.TRICKSTER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> TRICKSTER_BOOTS = ITEMS.register("trickster_boots",
            () -> new ArmorItem(ModArmorMaterials.TRICKSTER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Pandemonium Guard
    public static final DeferredItem<Item> PANDEMONIUM_HELMET = ITEMS.register("pandemonium_helmet",
            () -> new ArmorItem(ModArmorMaterials.PANDEMONIUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> PANDEMONIUM_CHESTPLATE = ITEMS.register("pandemonium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PANDEMONIUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> PANDEMONIUM_LEGGINGS = ITEMS.register("pandemonium_leggings",
            () -> new ArmorItem(ModArmorMaterials.PANDEMONIUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> PANDEMONIUM_BOOTS = ITEMS.register("pandemonium_boots",
            () -> new ArmorItem(ModArmorMaterials.PANDEMONIUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Weaver's Garb
    public static final DeferredItem<Item> WEAVER_HELMET = ITEMS.register("weaver_helmet",
            () -> new ArmorItem(ModArmorMaterials.WEAVER, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> WEAVER_CHESTPLATE = ITEMS.register("weaver_chestplate",
            () -> new ArmorItem(ModArmorMaterials.WEAVER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> WEAVER_LEGGINGS = ITEMS.register("weaver_leggings",
            () -> new ArmorItem(ModArmorMaterials.WEAVER, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> WEAVER_BOOTS = ITEMS.register("weaver_boots",
            () -> new ArmorItem(ModArmorMaterials.WEAVER, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Genesis Hide
    public static final DeferredItem<Item> GENESIS_HELMET = ITEMS.register("genesis_helmet",
            () -> new ArmorItem(ModArmorMaterials.GENESIS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> GENESIS_CHESTPLATE = ITEMS.register("genesis_chestplate",
            () -> new ArmorItem(ModArmorMaterials.GENESIS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> GENESIS_LEGGINGS = ITEMS.register("genesis_leggings",
            () -> new ArmorItem(ModArmorMaterials.GENESIS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> GENESIS_BOOTS = ITEMS.register("genesis_boots",
            () -> new ArmorItem(ModArmorMaterials.GENESIS, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Architect's Frame
    public static final DeferredItem<Item> ARCHITECT_HELMET = ITEMS.register("architect_helmet",
            () -> new ArmorItem(ModArmorMaterials.ARCHITECT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> ARCHITECT_CHESTPLATE = ITEMS.register("architect_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ARCHITECT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> ARCHITECT_LEGGINGS = ITEMS.register("architect_leggings",
            () -> new ArmorItem(ModArmorMaterials.ARCHITECT, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> ARCHITECT_BOOTS = ITEMS.register("architect_boots",
            () -> new ArmorItem(ModArmorMaterials.ARCHITECT, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Ashmantle
    public static final DeferredItem<Item> ASHMANTLE_HELMET = ITEMS.register("ashmantle_helmet",
            () -> new ArmorItem(ModArmorMaterials.ASHMANTLE, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> ASHMANTLE_CHESTPLATE = ITEMS.register("ashmantle_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ASHMANTLE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> ASHMANTLE_LEGGINGS = ITEMS.register("ashmantle_leggings",
            () -> new ArmorItem(ModArmorMaterials.ASHMANTLE, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> ASHMANTLE_BOOTS = ITEMS.register("ashmantle_boots",
            () -> new ArmorItem(ModArmorMaterials.ASHMANTLE, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Ruinclad
    public static final DeferredItem<Item> RUINCLAD_HELMET = ITEMS.register("ruinclad_helmet",
            () -> new ArmorItem(ModArmorMaterials.RUINCLAD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> RUINCLAD_CHESTPLATE = ITEMS.register("ruinclad_chestplate",
            () -> new ArmorItem(ModArmorMaterials.RUINCLAD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> RUINCLAD_LEGGINGS = ITEMS.register("ruinclad_leggings",
            () -> new ArmorItem(ModArmorMaterials.RUINCLAD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> RUINCLAD_BOOTS = ITEMS.register("ruinclad_boots",
            () -> new ArmorItem(ModArmorMaterials.RUINCLAD, ArmorItem.Type.BOOTS, new Item.Properties()));

    // Obliterator's Carapace
    public static final DeferredItem<Item> OBLITERATOR_HELMET = ITEMS.register("obliterator_helmet",
            () -> new ArmorItem(ModArmorMaterials.OBLITERATOR, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final DeferredItem<Item> OBLITERATOR_CHESTPLATE = ITEMS.register("obliterator_chestplate",
            () -> new ArmorItem(ModArmorMaterials.OBLITERATOR, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final DeferredItem<Item> OBLITERATOR_LEGGINGS = ITEMS.register("obliterator_leggings",
            () -> new ArmorItem(ModArmorMaterials.OBLITERATOR, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final DeferredItem<Item> OBLITERATOR_BOOTS = ITEMS.register("obliterator_boots",
            () -> new ArmorItem(ModArmorMaterials.OBLITERATOR, ArmorItem.Type.BOOTS, new Item.Properties()));

}

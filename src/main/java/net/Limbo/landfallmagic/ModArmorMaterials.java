package net.Limbo.landfallmagic;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.List;
import java.util.Map;

public class ModArmorMaterials {

    // NATURE/COMMON TIER
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> VERDANT = ModRegistries.ARMOR_MATERIALS.register("verdant", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 1, ArmorItem.Type.LEGGINGS, 2, ArmorItem.Type.CHESTPLATE, 3, ArmorItem.Type.HELMET, 1, ArmorItem.Type.BODY, 3),
            15, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.LIVING_BARK.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "verdant"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> WILDHIDE = ModRegistries.ARMOR_MATERIALS.register("wildhide", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            12, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.WILDHIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "wildhide"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STONEBARK = ModRegistries.ARMOR_MATERIALS.register("stonebark", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 6),
            9, SoundEvents.ARMOR_EQUIP_CHAIN, () -> Ingredient.of(ModItems.ROOTSTEEL_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "stonebark"))), 0.5f, 0.0f));

    // ARCANE TIER
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ARCANIST = ModRegistries.ARMOR_MATERIALS.register("arcanist", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 3, ArmorItem.Type.CHESTPLATE, 4, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 4),
            20, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(ModItems.ARCANE_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "arcanist"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SPELLBINDER = ModRegistries.ARMOR_MATERIALS.register("spellbinder", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            18, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(ModItems.RUNIC_THREAD.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "spellbinder"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> RUNEFORGED = ModRegistries.ARMOR_MATERIALS.register("runeforged", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 7, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 7),
            15, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModItems.RUNEFORGED_ALLOY.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "runeforged"))), 1.0f, 0.0f));

    // DARK/OCCULT TIER
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> OCCULT = ModRegistries.ARMOR_MATERIALS.register("occult", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            13, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.OCCULT_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "occult"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> HEXSKIN = ModRegistries.ARMOR_MATERIALS.register("hexskin", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 6),
            11, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(ModItems.BLOODSTAINED_CLOTH.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "hexskin"))), 0.0f, 0.5f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BLOODFORGED = ModRegistries.ARMOR_MATERIALS.register("bloodforged", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 8, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 8),
            10, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModItems.SACRIFICIAL_STEEL.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "bloodforged"))), 1.5f, 0.0f));

    // ELEMENTAL TIERS - FIRE
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> EMBERWEAVE = ModRegistries.ARMOR_MATERIALS.register("emberweave", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            12, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.BLAZEFIBER_CLOTH.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "emberweave"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ASHENHIDE = ModRegistries.ARMOR_MATERIALS.register("ashenhide", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 6),
            10, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.MAGMA_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "ashenhide"))), 0.5f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> INFERNO = ModRegistries.ARMOR_MATERIALS.register("inferno", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 8, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 8),
            8, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(ModItems.MOLTEN_IRON.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "inferno"))), 2.0f, 0.5f));

    // WATER
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> TIDECALLER = ModRegistries.ARMOR_MATERIALS.register("tidecaller", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            14, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.TIDECLOTH.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "tidecaller"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BRINEHIDE = ModRegistries.ARMOR_MATERIALS.register("brinehide", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 6),
            11, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.SCALEHIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "brinehide"))), 0.5f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> LEVIATHAN = ModRegistries.ARMOR_MATERIALS.register("leviathan", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 8, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 8),
            9, SoundEvents.ARMOR_EQUIP_TURTLE, () -> Ingredient.of(ModItems.CORALSTEEL_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "leviathan"))), 2.0f, 0.5f));

    // EARTH
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STONEVEIL = ModRegistries.ARMOR_MATERIALS.register("stoneveil", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            16, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.STONEWOVEN_CLOTH.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "stoneveil"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> BURROWHIDE = ModRegistries.ARMOR_MATERIALS.register("burrowhide", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 6),
            13, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.BURROW_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "burrowhide"))), 1.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> MOUNTAINBREAKER = ModRegistries.ARMOR_MATERIALS.register("mountainbreaker", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 8, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 8),
            10, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModItems.GEO_ALLOY.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "mountainbreaker"))), 2.5f, 0.5f));

    // AIR
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SKYSHROUD = ModRegistries.ARMOR_MATERIALS.register("skyshroud", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 3, ArmorItem.Type.CHESTPLATE, 4, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 4),
            18, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.CLOUDSILK.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "skyshroud"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> GALEHIDE = ModRegistries.ARMOR_MATERIALS.register("galehide", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            15, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.FEATHERED_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "galehide"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> STORMFORGED = ModRegistries.ARMOR_MATERIALS.register("stormforged", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 7, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 7),
            12, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModItems.SKY_ALLOY.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "stormforged"))), 1.0f, 0.0f));

    // LIGHT
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> SUNBLESSED = ModRegistries.ARMOR_MATERIALS.register("sunblessed", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            20, SoundEvents.ARMOR_EQUIP_GOLD, () -> Ingredient.of(ModItems.SUNTHREAD.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "sunblessed"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> DAWNHIDE = ModRegistries.ARMOR_MATERIALS.register("dawnhide", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 6),
            18, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.RADIANT_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "dawnhide"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> RADIANT = ModRegistries.ARMOR_MATERIALS.register("radiant", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 8, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 8),
            15, SoundEvents.ARMOR_EQUIP_GOLD, () -> Ingredient.of(ModItems.BLESSED_GOLD_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "radiant"))), 1.0f, 0.0f));

    // DARK
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> UMBRAL = ModRegistries.ARMOR_MATERIALS.register("umbral", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 4, ArmorItem.Type.CHESTPLATE, 5, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 5),
            14, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.SHADOWCLOTH.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "umbral"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> DUSKFANG = ModRegistries.ARMOR_MATERIALS.register("duskfang", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 2, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 2, ArmorItem.Type.BODY, 6),
            12, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.UMBRAL_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "duskfang"))), 0.5f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ABYSSGUARD = ModRegistries.ARMOR_MATERIALS.register("abyssguard", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 8, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 8),
            10, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModItems.OBSIDIAN_STEEL.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "abyssguard"))), 2.0f, 0.0f));

    // ORDER
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> GEOMETER = ModRegistries.ARMOR_MATERIALS.register("geometer", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 6),
            22, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.LATTICE_THREAD.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "geometer"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> RULEBOUND = ModRegistries.ARMOR_MATERIALS.register("rulebound", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 7, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 7),
            19, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.PATTERNED_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "rulebound"))), 0.5f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> LAWKEEPER = ModRegistries.ARMOR_MATERIALS.register("lawkeeper", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 4, ArmorItem.Type.LEGGINGS, 7, ArmorItem.Type.CHESTPLATE, 9, ArmorItem.Type.HELMET, 4, ArmorItem.Type.BODY, 9),
            16, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Ingredient.of(ModItems.GEOMETRIC_INGOT.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "lawkeeper"))), 3.0f, 0.1f));

    // CHAOS
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> WILDMANTLE = ModRegistries.ARMOR_MATERIALS.register("wildmantle", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 5, ArmorItem.Type.CHESTPLATE, 6, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 6),
            22, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(ModItems.WARPCLOTH.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "wildmantle"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> TRICKSTER = ModRegistries.ARMOR_MATERIALS.register("trickster", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 3, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 7, ArmorItem.Type.HELMET, 3, ArmorItem.Type.BODY, 7),
            19, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.CHAOTIC_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "trickster"))), 0.5f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> PANDEMONIUM = ModRegistries.ARMOR_MATERIALS.register("pandemonium", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 4, ArmorItem.Type.LEGGINGS, 7, ArmorItem.Type.CHESTPLATE, 9, ArmorItem.Type.HELMET, 4, ArmorItem.Type.BODY, 9),
            16, SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ModItems.DISCORD_ALLOY.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "pandemonium"))), 3.0f, 0.1f));

    // CREATION
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> WEAVER = ModRegistries.ARMOR_MATERIALS.register("weaver", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 4, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 7, ArmorItem.Type.HELMET, 4, ArmorItem.Type.BODY, 7),
            25, SoundEvents.ARMOR_EQUIP_ELYTRA, () -> Ingredient.of(ModItems.AETHER_SILK.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "weaver"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> GENESIS = ModRegistries.ARMOR_MATERIALS.register("genesis", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 4, ArmorItem.Type.LEGGINGS, 7, ArmorItem.Type.CHESTPLATE, 8, ArmorItem.Type.HELMET, 4, ArmorItem.Type.BODY, 8),
            22, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.MYTHIC_BEAST_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "genesis"))), 1.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ARCHITECT = ModRegistries.ARMOR_MATERIALS.register("architect", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 5, ArmorItem.Type.LEGGINGS, 8, ArmorItem.Type.CHESTPLATE, 10, ArmorItem.Type.HELMET, 5, ArmorItem.Type.BODY, 10),
            18, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(ModItems.STAR_ALLOY.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "architect"))), 4.0f, 0.2f));

    // DESTRUCTION
    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> ASHMANTLE = ModRegistries.ARMOR_MATERIALS.register("ashmantle", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 4, ArmorItem.Type.LEGGINGS, 6, ArmorItem.Type.CHESTPLATE, 7, ArmorItem.Type.HELMET, 4, ArmorItem.Type.BODY, 7),
            25, SoundEvents.ARMOR_EQUIP_ELYTRA, () -> Ingredient.of(ModItems.ASHWEAVE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "ashmantle"))), 0.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> RUINCLAD = ModRegistries.ARMOR_MATERIALS.register("ruinclad", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 4, ArmorItem.Type.LEGGINGS, 7, ArmorItem.Type.CHESTPLATE, 8, ArmorItem.Type.HELMET, 4, ArmorItem.Type.BODY, 8),
            22, SoundEvents.ARMOR_EQUIP_LEATHER, () -> Ingredient.of(ModItems.RUIN_HIDE.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "ruinclad"))), 1.0f, 0.0f));

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> OBLITERATOR = ModRegistries.ARMOR_MATERIALS.register("obliterator", () -> new ArmorMaterial(
            Map.of(ArmorItem.Type.BOOTS, 5, ArmorItem.Type.LEGGINGS, 8, ArmorItem.Type.CHESTPLATE, 10, ArmorItem.Type.HELMET, 5, ArmorItem.Type.BODY, 10),
            18, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(ModItems.OBLIVION_SHARD.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "obliterator"))), 4.0f, 0.2f));
}
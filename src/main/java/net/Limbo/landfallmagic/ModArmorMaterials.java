package net.Limbo.landfallmagic;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;

public class ModArmorMaterials {
    // Common/Nature tier
    public static final ResourceKey<ArmorMaterial> VERDANT = createKey("verdant");
    public static final ResourceKey<ArmorMaterial> WILDHIDE = createKey("wildhide");
    public static final ResourceKey<ArmorMaterial> STONEBARK = createKey("stonebark");

    // Arcane tier
    public static final ResourceKey<ArmorMaterial> ARCANIST = createKey("arcanist");
    public static final ResourceKey<ArmorMaterial> SPELLBINDER = createKey("spellbinder");
    public static final ResourceKey<ArmorMaterial> RUNEFORGED = createKey("runeforged");

    // Dark/Occult tier
    public static final ResourceKey<ArmorMaterial> OCCULT = createKey("occult");
    public static final ResourceKey<ArmorMaterial> HEXSKIN = createKey("hexskin");
    public static final ResourceKey<ArmorMaterial> BLOODFORGED = createKey("bloodforged");

    // Fire tier
    public static final ResourceKey<ArmorMaterial> EMBERWEAVE = createKey("emberweave");
    public static final ResourceKey<ArmorMaterial> ASHENHIDE = createKey("ashenhide");
    public static final ResourceKey<ArmorMaterial> INFERNO = createKey("inferno");

    // Water tier
    public static final ResourceKey<ArmorMaterial> TIDECALLER = createKey("tidecaller");
    public static final ResourceKey<ArmorMaterial> BRINEHIDE = createKey("brinehide");
    public static final ResourceKey<ArmorMaterial> LEVIATHAN = createKey("leviathan");

    // Earth tier
    public static final ResourceKey<ArmorMaterial> STONEVEIL = createKey("stoneveil");
    public static final ResourceKey<ArmorMaterial> BURROWHIDE = createKey("burrowhide");
    public static final ResourceKey<ArmorMaterial> MOUNTAINBREAKER = createKey("mountainbreaker");

    // Air tier
    public static final ResourceKey<ArmorMaterial> SKYSHROUD = createKey("skyshroud");
    public static final ResourceKey<ArmorMaterial> GALEHIDE = createKey("galehide");
    public static final ResourceKey<ArmorMaterial> STORMFORGED = createKey("stormforged");

    // Light tier
    public static final ResourceKey<ArmorMaterial> SUNBLESSED = createKey("sunblessed");
    public static final ResourceKey<ArmorMaterial> DAWNHIDE = createKey("dawnhide");
    public static final ResourceKey<ArmorMaterial> RADIANT = createKey("radiant");

    // Dark tier
    public static final ResourceKey<ArmorMaterial> UMBRAL = createKey("umbral");
    public static final ResourceKey<ArmorMaterial> DUSKFANG = createKey("duskfang");
    public static final ResourceKey<ArmorMaterial> ABYSSGUARD = createKey("abyssguard");

    // Order tier
    public static final ResourceKey<ArmorMaterial> GEOMETER = createKey("geometer");
    public static final ResourceKey<ArmorMaterial> RULEBOUND = createKey("rulebound");
    public static final ResourceKey<ArmorMaterial> LAWKEEPER = createKey("lawkeeper");

    // Chaos tier
    public static final ResourceKey<ArmorMaterial> WILDMANTLE = createKey("wildmantle");
    public static final ResourceKey<ArmorMaterial> TRICKSTER = createKey("trickster");
    public static final ResourceKey<ArmorMaterial> PANDEMONIUM = createKey("pandemonium");

    // Creation tier
    public static final ResourceKey<ArmorMaterial> WEAVER = createKey("weaver");
    public static final ResourceKey<ArmorMaterial> GENESIS = createKey("genesis");
    public static final ResourceKey<ArmorMaterial> ARCHITECT = createKey("architect");

    // Destruction tier
    public static final ResourceKey<ArmorMaterial> ASHMANTLE = createKey("ashmantle");
    public static final ResourceKey<ArmorMaterial> RUINCLAD = createKey("ruinclad");
    public static final ResourceKey<ArmorMaterial> OBLITERATOR = createKey("obliterator");

    private static ResourceKey<ArmorMaterial> createKey(String name) {
        return ResourceKey.create(Registries.ARMOR_MATERIAL, ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, name));
    }
}
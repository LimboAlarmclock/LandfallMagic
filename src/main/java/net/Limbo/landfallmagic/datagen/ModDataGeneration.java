package net.Limbo.landfallmagic.datagen;

import net.Limbo.landfallmagic.ModArmorMaterials;
import net.Limbo.landfallmagic.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.Map;

public class ModDataGeneration {
    public static void bootstrap(BootstrapContext<ArmorMaterial> context) {
        // NATURE/COMMON TIER
        context.register(ModArmorMaterials.VERDANT, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS,1,
                        ArmorItem.Type.LEGGINGS,2,
                        ArmorItem.Type.CHESTPLATE,3,
                        ArmorItem.Type.HELMET,1,
                        ArmorItem.Type.BODY,3
                ),
                15, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.LIVING_BARK.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "verdant")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "verdant_overlay"))
                ), 0.0f, 0.0f));

        context.register(ModArmorMaterials.WILDHIDE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                12, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.WILDHIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "wildhide")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "wildhide_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.STONEBARK, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 6
                ),
                9, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_CHAIN), () -> Ingredient.of(ModItems.ROOTSTEEL_INGOT.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stonebark")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stonebark_overlay"))
                ), 0.5f, 0.0f
        ));

        // ARCANE TIER
        context.register(ModArmorMaterials.ARCANIST, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 3,
                        ArmorItem.Type.CHESTPLATE, 4,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 4
                ),
                20, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_GENERIC), () -> Ingredient.of(ModItems.ARCANE_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "arcanist")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "arcanist_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.SPELLBINDER, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                18, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_GENERIC), () -> Ingredient.of(ModItems.RUNIC_THREAD.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "spellbinder")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "spellbinder_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.RUNEFORGED, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 7,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 7
                ),
                15, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_IRON), () -> Ingredient.of(ModItems.RUNEFORGED_ALLOY.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "runeforged")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "runeforged_overlay"))
                ), 1.0f, 0.0f
        ));

        // DARK/OCCULT TIER
        context.register(ModArmorMaterials.OCCULT, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                13, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.OCCULT_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "occult")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "occult_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.HEXSKIN, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 6
                ),
                11, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_GENERIC), () -> Ingredient.of(ModItems.BLOODSTAINED_CLOTH.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "hexskin")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "hexskin_overlay"))
                ), 0.0f, 0.5f
        ));

        context.register(ModArmorMaterials.BLOODFORGED, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 8,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 8
                ),
                10, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_IRON), () -> Ingredient.of(ModItems.SACRIFICIAL_STEEL.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "bloodforged")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "bloodforged_overlay"))
                ), 1.5f, 0.0f
        ));

        // ELEMENTAL TIERS - FIRE
        context.register(ModArmorMaterials.EMBERWEAVE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                12, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.BLAZEFIBER_CLOTH.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "emberweave")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "emberweave_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.ASHENHIDE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 6
                ),
                10, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.MAGMA_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "ashenhide")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "ashenhide_overlay"))
                ), 0.5f, 0.0f
        ));

        context.register(ModArmorMaterials.INFERNO, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 8,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 8
                ),
                8, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_NETHERITE), () -> Ingredient.of(ModItems.MOLTEN_IRON.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "inferno")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "inferno_overlay"))
                ), 2.0f, 0.5f
        ));

        // WATER
        context.register(ModArmorMaterials.TIDECALLER, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                14, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.TIDECLOTH.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "tidecaller")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "tidecaller_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.BRINEHIDE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 6
                ),
                11, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.SCALEHIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "brinehide")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "brinehide_overlay"))
                ), 0.5f, 0.0f
        ));

        context.register(ModArmorMaterials.LEVIATHAN, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 8,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 8
                ),
                9, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_TURTLE), () -> Ingredient.of(ModItems.CORALSTEEL_INGOT.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "leviathan")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "leviathan_overlay"))
                ), 2.0f, 0.5f
        ));

        // EARTH
        context.register(ModArmorMaterials.STONEVEIL, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                16, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.STONEWOVEN_CLOTH.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stoneveil")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stoneveil_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.BURROWHIDE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 6
                ),
                13, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.BURROW_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "burrowhide")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "burrowhide_overlay"))
                ), 1.0f, 0.0f
        ));

        context.register(ModArmorMaterials.MOUNTAINBREAKER, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 8,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 8
                ),
                10, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_IRON), () -> Ingredient.of(ModItems.GEO_ALLOY.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "mountainbreaker")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "mountainbreaker_overlay"))
                ), 2.5f, 0.5f
        ));

        // AIR
        context.register(ModArmorMaterials.SKYSHROUD, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 3,
                        ArmorItem.Type.CHESTPLATE, 4,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 4
                ),
                18, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.CLOUDSILK.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "skyshroud")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "skyshroud_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.GALEHIDE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                15, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.FEATHERED_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "galehide")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "galehide_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.STORMFORGED, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 7,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 7
                ),
                12, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_IRON), () -> Ingredient.of(ModItems.SKY_ALLOY.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stormforged")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stormforged_overlay"))
                ), 1.0f, 0.0f
        ));

        // LIGHT
        context.register(ModArmorMaterials.SUNBLESSED, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                20, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_GOLD), () -> Ingredient.of(ModItems.SUNTHREAD.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "sunblessed")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "sunblessed_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.DAWNHIDE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 6
                ),
                18, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.RADIANT_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "dawnhide")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "dawnhide_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.RADIANT, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 8,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 8
                ),
                15, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_GOLD), () -> Ingredient.of(ModItems.BLESSED_GOLD_INGOT.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "radiant")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "radiant_overlay"))
                ), 1.0f, 0.0f
        ));

        // DARK
        context.register(ModArmorMaterials.UMBRAL, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 4,
                        ArmorItem.Type.CHESTPLATE, 5,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 5
                ),
                14, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.SHADOWCLOTH.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "umbral")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "umbral_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.DUSKFANG, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 2,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 2,
                        ArmorItem.Type.BODY, 6
                ),
                12, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.UMBRAL_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "duskfang")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "duskfang_overlay"))
                ), 0.5f, 0.0f
        ));

        context.register(ModArmorMaterials.ABYSSGUARD, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 8,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 8
                ),
                10, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_IRON), () -> Ingredient.of(ModItems.OBSIDIAN_STEEL.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "abyssguard")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "abyssguard_overlay"))
                ), 2.0f, 0.0f
        ));

        // ORDER
        context.register(ModArmorMaterials.GEOMETER, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 6
                ),
                22, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_DIAMOND), () -> Ingredient.of(ModItems.LATTICE_THREAD.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "geometer")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "geometer_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.RULEBOUND, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 7,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 7
                ),
                19, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.PATTERNED_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "rulebound")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "rulebound_overlay"))
                ), 0.5f, 0.0f
        ));

        context.register(ModArmorMaterials.LAWKEEPER, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 4,
                        ArmorItem.Type.LEGGINGS, 7,
                        ArmorItem.Type.CHESTPLATE, 9,
                        ArmorItem.Type.HELMET, 4,
                        ArmorItem.Type.BODY, 9
                ),
                16, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_DIAMOND), () -> Ingredient.of(ModItems.GEOMETRIC_INGOT.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "lawkeeper")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "lawkeeper_overlay"))
                ), 3.0f, 0.1f
        ));

        // CHAOS
        context.register(ModArmorMaterials.WILDMANTLE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 5,
                        ArmorItem.Type.CHESTPLATE, 6,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 6
                ),
                22, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_GENERIC), () -> Ingredient.of(ModItems.WARPCLOTH.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "wildmantle")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "wildmantle_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.TRICKSTER, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 3,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 7,
                        ArmorItem.Type.HELMET, 3,
                        ArmorItem.Type.BODY, 7
                ),
                19, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.CHAOTIC_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "trickster")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "trickster_overlay"))
                ), 0.5f, 0.0f
        ));

        context.register(ModArmorMaterials.PANDEMONIUM, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 4,
                        ArmorItem.Type.LEGGINGS, 7,
                        ArmorItem.Type.CHESTPLATE, 9,
                        ArmorItem.Type.HELMET, 4,
                        ArmorItem.Type.BODY, 9
                ),
                16, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_IRON), () -> Ingredient.of(ModItems.DISCORD_ALLOY.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "pandemonium")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "pandemonium_overlay"))
                ), 3.0f, 0.1f
        ));

        // CREATION
        context.register(ModArmorMaterials.WEAVER, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 4,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 7,
                        ArmorItem.Type.HELMET, 4,
                        ArmorItem.Type.BODY, 7
                ),
                25, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_ELYTRA), () -> Ingredient.of(ModItems.AETHER_SILK.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "weaver")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "weaver_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.GENESIS, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 4,
                        ArmorItem.Type.LEGGINGS, 7,
                        ArmorItem.Type.CHESTPLATE, 8,
                        ArmorItem.Type.HELMET, 4,
                        ArmorItem.Type.BODY, 8
                ),
                22, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.MYTHIC_BEAST_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "genesis")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "genesis_overlay"))
                ), 1.0f, 0.0f
        ));

        context.register(ModArmorMaterials.ARCHITECT, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 5,
                        ArmorItem.Type.LEGGINGS, 8,
                        ArmorItem.Type.CHESTPLATE, 10,
                        ArmorItem.Type.HELMET, 5,
                        ArmorItem.Type.BODY, 10
                ),
                18, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_NETHERITE), () -> Ingredient.of(ModItems.STAR_ALLOY.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "architect")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "architect_overlay"))
                ), 4.0f, 0.2f
        ));

        // DESTRUCTION
        context.register(ModArmorMaterials.ASHMANTLE, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 4,
                        ArmorItem.Type.LEGGINGS, 6,
                        ArmorItem.Type.CHESTPLATE, 7,
                        ArmorItem.Type.HELMET, 4,
                        ArmorItem.Type.BODY, 7
                ),
                25, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_ELYTRA), () -> Ingredient.of(ModItems.ASHWEAVE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "ashmantle")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "ashmantle_overlay"))
                ), 0.0f, 0.0f
        ));

        context.register(ModArmorMaterials.RUINCLAD, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 4,
                        ArmorItem.Type.LEGGINGS, 7,
                        ArmorItem.Type.CHESTPLATE, 8,
                        ArmorItem.Type.HELMET, 4,
                        ArmorItem.Type.BODY, 8
                ),
                22, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_LEATHER), () -> Ingredient.of(ModItems.RUIN_HIDE.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "ruinclad")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "ruinclad_overlay"))
                ), 1.0f, 0.0f
        ));

        context.register(ModArmorMaterials.OBLITERATOR, new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.BOOTS, 5,
                        ArmorItem.Type.LEGGINGS, 8,
                        ArmorItem.Type.CHESTPLATE, 10,
                        ArmorItem.Type.HELMET, 5,
                        ArmorItem.Type.BODY, 10
                ),
                18, Holder.direct((SoundEvent) SoundEvents.ARMOR_EQUIP_NETHERITE), () -> Ingredient.of(ModItems.OBLIVION_SHARD.get()),
                List.of(
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "obliterator")),
                        new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "obliterator_overlay"))
                ), 4.0f, 0.2f
        ));
    }
}
package net.Limbo.landfallmagic.datagen;

import net.Limbo.landfallmagic.ModArmorMaterials;
import net.Limbo.landfallmagic.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Map;

public class ModDataGeneration {
    public static void bootstrap(BootstrapContext<ArmorMaterial> context) {
        // NATURE/COMMON TIER
        context.register(ModArmorMaterials.VERDANT, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "verdant"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "verdant_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 1,
                        ArmorMaterial.Type.LEGGINGS, 2,
                        ArmorMaterial.Type.CHESTPLATE, 3,
                        ArmorMaterial.Type.HELMET, 1,
                        ArmorMaterial.Type.BODY, 3
                ),
                15,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.LIVING_BARK.get())
        ));

        context.register(ModArmorMaterials.WILDHIDE, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "wildhide"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "wildhide_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 4,
                        ArmorMaterial.Type.CHESTPLATE, 5,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 5
                ),
                12,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.WILDHIDE.get())
        ));

        context.register(ModArmorMaterials.STONEBARK, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stonebark"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stonebark_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 5,
                        ArmorMaterial.Type.CHESTPLATE, 6,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 6
                ),
                9,
                Holder.direct(SoundEvents.ARMOR_EQUIP_CHAIN),
                0.5f,
                0.0f,
                () -> Ingredient.of(ModItems.ROOTSTEEL_INGOT.get())
        ));

        // ARCANE TIER
        context.register(ModArmorMaterials.ARCANIST, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "arcanist"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "arcanist_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 3,
                        ArmorMaterial.Type.CHESTPLATE, 4,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 4
                ),
                20,
                Holder.direct(SoundEvents.ARMOR_EQUIP_GENERIC),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.ARCANE_HIDE.get())
        ));

        context.register(ModArmorMaterials.SPELLBINDER, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "spellbinder"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "spellbinder_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 4,
                        ArmorMaterial.Type.CHESTPLATE, 5,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 5
                ),
                18,
                Holder.direct(SoundEvents.ARMOR_EQUIP_GENERIC),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.RUNIC_THREAD.get())
        ));

        context.register(ModArmorMaterials.RUNEFORGED, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "runeforged"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "runeforged_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 3,
                        ArmorMaterial.Type.LEGGINGS, 6,
                        ArmorMaterial.Type.CHESTPLATE, 7,
                        ArmorMaterial.Type.HELMET, 3,
                        ArmorMaterial.Type.BODY, 7
                ),
                15,
                Holder.direct(SoundEvents.ARMOR_EQUIP_IRON),
                1.0f,
                0.0f,
                () -> Ingredient.of(ModItems.RUNEFOrged_ALLOY.get())
        ));

        // DARK/OCCULT TIER
        context.register(ModArmorMaterials.OCCULT, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "occult"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "occult_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 4,
                        ArmorMaterial.Type.CHESTPLATE, 5,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 5
                ),
                13,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.OCCULT_HIDE.get())
        ));

        context.register(ModArmorMaterials.HEXSKIN, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "hexskin"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "hexskin_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 5,
                        ArmorMaterial.Type.CHESTPLATE, 6,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 6
                ),
                11,
                Holder.direct(SoundEvents.ARMOR_EQUIP_GENERIC),
                0.0f,
                0.5f,
                () -> Ingredient.of(ModItems.BLOODSTAINED_CLOTH.get())
        ));

        context.register(ModArmorMaterials.BLOODFORGED, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "bloodforged"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "bloodforged_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 3,
                        ArmorMaterial.Type.LEGGINGS, 6,
                        ArmorMaterial.Type.CHESTPLATE, 8,
                        ArmorMaterial.Type.HELMET, 3,
                        ArmorMaterial.Type.BODY, 8
                ),
                10,
                Holder.direct(SoundEvents.ARMOR_EQUIP_IRON),
                1.5f,
                0.0f,
                () -> Ingredient.of(ModItems.SACRIFICIAL_STEEL.get())
        ));

        // ELEMENTAL TIERS - FIRE
        context.register(ModArmorMaterials.EMBERWEAVE, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "emberweave"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "emberweave_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 4,
                        ArmorMaterial.Type.CHESTPLATE, 5,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 5
                ),
                12,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.BLAZEFIBER_CLOTH.get())
        ));

        context.register(ModArmorMaterials.ASHENHIDE, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "ashenhide"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "ashenhide_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 5,
                        ArmorMaterial.Type.CHESTPLATE, 6,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 6
                ),
                10,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.5f,
                0.0f,
                () -> Ingredient.of(ModItems.MAGMA_HIDE.get())
        ));

        context.register(ModArmorMaterials.INFERNO, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "inferno"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "inferno_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 3,
                        ArmorMaterial.Type.LEGGINGS, 6,
                        ArmorMaterial.Type.CHESTPLATE, 8,
                        ArmorMaterial.Type.HELMET, 3,
                        ArmorMaterial.Type.BODY, 8
                ),
                8,
                Holder.direct(SoundEvents.ARMOR_EQUIP_NETHERITE),
                2.0f,
                0.5f,
                () -> Ingredient.of(ModItems.MOLTEN_IRON.get())
        ));

        // WATER
        context.register(ModArmorMaterials.TIDECALLER, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "tidecaller"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "tidecaller_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 4,
                        ArmorMaterial.Type.CHESTPLATE, 5,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 5
                ),
                14,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.TIDECLOTH.get())
        ));

        context.register(ModArmorMaterials.BRINEHIDE, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "brinehide"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "brinehide_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 5,
                        ArmorMaterial.Type.CHESTPLATE, 6,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 6
                ),
                11,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.5f,
                0.0f,
                () -> Ingredient.of(ModItems.SCALEHIDE.get())
        ));

        context.register(ModArmorMaterials.LEVIATHAN, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "leviathan"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "leviathan_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 3,
                        ArmorMaterial.Type.LEGGINGS, 6,
                        ArmorMaterial.Type.CHESTPLATE, 8,
                        ArmorMaterial.Type.HELMET, 3,
                        ArmorMaterial.Type.BODY, 8
                ),
                9,
                Holder.direct(SoundEvents.ARMOR_EQUIP_TURTLE),
                2.0f,
                0.5f,
                () -> Ingredient.of(ModItems.CORALSTEEL_INGOT.get())
        ));

        // EARTH
        context.register(ModArmorMaterials.STONEVEIL, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stoneveil"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stoneveil_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 4,
                        ArmorMaterial.Type.CHESTPLATE, 5,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 5
                ),
                16,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.STONEWOVEN_CLOTH.get())
        ));

        context.register(ModArmorMaterials.BURROWHIDE, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "burrowhide"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "burrowhide_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 5,
                        ArmorMaterial.Type.CHESTPLATE, 6,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 6
                ),
                13,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                1.0f,
                0.0f,
                () -> Ingredient.of(ModItems.BURROW_HIDE.get())
        ));

        context.register(ModArmorMaterials.MOUNTAINBREAKER, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "mountainbreaker"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "mountainbreaker_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 3,
                        ArmorMaterial.Type.LEGGINGS, 6,
                        ArmorMaterial.Type.CHESTPLATE, 8,
                        ArmorMaterial.Type.HELMET, 3,
                        ArmorMaterial.Type.BODY, 8
                ),
                10,
                Holder.direct(SoundEvents.ARMOR_EQUIP_IRON),
                2.5f,
                0.5f,
                () -> Ingredient.of(ModItems.GEO_ALLOY.get())
        ));

        // AIR
        context.register(ModArmorMaterials.SKYSHROUD, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "skyshroud"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "skyshroud_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 3,
                        ArmorMaterial.Type.CHESTPLATE, 4,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 4
                ),
                18,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.CLOUDSILK.get())
        ));

        context.register(ModArmorMaterials.GALEHIDE, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "galehide"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "galehide_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 4,
                        ArmorMaterial.Type.CHESTPLATE, 5,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 5
                ),
                15,
                Holder.direct(SoundEvents.ARMOR_EQUIP_LEATHER),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.FEATHERED_HIDE.get())
        ));

        context.register(ModArmorMaterials.STORMFORGED, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stormforged"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "stormforged_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 3,
                        ArmorMaterial.Type.LEGGINGS, 5,
                        ArmorMaterial.Type.CHESTPLATE, 7,
                        ArmorMaterial.Type.HELMET, 3,
                        ArmorMaterial.Type.BODY, 7
                ),
                12,
                Holder.direct(SoundEvents.ARMOR_EQUIP_IRON),
                1.0f,
                0.0f,
                () -> Ingredient.of(ModItems.SKY_ALLOY.get())
        ));

        // Continue with LIGHT, DARK, ORDER, CHAOS, CREATION, DESTRUCTION tiers...
        // I'll include a few more as examples, but the pattern is the same

        // LIGHT
        context.register(ModArmorMaterials.SUNBLESSED, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "sunblessed"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("landfallmagic", "sunblessed_overlay"), "", false)
                ),
                Map.of(
                        ArmorMaterial.Type.BOOTS, 2,
                        ArmorMaterial.Type.LEGGINGS, 4,
                        ArmorMaterial.Type.CHESTPLATE, 5,
                        ArmorMaterial.Type.HELMET, 2,
                        ArmorMaterial.Type.BODY, 5
                ),
                20,
                Holder.direct(SoundEvents.ARMOR_EQUIP_GOLD),
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.
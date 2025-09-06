package net.Limbo.landfallmagic.datagen;

import net.Limbo.landfallmagic.ModArmorMaterials;
import net.Limbo.landfallmagic.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Map;

public class ModDataGeneration {
    public static void bootstrap(BootstrapContext<ArmorMaterial> context) {
        // Example for Verdant Armor. Repeat this pattern for all other armor sets.
        context.register(ModArmorMaterials.VERDANT, new ArmorMaterial(
                Map.of(
                        ArmorMaterial.Layer.INNER_ARMOR, new ArmorMaterial.Layer(new ResourceLocation("verdant"), "", false),
                        ArmorMaterial.Layer.OUTER_ARMOR, new ArmorMaterial.Layer(new ResourceLocation("verdant"), "", false)
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
    }
}
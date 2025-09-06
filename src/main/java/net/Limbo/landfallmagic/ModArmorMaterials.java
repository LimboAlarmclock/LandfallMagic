package net.Limbo.landfallmagic;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;

public class ModArmorMaterials {
    public static final ResourceKey<ArmorMaterial> VERDANT = createKey("verdant");

    private static ResourceKey<ArmorMaterial> createKey(String name) {
        return ResourceKey.create(Registries.ARMOR_MATERIAL, new ResourceLocation(landfallmagic.MODID, name));
    }
}
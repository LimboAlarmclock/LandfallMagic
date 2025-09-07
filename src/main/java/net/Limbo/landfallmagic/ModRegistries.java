package net.Limbo.landfallmagic;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorMaterial;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRegistries {
    // This is the new home for your Armor Material DeferredRegister.
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, landfallmagic.MODID);
}
package net.Limbo.landfallmagic.datagen;

import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENTS =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, landfallmagic.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Spell>> SPELL =
            DATA_COMPONENTS.register("spell", () ->
                    DataComponentType.<Spell>builder()
                            .persistent(Spell.CODEC)
                            .networkSynchronized(Spell.STREAM_CODEC) // <-- ADD THIS
                            .build());

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ItemContainerContents>> GRIMOIRE_CONTENTS =
            DATA_COMPONENTS.register("grimoire_contents", () ->
                    DataComponentType.<ItemContainerContents>builder()
                            .persistent(ItemContainerContents.CODEC)
                            .networkSynchronized(ItemContainerContents.STREAM_CODEC)
                            .build());
}
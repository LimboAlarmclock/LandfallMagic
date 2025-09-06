
package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.entity.DireWolfEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(net.minecraft.core.registries.Registries.ENTITY_TYPE, landfallmagic.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<DireWolfEntity>> DIRE_WOLF =
            ENTITY_TYPES.register("dire_wolf",
                    () -> EntityType.Builder.of(DireWolfEntity::new, MobCategory.MONSTER)
                            .sized(0.8f, 0.9f) // width, height
                            .build("dire_wolf"));
}

package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.entity.BindingTendrilsEntity;
import net.Limbo.landfallmagic.entity.DireWolfEntity;
import net.Limbo.landfallmagic.entity.sorcerery.SpellProjectileEntity;
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
    public static final DeferredHolder<EntityType<?>, EntityType<SpellProjectileEntity>> SPELL_PROJECTILE =
            ENTITY_TYPES.register("spell_projectile",
                    () -> EntityType.Builder.<SpellProjectileEntity>of(SpellProjectileEntity::new, MobCategory.MISC)
                            .sized(0.25F, 0.25F)
                            .clientTrackingRange(4).updateInterval(10)
                            .build("spell_projectile"));
    public static final DeferredHolder<EntityType<?>, EntityType<BindingTendrilsEntity>> BINDING_TENDRILS =
            ENTITY_TYPES.register("binding_tendrils",
                    () -> EntityType.Builder.<BindingTendrilsEntity>of(BindingTendrilsEntity::new, MobCategory.MISC)
                            .sized(1.5f, 1.5f) // The size of its hitbox for rendering
                            .clientTrackingRange(8)
                            .build("binding_tendrils"));
}
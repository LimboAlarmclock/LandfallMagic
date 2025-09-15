// limboalarmclock/landfallmagic/LandfallMagic-55ca6e4061a02348b10815189a22f8d987a24ec1/src/main/java/net/Limbo/landfallmagic/spell/effect/SpellEffectRegistry.java
package net.Limbo.landfallmagic.spell.effect;

import net.Limbo.landfallmagic.ModEntities;
import net.Limbo.landfallmagic.ModParticles;
import net.Limbo.landfallmagic.entity.BindingTendrilsEntity;
import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class SpellEffectRegistry {

    // A record to hold all the effects for a single spell
    public record SpellEffects(
            SoundEvent castSound,
            ParticleOptions particle,
            Consumer<EntityHitResult> onHit
    ) {}

    private static final Map<String, SpellEffects> spellEffectMap = new HashMap<>();

    public static void registerSpellEffects() {
        // Register Ignition Bolt
        spellEffectMap.put("Ignition Bolt", new SpellEffects(
                SoundEvents.GHAST_SHOOT,
                ParticleTypes.FLAME,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().setRemainingFireTicks(100); // 5 seconds
                        level.explode(null, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 1.0F, Level.ExplosionInteraction.MOB);
                    }
                }
        ));

        // Register Rock Throw
        spellEffectMap.put("Rock Throw", new SpellEffects(
                SoundEvents.STONE_BREAK,
                ParticleTypes.ASH,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().hurt(level.damageSources().magic(), 6.0F);
                        if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
                        }
                    }
                }
        ));

        // Register Water Jet
        spellEffectMap.put("Water Jet", new SpellEffects(
                SoundEvents.PLAYER_SWIM,
                ParticleTypes.DRIPPING_WATER,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().hurt(level.damageSources().magic(), 4.0F);
                        if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 0));
                        }
                    }
                }
        ));

        // Register Gust Bolt
        spellEffectMap.put("Gust Bolt", new SpellEffects(
                SoundEvents.ENDER_DRAGON_FLAP,
                ParticleTypes.CLOUD,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().push(0, 0.5, 0);
                    }
                }
        ));

        // Register Healing Ray
        spellEffectMap.put("Healing Ray", new SpellEffects(
                SoundEvents.AMETHYST_BLOCK_CHIME,
                ParticleTypes.HEART,
                (hitResult) -> {
                    if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                        livingEntity.heal(6.0f);
                    }
                }
        ));

        // Register Shadow Bolt
        spellEffectMap.put("Shadow Bolt", new SpellEffects(
                SoundEvents.WITHER_SHOOT,
                ParticleTypes.SQUID_INK,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().hurt(level.damageSources().magic(), 5.0F);
                        if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100));
                        }
                    }
                }
        ));

        spellEffectMap.put("Binding Shot", new SpellEffects(
                SoundEvents.LEASH_KNOT_PLACE,
                ParticleTypes.ENCHANTED_HIT,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                        if (!level.isClientSide) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));

                            if (level instanceof ServerLevel serverLevel) {
                                // Create our new animation entity
                                BindingTendrilsEntity animationEntity = new BindingTendrilsEntity(ModEntities.BINDING_TENDRILS.get(), serverLevel);
                                // Set its position to the target mob's position
                                animationEntity.setPos(livingEntity.position());
                                // Spawn it into the world
                                serverLevel.addFreshEntity(animationEntity);
                            }
                        }
                    }
                }
        ));

        // Register Unstable Orb
        spellEffectMap.put("Unstable Orb", new SpellEffects(
                SoundEvents.CREEPER_PRIMED,
                ParticleTypes.POOF,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        level.explode(null, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 2.0F, Level.ExplosionInteraction.MOB);
                    }
                }
        ));

        // Register Growth Spore
        spellEffectMap.put("Growth Spore", new SpellEffects(
                SoundEvents.CROP_PLANTED,
                ParticleTypes.COMPOSTER,
                (hitResult) -> {
                    if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                        livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
                    }
                }
        ));

        // Register Shatter Bolt
        spellEffectMap.put("Shatter Bolt", new SpellEffects(
                SoundEvents.GLASS_BREAK,
                ParticleTypes.CRIT,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().hurt(level.damageSources().magic(), 8.0F);
                    }
                }
        ));
    }

    public static SpellEffects getEffectsFor(Spell spell) {
        if (spell == null) return null;
        return spellEffectMap.get(spell.name());
    }
}
package net.Limbo.landfallmagic.spell.effect;

import net.Limbo.landfallmagic.ModParticles;
import net.Limbo.landfallmagic.entity.sorcerery.SpellProjectileEntity;
import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
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

        // Register Binding Shot - FIXED VERSION
        spellEffectMap.put("Binding Shot", new SpellEffects(
                SoundEvents.LEASH_KNOT_PLACE,
                ParticleTypes.ENCHANTED_HIT, // This is just for the projectile itself
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                        // Apply the slowness effect on the server
                        if (!level.isClientSide) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));

                            // Spawn particles using ServerLevel.sendParticles for proper network sync
                            if (level instanceof ServerLevel serverLevel) {
                                spawnBindingTendrilsAroundEntity(serverLevel, livingEntity);
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

    // Helper method to spawn binding tendrils around an entity properly
    private static void spawnBindingTendrilsAroundEntity(ServerLevel serverLevel, LivingEntity target) {
        // Get entity bounding box for proper coverage
        AABB boundingBox = target.getBoundingBox();

        // Calculate entity dimensions
        double entityWidth = Math.max(boundingBox.getXsize(), 0.6); // Minimum width
        double entityHeight = Math.max(boundingBox.getYsize(), 1.0); // Minimum height
        double entityDepth = Math.max(boundingBox.getZsize(), 0.6); // Minimum depth

        // Entity center position
        double centerX = target.getX();
        double centerY = target.getY() + entityHeight * 0.5; // Middle height
        double centerZ = target.getZ();

        // Spawn particles in a helix/binding pattern around the entity
        int helixPoints = 25; // More points for better coverage
        double maxRadius = Math.max(entityWidth, entityDepth) * 0.8;

        for (int i = 0; i < helixPoints; i++) {
            // Create helix pattern
            double angle = (i * Math.PI * 3) / helixPoints; // 1.5 full rotations
            double heightRatio = (double)i / helixPoints;
            double radius = maxRadius * (0.5 + 0.5 * Math.sin(heightRatio * Math.PI)); // Varying radius

            double offsetX = Math.cos(angle) * radius;
            double offsetZ = Math.sin(angle) * radius;
            double offsetY = (heightRatio * entityHeight) - (entityHeight * 0.5); // Center on entity

            double particleX = centerX + offsetX;
            double particleY = centerY + offsetY;
            double particleZ = centerZ + offsetZ;

            // Small inward velocity to make tendrils appear to "grip" the entity
            double velX = -offsetX * 0.02;
            double velY = serverLevel.random.nextGaussian() * 0.02;
            double velZ = -offsetZ * 0.02;

            serverLevel.sendParticles(
                    ModParticles.TENDRIL_PARTICLE.get(),
                    particleX, particleY, particleZ,
                    1, // particle count per spawn point
                    velX, velY, velZ, // velocity
                    0.03 // speed multiplier
            );
        }

        // Add some random particles for extra visual effect
        for (int i = 0; i < 10; i++) {
            double randomX = centerX + (serverLevel.random.nextDouble() - 0.5) * entityWidth * 1.2;
            double randomY = centerY + (serverLevel.random.nextDouble() - 0.5) * entityHeight * 1.2;
            double randomZ = centerZ + (serverLevel.random.nextDouble() - 0.5) * entityDepth * 1.2;

            serverLevel.sendParticles(
                    ModParticles.TENDRIL_PARTICLE.get(),
                    randomX, randomY, randomZ,
                    1, 0, 0, 0, 0
            );
        }
    }

    public static SpellEffects getEffectsFor(Spell spell) {
        if (spell == null) return null;
        return spellEffectMap.get(spell.name());
    }
}
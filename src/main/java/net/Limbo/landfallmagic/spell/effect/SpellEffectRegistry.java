// limboalarmclock/landfallmagic/LandfallMagic-55ca6e4061a02348b10815189a22f8d987a24ec1/src/main/java/net/Limbo/landfallmagic/spell/effect/SpellEffectRegistry.java
package net.Limbo.landfallmagic.spell.effect;

import net.Limbo.landfallmagic.ModEntities;
import net.Limbo.landfallmagic.entity.spellentity.BindingTendrilsEntity;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class SpellEffectRegistry {

    // A record to hold all the effects for a single spell
    public record SpellEffects(
            SoundEvent castSound,
            ParticleOptions particle,
            Consumer<EntityHitResult> onHit,
            BiConsumer<Level, BlockHitResult> onBlockHit
    ) {}

    private static final Map<String, SpellEffects> spellEffectMap = new HashMap<>();
    private static final Random random = new Random();

    public static void registerSpellEffects() {
        // Register Ignition Bolt - now only fire damage, no explosion
        spellEffectMap.put("Ignition Bolt", new SpellEffects(
                SoundEvents.GHAST_SHOOT,
                ParticleTypes.FLAME,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().setRemainingFireTicks(100); // 5 seconds
                        hitResult.getEntity().hurt(level.damageSources().onFire(), 6.0F);
                    }
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide) {
                        BlockPos hitPos = blockHitResult.getBlockPos();
                        BlockPos firePos = hitPos.relative(blockHitResult.getDirection());

                        // Set fire to the block face that was hit
                        if (level.isEmptyBlock(firePos)) {
                            level.setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState());
                        }

                        // Spawn flame particles
                        if (level instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(ParticleTypes.FLAME,
                                    hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5,
                                    10, 0.5, 0.5, 0.5, 0.1);
                        }
                    }
                }
        ));

        // Register Rock Throw - now pushes entity back
        spellEffectMap.put("Rock Throw", new SpellEffects(
                SoundEvents.STONE_BREAK,
                ParticleTypes.ASH,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().hurt(level.damageSources().magic(), 6.0F);
                        if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
                            // Push entity back (assuming hit from front, push backwards)
                            livingEntity.push(-livingEntity.getLookAngle().x * 2.0, 0.2, -livingEntity.getLookAngle().z * 2.0);
                        }
                    }
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockPos hitPos = blockHitResult.getBlockPos();

                        // Spawn ash particles and stone break sound
                        serverLevel.sendParticles(ParticleTypes.ASH,
                                hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5,
                                15, 0.5, 0.5, 0.5, 0.1);

                        // Small chance to break weak blocks like glass
                        BlockState blockState = level.getBlockState(hitPos);
                        if (blockState.is(Blocks.GLASS) || blockState.is(Blocks.GLASS_PANE) ||
                                blockState.is(Blocks.WHITE_STAINED_GLASS) || blockState.is(Blocks.TORCH)) {
                            level.destroyBlock(hitPos, true);
                        }
                    }
                }
        ));

        // Register Water Jet - now causes 2 hearts of freezing damage
        spellEffectMap.put("Water Jet", new SpellEffects(
                SoundEvents.PLAYER_SWIM,
                ParticleTypes.DRIPPING_WATER,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().hurt(level.damageSources().freeze(), 4.0F); // 2 hearts = 4.0F damage
                        if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 2)); // Increased slowness for freezing effect
                        }
                    }
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockPos hitPos = blockHitResult.getBlockPos();
                        BlockPos waterPos = hitPos.relative(blockHitResult.getDirection());

                        // Spawn water particles
                        serverLevel.sendParticles(ParticleTypes.DRIPPING_WATER,
                                hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5,
                                20, 0.5, 0.5, 0.5, 0.1);

                        // Extinguish fire blocks
                        if (level.getBlockState(hitPos).is(Blocks.FIRE)) {
                            level.removeBlock(hitPos, false);
                        }
                        if (level.getBlockState(waterPos).is(Blocks.FIRE)) {
                            level.removeBlock(waterPos, false);
                        }

                        // Turn lava into obsidian/cobblestone
                        if (level.getBlockState(hitPos).is(Blocks.LAVA)) {
                            level.setBlockAndUpdate(hitPos, Blocks.OBSIDIAN.defaultBlockState());
                        }
                    }
                }
        ));

        // Register Gust Bolt - now throws entity 4 blocks in the air
        spellEffectMap.put("Gust Bolt", new SpellEffects(
                SoundEvents.ENDER_DRAGON_FLAP,
                ParticleTypes.CLOUD,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().push(0, 4.0, 0); // Launch 4 blocks up
                    }
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockPos hitPos = blockHitResult.getBlockPos();

                        // Spawn cloud particles in a wind pattern
                        serverLevel.sendParticles(ParticleTypes.CLOUD,
                                hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5,
                                25, 1.0, 1.0, 1.0, 0.2);

                        // Blow away light blocks
                        BlockState blockState = level.getBlockState(hitPos);
                        if (blockState.is(Blocks.TORCH) || blockState.is(Blocks.REDSTONE_TORCH) ||
                                blockState.is(Blocks.DEAD_BUSH) || blockState.is(Blocks.TALL_GRASS) ||
                                blockState.is(Blocks.FERN) || blockState.is(Blocks.DANDELION) ||
                                blockState.is(Blocks.POPPY)) {
                            level.destroyBlock(hitPos, true);
                        }
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
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockPos hitPos = blockHitResult.getBlockPos();

                        // Spawn heart particles
                        serverLevel.sendParticles(ParticleTypes.HEART,
                                hitPos.getX() + 0.5, hitPos.getY() + 1.0, hitPos.getZ() + 0.5,
                                8, 0.5, 0.5, 0.5, 0.05);

                        // Heal crops or grow plants
                        BlockPos above = hitPos.above();
                        if (level.isEmptyBlock(above) && level.getBlockState(hitPos).is(Blocks.DIRT)) {
                            level.setBlockAndUpdate(hitPos, Blocks.GRASS_BLOCK.defaultBlockState());
                        }
                        // Grow crops faster
                        BlockState blockState = level.getBlockState(hitPos);
                        if (blockState.getBlock() instanceof net.minecraft.world.level.block.CropBlock cropBlock) {
                            if (random.nextFloat() < 0.3f) { // 30% chance to grow
                                level.setBlockAndUpdate(hitPos, cropBlock.getStateForAge(Math.min(cropBlock.getMaxAge(),
                                        cropBlock.getAge(blockState) + 1)));
                            }
                        }
                    }
                }
        ));

        // Register Shadow Bolt - now causes darkness for 3 seconds instead of blindness
        spellEffectMap.put("Shadow Bolt", new SpellEffects(
                SoundEvents.WITHER_SHOOT,
                ParticleTypes.SQUID_INK,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().hurt(level.damageSources().magic(), 5.0F);
                        if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 60)); // 3 seconds (60 ticks)
                        }
                    }
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockPos hitPos = blockHitResult.getBlockPos();

                        // Spawn dark particles
                        serverLevel.sendParticles(ParticleTypes.SQUID_INK,
                                hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5,
                                20, 0.5, 0.5, 0.5, 0.1);

                        // Extinguish light sources
                        if (level.getBlockState(hitPos).is(Blocks.TORCH) ||
                                level.getBlockState(hitPos).is(Blocks.REDSTONE_TORCH) ||
                                level.getBlockState(hitPos).is(Blocks.LANTERN) ||
                                level.getBlockState(hitPos).is(Blocks.CAMPFIRE)) {
                            level.destroyBlock(hitPos, false);
                        }

                        // Extinguish fire
                        if (level.getBlockState(hitPos).is(Blocks.FIRE)) {
                            level.removeBlock(hitPos, false);
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
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 5));

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
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockPos hitPos = blockHitResult.getBlockPos();

                        // Spawn enchanted particles
                        serverLevel.sendParticles(ParticleTypes.ENCHANTED_HIT,
                                hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5,
                                15, 0.5, 0.5, 0.5, 0.1);

                        // Create binding tendrils animation at the impact point
                        BindingTendrilsEntity animationEntity = new BindingTendrilsEntity(ModEntities.BINDING_TENDRILS.get(), serverLevel);
                        animationEntity.setPos(hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5);
                        serverLevel.addFreshEntity(animationEntity);
                    }
                }
        ));

        // Register Unstable Orb - now has chance to either explode or heal
        spellEffectMap.put("Unstable Orb", new SpellEffects(
                SoundEvents.CREEPER_PRIMED,
                ParticleTypes.POOF,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        // 50% chance to explode, 50% chance to heal
                        if (random.nextBoolean()) {
                            // Explode
                            level.explode(null, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 2.0F, Level.ExplosionInteraction.MOB);
                        } else {
                            // Heal instead
                            if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                                livingEntity.heal(4.0f); // 2 hearts of healing
                            }
                        }
                    }
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide) {
                        BlockPos hitPos = blockHitResult.getBlockPos();
                        // 50% chance to explode, 50% chance to create beneficial effects
                        if (random.nextBoolean()) {
                            // Explode
                            level.explode(null, hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5, 2.0F, Level.ExplosionInteraction.MOB);
                        } else {
                            // Create beneficial effect - spawn flowers or heal nearby plants
                            if (level instanceof ServerLevel serverLevel) {
                                serverLevel.sendParticles(ParticleTypes.HEART,
                                        hitPos.getX() + 0.5, hitPos.getY() + 1.0, hitPos.getZ() + 0.5,
                                        10, 1.0, 1.0, 1.0, 0.1);

                                // Try to place a flower nearby
                                for (int i = 0; i < 3; i++) {
                                    BlockPos flowerPos = hitPos.offset(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
                                    if (level.isEmptyBlock(flowerPos) && level.getBlockState(flowerPos.below()).is(Blocks.GRASS_BLOCK)) {
                                        level.setBlockAndUpdate(flowerPos, random.nextBoolean() ?
                                                Blocks.DANDELION.defaultBlockState() : Blocks.POPPY.defaultBlockState());
                                        break;
                                    }
                                }
                            }
                        }
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
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockPos hitPos = blockHitResult.getBlockPos();

                        // Spawn composter particles
                        serverLevel.sendParticles(ParticleTypes.COMPOSTER,
                                hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5,
                                20, 0.5, 0.5, 0.5, 0.1);

                        // Grow plants in a small area
                        for (int x = -1; x <= 1; x++) {
                            for (int z = -1; z <= 1; z++) {
                                BlockPos checkPos = hitPos.offset(x, 0, z);
                                BlockPos abovePos = checkPos.above();

                                // Turn dirt to grass
                                if (level.getBlockState(checkPos).is(Blocks.DIRT) && level.isEmptyBlock(abovePos)) {
                                    level.setBlockAndUpdate(checkPos, Blocks.GRASS_BLOCK.defaultBlockState());
                                }

                                // Grow crops
                                BlockState blockState = level.getBlockState(checkPos);
                                if (blockState.getBlock() instanceof net.minecraft.world.level.block.CropBlock cropBlock) {
                                    if (random.nextFloat() < 0.6f) { // 60% chance to grow
                                        level.setBlockAndUpdate(checkPos, cropBlock.getStateForAge(Math.min(cropBlock.getMaxAge(),
                                                cropBlock.getAge(blockState) + random.nextInt(3) + 1)));
                                    }
                                }

                                // Spawn mushrooms
                                if (level.getBlockState(checkPos).is(Blocks.GRASS_BLOCK) && level.isEmptyBlock(abovePos) && random.nextFloat() < 0.2f) {
                                    level.setBlockAndUpdate(abovePos, random.nextBoolean() ?
                                            Blocks.BROWN_MUSHROOM.defaultBlockState() : Blocks.RED_MUSHROOM.defaultBlockState());
                                }
                            }
                        }
                    }
                }
        ));

        // Register Shatter Bolt - now gives weakness and small explosion
        spellEffectMap.put("Shatter Bolt", new SpellEffects(
                SoundEvents.GLASS_BREAK,
                ParticleTypes.CRIT,
                (hitResult) -> {
                    Level level = hitResult.getEntity().level();
                    if (!level.isClientSide) {
                        hitResult.getEntity().hurt(level.damageSources().magic(), 8.0F);
                        if (hitResult.getEntity() instanceof LivingEntity livingEntity) {
                            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 80, 1)); // 4 seconds of weakness II
                        }
                        // Small explosion
                        level.explode(null, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z, 1.0F, Level.ExplosionInteraction.MOB);
                    }
                },
                (level, blockHitResult) -> {
                    if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
                        BlockPos hitPos = blockHitResult.getBlockPos();

                        // Spawn crit particles
                        serverLevel.sendParticles(ParticleTypes.CRIT,
                                hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5,
                                15, 0.5, 0.5, 0.5, 0.2);

                        // Small explosion that's more likely to break fragile blocks
                        level.explode(null, hitPos.getX() + 0.5, hitPos.getY() + 0.5, hitPos.getZ() + 0.5, 1.0F, Level.ExplosionInteraction.MOB);

                        // Extra effect on glass and similar blocks
                        BlockState blockState = level.getBlockState(hitPos);
                        if (blockState.is(Blocks.GLASS) || blockState.is(Blocks.GLASS_PANE) ||
                                blockState.getBlock().toString().contains("glass") ||
                                blockState.is(Blocks.ICE) || blockState.is(Blocks.PACKED_ICE)) {
                            level.destroyBlock(hitPos, true);
                        }
                    }
                }
        ));
    }

    public static SpellEffects getEffectsFor(Spell spell) {
        if (spell == null) return null;
        return spellEffectMap.get(spell.name());
    }

    // Helper method to get entity hit effects
    public static Consumer<EntityHitResult> getEntityEffectsFor(Spell spell) {
        SpellEffects effects = getEffectsFor(spell);
        return effects != null ? effects.onHit() : null;
    }

    // Helper method to get block hit effects
    public static BiConsumer<Level, BlockHitResult> getBlockEffectsFor(Spell spell) {
        SpellEffects effects = getEffectsFor(spell);
        return effects != null ? effects.onBlockHit() : null;
    }
}
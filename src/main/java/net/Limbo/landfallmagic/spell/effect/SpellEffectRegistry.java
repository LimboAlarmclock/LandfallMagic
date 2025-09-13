package net.Limbo.landfallmagic.spell.effect;

import net.Limbo.landfallmagic.entity.sorcerery.SpellProjectileEntity;
import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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

        // Add future spells here...
    }

    public static SpellEffects getEffectsFor(Spell spell) {
        if (spell == null) return null;
        return spellEffectMap.get(spell.name());
    }
}
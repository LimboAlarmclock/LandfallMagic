package net.Limbo.landfallmagic.entity.sorcerery;

import net.Limbo.landfallmagic.ModEntities;
import net.Limbo.landfallmagic.datagen.ModDataComponents;
import net.Limbo.landfallmagic.spell.Spell;
import net.Limbo.landfallmagic.spell.effect.SpellEffectRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.Limbo.landfallmagic.ModDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class SpellProjectileEntity extends ThrowableItemProjectile {

    // This data accessor will sync our spell data from server to client
    private static final EntityDataAccessor<Spell> DATA_SPELL =
            SynchedEntityData.defineId(SpellProjectileEntity.class, ModDataSerializers.SPELL_SERIALIZER);

    public SpellProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public SpellProjectileEntity(Level pLevel, LivingEntity pShooter, Spell spell) {
        super(ModEntities.SPELL_PROJECTILE.get(), pShooter, pLevel);
        this.setSpell(spell);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
        pBuilder.define(DATA_SPELL, new Spell(null, null, "")); // Default empty spell
    }

    public void setSpell(Spell spell) {
        this.getEntityData().set(DATA_SPELL, spell);
    }

    public Spell getSpell() {
        return this.getEntityData().get(DATA_SPELL);
    }

    @Override
    public void tick() {
        super.tick();
        SpellEffectRegistry.SpellEffects effects = SpellEffectRegistry.getEffectsFor(getSpell());
        if (effects != null && this.level().isClientSide) {
            this.level().addParticle(effects.particle(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!this.level().isClientSide) {
            Spell spell = getSpell();
            SpellEffectRegistry.SpellEffects effects = SpellEffectRegistry.getEffectsFor(spell);
            if (effects == null) {
                this.discard();
                return;
            }

            if (pResult.getType() == HitResult.Type.ENTITY) {
                effects.onHit().accept((EntityHitResult) pResult);
            } else if (pResult instanceof BlockHitResult blockHitResult) {
                if (effects.onBlockHit() != null) {
                    effects.onBlockHit().accept(this.level(), blockHitResult);
                }
            }

            this.discard();
        }
    }


    @Override
    protected Item getDefaultItem() {
        // This is just for rendering, it can be a generic item
        return Items.AIR;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        // We don't need to save the spell data here since it's synced via the data accessor
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        // We don't need to read the spell data here either
    }
}
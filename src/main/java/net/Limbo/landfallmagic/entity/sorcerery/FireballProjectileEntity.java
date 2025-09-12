package net.Limbo.landfallmagic.entity.sorcerery;

import net.Limbo.landfallmagic.ModEntities;
import net.Limbo.landfallmagic.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FireballProjectileEntity extends ThrowableItemProjectile {

    public FireballProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FireballProjectileEntity(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.IGNITION_BOLT.get(), pShooter, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        // This is the item that the projectile will look like
        return ModItems.FIRE_ESSENCE.get();
    }

    // This method is called every tick to add cool particles
    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            this.level().addParticle(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    // This is called when the projectile hits an entity
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if (!this.level().isClientSide) {
            pResult.getEntity().setRemainingFireTicks(5); // Set the entity on fire for 5 seconds
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.0F, Level.ExplosionInteraction.MOB);
            this.discard(); // Remove the projectile
        }
    }

    // This is called when the projectile hits a block
    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level().isClientSide) {
            // Create a small explosion that damages mobs but doesn't break blocks
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1.5F, Level.ExplosionInteraction.MOB);
            this.discard(); // Remove the projectile
        }
    }
}
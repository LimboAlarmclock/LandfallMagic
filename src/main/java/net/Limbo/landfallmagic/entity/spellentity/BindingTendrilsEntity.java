package net.Limbo.landfallmagic.entity.spellentity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation; // <-- New Import
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class BindingTendrilsEntity extends Entity implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Define the animation as a constant
    private static final RawAnimation BIND_ANIM = RawAnimation.begin().thenPlay("animation.binding_tendrils.bind");

    private static final EntityDataAccessor<Optional<UUID>> TARGET_UUID =
            SynchedEntityData.defineId(BindingTendrilsEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private LivingEntity target;


    public BindingTendrilsEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.noPhysics = true;
    }
    public void setTarget(@Nullable LivingEntity target) {
        this.target = target;
        this.entityData.set(TARGET_UUID, Optional.ofNullable(target).map(Entity::getUUID));
    }
    @Nullable
    public LivingEntity getTarget() {
        if (this.target == null && this.level().isClientSide) {
            this.entityData.get(TARGET_UUID).ifPresent(uuid -> {
                if (this.level().getPlayerByUUID(uuid) != null) {
                    this.target = this.level().getPlayerByUUID(uuid);
                }
            });
        }
        return this.target;
    }


    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.tickCount > 40) {
            this.remove(RemovalReason.DISCARDED);
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        pBuilder.define(TARGET_UUID, Optional.empty());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) { }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) { }

    /**
     * This is the controller that plays your animation.
     */
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, event -> {
            // Use the new RawAnimation object
            event.getController().setAnimation(BIND_ANIM);
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
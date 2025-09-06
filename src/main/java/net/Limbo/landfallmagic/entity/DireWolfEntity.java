package net.Limbo.landfallmagic.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DireWolfEntity extends Wolf {

    public DireWolfEntity(EntityType<? extends Wolf> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setTame(false, false); // Ensure it starts untamed with no owner
    }

    @Override
    protected void registerGoals() {
        // Movement and basic behavior goals
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        // Optional: Add pack hunting behavior
        this.goalSelector.addGoal(6, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));

        // Target selection goals
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this)); // Retaliate when hurt
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Sheep.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Rabbit.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Wolf.createAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 12.0D); // Increased detection range
    }

    public boolean isTamable() {
        return false;
    }

    @Nullable
    public Wolf getBreedOffspring(ServerLevel pLevel, Wolf pOtherParent) {
        // Also override the Wolf-specific method for safety
        return null;
    }

    // Prevent taming attempts
    public void setTame(boolean pTamed) {
    }

    // Optional: Make them naturally hostile (always angry)
    public boolean isAngryAt(Player pPlayer) {
        return true; // Always hostile to players
    }

    // Optional: Prevent healing with meat
    @Override
    public boolean isFood(net.minecraft.world.item.ItemStack pStack) {
        return false; // Cannot be fed
    }
}
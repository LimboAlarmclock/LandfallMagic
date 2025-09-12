package net.Limbo.landfallmagic.item;

import net.Limbo.landfallmagic.datagen.ModDataComponents;
import net.Limbo.landfallmagic.entity.sorcerery.FireballProjectileEntity;
import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SpellPageItem extends Item {

    public SpellPageItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
        Spell spell = getSpell(itemStack);

        if (spell != null) {
            if (!pLevel.isClientSide) {
                // We'll use the spell's unique name to decide what to do
                switch (spell.name()) {
                    case "Ignition Bolt":
                        castIgnitionBolt(pLevel, pPlayer, itemStack);
                        break;
                    // You can add more cases for other spells here later
                    default:
                        pPlayer.sendSystemMessage(Component.literal("This spell has not yet been implemented!"));
                        break;
                }
            }
            return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
        }
        return InteractionResultHolder.fail(itemStack);
    }

    private void castIgnitionBolt(Level level, Player player, ItemStack stack) {
        // Play a casting sound
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.GHAST_SHOOT, SoundSource.PLAYERS, 0.5F, 1.0F);

        // Spawn the projectile
        FireballProjectileEntity fireball = new FireballProjectileEntity(level, player);
        fireball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        level.addFreshEntity(fireball);

        // Add a cooldown to the item
        player.getCooldowns().addCooldown(this, 20); // 20 ticks = 1 second cooldown
    }

    public static void setSpell(ItemStack stack, Spell spell) {
        try {
            if (spell == null || stack == null || stack.isEmpty()) return;
            if (ModDataComponents.SPELL == null || ModDataComponents.SPELL.get() == null) {
                landfallmagic.LOGGER.error("ModDataComponents.SPELL is null! Cannot set spell data.");
                return;
            }
            stack.set(ModDataComponents.SPELL.get(), spell);
        } catch (Exception e) {
            landfallmagic.LOGGER.error("Error in SpellPageItem.setSpell: ", e);
        }
    }

    public static Spell getSpell(ItemStack stack) {
        try {
            if (stack == null || stack.isEmpty()) return null;
            if (ModDataComponents.SPELL == null || ModDataComponents.SPELL.get() == null) {
                landfallmagic.LOGGER.error("ModDataComponents.SPELL is null! Cannot get spell data.");
                return null;
            }
            return stack.get(ModDataComponents.SPELL.get());
        } catch (Exception e) {
            landfallmagic.LOGGER.error("Error in SpellPageItem.getSpell: ", e);
            return null;
        }
    }

    @Override
    public Component getName(ItemStack pStack) {
        Spell spell = getSpell(pStack);
        if (spell != null) {
            return Component.translatable("item.landfallmagic.spell_page.named", spell.getDisplayName());
        }
        return super.getName(pStack);
    }
}
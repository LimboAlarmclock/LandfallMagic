package net.Limbo.landfallmagic.item;

import net.Limbo.landfallmagic.datagen.ModDataComponents;
import net.Limbo.landfallmagic.entity.sorcerery.SpellProjectileEntity;
import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.spell.Spell;
import net.Limbo.landfallmagic.spell.SpellForm;
import net.Limbo.landfallmagic.spell.effect.SpellEffectRegistry;
import net.minecraft.network.chat.Component;
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
                // Get the spell's effects from our new registry
                SpellEffectRegistry.SpellEffects effects = SpellEffectRegistry.getEffectsFor(spell);

                if (effects != null) {
                    // This handles all projectile spells now!
                    if (spell.form() == SpellForm.PROJECTILE) {
                        castProjectile(pLevel, pPlayer, itemStack, spell, effects);
                    }
                    // We can add handlers for other forms like SELF, ZONE, etc. here later
                    else {
                        pPlayer.sendSystemMessage(Component.literal("This spell form has not yet been implemented!"));
                    }
                } else {
                    pPlayer.sendSystemMessage(Component.literal("This spell has no defined effect!"));
                }
            }
            return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
        }
        return InteractionResultHolder.fail(itemStack);
    }

    private void castProjectile(Level level, Player player, ItemStack stack, Spell spell, SpellEffectRegistry.SpellEffects effects) {
        // Play the casting sound defined in the registry
        level.playSound(null, player.getX(), player.getY(), player.getZ(), effects.castSound(), SoundSource.PLAYERS, 0.7F, 1.2F);

        // Spawn the generic projectile and give it the spell data
        SpellProjectileEntity projectile = new SpellProjectileEntity(level, player, spell);
        projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        level.addFreshEntity(projectile);

        // Add a cooldown
        player.getCooldowns().addCooldown(this, 20); // 1 second cooldown
    }

    // REMOVE the old castIgnitionBolt() and castRockThrow() methods

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
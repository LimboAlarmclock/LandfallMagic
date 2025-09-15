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

        // DEBUG: Log that the method was called
        landfallmagic.LOGGER.info("SpellPageItem.use() called on {} side", pLevel.isClientSide ? "CLIENT" : "SERVER");

        Spell spell = getSpell(itemStack);

        // DEBUG: Log spell retrieval result
        if (spell == null) {
            landfallmagic.LOGGER.error("No spell found on spell page item!");
            pPlayer.sendSystemMessage(Component.literal("DEBUG: No spell data found on this item!"));
            return InteractionResultHolder.fail(itemStack);
        } else {
            landfallmagic.LOGGER.info("Found spell: {} (Form: {}, Element: {})",
                    spell.name(), spell.form(), spell.primaryElement());
        }

        if (!pLevel.isClientSide) {
            // Get the spell's effects from our new registry
            SpellEffectRegistry.SpellEffects effects = SpellEffectRegistry.getEffectsFor(spell);

            if (effects != null) {
                landfallmagic.LOGGER.info("Found spell effects for: {}", spell.name());

                // This handles all projectile spells now!
                if (spell.form() == SpellForm.PROJECTILE) {
                    landfallmagic.LOGGER.info("Casting projectile spell: {}", spell.name());
                    castProjectile(pLevel, pPlayer, itemStack, spell, effects);
                }
                // We can add handlers for other forms like SELF, ZONE, etc. here later
                else {
                    landfallmagic.LOGGER.info("Spell form {} not yet implemented", spell.form());
                    pPlayer.sendSystemMessage(Component.literal("This spell form (" + spell.form() + ") has not yet been implemented!"));
                }
            } else {
                landfallmagic.LOGGER.error("No spell effects found for: {}", spell.name());
                pPlayer.sendSystemMessage(Component.literal("This spell (" + spell.name() + ") has no defined effect!"));
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }

    private void castProjectile(Level level, Player player, ItemStack stack, Spell spell, SpellEffectRegistry.SpellEffects effects) {
        landfallmagic.LOGGER.info("castProjectile called for spell: {}", spell.name());

        try {
            // Play the casting sound defined in the registry
            level.playSound(null, player.getX(), player.getY(), player.getZ(), effects.castSound(), SoundSource.PLAYERS, 0.7F, 1.2F);

            // Spawn the generic projectile and give it the spell data
            SpellProjectileEntity projectile = new SpellProjectileEntity(level, player, spell);
            projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(projectile);

            landfallmagic.LOGGER.info("Successfully spawned projectile for spell: {}", spell.name());

            // Add a cooldown
            player.getCooldowns().addCooldown(this, 20); // 1 second cooldown
        } catch (Exception e) {
            landfallmagic.LOGGER.error("Error casting projectile spell: ", e);
            player.sendSystemMessage(Component.literal("Error casting spell: " + e.getMessage()));
        }
    }

    public static void setSpell(ItemStack stack, Spell spell) {
        try {
            if (spell == null || stack == null || stack.isEmpty()) {
                landfallmagic.LOGGER.error("Cannot set spell - null parameters: spell={}, stack={}", spell, stack);
                return;
            }

            if (ModDataComponents.SPELL == null || ModDataComponents.SPELL.get() == null) {
                landfallmagic.LOGGER.error("ModDataComponents.SPELL is null! Cannot set spell data.");
                return;
            }

            stack.set(ModDataComponents.SPELL.get(), spell);
            landfallmagic.LOGGER.info("Successfully set spell {} on item stack", spell.name());
        } catch (Exception e) {
            landfallmagic.LOGGER.error("Error in SpellPageItem.setSpell: ", e);
        }
    }

    public static Spell getSpell(ItemStack stack) {
        try {
            if (stack == null || stack.isEmpty()) {
                landfallmagic.LOGGER.debug("Cannot get spell - stack is null or empty");
                return null;
            }

            if (ModDataComponents.SPELL == null || ModDataComponents.SPELL.get() == null) {
                landfallmagic.LOGGER.error("ModDataComponents.SPELL is null! Cannot get spell data.");
                return null;
            }

            Spell spell = stack.get(ModDataComponents.SPELL.get());
            if (spell != null) {
                landfallmagic.LOGGER.debug("No spell data found on item stack");
            }
            return spell;
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
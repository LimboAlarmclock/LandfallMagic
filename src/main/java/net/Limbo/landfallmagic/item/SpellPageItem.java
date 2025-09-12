package net.Limbo.landfallmagic.item;

import net.Limbo.landfallmagic.datagen.ModDataComponents;
import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SpellPageItem extends Item {

    public SpellPageItem(Properties pProperties) {
        super(pProperties);
    }

    public static void setSpell(ItemStack stack, Spell spell) {
        try {
            landfallmagic.LOGGER.info("SpellPageItem.setSpell called with spell: {}", spell != null ? spell.name : "NULL");

            if (spell == null) {
                landfallmagic.LOGGER.warn("Attempted to set null spell on ItemStack");
                return;
            }

            if (stack == null || stack.isEmpty()) {
                landfallmagic.LOGGER.warn("Attempted to set spell on null or empty ItemStack");
                return;
            }

            // Check if ModDataComponents.SPELL is properly registered
            if (ModDataComponents.SPELL == null || ModDataComponents.SPELL.get() == null) {
                landfallmagic.LOGGER.error("ModDataComponents.SPELL is null! Cannot set spell data.");
                return;
            }

            landfallmagic.LOGGER.info("Setting spell component on ItemStack");
            stack.set(ModDataComponents.SPELL.get(), spell);
            landfallmagic.LOGGER.info("Spell component set successfully");

        } catch (Exception e) {
            landfallmagic.LOGGER.error("Error in SpellPageItem.setSpell: ", e);
            e.printStackTrace();
        }
    }

    public static Spell getSpell(ItemStack stack) {
        try {
            if (stack == null || stack.isEmpty()) {
                return null;
            }

            if (ModDataComponents.SPELL == null || ModDataComponents.SPELL.get() == null) {
                landfallmagic.LOGGER.error("ModDataComponents.SPELL is null! Cannot get spell data.");
                return null;
            }

            return stack.get(ModDataComponents.SPELL.get());

        } catch (Exception e) {
            landfallmagic.LOGGER.error("Error in SpellPageItem.getSpell: ", e);
            e.printStackTrace();
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
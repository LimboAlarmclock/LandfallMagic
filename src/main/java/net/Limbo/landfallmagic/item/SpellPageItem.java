package net.Limbo.landfallmagic.item;

import net.Limbo.landfallmagic.datagen.ModDataComponents;
import net.Limbo.landfallmagic.spell.Spell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class SpellPageItem extends Item {

    public SpellPageItem(Properties pProperties) {
        super(pProperties);
    }

    public static void setSpell(ItemStack stack, Spell spell) {
        if (spell != null) {
            // Use the new component system to set data
            stack.set(ModDataComponents.SPELL.get(), spell);
        }
    }

    public static Spell getSpell(ItemStack stack) {
        // Use the new component system to get data
        return stack.get(ModDataComponents.SPELL.get());
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
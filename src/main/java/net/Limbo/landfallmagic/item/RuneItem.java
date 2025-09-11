package net.Limbo.landfallmagic.item;

import net.Limbo.landfallmagic.spell.SpellElement;
import net.Limbo.landfallmagic.spell.SpellForm;
import net.minecraft.world.item.Item;

public class RuneItem extends Item {

    private final SpellForm form;
    private final SpellElement element;

    public RuneItem(SpellForm form) {
        super(new Item.Properties());
        this.form = form;
        this.element = null;
    }

    public RuneItem(SpellElement element) {
        super(new Item.Properties());
        this.form = null;
        this.element = element;
    }

    public SpellForm getForm() {
        return form;
    }

    public SpellElement getElement() {
        return element;
    }
}
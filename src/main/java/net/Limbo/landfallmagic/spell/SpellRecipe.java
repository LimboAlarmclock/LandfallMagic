package net.Limbo.landfallmagic.spell;

/**
 * A base interface for all spell recipes in the research table.
 * Ensures that every recipe can provide a resulting Spell.
 */
public interface SpellRecipe {
    Spell getResult();
}
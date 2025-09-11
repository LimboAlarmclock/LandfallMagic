package net.Limbo.landfallmagic.spell;

/**
 * Represents a Tier 1 recipe for crafting a base spell.
 * Recipe: Form Rune + Element Rune = Base Spell
 * @param form The required SpellForm component.
 * @param element The required SpellElement component.
 * @param result The resulting base Spell.
 */
public record Tier1SpellRecipe(SpellForm form, SpellElement element, Spell result) implements SpellRecipe {
    @Override
    public Spell getResult() {
        return result;
    }
}
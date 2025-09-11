package net.Limbo.landfallmagic.spell;

/**
 * Represents a Tier 2 recipe for augmenting an existing spell.
 * Recipe: Base Spell Page + Element Rune (as Augment) = Augmented Spell
 * @param baseSpell The required base Spell to be augmented.
 * @param augment The SpellElement used to augment the base spell.
 * @param result The resulting augmented Spell.
 */
public record Tier2SpellRecipe(Spell baseSpell, SpellElement augment, Spell result) implements SpellRecipe {
    @Override
    public Spell getResult() {
        return result;
    }
}
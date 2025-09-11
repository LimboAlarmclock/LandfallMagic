package net.Limbo.landfallmagic.spell;

import net.Limbo.landfallmagic.karma.KarmaType;

public enum SpellElement {
    FIRE(KarmaType.FIRE),
    EARTH(KarmaType.EARTH),
    WATER(KarmaType.WATER),
    AIR(KarmaType.AIR),
    LIGHT(KarmaType.LIGHT),
    DARK(KarmaType.DARK),
    ORDER(KarmaType.ORDER),
    CHAOS(KarmaType.CHAOS),
    CREATION(KarmaType.CREATION),
    DESTRUCTION(KarmaType.DESTRUCTION);

    private final KarmaType karmaType;

    SpellElement(KarmaType karmaType) {
        this.karmaType = karmaType;
    }

    public KarmaType getKarmaType() {
        return karmaType;
    }
}
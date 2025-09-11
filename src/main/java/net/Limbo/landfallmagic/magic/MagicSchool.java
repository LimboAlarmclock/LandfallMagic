package net.Limbo.landfallmagic.magic;

import net.minecraft.util.StringRepresentable;

public enum MagicSchool implements StringRepresentable {
    NONE("none"),
    DRUIDIC("druidic"),
    SORCERY("sorcery"),
    RITUALIST("ritualist");

    private final String name;

    MagicSchool(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
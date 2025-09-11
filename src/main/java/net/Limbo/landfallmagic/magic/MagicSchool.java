package net.Limbo.landfallmagic.magic;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;

public enum MagicSchool implements StringRepresentable {
    NONE("none"),
    DRUIDIC("druidic"),
    SORCERY("sorcery"),
    RITUALIST("ritualist");

    public static final Codec<MagicSchool> CODEC = StringRepresentable.fromEnum(MagicSchool::values);

    private final String name;

    MagicSchool(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
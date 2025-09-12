package net.Limbo.landfallmagic.magic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class PlayerMagic implements IPlayerMagic {
    public static final Codec<PlayerMagic> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    MagicSchool.CODEC.fieldOf("magicSchool").forGetter(PlayerMagic::getMagicSchool)
            ).apply(instance, PlayerMagic::new)
    );

    private MagicSchool magicSchool;

    public PlayerMagic() {
        this.magicSchool = MagicSchool.NONE;
    }

    public PlayerMagic(MagicSchool magicSchool) {
        this.magicSchool = magicSchool != null ? magicSchool : MagicSchool.NONE;
    }

    @Override
    public MagicSchool getMagicSchool() {
        return this.magicSchool;
    }

    @Override
    public void setMagicSchool(MagicSchool school) {
        this.magicSchool = school != null ? school : MagicSchool.NONE;
    }

    @Override
    public PlayerMagic copy() {
        return new PlayerMagic(this.magicSchool);
    }
}

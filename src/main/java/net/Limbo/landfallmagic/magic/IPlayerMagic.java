package net.Limbo.landfallmagic.magic;

public interface IPlayerMagic {
    MagicSchool getMagicSchool();
    void setMagicSchool(MagicSchool school);

    // Return the concrete type for the copyer
    PlayerMagic copy();
}
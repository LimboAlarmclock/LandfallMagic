package net.Limbo.landfallmagic.magic;

import net.minecraft.nbt.CompoundTag;

public interface IPlayerMagic {
    MagicSchool getMagicSchool();
    void setMagicSchool(MagicSchool school);
    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag nbt);
}
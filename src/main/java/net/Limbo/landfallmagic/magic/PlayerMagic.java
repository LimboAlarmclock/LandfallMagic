package net.Limbo.landfallmagic.magic;

import net.minecraft.nbt.CompoundTag;

public class PlayerMagic implements IPlayerMagic {
    private MagicSchool magicSchool = MagicSchool.NONE;

    @Override
    public MagicSchool getMagicSchool() {
        return this.magicSchool;
    }

    @Override
    public void setMagicSchool(MagicSchool school) {
        this.magicSchool = school;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putString("magicSchool", this.magicSchool.getSerializedName());
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        // Find the MagicSchool enum constant that matches the saved string
        this.magicSchool = MagicSchool.valueOf(nbt.getString("magicSchool").toUpperCase());
    }
}
package net.Limbo.landfallmagic.karma;

import net.minecraft.nbt.CompoundTag;
import java.util.Comparator;

public interface IKarma {
    int getKarma(KarmaType type);
    void setKarma(KarmaType type, int value);
    void addKarma(KarmaType type, int amount);
    CompoundTag serializeNBT();
    void deserializeNBT(CompoundTag tag);

    /**
     * Helper method to find the KarmaType with the highest value.
     * @return The dominant KarmaType.
     */
    default KarmaType getTopKarmaType() {
        return java.util.Arrays.stream(KarmaType.values())
                .max(Comparator.comparingInt(this::getKarma))
                .orElse(KarmaType.FIRE); // Default to fire if all are 0
    }
}
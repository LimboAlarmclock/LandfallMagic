package net.Limbo.landfallmagic.karma;

import net.Limbo.landfallmagic.Config;
import net.minecraft.nbt.CompoundTag;
import java.util.EnumMap;

public class Karma implements IKarma {

    private final EnumMap<KarmaType, Integer> karmaMap = new EnumMap<>(KarmaType.class);

    public Karma() {
        for (KarmaType type : KarmaType.values()) {
            karmaMap.put(type, 0); // initialize all types to 0
        }
    }

    @Override
    public int getKarma(KarmaType type) {
        return karmaMap.get(type);
    }

    @Override
    public void setKarma(KarmaType type, int value) {
        int maxValue = Config.MAX_KARMA_VALUE.get();
        karmaMap.put(type, Math.max(0, Math.min(maxValue, value))); // clamp 0-maxValue
        markDirty();
    }

    @Override
    public void addKarma(KarmaType type, int amount) {
        setKarma(type, karmaMap.get(type) + amount); // automatically clamps
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        for (KarmaType type : KarmaType.values()) {
            tag.putInt(type.name().toLowerCase(), karmaMap.get(type));
        }
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        for (KarmaType type : KarmaType.values()) {
            karmaMap.put(type, tag.getInt(type.name().toLowerCase()));
        }
    }

    private void markDirty() {
        // will be handled by KarmaSavedData
    }
}

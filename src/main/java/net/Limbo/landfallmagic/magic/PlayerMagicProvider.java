package net.Limbo.landfallmagic.magic;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.capabilities.Capability;
import net.neoforged.neoforge.common.capabilities.CapabilityManager;
import net.neoforged.neoforge.common.capabilities.CapabilityToken;
import net.neoforged.neoforge.common.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerMagicProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static final Capability<IPlayerMagic> PLAYER_MAGIC = CapabilityManager.get(new CapabilityToken<>() {});

    private IPlayerMagic magicSchool = null;
    private final LazyOptional<IPlayerMagic> optional = LazyOptional.of(this::getOrCreate);

    private IPlayerMagic getOrCreate() {
        if (this.magicSchool == null) {
            this.magicSchool = new PlayerMagic();
        }
        return this.magicSchool;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MAGIC) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return getOrCreate().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getOrCreate().deserializeNBT(nbt);
    }
}
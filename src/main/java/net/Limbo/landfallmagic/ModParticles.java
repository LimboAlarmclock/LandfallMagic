package net.Limbo.landfallmagic;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.minecraft.core.registries.Registries;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, landfallmagic.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TENDRIL_PARTICLE =
            PARTICLE_TYPES.register("tendril_particle", () -> new SimpleParticleType(true));

}
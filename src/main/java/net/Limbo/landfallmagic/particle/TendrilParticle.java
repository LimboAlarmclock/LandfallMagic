package net.Limbo.landfallmagic.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class TendrilParticle extends TextureSheetParticle {

    protected TendrilParticle(ClientLevel pLevel, double pX, double pY, double pZ,
                              double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        // Set the particle's behavior
        this.friction = 0.8F;
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        this.quadSize *= 0.85F;
        this.lifetime = 20 + this.random.nextInt(10); // Live for 1 to 1.5 seconds
        this.gravity = 0.02f; // Slightly fall

        // Set the color (greyish)
        this.rCol = 0.6F;
        this.gCol = 0.6F;
        this.bCol = 0.6F;
    }

    @Override
    public void tick() {
        super.tick();
        fadeOut();
    }

    private void fadeOut() {
        // Fade the particle out over its lifetime
        this.alpha = (-(1/(float)this.lifetime) * this.age + 1);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    // This is the "factory" that the game uses to create our particle
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType particleType, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            TendrilParticle particle = new TendrilParticle(level, x, y, z, dx, dy, dz);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}
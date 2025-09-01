package net.Limbo.landfallmagic;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class HolographicScreen extends Screen {

    // A list to hold our new karma particles
    private final List<KarmaParticle> particles = new ArrayList<>();
    private final Random random = new Random();

    // FIXED: Re-added the UI_COLOR variable for other screens to use
    protected static final int UI_COLOR = 0x00FFFF; // Cyan

    // Define colors for each of the 10 Karma types
    private static final int[] KARMA_COLORS = {
            0xFF4500, // Fire (OrangeRed)
            0x1E90FF, // Water (DodgerBlue)
            0x228B22, // Earth (ForestGreen)
            0xADD8E6, // Air (LightBlue)
            0xFFD700, // Light (Gold)
            0x4B0082, // Dark (Indigo)
            0x00FFFF, // Order (Cyan)
            0xFF00FF, // Chaos (Magenta)
            0x00FF7F, // Creation (SpringGreen)
            0x8B0000  // Destruction (DarkRed)
    };

    public HolographicScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
        // Create an initial set of particles when the GUI is opened
        if (particles.isEmpty()) {
            for (int i = 0; i < 150; i++) {
                particles.add(new KarmaParticle(
                        random.nextInt(this.width),
                        random.nextInt(this.height),
                        (random.nextFloat() - 0.5f) * 0.5f,
                        (random.nextFloat() - 0.5f) * 0.5f,
                        KARMA_COLORS[random.nextInt(KARMA_COLORS.length)]
                ));
            }
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // This will call our custom renderBackground below.
        super.render(guiGraphics, mouseX, mouseY, partialTicks);

        // Render the actual content of the screen on top.
        renderHolographicContent(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // First, draw a solid black background to make the colors pop.
        guiGraphics.fill(0, 0, this.width, this.height, 0xFF000000);

        // Update and render each karma particle
        updateAndRenderParticles(guiGraphics);
    }

    private void updateAndRenderParticles(GuiGraphics guiGraphics) {
        for (KarmaParticle particle : particles) {
            particle.update(this.width, this.height);
            particle.render(guiGraphics);
        }
    }

    protected abstract void renderHolographicContent(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks);

    protected void renderHolographicText(GuiGraphics guiGraphics, Component text, int x, int y) {
        guiGraphics.drawString(this.font, text, x, y, 0xFFFFFFFF);
    }

    protected void renderHolographicText(GuiGraphics guiGraphics, Component text, int x, int y, float flickerIntensity) {
        renderHolographicText(guiGraphics, text, x, y);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    // Inner class to manage each individual particle
    private static class KarmaParticle {
        private float x, y, vx, vy;
        private final int color;
        private int age;
        private final int maxAge;

        public KarmaParticle(float x, float y, float vx, float vy, int color) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.color = color;
            this.maxAge = 200 + new Random().nextInt(200); // Live for 10-20 seconds
            this.age = 0;
        }

        public void update(int screenWidth, int screenHeight) {
            this.x += this.vx;
            this.y += this.vy;
            this.age++;

            // If a particle goes off-screen or gets too old, reset it
            if (this.x < 0 || this.x > screenWidth || this.y < 0 || this.y > screenHeight || this.age > this.maxAge) {
                this.x = new Random().nextInt(screenWidth);
                this.y = new Random().nextInt(screenHeight);
                this.age = 0;
            }
        }

        public void render(GuiGraphics guiGraphics) {
            // Fade particles in and out based on their age for a twinkling effect
            float lifeRatio = (float) this.age / (float) this.maxAge;
            float alpha = (float) (Math.sin(lifeRatio * Math.PI) * 0.7f); // Multiplier makes them subtle

            int finalColor = ((int) (alpha * 255) << 24) | (this.color & 0x00FFFFFF);

            // Draw the particle as a 2x2 square
            guiGraphics.fill((int) this.x, (int) this.y, (int) this.x + 2, (int) this.y + 2, finalColor);
        }
    }
}
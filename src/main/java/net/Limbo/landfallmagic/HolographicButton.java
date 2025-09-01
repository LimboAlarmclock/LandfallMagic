package net.Limbo.landfallmagic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class HolographicButton extends Button {

    public HolographicButton(int x, int y, int width, int height, Component message, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int BORDER_COLOR = 0x40E0D0; // Cyan-turquoise color from your HolographicScreen

        // Button Background
        // Make it slightly transparent and pulse when hovered
        float alpha = this.isHoveredOrFocused() ? 0.7f + 0.1f * Mth.sin(System.currentTimeMillis() / 200f) : 0.4f;
        int backgroundColor = ((int) (alpha * 255) << 24) | BORDER_COLOR;
        guiGraphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, backgroundColor);

        // Button Border
        int borderColor = this.isHoveredOrFocused() ? 0xFFFFFFFF : BORDER_COLOR;
        guiGraphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + 1, borderColor); // Top
        guiGraphics.fill(this.getX(), this.getY(), this.getX() + 1, this.getY() + this.height, borderColor); // Left
        guiGraphics.fill(this.getX() + this.width - 1, this.getY(), this.getX() + this.width, this.getY() + this.height, borderColor); // Right
        guiGraphics.fill(this.getX(), this.getY() + this.height - 1, this.getX() + this.width, this.getY() + this.height, borderColor); // Bottom

        // Button Text
        int textColor = this.isHoveredOrFocused() ? 0xFFFFFF : 0xDDDDDD;
        guiGraphics.drawCenteredString(Minecraft.getInstance().font, this.getMessage(), this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, textColor);
    }
}
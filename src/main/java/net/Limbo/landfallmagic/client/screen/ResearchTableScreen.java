package net.Limbo.landfallmagic.client.screen;

import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.menu.ResearchTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ResearchTableScreen extends AbstractContainerScreen<ResearchTableMenu> {

    // This line tells the game exactly which texture to use
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "textures/gui/research_table_gui.png");

    public ResearchTableScreen(ResearchTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        // This method draws the texture file onto the screen
        guiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x404040, false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x404040, false);
        // Label for the first input slot
        guiGraphics.drawString(this.font, Component.literal("Component"), 26, 36, 0x404040, false);
        // Label for the second input slot
        guiGraphics.drawString(this.font, Component.literal("Component"), 80, 10, 0x404040, false);
        // Label for the output slot
        guiGraphics.drawString(this.font, Component.literal("Result"), 134, 36, 0x404040, false);
    }
}
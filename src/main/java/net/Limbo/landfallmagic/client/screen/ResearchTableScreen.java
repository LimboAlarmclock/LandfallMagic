package net.Limbo.landfallmagic.client.screen;

import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.menu.ResearchTableMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ResearchTableScreen extends AbstractContainerScreen<ResearchTableMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "textures/gui/research_table_gui.png");

    public ResearchTableScreen(ResearchTableMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;

        // Adjust label positions to match standard GUI
        this.inventoryLabelY = this.imageHeight - 94; // Standard position for player inventory label
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        // Debug GUI positioning
        landfallmagic.LOGGER.info("GUI initialized - leftPos: {}, topPos: {}, width: {}, height: {}",
                leftPos, topPos, imageWidth, imageHeight);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        // Render the background texture
        guiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        // Render the background first
        renderBackground(guiGraphics, mouseX, mouseY, delta);

        // Then render the container
        super.render(guiGraphics, mouseX, mouseY, delta);

        // Finally render tooltips on top
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        // Title of the GUI
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x404040, false);

        // Player inventory label
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x404040, false);

        // Labels for the slots - these should match your slot positions in the menu
        // Adjust these coordinates to match where your slots actually are
        guiGraphics.drawString(this.font, Component.literal("Form/Element"), 8, 35, 0x404040, false);
        guiGraphics.drawString(this.font, Component.literal("Form/Element"), 62, 9, 0x404040, false);
        guiGraphics.drawString(this.font, Component.literal("Result"), 116, 35, 0x404040, false);
    }
}
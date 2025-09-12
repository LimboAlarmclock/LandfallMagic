package net.Limbo.landfallmagic.client.screen;

import net.Limbo.landfallmagic.HolographicButton;
import net.Limbo.landfallmagic.karma.client.ClientKarmaManager;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.menu.GrimoireMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.ChunkPos;

public class GrimoireScreen extends AbstractContainerScreen<GrimoireMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "textures/gui/grimoire_gui.png");

    private enum Tab { SPELLS, RESEARCH, KARMA }
    private Tab currentTab = Tab.SPELLS;

    public GrimoireScreen(GrimoireMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 200;
        this.imageHeight = 216;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void init() {
        super.init();
        if (minecraft != null && minecraft.player != null) {
            ClientKarmaManager.requestKarmaDataAroundPlayer(2);
        }
        this.clearWidgets();
        int buttonY = this.topPos + 30;
        this.addRenderableWidget(new HolographicButton(this.leftPos + 10, buttonY, 55, 20, Component.literal("Spells"), (b) -> this.currentTab = Tab.SPELLS));
        this.addRenderableWidget(new HolographicButton(this.leftPos + 72, buttonY, 55, 20, Component.literal("Research"), (b) -> this.currentTab = Tab.RESEARCH));
        this.addRenderableWidget(new HolographicButton(this.leftPos + 135, buttonY, 55, 20, Component.literal("Karma"), (b) -> this.currentTab = Tab.KARMA));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        toggleSlots(this.currentTab == Tab.SPELLS);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderContentForTab(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void toggleSlots(boolean grimoireSlotsVisible) {
        for (Slot slot : this.menu.slots) {
            if (slot instanceof GrimoireMenu.HideableSpellSlot hideableSlot) {
                hideableSlot.visible = grimoireSlotsVisible;
            }
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    private void renderContentForTab(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int contentX = this.leftPos + 15;
        int contentY = this.topPos + 60;
        switch (currentTab) {
            case SPELLS -> {}
            case RESEARCH -> guiGraphics.drawCenteredString(this.font, "Research Page", this.width / 2, contentY, 0xFFFFFF);
            case KARMA -> renderKarmaPage(guiGraphics, contentX, contentY);
        }
    }

    private void renderKarmaPage(GuiGraphics guiGraphics, int x, int y) {
        if (this.minecraft == null || this.minecraft.player == null) return;
        ChunkPos playerChunk = new ChunkPos(this.minecraft.player.blockPosition());
        CompoundTag karmaData = ClientKarmaManager.getKarma(playerChunk);
        guiGraphics.drawString(this.font, "Karma: " + playerChunk, x, y, 0x40E0D0, true);
        y += 12;
        if (karmaData.isEmpty()) {
            guiGraphics.drawString(this.font, "No karma data...", x, y, 0xFF5555, false);
            return;
        }
        int col = 0; int startY = y;
        for (KarmaType type : KarmaType.values()) {
            String text = type.name() + ": " + karmaData.getInt(type.name().toLowerCase());
            guiGraphics.drawString(this.font, text, x + (col * 90), y, 0xFFFFFF, false);
            y += 10;
            if (y > startY + 40) { y = startY; col++; }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }
}
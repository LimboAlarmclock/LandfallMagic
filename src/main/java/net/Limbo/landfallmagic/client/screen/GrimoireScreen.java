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
        this.imageHeight = 182;
    }

    @Override
    protected void init() {
        super.init();
        if (minecraft != null && minecraft.player != null) {
            ClientKarmaManager.requestKarmaDataAroundPlayer(2);
        }

        this.clearWidgets();

        int buttonY = this.topPos + 30;
        this.addRenderableWidget(new HolographicButton(this.leftPos + 10, buttonY, 55, 20, Component.literal("Spells"), (button) -> this.currentTab = Tab.SPELLS));
        this.addRenderableWidget(new HolographicButton(this.leftPos + 72, buttonY, 55, 20, Component.literal("Research"), (button) -> this.currentTab = Tab.RESEARCH));
        this.addRenderableWidget(new HolographicButton(this.leftPos + 135, buttonY, 55, 20, Component.literal("Karma"), (button) -> this.currentTab = Tab.KARMA));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        // This is the key: we update the visibility flag before anything is rendered.
        toggleSlots(this.currentTab == Tab.SPELLS);

        // The default render method will now correctly skip drawing/interacting with inactive slots.
        super.render(guiGraphics, mouseX, mouseY, delta);

        renderContentForTab(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    private void toggleSlots(boolean grimoireSlotsVisible) {
        for (Slot slot : this.menu.slots) {
            // We check if the slot is our custom type
            if (slot instanceof GrimoireMenu.HideableSpellSlot hideableSlot) {
                // And set our custom flag, which controls isActive() and isEnabled()
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
            case SPELLS:
                break;
            case RESEARCH:
                guiGraphics.drawCenteredString(this.font, "Research Page", this.width / 2, contentY, 0xFFFFFF);
                break;
            case KARMA:
                renderKarmaPage(guiGraphics, contentX, contentY);
                break;
        }
    }

    private void renderKarmaPage(GuiGraphics guiGraphics, int x, int y) {
        if (this.minecraft == null || this.minecraft.player == null) return;

        ChunkPos playerChunk = new ChunkPos(this.minecraft.player.blockPosition());
        CompoundTag karmaData = ClientKarmaManager.getKarma(playerChunk);

        guiGraphics.drawString(this.font, Component.literal("Karma in current chunk (" + playerChunk.x + ", " + playerChunk.z + ")"), x, y, 0x40E0D0, true);
        y += 12;

        if (karmaData.isEmpty()) {
            guiGraphics.drawString(this.font, Component.literal("No karma data received..."), x, y, 0xFF5555, false);
            return;
        }

        int column = 0;
        int startY = y;
        for (KarmaType type : KarmaType.values()) {
            int value = karmaData.getInt(type.name().toLowerCase());
            String text = type.name() + ": " + value;
            guiGraphics.drawString(this.font, text, x + (column * 90), y, 0xFFFFFF, false);
            y += 10;
            if (y > startY + 40) {
                y = startY;
                column++;
            }
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // We leave this empty
    }
}
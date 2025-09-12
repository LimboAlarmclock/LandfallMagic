package net.Limbo.landfallmagic.client.screen;

import net.Limbo.landfallmagic.karma.client.ClientKarmaManager;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.magic.MagicSchool;
import net.Limbo.landfallmagic.menu.GrimoireMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.ChunkPos;
import org.lwjgl.glfw.GLFW;

public class GrimoireScreen extends AbstractContainerScreen<GrimoireMenu> {

    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(landfallmagic.MODID, "textures/gui/grimoire_gui.png");

    private enum Tab { SPELLS, RESEARCH, KARMA }
    private Tab currentTab = Tab.SPELLS;

    public GrimoireScreen(GrimoireMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 200;
        this.imageHeight = 240;
    }

    @Override
    protected void init() {
        super.init();
        if (minecraft != null && minecraft.player != null) {
            ClientKarmaManager.requestKarmaDataAroundPlayer(2);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // Don't render default labels, we'll do our own
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT) {
            int next = (currentTab.ordinal() + 1) % Tab.values().length;
            currentTab = Tab.values()[next];
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_LEFT) {
            int prev = (currentTab.ordinal() - 1 + Tab.values().length) % Tab.values().length;
            currentTab = Tab.values()[prev];
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
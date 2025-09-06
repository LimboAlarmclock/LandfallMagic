package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.karma.client.ClientKarmaManager;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ChunkPos;
import org.lwjgl.glfw.GLFW;

public class GrimoireScreen extends HolographicScreen {

    private static final Component GRIMOIRE_TITLE = Component.translatable("gui.landfallmagic.grimoire.title");
    private static final Component SPELLS_TAB = Component.translatable("gui.landfallmagic.grimoire.spells");
    private static final Component RESEARCH_TAB = Component.translatable("gui.landfallmagic.grimoire.research");
    private static final Component KARMA_TAB = Component.translatable("gui.landfallmagic.grimoire.karma");

    private enum Tab { SPELLS, RESEARCH, KARMA }
    private Tab currentTab = Tab.SPELLS;

    private final String[] knownSpells = {
            "Ignition Bolt", "Water Shield", "Earth Spike", "Wind Gust", "Shadow Veil"
    };

    private final String[] researchNotes = {
            "The karma flows in mysterious patterns...",
            "Elemental nodes seem to resonate with specific frequencies.",
            "Chaos and Order must be balanced for stable magic."
    };

    public GrimoireScreen() {
        super(GRIMOIRE_TITLE);
    }

    @Override
    protected void init() {
        super.init();
        // Request karma data when the screen is opened
        if (minecraft != null && minecraft.player != null) {
            ClientKarmaManager.requestCurrentChunkKarma();
            // Also request surrounding chunks for better coverage
            ClientKarmaManager.requestKarmaDataAroundPlayer(1);
        }
    }

    @Override
    protected void renderHolographicContent(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int centerX = this.width / 2;
        int topY = 40;

        renderHolographicText(guiGraphics, GRIMOIRE_TITLE, centerX - font.width(GRIMOIRE_TITLE) / 2, topY);

        renderTabIndicator(guiGraphics, centerX, topY + 20);

        int contentY = topY + 50;
        switch (currentTab) {
            case SPELLS -> renderSpellsContent(guiGraphics, centerX, contentY);
            case RESEARCH -> renderResearchContent(guiGraphics, centerX, contentY);
            case KARMA -> renderKarmaContent(guiGraphics, centerX, contentY);
        }
    }

    private void renderTabIndicator(GuiGraphics guiGraphics, int centerX, int y) {
        String tabs = "[ Spells | Research | Karma ]";
        renderHolographicText(guiGraphics, Component.literal(tabs), centerX - font.width(tabs) / 2, y);

        Component activeTab = switch(currentTab) {
            case SPELLS -> SPELLS_TAB;
            case RESEARCH -> RESEARCH_TAB;
            case KARMA -> KARMA_TAB;
        };
        int underlineWidth = font.width(activeTab) + 4;
        int underlineX = switch(currentTab) {
            case SPELLS -> centerX - font.width(tabs) / 2 + font.width("[ ") - 2;
            case RESEARCH -> centerX - font.width("Research | Karma ]") / 2 + font.width(" ") - 2;
            case KARMA -> centerX + font.width("[ Spells | Research ") / 2 - font.width("Karma") - 2;
        };
        guiGraphics.fill(underlineX, y + 10, underlineX + underlineWidth, y + 11, UI_COLOR);
    }

    private void renderSpellsContent(GuiGraphics guiGraphics, int centerX, int y) {
        for (int i = 0; i < knownSpells.length; i++) {
            Component spellName = Component.literal("• " + knownSpells[i]);
            renderHolographicText(guiGraphics, spellName, centerX - 100, y + (i * 12));
        }
    }

    private void renderResearchContent(GuiGraphics guiGraphics, int centerX, int y) {
        for (int i = 0; i < researchNotes.length; i++) {
            Component note = Component.literal("» " + researchNotes[i]);
            renderHolographicText(guiGraphics, note, centerX - 100, y + (i * 12));
        }
    }

    private void renderKarmaContent(GuiGraphics guiGraphics, int centerX, int y) {
        renderHolographicText(guiGraphics, Component.literal("Current Karma Readings:"), centerX - 100, y);
        renderHolographicText(guiGraphics, Component.literal("---------------------"), centerX - 100, y + 15);

        if (this.minecraft == null || this.minecraft.player == null) {
            return;
        }

        ChunkPos playerChunk = new ChunkPos(Minecraft.getInstance().player.blockPosition());

        if (ClientKarmaManager.hasKarmaData(playerChunk)) {
            CompoundTag karmaData = ClientKarmaManager.getKarma(playerChunk);

            // Calculate total karma and find dominant type
            int totalKarma = 0;
            KarmaType dominantType = KarmaType.FIRE; // Default
            int maxKarmaValue = 0;

            // First pass: calculate totals and find dominant type
            for (KarmaType type : KarmaType.values()) {
                int value = karmaData.getInt(type.name().toLowerCase());
                totalKarma += value;
                if (value > maxKarmaValue) {
                    maxKarmaValue = value;
                    dominantType = type;
                }
            }

            // Show summary info
            renderHolographicText(guiGraphics, Component.literal("Total Karma: " + totalKarma), centerX - 100, y + 30);
            renderHolographicText(guiGraphics, Component.literal("Dominant Type: " + dominantType.name()), centerX - 100, y + 42);
            renderHolographicText(guiGraphics, Component.literal(""), centerX - 100, y + 54); // Spacer

            // Show individual karma values
            int lineIndex = 0;
            for (KarmaType type : KarmaType.values()) {
                int value = karmaData.getInt(type.name().toLowerCase());
                String karmaString = type.name().substring(0, 1).toUpperCase() +
                        type.name().substring(1).toLowerCase() + ": " + value;

                // Add visual indicators for high karma values
                if (value > 500) {
                    karmaString += " ★"; // High karma indicator
                } else if (value > 100) {
                    karmaString += " •"; // Medium karma indicator
                }

                renderHolographicText(guiGraphics, Component.literal(karmaString), centerX - 100, y + 66 + (lineIndex * 12));
                lineIndex++;
            }

            // Add refresh indicator at the bottom
            renderHolographicText(guiGraphics, Component.literal(""), centerX - 100, y + 66 + (lineIndex * 12) + 10);
            renderHolographicText(guiGraphics, Component.literal("Press 'K' to refresh karma data"), centerX - 100, y + 66 + (lineIndex * 12) + 22);

        } else {
            renderHolographicText(guiGraphics, Component.literal("Requesting karma data from server..."), centerX - 100, y + 30);
            renderHolographicText(guiGraphics, Component.literal(""), centerX - 100, y + 42);
            renderHolographicText(guiGraphics, Component.literal("If this persists, try:"), centerX - 100, y + 54);
            renderHolographicText(guiGraphics, Component.literal("• Moving to a different chunk"), centerX - 100, y + 66);
            renderHolographicText(guiGraphics, Component.literal("• Pressing 'K' to manually refresh"), centerX - 100, y + 78);
            renderHolographicText(guiGraphics, Component.literal("• Placing a karma node nearby"), centerX - 100, y + 90);

            // Auto-request karma data every few seconds when we don't have it
            if (System.currentTimeMillis() % 3000 < 100) { // Every 3 seconds (roughly)
                ClientKarmaManager.requestCurrentChunkKarma();
            }
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        // Handle 'K' key to manually refresh karma data
        if (keyCode == GLFW.GLFW_KEY_K) {
            ClientKarmaManager.requestCurrentChunkKarma();
            ClientKarmaManager.requestKarmaDataAroundPlayer(2); // Request 5x5 area
            return true;
        }

        // Handle tab switching
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
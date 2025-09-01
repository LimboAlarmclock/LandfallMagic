package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.karma.client.KarmaData;
import net.Limbo.landfallmagic.karma.IKarma;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.ChunkPos;

public class ResearchTableScreen extends HolographicScreen {

    private static final Component RESEARCH_TITLE = Component.translatable("gui.landfallmagic.research_table.title");
    private static final Component CURRENT_RESEARCH = Component.translatable("gui.landfallmagic.research_table.current_research");

    private String currentResearchName = "Elemental Manipulation";
    private float researchProgress = 0.65f; // 65% complete

    public ResearchTableScreen() {
        super(RESEARCH_TITLE);
    }

    @Override
    protected void renderHolographicContent(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        int centerX = this.width / 2;
        int topY = 60;

        renderHolographicText(guiGraphics, RESEARCH_TITLE, centerX - this.font.width(RESEARCH_TITLE) / 2, topY);

        ChunkPos playerChunk = new ChunkPos(Minecraft.getInstance().player.blockPosition());
        IKarma karma = KarmaData.getKarma(playerChunk);

        int totalKarma = 0;
        String dominantType = "None";

        if (karma != null) {
            for (KarmaType type : KarmaType.values()) {
                totalKarma += karma.getKarma(type);
            }
            dominantType = karma.getTopKarmaType().name();
        }

        Component karmaText = Component.literal("Ambient Karma: " + totalKarma);
        renderHolographicText(guiGraphics, karmaText, centerX - this.font.width(karmaText) / 2, topY + 20);

        int separatorWidth = 200;
        guiGraphics.fill(centerX - separatorWidth / 2, topY + 35, centerX + separatorWidth / 2, topY + 36, UI_COLOR);

        if (currentResearchName != null) {
            renderHolographicText(guiGraphics, CURRENT_RESEARCH, centerX - 100, topY + 50);

            Component researchText = Component.literal(currentResearchName);
            renderHolographicText(guiGraphics, researchText, centerX - 100, topY + 62);

            Component progressText = Component.literal("Progress: " + (int) (researchProgress * 100) + "%");
            renderHolographicText(guiGraphics, progressText, centerX - 100, topY + 74);
        }

        renderHolographicText(guiGraphics, Component.literal("Karma Field Analysis:"), centerX - 100, topY + 100);
        renderHolographicText(guiGraphics, Component.literal("Sources Detected: 4"), centerX - 100, topY + 112);
        renderHolographicText(guiGraphics, Component.literal("Dominant Type: " + dominantType), centerX - 100, topY + 124);
    }
}
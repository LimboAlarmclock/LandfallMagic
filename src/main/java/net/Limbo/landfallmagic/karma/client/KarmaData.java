package net.Limbo.landfallmagic.karma.client;

import net.Limbo.landfallmagic.karma.IKarma;
import net.minecraft.world.level.ChunkPos;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public class KarmaData {
    private static final Map<ChunkPos, IKarma> clientKarmaMap = new HashMap<>();

    public static void setKarma(ChunkPos pos, IKarma karma) {
        clientKarmaMap.put(pos, karma);
    }

    @Nullable
    public static IKarma getKarma(ChunkPos pos) {
        return clientKarmaMap.get(pos);
    }
}
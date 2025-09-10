package net.Limbo.landfallmagic.worldgen;

import com.mojang.serialization.DynamicOps;
import net.Limbo.landfallmagic.karma.KarmaType;
import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;

import java.util.HashMap;
import java.util.Map;

public class KarmaNodePositions extends SavedData {

    private static final String FILE_ID = "karma_node_positions";
    private final Map<BlockPos, KarmaType> nodePositions = new HashMap<>();

    public Map<BlockPos, KarmaType> getNodePositions() {
        return nodePositions;
    }

    public void addPosition(BlockPos pos, KarmaType type) {
        this.nodePositions.put(pos, type);
        this.setDirty();
    }

    public static KarmaNodePositions get(ServerLevel level) {
        DimensionDataStorage storage = level.getDataStorage();
        return storage.computeIfAbsent(new Factory<>(KarmaNodePositions::new, KarmaNodePositions::load), FILE_ID);
    }

    public static KarmaNodePositions load(CompoundTag tag, HolderLookup.Provider provider) {
        KarmaNodePositions positions = new KarmaNodePositions();
        final DynamicOps<Tag> ops = provider.createSerializationContext(NbtOps.INSTANCE);
        ListTag listTag = tag.getList("nodes", 10); // NBT.TAG_COMPOUND

        for (Tag nbtBase : listTag) {
            CompoundTag nodeTag = (CompoundTag) nbtBase;
            // Decode the BlockPos from the "pos" tag
            BlockPos pos = BlockPos.CODEC.parse(ops, nodeTag.get("pos"))
                    .resultOrPartial(err -> landfallmagic.LOGGER.error("Failed to decode BlockPos: {}", err))
                    .orElse(null);

            // If the position is valid and a type is present, load it
            if (pos != null && nodeTag.contains("type", Tag.TAG_STRING)) {
                try {
                    KarmaType type = KarmaType.valueOf(nodeTag.getString("type"));
                    positions.nodePositions.put(pos, type);
                } catch (IllegalArgumentException e) {
                    landfallmagic.LOGGER.error("Failed to decode KarmaType from NBT: {}", nodeTag.getString("type"));
                }
            }
        }
        return positions;
    }

    @Override
    public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
        ListTag listTag = new ListTag();
        final DynamicOps<Tag> ops = provider.createSerializationContext(NbtOps.INSTANCE);

        for (Map.Entry<BlockPos, KarmaType> entry : this.nodePositions.entrySet()) {
            CompoundTag nodeTag = new CompoundTag();
            // Encode the BlockPos into a "pos" tag
            BlockPos.CODEC.encodeStart(ops, entry.getKey())
                    .resultOrPartial(err -> landfallmagic.LOGGER.error("Failed to encode BlockPos: {}", err))
                    .ifPresent(posTag -> nodeTag.put("pos", posTag));
            // Save the type as a string
            nodeTag.putString("type", entry.getValue().name());
            listTag.add(nodeTag);
        }
        tag.put("nodes", listTag);
        return tag;
    }
}
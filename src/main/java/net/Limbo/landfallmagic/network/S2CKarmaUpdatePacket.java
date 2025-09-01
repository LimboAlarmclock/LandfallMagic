package net.Limbo.landfallmagic.network;

import net.Limbo.landfallmagic.karma.client.ClientKarmaManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record S2CKarmaUpdatePacket(ChunkPos pos, CompoundTag karma) implements CustomPacketPayload {

    public static final Type<S2CKarmaUpdatePacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("landfallmagic", "karma_update"));

    // Custom StreamCodec for ChunkPos
    public static final StreamCodec<RegistryFriendlyByteBuf, ChunkPos> CHUNK_POS_CODEC = StreamCodec.of(
            (buf, chunkPos) -> {
                buf.writeInt(chunkPos.x);
                buf.writeInt(chunkPos.z);
            },
            (buf) -> new ChunkPos(buf.readInt(), buf.readInt())
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, S2CKarmaUpdatePacket> STREAM_CODEC =
            StreamCodec.composite(
                    CHUNK_POS_CODEC, S2CKarmaUpdatePacket::pos,
                    ByteBufCodecs.COMPOUND_TAG, S2CKarmaUpdatePacket::karma,
                    S2CKarmaUpdatePacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Handler {
        public static void handle(final S2CKarmaUpdatePacket packet, final IPayloadContext context) {
            context.enqueueWork(() -> {
                // Handle the packet on the client side
                ChunkPos pos = packet.pos();
                CompoundTag karmaData = packet.karma();

                // Update the client-side karma manager
                ClientKarmaManager.updateKarma(pos, karmaData);

                System.out.println("Received karma update for chunk " + pos + ": " + karmaData);
            });
        }
    }
}
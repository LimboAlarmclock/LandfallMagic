package net.Limbo.landfallmagic.network;

import net.Limbo.landfallmagic.karma.KarmaCapability;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record C2SKarmaRequestPacket(ChunkPos pos) implements CustomPacketPayload {

    public static final Type<C2SKarmaRequestPacket> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath("landfallmagic", "karma_request"));

    // Custom StreamCodec for ChunkPos (same as in S2CKarmaUpdatePacket)
    public static final StreamCodec<RegistryFriendlyByteBuf, ChunkPos> CHUNK_POS_CODEC = StreamCodec.of(
            (buf, chunkPos) -> {
                buf.writeInt(chunkPos.x);
                buf.writeInt(chunkPos.z);
            },
            (buf) -> new ChunkPos(buf.readInt(), buf.readInt())
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, C2SKarmaRequestPacket> STREAM_CODEC =
            StreamCodec.composite(
                    CHUNK_POS_CODEC, C2SKarmaRequestPacket::pos,
                    C2SKarmaRequestPacket::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static class Handler {
        public static void handle(final C2SKarmaRequestPacket packet, final IPayloadContext context) {
            context.enqueueWork(() -> {
                // This runs on the server side
                if (context.player() instanceof ServerPlayer serverPlayer) {
                    System.out.println("Server received karma request for chunk: " + packet.pos());
                    // Send the karma data for the requested chunk
                    KarmaCapability.sendKarmaToPlayer(serverPlayer, packet.pos());
                }
            });
        }
    }
}
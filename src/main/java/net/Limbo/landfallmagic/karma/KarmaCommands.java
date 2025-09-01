package net.Limbo.landfallmagic.karma;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.Limbo.landfallmagic.ModBlocks;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;

public class KarmaCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("chunkkarma")
                // ... (existing chunkkarma command)
        );

        dispatcher.register(Commands.literal("locatenode")
                .then(Commands.argument("type", StringArgumentType.word())
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            String typeStr = StringArgumentType.getString(ctx, "type").toUpperCase();
                            KarmaType type;
                            try {
                                type = KarmaType.valueOf(typeStr);
                            } catch (IllegalArgumentException e) {
                                ctx.getSource().sendFailure(Component.literal("Invalid node type: " + typeStr));
                                return 0;
                            }

                            Block targetNode = getNodeBlock(type);
                            if (targetNode == null) {
                                ctx.getSource().sendFailure(Component.literal("Could not find block for node type: " + typeStr));
                                return 0;
                            }

                            findNearestNode(player, targetNode);
                            return 1;
                        })
                )
        );
    }
    private static void findNearestNode(ServerPlayer player, Block targetNode) {
        new Thread(() -> {
            BlockPos playerPos = player.blockPosition();
            ServerLevel level = player.serverLevel();
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (int radius = 0; radius < 100; radius++) { // Search up to 100 chunks away
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (Math.abs(x) < radius && Math.abs(z) < radius) continue;

                        ChunkPos chunkPos = new ChunkPos(player.chunkPosition().x + x, player.chunkPosition().z + z);
                        if (!level.isLoaded(chunkPos.getWorldPosition())) {
                            continue;
                        }

                        for (int blockX = 0; blockX < 16; blockX++) {
                            for (int blockZ = 0; blockZ < 16; blockZ++) {
                                for (int blockY = level.getMinBuildHeight(); blockY < level.getMaxBuildHeight(); blockY++) {
                                    mutablePos.set(chunkPos.getMinBlockX() + blockX, blockY, chunkPos.getMinBlockZ() + blockZ);
                                    if (level.getBlockState(mutablePos).is(targetNode)) {
                                        player.sendSystemMessage(Component.literal("Found " + targetNode.getName().getString() + " at: " + mutablePos.getX() + ", " + mutablePos.getY() + ", " + mutablePos.getZ()));
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            player.sendSystemMessage(Component.literal("Could not find a " + targetNode.getName().getString() + " within 100 chunks."));
        }).start();
    }


    private static Block getNodeBlock(KarmaType type) {
        return switch (type) {
            case FIRE -> ModBlocks.FIRE_NODE.get();
            case WATER -> ModBlocks.WATER_NODE.get();
            case EARTH -> ModBlocks.EARTH_NODE.get();
            case AIR -> ModBlocks.AIR_NODE.get();
            case LIGHT -> ModBlocks.LIGHT_NODE.get();
            case DARK -> ModBlocks.DARK_NODE.get();
            case ORDER -> ModBlocks.ORDER_NODE.get();
            case CHAOS -> ModBlocks.CHAOS_NODE.get();
            case CREATION -> ModBlocks.CREATION_NODE.get();
            case DESTRUCTION -> ModBlocks.DESTRUCTION_NODE.get();
        };
    }
}
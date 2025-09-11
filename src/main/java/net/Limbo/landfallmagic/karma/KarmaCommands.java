package net.Limbo.landfallmagic.karma;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.Limbo.landfallmagic.ModBlocks;
import net.Limbo.landfallmagic.magic.MagicSchool;
import net.Limbo.landfallmagic.magic.PlayerMagicHelper;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.saveddata.SavedData;

public class KarmaCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        // Register both karma commands and magic commands
        registerKarmaCommands(dispatcher);
        registerMagicCommands(dispatcher);
    }

    private static void registerKarmaCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        // Fixed chunkkarma command - this was missing from your original
        dispatcher.register(Commands.literal("chunkkarma")
                .requires(source -> source.hasPermission(2)) // Require OP level 2
                .executes(ctx -> {
                    ServerPlayer player = ctx.getSource().getPlayerOrException();
                    ChunkPos chunkPos = new ChunkPos(player.blockPosition());

                    // Get karma data for current chunk
                    ServerLevel level = player.serverLevel();
                    KarmaCapability.KarmaSavedData karmaData = level.getDataStorage().computeIfAbsent(
                            new SavedData.Factory<>(
                                    KarmaCapability.KarmaSavedData::new,
                                    KarmaCapability.KarmaSavedData::load
                            ),
                            "landfallmagic_karma"
                    );

                    IKarma karma = karmaData.getKarma(chunkPos);

                    // Display karma values
                    ctx.getSource().sendSuccess(() -> Component.literal("Karma values for chunk " + chunkPos + ":"), false);
                    for (KarmaType type : KarmaType.values()) {
                        int value = karma.getKarma(type);
                        ctx.getSource().sendSuccess(() -> Component.literal("  " + type.name() + ": " + value), false);
                    }

                    return 1;
                })
                .then(Commands.literal("set")
                        .then(Commands.argument("type", StringArgumentType.word())
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0))
                                        .executes(ctx -> {
                                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                                            String typeStr = StringArgumentType.getString(ctx, "type").toUpperCase();
                                            int amount = IntegerArgumentType.getInteger(ctx, "amount");

                                            KarmaType type;
                                            try {
                                                type = KarmaType.valueOf(typeStr);
                                            } catch (IllegalArgumentException e) {
                                                ctx.getSource().sendFailure(Component.literal("Invalid karma type: " + typeStr));
                                                return 0;
                                            }

                                            ChunkPos chunkPos = new ChunkPos(player.blockPosition());
                                            ServerLevel level = player.serverLevel();

                                            KarmaCapability.KarmaSavedData karmaData = level.getDataStorage().computeIfAbsent(
                                                    new SavedData.Factory<>(
                                                            KarmaCapability.KarmaSavedData::new,
                                                            KarmaCapability.KarmaSavedData::load
                                                    ),
                                                    "landfallmagic_karma"
                                            );

                                            IKarma karma = karmaData.getKarma(chunkPos);
                                            karma.setKarma(type, amount);
                                            karmaData.markDirty();

                                            ctx.getSource().sendSuccess(() -> Component.literal("Set " + type.name() + " karma to " + amount + " in chunk " + chunkPos), true);

                                            // Send update to nearby players
                                            KarmaCapability.sendKarmaToPlayer(player, chunkPos);

                                            return 1;
                                        })
                                )
                        )
                )
                .then(Commands.literal("clear")
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            ChunkPos chunkPos = new ChunkPos(player.blockPosition());
                            ServerLevel level = player.serverLevel();

                            KarmaCapability.KarmaSavedData karmaData = level.getDataStorage().computeIfAbsent(
                                    new SavedData.Factory<>(
                                            KarmaCapability.KarmaSavedData::new,
                                            KarmaCapability.KarmaSavedData::load
                                    ),
                                    "landfallmagic_karma"
                            );

                            IKarma karma = karmaData.getKarma(chunkPos);
                            for (KarmaType type : KarmaType.values()) {
                                karma.setKarma(type, 0);
                            }
                            karmaData.markDirty();

                            ctx.getSource().sendSuccess(() -> Component.literal("Cleared all karma in chunk " + chunkPos), true);

                            // Send update to nearby players
                            KarmaCapability.sendKarmaToPlayer(player, chunkPos);

                            return 1;
                        })
                )
        );

        dispatcher.register(Commands.literal("locatenode")
                .requires(source -> source.hasPermission(2)) // Require OP level 2
                .then(Commands.argument("type", StringArgumentType.word())
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            String typeStr = StringArgumentType.getString(ctx, "type").toUpperCase();
                            KarmaType type;
                            try {
                                type = KarmaType.valueOf(typeStr);
                            } catch (IllegalArgumentException e) {
                                ctx.getSource().sendFailure(Component.literal("Invalid node type: " + typeStr + ". Valid types: " + String.join(", ", java.util.Arrays.stream(KarmaType.values()).map(Enum::name).toArray(String[]::new))));
                                return 0;
                            }

                            Block targetNode = getNodeBlock(type);
                            if (targetNode == null) {
                                ctx.getSource().sendFailure(Component.literal("Could not find block for node type: " + typeStr));
                                return 0;
                            }

                            ctx.getSource().sendSuccess(() -> Component.literal("Searching for " + type.name() + " node... This may take a moment."), false);
                            findNearestNode(player, targetNode, type);
                            return 1;
                        })
                )
        );
    }

    private static void registerMagicCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("magic")
                .requires(source -> source.hasPermission(2)) // OP level 2
                .then(Commands.literal("set")
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("school", StringArgumentType.string())
                                        .executes(KarmaCommands::setMagicSchool))))
                .then(Commands.literal("get")
                        .then(Commands.argument("player", EntityArgument.player())
                                .executes(KarmaCommands::getMagicSchool)))
        );
    }

    private static int setMagicSchool(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = EntityArgument.getPlayer(context, "player");
            String schoolName = StringArgumentType.getString(context, "school").toUpperCase();

            MagicSchool school;
            try {
                school = MagicSchool.valueOf(schoolName);
            } catch (IllegalArgumentException e) {
                context.getSource().sendFailure(Component.literal("Invalid magic school: " + schoolName));
                context.getSource().sendFailure(Component.literal("Valid schools: NONE, DRUIDIC, SORCERY, RITUALIST"));
                return 0;
            }

            PlayerMagicHelper.setPlayerMagicSchool(player, school);

            context.getSource().sendSuccess(() -> Component.literal(
                    "Set " + player.getName().getString() + "'s magic school to " + school.getSerializedName()
            ), true);

            return 1;
        } catch (Exception e) {
            context.getSource().sendFailure(Component.literal("Error setting magic school: " + e.getMessage()));
            return 0;
        }
    }

    private static int getMagicSchool(CommandContext<CommandSourceStack> context) {
        try {
            ServerPlayer player = EntityArgument.getPlayer(context, "player");
            MagicSchool school = PlayerMagicHelper.getPlayerMagicSchool(player);

            context.getSource().sendSuccess(() -> Component.literal(
                    player.getName().getString() + "'s magic school: " + school.getSerializedName()
            ), false);

            return 1;
        } catch (Exception e) {
            context.getSource().sendFailure(Component.literal("Error getting magic school: " + e.getMessage()));
            return 0;
        }
    }

    private static void findNearestNode(ServerPlayer player, Block targetNode, KarmaType type) {
        // Run search in a separate thread to avoid blocking the server
        new Thread(() -> {
            BlockPos playerPos = player.blockPosition();
            ServerLevel level = player.serverLevel();
            BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

            for (int radius = 1; radius <= 100; radius++) { // Search up to 100 chunks away
                // Search the perimeter of each radius
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        // Only check the perimeter (not already checked inner areas)
                        if (Math.abs(x) < radius && Math.abs(z) < radius) continue;

                        ChunkPos chunkPos = new ChunkPos(player.chunkPosition().x + x, player.chunkPosition().z + z);

                        // Force load the chunk temporarily if it's not loaded
                        if (!level.hasChunk(chunkPos.x, chunkPos.z)) {
                            continue; // Skip unloaded chunks for now
                        }

                        // Search the chunk
                        for (int blockX = 0; blockX < 16; blockX++) {
                            for (int blockZ = 0; blockZ < 16; blockZ++) {
                                for (int blockY = level.getMinBuildHeight(); blockY < level.getMaxBuildHeight(); blockY++) {
                                    mutablePos.set(chunkPos.getMinBlockX() + blockX, blockY, chunkPos.getMinBlockZ() + blockZ);
                                    if (level.getBlockState(mutablePos).is(targetNode)) {
                                        double distance = playerPos.distSqr(mutablePos);
                                        player.sendSystemMessage(Component.literal("Found " + type.name() + " node at: " +
                                                mutablePos.getX() + ", " + mutablePos.getY() + ", " + mutablePos.getZ() +
                                                " (Distance: " + String.format("%.1f", Math.sqrt(distance)) + " blocks)"));
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            player.sendSystemMessage(Component.literal("Could not find a " + type.name() + " node within 100 chunks. Try using creative mode to place some nodes for testing."));
        }, "KarmaNodeSearch-" + player.getName().getString()).start();
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
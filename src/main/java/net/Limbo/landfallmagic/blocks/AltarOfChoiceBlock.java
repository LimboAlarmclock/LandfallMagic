package net.Limbo.landfallmagic.blocks;

import net.Limbo.landfallmagic.magic.MagicSchool;
import net.Limbo.landfallmagic.magic.PlayerMagicHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.ChatFormatting;

public class AltarOfChoiceBlock extends Block {

    public AltarOfChoiceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                            Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        ServerPlayer serverPlayer = (ServerPlayer) player;

        // Check if player already has a school
        if (PlayerMagicHelper.hasMagicSchool(player)) {
            MagicSchool currentSchool = PlayerMagicHelper.getPlayerMagicSchool(player);
            player.sendSystemMessage(Component.literal("You have already chosen the path of ")
                    .append(Component.literal(currentSchool.getSerializedName())
                            .withStyle(Style.EMPTY.withColor(getSchoolColor(currentSchool))))
                    .append(Component.literal(" magic. Your destiny is sealed.")));

            level.playSound(null, pos, SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 1.0f, 1.0f);
            return InteractionResult.SUCCESS;
        }

        // Show school selection menu
        showSchoolSelectionMenu(serverPlayer, pos);
        return InteractionResult.SUCCESS;
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        ServerPlayer serverPlayer = (ServerPlayer) player;
        ItemStack heldItem = player.getItemInHand(hand);

        // Check if player already has a school
        if (PlayerMagicHelper.hasMagicSchool(player)) {
            MagicSchool currentSchool = PlayerMagicHelper.getPlayerMagicSchool(player);
            player.sendSystemMessage(Component.literal("You have already chosen the path of ")
                    .append(Component.literal(currentSchool.getSerializedName())
                            .withStyle(Style.EMPTY.withColor(getSchoolColor(currentSchool))))
                    .append(Component.literal(" magic.")));
            return InteractionResult.SUCCESS;
        }

        // Check for school-specific items to make a choice
        MagicSchool chosenSchool = getSchoolFromItem(heldItem);

        if (chosenSchool != MagicSchool.NONE) {
            // Player chose a school with an item
            chooseSchool(serverPlayer, chosenSchool, pos, level);

            // Consume the item
            if (!player.getAbilities().instabuild) {
                heldItem.shrink(1);
            }

            return InteractionResult.CONSUME;
        } else {
            // No valid item, show selection menu
            showSchoolSelectionMenu(serverPlayer, pos);
            return InteractionResult.SUCCESS;
        }
    }

    private void showSchoolSelectionMenu(ServerPlayer player, BlockPos pos) {
        player.sendSystemMessage(Component.literal("═══════════════════════════════════════")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));

        player.sendSystemMessage(Component.literal("        THE ALTAR OF CHOICE")
                .withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));

        player.sendSystemMessage(Component.literal("═══════════════════════════════════════")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));

        player.sendSystemMessage(Component.literal(""));

        player.sendSystemMessage(Component.literal("Choose your magical path by using one of these items:")
                .withStyle(ChatFormatting.WHITE));

        player.sendSystemMessage(Component.literal(""));

        // Druidic School
        player.sendSystemMessage(Component.literal("🌿 DRUIDIC MAGIC")
                .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        player.sendSystemMessage(Component.literal("   Use: ")
                .withStyle(ChatFormatting.GRAY)
                .append(Component.literal("Oak Sapling, Wheat Seeds, or Bone Meal")
                        .withStyle(ChatFormatting.GREEN)));
        player.sendSystemMessage(Component.literal("   Path of nature, growth, and harmony")
                .withStyle(ChatFormatting.DARK_GREEN));

        player.sendSystemMessage(Component.literal(""));

        // Sorcery School
        player.sendSystemMessage(Component.literal("⚡ SORCERY MAGIC")
                .withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD));
        player.sendSystemMessage(Component.literal("   Use: ")
                .withStyle(ChatFormatting.GRAY)
                .append(Component.literal("Lapis Lazuli, Redstone, or Glowstone Dust")
                        .withStyle(ChatFormatting.BLUE)));
        player.sendSystemMessage(Component.literal("   Path of raw power and arcane knowledge")
                .withStyle(ChatFormatting.DARK_BLUE));

        player.sendSystemMessage(Component.literal(""));

        // Ritualist School
        player.sendSystemMessage(Component.literal("🕯️ RITUALIST MAGIC")
                .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD));
        player.sendSystemMessage(Component.literal("   Use: ")
                .withStyle(ChatFormatting.GRAY)
                .append(Component.literal("Candle, Soul Sand, or Nether Wart")
                        .withStyle(ChatFormatting.DARK_PURPLE)));
        player.sendSystemMessage(Component.literal("   Path of ancient rites and forbidden knowledge")
                .withStyle(ChatFormatting.DARK_RED));

        player.sendSystemMessage(Component.literal(""));
        player.sendSystemMessage(Component.literal("═══════════════════════════════════════")
                .withStyle(ChatFormatting.GOLD));

        // Play mysterious sound
        player.level().playSound(null, pos, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5f, 1.5f);
    }

    private MagicSchool getSchoolFromItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return MagicSchool.NONE;
        }

        // Druidic items (nature-themed)
        if (stack.is(Items.OAK_SAPLING) || stack.is(Items.BIRCH_SAPLING) ||
                stack.is(Items.SPRUCE_SAPLING) || stack.is(Items.JUNGLE_SAPLING) ||
                stack.is(Items.WHEAT_SEEDS) || stack.is(Items.BEETROOT_SEEDS) ||
                stack.is(Items.PUMPKIN_SEEDS) || stack.is(Items.MELON_SEEDS) ||
                stack.is(Items.BONE_MEAL) || stack.is(Items.SWEET_BERRIES)) {
            return MagicSchool.DRUIDIC;
        }

        // Sorcery items (arcane/magical)
        if (stack.is(Items.LAPIS_LAZULI) || stack.is(Items.REDSTONE) ||
                stack.is(Items.GLOWSTONE_DUST) || stack.is(Items.BLAZE_POWDER) ||
                stack.is(Items.ENDER_PEARL) || stack.is(Items.EXPERIENCE_BOTTLE) ||
                stack.is(Items.ENCHANTED_BOOK)) {
            return MagicSchool.SORCERY;
        }

        // Ritualist items (dark/occult)
        if (stack.is(Items.CANDLE) || stack.is(Items.SOUL_SAND) ||
                stack.is(Items.NETHER_WART) || stack.is(Items.GHAST_TEAR) ||
                stack.is(Items.WITHER_SKELETON_SKULL) || stack.is(Items.SOUL_TORCH) ||
                stack.is(Items.BLACK_CANDLE) || stack.is(Items.CRYING_OBSIDIAN)) {
            return MagicSchool.RITUALIST;
        }

        return MagicSchool.NONE;
    }

    private void chooseSchool(ServerPlayer player, MagicSchool school, BlockPos pos, Level level) {
        PlayerMagicHelper.setPlayerMagicSchool(player, school);

        // Dramatic announcement
        player.sendSystemMessage(Component.literal(""));
        player.sendSystemMessage(Component.literal("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));

        player.sendSystemMessage(Component.literal("        THE CHOICE HAS BEEN MADE")
                .withStyle(ChatFormatting.LIGHT_PURPLE, ChatFormatting.BOLD));

        player.sendSystemMessage(Component.literal("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★")
                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));

        player.sendSystemMessage(Component.literal(""));

        String schoolMessage = getSchoolChoiceMessage(school);
        player.sendSystemMessage(Component.literal(schoolMessage)
                .withStyle(Style.EMPTY.withColor(getSchoolColor(school)).withBold(true)));

        player.sendSystemMessage(Component.literal(""));
        player.sendSystemMessage(Component.literal("Your magical journey begins now...")
                .withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));

        // Play appropriate sound and effects
        playSchoolChoiceEffects(level, pos, school, player);

        // Announce to nearby players
        announceChoice(level, pos, player, school);
    }

    private String getSchoolChoiceMessage(MagicSchool school) {
        return switch (school) {
            case DRUIDIC -> "🌿 You have chosen the path of DRUIDIC MAGIC!\n" +
                    "   Nature bends to your will. The forests whisper their secrets.";
            case SORCERY -> "⚡ You have chosen the path of SORCERY!\n" +
                    "   Raw magical power flows through your veins. Reality bends before you.";
            case RITUALIST -> "🕯️ You have chosen the path of RITUALIST MAGIC!\n" +
                    "   Ancient knowledge is yours. The old ways guide your power.";
            default -> "You have chosen... nothing? This shouldn't happen!";
        };
    }

    private ChatFormatting getSchoolColor(MagicSchool school) {
        return switch (school) {
            case DRUIDIC -> ChatFormatting.GREEN;
            case SORCERY -> ChatFormatting.BLUE;
            case RITUALIST -> ChatFormatting.DARK_PURPLE;
            default -> ChatFormatting.GRAY;
        };
    }

    private void playSchoolChoiceEffects(Level level, BlockPos pos, MagicSchool school, Player player) {
        switch (school) {
            case DRUIDIC -> {
                level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
                level.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0f, 0.8f);
            }
            case SORCERY -> {
                level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
                level.playSound(null, pos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 1.0f, 0.5f);
            }
            case RITUALIST -> {
                level.playSound(null, pos, SoundEvents.PORTAL_TRIGGER, SoundSource.BLOCKS, 1.0f, 0.8f);
                level.playSound(null, pos, SoundEvents.WARDEN_ROAR, SoundSource.BLOCKS, 1.0f, 1.2f);
            }
        }

        // Add some particle effects if you want (would need client-side code)
        // For now, just play a final confirmation sound
        level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    private void announceChoice(Level level, BlockPos pos, Player chooser, MagicSchool school) {
        // Announce to all players within 50 blocks
        level.players().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer &&
                    player.blockPosition().distSqr(pos) <= 2500) { // 50 blocks squared

                if (player != chooser) {
                    serverPlayer.sendSystemMessage(Component.literal("")
                            .append(Component.literal(chooser.getName().getString())
                                    .withStyle(ChatFormatting.YELLOW))
                            .append(Component.literal(" has chosen the path of "))
                            .append(Component.literal(school.getSerializedName() + " magic")
                                    .withStyle(Style.EMPTY.withColor(getSchoolColor(school))))
                            .append(Component.literal("!")));
                }
            }
        });
    }
}
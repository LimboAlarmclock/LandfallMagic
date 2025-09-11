package net.Limbo.landfallmagic.blocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import com.mojang.logging.LogUtils;
import net.Limbo.landfallmagic.magic.MagicSchool;
import net.Limbo.landfallmagic.magic.PlayerMagicHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.slf4j.Logger;

public class AltarOfChoiceBlock extends Block {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 14, 15);

    public AltarOfChoiceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    // THIS METHOD IS CALLED ONLY WHEN THE PLAYER IS HOLDING AN ITEM
    @Override
    public ItemInteractionResult useItemOn(ItemStack heldItem, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide() || hand != InteractionHand.MAIN_HAND) {
            return ItemInteractionResult.SUCCESS;
        }

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return ItemInteractionResult.FAIL;
        }

        if (PlayerMagicHelper.hasMagicSchool(serverPlayer)) {
            sendAlreadyChosenMessage(serverPlayer);
            level.playSound(null, pos, SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 1.0f, 1.0f);
            return ItemInteractionResult.SUCCESS;
        }

        // The held item is now a direct parameter of this method!
        MagicSchool chosenSchool = getSchoolFromItem(heldItem);

        if (chosenSchool != MagicSchool.NONE) {
            LOGGER.info("Player {} chose school {} with item {}", player.getName().getString(), chosenSchool.getSerializedName(), heldItem.getDisplayName().getString());
            chooseSchool(serverPlayer, chosenSchool, pos, level);

            if (!serverPlayer.getAbilities().instabuild) {
                heldItem.shrink(1);
            }
            return ItemInteractionResult.CONSUME;
        }

        showSchoolSelectionMenu(serverPlayer, pos);
        return ItemInteractionResult.SUCCESS;
    }


    // THIS NEW METHOD IS CALLED ONLY WHEN THE PLAYER'S HAND IS EMPTY
    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResult.FAIL;
        }

        // Check if player already has a school
        if (PlayerMagicHelper.hasMagicSchool(serverPlayer)) {
            sendAlreadyChosenMessage(serverPlayer);
            level.playSound(null, pos, SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 1.0f, 1.0f);
            return InteractionResult.SUCCESS;
        }

        // With an empty hand, always show the menu
        LOGGER.info("Player {} is viewing the Altar of Choice menu.", player.getName().getString());
        showSchoolSelectionMenu(serverPlayer, pos);
        return InteractionResult.SUCCESS;
    }

    // Helper method to avoid duplicating code
    private void sendAlreadyChosenMessage(ServerPlayer serverPlayer) {
        MagicSchool currentSchool = PlayerMagicHelper.getPlayerMagicSchool(serverPlayer);
        serverPlayer.sendSystemMessage(Component.literal("You have already chosen the path of ")
                .append(Component.literal(currentSchool.getSerializedName()).withStyle(getSchoolColor(currentSchool)))
                .append(Component.literal(" magic.")));
    }


    // --- ALL YOUR OTHER METHODS REMAIN THE SAME ---

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
        player.sendSystemMessage(Component.literal("🌿 DRUIDIC MAGIC")
                .withStyle(ChatFormatting.GREEN, ChatFormatting.BOLD));
        player.sendSystemMessage(Component.literal("   Use: ")
                .withStyle(ChatFormatting.GRAY)
                .append(Component.literal("Oak Sapling, Wheat Seeds, or Bone Meal")
                        .withStyle(ChatFormatting.GREEN)));
        player.sendSystemMessage(Component.literal("   Path of nature, growth, and harmony")
                .withStyle(ChatFormatting.DARK_GREEN));
        player.sendSystemMessage(Component.literal(""));
        player.sendSystemMessage(Component.literal("⚡ SORCERY MAGIC")
                .withStyle(ChatFormatting.BLUE, ChatFormatting.BOLD));
        player.sendSystemMessage(Component.literal("   Use: ")
                .withStyle(ChatFormatting.GRAY)
                .append(Component.literal("Lapis Lazuli, Redstone, or Glowstone Dust")
                        .withStyle(ChatFormatting.BLUE)));
        player.sendSystemMessage(Component.literal("   Path of raw power and arcane knowledge")
                .withStyle(ChatFormatting.DARK_BLUE));
        player.sendSystemMessage(Component.literal(""));
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
        player.level().playSound(null, pos, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5f, 1.5f);
    }

    private MagicSchool getSchoolFromItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return MagicSchool.NONE;
        }
        if (stack.is(Items.OAK_SAPLING) || stack.is(Items.BIRCH_SAPLING) ||
                stack.is(Items.SPRUCE_SAPLING) || stack.is(Items.JUNGLE_SAPLING) ||
                stack.is(Items.WHEAT_SEEDS) || stack.is(Items.BEETROOT_SEEDS) ||
                stack.is(Items.PUMPKIN_SEEDS) || stack.is(Items.MELON_SEEDS) ||
                stack.is(Items.BONE_MEAL) || stack.is(Items.SWEET_BERRIES)) {
            return MagicSchool.DRUIDIC;
        }
        if (stack.is(Items.LAPIS_LAZULI) || stack.is(Items.REDSTONE) ||
                stack.is(Items.GLOWSTONE_DUST) || stack.is(Items.BLAZE_POWDER) ||
                stack.is(Items.ENDER_PEARL) || stack.is(Items.EXPERIENCE_BOTTLE) ||
                stack.is(Items.ENCHANTED_BOOK)) {
            return MagicSchool.SORCERY;
        }
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
        playSchoolChoiceEffects(level, pos, school, player);
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
        level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 1.0f, 1.0f);
    }

    private void announceChoice(Level level, BlockPos pos, Player chooser, MagicSchool school) {
        level.players().forEach(player -> {
            if (player instanceof ServerPlayer serverPlayer &&
                    player.blockPosition().distSqr(pos) <= 2500) {
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
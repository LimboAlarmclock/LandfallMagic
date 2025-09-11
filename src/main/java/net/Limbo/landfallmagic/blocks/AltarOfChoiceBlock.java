package net.Limbo.landfallmagic.blocks;

import net.Limbo.landfallmagic.ModItems;
import net.Limbo.landfallmagic.magic.MagicSchool;
import net.Limbo.landfallmagic.magic.PlayerMagicProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;

public class AltarOfChoiceBlock extends Block {

    public AltarOfChoiceBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .strength(3.0F, 9.0F)
                .sound(SoundType.STONE)
                .requiresCorrectToolForDrops()
                .lightLevel(state -> 7));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        player.getCapability(PlayerMagicProvider.PLAYER_MAGIC).ifpresent(magic -> {
            if (magic.getMagicSchool() != MagicSchool.NONE) {
                player.sendSystemMessage(Component.literal("You have already chosen your path."));
                return;
            }

            ItemStack heldItem = player.getMainHandItem();
            MagicSchool schoolToSet = null;

            if (heldItem.is(ModItems.WOLF_FANG.get())) {
                schoolToSet = MagicSchool.DRUIDIC;
            } else if (heldItem.is(ModItems.ARCANE_DUST.get())) {
                schoolToSet = MagicSchool.SORCERY;
            } else if (heldItem.is(ModItems.BONE_SHARDS.get())) {
                schoolToSet = MagicSchool.RITUALIST;
            }

            if (schoolToSet != null) {
                magic.setMagicSchool(schoolToSet);
                player.sendSystemMessage(Component.literal("You have chosen the path of the " + schoolToSet.getSerializedName() + "."));
                level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 1.0F, 1.0F);

                // Consume the item
                if (!player.getAbilities().instabuild) {
                    heldItem.shrink(1);
                }
            } else {
                player.sendSystemMessage(Component.literal("The altar does not respond to this item."));
            }
        });

        return InteractionResult.SUCCESS;
    }
}
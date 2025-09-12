package net.Limbo.landfallmagic.blocks;

import com.mojang.serialization.MapCodec;
import net.Limbo.landfallmagic.entity.GrimoireBlockEntity;
import net.Limbo.landfallmagic.magic.PlayerMagicHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class GrimoireBookBlock extends BaseEntityBlock {

    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final MapCodec<GrimoireBookBlock> CODEC = simpleCodec(GrimoireBookBlock::new);

    public GrimoireBookBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
    }

    public GrimoireBookBlock() {
        this(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GRAY)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .noOcclusion());
    }

    // This method is called when the block is placed in the world.
    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!pLevel.isClientSide) {
            BlockEntity be = pLevel.getBlockEntity(pPos);
            if (be instanceof GrimoireBlockEntity grimoireBE) {
                // We load the inventory from the item stack that was used to place the block.
                grimoireBE.loadFromItem(pStack);
            }
        }
    }

    // This method is called to remove the block and its entity, dropping items.
    // We override it to ensure the block entity's data is saved before drops are calculated.
    @Override
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        BlockEntity blockentity = pLevel.getBlockEntity(pPos);
        if (blockentity instanceof GrimoireBlockEntity grimoireBE) {
            if (!pLevel.isClientSide && !pPlayer.isCreative()) {
                ItemStack itemstack = new ItemStack(this);
                grimoireBE.saveInventoryToItemStack(itemstack);
                ItemEntity itementity = new ItemEntity(pLevel, (double)pPos.getX() + 0.5, (double)pPos.getY() + 0.5, (double)pPos.getZ() + 0.5, itemstack);
                itementity.setDefaultPickUpDelay();
                pLevel.addFreshEntity(itementity);
            }
        }

        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPEN);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new GrimoireBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            boolean isOpen = pState.getValue(OPEN);
            if (isOpen) {
                BlockEntity entity = pLevel.getBlockEntity(pPos);
                if (entity instanceof GrimoireBlockEntity grimoireBlockEntity) {
                    ((ServerPlayer) pPlayer).openMenu(grimoireBlockEntity, buffer -> {
                        buffer.writeBlockPos(pPos);
                        buffer.writeEnum(PlayerMagicHelper.getPlayerMagicSchool(pPlayer));
                    });
                }
            } else {
                pLevel.setBlock(pPos, pState.setValue(OPEN, true), 3);
                pLevel.playSound(null, pPos, SoundEvents.BOOK_PAGE_TURN, SoundSource.BLOCKS, 0.7f, 1.0f);
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}
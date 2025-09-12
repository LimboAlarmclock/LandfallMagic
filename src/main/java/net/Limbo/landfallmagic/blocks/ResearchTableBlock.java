package net.Limbo.landfallmagic.blocks;

import com.mojang.serialization.MapCodec;
import net.Limbo.landfallmagic.ModBlockEntities;
import net.Limbo.landfallmagic.entity.ResearchTableBlockEntity;
import net.Limbo.landfallmagic.entity.TickingBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ResearchTableBlock extends BaseEntityBlock {

    private static final VoxelShape SHAPE = Shapes.or(
            Block.box(2, 0, -4, 14, 12, -2),   // northeastleg
            Block.box(12, 0, 18, 14, 12, 20), // southwestleg
            Block.box(2, 0, 18, 4, 12, 20),  // southeastleg
            Block.box(12, 0, -4, 14, 12, -2),   // Other northeastleg
            Block.box(2, 10, -2, 3, 12, 18),   // westrunnerboard
            Block.box(13, 10, -2, 14, 12, 18),  // eastrunnerboard
            Block.box(2, 12, -4, 14, 14, 20), // tabletop
            Block.box(4, 7, 19, 12, 12, 20), // southtableflap
            Block.box(4, 7, -4, 12, 12, -3)    // northtableflap
    );

    public ResearchTableBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(ResearchTableBlock::new);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ResearchTableBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof ResearchTableBlockEntity researchTableBE) {
                ((ServerPlayer)pPlayer).openMenu(researchTableBE, buffer -> buffer.writeBlockPos(pPos));
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        // This method should only be called when the block entity is created, not every tick!
        if (pLevel.isClientSide()) {
            return null;
        }

        // Use the standard createTickerHelper method - this is the correct way
        return createTickerHelper(pBlockEntityType, ModBlockEntities.RESEARCH_TABLE_BE.get(),
                (level, pos, state, blockEntity) -> blockEntity.tick());
    }
}
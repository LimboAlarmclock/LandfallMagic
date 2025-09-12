package net.Limbo.landfallmagic.blocks;

import com.mojang.serialization.MapCodec;
import net.Limbo.landfallmagic.entity.GrimoireBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class GrimoireBookBlock extends BaseEntityBlock {

    public static final BooleanProperty OPEN = BooleanProperty.create("open");
    public static final MapCodec<GrimoireBookBlock> CODEC = simpleCodec(GrimoireBookBlock::new);

    private static final VoxelShape CLOSED_SHAPE = Shapes.or(
            Block.box(1, 0, 4, 15, 1, 13),
            Block.box(1, 2, 4, 15, 3, 13),
            Block.box(2, 1, 4, 14, 2, 12),
            Block.box(1, 1, 3, 15, 2, 4)
    );

    private static final VoxelShape OPEN_SHAPE = Shapes.or(
            Block.box(1, 0, 8, 15, 1, 17),
            Block.box(1, 0, -1, 15, 1, 8),
            Block.box(2, 1, -2, 14, 1.25, 16),
            Block.box(1, 0, 6.5, 15, 1, 8.5)
    );

    public GrimoireBookBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
    }

    // Default constructor for the codec
    public GrimoireBookBlock() {
        this(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GRAY)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops()
                .noOcclusion());
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
                // If the book is open, open the menu
                BlockEntity entity = pLevel.getBlockEntity(pPos);
                if (entity instanceof GrimoireBlockEntity) {
                    pPlayer.openMenu((GrimoireBlockEntity)entity);
                }
            } else {
                // If the book is closed, just open it
                pLevel.setBlock(pPos, pState.setValue(OPEN, true), 3);
                pLevel.playSound(null, pPos, SoundEvents.BOOK_PAGE_TURN, SoundSource.BLOCKS, 0.7f, 1.0f);
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(OPEN) ? OPEN_SHAPE : CLOSED_SHAPE;
    }
}
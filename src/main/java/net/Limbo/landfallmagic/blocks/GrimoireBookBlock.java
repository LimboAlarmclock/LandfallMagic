package net.Limbo.landfallmagic.blocks;

import net.Limbo.landfallmagic.GrimoireScreen;
import net.Limbo.landfallmagic.karma.KarmaCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GrimoireBookBlock extends Block {

    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    private static final VoxelShape CLOSED_SHAPE = Shapes.or(
            Block.box(1, 0, 4, 15, 1, 13),    // Bottom cover
            Block.box(1, 2, 4, 15, 3, 13),    // Top cover
            Block.box(2, 1, 4, 14, 2, 12),    // Pages
            Block.box(1, 1, 3, 15, 2, 4)      // Spine
    );

    private static final VoxelShape OPEN_SHAPE = Shapes.or(
            Block.box(1, 0, 8, 15, 1, 17),     // Right page
            Block.box(1, 0, -1, 15, 1, 8),     // Left page
            Block.box(2, 1, -2, 14, 1.25, 16), // Pages content
            Block.box(1, 0, 6.5, 15, 1, 8.5)   // Center binding
    );

    public GrimoireBookBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_GRAY)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops()
                .noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(OPEN);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            boolean isOpen = state.getValue(OPEN);
            if (isOpen) {
                Minecraft.getInstance().setScreen(new GrimoireScreen());
                level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS,
                        0.5f, 1.0f + (level.random.nextFloat() - 0.5f) * 0.2f, false);
            } else {
                level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        SoundEvents.BOOK_PAGE_TURN, SoundSource.BLOCKS,
                        0.7f, 1.0f, false);
            }
        } else { // Server side
            if (player instanceof ServerPlayer serverPlayer) {
                ChunkPos chunkPos = new ChunkPos(player.blockPosition());
                KarmaCapability.sendKarmaToPlayer(serverPlayer, chunkPos);
            }
            level.setBlock(pos, state.cycle(OPEN), 3);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(OPEN) ? OPEN_SHAPE : CLOSED_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state, level, pos, context);
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return getShape(state, level, pos, CollisionContext.empty());
    }
}
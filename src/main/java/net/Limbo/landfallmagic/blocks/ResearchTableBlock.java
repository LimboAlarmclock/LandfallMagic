package net.Limbo.landfallmagic.blocks;

import net.Limbo.landfallmagic.ResearchTableScreen;
import net.Limbo.landfallmagic.karma.KarmaCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;

public class ResearchTableBlock extends Block {

    // Define the complex research table shape based on your model
    private static final VoxelShape RESEARCH_TABLE_SHAPE = Shapes.or(
            // Main tabletop (extends beyond normal block bounds)
            Block.box(-8, 12, 0, 24, 13, 16),

            // Table legs (front)
            Block.box(20, 0, 1, 22, 12, 2),   // Front right leg
            Block.box(22, 0, 1, 23, 12, 2),   // Front right support
            Block.box(22, 0, 2, 23, 12, 4),   // Front right corner

            // Table legs (back left)
            Block.box(-7, 0, 1, -6, 12, 2),   // Back left leg
            Block.box(-6, 0, 1, -4, 12, 2),   // Back left support
            Block.box(-7, 0, 2, -6, 12, 4),   // Back left corner

            // Table legs (back right)
            Block.box(-7, 0, 12, -6, 12, 14), // Back right corner posts
            Block.box(-7, 0, 14, -6, 12, 15), // Back right edge
            Block.box(-6, 0, 14, -4, 12, 15), // Back right support
            Block.box(21, 0, 12, 22, 12, 14), // Front right back corner
            Block.box(21, 0, 14, 22, 12, 15), // Front right back edge
            Block.box(19, 0, 14, 21, 12, 15) // Front right back support
    );

    public ResearchTableBlock() {
        super(BlockBehaviour.Properties.of()
                .mapColor(MapColor.COLOR_RED)
                .strength(3.0F, 6.0F)
                .sound(SoundType.WOOD)
                .requiresCorrectToolForDrops()
                .noOcclusion());
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            // Open the holographic research table GUI
            Minecraft.getInstance().setScreen(new ResearchTableScreen());
        } else { // On the server
            // Send karma data to the player before they open the screen
            if (player instanceof ServerPlayer serverPlayer) {
                ChunkPos chunkPos = new ChunkPos(player.blockPosition());
                KarmaCapability.sendKarmaToPlayer(serverPlayer, chunkPos);
            }
            // TODO: Add particle effects when opening
            spawnOpeningParticles(level, pos, player);

            return InteractionResult.SUCCESS;
        }

        // Server side - you might want to send data to client here
        // For now, just return success
        return InteractionResult.SUCCESS;
    }

    private void spawnOpeningParticles(Level level, BlockPos pos, Player player) {
        // Add some magical particle effects when the holographic interface opens
        // You can customize these based on your mod's particle system

        for (int i = 0; i < 20; i++) {
            double offsetX = (level.random.nextDouble() - 0.5) * 2.0;
            double offsetY = level.random.nextDouble() * 2.0;
            double offsetZ = (level.random.nextDouble() - 0.5) * 2.0;

            // TODO: Replace with your custom karma particles
            // level.addParticle(ModParticles.KARMA_PARTICLE.get(),
            //     pos.getX() + 0.5 + offsetX,
            //     pos.getY() + 1.0 + offsetY,
            //     pos.getZ() + 0.5 + offsetZ,
            //     0.0, 0.1, 0.0);
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return RESEARCH_TABLE_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // For collision, we might want a simpler shape to avoid getting stuck
        // You can return the full shape or create a simplified version
        return RESEARCH_TABLE_SHAPE;
    }

    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return RESEARCH_TABLE_SHAPE;
    }
}
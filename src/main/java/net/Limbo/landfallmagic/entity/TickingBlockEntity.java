package net.Limbo.landfallmagic.entity;

import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public interface TickingBlockEntity {
    void tick();

    static <T extends BlockEntity & TickingBlockEntity> BlockEntityTicker<T> tick(Level pLevel, BlockPos pPos, BlockState pState, T pBlockEntity) {
        if (!pLevel.isClientSide()) {
            // This method should only be called ONCE when the ticker is created, not every tick
            landfallmagic.LOGGER.info("Creating ticker for: {} at {}", pBlockEntity.getClass().getSimpleName(), pPos);

            // Return the actual ticker that will be called every tick
            return (level, pos, state, be) -> be.tick();
        }
        return null;
    }
}
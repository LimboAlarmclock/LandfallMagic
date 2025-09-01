package net.Limbo.landfallmagic.karma;

import net.Limbo.landfallmagic.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class KarmaNodeBlock extends Block {

    private final KarmaType karmaType;

    /**
     * A block that periodically spreads a specific type of Karma to its
     * surrounding chunks.
     *
     * @param type The KarmaType this node will spread.
     * @param properties The block properties.
     */
    public KarmaNodeBlock(KarmaType type, Properties properties) {
        super(properties);
        this.karmaType = type;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Get the karma amount from config at runtime to avoid initialization order issues
        int karmaAmount = Config.NODE_SPREAD_AMOUNT.get();

        // This is the corrected call, now passing the block's specific KarmaType and amount.
        KarmaAuraHandler.spreadKarma(level, pos, this.karmaType, karmaAmount);

        // Schedule the next tick using the delay from the config file.
        level.scheduleTick(pos, this, Config.NODE_TICK_DELAY.get());
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            // Kick off the ticking loop when the block is placed.
            // Uses the configured tick delay.
            level.scheduleTick(pos, this, Config.NODE_TICK_DELAY.get());
        }
    }
}

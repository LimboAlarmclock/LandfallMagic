package net.Limbo.landfallmagic.karma;

import net.Limbo.landfallmagic.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class KarmaNodeBlock extends Block {

    // 1. ADD THE NEW PROPERTY
    public static final BooleanProperty INITIALIZED = BooleanProperty.create("initialized");

    private final KarmaType karmaType;

    public KarmaNodeBlock(KarmaType type, Properties properties) {
        super(properties);
        this.karmaType = type;
        // 2. SET THE DEFAULT STATE FOR THE NEW PROPERTY
        this.registerDefaultState(this.stateDefinition.any().setValue(INITIALIZED, false));
    }

    // 3. REGISTER THE NEW PROPERTY SO THE GAME RECOGNIZES IT
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(INITIALIZED);
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int karmaAmount = Config.NODE_SPREAD_AMOUNT.get();
        KarmaAuraHandler.spreadKarma(level, pos, this.karmaType, karmaAmount);
        level.scheduleTick(pos, this, Config.NODE_TICK_DELAY.get());
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        super.onPlace(state, level, pos, oldState, isMoving);
        if (!level.isClientSide) {
            // When a player places it, we activate it and immediately mark it as initialized
            level.scheduleTick(pos, this, Config.NODE_TICK_DELAY.get());
            level.setBlock(pos, state.setValue(INITIALIZED, true), 3);
        }
    }
}
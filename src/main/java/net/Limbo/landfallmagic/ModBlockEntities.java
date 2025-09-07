package net.Limbo.landfallmagic;

import net.Limbo.landfallmagic.entity.KarmaCondenserBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE, landfallmagic.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<KarmaCondenserBlockEntity>> KARMA_CONDENSER_BE =
            BLOCK_ENTITIES.register("karma_condenser", () ->
                    BlockEntityType.Builder.of(KarmaCondenserBlockEntity::new,
                            ModBlocks.KARMA_CONDENSER.get()).build(null));
}
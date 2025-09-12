package net.Limbo.landfallmagic.entity;

import net.Limbo.landfallmagic.ModBlockEntities;
import net.Limbo.landfallmagic.magic.PlayerMagicHelper;
import net.Limbo.landfallmagic.menu.GrimoireMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class GrimoireBlockEntity extends BlockEntity implements MenuProvider {

    public GrimoireBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRIMOIRE_BE.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.landfallmagic.grimoire.title");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        // This is where the magic happens: we get the player's school and pass it to the menu
        return new GrimoireMenu(pContainerId, pPlayerInventory, PlayerMagicHelper.getPlayerMagicSchool(pPlayer));
    }
}
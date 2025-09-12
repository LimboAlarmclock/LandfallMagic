package net.Limbo.landfallmagic.entity;

import net.Limbo.landfallmagic.ModBlockEntities;
import net.Limbo.landfallmagic.datagen.ModDataComponents;
import net.Limbo.landfallmagic.magic.PlayerMagicHelper;
import net.Limbo.landfallmagic.menu.GrimoireMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List; // <-- THIS IMPORT WAS MISSING

public class GrimoireBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(15);

    public GrimoireBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRIMOIRE_BE.get(), pPos, pBlockState);
    }

    // This helper method saves the inventory to an item stack's components
    public void saveInventoryToItemStack(ItemStack stack) {
        NonNullList<ItemStack> items = NonNullList.withSize(this.itemHandler.getSlots(), ItemStack.EMPTY);
        for (int i = 0; i < this.itemHandler.getSlots(); i++) {
            items.set(i, this.itemHandler.getStackInSlot(i));
        }
        stack.set(ModDataComponents.GRIMOIRE_CONTENTS.get(), ItemContainerContents.fromItems(items));
    }

    // This helper method loads the inventory from an item stack's components
    public void loadFromItem(ItemStack stack) {
        ItemContainerContents contents = stack.get(ModDataComponents.GRIMOIRE_CONTENTS.get());
        if (contents != null) {
            List<ItemStack> items = contents.stream().toList();
            for (int i = 0; i < this.itemHandler.getSlots(); i++) {
                this.itemHandler.setStackInSlot(i, i < items.size() ? items.get(i) : ItemStack.EMPTY);
            }
        }
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.landfallmagic.grimoire.title");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new GrimoireMenu(pContainerId, pPlayerInventory, this, PlayerMagicHelper.getPlayerMagicSchool(pPlayer));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
    }

    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
    }
}
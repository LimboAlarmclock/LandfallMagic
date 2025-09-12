package net.Limbo.landfallmagic.menu;

import net.Limbo.landfallmagic.entity.GrimoireBlockEntity;
import net.Limbo.landfallmagic.item.SpellPageItem;
import net.Limbo.landfallmagic.magic.MagicSchool;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class GrimoireMenu extends AbstractContainerMenu {
    public final MagicSchool magicSchool;
    public final GrimoireBlockEntity blockEntity;
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int GRIMOIRE_SLOT_COUNT = 15;

    public GrimoireMenu(int pContainerId, Inventory inv, RegistryFriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), extraData.readEnum(MagicSchool.class));
    }

    public GrimoireMenu(int pContainerId, Inventory inv, BlockEntity entity, MagicSchool magicSchool) {
        super(ModMenuTypes.GRIMOIRE_MENU.get(), pContainerId);
        this.blockEntity = (GrimoireBlockEntity) entity;
        this.magicSchool = magicSchool;

        addPlayerHotbar(inv);
        addGrimoireSlots(this.blockEntity.itemHandler);
    }

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem() || !sourceSlot.isActive()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < HOTBAR_SLOT_COUNT) {
            if (!this.moveItemStackTo(sourceStack, HOTBAR_SLOT_COUNT, HOTBAR_SLOT_COUNT + GRIMOIRE_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < HOTBAR_SLOT_COUNT + GRIMOIRE_SLOT_COUNT) {
            if (!this.moveItemStackTo(sourceStack, 0, HOTBAR_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        int hotbarY = 158;
        int hotbarX = 20;
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, hotbarX + i * 18, hotbarY));
        }
    }

    private void addGrimoireSlots(IItemHandler itemHandler) {
        int grimoireX = 55;
        int grimoireY = 60;
        int slotIndex = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 5; col++) {
                this.addSlot(new HideableSpellSlot(itemHandler, slotIndex++, grimoireX + col * 18, grimoireY + row * 18));
            }
        }
    }

    // A custom slot that only accepts SpellPageItems and has a visibility flag
    public static class HideableSpellSlot extends SlotItemHandler {
        public boolean visible = true;

        public HideableSpellSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return stack.getItem() instanceof SpellPageItem;
        }

        @Override
        public boolean isActive() {
            return visible;
        }
    }
}
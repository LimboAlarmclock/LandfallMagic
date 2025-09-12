package net.Limbo.landfallmagic.menu;

import net.Limbo.landfallmagic.entity.ResearchTableBlockEntity;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class ResearchTableMenu extends AbstractContainerMenu {
    public final ResearchTableBlockEntity blockEntity;

    // This is the new client-side constructor
    public ResearchTableMenu(int pContainerId, Inventory inv, RegistryFriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public ResearchTableMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        super(ModMenuTypes.RESEARCH_TABLE_MENU.get(), pContainerId);
        this.blockEntity = (ResearchTableBlockEntity) entity;

        // Add the research table's slots
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 0, 26, 46)); // Input Slot 1
        this.addSlot(new SlotItemHandler(blockEntity.itemHandler, 1, 80, 20)); // Input Slot 2

        // Use the new ResultSlot for the output
        this.addSlot(new ResultSlot(blockEntity.itemHandler, 2, 134, 46)); // Output Slot

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    // --- Custom Result Slot ---
    public static class ResultSlot extends SlotItemHandler {
        public ResultSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            // Players can't place items into the result slot
            return false;
        }

        @Override
        public void onTake(Player thePlayer, ItemStack stack) {
            // When the result is taken, consume the ingredients from the input slots
            this.getItemHandler().extractItem(0, 1, false);
            this.getItemHandler().extractItem(1, 1, false);
            super.onTake(thePlayer, stack);
        }
    }


    // --- Helper methods and quickMoveStack ---
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = 0; // Block entity slots start at 0
    private static final int TE_INVENTORY_SLOT_COUNT = 3;

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // If the slot is the result slot (index 2)
        if (index == 2) {
            if (!this.moveItemStackTo(sourceStack, 3, 39, true)) { // Move to player inv
                return ItemStack.EMPTY;
            }
            sourceSlot.onQuickCraft(sourceStack, copyOfSourceStack);
        }
        // If moving from player inventory (index 3 to 38) to our inputs
        else if (index >= 3 && index < 39) {
            if (!this.moveItemStackTo(sourceStack, 0, 2, false)) { // Move to input slots 0 or 1
                return ItemStack.EMPTY;
            }
        }
        // If moving from our inputs (index 0 or 1) to player inventory
        else if (index < 3) {
            if (!this.moveItemStackTo(sourceStack, 3, 39, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (sourceStack.isEmpty()) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }

        if (sourceStack.getCount() == copyOfSourceStack.getCount()) {
            return ItemStack.EMPTY;
        }

        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
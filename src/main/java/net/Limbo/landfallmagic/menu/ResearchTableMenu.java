package net.Limbo.landfallmagic.menu;

import net.Limbo.landfallmagic.entity.ResearchTableBlockEntity;
import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
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

        // Debug the block entity
        landfallmagic.LOGGER.info("Menu created for block entity at: {}", blockEntity.getBlockPos());

        // Add the research table's slots with debug info
        Slot slot0 = new SlotItemHandler(blockEntity.itemHandler, 0, 26, 46);
        Slot slot1 = new SlotItemHandler(blockEntity.itemHandler, 1, 80, 20);
        Slot slot2 = new SlotItemHandler(blockEntity.itemHandler, 2, 134, 46);

        this.addSlot(slot0); // Catalyst Slot
        this.addSlot(slot1); // Main Input Slot
        this.addSlot(slot2); // Output Slot

        landfallmagic.LOGGER.info("Research table slots added at positions:");
        landfallmagic.LOGGER.info("  Slot 0: ({}, {})", slot0.x, slot0.y);
        landfallmagic.LOGGER.info("  Slot 1: ({}, {})", slot1.x, slot1.y);
        landfallmagic.LOGGER.info("  Slot 2: ({}, {})", slot2.x, slot2.y);

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    // --- Helper methods and quickMoveStack remain the same ---
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;
    private static final int TE_INVENTORY_SLOT_COUNT = 3;

    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            return ItemStack.EMPTY;
        }
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
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
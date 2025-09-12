package net.Limbo.landfallmagic.menu;

import net.Limbo.landfallmagic.magic.MagicSchool;
import net.Limbo.landfallmagic.magic.PlayerMagicHelper;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class GrimoireMenu extends AbstractContainerMenu {
    public final MagicSchool magicSchool;

    // Client-side constructor
    public GrimoireMenu(int pContainerId, Inventory inv, RegistryFriendlyByteBuf extraData) {
        this(pContainerId, inv, extraData.readEnum(MagicSchool.class));
    }

    // Server-side constructor
    public GrimoireMenu(int pContainerId, Inventory inv, MagicSchool magicSchool) {
        super(ModMenuTypes.GRIMOIRE_MENU.get(), pContainerId);
        this.magicSchool = magicSchool;

        // The Grimoire doesn't have its own inventory slots, but we still need the player's
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        // No items in the grimoire, so we don't need to handle shift-clicking
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true; // The Grimoire is always accessible
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 152 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 210));
        }
    }
}
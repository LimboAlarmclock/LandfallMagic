package net.Limbo.landfallmagic.entity;

import net.Limbo.landfallmagic.ModBlockEntities;
import net.Limbo.landfallmagic.ModItems;
import net.Limbo.landfallmagic.item.RuneItem;
import net.Limbo.landfallmagic.item.SpellPageItem;
import net.Limbo.landfallmagic.menu.ResearchTableMenu;
import net.Limbo.landfallmagic.spell.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ResearchTableBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(3);

    public ResearchTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RESEARCH_TABLE_BE.get(), pPos, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("gui.landfallmagic.research_table.title");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ResearchTableMenu(pContainerId, pPlayerInventory, this);
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

    public void tick() {
        if (level == null || level.isClientSide()) {
            return;
        }

        ItemStack input1 = itemHandler.getStackInSlot(0);
        ItemStack input2 = itemHandler.getStackInSlot(1);

        if (input1.isEmpty() || input2.isEmpty()) {
            setResult(ItemStack.EMPTY);
            return;
        }

        // Check for Tier 1 and Tier 2 recipes
        Optional<Spell> resultSpell = tryTier1Recipe(input1, input2)
                .or(() -> tryTier2Recipe(input1, input2));

        // Create the resulting ItemStack if a recipe was found
        ItemStack resultStack = resultSpell.map(spell -> {
            ItemStack stack = new ItemStack(ModItems.SPELL_PAGE.get());
            SpellPageItem.setSpell(stack, spell);
            return stack;
        }).orElse(ItemStack.EMPTY);

        setResult(resultStack);
    }

    private void setResult(ItemStack resultStack) {
        if (!ItemStack.isSameItemSameComponents(itemHandler.getStackInSlot(2), resultStack)) {
            itemHandler.setStackInSlot(2, resultStack);
            setChanged();
        }
    }

    private Optional<Spell> tryTier1Recipe(ItemStack input1, ItemStack input2) {
        if (!(input1.getItem() instanceof RuneItem) || !(input2.getItem() instanceof RuneItem)) {
            return Optional.empty();
        }

        RuneItem rune1 = (RuneItem) input1.getItem();
        RuneItem rune2 = (RuneItem) input2.getItem();

        SpellForm form = null;
        SpellElement element = null;

        if (rune1.getForm() != null && rune2.getElement() != null) {
            form = rune1.getForm();
            element = rune2.getElement();
        } else if (rune1.getElement() != null && rune2.getForm() != null) {
            form = rune2.getForm();
            element = rune1.getElement();
        }

        if (form != null && element != null) {
            return SpellRecipeRegistry.findTier1Recipe(form, element).map(Tier1SpellRecipe::getResult);
        }

        return Optional.empty();
    }

    private Optional<Spell> tryTier2Recipe(ItemStack input1, ItemStack input2) {
        ItemStack spellPageStack;
        ItemStack runeStack;

        if (input1.getItem() instanceof SpellPageItem && input2.getItem() instanceof RuneItem) {
            spellPageStack = input1;
            runeStack = input2;
        } else if (input2.getItem() instanceof SpellPageItem && input1.getItem() instanceof RuneItem) {
            spellPageStack = input2;
            runeStack = input1;
        } else {
            return Optional.empty();
        }

        Spell baseSpell = SpellPageItem.getSpell(spellPageStack);
        RuneItem augmentRune = (RuneItem) runeStack.getItem();

        if (baseSpell != null && augmentRune.getElement() != null) {
            return SpellRecipeRegistry.findTier2Recipe(baseSpell, augmentRune.getElement()).map(Tier2SpellRecipe::getResult);
        }

        return Optional.empty();
    }
}
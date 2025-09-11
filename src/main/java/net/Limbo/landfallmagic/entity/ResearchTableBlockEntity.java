package net.Limbo.landfallmagic.entity;

import net.Limbo.landfallmagic.ModBlockEntities;
import net.Limbo.landfallmagic.ModItems;
import net.Limbo.landfallmagic.item.RuneItem;
import net.Limbo.landfallmagic.item.SpellPageItem;
import net.Limbo.landfallmagic.landfallmagic;
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

public class ResearchTableBlockEntity extends BlockEntity implements MenuProvider, TickingBlockEntity {

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

    @Override
    public void tick() {
        if (level == null || level.isClientSide()) {
            return;
        }

        // DEBUG: Check if the tick method is running at all
        // You should see this message spamming your console. If not, the ticker isn't registered correctly.
        // landfallmagic.LOGGER.info("Research Table is ticking!");

        ItemStack input1 = itemHandler.getStackInSlot(0);
        ItemStack input2 = itemHandler.getStackInSlot(1);

        // No need to craft if inputs are empty
        if (input1.isEmpty() || input2.isEmpty()) {
            if (!itemHandler.getStackInSlot(2).isEmpty()) {
                itemHandler.setStackInSlot(2, ItemStack.EMPTY); // Clear output if inputs are removed
            }
            return;
        }

        // DEBUG: Log the items in the slots
        landfallmagic.LOGGER.info("Input 1: " + input1.getItem());
        landfallmagic.LOGGER.info("Input 2: " + input2.getItem());


        // --- Recipe Checking ---
        Optional<Spell> resultSpell = Optional.empty();

        // Check Tier 2 recipes first in both orientations
        resultSpell = findTier2Recipe(input1, input2).map(Tier2SpellRecipe::getResult);
        if (resultSpell.isEmpty()) {
            resultSpell = findTier2Recipe(input2, input1).map(Tier2SpellRecipe::getResult);
        }

        // If no Tier 2 recipe was found, check for Tier 1
        if (resultSpell.isEmpty()) {
            resultSpell = findTier1Recipe(input1, input2).map(Tier1SpellRecipe::getResult);
        }

        // --- Crafting ---
        if (resultSpell.isPresent()) {
            landfallmagic.LOGGER.info("Recipe found! Result: " + resultSpell.get().name);
            ItemStack resultStack = new ItemStack(ModItems.SPELL_PAGE.get());
            SpellPageItem.setSpell(resultStack, resultSpell.get());

            // Check if we already crafted this and it's sitting in the output
            if (!ItemStack.isSameItemSameComponents(itemHandler.getStackInSlot(2), resultStack)) {
                itemHandler.setStackInSlot(2, resultStack); // Set the output
                setChanged(); // Mark the block entity as dirty
            }
        } else {
            landfallmagic.LOGGER.info("No matching recipe found.");
            if (!itemHandler.getStackInSlot(2).isEmpty()) {
                itemHandler.setStackInSlot(2, ItemStack.EMPTY); // Clear output if recipe is no longer valid
            }
        }
    }

    private Optional<Tier1SpellRecipe> findTier1Recipe(ItemStack input1, ItemStack input2) {
        if (input1.getItem() instanceof RuneItem rune1 && input2.getItem() instanceof RuneItem rune2) {
            SpellForm form = (rune1.getForm() != null) ? rune1.getForm() : rune2.getForm();
            SpellElement element = (rune1.getElement() != null) ? rune1.getElement() : rune2.getElement();

            if (form != null && element != null) {
                landfallmagic.LOGGER.info("Checking Tier 1 Recipe for Form: " + form + ", Element: " + element);
                return SpellRecipeRegistry.findTier1Recipe(form, element);
            }
        }
        return Optional.empty();
    }

    private Optional<Tier2SpellRecipe> findTier2Recipe(ItemStack potentialSpellPage, ItemStack potentialAugment) {
        if (potentialSpellPage.getItem() instanceof SpellPageItem && potentialAugment.getItem() instanceof RuneItem augmentRune) {
            Spell baseSpell = SpellPageItem.getSpell(potentialSpellPage);
            SpellElement augment = augmentRune.getElement();

            if (baseSpell != null && augment != null) {
                landfallmagic.LOGGER.info("Checking Tier 2 Recipe for Base Spell: " + baseSpell.name + ", Augment: " + augment);
                return SpellRecipeRegistry.findTier2Recipe(baseSpell, augment);
            }
        }
        return Optional.empty();
    }
}
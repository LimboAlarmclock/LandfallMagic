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

public class ResearchTableBlockEntity extends BlockEntity implements MenuProvider {

    public final ItemStackHandler itemHandler = new ItemStackHandler(3);
    private int tickCounter = 0;

    public ResearchTableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.RESEARCH_TABLE_BE.get(), pPos, pBlockState);
        landfallmagic.LOGGER.info("ResearchTableBlockEntity created at: {}", pPos);
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

    // This is the method that will be called every tick by the ticker
    public void tick() {
        if (level == null || level.isClientSide()) {
            return;
        }

        tickCounter++;

        try {
            // Debug message every 20 ticks (1 second)
            if (tickCounter % 20 == 0) {
                landfallmagic.LOGGER.info("ResearchTableBlockEntity tick #{} at {}", tickCounter, worldPosition);
            }

            ItemStack input1 = itemHandler.getStackInSlot(0);
            ItemStack input2 = itemHandler.getStackInSlot(1);

            // Debug slot contents when items are present
            if (tickCounter % 20 == 0 && (!input1.isEmpty() || !input2.isEmpty())) {
                landfallmagic.LOGGER.info("=== RESEARCH TABLE STATUS ===");
                landfallmagic.LOGGER.info("Slot 0: {} ({})", input1.isEmpty() ? "EMPTY" : input1.getItem().toString(), input1.getCount());
                landfallmagic.LOGGER.info("Slot 1: {} ({})", input2.isEmpty() ? "EMPTY" : input2.getItem().toString(), input2.getCount());
                landfallmagic.LOGGER.info("Slot 2: {} ({})", itemHandler.getStackInSlot(2).isEmpty() ? "EMPTY" : itemHandler.getStackInSlot(2).getItem().toString(), itemHandler.getStackInSlot(2).getCount());
            }

            // Clear output if inputs are empty
            if (input1.isEmpty() || input2.isEmpty()) {
                if (!itemHandler.getStackInSlot(2).isEmpty()) {
                    landfallmagic.LOGGER.info("Clearing output - inputs are empty");
                    itemHandler.setStackInSlot(2, ItemStack.EMPTY);
                    setChanged();
                }
                return;
            }

            // Check if both inputs are runes
            if (!(input1.getItem() instanceof RuneItem) || !(input2.getItem() instanceof RuneItem)) {
                if (tickCounter % 20 == 0) {
                    landfallmagic.LOGGER.info("Skipping - not both RuneItems. Input1: {}, Input2: {}",
                            input1.getItem().getClass().getSimpleName(),
                            input2.getItem().getClass().getSimpleName());
                }
                return;
            }

            RuneItem rune1 = (RuneItem) input1.getItem();
            RuneItem rune2 = (RuneItem) input2.getItem();

            if (tickCounter % 20 == 0) {
                landfallmagic.LOGGER.info("Processing runes:");
                landfallmagic.LOGGER.info("  Rune1 - Form: {}, Element: {}", rune1.getForm(), rune1.getElement());
                landfallmagic.LOGGER.info("  Rune2 - Form: {}, Element: {}", rune2.getForm(), rune2.getElement());
            }

            // Find form and element from the two runes
            SpellForm form = null;
            SpellElement element = null;

            if (rune1.getForm() != null && rune2.getElement() != null) {
                form = rune1.getForm();
                element = rune2.getElement();
            } else if (rune1.getElement() != null && rune2.getForm() != null) {
                form = rune2.getForm();
                element = rune1.getElement();
            }

            if (form == null || element == null) {
                if (tickCounter % 20 == 0) {
                    landfallmagic.LOGGER.info("Invalid combination - need exactly one form and one element");
                    landfallmagic.LOGGER.info("  Combined - Form: {}, Element: {}", form, element);
                }
                return;
            }

            // Try to find a Tier 1 recipe - wrap in try-catch
            Optional<Tier1SpellRecipe> recipe = Optional.empty();
            try {
                landfallmagic.LOGGER.info("Attempting to find recipe for Form: {}, Element: {}", form, element);
                recipe = SpellRecipeRegistry.findTier1Recipe(form, element);
                landfallmagic.LOGGER.info("Recipe search completed. Found: {}", recipe.isPresent());
            } catch (Exception e) {
                landfallmagic.LOGGER.error("Error finding recipe: ", e);
                return;
            }

            if (recipe.isPresent()) {
                try {
                    Spell resultSpell = recipe.get().getResult();
                    landfallmagic.LOGGER.info("RECIPE FOUND: {} + {} = {}", form, element, resultSpell.name);

                    // Check if ModItems.SPELL_PAGE is not null
                    if (ModItems.SPELL_PAGE == null || ModItems.SPELL_PAGE.get() == null) {
                        landfallmagic.LOGGER.error("ModItems.SPELL_PAGE is null!");
                        return;
                    }

                    landfallmagic.LOGGER.info("Creating ItemStack with SPELL_PAGE item");
                    ItemStack resultStack = new ItemStack(ModItems.SPELL_PAGE.get());
                    landfallmagic.LOGGER.info("ItemStack created successfully: {}", resultStack);

                    landfallmagic.LOGGER.info("Setting spell on ItemStack: {}", resultSpell.name);
                    SpellPageItem.setSpell(resultStack, resultSpell);
                    landfallmagic.LOGGER.info("Spell set successfully");

                    // Verify the spell was set
                    Spell verifySpell = SpellPageItem.getSpell(resultStack);
                    landfallmagic.LOGGER.info("Spell verification: {}", verifySpell != null ? verifySpell.name : "NULL");

                    // Only update if the result has changed
                    if (!ItemStack.isSameItemSameComponents(itemHandler.getStackInSlot(2), resultStack)) {
                        landfallmagic.LOGGER.info("Placing result in output slot");
                        itemHandler.setStackInSlot(2, resultStack);
                        setChanged();
                        landfallmagic.LOGGER.info("Result placed successfully: {}", resultSpell.name);
                    } else {
                        landfallmagic.LOGGER.info("Result already in slot, no update needed");
                    }

                } catch (Exception e) {
                    landfallmagic.LOGGER.error("Error creating result item: ", e);
                    e.printStackTrace();
                }

            } else {
                if (tickCounter % 20 == 0) {
                    landfallmagic.LOGGER.info("NO RECIPE FOUND for Form: {}, Element: {}", form, element);
                }

                // Clear output if no valid recipe
                if (!itemHandler.getStackInSlot(2).isEmpty()) {
                    itemHandler.setStackInSlot(2, ItemStack.EMPTY);
                    setChanged();
                    if (tickCounter % 20 == 0) {
                        landfallmagic.LOGGER.info("Output slot cleared - no valid recipe");
                    }
                }
            }

        } catch (Exception e) {
            landfallmagic.LOGGER.error("Critical error in ResearchTableBlockEntity.tick(): ", e);
            e.printStackTrace();
        }
    }
}
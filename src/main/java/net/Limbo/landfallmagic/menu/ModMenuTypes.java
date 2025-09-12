package net.Limbo.landfallmagic.menu;

import net.Limbo.landfallmagic.landfallmagic;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, landfallmagic.MODID);

    public static final DeferredHolder<MenuType<?>, MenuType<ResearchTableMenu>> RESEARCH_TABLE_MENU =
            MENUS.register("research_table_menu",
                    // Use a direct method reference now that the constructor signature matches
                    () -> IMenuTypeExtension.create(ResearchTableMenu::new));

    public static final DeferredHolder<MenuType<?>, MenuType<GrimoireMenu>> GRIMOIRE_MENU =
            MENUS.register("grimoire_menu",
                    () -> IMenuTypeExtension.create(GrimoireMenu::new));
}
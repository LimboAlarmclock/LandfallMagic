package net.Limbo.landfallmagic.datagen;

import net.Limbo.landfallmagic.landfallmagic;
import net.Limbo.landfallmagic.worldgen.BiomeModifications;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = landfallmagic.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        RegistrySetBuilder builder = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, BiomeModifications::bootstrapConfiguredFeatures)
                .add(Registries.PLACED_FEATURE, BiomeModifications::bootstrapPlacedFeatures)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BiomeModifications::bootstrapBiomeModifiers)
                .add(Registries.ARMOR_MATERIAL, ModDataGeneration::bootstrap); // <-- ADD THIS LINE

        generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
                packOutput, lookupProvider, builder, Set.of(landfallmagic.MODID)));
    }
}
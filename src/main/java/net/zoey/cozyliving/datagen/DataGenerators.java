package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.data.event.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;
import net.zoey.cozyliving.*;

import java.util.concurrent.*;

@Mod.EventBusSubscriber(modid = CozyLiving.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var packOutput = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(
            event.includeServer(),
            ModLootTableProvider.create(packOutput)
        );
        var blockTagsProvider = generator.addProvider(
            event.includeServer(),
            new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper)
        );
        generator.addProvider(
            event.includeServer(), new ModItemTagsProvider(
                packOutput,
                lookupProvider,
                blockTagsProvider.contentsGetter(),
                existingFileHelper
            )
        );

        generator.addProvider(
                event.includeServer(), new ModBiomeTagsProvider(
                        packOutput,
                        lookupProvider,
                        existingFileHelper
                )
        );

        generator.addProvider(
            event.includeClient(),
            new ModBlockStateProvider(packOutput, existingFileHelper)
        );
        generator.addProvider(
            event.includeClient(),
            new ModItemModelProvider(packOutput, existingFileHelper)
        );
        generator.addProvider(
            event.includeServer(),
            new ModWorldGenProvider(packOutput, lookupProvider));
    }
}

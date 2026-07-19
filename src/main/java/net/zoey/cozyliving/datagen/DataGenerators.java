package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.damagesource.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.data.event.*;
import net.neoforged.fml.common.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.datagen.create.*;
import net.zoey.cozyliving.level.gen.*;

import java.util.*;

@EventBusSubscriber(modid = CozyLiving.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var packOutput = generator.getPackOutput();
        var existingFileHelper = event.getExistingFileHelper();
        var lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));
        generator.addProvider(
            event.includeServer(),
            new HauntingRecipeProvider(packOutput, lookupProvider)
        );
        generator.addProvider(
            event.includeServer(),
            new MillingRecipeProvider(packOutput, lookupProvider)
        );
        generator.addProvider(
            event.includeServer(),
            new CrushingRecipeProvider(packOutput, lookupProvider)
        );
        generator.addProvider(
            event.includeServer(),
            new ModLootTableProvider(packOutput, lookupProvider)
        );
        var blockTagsProvider = generator.addProvider(
            event.includeServer(),
            new ModBlockTagsProvider(packOutput, lookupProvider, existingFileHelper)
        );
        generator.addProvider(
            event.includeServer(),
            new ModItemTagsProvider(
                packOutput,
                lookupProvider,
                blockTagsProvider.contentsGetter(),
                existingFileHelper
            )
        );

        generator.addProvider(
            event.includeServer(),
            new ModBiomeTagsProvider(packOutput, lookupProvider, existingFileHelper)
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
            new ModDataMapProvider(packOutput, lookupProvider)
        );
        generator.addProvider(
            event.includeServer(),
            new ModGlobalLootModifiersProvider(packOutput, lookupProvider)
        );
        generator.addProvider(
            event.includeServer(),
            new ModDatapackBuiltinEntriesProvider(packOutput, lookupProvider)
        );
    }
}

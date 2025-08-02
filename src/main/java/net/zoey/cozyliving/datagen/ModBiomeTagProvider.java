package net.zoey.cozyliving.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import net.zoey.cozyliving.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends FabricTagProvider<Biome> {

    public ModBiomeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.BIOME, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Biomes.HAS_COTTON_SHRUB_PATCHES)
                //.addTag(BiomeTags.IS_JUNGLE)
                .forceAddTag(BiomeTags.IS_SAVANNA)
                .forceAddTag(BiomeTags.IS_JUNGLE);

        getOrCreateTagBuilder(ModTags.Biomes.HAS_RASPBERRY_PATCHES)
                .forceAddTag(BiomeTags.IS_TAIGA)
                .forceAddTag(BiomeTags.IS_FOREST)
                .add(BiomeKeys.PLAINS);
    }
}

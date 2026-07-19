package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.tags.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.level.biome.*;
import net.neoforged.neoforge.common.data.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(
        PackOutput packOutput,
        CompletableFuture<HolderLookup.Provider> holderLookup,
        @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(packOutput, holderLookup, CozyLiving.MODID, existingFileHelper);
    }

    protected void addTags(@NotNull HolderLookup.Provider holderLookup) {
        tag(ModTags.Biomes.HAS_RASPBERRY_PATCHES.get())
            .addTag(BiomeTags.IS_FOREST)
            .addTag(BiomeTags.IS_TAIGA)
            .add(Biomes.PLAINS)
            .add(Biomes.MEADOW);
        tag(ModTags.Biomes.HAS_COTTON_SHRUB_PATCHES.get())
            .addTag(BiomeTags.IS_SAVANNA)
            .addTag(BiomeTags.IS_JUNGLE);
    }
}
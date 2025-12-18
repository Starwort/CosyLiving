package net.zoey.cozyliving.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.content.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {

    public ModBiomeTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, CozyLiving.MODID, existingFileHelper);

        }
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(ModTags.Biomes.HAS_RASPBERRY_PATCHES.get())
                .addTag(BiomeTags.IS_FOREST)
                .addTag(BiomeTags.IS_TAIGA)
                .add(Biomes.PLAINS)
                .add(Biomes.MEADOW);
        //TODO: see how to add modded tags here like terralith:highlands
        tag(ModTags.Biomes.HAS_COTTON_SHRUB_PATCHES.get())
                .addTag(BiomeTags.IS_SAVANNA)
                .addTag(BiomeTags.IS_JUNGLE);
                //.add(Biomes.LUSH_CAVES);
    }
}
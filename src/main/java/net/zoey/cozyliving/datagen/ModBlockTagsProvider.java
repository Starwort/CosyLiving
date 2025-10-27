package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.tags.*;
import net.minecraftforge.common.data.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(
        PackOutput output,
        CompletableFuture<HolderLookup.Provider> lookupProvider,
        @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, CozyLiving.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.LOGS_THAT_BURN)
            .add(ModBlocks.COCONUT_LOG.get())
            .add(ModBlocks.COCONUT_WOOD.get())
            .add(ModBlocks.STRIPPED_COCONUT_LOG.get())
            .add(ModBlocks.STRIPPED_COCONUT_WOOD.get());
        tag(BlockTags.PLANKS).add(ModBlocks.COCONUT_PLANKS.get());
    }
}

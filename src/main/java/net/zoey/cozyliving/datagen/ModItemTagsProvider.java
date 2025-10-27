package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.data.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(
        PackOutput output,
        CompletableFuture<HolderLookup.Provider> holderLookup,
        CompletableFuture<TagLookup<Block>> tagLookup,
        @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(output, holderLookup, tagLookup, CozyLiving.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(ItemTags.LOGS_THAT_BURN)
            .add(ModBlocks.COCONUT_LOG.get().asItem())
            .add(ModBlocks.COCONUT_WOOD.get().asItem())
            .add(ModBlocks.STRIPPED_COCONUT_LOG.get().asItem())
            .add(ModBlocks.STRIPPED_COCONUT_WOOD.get().asItem());

        tag(ItemTags.PLANKS).add(ModBlocks.COCONUT_PLANKS.get().asItem());
    }
}

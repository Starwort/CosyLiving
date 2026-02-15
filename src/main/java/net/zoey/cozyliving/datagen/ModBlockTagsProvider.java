package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.Item;
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
        tag(BlockTags.BEACON_BASE_BLOCKS).add(ModBlocks.BENITOITE_BLOCK.block(), ModBlocks.RASPBERRY_RHODOLITE_BLOCK.block());
        tag(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.COCONUT_HANGING_SIGN.block());
        tag(BlockTags.CROPS).add(ModBlocks.RASPBERRY_BUSH.block(), ModBlocks.COTTON_CROP.block(), ModBlocks.COCONUT_PLANT.block());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.COCONUT_FENCE_GATE.block());
        tag(BlockTags.FLOWER_POTS).add(ModBlocks.POTTED_COTTON.block(),ModBlocks.POTTED_COCONUT_SAPLING.block());
        tag(BlockTags.LEAVES).add(ModBlocks.COCONUT_LEAVES.block(),ModBlocks.COCONUT_LEAVES_CORNER.block());
        tag(BlockTags.LOGS_THAT_BURN).addTag(ModTags.Blocks.COCONUT_LOGS.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BENITOITE_BLOCK.block())
                .add(ModBlocks.BENITOITE_ORE.block())
                .add(ModBlocks.DEEPSLATE_BENITOITE_ORE.block())
                .add(ModBlocks.RASPBERRY_RHODOLITE_BLOCK.block())
                .add(ModBlocks.RASPBERRY_RHODOLITE_ORE.block())
                .add(ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE.block());

        tag(BlockTags.OCCLUDES_VIBRATION_SIGNALS).add(ModBlocks.COTTON_BALE.block());
        tag(BlockTags.PLANKS).add(ModBlocks.COCONUT_PLANKS.block());
        tag(BlockTags.SAPLINGS).add(ModBlocks.COCONUT_SAPLING.block());
        tag(BlockTags.STANDING_SIGNS).add(ModBlocks.COCONUT_SIGN.block());
        tag(BlockTags.WALL_HANGING_SIGNS).add(ModBlocks.COCONUT_WALL_HANGING_SIGN.block());
        tag(BlockTags.WALL_SIGNS).add(ModBlocks.COCONUT_WALL_SIGN.block());
        tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.COCONUT_BUTTON.block());
        tag(BlockTags.WOODEN_DOORS).add(ModBlocks.COCONUT_DOOR.block());
        tag(BlockTags.WOODEN_FENCES).add(ModBlocks.COCONUT_FENCE.block());
        tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.COCONUT_PRESSURE_PLATE.block());
        tag(BlockTags.WOODEN_SLABS).add(ModBlocks.COCONUT_SLAB.block());
        tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.COCONUT_STAIRS.block());
        tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.COCONUT_TRAPDOOR.block());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(ModTags.Blocks.COCONUT_LOGS.get())
                .add(ModBlocks.COCONUT_PLANKS.block())
                .add(ModBlocks.COCONUT_SAPLING.block())
                .add(ModBlocks.COCONUT_HANGING_SIGN.block())
                .add(ModBlocks.COCONUT_SIGN.block())
                .add(ModBlocks.COCONUT_WALL_HANGING_SIGN.block())
                .add(ModBlocks.COCONUT_WALL_SIGN.block())
                .add(ModBlocks.COCONUT_BUTTON.block())
                .add(ModBlocks.COCONUT_DOOR.block())
                .add(ModBlocks.COCONUT_FENCE.block())
                .add(ModBlocks.COCONUT_PRESSURE_PLATE.block())
                .add(ModBlocks.COCONUT_SLAB.block())
                .add(ModBlocks.COCONUT_STAIRS.block())
                .add(ModBlocks.COCONUT_TRAPDOOR.block())
                .add(ModBlocks.RASPBERRY_CRATE.block())
                .add(ModBlocks.RASPBERRY_BUSH.block())
                .add(ModBlocks.COCONUT.block())
                .add(ModBlocks.COCONUT_CRATE.block());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.COCONUT_LEAVES.block())
                .add(ModBlocks.COCONUT_LEAVES_CORNER.block())
                .add(ModBlocks.COTTON_BALE.block())
                .add(ModBlocks.RASPBERRY_CRATE.block())
                .add(ModBlocks.COCONUT_CRATE.block());

        tag(ModTags.Blocks.COCONUT_LOGS.get())
                .add(ModBlocks.COCONUT_LOG.block())
                .add(ModBlocks.COCONUT_WOOD.block())
                .add(ModBlocks.STRIPPED_COCONUT_LOG.block())
                .add(ModBlocks.STRIPPED_COCONUT_WOOD.block());
    }



}

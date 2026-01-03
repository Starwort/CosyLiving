package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.*;
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
        tag(ModTags.Items.COCONUT_LOGS.get())
            .add(ModBlocks.COCONUT_LOG.asItem())
            .add(ModBlocks.COCONUT_WOOD.asItem())
            .add(ModBlocks.STRIPPED_COCONUT_LOG.asItem())
            .add(ModBlocks.STRIPPED_COCONUT_WOOD.asItem());

        tag(ModTags.Items.BREWING_STAND_INGREDIENT_USEABLE.get())
            .add(ModBlocks.RASPBERRY_BUSH.asItem())
            .add(ModItems.CINNAMON_STICK.item())
            .add(ModItems.GILDED_CINNAMON_STICK.item())
            .add(Items.COCOA_BEANS);

        tag(ModTags.Items.BREWING_STAND_INPUT_USEABLE.get())
            .add(ModItems.Food.COCONUT_MILK.item())
            .add(ModItems.Food.HEAVY_CREAM.item())
            .add(ModItems.Food.RASPBERRY_TEA.item())
            .add(ModItems.Food.HERBAL_TEA.item())
            .add(ModItems.Food.HOT_CHOCOLATE.item());

        tag(ModTags.Items.JAMS.get())
            .add(ModItems.Food.RASPBERRY_JAM.item())
            .add(ModItems.Food.SWEETBERRY_JAM.item())
            .add(ModItems.Food.APPLE_JAM.item())
            .add(ModItems.Food.GLOWBERRY_JAM.item());

        tag(ModTags.Items.COZYLIVING_STARTER_ITEMS.get())
            .add(ModBlocks.RASPBERRY_BUSH.asItem())
            .add(ModBlocks.COTTON_CROP.asItem())
            .add(ModBlocks.COTTON_SHRUB.asItem())
            .add(ModItems.CINNAMON_STICK.item())
            .add(ModItems.GILDED_CINNAMON_STICK.item())
            .add(ModBlocks.COCONUT.asItem());

        tag(ModTags.Items.COTTON_ITEMS.get())
            .add(ModBlocks.COTTON_CROP.asItem())
            .add(ModBlocks.COTTON_SHRUB.asItem());

        tag(ModTags.Items.ICE_CREAMS.get())
            .add(ModItems.Food.COCONUT_ICE_CREAM.item())
            .add(ModItems.Food.HONEYCOMB_ICE_CREAM.item())
            .add(ModItems.Food.RASPBERRY_ICE_CREAM.item())
            .add(ModItems.Food.TRIPLE_ICE_CREAM.item());

        tag(ItemTags.BEACON_PAYMENT_ITEMS)
            .add(ModItems.BENITOITE.item())
            .add(ModItems.RASPBERRY_RHODOLITE.item())
            .add(ModItems.GILDED_CINNAMON_STICK.item());

        tag(ItemTags.FENCE_GATES).add(ModBlocks.COCONUT_FENCE_GATE.asItem());

        tag(ItemTags.PLANKS).add(ModBlocks.COCONUT_PLANKS.asItem());

        tag(Tags.Items.LEATHER).add(ModItems.BUCKRAM.item());
        tag(ModTags.Items.GLOWING_ITEMS.get())
            .add(ModItems.Food.GLOWBERRY_JAM.item())
            .add(Items.GLOW_INK_SAC);

        tag(ItemTags.SIGNS).add(ModItems.COCONUT_SIGN.item());
        tag(ItemTags.HANGING_SIGNS).add(ModItems.COCONUT_HANGING_SIGN.item());

        tag(ItemTags.BOATS).add(ModItems.COCONUT_BOAT.item());
        tag(ItemTags.CHEST_BOATS).add(ModItems.COCONUT_CHEST_BOAT.item());

        tag(ItemTags.LOGS_THAT_BURN).addTag(ModTags.Items.COCONUT_LOGS.get());

        tag(ItemTags.WOODEN_BUTTONS).add(ModBlocks.COCONUT_BUTTON.asItem());

        tag(ItemTags.WOODEN_DOORS).add(ModBlocks.COCONUT_DOOR.asItem());

        tag(ItemTags.WOODEN_FENCES).add(ModBlocks.COCONUT_FENCE.asItem());

        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.COCONUT_PRESSURE_PLATE.asItem());

        tag(ItemTags.WOODEN_SLABS).add(ModBlocks.COCONUT_SLAB.asItem());

        tag(ItemTags.WOODEN_STAIRS).add(ModBlocks.COCONUT_STAIRS.asItem());

        tag(ItemTags.WOODEN_TRAPDOORS).add(ModBlocks.COCONUT_TRAPDOOR.asItem());
    }
}

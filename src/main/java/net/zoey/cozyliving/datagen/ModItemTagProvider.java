package net.zoey.cozyliving.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.item.ModItems;
import net.zoey.cozyliving.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        getOrCreateTagBuilder(ModTags.Items.COCONUT_LOGS)
                .add(ModBlocks.COCONUT_LOG.asItem())
                .add(ModBlocks.COCONUT_WOOD.asItem())
                .add(ModBlocks.STRIPPED_COCONUT_LOG.asItem())
                .add(ModBlocks.STRIPPED_COCONUT_WOOD.asItem());

        getOrCreateTagBuilder(ModTags.Items.BREWING_STAND_INGREDIENT_USEABLE)
                .add(ModItems.RASPBERRY)
                .add(ModItems.CINNAMON_STICK)
                .add(ModItems.GILDED_CINNAMON_STICK)
                .add(Items.COCOA_BEANS);

        getOrCreateTagBuilder(ModTags.Items.BREWING_STAND_INPUT_USEABLE)
                .add(ModItems.COCONUT_MILK)
                .add(ModItems.HEAVY_CREAM)
                .add(ModItems.RASPBERRY_TEA)
                .add(ModItems.HERBAL_TEA)
                .add(ModItems.HOT_CHOCOLATE);

        getOrCreateTagBuilder(ModTags.Items.JAMS)
                .add(ModItems.RASPBERRY_JAM)
                .add(ModItems.SWEETBERRY_JAM)
                .add(ModItems.APPLE_JAM)
                .add(ModItems.GLOWBERRY_JAM);

        getOrCreateTagBuilder(ModTags.Items.COZYLIVING_STARTER_ITEMS)
                .add(ModItems.RASPBERRY)
                .add(ModItems.COTTON_BOLL)
                .add(ModItems.COTTON_SHRUB)
                .add(ModItems.CINNAMON_STICK)
                .add(ModItems.GILDED_CINNAMON_STICK)
                .add(ModBlocks.COCONUT.asItem());

        getOrCreateTagBuilder(ModTags.Items.COTTON_ITEMS)
                .add(ModItems.COTTON_BOLL)
                .add(ModItems.COTTON_SHRUB);

        getOrCreateTagBuilder(ModTags.Items.ICE_CREAMS)
                .add(ModItems.COCONUT_ICE_CREAM)
                .add(ModItems.HONEYCOMB_ICE_CREAM)
                .add(ModItems.RASPBERRY_ICE_CREAM)
                .add(ModItems.TRIPLE_ICE_CREAM);

        getOrCreateTagBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.BENITOITE)
                .add(ModItems.RASPBERRY_RHODOLITE)
                .add(ModItems.GILDED_CINNAMON_STICK);

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlocks.COCONUT_FENCE_GATE.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlocks.COCONUT_PLANKS.asItem());


        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(ModItems.COCONUT_SIGN);
        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(ModItems.HANGING_COCONUT_SIGN);

        getOrCreateTagBuilder(ItemTags.BOATS)
                .add(ModItems.COCONUT_BOAT);
        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.COCONUT_CHEST_BOAT);

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.COCONUT_LOGS);

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.COCONUT_BUTTON.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.COCONUT_DOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.COCONUT_FENCE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.COCONUT_PRESSURE_PLATE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.COCONUT_SLAB.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.COCONUT_STAIRS.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.COCONUT_TRAPDOOR.asItem());
    }
}

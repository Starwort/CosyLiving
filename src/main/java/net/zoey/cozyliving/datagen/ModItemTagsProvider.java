package net.zoey.cozyliving.datagen;

import com.simibubi.create.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.common.data.*;
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

        tag(ModTags.Items.PAMPAS_GRASSES.get())
            .add(ModBlocks.PINK_PAMPAS_GRASS.asItem())
            .add(ModBlocks.WHITE_PAMPAS_GRASS.asItem());

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

        tag(ModTags.Items.TRIGGER_ROOT_ADVANCEMENT.get())
            .add(ModBlocks.RASPBERRY_BUSH.asItem())
            .add(ModBlocks.COTTON_CROP.asItem())
            .add(ModBlocks.COTTON_SHRUB.asItem())
            .add(ModItems.CINNAMON_STICK.item())
            .add(ModItems.GILDED_CINNAMON_STICK.item())
            .add(ModBlocks.COCONUT.asItem())
            .add(ModBlocks.PINK_PAMPAS_GRASS.asItem())
            .add(ModBlocks.WHITE_PAMPAS_GRASS.asItem());

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

        tag(ItemTags.TALL_FLOWERS).addTag(ModTags.Items.PAMPAS_GRASSES.get());
        tag(Tags.Items.LEATHERS).add(ModItems.BUCKRAM.item());

        tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(
            "c",
            "gems/raspberry_rhodolite"
        ))).add(ModItems.RASPBERRY_RHODOLITE.item());
        tag(ItemTags.create(ResourceLocation.fromNamespaceAndPath(
            "c",
            "gems/benitoite"
        ))).add(ModItems.BENITOITE.item());
        tag(Tags.Items.GEMS)
            .add(ModItems.RASPBERRY_RHODOLITE.item())
            .add(ModItems.BENITOITE.item());

        tag(Tags.Items.SEEDS).add(
            ModBlocks.COTTON_CROP.asItem(),
            ModBlocks.RASPBERRY_BUSH.item()
        );

        tag(AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
            .add(ModItems.CHARCOAL_INK.item())
            .add(ModItems.Food.CANDY_APPLE.item())
            .add(ModItems.Food.GOLDEN_CANDY_APPLE.item())
            .add(ModItems.Food.ENCHANTED_GOLDEN_CANDY_APPLE.item())
            .add(ModItems.Food.APPLE_SAUCE.item())
            .add(ModItems.Food.COCONUT_MILK.item())
            .add(ModItems.Food.HEAVY_CREAM.item())
            .add(ModItems.Food.RASPBERRY_TEA.item())
            .add(ModItems.Food.HERBAL_TEA.item())
            .add(ModItems.Food.GILDED_TEA.item())
            .add(ModItems.Food.HOT_CHOCOLATE.item())
            .add(ModItems.Food.HOTTER_CHOCOLATE.item())
            .add(ModItems.Food.SLEEPY_TEA.item())
            .add(ModItems.Food.BERRY_BLEND_SMOOTHIE.item())
            .add(ModItems.Food.RASPBERRY_JAM.item())
            .add(ModItems.Food.APPLE_JAM.item())
            .add(ModItems.Food.SWEETBERRY_JAM.item())
            .add(ModItems.Food.GLOWBERRY_JAM.item())
            .add(ModBlocks.GLOWBERRY_TART.asItem())
            .add(ModBlocks.RASPBERRY_PIE.asItem())
            .add(ModBlocks.CINNAMON_PIE.asItem())
            .add(ModBlocks.GOLDEN_CARROT_CAKE.asItem())
            .add(ModItems.Food.VILLAGER_STEW.item())
            .add(ModItems.Food.HONEYCOMB_ICE_CREAM.item())
            .add(ModItems.Food.COCONUT_ICE_CREAM.item())
            .add(ModItems.Food.RASPBERRY_ICE_CREAM.item())
            .add(ModItems.Food.TRIPLE_ICE_CREAM.item())
            .add(ModItems.Food.MYCO_MEDLEY.item())
            .add(ModBlocks.RED_VELVET_CAKE.asItem());
        tag(AllTags.AllItemTags.DEPLOYABLE_DRINK.tag)
            .add(ModItems.Food.BERRY_BLEND_SMOOTHIE.item())
            .add(ModItems.Food.COCONUT_MILK.item())
            .add(ModItems.Food.GILDED_TEA.item())
            .add(ModItems.Food.HEAVY_CREAM.item())
            .add(ModItems.Food.HERBAL_TEA.item())
            .add(ModItems.Food.HOT_CHOCOLATE.item())
            .add(ModItems.Food.HOTTER_CHOCOLATE.item())
            .add(ModItems.Food.PINA_GLOWADA.item())
            .add(ModItems.Food.RASPBERRY_TEA.item())
            .add(ModItems.Food.SLEEPY_TEA.item());
    }
}

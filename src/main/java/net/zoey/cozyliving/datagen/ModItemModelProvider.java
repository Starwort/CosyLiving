package net.zoey.cozyliving.datagen;

import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(
        PackOutput output,
        ExistingFileHelper existingFileHelper
    ) {
        super(output, CozyLiving.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.Food.CINNAMON_BUN);
        simpleItem(ModItems.Food.COCONUT_MILK);
        simpleItem(ModItems.Food.HEAVY_CREAM);
        simpleItem(ModItems.Food.CANDY_APPLE);
        simpleItem(ModItems.Food.GOLDEN_CANDY_APPLE);
        withExistingParent(
            ModItems.Food.ENCHANTED_GOLDEN_CANDY_APPLE
                .holder()
                .getId()
                .getPath(), ResourceLocation.parse("item/generated")
        ).texture(
            "layer0",
            CozyLiving.loc("item/" + ModItems.Food.GOLDEN_CANDY_APPLE
                .holder()
                .getId()
                .getPath())
        );
        simpleItem(ModItems.Food.ROASTED_PUMPKIN_SEEDS);
        simpleItem(ModItems.Food.ROASTED_MELON_SEEDS);
        simpleItem(ModItems.Food.GLOWBERRY_TART_SLICE);
        simpleItem(ModItems.Food.RASPBERRY_PIE_SLICE);
        simpleItem(ModItems.Food.CINNAMON_PIE_SLICE);
        simpleItem(ModItems.Food.GOLDEN_CARROT_CAKE_SLICE);
        simpleItem(ModItems.Food.APPLE_SAUCE);
        simpleItem(ModItems.Food.WATERMELON_POPSICLE);
        simpleItem(ModItems.Food.HONEYCOMB_ICE_CREAM);
        simpleItem(ModItems.Food.COCONUT_ICE_CREAM);
        simpleItem(ModItems.Food.RASPBERRY_ICE_CREAM);
        simpleItem(ModItems.Food.TRIPLE_ICE_CREAM);
        simpleItem(ModItems.Food.VILLAGER_STEW);
        simpleItem(ModItems.Food.RASPBERRY_TEA);
        simpleItem(ModItems.Food.HERBAL_TEA);
        simpleItem(ModItems.Food.GILDED_TEA);
        simpleItem(ModItems.Food.HOT_CHOCOLATE);
        simpleItem(ModItems.Food.HOTTER_CHOCOLATE);
        simpleItem(ModItems.Food.SLEEPY_TEA);
        simpleItem(ModItems.Food.GOOPY_CHORUS);
        simpleItem(ModItems.Food.MYCO_MEDLEY);
        simpleItem(ModItems.Food.BERRY_BLEND_SMOOTHIE);
        simpleItem(ModItems.Food.RASPBERRY_JAM);
        simpleItem(ModItems.Food.APPLE_JAM);
        simpleItem(ModItems.Food.SWEETBERRY_JAM);
        simpleItem(ModItems.Food.GLOWBERRY_JAM);
        simpleItem(ModItems.Food.RASPBERRY_JAM_DOUGHNUT);
        simpleItem(ModItems.Food.GLOWBERRY_JAM_DOUGHNUT);
        simpleItem(ModItems.Food.APPLE_JAM_DOUGHNUT);
        simpleItem(ModItems.Food.SWEETBERRY_JAM_DOUGHNUT);
        simpleItem(ModItems.Food.CREAM_DOUGHNUT);
        simpleItem(ModItems.Food.CHOCOLATE_BAR);
        simpleItem(ModItems.Food.RED_SUGAR);
        simpleItem(ModItems.Food.MAO_CROQUI);
        simpleItem(ModBlocks.RASPBERRY_BUSH);
        simpleItem(ModBlocks.GLOWBERRY_TART);
        simpleItem(ModBlocks.RASPBERRY_PIE);
        simpleItem(ModBlocks.CINNAMON_PIE);
        simpleItem(ModBlocks.GOLDEN_CARROT_CAKE);
        simpleItem(ModBlocks.COCONUT_DOOR);
        simpleItem(ModBlocks.RED_VELVET_CAKE);
        simpleItem(ModItems.Food.RED_VELVET_CAKE_SLICE);

        simpleItem(ModItems.WAND_OF_HUNGER);
        simpleItem(ModItems.RASPBERRY_RHODOLITE);
        simpleItem(ModItems.BENITOITE);
        simpleItem(ModItems.CINNAMON_STICK);
        simpleItem(ModItems.GILDED_CINNAMON_STICK);
        simpleItem(ModItems.CHARCOAL_INK);
        simpleItem(ModItems.BUCKRAM);
        simpleItem(ModItems.COCONUT_SIGN);
        simpleItem(ModItems.COCONUT_HANGING_SIGN);
        simpleItem(ModItems.COCONUT_BOAT);
        simpleItem(ModItems.COCONUT_CHEST_BOAT);
        simpleItem(ModItems.FLOWER_CROWN);
        simpleItem(ModBlocks.COTTON_CROP);
        simpleItem(ModBlocks.COTTON_SHRUB);
        simpleItem(ModBlocks.COCONUT_SAPLING);
        simpleItem(ModItems.Food.BUTTERSCOTCH_STAR);
        simpleItem(ModItems.Food.GORB_COOKIE);
        simpleItem(ModItems.Food.ICTO_COOKIE);
        simpleItem(ModItems.Food.FORTUNE_COOKIE);

        simpleItem(ModBlocks.PINK_PAMPAS_GRASS);
        simpleItem(ModBlocks.WHITE_PAMPAS_GRASS);
    }

    private ItemModelBuilder simpleItem(ModItems.Food food) {
        return simpleItem(food.holder());
    }

    private ItemModelBuilder simpleItem(ModItems item) {
        return simpleItem(item.holder());
    }

    private ItemModelBuilder simpleItem(ModBlocks block) {
        var item = block.itemHolder();
        assert item != null : "Called simpleItem on a block with no item!";
        return simpleItem(item);
    }

    private ItemModelBuilder simpleItem(DeferredHolder<Item, Item> item) {
        var path = item.getId().getPath();
        return withExistingParent(
            path,
            ResourceLocation.parse("item/generated")
        ).texture(
            "layer0",
            CozyLiving.loc("item/" + path)
        );
    }
}

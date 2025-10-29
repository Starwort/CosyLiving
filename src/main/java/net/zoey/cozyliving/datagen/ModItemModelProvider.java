package net.zoey.cozyliving.datagen;

import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.registries.*;
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
                .registryObject()
                .getId()
                .getPath(), ResourceLocation.parse("item/generated")
        ).texture(
            "layer0", ResourceLocation.fromNamespaceAndPath(
                CozyLiving.MODID,
                "item/" + ModItems.Food.GOLDEN_CANDY_APPLE
                    .registryObject()
                    .getId()
                    .getPath()
            )
        );
        simpleItem(ModItems.Food.ROASTED_PUMPKIN_SEEDS);
        simpleItem(ModItems.Food.ROASTED_MELON_SEEDS);
        simpleItem(ModItems.Food.GLOWBERRY_TART_SLICE);
        simpleItem(ModItems.Food.RASPBERRY_PIE_SLICE);
        simpleItem(ModItems.Food.CINNAMON_PIE_SLICE);
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
        simpleItem(ModItems.Food.RASPBERRY);
        simpleItem(ModItems.Food.GLOWBERRY_TART);
        simpleItem(ModItems.Food.RASPBERRY_PIE);
        simpleItem(ModItems.Food.CINNAMON_PIE);

        simpleItem(ModItems.WAND_OF_HUNGER);
        simpleItem(ModItems.RASPBERRY_RHODOLITE);
        simpleItem(ModItems.BENITOITE);
        simpleItem(ModItems.CINNAMON_STICK);
        simpleItem(ModItems.GILDED_CINNAMON_STICK);
        simpleItem(ModItems.CHARCOAL_INK);
        simpleItem(ModItems.BUCKRAM);
        //        simpleItem(ModItems.COTTON_BOLL);
        //        simpleItem(ModItems.COTTON_SHRUB);
        simpleItem(ModItems.COCONUT_SIGN);
        simpleItem(ModItems.COCONUT_HANGING_SIGN);
        simpleItem(ModItems.COCONUT_BOAT);
        simpleItem(ModItems.COCONUT_CHEST_BOAT);
        simpleItem(ModItems.FLOWER_CROWN);
    }

    private ItemModelBuilder simpleItem(ModItems.Food food) {
        return simpleItem(food.registryObject());
    }

    private ItemModelBuilder simpleItem(ModItems item) {
        return simpleItem(item.registryObject());
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        var id = item.getId();
        assert id != null;
        var path = id.getPath();
        return withExistingParent(
            path,
            ResourceLocation.parse("item/generated")
        ).texture(
            "layer0",
            ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, "item/" + path)
        );
    }
}

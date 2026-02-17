// See https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/foundation/data/recipe/CreateHauntingRecipeGen.java
// https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/api/data/recipe/HauntingRecipeGen.java
package net.zoey.cozyliving.datagen.create;

import com.simibubi.create.api.data.recipe.*;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;

public class HauntingRecipeProvider extends HauntingRecipeGen {
    public GeneratedRecipe convert(
        RegistryObject<Item> input,
        RegistryObject<Item> result
    ) {
        return create(
            input.getId().getPath(),
            p -> p
                .withItemIngredients(Ingredient.of(input.get()))
                .output(result.get())
                .whenModLoaded("create")
        );
    }

    GeneratedRecipe PINK_PAMPAS = convert(
        ModBlocks.WHITE_PAMPAS_GRASS.itemRegistryObject(),
        ModBlocks.PINK_PAMPAS_GRASS.itemRegistryObject()
    ),

    WHITE_PAMPAS = convert(
        ModBlocks.PINK_PAMPAS_GRASS.itemRegistryObject(),
        ModBlocks.WHITE_PAMPAS_GRASS.itemRegistryObject()
    );

    public HauntingRecipeProvider(PackOutput output) {
        super(output, CozyLiving.MODID);
    }
}

// See https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/foundation/data/recipe/CreateHauntingRecipeGen.java
// https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/api/data/recipe/HauntingRecipeGen.java
package net.zoey.cozyliving.datagen.create;

import com.simibubi.create.api.data.recipe.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;

import java.util.concurrent.*;

public class HauntingRecipeProvider extends HauntingRecipeGen {
    public GeneratedRecipe convert(
        DeferredHolder<Item,Item> input,
        DeferredHolder<Item,Item> result
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
        ModBlocks.WHITE_PAMPAS_GRASS.itemHolder(),
        ModBlocks.PINK_PAMPAS_GRASS.itemHolder()
    ),

    WHITE_PAMPAS = convert(
        ModBlocks.PINK_PAMPAS_GRASS.itemHolder(),
        ModBlocks.WHITE_PAMPAS_GRASS.itemHolder()
    );

    public HauntingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CozyLiving.MODID);
    }
}

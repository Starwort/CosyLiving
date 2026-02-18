// See https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/foundation/data/recipe/CreateHauntingRecipeGen.java
// https://github.com/Creators-of-Create/Create/blob/mc1.20.1/dev/src/main/java/com/simibubi/create/api/data/recipe/HauntingRecipeGen.java
package net.zoey.cozyliving.datagen.create;

import com.simibubi.create.api.data.recipe.*;
import com.simibubi.create.content.processing.recipe.*;
import net.minecraft.data.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraftforge.common.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;

import java.util.function.*;

public class MillingRecipeProvider extends MillingRecipeGen {
    GeneratedRecipe PAMPAS_GRASSES = create(
        "pampas_grasses",
        p -> p
            .require(ModTags.Items.PAMPAS_GRASSES.get())
            .duration(100)
            .output(Items.FEATHER, 2)
            .output(0.5f, Items.FEATHER, 1)
            .whenModLoaded("create")
    );

    public MillingRecipeProvider(PackOutput output) {
        super(output, CozyLiving.MODID);
    }
}

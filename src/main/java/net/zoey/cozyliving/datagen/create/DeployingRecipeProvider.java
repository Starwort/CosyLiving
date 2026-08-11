package net.zoey.cozyliving.datagen.create;

import com.simibubi.create.api.data.recipe.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.crafting.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.block.*;

import java.util.concurrent.*;

@SuppressWarnings("unused")
public class DeployingRecipeProvider extends DeployingRecipeGen {
    public GeneratedRecipe sliceSliceableFoodBlock(
        ModBlocks sliceable
    ) {
        return create(
            sliceable.asItemId(),
            p -> p
                .require(sliceable.asItem())
                .require(ItemTags.SWORDS)
                .toolNotConsumed()
                .output(((GenericSliceableFoodBlock) sliceable.block()).sliceItem.get(), 4)
                .whenModLoaded("create")
        );
    }

    GeneratedRecipe
        GLOWBERRY_TART = sliceSliceableFoodBlock(ModBlocks.GLOWBERRY_TART),
        CINNAMON_PIE = sliceSliceableFoodBlock(ModBlocks.CINNAMON_PIE),
        RASPBERRY_PIE = sliceSliceableFoodBlock(ModBlocks.RASPBERRY_PIE),
        GOLDEN_CARROT_CAKE = sliceSliceableFoodBlock(ModBlocks.GOLDEN_CARROT_CAKE),
        RED_VELVET_CAKE = sliceSliceableFoodBlock(ModBlocks.RED_VELVET_CAKE);

    public DeployingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, CozyLiving.MODID);
    }
}

package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.datamaps.builtin.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

import java.util.concurrent.*;

public class ModDataMapProvider extends DataMapProvider {
    public ModDataMapProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(@NotNull HolderLookup.Provider provider) {
        builder(NeoForgeDataMaps.COMPOSTABLES)
            .add(ModBlocks.COCONUT.asItemId(), new Compostable(0.30f), false)
            .add(ModItems.Food.APPLE_SAUCE.id(), new Compostable(0.65f), false)
            .add(ModItems.Food.CANDY_APPLE.id(), new Compostable(0.65f), false)
            .add(ModItems.Food.CINNAMON_BUN.id(), new Compostable(0.65f), false)
            .add(ModBlocks.RASPBERRY_BUSH.asItemId(), new Compostable(0.30f), false)
            .add(ModItems.Food.ROASTED_MELON_SEEDS.id(), new Compostable(0.30f), false)
            .add(ModItems.Food.ROASTED_PUMPKIN_SEEDS.id(), new Compostable(0.30f), false)
            .add(ModItems.Food.WATERMELON_POPSICLE.id(), new Compostable(0.30f), false)
            .add(ModBlocks.GLOWBERRY_TART.asItemId(), new Compostable(1f), false)
            .add(ModItems.Food.GLOWBERRY_TART_SLICE.id(), new Compostable(0.30f), false)
            .add(ModBlocks.RASPBERRY_PIE.asItemId(), new Compostable(1f), false)
            .add(ModItems.Food.RASPBERRY_PIE_SLICE.id(), new Compostable(0.30f), false)
            .add(ModBlocks.CINNAMON_PIE.asItemId(), new Compostable(0.100f), false)
            .add(ModItems.Food.CINNAMON_PIE_SLICE.id(), new Compostable(0.30f), false)
            .add(ModBlocks.GOLDEN_CARROT_CAKE.asItemId(), new Compostable(0.100f), false)
            .add(ModItems.Food.GOLDEN_CARROT_CAKE_SLICE.id(), new Compostable(0.30f), false)
            .add(ModItems.CINNAMON_STICK.id(), new Compostable(0.30f), false)
            .add(ModItems.GILDED_CINNAMON_STICK.id(), new Compostable(1f), false)
            .add(ModItems.FLOWER_CROWN.id(), new Compostable(1f), false)
            .add(ModBlocks.COCONUT_SAPLING.asItemId(), new Compostable(0.30f), false)
            .add(ModBlocks.COCONUT_LEAVES.asItemId(), new Compostable(0.30f), false)
            .add(ModItems.Food.GOOPY_CHORUS.id(), new Compostable(0.30f), false)
            .add(ModItems.Food.BUTTERSCOTCH_STAR.id(), new Compostable(0.30f), false)
            .add(ModBlocks.PINK_PAMPAS_GRASS.asItemId(), new Compostable(0.30f), false)
            .add(ModBlocks.WHITE_PAMPAS_GRASS.asItemId(), new Compostable(0.30f), false)
        ;
    }
}

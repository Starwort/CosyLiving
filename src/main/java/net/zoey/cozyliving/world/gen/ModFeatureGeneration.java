package net.zoey.cozyliving.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import static net.zoey.cozyliving.util.ModTags.Biomes.HAS_COTTON_SHRUB_PATCHES;
import static net.zoey.cozyliving.util.ModTags.Biomes.HAS_RASPBERRY_PATCHES;
import static net.zoey.cozyliving.world.gen.ModPlacedFeatures.BENITOITE_ORE_PLACED_KEY;
import static net.zoey.cozyliving.world.gen.ModPlacedFeatures.RASPBERRY_RHODOLITE_ORE_PLACED_KEY;

public class ModFeatureGeneration {




    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, BENITOITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, RASPBERRY_RHODOLITE_ORE_PLACED_KEY);
    }

    public static void generateVegetation() {
        BiomeModifications.addFeature(BiomeSelectors.tag(HAS_RASPBERRY_PATCHES),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_RASPBERRIES_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.tag(HAS_COTTON_SHRUB_PATCHES),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_COTTON_SHRUB_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.LUSH_CAVES),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_LUSH_COTTON_SHRUB_PLACED_KEY);

    }

    public static void generateTrees(){
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.BEACH),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.COCONUT_TREE_PLACED_KEY
        );
    }

}
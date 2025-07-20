package net.zoey.cozyliving.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

import static net.zoey.cozyliving.world.gen.ModPlacedFeatures.BENITOITE_ORE_PLACED_KEY;
import static net.zoey.cozyliving.world.gen.ModPlacedFeatures.RASPBERRY_RHODOLITE_ORE_PLACED_KEY;

public class ModFeatureGeneration {
    public static final TagKey<Biome> HAS_RASPBERRY_PATCHES = TagKey.of(RegistryKeys.BIOME, new Identifier("has_raspberry_patches"));

    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, BENITOITE_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES, RASPBERRY_RHODOLITE_ORE_PLACED_KEY);
    }

    public static void generateVegetation() {
        BiomeModifications.addFeature(BiomeSelectors.tag(HAS_RASPBERRY_PATCHES),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.PATCH_RASPBERRIES_PLACED_KEY);

    }

    public static void generateTrees(){
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.BEACH),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.COCONUT_TREE_PLACED_KEY
        );
    }

}
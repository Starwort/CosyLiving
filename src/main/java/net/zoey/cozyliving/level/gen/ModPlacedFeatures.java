package net.zoey.cozyliving.level.gen;

import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.data.worldgen.placement.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.placement.*;
import net.zoey.cozyliving.*;

import java.util.*;


public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RASPBERRY_RHODOLITE_ORE_PLACED_KEY = registerKey(
        "raspberry_rhodolite_ore_placed");
    public static final ResourceKey<PlacedFeature> BENITOITE_ORE_PLACED_KEY = registerKey(
        "benitoite_ore_placed");
    public static final ResourceKey<PlacedFeature> PATCH_RASPBERRIES_PLACED_KEY = registerKey(
        "patch_raspberries_placed");
    public static final ResourceKey<PlacedFeature> PATCH_COTTON_SHRUB_PLACED_KEY = registerKey(
        "patch_cotton_shrub_placed");
    public static final ResourceKey<PlacedFeature> PATCH_LUSH_COTTON_SHRUB_PLACED_KEY = registerKey(
        "patch_lush_cotton_shrub_placed");
    public static final ResourceKey<PlacedFeature> COCONUT_TREE_PLACED_KEY = registerKey(
        "coconut_tree_placed");
    public static final ResourceKey<PlacedFeature> PATCH_PINK_PAMPAS_PLACED_KEY = registerKey(
        "patch_pink_pampas_placed");
    public static final ResourceKey<PlacedFeature> PATCH_WHITE_PAMPAS_PLACED_KEY = registerKey(
        "patch_white_pampas_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(
            Registries.CONFIGURED_FEATURE);

        register(
            context,
            RASPBERRY_RHODOLITE_ORE_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.RASPBERRY_RHODOLITE_ORE_KEY),
            ModOrePlacement.modifiersWithCount(
                3, //spawns roughly three times per chunk, most common at y -63 and least common at y 127
                HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-63),
                    VerticalAnchor.absolute(127)
                )
            )
        );

        register(
            context,
            BENITOITE_ORE_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.BENITOITE_ORE_KEY),
            ModOrePlacement.modifiersWithCount(
                3, //spawns roughly three times per chunk, most common at y -63 and least common at y 127
                HeightRangePlacement.triangle(
                    VerticalAnchor.absolute(-63),
                    VerticalAnchor.absolute(127)
                )
            )
        );

        register(
            context,
            PATCH_RASPBERRIES_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_RASPBERRIES_KEY),
            List.of(
                RarityFilter.onAverageOnceEvery(31),
                InSquarePlacement.spread(),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome()
            )
        );

        /*register(context, PATCH_COTTON_SHRUB_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_COTTON_SHRUB_KEY),
                List.of(RarityFilter.onAverageOnceEvery(15), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                        BiomeFilter.biome()));*/

        register(
            context, PATCH_COTTON_SHRUB_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_COTTON_SHRUB_KEY), List.of(
                RarityFilter.onAverageOnceEvery(31),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome()
            )
        );

        //TODO: cotton shrubs kinda just fail to generate in lush caves for some reason
        register(
            context,
            PATCH_LUSH_COTTON_SHRUB_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_LUSH_COTTON_SHRUB_KEY),
            List.of(
                CountPlacement.of(CozyLiving.DEBUG_MODE ? 999 : 125),
                InSquarePlacement.spread(),
                PlacementUtils.FULL_RANGE,
                BiomeFilter.biome()
                //EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.replaceable(), 12),
                //RandomOffsetPlacement.vertical(ConstantInt.of(1))
            )
        );

        //Coconut trees
        /*register(context, COCONUT_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.COCONUT_TREE_KEY),
                RarityFilterPlacementModifier.of(10), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());*/
        register(
            context,
            COCONUT_TREE_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.COCONUT_TREE_KEY),
            List.of(
                RarityFilter.onAverageOnceEvery(15),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome()
            )
        );

        //PAMPAS
        register(
            context, PATCH_PINK_PAMPAS_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_PINK_PAMPAS_KEY), List.of(
                RarityFilter.onAverageOnceEvery(127), //Halved as there's two types
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome()
            )
        );
        register(
            context, PATCH_WHITE_PAMPAS_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_WHITE_PAMPAS_KEY), List.of(
                RarityFilter.onAverageOnceEvery(127), //Halved as there's two types
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome()
            )
        );
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(
            Registries.PLACED_FEATURE,
            CozyLiving.loc(name)
        );
    }

    private static void register(
        BootstrapContext<PlacedFeature> context,
        ResourceKey<PlacedFeature> key,
        Holder<ConfiguredFeature<?, ?>> configuration,
        List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
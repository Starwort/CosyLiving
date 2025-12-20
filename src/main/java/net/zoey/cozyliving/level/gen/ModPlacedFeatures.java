package net.zoey.cozyliving.level.gen;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.VegetationPatchFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.zoey.cozyliving.CozyLiving;

import java.util.List;

import static net.minecraft.world.level.levelgen.placement.InSquarePlacement.spread;

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


    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(
            Registries.CONFIGURED_FEATURE);

        register(
            context,
            RASPBERRY_RHODOLITE_ORE_PLACED_KEY,
            configuredFeatures.getOrThrow(ModConfiguredFeatures.RASPBERRY_RHODOLITE_ORE_KEY),
            ModOrePlacement.modifiersWithCount(
                7, //spawns roughly seven times per chunk, most common at y -63 and least common at y 127
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
                7, //spawns roughly seven times per chunk, most common at y -63 and least common at y 127
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
                RarityFilter.onAverageOnceEvery(CozyLiving.DEBUG_MODE ? 1 : 15),
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
            // TODO: remove this debug switch once raspberry patch placement is fixed
            configuredFeatures.getOrThrow(CozyLiving.DEBUG_MODE
                ? ModConfiguredFeatures.PATCH_RASPBERRIES_KEY
                : ModConfiguredFeatures.PATCH_COTTON_SHRUB_KEY), List.of(
                RarityFilter.onAverageOnceEvery(CozyLiving.DEBUG_MODE ? 1 : 31),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome()
            )
        );

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
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(
            Registries.PLACED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, name)
        );
    }

    private static void register(
        BootstapContext<PlacedFeature> context,
        ResourceKey<PlacedFeature> key,
        Holder<ConfiguredFeature<?, ?>> configuration,
        List<PlacementModifier> modifiers
    ) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
package net.zoey.cozyliving.world.gen;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.world.ModConfiguredFeatures;

import java.util.List;

public class ModPlacedFeatures {

    //PlacedFeatures

    public static final RegistryKey<PlacedFeature> PATCH_RASPBERRIES_PLACED_KEY = registerKey("patch_raspberries_placed");
    public static final RegistryKey<PlacedFeature> PATCH_COTTON_SHRUB_PLACED_KEY = registerKey("patch_cotton_shrub_placed");
    public static final RegistryKey<PlacedFeature> PATCH_LUSH_COTTON_SHRUB_PLACED_KEY = registerKey("patch_lush_cotton_shrub_placed");
    public static final RegistryKey<PlacedFeature> BENITOITE_ORE_PLACED_KEY = registerKey("benitoite_ore_placed");
    public static final RegistryKey<PlacedFeature> RASPBERRY_RHODOLITE_ORE_PLACED_KEY = registerKey("raspberry_rhodolite_ore_placed");
    public static final RegistryKey<PlacedFeature> COCONUT_TREE_PLACED_KEY = registerKey("coconut_tree_placed");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, BENITOITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BENITOITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(7, // Veins per Chunk
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-63), YOffset.fixed(127))));

        register(context, RASPBERRY_RHODOLITE_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RASPBERRY_RHODOLITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(7, // Veins per Chunk
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-63), YOffset.fixed(127))));

        //rarity is usually 31
        register(context, PATCH_RASPBERRIES_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_RASPBERRIES_KEY),
                RarityFilterPlacementModifier.of(15), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(context, PATCH_COTTON_SHRUB_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_COTTON_SHRUB_KEY),
                RarityFilterPlacementModifier.of(15), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());

        register(   //copied this from lush cave foliage lol
                context,
                PATCH_LUSH_COTTON_SHRUB_PLACED_KEY,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.PATCH_LUSH_COTTON_SHRUB_KEY),
                CountPlacementModifier.of(125),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12),
                RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
                BiomePlacementModifier.of());

        register(context, COCONUT_TREE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.COCONUT_TREE_KEY),
                RarityFilterPlacementModifier.of(10), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                BiomePlacementModifier.of());

    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(CozyLiving.MOD_ID, name));

    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }


}
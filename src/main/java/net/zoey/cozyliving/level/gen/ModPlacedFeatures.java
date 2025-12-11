package net.zoey.cozyliving.level.gen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.zoey.cozyliving.CozyLiving;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> RASPBERRY_RHODOLITE_ORE_PLACED_KEY = registerKey("raspberry_rhodolite_ore_placed");
    public static final ResourceKey<PlacedFeature> BENITOITE_ORE_PLACED_KEY = registerKey("benitoite_ore_placed");



    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, RASPBERRY_RHODOLITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RASPBERRY_RHODOLITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(7, //spawns roughly seven times per chunk, most common at y -63 and least common at y 127
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-63), VerticalAnchor.absolute(127))));

        register(context, BENITOITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BENITOITE_ORE_KEY),
                ModOrePlacement.modifiersWithCount(7, //spawns roughly seven times per chunk, most common at y -63 and least common at y 127
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(-63), VerticalAnchor.absolute(127))));
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
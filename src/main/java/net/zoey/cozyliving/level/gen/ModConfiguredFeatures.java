package net.zoey.cozyliving.level.gen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RASPBERRY_RHODOLITE_ORE_KEY = registerKey("raspberry_rhodolite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BENITOITE_ORE_KEY = registerKey("benitoite_ore");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> raspberryRhodoliteOres
                = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.RASPBERRY_RHODOLITE_ORE.block().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE.block().defaultBlockState()));

        List<OreConfiguration.TargetBlockState> benitoiteOres
                = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.BENITOITE_ORE.block().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_BENITOITE_ORE.block().defaultBlockState()));

        register(context, RASPBERRY_RHODOLITE_ORE_KEY, Feature.ORE, new OreConfiguration(raspberryRhodoliteOres, 3));
        register(context, BENITOITE_ORE_KEY, Feature.ORE, new OreConfiguration(benitoiteOres, 3));

    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
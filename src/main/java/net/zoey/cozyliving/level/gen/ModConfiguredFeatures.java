package net.zoey.cozyliving.level.gen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.level.RaspberryBushesFeature;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RASPBERRY_RHODOLITE_ORE_KEY = registerKey("raspberry_rhodolite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BENITOITE_ORE_KEY = registerKey("benitoite_ore");
    //public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_RASPBERRIES_KEY = registerKey("patch_raspberries");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_COTTON_SHRUB_KEY = registerKey("patch_cotton_shrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_LUSH_COTTON_SHRUB_KEY = registerKey("patch_lush_cotton_shrub");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        //RASPBERRY RHODOLITE
        List<OreConfiguration.TargetBlockState> raspberryRhodoliteOres
                = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.RASPBERRY_RHODOLITE_ORE.block().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE.block().defaultBlockState()));

        register(context, RASPBERRY_RHODOLITE_ORE_KEY, Feature.ORE, new OreConfiguration(raspberryRhodoliteOres, 3));



        //BENITOITE
        List<OreConfiguration.TargetBlockState> benitoiteOres
                = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.BENITOITE_ORE.block().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceable, ModBlocks.DEEPSLATE_BENITOITE_ORE.block().defaultBlockState()));

        register(context, BENITOITE_ORE_KEY, Feature.ORE, new OreConfiguration(benitoiteOres, 3));

        //COTTON
        //public static final Feature<RandomPatchConfiguration> FLOWER = register("flower", new RandomPatchFeature(RandomPatchConfiguration.CODEC));

        /*register(context, PATCH_COTTON_SHRUB_KEY, Feature.FLOWER, new RandomPatchConfiguration(5,5,5,

                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.COTTON_SHRUB.block().defaultBlockState()))

        ));*/

        register(context, PATCH_COTTON_SHRUB_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(ModBlocks.COTTON_SHRUB.block()
                                .defaultBlockState())
                ), List.of(Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK)));

        register(context, PATCH_LUSH_COTTON_SHRUB_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(ModBlocks.COTTON_SHRUB.block()
                                .defaultBlockState())
                ), List.of(Blocks.CLAY, Blocks.MOSS_BLOCK, Blocks.GRASS_BLOCK)));


        //RASPBERRY PATCH STUFF
        //register(context, PATCH_RASPBERRIES_KEY, RaspberryBushesFeature.RASPBERRY_BUSHES, new NoneFeatureConfiguration());
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
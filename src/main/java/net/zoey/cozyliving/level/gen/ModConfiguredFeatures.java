package net.zoey.cozyliving.level.gen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.level.RaspberryBushesFeature;
import net.zoey.cozyliving.level.gen.coconut_tree.CoconutTreeFeature;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> RASPBERRY_RHODOLITE_ORE_KEY = registerKey(
        "raspberry_rhodolite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BENITOITE_ORE_KEY = registerKey(
        "benitoite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_RASPBERRIES_KEY = registerKey(
        "patch_raspberries");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_COTTON_SHRUB_KEY = registerKey(
        "patch_cotton_shrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_LUSH_COTTON_SHRUB_KEY = registerKey(
        "patch_lush_cotton_shrub");

    public static final ResourceKey<ConfiguredFeature<?, ?>> COCONUT_TREE_KEY = registerKey(
            "coconut_tree");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        //RASPBERRY RHODOLITE
        List<OreConfiguration.TargetBlockState> raspberryRhodoliteOres = List.of(
            OreConfiguration.target(
                stoneReplaceable,
                ModBlocks.RASPBERRY_RHODOLITE_ORE.block().defaultBlockState()
            ), OreConfiguration.target(
                deepslateReplaceable,
                ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE.block().defaultBlockState()
            )
        );

        context.register(
            RASPBERRY_RHODOLITE_ORE_KEY,
            new ConfiguredFeature<>(
                Feature.ORE,
                new OreConfiguration(raspberryRhodoliteOres, 3)
            )
        );


        //BENITOITE
        List<OreConfiguration.TargetBlockState> benitoiteOres = List.of(
            OreConfiguration.target(
                stoneReplaceable,
                ModBlocks.BENITOITE_ORE.block().defaultBlockState()
            ),
            OreConfiguration.target(
                deepslateReplaceable,
                ModBlocks.DEEPSLATE_BENITOITE_ORE.block().defaultBlockState()
            )
        );

        context.register(
            BENITOITE_ORE_KEY,
            new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(benitoiteOres, 3))
        );

        //COTTON
        //public static final Feature<RandomPatchConfiguration> FLOWER = register("flower", new RandomPatchFeature(RandomPatchConfiguration.CODEC));

        /*register(context, PATCH_COTTON_SHRUB_KEY, Feature.FLOWER, new RandomPatchConfiguration(5,5,5,

                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.COTTON_SHRUB.block().defaultBlockState()))

        ));*/

        context.register(
            PATCH_COTTON_SHRUB_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.COTTON_SHRUB
                    .block()
                    .defaultBlockState())),
                List.of(Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK)
            )
            )
        );

        context.register(
            PATCH_LUSH_COTTON_SHRUB_KEY, new ConfiguredFeature<>(
                Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.COTTON_SHRUB
                    .block()
                    .defaultBlockState())),
                List.of(Blocks.CLAY, Blocks.MOSS_BLOCK, Blocks.GRASS_BLOCK)
            )
            )
        );


        //RASPBERRY PATCH STUFF
        context.register(
            PATCH_RASPBERRIES_KEY,
            new ConfiguredFeature<>(
                RaspberryBushesFeature.RASPBERRY_BUSHES.get(),
                new NoneFeatureConfiguration()
            )
        );


        //COCONUT TREE STUFF
        /*context.register(
                COCONUT_TREE_KEY, new ConfiguredFeature<>(
                        Feature.TREE,

                        new TreeConfiguration.TreeConfigurationBuilder(
                                BlockStateProvider.simple(ModBlocks.COCONUT_LOG.block().defaultBlockState()),
                                new StraightTrunkPlacer(5, 4, 3),

                                BlockStateProvider.simple(ModBlocks.COCONUT_LEAVES.block().defaultBlockState()),
                                new BlobFoliagePlacer(ConstantInt.of(3), ConstantInt.of(2), 3),

                                new TwoLayersFeatureSize(1, 0, 2))

                        .build()));*/
        context.register(
                COCONUT_TREE_KEY,
                new ConfiguredFeature<>(
                        CoconutTreeFeature.COCONUT_TREE.get(),
                        new NoneFeatureConfiguration()
                )
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, name)
        );
    }
}
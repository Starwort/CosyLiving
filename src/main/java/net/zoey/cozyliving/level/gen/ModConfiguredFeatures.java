package net.zoey.cozyliving.level.gen;

import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.level.*;
import net.zoey.cozyliving.level.gen.coconut_tree.*;

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

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PINK_PAMPAS_KEY = registerKey(
            "patch_pink_pampas");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_WHITE_PAMPAS_KEY = registerKey(
            "patch_white_pampas");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
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
        context.register(
                COCONUT_TREE_KEY,
                new ConfiguredFeature<>(
                        CoconutTreeFeature.COCONUT_TREE.get(),
                        new NoneFeatureConfiguration()
                )
        );

        //PAMPAS GRASS
        context.register(
                PATCH_PINK_PAMPAS_KEY, new ConfiguredFeature<>(
                        Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.PINK_PAMPAS_GRASS
                                .block()
                                .defaultBlockState())),
                        List.of(Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK)
                )
                )
        );
        context.register(
                PATCH_WHITE_PAMPAS_KEY, new ConfiguredFeature<>(
                        Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(
                        Feature.SIMPLE_BLOCK,
                        new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.WHITE_PAMPAS_GRASS
                                .block()
                                .defaultBlockState())),
                        List.of(Blocks.GRASS_BLOCK, Blocks.MOSS_BLOCK)
                )
                )
        );

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(
            Registries.CONFIGURED_FEATURE,
            CozyLiving.loc(name)
        );
    }
}
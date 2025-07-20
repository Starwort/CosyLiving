package net.zoey.cozyliving.world;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.world.gen.coconut_tree.CoconutTreeFeature;
import net.zoey.cozyliving.world.gen.raspberry_bush_patches.RaspberryBushesFeature;

import java.util.List;

public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> BENITOITE_ORE_KEY = registerKey("benitoite_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> RASPBERRY_RHODOLITE_ORE_KEY = registerKey("raspberry_rhodolite_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> PATCH_RASPBERRIES_KEY = registerKey("patch_raspberries");

    public static final RegistryKey<ConfiguredFeature<?, ?>> COCONUT_TREE_KEY = registerKey("coconut_tree");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {


        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        //BENITOITE STUFF
        List<OreFeatureConfig.Target> benitoiteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.BENITOITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_BENITOITE_ORE.getDefaultState()));

        register(context, BENITOITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(benitoiteOres, 3));

        //RASPBERRY RHODOLITE STUFF
        List<OreFeatureConfig.Target> raspberryRhodoliteOres =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.RASPBERRY_RHODOLITE_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE.getDefaultState()));

        register(context, RASPBERRY_RHODOLITE_ORE_KEY, Feature.ORE, new OreFeatureConfig(raspberryRhodoliteOres, 3));

        //RASPBERRY PATCH STUFF

        register(context, PATCH_RASPBERRIES_KEY, RaspberryBushesFeature.RASPBERRY_BUSHES, new DefaultFeatureConfig());

        /*register(context, COCONUT_TREE_KEY, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(ModBlocks.COCONUT_LOG),
                        new StraightTrunkPlacer(4, 4, 4),
                        BlockStateProvider.of(ModBlocks.COCONUT_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0),2),
                        new TwoLayersFeatureSize(3, 0, 3)).build());*/
        register(context, COCONUT_TREE_KEY, CoconutTreeFeature.COCONUT_TREE, new DefaultFeatureConfig());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey (String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(CozyLiving.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>>void register
            (Registerable < ConfiguredFeature < ?, ?>>context,
             RegistryKey < ConfiguredFeature < ?, ?>>key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
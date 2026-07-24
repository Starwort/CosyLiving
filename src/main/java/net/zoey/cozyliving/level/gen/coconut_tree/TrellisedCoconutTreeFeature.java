package net.zoey.cozyliving.level.gen.coconut_tree;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.content.block.CoconutLogBlock;
import net.zoey.cozyliving.content.block.CoconutPlantBlock;


//TODO: THIS IS KINDA SLOPPY, I FEEL LIKE THIS COULD ALL JUST BE PLACED INSIDE THE COCONUT TREE FEATURE FILE...

public class TrellisedCoconutTreeFeature extends CoconutTreeFeature {
    BooleanProperty NATURAL = CoconutLogBlock.NATURAL;

    public TrellisedCoconutTreeFeature() {
        super();
    }

    public static final DeferredHolder<Feature<?>, TrellisedCoconutTreeFeature> TRELLISED_COCONUT_TREE = CozyLiving.FEATURES.register("trellised_coconut_tree",
        TrellisedCoconutTreeFeature::new
    );

    public static void register() {
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        return generateTree(context, false);
    }
}

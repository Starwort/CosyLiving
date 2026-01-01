package net.zoey.cozyliving.level;

import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.grower.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.zoey.cozyliving.level.gen.ModConfiguredFeatures;
import org.jetbrains.annotations.*;

public class CoconutTreeGrower extends AbstractTreeGrower {
    @Override
    protected @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(
        @NotNull RandomSource random,
        boolean hasFlowers
    ) {
        return ModConfiguredFeatures.COCONUT_TREE_KEY;
    }
}

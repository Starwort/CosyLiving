package net.zoey.cozyliving.world.gen.coconut_tree;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.zoey.cozyliving.world.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class CoconutSaplingGenerator extends SaplingGenerator {
    @Override
    protected @Nullable RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.COCONUT_TREE_KEY;
    }
}

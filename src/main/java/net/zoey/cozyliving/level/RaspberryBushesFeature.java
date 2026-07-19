package net.zoey.cozyliving.level;

import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;

import java.util.function.*;

import static net.zoey.cozyliving.content.block.RaspberryBushBlock.*;

public class RaspberryBushesFeature extends Feature<NoneFeatureConfiguration> {
    public RaspberryBushesFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public static final DeferredHolder<Feature<?>, RaspberryBushesFeature> RASPBERRY_BUSHES = CozyLiving.FEATURES.register("raspberry_bushes",
        RaspberryBushesFeature::new
    );

    public static void register() {
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var blockPos = context.origin();
        var random = context.random();
        var mutablePos = new BlockPos.MutableBlockPos();
        int successes = 0;
        int xz_spread = 5;
        int y_spread = 3;
        for (int tries = 0; tries < 33; ++tries) {
            mutablePos.setWithOffset(
                blockPos,
                random.nextInt(xz_spread) - random.nextInt(xz_spread),
                random.nextInt(y_spread) - random.nextInt(y_spread),
                random.nextInt(xz_spread) - random.nextInt(xz_spread)
            );
            if (level.isEmptyBlock(mutablePos) //Is air at position
                && (
                level.isEmptyBlock(mutablePos.above())
                    //Is air at position above (in case of larger bush)
                    && (mutablePos.getY() <= level.getMaxBuildHeight() - 2)
                    //Isn't out of bounds
                    && (
                    level.getBlockState(mutablePos.below()).is(BlockTags.DIRT)
                )
            )) //Block below is suitable
            {
                placeBush(
                    level,
                    mutablePos,
                    random,
                    successes == 0
                );   //Places one of the bush types
                successes++;
            }
        }
        return successes > 0;
    }


    public static void placeBush(
        WorldGenLevel level,
        BlockPos.MutableBlockPos mutablePos,
        RandomSource random,
        boolean isFirst
    ) {
        // If this is the first bush placed, make it fully grown
        // Otherwise, choose a random growth stage
        var age = isFirst ? 9 : random.nextInt(10);
        // stages => 0; 1; 2; 3,0; 3,1; 3,2; 3,3; 3,4; 4,3; 4,4
        if (age > 2) {
            var topAge = age - 3;
            if (topAge > 4) {
                topAge -= 2;
            }
            age = age >= 8 ? 4 : 3;
            level.setBlock(
                mutablePos.above(),
                ModBlocks.RASPBERRY_BUSH
                    .block()
                    .defaultBlockState()
                    .setValue(AGE, topAge)
                    .setValue(HALF, DoubleBlockHalf.UPPER),
                Block.UPDATE_ALL
            );
        }
        level.setBlock(
            mutablePos,
            ModBlocks.RASPBERRY_BUSH
                .block()
                .defaultBlockState()
                .setValue(AGE, age)
                .setValue(HALF, DoubleBlockHalf.LOWER),
            Block.UPDATE_ALL
        );
    }
}
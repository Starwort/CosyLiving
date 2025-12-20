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
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;

import static net.zoey.cozyliving.content.block.RaspberryBushBlock.*;

public class RaspberryBushesFeature extends Feature<NoneFeatureConfiguration> {
    public RaspberryBushesFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    public RaspberryBushesFeature() {
        this(NoneFeatureConfiguration.CODEC);
    }

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> RASPBERRY_BUSHES = CozyLiving.FEATURES.register("raspberry_bushes",
        RaspberryBushesFeature::new
    );

    public static void register(IEventBus modEventBus) {
        var registry = CozyLiving.FEATURES;
        CozyLiving.LOGGER.info("Registering Raspberry Bushes feature");
        registry.register(modEventBus);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var blockPos = context.origin();
        var _config = context.config(); // we don't read this but maybe it's somehow necessary??
        var random = context.random();
        var mutablePos = new BlockPos.MutableBlockPos();
        CozyLiving.LOGGER.info(
            "Trying to place a RaspberryBushesFeature at {} {} {}",
            blockPos.getX(),
            blockPos.getY(),
            blockPos.getZ()
        );
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
        var age = isFirst ? 7 : random.nextInt(8);
        CozyLiving.LOGGER.info(
            "Placing a bush (age {}) at {} {} {}",
            age,
            mutablePos.getX(),
            mutablePos.getY(),
            mutablePos.getZ()
        );
        // stages => 0; 1; 2; 3,0; 3,1; 3,2; 3,3; 4,4
        if (age > 2) {
            var topAge = age - 3;
            age = topAge == 4 ? 4 : 3;
            level.setBlock(
                mutablePos.above(),
                ModBlocks.RASPBERRY_BUSH
                    .block()
                    .defaultBlockState()
                    .setValue(AGE, topAge)
                    .setValue(HALF, DoubleBlockHalf.UPPER),
                // TODO: this feels like the wrong update mode for world-gen
                Block.UPDATE_CLIENTS
            );
            CozyLiving.LOGGER.info(
                "Placed top half (age {}) at {} {} {}",
                topAge,
                mutablePos.above().getX(),
                mutablePos.above().getY(),
                mutablePos.above().getZ()
            );
        }
        level.setBlock(
            mutablePos,
            ModBlocks.RASPBERRY_BUSH
                .block()
                .defaultBlockState()
                .setValue(AGE, age)
                .setValue(HALF, DoubleBlockHalf.LOWER),
            // TODO: this feels like the wrong update mode for world-gen
            Block.UPDATE_CLIENTS
        );
        CozyLiving.LOGGER.info(
            "Placed bottom half (age {}) at {} {} {}",
            age,
            mutablePos.getX(),
            mutablePos.getY(),
            mutablePos.getZ()
        );
    }
}
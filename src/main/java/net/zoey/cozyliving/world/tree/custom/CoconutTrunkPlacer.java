package net.zoey.cozyliving.world.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class CoconutTrunkPlacer extends TrunkPlacer {
    public static final Codec<CoconutTrunkPlacer> CODEC = RecordCodecBuilder.create(objectInstance ->
            fillTrunkPlacerFields(objectInstance).apply(objectInstance, CoconutTrunkPlacer::new));

    public CoconutTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        //return ModTrunkPlacerTypes.COCONUT_TRUNK_PLACER;
        return null;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);
        int genHeight = height + random.nextBetween(firstRandomHeight, firstRandomHeight + 2) + random.nextBetween(secondRandomHeight -1, secondRandomHeight + 1);
        int firstBend = random.nextBetween(2, genHeight-3);
        int secondBend = random.nextBetween(firstBend, genHeight);
        int bendDirection = random.nextBetween(0,3);
        BlockPos currentPos = startPos;

        for(int i = 0; i < genHeight; i++){
            getAndSetState(world, replacer, random, startPos.up(i), config);
            if(i == firstBend || i == secondBend){
                switch (bendDirection){
                    case 0:
                        currentPos.add(-1, 0, 0);
                        break;
                    case 1:
                        currentPos.add(1, 0, 0);
                    case 2:
                        currentPos.add(0, 0, -1);
                    default:
                        currentPos.add(0, 0, 1);
                }
            }
        }

        return ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(genHeight), 0, false));
    }
}

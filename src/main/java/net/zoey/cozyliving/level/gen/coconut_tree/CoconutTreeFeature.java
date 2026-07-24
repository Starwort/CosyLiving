package net.zoey.cozyliving.level.gen.coconut_tree;

import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.block.*;

import java.util.Random;

public class CoconutTreeFeature extends Feature<NoneFeatureConfiguration> {
    BooleanProperty NATURAL = CoconutLogBlock.NATURAL;

    public CoconutTreeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    public static final DeferredHolder<Feature<?>, CoconutTreeFeature> COCONUT_TREE = CozyLiving.FEATURES.register("coconut_tree",
        CoconutTreeFeature::new
    );

    public static void register() {
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {

        return generateTree(context, true);
    }

    public boolean generateTree(FeaturePlaceContext<NoneFeatureConfiguration> context, boolean bendy){

        WorldGenLevel structureWorldAccess = context.level(); //Get access to world
        BlockPos blockPos = context.origin();                 //Get position to spawn feature
        RandomSource random = context.random();               //Get access to structure random

        //DON'T GENERATE IF WRONG SOIL
        if (!(structureWorldAccess.getBlockState(blockPos.below()).is(BlockTags.DIRT)
                || (structureWorldAccess.getBlockState(blockPos.below()).is(BlockTags.SAND)))) {
            return false;
        }


        //MAP OUT TREE TRUNK
        int genHeight = random.nextInt(7, 11);
        int firstBend = genHeight / 2; //First bend occurs halfway through the trunk
        int secondBend = ((genHeight - firstBend) / 2) + firstBend; //Second bend occurs halfway between first bend and top
        int bendDirection = random.nextInt(0, 3);


        //GENERATE LOG
        for (int i = 0; i < genHeight; i++) {
            if ((structureWorldAccess.getBlockState(blockPos).isAir() || structureWorldAccess.getBlockState(blockPos).is(BlockTags.LEAVES))) {
                structureWorldAccess.setBlock(blockPos, ModBlocks.COCONUT_LOG.block().defaultBlockState().setValue(NATURAL, true), 2);
            }
            if ((i == firstBend || i == secondBend) && bendy) {
                blockPos = switch (bendDirection) {
                    case 0 -> blockPos.north();
                    case 1 -> blockPos.east();
                    case 2 -> blockPos.south();
                    default -> blockPos.west();
                };
            }
            blockPos = blockPos.above();
        }


        //GENERATE TOP
        BlockPos printingPos;
        printingPos = blockPos.offset(-2, 2, -2);
        printLayer(canopyLayer3, structureWorldAccess, printingPos, 5);
        printingPos = blockPos.offset(-3, 1, -3);
        printLayer(canopyLayer2, structureWorldAccess, printingPos, 7);
        printingPos = blockPos.offset(-4, 0, -4);
        printLayer(canopyLayer1, structureWorldAccess, printingPos, 9);
        return true;
    }

    private void printLayer(char[][] layer, WorldGenLevel structureWorldAccess, BlockPos printingPos, int layerSize) {
        BlockState currentBlock;
        BlockPos pos = printingPos;
        for (char[] array : layer) {
            for (char block : array) {
                currentBlock = switch (block) {
                    case 'a' -> Blocks.AIR.defaultBlockState();
                    case 'l' -> ModBlocks.COCONUT_LEAVES.block().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1);
                    case 'k' -> ModBlocks.COCONUT_LEAVES_CORNER.block().defaultBlockState();
                    case 'c' ->
                        ModBlocks.COCONUT_PLANT.block().defaultBlockState().setValue(CoconutPlantBlock.AGE, structureWorldAccess.getRandom().nextInt(0, 3));
                    case 't' -> ModBlocks.COCONUT_LOG.block().defaultBlockState().setValue(NATURAL, Boolean.TRUE);
                    case 'x' ->
                        ModBlocks.COCONUT_LOG.block().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.X).setValue(NATURAL, Boolean.TRUE);
                    case 'z' ->
                        ModBlocks.COCONUT_LOG.block().defaultBlockState().setValue(BlockStateProperties.AXIS, Direction.Axis.Z).setValue(NATURAL, Boolean.TRUE);
                    default ->
                        Blocks.REDSTONE_BLOCK.defaultBlockState(); //just checks to make sure nothing FUCKS UP lol
                };
                //If the position is empty or leaves, place the block
                if ((currentBlock != Blocks.AIR.defaultBlockState()) && (structureWorldAccess.getBlockState(pos).isAir() || structureWorldAccess.getBlockState(pos).is(BlockTags.LEAVES))) {
                    structureWorldAccess.setBlock(pos, currentBlock, 2);
                }

                pos = pos.offset(1, 0, 0);
            }
            pos = pos.offset(-layerSize, 0, 1);
        }
    }



    //a = air, l = leaves, c = coconut, t = trunk, x = x facing trunk, z = z facing trunk
    char[][] canopyLayer1 = {
        {'a', 'a', 'a', 'a', 'k', 'a', 'a', 'a', 'a'},
        {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
        {'a', 'a', 'k', 'a', 'c', 'a', 'k', 'a', 'a'},
        {'a', 'a', 'a', 'c', 'a', 'c', 'a', 'a', 'a'},
        {'k', 'a', 'c', 'a', 't', 'a', 'c', 'a', 'k'},
        {'a', 'a', 'a', 'c', 'a', 'c', 'a', 'a', 'a'},
        {'a', 'a', 'k', 'a', 'c', 'a', 'k', 'a', 'a'},
        {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
        {'a', 'a', 'a', 'a', 'k', 'a', 'a', 'a', 'a'}};

    char[][] canopyLayer2 = {
        {'a', 'a', 'a', 'l', 'a', 'a', 'a'},
        {'a', 'a', 'l', 'l', 'a', 'a', 'a'},
        {'a', 'a', 'l', 'z', 'l', 'l', 'a'},
        {'l', 'l', 'x', 't', 'x', 'l', 'l'},
        {'a', 'l', 'l', 'z', 'l', 'a', 'a'},
        {'a', 'a', 'a', 'l', 'l', 'a', 'a'},
        {'a', 'a', 'a', 'l', 'a', 'a', 'a'}};

    char[][] canopyLayer3 = {
        {'a', 'a', 'l', 'a', 'a'},
        {'a', 'a', 'l', 'a', 'a'},
        {'l', 'l', 'l', 'l', 'l'},
        {'a', 'a', 'l', 'a', 'a'},
        {'a', 'a', 'l', 'a', 'a'}};

}

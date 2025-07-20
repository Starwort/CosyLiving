package net.zoey.cozyliving.world.gen.coconut_tree;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.block.custom.CoconutPlantBlock;

import static net.minecraft.block.PillarBlock.AXIS;
import static net.zoey.cozyliving.block.ModBlocks.COCONUT_PLANT;
import static net.zoey.cozyliving.block.custom.CoconutLogBlock.NATURAL;

public class CoconutTreeFeature extends Feature<DefaultFeatureConfig> {
    public CoconutTreeFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld(); //Get access to world
        BlockPos blockPos = context.getOrigin();                        //Get position to spawn feature


        if (!(structureWorldAccess.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)
        || (structureWorldAccess.getBlockState(blockPos.down()).isIn(BlockTags.SAND)))){
            return false;
        }

        context.getConfig();                                            //Honestly I have no idea what this is for
        Random random = context.getRandom();                            //Get random generator based on world seed
        //BlockPos.Mutable mutable = blockPos.mutableCopy();              //?????
        int genHeight = random.nextBetween(7, 11);
        int firstBend = genHeight/2; //First bend occurs halfway through the trunk
        int secondBend = ((genHeight-firstBend)/2)+firstBend; //Second bend occurs halfway between first bend and top
        int bendDirection = random.nextBetween(0,3);

        //GENERATE THE LOG
        for(int i = 0; i < genHeight; i++){
            if (structureWorldAccess.getBlockState(blockPos).isAir()){
                structureWorldAccess.setBlockState(blockPos, ModBlocks.COCONUT_LOG.getDefaultState().with(NATURAL, true), 2);
            }
            if(i == firstBend || i == secondBend){
                blockPos = switch (bendDirection) {
                    case 0 -> blockPos.north();
                    case 1 -> blockPos.east();
                    case 2 -> blockPos.south();
                    default -> blockPos.west();
                };
            }
            blockPos = blockPos.up();
        }



        //GENERATE THE TOP
        //gotta do it backwards so the coconuts don't break
        BlockPos printingPos;
        printingPos = blockPos.add(-2, 2,-2);
        printLayer(canopyLayer3, structureWorldAccess, printingPos, 5);
        printingPos = blockPos.add(-3, 1,-3);
        printLayer(canopyLayer2, structureWorldAccess, printingPos, 7);
        printingPos = blockPos.add(-4, 0,-4);
        printLayer(canopyLayer1, structureWorldAccess, printingPos, 9);

        return true;
    }

    private void printLayer(char[][] layer, StructureWorldAccess structureWorldAccess, BlockPos printingPos, int layerSize){
        BlockState currentBlock;
        BlockPos pos = printingPos;
        for (char[] array : layer) {
            for (char block : array){
                currentBlock = switch (block){
                    case 'a' -> Blocks.AIR.getDefaultState();
                    case 'l' -> ModBlocks.COCONUT_LEAVES.getDefaultState();
                    case 'k' -> ModBlocks.COCONUT_LEAVES_CORNER.getDefaultState();
                    case 'c' -> COCONUT_PLANT.getDefaultState().with(CoconutPlantBlock.AGE, structureWorldAccess.getRandom().nextBetween(0,3));
                    case 't' -> ModBlocks.COCONUT_LOG.getDefaultState().with(NATURAL, Boolean.TRUE);
                    case 'x' -> ModBlocks.COCONUT_LOG.getDefaultState().with(AXIS, Direction.Axis.X).with(NATURAL, Boolean.TRUE);
                    case 'z' -> ModBlocks.COCONUT_LOG.getDefaultState().with(AXIS, Direction.Axis.Z).with(NATURAL, Boolean.TRUE);
                    default -> Blocks.REDSTONE_BLOCK.getDefaultState();
                };
                //If the position is empty, place the block
                if (structureWorldAccess.getBlockState(printingPos).isAir()){
                    structureWorldAccess.setBlockState(pos, currentBlock, 2);
                }

                pos = pos.add(1,0,0);
            }
            pos = pos.add(-layerSize,0,1);
        }
    }




    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }

    public static final Feature<DefaultFeatureConfig> COCONUT_TREE;
    static{ COCONUT_TREE = register("coconut_tree", new CoconutTreeFeature(DefaultFeatureConfig.CODEC));}

    public static void registerCoconutTreeFeature() {
        CozyLiving.LOGGER.info("Registering Coconut Tree Feature for " + CozyLiving.MOD_ID);
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

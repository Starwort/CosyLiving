package net.zoey.cozyliving.world.gen.raspberry_bush_patches;

import com.mojang.serialization.Codec;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;

import static net.zoey.cozyliving.block.custom.RaspberryBushBlock.AGE;
import static net.zoey.cozyliving.block.custom.RaspberryBushBlock.HALF;

public class RaspberryBushesFeature extends Feature<DefaultFeatureConfig> {
    public RaspberryBushesFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld(); //Get access to world
        BlockPos blockPos = context.getOrigin();                        //Get position to spawn feature
        context.getConfig();                                            //Honestly I have no idea what this is for
        Random random = context.getRandom();                            //Get random generator based on world seed
        BlockPos.Mutable mutable = new BlockPos.Mutable();              //?????
        int successes = 0;
        int xz_spread = 5;
        int y_spread = 3;
        for(int tries = 0; tries < 33; ++tries) {
            mutable.set(blockPos, random.nextInt(xz_spread) - random.nextInt(xz_spread), random.nextInt(y_spread) - random.nextInt(y_spread), random.nextInt(xz_spread) - random.nextInt(xz_spread));
            if (structureWorldAccess.isAir(mutable) //Is air at position
                    && (structureWorldAccess.isAir(mutable.up()) //Is air at position above (in case of larger bush)
                    && (mutable.getY() <= 318)      //Isn't out of bounds
                    && (structureWorldAccess.getBlockState(mutable.down()).isIn(BlockTags.DIRT)))) //Block below is suitable
            {
                PlaceBush(structureWorldAccess, mutable, random, successes==0);   //Places one of the bush types
                successes++;
            }
        }
        return successes > 0;
    }

    public static void PlaceBush(StructureWorldAccess structureWorldAccess, BlockPos.Mutable mutable, Random random, Boolean isFirst) {

        if (isFirst) { //If this is the first bush placed, make it fully grown
            structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 4), 2);
            structureWorldAccess.setBlockState(mutable.up(), ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 4).with(HALF, DoubleBlockHalf.UPPER), 2);
        } else { //Otherwise, select from this list of options:
            switch (random.nextInt(9)) {
                case 0:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 0), 2);
                    break;
                case 1:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 1), 2);
                    break;
                case 2:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 2), 2);
                    break;
                case 3:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 3), 2);
                    structureWorldAccess.setBlockState(mutable.up(), ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 0).with(HALF, DoubleBlockHalf.UPPER), 2);
                    break;
                case 4:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 3), 2);
                    structureWorldAccess.setBlockState(mutable.up(), ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 1).with(HALF, DoubleBlockHalf.UPPER), 2);
                    break;
                case 5:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 3), 2);
                    structureWorldAccess.setBlockState(mutable.up(), ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 2).with(HALF, DoubleBlockHalf.UPPER), 2);
                    break;
                case 6:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 3), 2);
                    structureWorldAccess.setBlockState(mutable.up(), ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 3).with(HALF, DoubleBlockHalf.UPPER), 2);
                    break;
                case 7:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 3), 2);
                    structureWorldAccess.setBlockState(mutable.up(), ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 4).with(HALF, DoubleBlockHalf.UPPER), 2);
                    break;
                case 8:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 4), 2);
                    structureWorldAccess.setBlockState(mutable.up(), ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 3).with(HALF, DoubleBlockHalf.UPPER), 2);
                    break;
                default:
                    structureWorldAccess.setBlockState(mutable, ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 4), 2);
                    structureWorldAccess.setBlockState(mutable.up(), ModBlocks.RASPBERRY_BUSH.getDefaultState().with(AGE, 4).with(HALF, DoubleBlockHalf.UPPER), 2);
                    break;
            }
        }
    }





    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }

    public static final Feature<DefaultFeatureConfig> RASPBERRY_BUSHES;
    static{ RASPBERRY_BUSHES = register("raspberry_bushes", new RaspberryBushesFeature(DefaultFeatureConfig.CODEC));}

    public static void registerRaspberryBushesFeature() {
        CozyLiving.LOGGER.info("Registering Raspberry Bushes Feature for " + CozyLiving.MOD_ID);
    }
}

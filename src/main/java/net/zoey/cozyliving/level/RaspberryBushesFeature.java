package net.zoey.cozyliving.level;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.content.ModItems;


import static net.zoey.cozyliving.content.block.RaspberryBushBlock.AGE;
import static net.zoey.cozyliving.content.block.RaspberryBushBlock.HALF;

public class RaspberryBushesFeature extends Feature<NoneFeatureConfiguration> {
    public RaspberryBushesFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level(); //Get access to world
        BlockPos blockPos = context.origin();                        //Get position to spawn feature
        context.config();                                            //Honestly I have no idea what this is for
        RandomSource random = context.random();                            //Get random generator based on world seed
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();              //?????
        int successes = 0;
        int xz_spread = 5;
        int y_spread = 3;
        for(int tries = 0; tries < 33; ++tries) {
            mutable.set(random.nextInt(xz_spread) - random.nextInt(xz_spread), random.nextInt(y_spread) - random.nextInt(y_spread), random.nextInt(xz_spread) - random.nextInt(xz_spread));
            if (worldgenlevel.isEmptyBlock(mutable) //Is air at position
                    && (worldgenlevel.isEmptyBlock(mutable.above()) //Is air at position above (in case of larger bush)
                    && (mutable.getY() <= worldgenlevel.getMaxBuildHeight()-2)      //Isn't out of bounds
                    && (worldgenlevel.getBlockState(mutable.below()).is(BlockTags.DIRT)))) //Block below is suitable
            {
                PlaceBush(worldgenlevel, mutable, random, successes==0);   //Places one of the bush types
                successes++;
            }
        }
        return successes > 0;
    }


    public static void PlaceBush(WorldGenLevel worldgenlevel, BlockPos.MutableBlockPos mutable, RandomSource random, Boolean isFirst) {

        if (isFirst) { //If this is the first bush placed, make it fully grown
            worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,4).setValue(HALF,DoubleBlockHalf.LOWER), 2);
            worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,4).setValue(HALF,DoubleBlockHalf.UPPER), 2);
        } else { //Otherwise, select from this list of options:
            switch (random.nextInt(9)) {
                case 0:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,0).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    break;
                case 1:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,1).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    break;
                case 2:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,2).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    break;
                case 3:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,3).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,0).setValue(HALF,DoubleBlockHalf.UPPER), 2);
                    break;
                case 4:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,3).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,1).setValue(HALF,DoubleBlockHalf.UPPER), 2);
                    break;
                case 5:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,3).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,2).setValue(HALF,DoubleBlockHalf.UPPER), 2);
                    break;
                case 6:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,3).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,3).setValue(HALF,DoubleBlockHalf.UPPER), 2);
                    break;
                case 7:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,3).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,4).setValue(HALF,DoubleBlockHalf.UPPER), 2);
                    break;
                case 8:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,4).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,3).setValue(HALF,DoubleBlockHalf.UPPER), 2);
                    break;
                default:
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,4).setValue(HALF,DoubleBlockHalf.LOWER), 2);
                    worldgenlevel.setBlock(mutable, ModBlocks.RASPBERRY_BUSH.block().defaultBlockState().setValue(AGE,4).setValue(HALF,DoubleBlockHalf.UPPER), 2);
                    break;
            }
        }
    }
    /*
    //TODO: BuiltInRegistries.FEATURE is deprecated.
    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String pKey, F pValue) {
        return Registry.register(ForgeRegistries.FEATURES, pKey, pValue);
    }*/
    /*public static final Feature<NoneFeatureConfiguration> RASPBERRY_BUSHES;
    static{ RASPBERRY_BUSHES = register("raspberry_bushes", new RaspberryBushesFeature(NoneFeatureConfiguration.CODEC));}

    public static void register(IEventBus modEventBus) {
        ModItems.Food.register();
        CozyLiving.LOGGER.info("Registering raspberry bush feature");
        CozyLiving.FEATURES.register(modEventBus);

    }*/
    /*public static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create(ForgeRegistries.FEATURES,
            CozyLiving.MODID
    );*/

    /*public static void register(IEventBus modEventBus) {

        CozyLiving.FEATURES.register(modEventBus);
    }*/

    //TODO: idk what i'm doing
    //public static final RegistryObject<Feature<?>> RASPBERRY_BUSHES = REGISTER.register(
     //       "raspberry_bushes", ForgeRegistries.FEATURES);

}
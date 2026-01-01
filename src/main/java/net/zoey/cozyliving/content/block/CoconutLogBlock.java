package net.zoey.cozyliving.content.block;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.content.ModSounds;
import org.jetbrains.annotations.NotNull;

public class CoconutLogBlock extends FlammableRotatedPillarBlock{

    public static final BooleanProperty NATURAL = BooleanProperty.create("natural");

    public CoconutLogBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(NATURAL);
        builder.add(AXIS);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void attack(BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player) {

        //CozyLiving.LOGGER.info("Started chain");
        boolean foundTop = false;
        BlockPos position = pos;
        BlockState blockState;
        BlockState naturalWood = ModBlocks.COCONUT_LOG.block().defaultBlockState().setValue(NATURAL, Boolean.TRUE);

        while(!foundTop){
            //CozyLiving.LOGGER.info("Current position: " + position);
            blockState = level.getBlockState(position.above());
            //CozyLiving.LOGGER.info("Current block: " + blockState.toString());
            if (blockState == naturalWood){
                position = position.above();
            } else if (level.getBlockState(position.above().north()) == naturalWood) {
                position = position.above().north();
            } else if (level.getBlockState(position.above().east()) == naturalWood) {
                position = position.above().east();
            } else if (level.getBlockState(position.above().south()) == naturalWood) {
                position = position.above().south();
            } else if (level.getBlockState(position.above().west()) == naturalWood) {
                position = position.above().west();
            } else {
                foundTop = true;
                //CozyLiving.LOGGER.info("Top position found: " + position);
            }
        }
        if (level.getBlockState(position.above()).is(ModBlocks.COCONUT_LEAVES.block())){
            level.playSound(null, position, ModSounds.LEAVES_RUSTLE.sound(), SoundSource.BLOCKS, 0.05F, 0.8F + level.random.nextFloat() * 0.4F);
            level.addDestroyBlockEffect(position.below(), ModBlocks.COCONUT_LEAVES.block().defaultBlockState());
            level.scheduleTick(position.offset(2, -1, 0), ModBlocks.COCONUT_PLANT.block(), 1);
            level.scheduleTick(position.offset(1, -1, 1), ModBlocks.COCONUT_PLANT.block(), 3);
            level.scheduleTick(position.offset(0, -1, 2), ModBlocks.COCONUT_PLANT.block(), 5);
            level.scheduleTick(position.offset(-1, -1, 1), ModBlocks.COCONUT_PLANT.block(), 7);
            level.scheduleTick(position.offset(-2, -1, 0), ModBlocks.COCONUT_PLANT.block(), 9);
            level.scheduleTick(position.offset(-1, -1, -1), ModBlocks.COCONUT_PLANT.block(), 11);
            level.scheduleTick(position.offset(0, -1, -2), ModBlocks.COCONUT_PLANT.block(), 13);
            level.scheduleTick(position.offset(1, -1, -1), ModBlocks.COCONUT_PLANT.block(), 15);
            //CozyLiving.LOGGER.info("SUCCEEDED!");
        } else {
            //CozyLiving.LOGGER.info("FAILED!");
        }
        super.attack(state, level, pos, player);
    }
}

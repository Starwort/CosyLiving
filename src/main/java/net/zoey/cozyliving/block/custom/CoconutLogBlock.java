package net.zoey.cozyliving.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.sound.ModSounds;

public class CoconutLogBlock extends PillarBlock {

    public static final BooleanProperty NATURAL = BooleanProperty.of("natural");

    public CoconutLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(NATURAL, Boolean.FALSE).with(AXIS, Direction.Axis.Y));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NATURAL);
        builder.add(AXIS);
        //super.appendProperties(builder);
    }

    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        //CozyLiving.LOGGER.info("Started chain");
        boolean foundTop = false;
        BlockPos position = pos;
        BlockState blockState;
        BlockState naturalWood = ModBlocks.COCONUT_LOG.getDefaultState().with(NATURAL, Boolean.TRUE);

        while(!foundTop){
            //CozyLiving.LOGGER.info("Current position: " + position);
            blockState = world.getBlockState(position.up());
            //CozyLiving.LOGGER.info("Current block: " + blockState.toString());
            if (blockState == naturalWood){
                position = position.up();
            } else if (world.getBlockState(position.up().north()) == naturalWood) {
                position = position.up().north();
            } else if (world.getBlockState(position.up().east()) == naturalWood) {
                position = position.up().east();
            } else if (world.getBlockState(position.up().south()) == naturalWood) {
                position = position.up().south();
            } else if (world.getBlockState(position.up().west()) == naturalWood) {
                position = position.up().west();
            } else {
                foundTop = true;
                //CozyLiving.LOGGER.info("Top position found: " + position);
            }
        }
        if (world.getBlockState(position.up()).isOf(ModBlocks.COCONUT_LEAVES)){
            world.playSound(null, position, ModSounds.LEAVES_RUSTLE, SoundCategory.BLOCKS, 0.05F, 0.8F + world.random.nextFloat() * 0.4F);
            world.addBlockBreakParticles(position.down(), ModBlocks.COCONUT_LEAVES.getDefaultState());
            world.scheduleBlockTick(position.add(2, -1, 0), ModBlocks.COCONUT_PLANT, 1);
            world.scheduleBlockTick(position.add(1, -1, 1), ModBlocks.COCONUT_PLANT, 3);
            world.scheduleBlockTick(position.add(0, -1, 2), ModBlocks.COCONUT_PLANT, 5);
            world.scheduleBlockTick(position.add(-1, -1, 1), ModBlocks.COCONUT_PLANT, 7);
            world.scheduleBlockTick(position.add(-2, -1, 0), ModBlocks.COCONUT_PLANT, 9);
            world.scheduleBlockTick(position.add(-1, -1, -1), ModBlocks.COCONUT_PLANT, 11);
            world.scheduleBlockTick(position.add(0, -1, -2), ModBlocks.COCONUT_PLANT, 13);
            world.scheduleBlockTick(position.add(1, -1, -1), ModBlocks.COCONUT_PLANT, 15);
            //CozyLiving.LOGGER.info("SUCCEEDED!");
        } else {
            //CozyLiving.LOGGER.info("FAILED!");
        }



        //Trigger all the coconuts


        //Hope this works! :p
        super.onBlockBreakStart(state, world, pos, player);
    }
}

package net.zoey.cozyliving.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.zoey.cozyliving.block.ModBlocks;

public class CoconutLeavesCornerBlock extends Block {
    public CoconutLeavesCornerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldDecay(world, pos)) {
            dropStacks(state, world, pos);
            world.removeBlock(pos, false);
        }
    }

    protected boolean shouldDecay(ServerWorld world, BlockPos pos){

        if (world.getBlockState(pos.up().north()).isOf(ModBlocks.COCONUT_LEAVES)){
            return false;
        } else if (world.getBlockState(pos.up().east()).isOf(ModBlocks.COCONUT_LEAVES)){
            return false;
        } else if (world.getBlockState(pos.up().south()).isOf(ModBlocks.COCONUT_LEAVES)){
            return false;
        } else if (world.getBlockState(pos.up().west()).isOf(ModBlocks.COCONUT_LEAVES)){
            return false;
        }
        return true;
    }


}

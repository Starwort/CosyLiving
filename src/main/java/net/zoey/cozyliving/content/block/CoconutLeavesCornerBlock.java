package net.zoey.cozyliving.content.block;


import net.minecraft.core.BlockPos;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.zoey.cozyliving.content.ModBlocks;

import java.util.Random;

public class CoconutLeavesCornerBlock extends Block {
    public CoconutLeavesCornerBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) { return true; }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (this.shouldDecay(pLevel, pPos)) {
            dropResources(pState, pLevel, pPos);
            pLevel.removeBlock(pPos, false);
        }
    }

    protected boolean shouldDecay(ServerLevel pLevel, BlockPos pPos){

        if (pLevel.getBlockState(pPos.above().north()).is(ModBlocks.COCONUT_LEAVES.block())){
            return false;
        } else if (pLevel.getBlockState(pPos.above().east()).is(ModBlocks.COCONUT_LEAVES.block())){
            return false;
        } else if (pLevel.getBlockState(pPos.above().south()).is(ModBlocks.COCONUT_LEAVES.block())){
            return false;
        } else if (pLevel.getBlockState(pPos.above().west()).is(ModBlocks.COCONUT_LEAVES.block())){
            return false;
        }
        return true;
    }


    //TODO: see if pick block functionality can return a regular leaf block ?
}

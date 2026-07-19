package net.zoey.cozyliving.content.block;


import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class CoconutLeavesCornerBlock extends Block {
    public CoconutLeavesCornerBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return true;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource pRandom) {
        if (this.shouldDecay(level, pos)) {
            dropResources(state, level, pos);
            level.removeBlock(pos, false);
        }
    }

    protected boolean shouldDecay(ServerLevel level, BlockPos pos) {
        return !(
            level.getBlockState(pos.above().north()).is(ModBlocks.COCONUT_LEAVES.block())
            || level.getBlockState(pos.above().east()).is(ModBlocks.COCONUT_LEAVES.block())
            || level.getBlockState(pos.above().south()).is(ModBlocks.COCONUT_LEAVES.block())
            || level.getBlockState(pos.above().west()).is(ModBlocks.COCONUT_LEAVES.block())
        );
    }

    //TODO: see if pick block functionality can return a regular leaf block ?
}

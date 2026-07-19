package net.zoey.cozyliving.content.block;

import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class CoconutLogBlock extends FlammableRotatedPillarBlock {
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
    public void attack(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player) {
        boolean foundTop = false;
        BlockPos position = pos;
        BlockState blockState;
        BlockState naturalWood = ModBlocks.COCONUT_LOG.block().defaultBlockState().setValue(NATURAL, Boolean.TRUE);

        while (!foundTop) {
            blockState = level.getBlockState(position.above());
            if (blockState == naturalWood) {
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
            }
        }
        if (level.getBlockState(position.above()).is(ModBlocks.COCONUT_LEAVES.block())) {
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
        }
        super.attack(state, level, pos, player);
    }
}

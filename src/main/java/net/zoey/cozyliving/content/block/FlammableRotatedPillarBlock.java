package net.zoey.cozyliving.content.block;

import net.minecraft.core.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.neoforged.neoforge.common.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class FlammableRotatedPillarBlock extends RotatedPillarBlock {
    public FlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos,
        @NotNull Direction direction
    ) {
        return true;
    }

    @Override
    public int getFlammability(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos,
        @NotNull Direction direction
    ) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos,
        @NotNull Direction direction
    ) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(
        @NotNull BlockState state,
        @NotNull UseOnContext context,
        @NotNull ItemAbility itemAbility,
        boolean simulate
    ) {
        if (itemAbility == ItemAbilities.AXE_STRIP) {
            if (state.is(ModBlocks.COCONUT_LOG.block())) {
                return ModBlocks.STRIPPED_COCONUT_LOG
                    .block()
                    .defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS));
            }

            if (state.is(ModBlocks.COCONUT_WOOD.block())) {
                return ModBlocks.STRIPPED_COCONUT_WOOD
                    .block()
                    .defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}

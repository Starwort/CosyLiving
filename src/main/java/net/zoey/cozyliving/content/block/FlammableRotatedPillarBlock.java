package net.zoey.cozyliving.content.block;

import net.minecraft.core.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraftforge.common.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class FlammableRotatedPillarBlock extends RotatedPillarBlock {
    public FlammableRotatedPillarBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(
        BlockState state,
        BlockGetter level,
        BlockPos pos,
        Direction direction
    ) {
        return true;
    }

    @Override
    public int getFlammability(
        BlockState state,
        BlockGetter level,
        BlockPos pos,
        Direction direction
    ) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(
        BlockState state,
        BlockGetter level,
        BlockPos pos,
        Direction direction
    ) {
        return 5;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(
        BlockState state,
        UseOnContext context,
        ToolAction toolAction,
        boolean simulate
    ) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            if (state.is(ModBlocks.COCONUT_LOG.get())) {
                return ModBlocks.STRIPPED_COCONUT_LOG
                    .get()
                    .defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS));
            }

            if (state.is(ModBlocks.COCONUT_WOOD.get())) {
                return ModBlocks.STRIPPED_COCONUT_WOOD
                    .get()
                    .defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}

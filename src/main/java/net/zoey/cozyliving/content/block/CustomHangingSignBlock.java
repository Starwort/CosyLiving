package net.zoey.cozyliving.content.block;

import net.minecraft.core.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.zoey.cozyliving.content.block.entity.*;
import org.jetbrains.annotations.*;

public class CustomHangingSignBlock extends CeilingHangingSignBlock {
    public CustomHangingSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public @NotNull BlockEntity newBlockEntity(
        @NotNull BlockPos pos,
        @NotNull BlockState state
    ) {
        return new CustomHangingSignBE(pos, state);
    }
}

package net.zoey.cozyliving.content.block.entity;

import net.minecraft.core.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class CustomHangingSignBE extends HangingSignBlockEntity {
    public CustomHangingSignBE(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ModBlocks.Entities.HANGING_SIGN.get();
    }
}

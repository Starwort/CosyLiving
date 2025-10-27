package net.zoey.cozyliving.content.block.entity;

import net.minecraft.core.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class CustomSignBE extends SignBlockEntity {
    public CustomSignBE(BlockPos pos, BlockState state) {
        super(ModBlocks.Entities.SIGN.get(), pos, state);
    }

    @Override
    public @NotNull BlockEntityType<?> getType() {
        return ModBlocks.Entities.SIGN.get();
    }
}

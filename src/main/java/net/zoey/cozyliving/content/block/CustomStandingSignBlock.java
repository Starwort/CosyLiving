package net.zoey.cozyliving.content.block;

import net.minecraft.core.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.zoey.cozyliving.content.block.entity.*;

public class CustomStandingSignBlock extends StandingSignBlock {
    public CustomStandingSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CustomSignBE(pos, state);
    }
}

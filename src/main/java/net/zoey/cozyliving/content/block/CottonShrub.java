package net.zoey.cozyliving.content.block;

import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.*;

public class CottonShrub extends BushBlock {
    MapCodec<? extends BushBlock> CODEC = simpleCodec(CottonShrub::new);

    CottonShrub(Properties properties) {
        super(properties);
    }

    public CottonShrub() {
        this(Properties
            .of()
            .mapColor(MapColor.QUARTZ)
            .noCollission()
            .instabreak()
            .sound(SoundType.CROP)
            .offsetType(OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
            .ignitedByLava());
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    public @NotNull VoxelShape getShape(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos,
        @NotNull CollisionContext context
    ) {
        return box(3, 0, 3, 13, 13, 13);
    }
}

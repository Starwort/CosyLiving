package net.zoey.cozyliving.content.block;

import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.shapes.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class CoconutBlock extends FallingBlock implements Fallable {
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;

    public static final int COCONUT_FALL_MAX_DAMAGE = 6;
    public static final float COCONUT_FALL_DAMAGE = 1;

    public CoconutBlock() {
        super(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.COLOR_BROWN)
            .sound(ModSounds.COCONUT_SOUNDS)
            .instrument(NoteBlockInstrument.CUSTOM_HEAD)
            .noOcclusion()
            .destroyTime(.3f));

        registerDefaultState(defaultBlockState().setValue(PERSISTENT, true));
    }

    @Override
    public @NotNull DamageSource getFallDamageSource(@NotNull Entity entity) {
        return ModDamageTypes.COCONUT_BONK.asSource(entity.level());
    }

    @Override
    protected void falling(FallingBlockEntity entity) {
        entity.setHurtsEntities(COCONUT_FALL_DAMAGE, COCONUT_FALL_MAX_DAMAGE);
        entity.dropItem = true;
    }

    @Override
    public void onLand(
        @NotNull Level level,
        @NotNull BlockPos pos,
        @NotNull BlockState state,
        @NotNull BlockState replaceableState,
        FallingBlockEntity fallingBlock
    ) {
        if (!fallingBlock.isSilent()) {
            level.playSound(
                null,
                pos,
                ModSounds.COCONUT_BONK.sound(),
                SoundSource.BLOCKS,
                1f,
                0.8f + level.random.nextFloat() * 0.4f
            );
        }
    }

    @Override
    public void onBrokenAfterFall(
        @NotNull Level level,
        @NotNull BlockPos pos,
        FallingBlockEntity fallingBlock
    ) {
        if (!fallingBlock.isSilent()) {
            level.playSound(
                null,
                pos,
                ModSounds.COCONUT_BONK.sound(),
                SoundSource.BLOCKS,
                1f,
                0.8f + level.random.nextFloat() * 0.4f
            );
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos,
        @NotNull CollisionContext context
    ) {
        return box(4, 0, 4, 12, 8, 12);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(
        @NotNull BlockState state,
        @NotNull LevelReader level,
        BlockPos pos
    ) {
        return canSupportCenter(level, pos.below(), Direction.UP) || level
            .getBlockState(pos.below())
            .isAir();
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PERSISTENT);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(
        @NotNull BlockState _state,
        @NotNull ServerLevel level,
        @NotNull BlockPos pos,
        @NotNull RandomSource random
    ) {
        // scan up to 32 blocks above self to find a coconut plant
        // if we can't find one, the coconut will decay
        var toCheck = pos.above();
        for (int i = 0; i < 32; i++) {
            var state = level.getBlockState(toCheck);
            if (state.is(ModBlocks.COCONUT_PLANT.block())) {
                return;
            } else if (state.isAir()) {
                toCheck = toCheck.above();
            } else {
                break;
            }
        }
        // didn't find a coconut plant, we die now
        level.destroyBlock(pos, true);
    }
}

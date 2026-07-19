package net.zoey.cozyliving.content.block;

import com.mojang.serialization.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.item.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.shapes.*;
import net.minecraft.world.ticks.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class CoconutPlantBlock extends BushBlock implements BonemealableBlock {
    MapCodec<? extends BushBlock> CODEC = simpleCodec(CoconutPlantBlock::new);
    public static final int MAX_AGE = 3;
    public static final int RIPE_AGE = 2;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    private static final VoxelShape[] SHAPES = new VoxelShape[] {
        box(6, 12, 6, 10, 16, 10),
        box(5, 10, 5, 11, 16, 11),
        box(4, 8, 4, 12, 16, 12),
        box(4, 8, 4, 12, 16, 12),
    };

    public CoconutPlantBlock() {
        this(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.COLOR_BROWN)
            .sound(ModSounds.COCONUT_SOUNDS)
            .noOcclusion()
            .destroyTime(.3f));
    }

    public CoconutPlantBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public @NotNull VoxelShape getShape(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos,
        @NotNull CollisionContext context
    ) {
        int age = state.getValue(AGE);
        if (age > MAX_AGE) {
            // this prevents literally 20 crashes on launch and I have no idea
            // why they would happen. For some reason this is called on an
            // invalid state 20 times just before the registries are frozen
            CozyLiving.LOGGER.error(
                "Age of coconut plant in state {} at {} had invalid age of {}",
                state,
                pos,
                age
            );
            age = MAX_AGE;
        }
        return SHAPES[age];
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return true;
    }

    @Override
    public void randomTick(
        BlockState state,
        @NotNull ServerLevel level,
        @NotNull BlockPos pos,
        RandomSource random
    ) {
        int myAge = state.getValue(AGE);
        if (random.nextIntBetweenInclusive(1, 3) == 3) {
            if (myAge < MAX_AGE && random.nextInt(5) == 0
                && level.getRawBrightness(pos.above(), 0) >= 9) {
                var newState = state.setValue(AGE, myAge + 1);
                level.setBlock(pos, newState, Block.UPDATE_CLIENTS);
                level.gameEvent(
                    GameEvent.BLOCK_CHANGE,
                    pos,
                    GameEvent.Context.of(newState)
                );
            } else if (myAge == MAX_AGE) {
                level.scheduleTick(pos, this, 1, TickPriority.NORMAL);
            }
        }
    }

    @Override
    public void attack(
        BlockState state,
        @NotNull Level level,
        @NotNull BlockPos pos,
        @NotNull Player player
    ) {
        if (state.getValue(AGE) >= RIPE_AGE) {
            level.scheduleTick(pos, this, 1, TickPriority.NORMAL);
        }
        super.attack(state, level, pos, player);
    }

    @Override
    public void tick(
        @NotNull BlockState state,
        @NotNull ServerLevel level,
        @NotNull BlockPos pos,
        @NotNull RandomSource random
    ) {
        var ceiling = level.getBlockState(pos.above());
        if (!ceiling.is(BlockTags.LEAVES)) {
            level.destroyBlock(pos, true);
        } else if (state.getValue(AGE) >= RIPE_AGE) {
            level.playSound(
                null,
                pos,
                SoundEvents.ITEM_PICKUP,
                SoundSource.BLOCKS,
                1f,
                0.8f + random.nextFloat() * 0.4f
            );
            var naturalCoconutBlockState = ModBlocks.COCONUT
                .block()
                .defaultBlockState()
                    .setValue(CoconutBlock.PERSISTENT, false);
            var fallingBlock = FallingBlockEntity.fall(
                level,
                pos,
                naturalCoconutBlockState
            );
            fallingBlock.setHurtsEntities(
                CoconutBlock.COCONUT_FALL_DAMAGE,
                CoconutBlock.COCONUT_FALL_MAX_DAMAGE
            );
            fallingBlock.dropItem = true;
            level.setBlockAndUpdate(pos, defaultBlockState());
        }
    }

    @Override
    public boolean isValidBonemealTarget(
        @NotNull LevelReader level,
        @NotNull BlockPos pos,
        @NotNull BlockState state
    ) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(
        @NotNull Level level,
        @NotNull RandomSource random,
        @NotNull BlockPos pos,
        @NotNull BlockState state
    ) {
        return true;
    }

    @Override
    public void performBonemeal(
        @NotNull ServerLevel level,
        @NotNull RandomSource random,
        @NotNull BlockPos pos,
        @NotNull BlockState state
    ) {
        level.setBlock(
            pos,
            state.setValue(AGE, Math.min(MAX_AGE, state.getValue(AGE) + 1)),
            Block.UPDATE_CLIENTS
        );
    }

    @Override
    public boolean canSurvive(
        @NotNull BlockState state,
        LevelReader level,
        BlockPos pos
    ) {
        var ceiling = level.getBlockState(pos.above());
        return ceiling.is(BlockTags.LEAVES);
    }

    @Override
    protected @NotNull MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
}

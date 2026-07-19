package net.zoey.cozyliving.content.block;

import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class RaspberryBushBlock extends Block implements BonemealableBlock {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;
    private static final VoxelShape[] SHAPES = new VoxelShape[] {
        box(4f, 0f, 4f, 12f, 8f, 12f),
        box(3f, 0f, 3f, 13f, 10f, 13f),
        box(2f, 0f, 2f, 14f, 14f, 14f),
        box(1f, 0f, 1f, 15f, 16f, 15f),
        box(1f, 0f, 1f, 15f, 16f, 15f),
        // SHAPES[4] is a duplicate of SHAPES[3] - this is so ageing
        // does not crash the game
    };

    public RaspberryBushBlock() {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));
        registerDefaultState(stateDefinition
            .any()
            .setValue(HALF, DoubleBlockHalf.LOWER)
            .setValue(AGE, 0));
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, @NotNull BlockState state) {
        if (level.getBlockState(pos.below()).is(ModBlocks.RASPBERRY_BUSH.block())) {
            level.destroyBlock(pos.below(), true);
        }
    }

    @Override
    public @NotNull BlockState updateShape(
        @NotNull BlockState state,
        @NotNull Direction direction,
        @NotNull BlockState neighbourState,
        @NotNull LevelAccessor level,
        @NotNull BlockPos pos,
        @NotNull BlockPos neighbourPos
    ) {
        if (direction.equals(Direction.DOWN) && !(
            neighbourState.is(BlockTags.DIRT)
                || neighbourState.is(ModBlocks.RASPBERRY_BUSH.block())
                || neighbourState.is(Blocks.STRUCTURE_BLOCK)
                || neighbourState.is(Blocks.JIGSAW)
        )) {
            level.destroyBlock(pos, true);
            return Blocks.AIR.defaultBlockState();
        }
        return state;
    }

    @Override
    public boolean canSurvive(
        @NotNull BlockState state,
        LevelReader level,
        BlockPos pos
    ) {
        return level.getBlockState(pos.below()).is(BlockTags.DIRT);
    }

    @Override
    public void entityInside(
        @NotNull BlockState state,
        @NotNull Level level,
        @NotNull BlockPos pos,
        @NotNull Entity entity
    ) {
        // TODO: use entity tags?
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX
            && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.8, 0.75, 0.8));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
        builder.add(HALF);
    }

    @Override
    public @NotNull VoxelShape getShape(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos,
        @NotNull CollisionContext context
    ) {
        return SHAPES[state.getValue(AGE)];
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(AGE) < 4;
    }

    @Override
    public void randomTick(
        @NotNull BlockState state,
        @NotNull ServerLevel level,
        @NotNull BlockPos pos,
        RandomSource random
    ) {
        if (random.nextInt(15) == 0 && level.getRawBrightness(pos.above(), 0) >= 9) {
            performBonemeal(level, random, pos, state);
        }
    }

    @Override
    public boolean isValidBonemealTarget(
        @NotNull LevelReader level,
        @NotNull BlockPos pos,
        @NotNull BlockState state
    ) {
        return state.getValue(AGE) < 4;
    }

    @Override
    public boolean isBonemealSuccess(
        @NotNull Level level,
        @NotNull RandomSource pRandom,
        @NotNull BlockPos pos,
        @NotNull BlockState state
    ) {
        return state.getValue(AGE) < 4;
    }

    @Override
    public void performBonemeal(
        @NotNull ServerLevel level,
        @NotNull RandomSource random,
        @NotNull BlockPos pos,
        @NotNull BlockState state
    ) {
        int myAge = state.getValue(AGE);
        if (myAge >= 4) {
            return;
        }
        var myHalf = state.getValue(HALF);

        if (myHalf == DoubleBlockHalf.UPPER || myAge < 2) {
            // always grow if this is a top half or if not ready to have a top half
            level.setBlockAndUpdate(pos, state.setValue(AGE, myAge + 1));
        } else if (myAge == 2) {
            // grow into age 3 and spawn a block above me
            // TODO: does this just delete light sources?
            level.setBlockAndUpdate(pos, state.setValue(AGE, 3));
            level.setBlockAndUpdate(
                pos.above(),
                defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)
            );
        } else {
            var aboveAge = -1;
            var aboveState = level.getBlockState(pos.above());
            if (aboveState.is(ModBlocks.RASPBERRY_BUSH.block())) {
                aboveAge = aboveState.getValue(AGE);
            } else {
                // somehow our upper half is gone. Put it back.
                level.setBlockAndUpdate(
                    pos.above(),
                    defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER)
                );
                // you don't get to have the rest of your random tick
                // if you deleted half the plant
                return;
            }
            if (aboveAge < 3) {
                // grow the top half
                level.setBlockAndUpdate(
                    pos.above(),
                    aboveState.setValue(AGE, aboveAge + 1)
                );
            } else {
                // our top half is old enough, put berries on this half
                level.setBlockAndUpdate(pos, state.setValue(AGE, 4));
            }
        }
    }

    @Override
    public boolean propagatesSkylightDown(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos
    ) {
        return true;
    }

    @Override
    public @NotNull InteractionResult useWithoutItem(
        BlockState state,
        @NotNull Level level,
        @NotNull BlockPos pos,
        @NotNull Player player,
        @NotNull BlockHitResult hit
    ) {
        int myAge = state.getValue(AGE);
        // if this plant is ripe, try to pick both halves
        if (myAge == 4) {
            int berriesToDrop = level.random.nextInt(2) + 1;

            var iAmLower = state.getValue(HALF) == DoubleBlockHalf.LOWER;

            level.playSound(
                player,
                pos.getX() + 0.5,
                pos.getY() + (iAmLower ? 1 : 0),
                pos.getZ() + 0.5,
                SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES,
                SoundSource.BLOCKS,
                1f,
                0.8f + level.random.nextFloat() * 0.4f
            );

            var newState = state.setValue(AGE, 3);
            level.setBlockAndUpdate(pos, newState);
            var otherHalf = iAmLower ? pos.above() : pos.below();
            var otherState = level.getBlockState(otherHalf);
            if (otherState.is(ModBlocks.RASPBERRY_BUSH.block())
                && otherState.getValue(AGE) == 4) {
                otherState.setValue(AGE, 3);
                level.setBlockAndUpdate(otherHalf, otherState);
                berriesToDrop += level.random.nextInt(2) + 1;
            }
            level.gameEvent(
                GameEvent.BLOCK_CHANGE,
                pos,
                GameEvent.Context.of(player, newState)
            );
            level.gameEvent(
                GameEvent.BLOCK_CHANGE,
                otherHalf,
                GameEvent.Context.of(player, otherState)
            );
            var stack = new ItemStack(ModBlocks.RASPBERRY_BUSH.asItem(), berriesToDrop);
            player.getInventory().placeItemBackInInventory(stack);
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return super.useWithoutItem(state, level, pos, player, hit);
        }
    }
}

package net.zoey.cozyliving.content.block;

import net.minecraft.core.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

public class SliceableFoodBlock extends Block {
    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public final Supplier<Item> sliceItem;

    public SliceableFoodBlock(
        @NotNull Supplier<Item> sliceItem,
        BlockBehaviour.Properties properties
    ) {
        super(properties);

        this.sliceItem = sliceItem;
        registerDefaultState(defaultBlockState().setValue(BITES, 0));
    }

    private static final VoxelShape slice0 = box(8, 0, 2, 14, 4, 8);
    private static final VoxelShape slice1 = box(2, 0, 2, 8, 4, 8);
    private static final VoxelShape slice2 = box(2, 0, 8, 8, 4, 14);
    private static final VoxelShape slice3 = box(8, 0, 8, 14, 4, 14);

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull VoxelShape getShape(
        @NotNull BlockState state,
        @NotNull BlockGetter level,
        @NotNull BlockPos pos,
        @NotNull CollisionContext context
    ) {
        return switch (state.getValue(BITES)) {
            case 3 -> switch (state.getValue(FACING)) {
                case SOUTH -> slice0;
                case EAST -> slice1;
                case NORTH -> slice2;
                case WEST -> slice3;
                default -> throw new IllegalStateException(
                    "Block with Horizontal Facing in invalid state");
            };
            case 2 -> switch (state.getValue(FACING)) {
                case SOUTH -> Shapes.or(slice0, slice1);
                case EAST -> Shapes.or(slice1, slice2);
                case NORTH -> Shapes.or(slice2, slice3);
                case WEST -> Shapes.or(slice3, slice0);
                default -> throw new IllegalStateException(
                    "Block with Horizontal Facing in invalid state");
            };
            case 1 -> switch (state.getValue(FACING)) {
                case SOUTH -> Shapes.or(slice0, slice1, slice2);
                case EAST -> Shapes.or(slice1, slice2, slice3);
                case NORTH -> Shapes.or(slice2, slice3, slice0);
                case WEST -> Shapes.or(slice3, slice0, slice1);
                default -> throw new IllegalStateException(
                    "Block with Horizontal Facing in invalid state");
            };
            default -> box(2, 0, 2, 14, 4, 14);
        };
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(
            FACING,
            context.getHorizontalDirection().getOpposite()
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BITES);
        builder.add(FACING);
    }

    @Override
    @SuppressWarnings("deprecation")
    public @NotNull InteractionResult use(
        @NotNull BlockState state,
        @NotNull Level level,
        @NotNull BlockPos pos,
        @NotNull Player player,
        @NotNull InteractionHand hand,
        @NotNull BlockHitResult hit
    ) {
        var held = player.getMainHandItem();

        if (held.getItem() instanceof SwordItem) {
            // cut the sliceable block
            var inv = player.getInventory();
            var sliceStack = new ItemStack(sliceItem.get());
            inv.placeItemBackInInventory(sliceStack);

            level.playSound(
                null,
                pos,
                SoundEvents.WOOL_BREAK,
                SoundSource.PLAYERS,
                1,
                1
            );
            return consumeChunk(level, pos, state, player);
        }
        if (level.isClientSide || !(held.getItem() instanceof DebugStickItem)) {
            if (tryEat(level, pos, state, player).consumesAction()) {
                return InteractionResult.SUCCESS;
            }
            if (held.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }
        return tryEat(level, pos, state, player);
    }

    private @NotNull InteractionResult consumeChunk(
        @NotNull Level level,
        @NotNull BlockPos pos,
        @NotNull BlockState state,
        @NotNull Player player
    ) {
        int eaten = state.getValue(BITES);
        if (eaten < 3) {
            level.setBlock(pos, state.setValue(BITES, eaten + 1), Block.UPDATE_ALL);
        } else {
            level.removeBlock(pos, false);
            level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
        }
        return InteractionResult.SUCCESS;
    }

    private @NotNull InteractionResult tryEat(
        @NotNull Level level,
        @NotNull BlockPos pos,
        @NotNull BlockState state,
        @NotNull Player player
    ) {
        if (!player.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            player.eat(level, new ItemStack(sliceItem.get()));
            level.gameEvent(player, GameEvent.EAT, pos);
            return consumeChunk(level, pos, state, player);
        }
    }
}

package net.zoey.cozyliving.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class LargeSliceableFoodBlock extends GenericSliceableFoodBlock{
    public LargeSliceableFoodBlock(@NotNull Supplier<Item> sliceItem, Properties properties) {
        super(sliceItem, properties);
    }

    private static final VoxelShape slice0 = box(8, 0, 1, 15, 8, 8);
    private static final VoxelShape slice1 = box(1, 0, 1, 8, 8, 8);
    private static final VoxelShape slice2 = box(1, 0, 8, 8, 8, 15);
    private static final VoxelShape slice3 = box(8, 0, 8, 15, 8, 15);

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
            default -> box(1, 0, 1, 15, 8, 15);
        };
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(
                FACING,
                context.getHorizontalDirection().getOpposite()
        );
    }
}

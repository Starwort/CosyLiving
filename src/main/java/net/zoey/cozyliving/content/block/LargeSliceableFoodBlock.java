package net.zoey.cozyliving.content.block;

import net.minecraft.world.item.*;
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

public class LargeSliceableFoodBlock extends GenericSliceableFoodBlock{
    public LargeSliceableFoodBlock(@NotNull Supplier<Item> sliceItem, Properties properties) {
        super(sliceItem, properties);
    }

    @Override
    protected VoxelShape slice0() {
        return box(8, 0, 1, 15, 8, 8);
    }
    @Override
    protected VoxelShape slice1() {
        return box(1, 0, 1, 8, 8, 8);
    }
    @Override
    protected VoxelShape slice2() {
        return box(1, 0, 8, 8, 8, 15);
    }
    @Override
    protected VoxelShape slice3() {
        return box(8, 0, 8, 15, 8, 15);
    }
}

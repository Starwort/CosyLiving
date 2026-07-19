package net.zoey.cozyliving.content.item;

import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.*;
import org.jetbrains.annotations.*;

public class BurnableBlockItem extends ItemNameTooltipBlockItem {
    public final int burnTime;

    public BurnableBlockItem(Block block, Properties properties) {
        super(block, properties);
        this.burnTime = 100;
    }

    public BurnableBlockItem(Block block, Properties properties, int burnTime) {
        super(block, properties);
        this.burnTime = burnTime;
    }

    @Override
    public int getBurnTime(@NotNull ItemStack stack, @Nullable RecipeType<?> recipeType) {
        return burnTime;
    }

}

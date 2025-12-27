package net.zoey.cozyliving.content.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class BurnableBlockItem extends ItemNameTooltipBlockItem{
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
        public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
        return burnTime;
    }

}

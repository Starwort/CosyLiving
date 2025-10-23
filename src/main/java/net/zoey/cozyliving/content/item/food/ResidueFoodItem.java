package net.zoey.cozyliving.content.item.food;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.zoey.cozyliving.content.item.*;
import org.jetbrains.annotations.*;

public class ResidueFoodItem extends TooltipItem {
    Item residueKind;

    public ResidueFoodItem(Properties properties, Item residueItem) {
        super(properties.craftRemainder(residueItem));
        residueKind = residueItem;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(
        @NotNull ItemStack stack,
        @NotNull Level level,
        @NotNull LivingEntity user
    ) {
        super.finishUsingItem(stack, level, user);
        if (user instanceof Player player) {
            return CLItemUtils.createFilledResultWithoutConsuming(
                stack,
                player,
                new ItemStack(residueKind),
                true
            );
        }
        return stack;
    }

    public static class Eat extends ResidueFoodItem {
        public Eat(Properties properties, Item residueItem) {
            super(properties, residueItem);
        }

        @Override
        public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
            return UseAnim.EAT;
        }
    }

    public static class Drink extends ResidueFoodItem {
        public Drink(Properties properties, Item residueItem) {
            super(properties, residueItem);
        }

        @Override
        public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
            return UseAnim.DRINK;
        }
    }
}

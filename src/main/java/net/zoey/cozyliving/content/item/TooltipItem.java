package net.zoey.cozyliving.content.item;

import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.zoey.cozyliving.common.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class TooltipItem extends Item {
    public TooltipItem() {
        super(new Properties());
    }

    int tooltipQuantity = 1;
    public TooltipItem(Properties properties) {
        super(properties);
    }
    public TooltipItem(Properties properties, int tooltipCount) {
        super(properties);
        tooltipQuantity = tooltipCount;
    }

    @Override
    public void appendHoverText(
        @NotNull ItemStack stack,
        @NotNull TooltipContext context,
        @NotNull List<Component> tooltip,
        @NotNull TooltipFlag flag
    ) {

        if(tooltipQuantity == 2){
            tooltip.add(Component.translatable(
                    "tooltip.cozyliving." + CLItemUtils.idOf(stack.getItem()) + ".1"));
            tooltip.add(Component.translatable(
                    "tooltip.cozyliving." + CLItemUtils.idOf(stack.getItem()) + ".2"));
        } else {
            tooltip.add(Component.translatable(
                    "tooltip.cozyliving." + CLItemUtils.idOf(stack.getItem())));
        }
        super.appendHoverText(stack, context, tooltip, flag);
    }
}

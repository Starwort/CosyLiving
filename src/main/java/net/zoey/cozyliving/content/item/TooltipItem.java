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

    public TooltipItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
        @NotNull ItemStack stack,
        @NotNull TooltipContext context,
        @NotNull List<Component> tooltip,
        @NotNull TooltipFlag flag
    ) {
        tooltip.add(Component.translatable(
            "tooltip.cozyliving." + CLItemUtils.idOf(stack.getItem())));
        super.appendHoverText(stack, context, tooltip, flag);
    }
}

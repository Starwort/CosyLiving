package net.zoey.cozyliving.content.item;

import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
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
        @Nullable Level level,
        @NotNull List<Component> tooltip,
        @NotNull TooltipFlag flag
    ) {
        tooltip.add(Component.translatable(
            "tooltip.cozyliving." + CLItemUtils.idOf(stack.getItem())));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}

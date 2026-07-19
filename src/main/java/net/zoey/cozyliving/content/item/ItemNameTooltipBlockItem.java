package net.zoey.cozyliving.content.item;

import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.zoey.cozyliving.common.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class ItemNameTooltipBlockItem extends ItemNameBlockItem {
    public ItemNameTooltipBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public ItemNameTooltipBlockItem(Block block) {
        super(block, new Properties());
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

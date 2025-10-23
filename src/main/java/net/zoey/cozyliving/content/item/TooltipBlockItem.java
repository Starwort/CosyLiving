package net.zoey.cozyliving.content.item;

import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class TooltipBlockItem extends BlockItem {
    public TooltipBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public TooltipBlockItem(Block block) {
        super(block, new Properties());
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

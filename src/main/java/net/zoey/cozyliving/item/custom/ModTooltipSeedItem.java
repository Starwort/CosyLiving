package net.zoey.cozyliving.item.custom;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModTooltipSeedItem extends AliasedBlockItem {
    String translationKey;

    public ModTooltipSeedItem(Block block, Settings settings, String translationID) {
        super(block, settings);
        translationKey = translationID;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

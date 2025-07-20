package net.zoey.cozyliving.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FlowerCrownItem extends Item implements Equipment {
    String translationKey;

    public FlowerCrownItem(Settings settings, String translationID) {
        super(settings);
        translationKey = translationID;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey));
        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}

package net.zoey.cozyliving.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TooltipArmorItem extends ArmorItem {
    String translationKey;
    public TooltipArmorItem(ArmorMaterial material, Type type, Settings settings, String translationID) {
        super(material, type, settings);
        translationKey = translationID;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.cozyliving." + translationKey));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

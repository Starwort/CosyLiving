package net.zoey.cozyliving.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.UseAction;

public class PinaGlowadaItem extends ModTooltipItem{
    public PinaGlowadaItem(Settings settings, String translationID) {
        super(settings, translationID);
   }
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }
    public int getMaxUseTime(ItemStack stack) {
        return 45;
    }

}

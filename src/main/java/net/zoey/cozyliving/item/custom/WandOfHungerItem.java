package net.zoey.cozyliving.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class WandOfHungerItem extends ModTooltipItem{


    public WandOfHungerItem(Settings settings, String translationID) {
        super(settings, translationID);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.getHungerManager().setFoodLevel(1);
        user.getHungerManager().setSaturationLevel(1);
        user.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP, 1f, 1f);
        return TypedActionResult.success(user.getStackInHand(hand));
    }

}

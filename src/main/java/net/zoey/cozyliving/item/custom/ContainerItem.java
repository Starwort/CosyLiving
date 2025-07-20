package net.zoey.cozyliving.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class ContainerItem extends ModTooltipItem {
    ItemStack itemStackContainer;
    public ContainerItem(Settings settings, String translationID, ItemStack container) {
        super(settings, translationID);
        this.itemStackContainer = container;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity playerEntity && !playerEntity.getAbilities().creativeMode) {
            if (!playerEntity.getInventory().insertStack(new ItemStack(itemStackContainer.getItem()))) {
                playerEntity.dropItem(itemStackContainer, false);
            }
        }
        return super.finishUsing(stack, world, user);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

}

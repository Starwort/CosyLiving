package net.zoey.cozyliving.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class HotterChocolateItem extends ContainerItem {
    ItemStack itemStackContainer;
    public HotterChocolateItem(Settings settings, String translationID, ItemStack container) {
        super(settings, translationID, container);
        itemStackContainer = container;
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (!world.isClient) {
            user.setOnFire(true);
            user.setFireTicks(300);
        }
        user.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1f, 1f);
        return super.finishUsing(stack, world, user);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

}

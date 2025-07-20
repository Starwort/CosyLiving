package net.zoey.cozyliving.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class FreezingItem extends ContainerItem{
    ItemStack itemStackContainer;
    SoundEvent consumeSound;
    public FreezingItem(Settings settings, String translationID, ItemStack container) {
        super(settings, translationID, container);
        this.itemStackContainer = container;
        this.consumeSound = SoundEvents.ENTITY_GENERIC_EAT;
    }

    public FreezingItem(Settings settings, String translationID, ItemStack container, SoundEvent consumeSound) {
        super(settings, translationID, container);
        this.itemStackContainer = container;
        this.consumeSound = consumeSound;
    }


    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (!world.isClient) {
            user.extinguishWithSound();
            user.setFrozenTicks(100);
        }

        return super.finishUsing(stack, world, user);


    }

    public SoundEvent getDrinkSound() {
        return consumeSound;
    }

    public SoundEvent getEatSound() {
        return consumeSound;
    }
}

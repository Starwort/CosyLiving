package net.zoey.cozyliving.content.item.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.zoey.cozyliving.content.item.*;
import org.jetbrains.annotations.*;

public class BottledFoodItem extends ResidueFoodItem {
    int drinkingTime;
    SoundEvent drinkingSound;

    public BottledFoodItem(Properties properties, int timeToDrink) {
        this(properties, timeToDrink, SoundEvents.GENERIC_DRINK);
    }

    public BottledFoodItem(
        Properties properties,
        int timeToDrink,
        SoundEvent drinkSound
    ) {
        super(properties, Items.GLASS_BOTTLE);
        drinkingTime = timeToDrink;
        drinkingSound = drinkSound;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(
        @NotNull ItemStack stack,
        @NotNull Level level,
        @NotNull LivingEntity user
    ) {
        if (user instanceof ServerPlayer player) {
            CriteriaTriggers.CONSUME_ITEM.trigger(player, stack);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        var item = CLItemUtils.idOf(stack.getItem());
        if (item.equals("coconut_milk") || item.equals("heavy_cream")) {
            if (!level.isClientSide) {
                user.removeAllEffects();
            }
        }
        return super.finishUsingItem(stack, level, user);
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return drinkingTime;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull SoundEvent getDrinkingSound() {
        return drinkingSound;
    }

    @Override
    public @NotNull SoundEvent getEatingSound() {
        return drinkingSound;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
        @NotNull Level level,
        @NotNull Player player,
        @NotNull InteractionHand hand
    ) {
        return ItemUtils.startUsingInstantly(level, player, hand);
    }
}

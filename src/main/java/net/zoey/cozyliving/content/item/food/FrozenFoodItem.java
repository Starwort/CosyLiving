package net.zoey.cozyliving.content.item.food;

import net.minecraft.advancements.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.stats.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.*;

public class FrozenFoodItem extends ResidueFoodItem {
    SoundEvent consumeSound;

    public FrozenFoodItem(Properties properties) {
        this(properties, Items.BOWL);
    }

    public FrozenFoodItem(Properties properties, Item eatResult) {
        super(properties, eatResult);
        consumeSound = SoundEvents.GENERIC_EAT;
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
        if (!level.isClientSide) {
            user.extinguishFire();
            user.setTicksFrozen(100);
        }
        return super.finishUsingItem(stack, level, user);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull SoundEvent getDrinkingSound() {
        return consumeSound;
    }

    @Override
    public @NotNull SoundEvent getEatingSound() {
        return consumeSound;
    }
}

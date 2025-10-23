package net.zoey.cozyliving.content.item.food;

import net.minecraft.advancements.*;
import net.minecraft.server.level.*;
import net.minecraft.stats.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.zoey.cozyliving.content.item.*;
import org.jetbrains.annotations.*;

public class CandyAppleItem extends ResidueFoodItem {
    public CandyAppleItem(Properties properties) {
        super(properties, Items.STICK);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack stack) {
        return CLItemUtils.idOf(stack.getItem()).startsWith("enchanted");
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return 60;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.EAT;
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
            user.removeEffect(MobEffects.POISON);
        }
        return super.finishUsingItem(stack, level, user);
    }
}

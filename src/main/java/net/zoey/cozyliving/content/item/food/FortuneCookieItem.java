package net.zoey.cozyliving.content.item.food;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.zoey.cozyliving.common.CLItemUtils;
import net.zoey.cozyliving.content.item.TooltipItem;
import org.jetbrains.annotations.NotNull;

public class FortuneCookieItem extends TooltipItem {
    public FortuneCookieItem(Properties properties) {
        super(properties);
    }

    String[] translationKeys = {
            "gorb.1",
            "gorb.2",
            "gorb.3",
            "aegis.1",
            "aegis.2"
    };

    @Override
    public @NotNull ItemStack finishUsingItem(
            @NotNull ItemStack stack,
            @NotNull Level level,
            @NotNull LivingEntity user
    ) {


        super.finishUsingItem(stack, level, user);

        ItemStack FortunePaper = new ItemStack(Items.PAPER); //TODO: why are we getting multiple fortunes?
        FortunePaper.setHoverName(Component.translatable("fortune.cozyliving." + translationKeys[level.getRandom().nextInt(translationKeys.length-1)]));

        if (user instanceof Player player) {
            return CLItemUtils.createFilledResultWithoutConsuming(
                    stack,
                    player,
                    FortunePaper,
                    true
            );
        }
        return stack;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.EAT;
    }

}


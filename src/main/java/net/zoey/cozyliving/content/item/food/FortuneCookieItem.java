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
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.common.CLItemUtils;
import net.zoey.cozyliving.content.item.TooltipItem;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FortuneCookieItem extends TooltipItem {
    public FortuneCookieItem(Properties properties) {
        super(properties);
    }
    public static final List<String> Fortunes = new ArrayList<>();

    static{
        Fortunes.add("fortune.cozyliving.gorb.1");
        Fortunes.add("fortune.cozyliving.gorb.2");
        Fortunes.add("fortune.cozyliving.gorb.3");
        Fortunes.add("fortune.cozyliving.gorb.4");
        Fortunes.add("fortune.cozyliving.aegis.1");
        Fortunes.add("fortune.cozyliving.aegis.2");
        Fortunes.add("fortune.cozyliving.elo.1");
        Fortunes.add("fortune.cozyliving.elo.2");
        Fortunes.add("fortune.cozyliving.elo.3");
        Fortunes.add("fortune.cozyliving.elo.4");
        Fortunes.add("fortune.cozyliving.elo.5");
        Fortunes.add("fortune.cozyliving.elo.6");
        Fortunes.add("fortune.cozyliving.elo.7");
        Fortunes.add("fortune.cozyliving.elo.8");
        Fortunes.add("fortune.cozyliving.elo.9");
        Fortunes.add("fortune.cozyliving.elo.10");
        Fortunes.add("fortune.cozyliving.elo.11");
        Fortunes.add("fortune.cozyliving.elo.12");
        Fortunes.add("fortune.cozyliving.elo.13");
        Fortunes.add("fortune.cozyliving.chan.1");
        Fortunes.add("fortune.cozyliving.zoey.1");
        Fortunes.add("fortune.cozyliving.zoey.2");
        Fortunes.add("fortune.cozyliving.zoey.3");
        Fortunes.add("fortune.cozyliving.zoey.4");
        Fortunes.add("fortune.cozyliving.zoey.5");
        Fortunes.add("fortune.cozyliving.zoey.6");
        Fortunes.add("fortune.cozyliving.zoey.7");
        Fortunes.add("fortune.cozyliving.zoey.8");
        Fortunes.add("fortune.cozyliving.zoey.9");
        Fortunes.add("fortune.cozyliving.zoey.10");
        Fortunes.add("fortune.cozyliving.zoey.11");
        Fortunes.add("fortune.cozyliving.star.1");
        Fortunes.add("fortune.cozyliving.star.2");
    }


    @Override
    public @NotNull ItemStack finishUsingItem(
            @NotNull ItemStack stack,
            @NotNull Level level,
            @NotNull LivingEntity user
    ) {

        super.finishUsingItem(stack, level, user);

        if(!level.isClientSide()){ //Gotta only run on server to prevent misprediction error
            ItemStack FortunePaper = null;
            FortunePaper = new ItemStack(Items.PAPER);
            FortunePaper.setHoverName(Component.translatable(Fortunes.get(level.getRandom().nextInt(Fortunes.size() - 1))));

            if (user instanceof Player player) {
                return CLItemUtils.createFilledResultWithoutConsuming(
                        stack,
                        player,
                        FortunePaper,
                        true
                );
            }
        }



        return stack;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.EAT;
    }

}


package net.zoey.cozyliving.content.item.food;

import net.minecraft.client.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.*;
import net.zoey.cozyliving.common.CLItemUtils;
import net.zoey.cozyliving.content.common.*;
import net.zoey.cozyliving.content.item.TooltipItem;
import org.jetbrains.annotations.NotNull;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class FortuneCookieItem extends TooltipItem {
    public FortuneCookieItem() {
        super(FoodValues.FORTUNE_COOKIE.intoProperties());
    }

    public static final List<String> Fortunes = new ArrayList<>();

    static {
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
        Fortunes.add("fortune.cozyliving.elo.14");
        Fortunes.add("fortune.cozyliving.elo.15");
        Fortunes.add("fortune.cozyliving.elo.16");
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
        Fortunes.add("fortune.cozyliving.star.3");
        Fortunes.add("fortune.cozyliving.star.4");
        Fortunes.add("fortune.cozyliving.star.5");
        Fortunes.add("fortune.cozyliving.star.6");
        Fortunes.add("fortune.cozyliving.star.7");
    }


    @Override
    public @NotNull ItemStack finishUsingItem(
        @NotNull ItemStack stack,
        @NotNull Level level,
        @NotNull LivingEntity user
    ) {

        super.finishUsingItem(stack, level, user);

        if (!level.isClientSide() && user instanceof Player player) {
            // Run only on the server to stop the client from mispredicting the result
            ItemStack fortune = generateFortune(level, player);
            return CLItemUtils.createFilledResultWithoutConsuming(
                stack,
                player,
                fortune,
                true
            );
        }


        return stack;
    }

    static final String[] NS = new String[] {"N", "S"};
    static final String[] EW = new String[] {"E", "W"};

    private static @NotNull ItemStack generateFortune(
        @NotNull Level level,
        Player player
    ) {
        ItemStack FortunePaper = new ItemStack(Items.PAPER);
        var rand = level.getRandom();
        var mods = ModList.get().getMods();
        var randomModName = mods.get(rand.nextInt(mods.size())).getDisplayName();
        var daysInFuture = rand.nextInt(365, 3653);
        var futureDate = LocalDate
            .now()
            .plusDays(daysInFuture)
            .format(DateTimeFormatter.ofPattern("ccc, d MMM yyyy"));

        // Format parameters:
        // XX XX XX XX [four numbers 0-99] $random_mod_name $obfuscated_random_mod_name
        // $player_name $random_future_date NN WW [random coordinates; 0-89 for N, 0-189 for W]
        // $random_NS $random_EW
        FortunePaper.setHoverName(Component.translatable(
            Fortunes.get(rand.nextInt(Fortunes.size() - 1)),
            rand.nextInt(100),
            rand.nextInt(100),
            rand.nextInt(100),
            rand.nextInt(100),
            randomModName,
            "§k" + randomModName,
            player.getDisplayName(),
            futureDate,
            rand.nextInt(90),
            rand.nextInt(180),
            NS[rand.nextInt(2)],
            EW[rand.nextInt(2)]
        ));
        return FortunePaper;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.EAT;
    }

}


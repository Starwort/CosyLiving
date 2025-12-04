package net.zoey.cozyliving.content.item.food;

import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.zoey.cozyliving.content.common.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class MaoCroquiItem extends Item {
    public MaoCroquiItem() {
        super(FoodValues.MAO_CROQUI.intoProperties());
    }

    @Override
    public void appendHoverText(
        @NotNull ItemStack stack,
        @Nullable Level level,
        @NotNull List<Component> tooltip,
        @NotNull TooltipFlag flag
    ) {
        var chosenPlayer = getRandomPlayerName(level);
        tooltip.add(Component.translatable(
            "tooltip.cozyliving.mao_croqui.1",
            chosenPlayer
        ));
        tooltip.add(Component.translatable(
            "tooltip.cozyliving.mao_croqui.2",
            chosenPlayer
        ));
        tooltip.add(Component.translatable(
            "tooltip.cozyliving.mao_croqui.3",
            chosenPlayer
        ));
        tooltip.add(Component.translatable(
            "tooltip.cozyliving.mao_croqui.4",
            chosenPlayer
        ));
        if (flag.isAdvanced() && level != null) {
            tooltip.add(Component.literal("Current GameTime: " + lastKnownGameTime));
            tooltip.add(Component.literal(
                "Current scrambled GameTime: " + scramble(lastKnownGameTime / 40)));
        }
        super.appendHoverText(stack, level, tooltip, flag);
    }

    static long scramble(long x) {
        x ^= (x << 13);
        x *= 0xbf58476d1ce4e5b9L;
        x ^= (x >>> 7);
        x *= 0x94d049bb133111ebL;
        x ^= (x << 17);
        x >>>= 1;
        return x;
    }

    // hack due to gameTime not being properly monotonic
    private static long lastKnownGameTime = 0;

    private Component getRandomPlayerName(@Nullable Level level) {
        if (level == null) {
            lastKnownGameTime = 0;
            return Component.translatable(
                "tooltip.cozyliving.mao_croqui.default_player_name");
        } else {
            var players = level.players();
            // construct a random-looking seed that's deterministic
            // to every two seconds
            var newTime = level.getGameTime();
            if (newTime > lastKnownGameTime) {
                lastKnownGameTime = newTime;
            }
            var time = scramble(lastKnownGameTime / 40);
            return players.get((int) (time % players.size())).getName();
        }
    }
}

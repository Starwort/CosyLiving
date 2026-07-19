package net.zoey.cozyliving.common;

import net.minecraft.core.registries.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.*;
import org.jetbrains.annotations.*;

public class CLItemUtils {
    public static @NotNull ItemStack createFilledResultWithoutConsuming(
        @NotNull ItemStack usedStack,
        @NotNull Player player,
        @NotNull ItemStack stackToGenerate,
        boolean vanillaConsumedYet
    ) {
        if (player.getAbilities().instabuild) {
            // try to give one if the player doesn't already have one
            if (!player.getInventory().contains(stackToGenerate)) {
                player.getInventory().add(stackToGenerate);
            }
            return usedStack;
        }
        // this is pretty much the contents of `ItemUtils.createFilledResult` but
        // without the call to `usedStack.shrink()`
        if (vanillaConsumedYet ? usedStack.isEmpty() : usedStack.getCount() == 1) {
            return stackToGenerate;
        } else if (!player.getInventory().add(stackToGenerate)) {
            player.drop(stackToGenerate, false);
        }
        return usedStack;
    }

    public static String idOf(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).getPath();
    }
}

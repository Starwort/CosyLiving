package net.zoey.cozyliving.content.effect;

import net.minecraft.stats.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import org.jetbrains.annotations.*;

public class WellRestedMobEffect extends MobEffect {
    public WellRestedMobEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFAE420);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
        }
        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        // Prevent lag by only ticking once a second
        // Tbh it doesn't seem like it should cause much lag anyway but w/e
        // Also despite the original comment saying once a second it actually ticks once
        // per 3 seconds. This version ticks once a second instead
        return duration % 20 == 0;
    }
}

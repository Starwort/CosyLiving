package net.zoey.cozyliving.content.effect;

import net.minecraft.stats.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

public class SleepyMobEffect extends MobEffect {
    public SleepyMobEffect() {
        super(MobEffectCategory.HARMFUL, 0x657DA6);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        if (entity instanceof Player player) {
            // Avoid breaking things (boring)
            player.resetStat(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
            // Make 'em sleepy
            player.awardStat(Stats.TIME_SINCE_REST, 240_000);

            if (player.getSleepTimer() != 0) {
                player.addEffect(new MobEffectInstance(
                    ModEffects.WELL_RESTED.effect(),
                    216_000,
                    amplifier,
                    true,
                    false
                ));
                player.removeEffect(ModEffects.SLEEPY.effect());
                player.removeEffect(MobEffects.POISON);
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        // Prevent lag by only ticking once a second
        // Tbh it doesn't seem like it should cause much lag anyway but w/e
        // Also despite the original comment saying once a second it actually ticks once
        // per 3 seconds. This version ticks once a second instead
        return duration % 20 == 0;
    }
}

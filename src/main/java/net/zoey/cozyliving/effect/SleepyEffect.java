package net.zoey.cozyliving.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.zoey.cozyliving.effect.ModEffects;
import net.minecraft.stat.Stats;

import static net.zoey.cozyliving.effect.ModEffects.SLEEPY;

public class SleepyEffect extends StatusEffect {

    public SleepyEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.isPlayer()){                              //If entity is a player
            PlayerEntity player = (PlayerEntity) entity;    //Cast entity to player
            player.resetStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST)); //Reset stat to zero to not break things lol
            player.increaseStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST), 240000); //Make 'em sleepy

            if (player.getSleepTimer() != 0){
                player.addStatusEffect(new StatusEffectInstance(ModEffects.WELL_RESTED, 216000, amplifier, true, false));
                player.removeStatusEffect(ModEffects.SLEEPY);
                player.removeStatusEffect(StatusEffects.POISON);
            }
            //If player sleep timer is something else:
        }

    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {  //Prevents lag by making it only update every second
        return duration % 60 == 0;
    }
}

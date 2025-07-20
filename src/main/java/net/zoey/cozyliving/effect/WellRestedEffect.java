package net.zoey.cozyliving.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.Stats;

public class WellRestedEffect extends StatusEffect {

    public WellRestedEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.isPlayer()){                              //Get the client entity
            //UUID entityID = entity.getUuid();               //Get this entity's ID
            //MinecraftServer server = entity.getServer();    //Get the server
            //ServerPlayerEntity serverPlayer = server.getPlayerManager().getPlayer(entityID);  //Get the player as a server player, I am a fucking genius

            PlayerEntity player = (PlayerEntity) entity;
            //This may cause a lot of lag if it's happening every tick tho...
            player.resetStat(Stats.CUSTOM.getOrCreateStat(Stats.TIME_SINCE_REST));
        }

        //return super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {  //Prevents lag by making it only update every second
        return duration % 60 == 0;
    }
}

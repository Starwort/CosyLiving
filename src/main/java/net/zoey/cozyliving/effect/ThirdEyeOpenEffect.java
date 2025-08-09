package net.zoey.cozyliving.effect;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.zoey.cozyliving.CozyLiving;

import java.awt.*;
import java.util.List;

public class ThirdEyeOpenEffect extends StatusEffect {


    protected ThirdEyeOpenEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

        final int maxRoll = 33;
        int rollSize = 20 / (amplifier + 1);
        World world = entity.getWorld();
        int roll = world.getRandom().nextBetween(1, rollSize + 5);
        CozyLiving.LOGGER.info("Roll = " + roll);

        //DEBUG SHIT

        if (!world.isClient){
            switch (roll){ //One of these effects should run at random every ~20 seconds
                case 1:
                    if (!entity.hasStatusEffect(StatusEffects.LEVITATION)) {
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 60), null);
                    }
                    break;
                case 2:
                    if (!entity.hasStatusEffect(StatusEffects.LEVITATION)) {
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 60, 1), null);
                    }
                    break;
                case 3:
                    if (!entity.hasStatusEffect(StatusEffects.INVISIBILITY)) {
                        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 90), null);
                    }
                    break;
                case 4:
                    teleport(world, entity);
                    break;
                case 5, 6:
                    Text signature = Text.literal("<").append(entity.getDisplayName()).append("> ");
                    Text content = (speakNonsense(world, entity));
                    Text message = Text.empty().append(signature).append(content);

                    PlayerManager playerManager = MinecraftClient.getInstance().getServer().getPlayerManager();
                    if (playerManager != null){
                        playerManager.broadcast(message, false);
                    }
                    break;
            }
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {  //Prevents lag by making it only update every second
        return duration % 30 == 0;
    }

    public Text speakNonsense(World world, LivingEntity entity){
        Random random = world.getRandom();
        int roll = random.nextBetween(1,10);

        List<? extends PlayerEntity> playerEntityList = world.getPlayers();

        Text theyAreZorty = Text.literal("").append(playerEntityList.get(random.nextInt(playerEntityList.size())).getDisplayName()).append(Text.translatable("whisper.cozyliving.they_are_so_zorty"));
        return switch (roll) {
            case 1 -> Text.translatable("whisper.cozyliving.i_see_everything");
            case 2 -> Text.translatable("whisper.cozyliving.i_am_so_zorty");
            case 3 -> theyAreZorty;
            case 4 -> Text.translatable("whisper.cozyliving.woah");
            case 5 -> Text.translatable("whisper.cozyliving.where_am_i");
            case 6 -> Text.translatable("whisper.cozyliving.i_am_so");
            case 7 -> Text.translatable("whisper.cozyliving.holy_moly");
            case 8 -> Text.translatable("whisper.cozyliving.insanity");
            case 9 -> Text.translatable("whisper.cozyliving.so_big");
            default -> Text.translatable("whisper.cozyliving.i_can_see_colours");
        };
    }

    public void teleport(World world, LivingEntity entity){
        for (int i = 0; i < 16; ++i) {
            double d = entity.getX() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
            double e = MathHelper.clamp(entity.getY() + (double) (entity.getRandom().nextInt(16) - 8), (double) world.getBottomY(), (double) (world.getBottomY() + ((ServerWorld) world).getLogicalHeight() - 1));
            double f = entity.getZ() + (entity.getRandom().nextDouble() - 0.5) * 16.0;
            if (entity.hasVehicle()) {
                entity.stopRiding();
            }

            Vec3d vec3d = entity.getPos();
            if (entity.teleport(d, e, f, true)) {
                world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(entity));
                SoundCategory soundCategory;
                SoundEvent soundEvent;
                if (entity instanceof FoxEntity) {
                    soundEvent = SoundEvents.ENTITY_FOX_TELEPORT;
                    soundCategory = SoundCategory.NEUTRAL;
                } else {
                    soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    soundCategory = SoundCategory.PLAYERS;
                }

                world.playSound((PlayerEntity)null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                entity.onLanding();
                break;
            }

        }
    }
}

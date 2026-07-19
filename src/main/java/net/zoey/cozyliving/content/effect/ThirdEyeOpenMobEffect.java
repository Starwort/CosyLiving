package net.zoey.cozyliving.content.effect;

import net.minecraft.client.gui.components.*;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.stats.*;
import net.minecraft.util.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.gameevent.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.function.*;

public class ThirdEyeOpenMobEffect extends MobEffect {
    public ThirdEyeOpenMobEffect() {
        super(MobEffectCategory.HARMFUL, 0xD429FF);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity entity, int amplifier) {
        int rollSize = 20 / (amplifier + 1);
        var level = entity.level();
        if (level.isClientSide()) {
            return false;
        }
        int roll = level.getRandom().nextInt(rollSize + 5);
        switch (roll) {
            case 0:
                if (!entity.hasEffect(MobEffects.LEVITATION)) {
                    entity.addEffect(
                        new MobEffectInstance(MobEffects.LEVITATION, 60),
                        null
                    );
                }
                break;
            case 1:
                if (!entity.hasEffect(MobEffects.LEVITATION)) {
                    entity.addEffect(
                        new MobEffectInstance(
                            MobEffects.LEVITATION,
                            60,
                            1
                        ), null
                    );
                }
                break;
            case 2:
                if (!entity.hasEffect(MobEffects.INVISIBILITY)) {
                    entity.addEffect(
                        new MobEffectInstance(MobEffects.INVISIBILITY, 90),
                        null
                    );
                }
                break;
            case 3:
                teleport(level, entity);
                break;
            case 4:
                if (entity instanceof Player player) {
                    var msg = PlayerChatMessage.unsigned(
                        player.getUUID(),
                        babble(level)
                    );
                    player
                        .createCommandSourceStack()
                        .sendChatMessage(
                            new OutgoingChatMessage.Player(msg),
                            false,
                            ChatType.bind(ChatType.CHAT, player)
                        );
                }
                break;
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

    private void teleport(Level level, LivingEntity entity) {
        var rand = level.getRandom();
        if (entity.isPassenger()) {
            entity.stopRiding();
        }
        for (var i = 0; i < 16; i++) {
            var x = entity.getX() + (rand.nextDouble() - 0.5) * 16;
            var y = Mth.clamp(
                entity.getY() + rand.nextInt(16) - 8,
                level.getMinBuildHeight(),
                level.getMaxBuildHeight()
            );
            var z = entity.getZ() + (rand.nextDouble() - 0.5) * 16;
            var oldPos = entity.position();
            if (entity.randomTeleport(x, y, z, true)) {
                level.gameEvent(
                    GameEvent.TELEPORT,
                    oldPos,
                    GameEvent.Context.of(entity)
                );
                var soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                var soundSource = SoundSource.PLAYERS;
                if (entity instanceof Fox) {
                    soundEvent = SoundEvents.FOX_TELEPORT;
                    soundSource = SoundSource.NEUTRAL;
                }
                Player source = null;
                if (entity instanceof Player player) {
                    source = player;
                }

                level.playSound(source, x, y, z, soundEvent, soundSource, 1, 1);
                entity.resetFallDistance();
            }
        }
    }

    // Can't make it with generics, can't make it without generics. There's no winning
    @SuppressWarnings("unchecked")
    private static final Function<Level, String>[] babbles = new Function[] {
        _level -> "I!!! See!!!! Ebverything!!!!!!!",
        _level -> "i am sbo zorty...",
        // I hate this language
        (Function<Level, String>) level -> {
            var players = level.players();
            return players
                .get(level.getRandom().nextInt(players.size()))
                .getDisplayName()
                .getString() + "s are sso zorty...";
        },
        _level -> "woahhhhhhh...",
        _level -> "wh... where is... me",
        _level -> "hphpholy bmol y",
        _level -> "wwjoah i am so ch ;gigh",
        _level -> "i cand see cldourours",
        _level -> "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
        _level -> "everyyfing isss s sooooo bgig",
    };

    private String babble(Level level) {
        return babbles[level.getRandom().nextInt(babbles.length)].apply(level);
    }
}

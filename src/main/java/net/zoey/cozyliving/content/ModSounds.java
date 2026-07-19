package net.zoey.cozyliving.content;

import net.minecraft.sounds.*;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.common.util.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;

public enum ModSounds {
    COCONUT_BONK("coconut_bonk"),

    LEAVES_RUSTLE("leaves_rustle"),

    JAM_SMEAR("jam_smear"),

    INK_SMEAR("ink_smear"),
    ;
    public static final SoundType COCONUT_SOUNDS = new DeferredSoundType(
        1f,
        1f,
        SoundType.BAMBOO::getBreakSound,
        SoundType.BAMBOO::getStepSound,
        SoundType.BAMBOO::getPlaceSound,
        SoundType.BAMBOO::getHitSound,
        COCONUT_BONK::sound
    );

    private final DeferredHolder<SoundEvent, SoundEvent> myValue;

    ModSounds(String id) {
        myValue = CozyLiving.SOUNDS.register(
            id,
            () -> SoundEvent.createVariableRangeEvent(CozyLiving.loc(id))
        );
    }

    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
            "Found {} sounds",
            CozyLiving.SOUNDS.getEntries().size()
        );
        CozyLiving.SOUNDS.register(modEventBus);
    }

    public DeferredHolder<SoundEvent, SoundEvent> holder() {
        return myValue;
    }

    public SoundEvent sound() {
        return myValue.get();
    }
}

package net.zoey.cozyliving.content;

import net.minecraft.sounds.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.common.util.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;

public enum ModSounds {
    COCONUT_BONK("coconut_bonk"),

    LEAVES_RUSTLE("leaves_rustle"),

    JAM_SMEAR("jam_smear"),

    INK_SMEAR("ink_smear"),
    ;
    public static final SoundType COCONUT_SOUNDS = new ForgeSoundType(
        1f,
        1f,
        SoundType.BAMBOO::getBreakSound,
        SoundType.BAMBOO::getStepSound,
        SoundType.BAMBOO::getPlaceSound,
        SoundType.BAMBOO::getHitSound,
        COCONUT_BONK::sound
    );

    private final RegistryObject<SoundEvent> myValue;

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

    public RegistryObject<SoundEvent> registryObject() {
        return myValue;
    }

    public SoundEvent sound() {
        return myValue.get();
    }
}

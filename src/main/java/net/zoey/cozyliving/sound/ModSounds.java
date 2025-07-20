package net.zoey.cozyliving.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;

public class ModSounds {

    public static final SoundEvent COCONUT_BONK = registerSoundEvent("coconut_bonk");
    public static final SoundEvent LEAVES_RUSTLE = registerSoundEvent("leaves_rustle");
    public static final SoundEvent JAM_SMEAR = registerSoundEvent("jam_smear");

    public static final BlockSoundGroup COCONUT_SOUNDS = new BlockSoundGroup(1f, 1f,
            BlockSoundGroup.BAMBOO.getBreakSound(),
            BlockSoundGroup.BAMBOO.getStepSound(),
            BlockSoundGroup.BAMBOO.getPlaceSound(),
            BlockSoundGroup.BAMBOO.getHitSound(),
            COCONUT_BONK);


    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(CozyLiving.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        CozyLiving.LOGGER.info("Registering Sounds for " + CozyLiving.MOD_ID);
    }
}

package net.zoey.cozyliving.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;

public class ModEffects {

    public static final StatusEffect WELL_RESTED = registerStatusEffect("well_rested",
            new WellRestedEffect(StatusEffectCategory.BENEFICIAL, 0xfae420));

    public static final StatusEffect SLEEPY = registerStatusEffect("sleepy",
            new SleepyEffect(StatusEffectCategory.HARMFUL, 0x657da6));

    public static final StatusEffect THIRD_EYE_OPEN = registerStatusEffect("third_eye_open",
            new ThirdEyeOpenEffect(StatusEffectCategory.HARMFUL, 0xd429ff));


    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(CozyLiving.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        CozyLiving.LOGGER.info("Registering Mod Effects for " + CozyLiving.MOD_ID);

    }
}

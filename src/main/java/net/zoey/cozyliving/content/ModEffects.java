package net.zoey.cozyliving.content;

import net.minecraft.resources.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.level.block.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.effect.*;

import java.util.function.*;

public enum ModEffects {
    WELL_RESTED("well_rested", WellRestedMobEffect::new),

    SLEEPY("sleepy", SleepyMobEffect::new),

    THIRD_EYE_OPEN("third_eye_open", ThirdEyeOpenMobEffect::new),
    ;

    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
            "Found {} effects",
            CozyLiving.EFFECTS.getEntries().size()
        );
        CozyLiving.EFFECTS.register(modEventBus);
    }

    private final DeferredHolder<MobEffect, MobEffect> myValue;

    ModEffects(String name, Supplier<MobEffect> supplier) {
        myValue = CozyLiving.EFFECTS.register(name, supplier);
    }

    public ResourceLocation id() {
        return myValue.getId();
    }

    public DeferredHolder<MobEffect, MobEffect> holder() {
        return myValue;
    }

    public MobEffect effect() {
        return myValue.get();
    }
}

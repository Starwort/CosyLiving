package net.zoey.cozyliving.content;

import net.minecraft.advancements.*;
import net.minecraft.world.effect.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.advancement.*;

import java.util.function.*;

public enum ModAdvancementTriggers {
    NEGATE_FALL_DAMAGE_FROM_COTTON_BALE(
        "negate_damage_from_cotton_bale",
        NegateFallDamageOnCottonBaleTrigger::new,
        NegateFallDamageOnCottonBaleTrigger::instance
    ),
    ;

    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
            "Found {} trigger types",
            CozyLiving.TRIGGER_TYPES.getEntries().size()
        );
        CozyLiving.TRIGGER_TYPES.register(modEventBus);
    }

    private final Supplier<CriterionTrigger<? extends CriterionTriggerInstance>> myValue;
    private final Supplier<? extends CriterionTriggerInstance> myInstanceSupplier;

    <T extends CriterionTriggerInstance> ModAdvancementTriggers(
        String name,
        Supplier<CriterionTrigger<T>> supplier,
        Supplier<T> instanceSupplier
    ) {
        myValue = CozyLiving.TRIGGER_TYPES.register(name, supplier);
        myInstanceSupplier = instanceSupplier;
    }

    public CriterionTrigger<?> get() {
        return myValue.get();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public Criterion getCriterion() {
        return ((CriterionTrigger)myValue.get()).createCriterion(myInstanceSupplier.get());
    }
}

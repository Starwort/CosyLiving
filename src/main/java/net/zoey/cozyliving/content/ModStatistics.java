package net.zoey.cozyliving.content;

import net.minecraft.resources.*;
import net.minecraft.stats.*;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;

public enum ModStatistics {
    LAND_ON_COTTON_BALE("land_on_cotton_bale"),
    ;

    private final DeferredHolder<ResourceLocation, ResourceLocation> myValue;
    private final StatFormatter myFormatter;

    ModStatistics(String id, StatFormatter formatter) {
        var myKey = CozyLiving.loc(id);
        myFormatter = formatter;
        myValue = CozyLiving.STATISTICS.register(id, () -> myKey);
    }

    ModStatistics(String id) {
        this(id, StatFormatter.DEFAULT);
    }

    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
            "Found {} statistics",
            CozyLiving.STATISTICS.getEntries().size()
        );
        CozyLiving.STATISTICS.register(modEventBus);
    }

    ResourceLocation key() {
        return myValue.get();
    }

    Stat<ResourceLocation> asStat() {
        return Stats.CUSTOM.get(key(), myFormatter);
    }
}

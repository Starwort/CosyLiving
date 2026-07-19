package net.zoey.cozyliving.level.gen;

import net.neoforged.bus.api.*;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.level.RaspberryBushesFeature;
import net.zoey.cozyliving.level.gen.coconut_tree.CoconutTreeFeature;

public class ModCustomFeatures {
    public static void RegisterFeatures(IEventBus modEventBus){

        var registry = CozyLiving.FEATURES;

        CozyLiving.LOGGER.info("Registering custom features");
        CoconutTreeFeature.register();
        RaspberryBushesFeature.register();

        registry.register(modEventBus);
    }
}

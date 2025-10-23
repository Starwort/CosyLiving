package net.zoey.cozyliving.content;

import net.minecraft.core.registries.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;

public class ModCreativeTabs {
    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info("Found {} creative tabs", REGISTER.getEntries().size());
        REGISTER.register(modEventBus);
    }

    public static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
        CozyLiving.MODID
    );
}

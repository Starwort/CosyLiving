package net.zoey.cozyliving.content;

import net.minecraft.core.registries.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;

public class ModBlocks {
    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info("Found {} blocks", REGISTER.getEntries().size());
        REGISTER.register(modEventBus);
    }

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS,
        CozyLiving.MODID
    );
}

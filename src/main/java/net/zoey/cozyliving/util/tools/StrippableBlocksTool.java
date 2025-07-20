package net.zoey.cozyliving.util.tools;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.zoey.cozyliving.block.ModBlocks;

public class StrippableBlocksTool {
    public static void addToRegister(){
        StrippableBlockRegistry.register(ModBlocks.COCONUT_LOG, ModBlocks.STRIPPED_COCONUT_LOG);
        StrippableBlockRegistry.register(ModBlocks.COCONUT_WOOD, ModBlocks.STRIPPED_COCONUT_WOOD);
    }
}

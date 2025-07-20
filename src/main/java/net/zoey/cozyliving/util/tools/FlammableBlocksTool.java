package net.zoey.cozyliving.util.tools;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.zoey.cozyliving.block.ModBlocks;

public class FlammableBlocksTool {
    public static void addToRegister(){
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.COCONUT_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.COCONUT_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_COCONUT_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_COCONUT_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.COCONUT_PLANKS, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.COTTON_BALE, 25, 25);
    }
}

package net.zoey.cozyliving.util.tools;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.item.ModItems;

public class ModFuelRegistry {
    public static void AddItemsToFuelRegistry(){
        FuelRegistry.INSTANCE.add(ModItems.FLOWER_CROWN, 100);
        FuelRegistry.INSTANCE.add(ModItems.COTTON_SHRUB, 100);
        FuelRegistry.INSTANCE.add(ModItems.COTTON_BOLL, 33);
    }
}
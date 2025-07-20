package net.zoey.cozyliving.util.tools;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.item.ModItems;

public class CompostingTool {
    public static void addToRegister(){
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.COCONUT, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.APPLE_SAUCE, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CANDY_APPLE, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CINNAMON_BUN, 0.65f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.RASPBERRY, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.ROASTED_MELON_SEEDS, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.ROASTED_PUMPKIN_SEEDS, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.WATERMELON_POPSICLE, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.GLOWBERRY_TART, 1f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.GLOWBERRY_TART_SLICE, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.RASPBERRY_PIE, 1f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.RASPBERRY_PIE_SLICE, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CINNAMON_PIE, 0.100f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CINNAMON_PIE_SLICE, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.CINNAMON_STICK, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.GILDED_CINNAMON_STICK, 1f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.FLOWER_CROWN, 1f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.COCONUT_SAPLING, 0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModBlocks.COCONUT_LEAVES,0.30f);
        CompostingChanceRegistry.INSTANCE.add(ModItems.GOOPY_CHORUS,0.30f);
    }
}

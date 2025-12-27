package net.zoey.cozyliving.util;

import net.minecraft.world.level.block.ComposterBlock;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.content.ModItems;

public class CompostingUtil {
    public static void addToRegister(){

        ComposterBlock.COMPOSTABLES.put(ModBlocks.COCONUT.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.APPLE_SAUCE.item(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.CANDY_APPLE.item(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.CINNAMON_BUN.item(), 0.65f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.RASPBERRY_BUSH.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.ROASTED_MELON_SEEDS.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.ROASTED_PUMPKIN_SEEDS.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.WATERMELON_POPSICLE.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.GLOWBERRY_TART.item(), 1f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.GLOWBERRY_TART_SLICE.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.RASPBERRY_PIE.item(), 1f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.RASPBERRY_PIE_SLICE.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.CINNAMON_PIE.item(), 0.100f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.CINNAMON_PIE_SLICE.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.GOLDEN_CARROT_CAKE.item(), 0.100f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.GOLDEN_CARROT_CAKE_SLICE.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModItems.CINNAMON_STICK.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModItems.GILDED_CINNAMON_STICK.item(), 1f);
        ComposterBlock.COMPOSTABLES.put(ModItems.FLOWER_CROWN.item(), 1f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.COCONUT_SAPLING.item(), 0.30f);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.COCONUT_LEAVES.item(),0.30f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.GOOPY_CHORUS.item(),0.30f);
        ComposterBlock.COMPOSTABLES.put(ModItems.Food.BUTTERSCOTCH_STAR.item(),0.30f);
    }
}


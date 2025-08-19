package net.zoey.cozyliving;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
//import net.zoey.cozyliving.BrewingStandItems.ModBrewingStandItems;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.effect.ModEffects;
import net.zoey.cozyliving.entity.ModBoats;
import net.zoey.cozyliving.entity.ModEntities;
import net.zoey.cozyliving.entity.custom.LadyBeetleEntity;
import net.zoey.cozyliving.foodComponents.ModFoodComponents;
import net.zoey.cozyliving.item.ModItemGroups;
import net.zoey.cozyliving.item.ModItems;
import net.zoey.cozyliving.sound.ModSounds;
import net.zoey.cozyliving.statistic.ModStatistics;
import net.zoey.cozyliving.util.ModDamageTypes;
import net.zoey.cozyliving.util.ModLootTableModifiers;
import net.zoey.cozyliving.util.ModTags;
import net.zoey.cozyliving.util.tools.*;
import net.zoey.cozyliving.world.gen.ModFeatureGeneration;
import net.zoey.cozyliving.world.gen.coconut_tree.CoconutTreeFeature;
import net.zoey.cozyliving.world.gen.raspberry_bush_patches.RaspberryBushesFeature;
import net.zoey.cozyliving.world.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CozyLiving implements ModInitializer {
	public static final String MOD_ID = "cozyliving";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Cozy Living Initializing!");

		ModStatistics.registerModStatistics();
		ModItems.registerModItems();


		ModDamageTypes.registerDamageTypes();
		ModSounds.registerSounds();
		ModFoodComponents.registerModFoodComponents();

		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
		ModLootTableModifiers.modifyLootTables();
		RaspberryBushesFeature.registerRaspberryBushesFeature();
		CoconutTreeFeature.registerCoconutTreeFeature();
		ModTrunkPlacerTypes.register();
		ModTags.registerModTags();

		ModFeatureGeneration.generateVegetation();
		ModFeatureGeneration.generateOres();
		ModFeatureGeneration.generateTrees();
		ModBoats.registerBoats();

		ModEffects.registerEffects();

		//Tools
		CompostingTool.addToRegister();
		StrippableBlocksTool.addToRegister();
		FlammableBlocksTool.addToRegister();
		ModFuelRegistry.AddItemsToFuelRegistry();

		//Entities
		FabricDefaultAttributeRegistry.register(ModEntities.LADYBEETLE, LadyBeetleEntity.createLadyBeetleAttributes());

		//ModBrewingStandItems.registerBrewingStandItems();

		//FabricBrewingRecipeRegistry.registerItemRecipe(Potions.WATER, ModItems.RASPBERRY, ModItems.HERBAL_TEA);
		//FabricBrewingRecipeRegistry.registerItemRecipe();
		//BrewingRecipeRegistry.ITEM_RECIPES.add(new BrewingRecipeRegistry.Recipe(input, ingredient, output));
		//BrewingRecipeRegistry.registerItemRecipe(Potions.WATER, ModItems.RASPBERRY, ModItems.HERBAL_TEA);
	}

}
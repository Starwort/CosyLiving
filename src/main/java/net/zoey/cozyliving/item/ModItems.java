package net.zoey.cozyliving.item;

//import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;
//import net.zoey.cozyliving.entity.ModBoats;
//import net.zoey.cozyliving.entity.ModEntities;
import net.zoey.cozyliving.entity.ModBoats;
import net.zoey.cozyliving.entity.ModEntities;
import net.zoey.cozyliving.foodComponents.ModFoodComponents;
import net.zoey.cozyliving.item.custom.*;

import static net.minecraft.item.Items.*;
import static net.zoey.cozyliving.block.ModBlocks.COTTON_CROP;


public class ModItems {

    //DEBUG ITEMS
    public static final Item WAND_OF_HUNGER = registerItem("wand_of_hunger",
            new WandOfHungerItem(new Item.Settings().maxCount(1), "wand_of_hunger"));

    //INEDIBLE ITEMS
    public static final Item RASPBERRY_RHODOLITE = registerItem("raspberry_rhodolite",
            new ModTooltipItem(new Item.Settings().fireproof(), "raspberry_rhodolite"));

    public static final Item BENITOITE = registerItem("benitoite",
            new ModTooltipItem(new Item.Settings().fireproof(), "benitoite"));

    public static final Item CINNAMON_STICK = registerItem("cinnamon_stick",
            new ModTooltipItem(new Item.Settings(), "cinnamon_stick"));

    public static final Item GILDED_CINNAMON_STICK = registerItem("gilded_cinnamon_stick",
            new ModTooltipItem(new Item.Settings().rarity(Rarity.RARE), "gilded_cinnamon_stick"));



    //EDIBLE ITEMS
    public static final Item CINNAMON_BUN = registerItem("cinnamon_bun",
            new ModTooltipItem(new Item.Settings().food(ModFoodComponents.CINNAMON_BUN), "cinnamon_bun"));

    public static final Item COCONUT_MILK = registerItem("coconut_milk",
            new BottledItem(new Item.Settings().food(ModFoodComponents.COCONUT_MILK).recipeRemainder(GLASS_BOTTLE), 40,"coconut_milk"));

    public static final Item HEAVY_CREAM = registerItem("heavy_cream",
            new BottledItem(new Item.Settings().food(ModFoodComponents.HEAVY_CREAM).recipeRemainder(GLASS_BOTTLE), 90,"heavy_cream", SoundEvents.ITEM_HONEY_BOTTLE_DRINK));

    public static final Item CANDY_APPLE = registerItem("candy_apple",
            new CandyAppleItem(new Item.Settings().food(ModFoodComponents.CANDY_APPLE).recipeRemainder(STICK), "candy_apple"));

    public static final Item ROASTED_PUMPKIN_SEEDS = registerItem("roasted_pumpkin_seeds",
            new ModTooltipItem(new Item.Settings().food(ModFoodComponents.ROASTED_SEEDS), "roasted_pumpkin_seeds"));

    public static final Item ROASTED_MELON_SEEDS = registerItem("roasted_melon_seeds",
            new ModTooltipItem(new Item.Settings().food(ModFoodComponents.ROASTED_SEEDS), "roasted_melon_seeds"));

    public static final Item GLOWBERRY_TART_SLICE = registerItem("glowberry_tart_slice",
            new ModTooltipItem(new Item.Settings().food(ModFoodComponents.GLOWBERRY_TART), "glowberry_tart_slice"));

    public static final Item RASPBERRY_PIE_SLICE = registerItem("raspberry_pie_slice",
            new ModTooltipItem(new Item.Settings().food(ModFoodComponents.RASPBERRY_PIE), "raspberry_pie_slice"));

    public static final Item CINNAMON_PIE_SLICE = registerItem("cinnamon_pie_slice",
            new ModTooltipItem(new Item.Settings().food(ModFoodComponents.CINNAMON_PIE), "cinnamon_pie_slice"));

    public static final Item APPLE_SAUCE = registerItem("apple_sauce",
            new BottledItem(new Item.Settings().food(ModFoodComponents.APPLE_SAUCE).recipeRemainder(GLASS_BOTTLE), 15, "apple_sauce", SoundEvents.ITEM_HONEY_BOTTLE_DRINK));

    public static final Item WATERMELON_POPSICLE = registerItem("watermelon_popsicle",
            new FreezingItem(new Item.Settings().food(ModFoodComponents.WATERMELON_POPSICLE).recipeRemainder(STICK), "watermelon_popsicle", Items.STICK.getDefaultStack()));
    public static final Item HONEYCOMB_ICE_CREAM = registerItem("honeycomb_ice_cream",
            new FreezingItem(new Item.Settings().food(ModFoodComponents.ICE_CREAM).recipeRemainder(BOWL), "honeycomb_ice_cream", BOWL.getDefaultStack()));
    public static final Item COCONUT_ICE_CREAM = registerItem("coconut_ice_cream",
            new FreezingItem(new Item.Settings().food(ModFoodComponents.ICE_CREAM).recipeRemainder(BOWL), "coconut_ice_cream", BOWL.getDefaultStack()));
    public static final Item RASPBERRY_ICE_CREAM = registerItem("raspberry_ice_cream",
            new FreezingItem(new Item.Settings().food(ModFoodComponents.ICE_CREAM).recipeRemainder(BOWL), "raspberry_ice_cream", BOWL.getDefaultStack()));
    public static final Item TRIPLE_ICE_CREAM = registerItem("triple_ice_cream",
            new FreezingItem(new Item.Settings().food(ModFoodComponents.ICE_CREAM).recipeRemainder(BOWL), "triple_ice_cream", BOWL.getDefaultStack()));

    public static final Item VILLAGER_STEW = registerItem("villager_stew",
            new ContainerItem(new Item.Settings().food(ModFoodComponents.VILLAGER_STEW).recipeRemainder(BOWL), "villager_stew", BOWL.getDefaultStack()));

    public static final Item RASPBERRY_TEA = registerItem("raspberry_tea",
            new BottledItem(new Item.Settings().food(ModFoodComponents.RASPBERRY_TEA).recipeRemainder(GLASS_BOTTLE),40,"raspberry_tea"));
    public static final Item HERBAL_TEA = registerItem("herbal_tea",
            new BottledItem(new Item.Settings().food(ModFoodComponents.HERBAL_TEA).recipeRemainder(GLASS_BOTTLE),40,"herbal_tea"));
    public static final Item GILDED_TEA = registerItem("gilded_tea",
            new BottledItem(new Item.Settings().food(ModFoodComponents.GILDED_TEA).recipeRemainder(GLASS_BOTTLE),40,"gilded_tea"));
    public static final Item HOT_CHOCOLATE = registerItem("hot_chocolate",
            new BottledItem(new Item.Settings().food(ModFoodComponents.HOT_CHOCOLATE), 45, "hot_chocolate"));
    public static final Item HOTTER_CHOCOLATE = registerItem("hotter_chocolate",
            new HotterChocolateItem(new Item.Settings().food(ModFoodComponents.HOTTER_CHOCOLATE).recipeRemainder(GLASS_BOTTLE), "hotter_chocolate", GLASS_BOTTLE.getDefaultStack()));
    public static final Item SLEEPY_TEA = registerItem("sleepy_tea",
            new SleepyTeaItem(new Item.Settings().food(ModFoodComponents.SLEEPY_TEA).recipeRemainder(GLASS_BOTTLE), 40, "sleepy_tea"));
    public static final Item GOOPY_CHORUS = registerItem("goopy_chorus",
            new ModTooltipItem(new Item.Settings().food(ModFoodComponents.GOOPY_CHORUS), "goopy_chorus"));
    public static final Item MYCO_MEDLEY = registerItem("myco_medley",
            new ContainerItem(new Item.Settings().food(ModFoodComponents.MYCO_MEDLEY).recipeRemainder(BOWL), "myco_medley", BOWL.getDefaultStack()));
    public static final Item BERRY_BLEND_SMOOTHIE = registerItem("berry_blend_smoothie",
            new BottledItem(new Item.Settings().food(ModFoodComponents.BERRY_BLEND_SMOOTHIE).recipeRemainder(GLASS_BOTTLE),45,"berry_blend_smoothie"));
    public static final Item PINA_GLOWADA = registerItem("pina_glowada",
            new PinaGlowadaItem(new Item.Settings().food(ModFoodComponents.CINNAMON_PIE), "pina_glowada"));

    public static final Item RASPBERRY_JAM = registerItem("raspberry_jam",
            new BottledItem(new Item.Settings().food(ModFoodComponents.JAM).recipeRemainder(GLASS_BOTTLE),60,"raspberry_jam", SoundEvents.ITEM_HONEY_BOTTLE_DRINK));
    public static final Item APPLE_JAM = registerItem("apple_jam",
            new BottledItem(new Item.Settings().food(ModFoodComponents.JAM).recipeRemainder(GLASS_BOTTLE),60,"apple_jam", SoundEvents.ITEM_HONEY_BOTTLE_DRINK));
    public static final Item SWEETBERRY_JAM = registerItem("sweetberry_jam",
            new BottledItem(new Item.Settings().food(ModFoodComponents.JAM).recipeRemainder(GLASS_BOTTLE),60,"sweetberry_jam", SoundEvents.ITEM_HONEY_BOTTLE_DRINK));
    public static final Item GLOWBERRY_JAM = registerItem("glowberry_jam",
            new GlowberryJamItem(new Item.Settings().food(ModFoodComponents.JAM).recipeRemainder(GLASS_BOTTLE),"glowberry_jam"));


    //BLOCK ITEMS (MUST BE CALLED LAST, ELSE REQUIRED ITEMS MAY NOT BE REGISTERED

    public static final Item RASPBERRY = registerItem("raspberry",
            new ModAliasedBlockToolTipItem(ModBlocks.RASPBERRY_BUSH,new Item.Settings().food(ModFoodComponents.RASPBERRY), "raspberry"));

    public static final Item GLOWBERRY_TART = registerItem("glowberry_tart",
            new ModAliasedBlockToolTipItem(ModBlocks.GLOWBERRY_TART, new Item.Settings(), "glowberry_tart"));
    public static final Item RASPBERRY_PIE = registerItem("raspberry_pie",
            new ModAliasedBlockToolTipItem(ModBlocks.RASPBERRY_PIE, new Item.Settings(), "raspberry_pie"));
    public static final Item CINNAMON_PIE = registerItem("cinnamon_pie",
            new ModAliasedBlockToolTipItem(ModBlocks.CINNAMON_PIE, new Item.Settings(), "cinnamon_pie"));

    public static final Item COTTON_BOLL = registerItem("cotton_boll", new ModTooltipSeedItem(COTTON_CROP, new FabricItemSettings(), "cotton_boll"));

    public static final Item COTTON_SHRUB = registerItem("cotton_shrub", new TooltipBlockItem(ModBlocks.COTTON_SHRUB, new FabricItemSettings(), "cotton_shrub"));

    public static final Item COTTON_BALE = registerItem("cotton_bale", new TooltipBlockItem(ModBlocks.COTTON_BALE, new FabricItemSettings(), "cotton_bale"));

    public static final Item COCONUT_CRATE = registerItem("coconut_crate", new TooltipBlockItem(ModBlocks.COCONUT_CRATE, new FabricItemSettings(), "coconut_crate"));

    public static final Item RASPBERRY_CRATE = registerItem("raspberry_crate", new TooltipBlockItem(ModBlocks.RASPBERRY_CRATE, new FabricItemSettings(), "raspberry_crate"));


    //SIGN ITEMS
    public static final Item COCONUT_SIGN = registerItem("coconut_sign",
            new SignItem(new Item.Settings().maxCount(16), ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN));
    public static final Item HANGING_COCONUT_SIGN = registerItem("coconut_hanging_sign",
            new HangingSignItem(ModBlocks.COCONUT_HANGING_SIGN, ModBlocks.COCONUT_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    //BOAT ITEMS
    public static final Item COCONUT_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.COCONUT_BOAT_ID, ModBoats.COCONUT_BOAT_KEY, false);
    public static final Item COCONUT_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(ModBoats.COCONUT_CHEST_BOAT_ID, ModBoats.COCONUT_BOAT_KEY, true);


    //WEARABLE ITEMS
    public static final Item FLOWER_CROWN = registerItem("flower_crown",
            new TooltipArmorItem(ModArmorMaterials.FLOWER_CROWN, ArmorItem.Type.HELMET, new FabricItemSettings(), "flower_crown"));
            //new FlowerCrownItem(new FabricItemSettings(), "flower_crown"));


    //MISC
    public static final Item LADY_BEETLE_SPAWN_EGG = registerItem("lady_beetle_spawn_egg",
            new SpawnEggItem(ModEntities.LADYBEETLE, 0xd91845, 0x290911, new Item.Settings()));

    /*public static final Item RASPBERRY_JELLY_SPAWN_EGG = registerItem("raspberry_jelly_spawn_egg",
            new SpawnEggItem(ModEntities.JELLY, 0xe30b5d, 0x980036, new Item.Settings()));
    */

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CozyLiving.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CozyLiving.LOGGER.info("Registering Mod Items for " + CozyLiving.MOD_ID);
    }

}
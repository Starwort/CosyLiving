package net.zoey.cozyliving.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.item.ModItems;
import net.zoey.cozyliving.util.ModTags;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
    /*offerSmelting(consumer, RASPBERRY_RHODOLITE_SMELTABLES, RecipeCategory.MISC, ModItems.RASPBERRY_RHODOLITE, 15f, 200, "raspberry_rhodolite");
    offerBlasting(consumer, RASPBERRY_RHODOLITE_SMELTABLES, RecipeCategory.MISC, ModItems.RASPBERRY_RHODOLITE, 15f, 100, "raspberry_rhodolite");
    offerSmelting(consumer, BENITOITE_SMELTABLES, RecipeCategory.MISC, ModItems.BENITOITE, 15f, 200, "benitoite");
    offerBlasting(consumer, BENITOITE_SMELTABLES, RecipeCategory.MISC, ModItems.BENITOITE, 15f, 100, "benitoite");

    offerReversibleCompactingRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.RASPBERRY_RHODOLITE, RecipeCategory.DECORATIONS, ModBlocks.RASPBERRY_RHODOLITE_BLOCK);
    offerReversibleCompactingRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.BENITOITE, RecipeCategory.DECORATIONS, ModBlocks.BENITOITE_BLOCK);*/

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHARCOAL_INK, 3)
                .input(Items.CHARCOAL)
                .input(Items.GLASS_BOTTLE, 3)
                .criterion(hasItem(Items.CHARCOAL), conditionsFromItem(Items.CHARCOAL))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.CHARCOAL_INK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BUCKRAM, 2)
                .pattern("ab")
                .pattern("ba")
                .input('a', ModItems.COTTON_BOLL)
                .input('b', Items.STRING)
                .criterion(hasItem(ModItems.COTTON_BOLL), conditionsFromItem(ModItems.COTTON_BOLL))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.BUCKRAM)));

    ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.BENITOITE, 9)
            .input(ModBlocks.BENITOITE_BLOCK)
            .criterion(hasItem(ModBlocks.BENITOITE_BLOCK), conditionsFromItem(ModBlocks.BENITOITE_BLOCK))
            .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.BENITOITE) + "_from_block"));

    ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RASPBERRY_RHODOLITE, 9)
            .input(ModBlocks.RASPBERRY_RHODOLITE_BLOCK)
            .criterion(hasItem(ModBlocks.RASPBERRY_RHODOLITE_BLOCK), conditionsFromItem(ModBlocks.RASPBERRY_RHODOLITE_BLOCK))
            .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.RASPBERRY_RHODOLITE) + "_from_block"));

    ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RASPBERRY_RHODOLITE_BLOCK, 1)
            .pattern("aaa")
            .pattern("aaa")
            .pattern("aaa")
            .input('a', ModItems.RASPBERRY_RHODOLITE)
            .criterion(hasItem(ModItems.RASPBERRY_RHODOLITE), conditionsFromItem(ModItems.RASPBERRY_RHODOLITE))
            .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.RASPBERRY_RHODOLITE_BLOCK)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BENITOITE_BLOCK, 1)
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .input('a', ModItems.BENITOITE)
                .criterion(hasItem(ModItems.BENITOITE), conditionsFromItem(ModItems.BENITOITE))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.BENITOITE_BLOCK)));

        createSmeltingRecipe(consumer, ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE, ModItems.RASPBERRY_RHODOLITE, 15f, "raspberry_rhodolite");
        createSmeltingRecipe(consumer, ModBlocks.RASPBERRY_RHODOLITE_ORE, ModItems.RASPBERRY_RHODOLITE, 15f, "raspberry_rhodolite");

        createSmeltingRecipe(consumer, ModBlocks.DEEPSLATE_BENITOITE_ORE, ModItems.BENITOITE, 15f, "benitoite");
        createSmeltingRecipe(consumer, ModBlocks.BENITOITE_ORE, ModItems.BENITOITE, 15f, "benitoite");



    //FOOD:

    ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.APPLE_SAUCE, 4)
            .input(Items.APPLE)
            .input(Items.GLASS_BOTTLE, 4)
            .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
            .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.APPLE_SAUCE)));

    ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.BERRY_BLEND_SMOOTHIE, 1)
            .input(Items.SWEET_BERRIES)
            .input(Items.GLOW_BERRIES)
            .input(ModItems.RASPBERRY)
            .input(ModItems.COCONUT_MILK)
            .input(Items.GLASS_BOTTLE)
            .criterion(hasItem(ModItems.COCONUT_MILK), conditionsFromItem(ModItems.COCONUT_MILK))
            .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.BERRY_BLEND_SMOOTHIE)));

    ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CANDY_APPLE, 1)
            .input(Items.APPLE)
            .input(Items.HONEY_BOTTLE)
            .input(Items.SUGAR)
            .input(Items.STICK)
            .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
            .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.CANDY_APPLE)));

    ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CINNAMON_BUN, 1)
            .input(ModItems.CINNAMON_STICK)
            .input(Items.SUGAR)
            .input(ModItems.HEAVY_CREAM)
            .input(Items.WHEAT)
            .criterion(hasItem(ModItems.CINNAMON_STICK), conditionsFromItem(ModItems.CINNAMON_STICK))
            .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.CINNAMON_BUN)));

    ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CINNAMON_PIE, 1)
            .pattern("AAA")
            .pattern("BDB")
            .pattern("CCC")
            .input('A', ModItems.CINNAMON_STICK)
            .input('B', Items.SUGAR)
            .input('C', Items.WHEAT)
            .input('D', ModItems.HEAVY_CREAM)
            .criterion(hasItem(ModItems.CINNAMON_STICK), conditionsFromItem(ModItems.CINNAMON_STICK))
            .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.CINNAMON_PIE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.COCONUT_MILK, 3)
                .input(ModBlocks.COCONUT)
                .input(Items.GLASS_BOTTLE, 3)
                .criterion(hasItem(ModBlocks.COCONUT), conditionsFromItem(ModBlocks.COCONUT))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.COCONUT_MILK)));


        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.GILDED_TEA, 3)
                .input(ModItems.GILDED_CINNAMON_STICK)
                .input(ModItems.RASPBERRY_TEA)
                .input(Items.BLAZE_POWDER)
                .criterion(hasItem(ModItems.GILDED_CINNAMON_STICK), conditionsFromItem(ModItems.GILDED_CINNAMON_STICK))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.GILDED_TEA)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.GLOWBERRY_TART, 1)
                .pattern("AAA")
                .pattern("BDB")
                .pattern("CCC")
                .input('A', Items.GLOW_BERRIES)
                .input('B', Items.SUGAR)
                .input('C', Items.WHEAT)
                .input('D', ModItems.APPLE_SAUCE)
                .criterion(hasItem(Items.GLOW_BERRIES), conditionsFromItem(Items.GLOW_BERRIES))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.GLOWBERRY_TART)));

        CookingRecipeJsonBuilder.create(Ingredient.ofItems(Items.CHORUS_FLOWER), RecipeCategory.FOOD, ModItems.GOOPY_CHORUS, 1, 200, RecipeSerializer.SMELTING)
                .criterion(hasItem(Items.CHORUS_FLOWER), conditionsFromItem(Items.CHORUS_FLOWER))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "goopy_chorus_from_smelting"));
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(Items.CHORUS_FLOWER), RecipeCategory.FOOD, ModItems.GOOPY_CHORUS, 1, 100, RecipeSerializer.SMOKING)
                .criterion(hasItem(Items.CHORUS_FLOWER), conditionsFromItem(Items.CHORUS_FLOWER))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "goopy_chorus_from_smoking"));
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(Items.CHORUS_FLOWER), RecipeCategory.FOOD, ModItems.GOOPY_CHORUS, 1, 600, RecipeSerializer.CAMPFIRE_COOKING)
                .criterion(hasItem(Items.CHORUS_FLOWER), conditionsFromItem(Items.CHORUS_FLOWER))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "goopy_chorus_from_campfire_cooking"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.HEAVY_CREAM, 2)
                .input(ModBlocks.COCONUT)
                .input(Items.GLASS_BOTTLE, 2)
                .criterion(hasItem(ModBlocks.COCONUT), conditionsFromItem(ModBlocks.COCONUT))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.HEAVY_CREAM)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.HERBAL_TEA, 1)
                .input(ModItems.CINNAMON_STICK)
                .input(Items.POTION)
                .input(Items.BLAZE_POWDER)
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.HERBAL_TEA)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.HONEYCOMB_ICE_CREAM, 1)
                .input(Items.HONEYCOMB)
                .input(Items.SUGAR)
                .input(Items.SNOWBALL)
                .input(ModItems.HEAVY_CREAM)
                .input(Items.BOWL)
                .criterion(hasItem(Items.HONEYCOMB), conditionsFromItem(Items.HONEYCOMB))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.HONEYCOMB_ICE_CREAM)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.COCONUT_ICE_CREAM, 1)
                .input(ModBlocks.COCONUT)
                .input(Items.SUGAR)
                .input(Items.SNOWBALL)
                .input(ModItems.HEAVY_CREAM)
                .input(Items.BOWL)
                .criterion(hasItem(ModBlocks.COCONUT), conditionsFromItem(ModBlocks.COCONUT))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.COCONUT_ICE_CREAM)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RASPBERRY_ICE_CREAM, 1)
                .input(ModItems.RASPBERRY)
                .input(Items.SUGAR)
                .input(Items.SNOWBALL)
                .input(ModItems.HEAVY_CREAM)
                .input(Items.BOWL)
                .criterion(hasItem(ModItems.RASPBERRY), conditionsFromItem(ModItems.RASPBERRY))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.RASPBERRY_ICE_CREAM)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.TRIPLE_ICE_CREAM, 3)
                .input(ModItems.RASPBERRY_ICE_CREAM)
                .input(ModItems.COCONUT_ICE_CREAM)
                .input(ModItems.HONEYCOMB_ICE_CREAM)
                .criterion("has_ice_cream", conditionsFromTag(ModTags.Items.ICE_CREAMS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.TRIPLE_ICE_CREAM)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.HOT_CHOCOLATE, 2)
                .input(Items.COCOA_BEANS)
                .input(ModItems.HEAVY_CREAM)
                .input(ModItems.COCONUT_MILK)
                .input(ModItems.CINNAMON_STICK)
                .input(Items.SUGAR)
                .input(Items.GLASS_BOTTLE, 2)
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.HOT_CHOCOLATE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.HOTTER_CHOCOLATE, 1)
                .input(ModItems.HOT_CHOCOLATE)
                .input(Items.GUNPOWDER)
                .input(Items.BLAZE_POWDER)
                .input(Items.MAGMA_CREAM)
                .criterion(hasItem(ModItems.HOT_CHOCOLATE), conditionsFromItem(ModItems.HOT_CHOCOLATE))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.HOTTER_CHOCOLATE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.MYCO_MEDLEY, 1)
                .input(Items.BROWN_MUSHROOM)
                .input(Items.CRIMSON_FUNGUS)
                .input(Items.RED_MUSHROOM)
                .input(Items.WARPED_FUNGUS)
                .input(Items.BOWL)
                .criterion(hasItem(Items.CRIMSON_FUNGUS), conditionsFromItem(Items.CRIMSON_FUNGUS))
                .criterion(hasItem(Items.WARPED_FUNGUS), conditionsFromItem(Items.WARPED_FUNGUS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.MYCO_MEDLEY)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RASPBERRY_PIE, 1)
                .pattern("AAA")
                .pattern("BDB")
                .pattern("CCC")
                .input('A', ModItems.RASPBERRY)
                .input('B', Items.SUGAR)
                .input('C', Items.WHEAT)
                .input('D', ModItems.APPLE_SAUCE)
                .criterion(hasItem(ModItems.RASPBERRY), conditionsFromItem(ModItems.RASPBERRY))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.RASPBERRY_PIE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RASPBERRY_TEA, 1)
                .input(ModItems.RASPBERRY)
                .input(Items.POTION)
                .input(Items.BLAZE_POWDER)
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.RASPBERRY_TEA)));

        createCookingRecipe(consumer, Items.MELON_SEEDS, ModItems.ROASTED_MELON_SEEDS, 0.1f);
        createCookingRecipe(consumer, Items.PUMPKIN_SEEDS, ModItems.ROASTED_PUMPKIN_SEEDS, 0.1f);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SLEEPY_TEA, 1)
                .input(Items.HONEY_BOTTLE)
                .input(Items.ALLIUM)
                .input(ModItems.COCONUT_MILK)
                .input(Items.GLASS_BOTTLE, 2)
                .input(Items.BLAZE_POWDER)
                .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                .criterion(hasItem(Items.PHANTOM_MEMBRANE), conditionsFromItem(Items.PHANTOM_MEMBRANE))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.SLEEPY_TEA)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.PINA_GLOWADA, 1)
                .input(ModBlocks.COCONUT)
                .input(Items.SUGAR)
                .input(Items.GLOW_BERRIES)
                .input(Blocks.ICE)
                .input(Items.BAMBOO)
                .criterion(hasItem(ModBlocks.COCONUT), conditionsFromItem(ModBlocks.COCONUT))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.PINA_GLOWADA)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.VILLAGER_STEW, 1)
                .input(Items.WHEAT)
                .input(Items.POTATO)
                .input(Items.BEETROOT)
                .input(Items.CARROT)
                .input(Items.BOWL)
                .criterion(hasItem(Items.WHEAT), conditionsFromItem(Items.WHEAT))
                .criterion(hasItem(Items.POTATO), conditionsFromItem(Items.POTATO))
                .criterion(hasItem(Items.BEETROOT), conditionsFromItem(Items.BEETROOT))
                .criterion(hasItem(Items.CARROT), conditionsFromItem(Items.CARROT))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.VILLAGER_STEW)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.WATERMELON_POPSICLE, 1)
                .pattern("A")
                .pattern("B")
                .pattern("C")
                .input('A', Items.MELON_SLICE)
                .input('B', Items.SNOWBALL)
                .input('C', Items.STICK)
                .criterion(hasItem(Items.MELON_SLICE), conditionsFromItem(Items.MELON_SLICE))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.WATERMELON_POPSICLE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.CHOCOLATE_BAR, 1)
                .input(Items.COCOA_BEANS)
                .input(Items.SUGAR)
                .input(Items.PAPER)
                .input(ModItems.COCONUT_MILK)
                .criterion(hasItem(Items.COCOA_BEANS), conditionsFromItem(Items.COCOA_BEANS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.CHOCOLATE_BAR)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RED_SUGAR, 1)
                .input(Items.REDSTONE)
                .input(Items.SUGAR)
                .criterion(hasItem(Items.REDSTONE), conditionsFromItem(Items.REDSTONE))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.RED_SUGAR)));


        //JAMS
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.RASPBERRY_JAM, 1)
                .input(Items.SUGAR)
                .input(Items.GLASS_BOTTLE)
                .input(ModItems.RASPBERRY)
                .criterion(hasItem(ModItems.RASPBERRY), conditionsFromItem(ModItems.RASPBERRY))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.RASPBERRY_JAM)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.GLOWBERRY_JAM, 1)
                .input(Items.SUGAR)
                .input(Items.GLASS_BOTTLE)
                .input(Items.GLOW_BERRIES)
                .criterion(hasItem(Items.GLOW_BERRIES), conditionsFromItem(Items.GLOW_BERRIES))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.GLOWBERRY_JAM)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.SWEETBERRY_JAM, 1)
                .input(Items.SUGAR)
                .input(Items.GLASS_BOTTLE)
                .input(Items.SWEET_BERRIES)
                .criterion(hasItem(Items.SWEET_BERRIES), conditionsFromItem(Items.SWEET_BERRIES))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.SWEETBERRY_JAM)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.APPLE_JAM, 2)
                .input(Items.SUGAR, 2)
                .input(Items.GLASS_BOTTLE, 2)
                .input(Items.APPLE)
                .criterion(hasItem(Items.APPLE), conditionsFromItem(Items.APPLE))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.APPLE_JAM)));

        //BUILDING BLOCKS

        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModItems.COCONUT_BOAT)
                .input('A', ModBlocks.COCONUT_PLANKS)
                .pattern("A A")
                .pattern("AAA")
                .group("boat")
                .criterion("in_water", requireEnteringFluid(Blocks.WATER))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.COCONUT_BOAT)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, ModItems.COCONUT_CHEST_BOAT)
                .input('A', ModBlocks.COCONUT_PLANKS)
                .input('B', Items.CHEST)
                .pattern("ABA")
                .pattern("AAA")
                .group("chest_boat")
                .criterion("has_boat", conditionsFromTag(ItemTags.BOATS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.COCONUT_CHEST_BOAT)));


        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.COCONUT_SIGN, 3)
                .group("sign")
                .input('A', ModBlocks.COCONUT_PLANKS)
                .input('B', Items.STICK)
                .pattern("AAA")
                .pattern("AAA")
                .pattern(" B ")
                .criterion(hasItem(ModBlocks.COCONUT_PLANKS), conditionsFromItem(ModBlocks.COCONUT_PLANKS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_SIGN)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, ModBlocks.COCONUT_HANGING_SIGN, 6)
                .group("hanging_sign")
                .input('A', ModBlocks.STRIPPED_COCONUT_LOG)
                .input('B', Items.CHAIN)
                .pattern("B B")
                .pattern("AAA")
                .pattern("AAA")
                .criterion("has_stripped_logs", conditionsFromItem(ModBlocks.STRIPPED_COCONUT_LOG))
        .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_HANGING_SIGN)));

        createSingleItemRecipe(consumer, ModBlocks.COCONUT_BUTTON, ModBlocks.COCONUT_PLANKS, "wooden_button", RecipeCategory.BUILDING_BLOCKS);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_DOOR, 3)
                .pattern("AA")
                .pattern("AA")
                .pattern("AA")
                .input('A', ModBlocks.COCONUT_PLANKS)
                .group("wooden_doors")
                .criterion(hasItem(ModBlocks.COCONUT_PLANKS), conditionsFromItem(ModBlocks.COCONUT_PLANKS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_DOOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_FENCE, 3)
                .pattern("ABA")
                .pattern("ABA")
                .input('A', ModBlocks.COCONUT_PLANKS)
                .input('B', Items.STICK)
                .group("wooden_fences")

                .criterion(hasItem(ModBlocks.COCONUT_PLANKS), conditionsFromItem(ModBlocks.COCONUT_PLANKS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_FENCE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_FENCE_GATE, 1)
                .pattern("BAB")
                .pattern("BAB")
                .input('A', ModBlocks.COCONUT_PLANKS)
                .input('B', Items.STICK)
                .group("wooden_fence_gates")
                .criterion(hasItem(ModBlocks.COCONUT_PLANKS), conditionsFromItem(ModBlocks.COCONUT_PLANKS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_FENCE_GATE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_PLANKS, 4)
                .input(ModTags.Items.COCONUT_LOGS)
                .group("planks")
                .criterion("has_log", conditionsFromTag(ModTags.Items.COCONUT_LOGS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_PLANKS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.COCONUT_PRESSURE_PLATE, 1)
                .pattern("AA")
                .input('A', ModBlocks.COCONUT_PLANKS)
                .group("wooden_pressure_plates")
                .criterion(hasItem(ModBlocks.COCONUT_PLANKS), conditionsFromItem(ModBlocks.COCONUT_PLANKS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_PRESSURE_PLATE)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_STAIRS, 4)
                .pattern("A  ")
                .pattern("AA ")
                .pattern("AAA")
                .input('A', ModBlocks.COCONUT_PLANKS)
                .group("wooden_stair")
                .criterion(hasItem(ModBlocks.COCONUT_PLANKS), conditionsFromItem(ModBlocks.COCONUT_PLANKS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_STAIRS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_SLAB, 6)
                .pattern("AAA")
                .input('A', ModBlocks.COCONUT_PLANKS)
                .group("wooden_slabs")
                .criterion(hasItem(ModBlocks.COCONUT_PLANKS), conditionsFromItem(ModBlocks.COCONUT_PLANKS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_SLAB)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_TRAPDOOR, 2)
                .pattern("AAA")
                .pattern("AAA")
                .input('A', ModBlocks.COCONUT_PLANKS)
                .group("wooden_trap_doors")
                .criterion(hasItem(ModBlocks.COCONUT_PLANKS), conditionsFromItem(ModBlocks.COCONUT_PLANKS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_TRAPDOOR)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_WOOD, 3)
                .pattern("AA")
                .pattern("AA")
                .input('A', ModBlocks.COCONUT_LOG)
                .criterion(hasItem(ModBlocks.COCONUT_LOG), conditionsFromItem(ModBlocks.COCONUT_LOG))
                .group("coconut_wood")
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_WOOD)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_COCONUT_WOOD, 3)
                .pattern("AA")
                .pattern("AA")
                .input('A', ModBlocks.STRIPPED_COCONUT_LOG)
                .criterion(hasItem(ModBlocks.STRIPPED_COCONUT_LOG), conditionsFromItem(ModBlocks.STRIPPED_COCONUT_LOG))
                .group("coconut_wood")
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.STRIPPED_COCONUT_LOG)));

        //COTTON STUFF
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Blocks.WHITE_WOOL, 1)
                .pattern("AA")
                .pattern("AA")
                .input('A', ModItems.COTTON_BOLL)
                .criterion(hasItem(ModItems.COTTON_BOLL), conditionsFromItem(ModItems.COTTON_BOLL))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "white_wool_from_cotton"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.STRING, 3)
                .pattern("AAA")
                .input('A', ModItems.COTTON_BOLL)
                .criterion(hasItem(ModItems.COTTON_BOLL), conditionsFromItem(ModItems.COTTON_BOLL))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "string_from_cotton"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.COTTON_BALE, 1)
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .input('a', ModItems.COTTON_BOLL)
                .criterion(hasItem(ModItems.COTTON_BOLL), conditionsFromItem(ModItems.COTTON_BOLL))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.COTTON_BALE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.COTTON_BOLL, 9)
                .input(ModItems.COTTON_BALE)
                .criterion(hasItem(ModItems.COTTON_BALE), conditionsFromItem(ModItems.COTTON_BALE))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.COTTON_BOLL) + "_from_block"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.COCONUT_CRATE, 1)
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .input('a', ModBlocks.COCONUT)
                .criterion(hasItem(ModBlocks.COCONUT), conditionsFromItem(ModBlocks.COCONUT))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT_CRATE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.COCONUT, 9)
                .input(ModItems.COCONUT_CRATE)
                .criterion(hasItem(ModItems.COCONUT_CRATE), conditionsFromItem(ModBlocks.COCONUT))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModBlocks.COCONUT) + "_from_block"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModItems.RASPBERRY_CRATE, 1)
                .pattern("aaa")
                .pattern("aaa")
                .pattern("aaa")
                .input('a', ModItems.RASPBERRY)
                .criterion(hasItem(ModItems.RASPBERRY), conditionsFromItem(ModItems.RASPBERRY))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.RASPBERRY_CRATE)));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RASPBERRY, 9)
                .input(ModItems.RASPBERRY_CRATE)
                .criterion(hasItem(ModItems.RASPBERRY_CRATE), conditionsFromItem(ModItems.RASPBERRY_CRATE))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.RASPBERRY) + "_from_block"));



        //ALTERNATE RECIPES
        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, Blocks.STICKY_PISTON, 1)
                .input(ModTags.Items.JAMS)
                .input(Blocks.PISTON)
                .group("sticky_piston")
                .criterion("has_jam", conditionsFromTag(ModTags.Items.JAMS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "sticky_piston_from_jam"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PINK_DYE, 1)
                .input(ModItems.RASPBERRY)
                .group("pink_dye")
                .criterion(hasItem(ModItems.RASPBERRY), conditionsFromItem(ModItems.RASPBERRY))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "pink_dye_from_raspberry"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.MILK_BUCKET, 1)
                .input(Items.BUCKET)
                .input(ModItems.COCONUT_MILK, 3)
                .group("milk_bucket")
                .criterion(hasItem(ModItems.COCONUT_MILK), conditionsFromItem(ModItems.COCONUT_MILK))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "milk_bucket_from_coconut_milk"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, Items.ITEM_FRAME, 1)
                .pattern("aaa")
                .pattern("aba")
                .pattern("aaa")
                .input('a', Items.STICK)
                .input('b', ModItems.BUCKRAM)
                .criterion(hasItem(ModItems.BUCKRAM), conditionsFromItem(ModItems.BUCKRAM))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "item_frame_from_buckram"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.GLOW_ITEM_FRAME, 1)
                .input(ModItems.GLOWBERRY_JAM)
                .input(Items.ITEM_FRAME, 1)
                .criterion(hasItem(Items.GLOW_ITEM_FRAME), conditionsFromItem(Items.GLOW_ITEM_FRAME))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "glow_item_frame_from_glowberry_jam"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BOOK, 1)
                .input(Items.PAPER, 3)
                .input(ModItems.BUCKRAM)
                .criterion(hasItem(ModItems.BUCKRAM), conditionsFromItem(ModItems.BUCKRAM))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "book_from_buckram"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.BLACK_DYE, 2)
                .input(ModItems.CHARCOAL_INK)
                .criterion(hasItem(ModItems.CHARCOAL_INK), conditionsFromItem(ModItems.CHARCOAL_INK))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "black_dye_from_charcoal_ink"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.WRITABLE_BOOK, 1)
                .input(Items.BOOK)
                .input(ModItems.CHARCOAL_INK)
                .input(Items.FEATHER)
                .criterion(hasItem(Items.BOOK), conditionsFromItem(Items.BOOK))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, "writable_book_from_charcoal_ink"));



        //MISC
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.FLOWER_CROWN, 1)
                .pattern("AAA")
                .pattern("A A")
                .pattern("AAA")
                .input('A', ItemTags.SMALL_FLOWERS)
                .criterion("has_flowers", conditionsFromTag(ItemTags.SMALL_FLOWERS))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(ModItems.FLOWER_CROWN)));

    }

    public void createSingleItemRecipe(Consumer<RecipeJsonProvider> consumer, ItemConvertible input, ItemConvertible output, String group, RecipeCategory recipeCategory) {
        ShapelessRecipeJsonBuilder.create(recipeCategory, output, 1)
                .input(input)
                .criterion(hasItem(input), conditionsFromItem(input))
                .group(group)
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(input)));
    }

    public void createCookingRecipe(Consumer<RecipeJsonProvider> consumer, ItemConvertible input, ItemConvertible output, float experience){
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), RecipeCategory.FOOD, output, experience, 200, RecipeSerializer.SMELTING)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(input) + "_from_smelting"));
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), RecipeCategory.FOOD, output, experience, 100, RecipeSerializer.SMOKING)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(input) + "_from_smoking"));
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), RecipeCategory.FOOD, output, experience, 600, RecipeSerializer.CAMPFIRE_COOKING)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(input) + "_from_campfire_cooking"));
    }

    public void createSmeltingRecipe(Consumer<RecipeJsonProvider> consumer, ItemConvertible input, ItemConvertible output, float experience, String group){
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), RecipeCategory.FOOD, output, experience, 200, RecipeSerializer.SMELTING)
                .group(group)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(input) + "_from_smelting"));
        CookingRecipeJsonBuilder.create(Ingredient.ofItems(input), RecipeCategory.FOOD, output, experience, 100, RecipeSerializer.BLASTING)
                .group(group)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(consumer, new Identifier(CozyLiving.MOD_ID, getRecipeName(input) + "_from_blasting"));
    }

}

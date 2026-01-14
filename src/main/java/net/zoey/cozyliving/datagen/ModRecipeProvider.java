package net.zoey.cozyliving.datagen;

import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.*;
import net.minecraftforge.common.crafting.conditions.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.content.ModItems;
import net.zoey.cozyliving.content.ModTags;
import org.jetbrains.annotations.*;

import java.util.function.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {

        //TODO: Add butter / coconut butter / margarine, doesn't really make sense to make butterscotch without butter

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, ModItems.Food.BUTTERSCOTCH_STAR.item(), 3)
            .requires(Items.SUGAR)
            .requires(ModItems.Food.HEAVY_CREAM.item())
            .requires(Items.WHEAT)
            .unlockedBy(
                getHasName(Items.SUGAR),
                has(Items.SUGAR)
            ) //TODO: make this need cocoa butter
            .save(writer);

        ShapelessRecipeBuilder
                .shapeless(RecipeCategory.MISC, ModItems.Food.FORTUNE_COOKIE.item(), 1)
                .requires(Items.SUGAR)
                .requires(Items.PAPER)
                .requires(Items.WHEAT)
                .unlockedBy(
                        getHasName(Items.SUGAR),
                        has(Items.SUGAR)
                )
                .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, ModItems.CHARCOAL_INK.item(), 3)
            .requires(Items.CHARCOAL)
            .requires(Items.GLASS_BOTTLE, 3)
            .unlockedBy(getHasName(Items.CHARCOAL), has(Items.CHARCOAL))
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, ModItems.BUCKRAM.item(), 1)
            .pattern("go")
            .pattern("rb")
            .define('g', ModBlocks.COTTON_CROP.asItem())
            .define('o', Items.STRING)
            .define('r', Items.STRING)
            .define('b', ModBlocks.COTTON_CROP.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COTTON_CROP.asItem()),
                has(ModBlocks.COTTON_CROP.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, ModBlocks.GOLDEN_CARROT_CAKE.asItem(), 1)
            .pattern("hch")
            .pattern("ggg")
            .pattern("wsw")
            .define('h', ModItems.Food.HEAVY_CREAM.item())
            .define('s', Items.SUGAR)
            .define('g', Items.GOLDEN_CARROT)
            .define('c', ModItems.CINNAMON_STICK.item())
            .define('w', Items.WHEAT)
            .unlockedBy(getHasName(Items.GOLDEN_CARROT), has(Items.GOLDEN_CARROT))
            .save(writer);

        ShapedRecipeBuilder
                .shaped(RecipeCategory.MISC, ModBlocks.RED_VELVET_CAKE.asItem(), 1)
                .pattern("hsh")
                .pattern("bcb")
                .pattern("www")
                .define('h', ModItems.Food.HEAVY_CREAM.item())
                .define('s', Items.SUGAR)
                .define('b', Items.BEETROOT)
                .define('c', Items.COCOA_BEANS)
                .define('w', Items.WHEAT)
                .unlockedBy(getHasName(Items.BEETROOT), has(Items.BEETROOT))
                .save(writer);

        //fuck

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, ModItems.BENITOITE.item(), 9)
            .requires(ModBlocks.BENITOITE_BLOCK.asItem())
            .unlockedBy(
                getHasName(ModBlocks.BENITOITE_BLOCK.asItem()),
                has(ModBlocks.BENITOITE_BLOCK.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, ModItems.RASPBERRY_RHODOLITE.item(), 9)
            .requires(ModBlocks.RASPBERRY_RHODOLITE_BLOCK.asItem())
            .unlockedBy(
                getHasName(ModBlocks.RASPBERRY_RHODOLITE_BLOCK.asItem()),
                has(ModBlocks.RASPBERRY_RHODOLITE_BLOCK.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.RASPBERRY_RHODOLITE_BLOCK.asItem(),
                1
            )
            .pattern("aaa")
            .pattern("aaa")
            .pattern("aaa")
            .define('a', ModItems.RASPBERRY_RHODOLITE.item())
            .unlockedBy(
                getHasName(ModItems.RASPBERRY_RHODOLITE.item()),
                has(ModItems.RASPBERRY_RHODOLITE.item())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.BENITOITE_BLOCK.asItem(),
                1
            )
            .pattern("aaa")
            .pattern("aaa")
            .pattern("aaa")
            .define('a', ModItems.BENITOITE.item())
            .unlockedBy(
                getHasName(ModItems.BENITOITE.item()),
                has(ModItems.BENITOITE.item())
            )
            .save(writer);

        createSmeltingRecipe(
            writer,
            ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE.asItem(),
            ModItems.RASPBERRY_RHODOLITE.item(),
            15f,
            "raspberry_rhodolite"
        );
        createSmeltingRecipe(
            writer,
            ModBlocks.RASPBERRY_RHODOLITE_ORE.asItem(),
            ModItems.RASPBERRY_RHODOLITE.item(),
            15f,
            "raspberry_rhodolite"
        );

        createSmeltingRecipe(
            writer,
            ModBlocks.DEEPSLATE_BENITOITE_ORE.asItem(),
            ModItems.BENITOITE.item(),
            15f,
            "benitoite"
        );
        createSmeltingRecipe(
            writer,
            ModBlocks.BENITOITE_ORE.asItem(),
            ModItems.BENITOITE.item(),
            15f,
            "benitoite"
        );


        //FOOD:
        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.APPLE_SAUCE.item(), 4)
            .requires(Items.APPLE)
            .requires(Items.GLASS_BOTTLE, 4)
            .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(
                RecipeCategory.FOOD,
                ModItems.Food.BERRY_BLEND_SMOOTHIE.item(),
                1
            )
            .requires(Items.SWEET_BERRIES)
            .requires(Items.GLOW_BERRIES)
            .requires(ModBlocks.RASPBERRY_BUSH.asItem())
            .requires(ModItems.Food.COCONUT_MILK.item())
            .requires(Items.GLASS_BOTTLE)
            .unlockedBy(
                getHasName(ModItems.Food.COCONUT_MILK.item()),
                has(ModItems.Food.COCONUT_MILK.item())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.CANDY_APPLE.item(), 1)
            .requires(Items.APPLE)
            .requires(Items.HONEY_BOTTLE)
            .requires(Items.SUGAR)
            .requires(Items.STICK)
            .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.GOLDEN_CANDY_APPLE.item(), 1)
            .requires(Items.GOLDEN_APPLE)
            .requires(Items.HONEY_BOTTLE)
            .requires(Items.SUGAR)
            .requires(Items.STICK)
            .unlockedBy(getHasName(Items.GOLDEN_APPLE), has(Items.GOLDEN_APPLE))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(
                RecipeCategory.FOOD,
                ModItems.Food.ENCHANTED_GOLDEN_CANDY_APPLE.item(),
                1
            )
            .requires(Items.ENCHANTED_GOLDEN_APPLE)
            .requires(Items.HONEY_BOTTLE)
            .requires(Items.SUGAR)
            .requires(Items.STICK)
            .unlockedBy(
                getHasName(Items.ENCHANTED_GOLDEN_APPLE),
                has(Items.ENCHANTED_GOLDEN_APPLE)
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.CINNAMON_BUN.item(), 1)
            .requires(ModItems.CINNAMON_STICK.item())
            .requires(Items.SUGAR)
            .requires(ModItems.Food.HEAVY_CREAM.item())
            .requires(Items.WHEAT)
            .unlockedBy(
                getHasName(ModItems.CINNAMON_STICK.item()),
                has(ModItems.CINNAMON_STICK.item())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.FOOD, ModBlocks.CINNAMON_PIE.asItem(), 1)
            .pattern("AAA")
            .pattern("BDB")
            .pattern("CCC")
            .define('A', ModItems.CINNAMON_STICK.item())
            .define('B', Items.SUGAR)
            .define('C', Items.WHEAT)
            .define('D', ModItems.Food.HEAVY_CREAM.item())
            .unlockedBy(
                getHasName(ModItems.CINNAMON_STICK.item()),
                has(ModItems.CINNAMON_STICK.item())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.COCONUT_MILK.item(), 3)
            .requires(ModBlocks.COCONUT.asItem())
            .requires(Items.GLASS_BOTTLE, 3)
            .unlockedBy(
                getHasName(ModBlocks.COCONUT.asItem()),
                has(ModBlocks.COCONUT.asItem())
            )
            .save(writer);


        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.GILDED_TEA.item(), 3)
            .requires(ModItems.GILDED_CINNAMON_STICK.item())
            .requires(ModItems.Food.RASPBERRY_TEA.item())
            .requires(Items.BLAZE_POWDER)
            .unlockedBy(
                getHasName(ModItems.GILDED_CINNAMON_STICK.item()),
                has(ModItems.GILDED_CINNAMON_STICK.item())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.FOOD, ModBlocks.GLOWBERRY_TART.asItem(), 1)
            .pattern("AAA")
            .pattern("BDB")
            .pattern("CCC")
            .define('A', Items.GLOW_BERRIES)
            .define('B', Items.SUGAR)
            .define('C', Items.WHEAT)
            .define('D', ModItems.Food.APPLE_SAUCE.item())
            .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
            .save(writer);


        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.HEAVY_CREAM.item(), 2)
            .requires(ModBlocks.COCONUT.asItem())
            .requires(Items.GLASS_BOTTLE, 2)
            .unlockedBy(
                getHasName(ModBlocks.COCONUT.asItem()),
                has(ModBlocks.COCONUT.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.HERBAL_TEA.item(), 1)
            .requires(ModItems.CINNAMON_STICK.item())
            .requires(Items.POTION)
            .requires(Items.BLAZE_POWDER)
            .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.HONEYCOMB_ICE_CREAM.item(), 1)
            .requires(Items.HONEYCOMB)
            .requires(Items.SUGAR)
            .requires(Items.SNOWBALL)
            .requires(ModItems.Food.HEAVY_CREAM.item())
            .requires(Items.BOWL)
            .unlockedBy(getHasName(Items.HONEYCOMB), has(Items.HONEYCOMB))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.COCONUT_ICE_CREAM.item(), 1)
            .requires(ModBlocks.COCONUT.asItem())
            .requires(Items.SUGAR)
            .requires(Items.SNOWBALL)
            .requires(ModItems.Food.HEAVY_CREAM.item())
            .requires(Items.BOWL)
            .unlockedBy(
                getHasName(ModBlocks.COCONUT.asItem()),
                has(ModBlocks.COCONUT.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.RASPBERRY_ICE_CREAM.item(), 1)
            .requires(ModBlocks.RASPBERRY_BUSH.asItem())
            .requires(Items.SUGAR)
            .requires(Items.SNOWBALL)
            .requires(ModItems.Food.HEAVY_CREAM.item())
            .requires(Items.BOWL)
            .unlockedBy(
                getHasName(ModBlocks.RASPBERRY_BUSH.asItem()),
                has(ModBlocks.RASPBERRY_BUSH.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.TRIPLE_ICE_CREAM.item(), 3)
            .requires(ModItems.Food.RASPBERRY_ICE_CREAM.item())
            .requires(ModItems.Food.COCONUT_ICE_CREAM.item())
            .requires(ModItems.Food.HONEYCOMB_ICE_CREAM.item())
            .unlockedBy("has_ice_cream", has(ModTags.Items.ICE_CREAMS.get()))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.HOT_CHOCOLATE.item(), 2)
            .requires(Items.COCOA_BEANS)
            .requires(ModItems.Food.HEAVY_CREAM.item())
            .requires(ModItems.Food.COCONUT_MILK.item())
            .requires(ModItems.CINNAMON_STICK.item())
            .requires(Items.SUGAR)
            .requires(Items.GLASS_BOTTLE, 2)
            .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.HOTTER_CHOCOLATE.item(), 1)
            .requires(ModItems.Food.HOT_CHOCOLATE.item())
            .requires(Items.GUNPOWDER)
            .requires(Items.BLAZE_POWDER)
            .requires(Items.MAGMA_CREAM)
            .unlockedBy(
                getHasName(ModItems.Food.HOT_CHOCOLATE.item()),
                has(ModItems.Food.HOT_CHOCOLATE.item())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.MYCO_MEDLEY.item(), 1)
            .requires(Items.BROWN_MUSHROOM)
            .requires(Items.CRIMSON_FUNGUS)
            .requires(Items.RED_MUSHROOM)
            .requires(Items.WARPED_FUNGUS)
            .requires(Items.BOWL)
            .unlockedBy(getHasName(Items.CRIMSON_FUNGUS), has(Items.CRIMSON_FUNGUS))
            .unlockedBy(getHasName(Items.WARPED_FUNGUS), has(Items.WARPED_FUNGUS))
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.FOOD, ModBlocks.RASPBERRY_PIE.asItem(), 1)
            .pattern("AAA")
            .pattern("BDB")
            .pattern("CCC")
            .define('A', ModBlocks.RASPBERRY_BUSH.asItem())
            .define('B', Items.SUGAR)
            .define('C', Items.WHEAT)
            .define('D', ModItems.Food.APPLE_SAUCE.item())
            .unlockedBy(
                getHasName(ModBlocks.RASPBERRY_BUSH.asItem()),
                has(ModBlocks.RASPBERRY_BUSH.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.RASPBERRY_TEA.item(), 1)
            .requires(ModBlocks.RASPBERRY_BUSH.asItem())
            .requires(Items.POTION)
            .requires(Items.BLAZE_POWDER)
            .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
            .save(writer);

        createCookingRecipe(
            writer,
            Items.MELON_SEEDS,
            ModItems.Food.ROASTED_MELON_SEEDS.item(),
            0.1f
        );
        createCookingRecipe(
            writer,
            Items.PUMPKIN_SEEDS,
            ModItems.Food.ROASTED_PUMPKIN_SEEDS.item(),
            0.1f
        );
        createCookingRecipe(
            writer,
            Items.CHORUS_FLOWER,
            ModItems.Food.GOOPY_CHORUS.item(),
            2f
        );

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.SLEEPY_TEA.item(), 2)
            .requires(Items.HONEY_BOTTLE)
            .requires(Items.ALLIUM)
            .requires(ModItems.Food.COCONUT_MILK.item())
            .requires(Items.GLASS_BOTTLE, 2)
            .requires(Items.BLAZE_POWDER)
            .unlockedBy(getHasName(Items.BLAZE_POWDER), has(Items.BLAZE_POWDER))
            .unlockedBy(getHasName(Items.PHANTOM_MEMBRANE), has(Items.PHANTOM_MEMBRANE))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.PINA_GLOWADA.item(), 1)
            .requires(ModBlocks.COCONUT.asItem())
            .requires(Items.SUGAR)
            .requires(Items.GLOW_BERRIES)
            .requires(Blocks.ICE)
            .requires(Items.BAMBOO)
            .unlockedBy(
                getHasName(ModBlocks.COCONUT.asItem()),
                has(ModBlocks.COCONUT.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.VILLAGER_STEW.item(), 1)
            .requires(Items.WHEAT)
            .requires(Items.POTATO)
            .requires(Items.BEETROOT)
            .requires(Items.CARROT)
            .requires(Items.BOWL)
            .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
            .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
            .unlockedBy(getHasName(Items.BEETROOT), has(Items.BEETROOT))
            .unlockedBy(getHasName(Items.CARROT), has(Items.CARROT))
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.FOOD, ModItems.Food.WATERMELON_POPSICLE.item(), 1)
            .pattern("A")
            .pattern("B")
            .pattern("C")
            .define('A', Items.MELON_SLICE)
            .define('B', Items.SNOWBALL)
            .define('C', Items.STICK)
            .unlockedBy(getHasName(Items.MELON_SLICE), has(Items.MELON_SLICE))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.CHOCOLATE_BAR.item(), 1)
            .requires(Items.COCOA_BEANS)
            .requires(Items.SUGAR)
            .requires(Items.PAPER)
            .requires(ModItems.Food.COCONUT_MILK.item())
            .unlockedBy(getHasName(Items.COCOA_BEANS), has(Items.COCOA_BEANS))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.RED_SUGAR.item(), 1)
            .requires(Items.REDSTONE)
            .requires(Items.SUGAR)
            .unlockedBy(getHasName(Items.REDSTONE), has(Items.REDSTONE))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.MAO_CROQUI.item(), 8)
            .requires(ModBlocks.RASPBERRY_BUSH.asItem())
            .requires(Items.GLOW_BERRIES)
            .requires(Items.APPLE)
            .requires(Items.SWEET_BERRIES)
            .requires(Items.SUGAR)
            .unlockedBy(
                getHasName(ModBlocks.RASPBERRY_BUSH.asItem()),
                has(ModBlocks.RASPBERRY_BUSH.asItem())
            )
            .save(writer);


        //JAMS
        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.RASPBERRY_JAM.item(), 1)
            .requires(Items.SUGAR)
            .requires(Items.GLASS_BOTTLE)
            .requires(ModBlocks.RASPBERRY_BUSH.asItem())
            .unlockedBy(
                getHasName(ModBlocks.RASPBERRY_BUSH.asItem()),
                has(ModBlocks.RASPBERRY_BUSH.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.GLOWBERRY_JAM.item(), 1)
            .requires(Items.SUGAR)
            .requires(Items.GLASS_BOTTLE)
            .requires(Items.GLOW_BERRIES)
            .unlockedBy(getHasName(Items.GLOW_BERRIES), has(Items.GLOW_BERRIES))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.SWEETBERRY_JAM.item(), 1)
            .requires(Items.SUGAR)
            .requires(Items.GLASS_BOTTLE)
            .requires(Items.SWEET_BERRIES)
            .unlockedBy(getHasName(Items.SWEET_BERRIES), has(Items.SWEET_BERRIES))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.FOOD, ModItems.Food.APPLE_JAM.item(), 2)
            .requires(Items.SUGAR, 2)
            .requires(Items.GLASS_BOTTLE, 2)
            .requires(Items.APPLE)
            .unlockedBy(getHasName(Items.APPLE), has(Items.APPLE))
            .save(writer);

        createDonutRecipe(
            writer,
            ModItems.Food.RASPBERRY_JAM.item(),
            ModItems.Food.RASPBERRY_JAM_DOUGHNUT.item()
        );
        createDonutRecipe(
            writer,
            ModItems.Food.GLOWBERRY_JAM.item(),
            ModItems.Food.GLOWBERRY_JAM_DOUGHNUT.item()
        );
        createDonutRecipe(
            writer,
            ModItems.Food.APPLE_JAM.item(),
            ModItems.Food.APPLE_JAM_DOUGHNUT.item()
        );
        createDonutRecipe(
            writer,
            ModItems.Food.SWEETBERRY_JAM.item(),
            ModItems.Food.SWEETBERRY_JAM_DOUGHNUT.item()
        );
        createDonutRecipe(
            writer,
            ModItems.Food.HEAVY_CREAM.item(),
            ModItems.Food.CREAM_DOUGHNUT.item()
        );

        //BUILDING BLOCKS

        ShapedRecipeBuilder
            .shaped(RecipeCategory.TRANSPORTATION, ModItems.COCONUT_BOAT.item())
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .pattern("A A")
            .pattern("AAA")
            .group("boat")
            .unlockedBy("in_water", insideOf(Blocks.WATER))
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.TRANSPORTATION, ModItems.COCONUT_CHEST_BOAT.item())
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .define('B', Items.CHEST)
            .pattern("ABA")
            .pattern("AAA")
            .group("chest_boat")
            .unlockedBy("has_boat", has(ItemTags.BOATS))
            .save(writer);


        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.DECORATIONS,
                ModItems.COCONUT_SIGN.item().asItem(),
                3
            )
            .group("sign")
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .define('B', Items.STICK)
            .pattern("AAA")
            .pattern("AAA")
            .pattern(" B ")
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.DECORATIONS,
                ModItems.COCONUT_HANGING_SIGN.item().asItem(),
                6
            )
            .group("hanging_sign")
            .define('A', ModBlocks.STRIPPED_COCONUT_LOG.asItem())
            .define('B', Items.CHAIN)
            .pattern("B B")
            .pattern("AAA")
            .pattern("AAA")
            .unlockedBy(
                "has_stripped_logs",
                has(ModBlocks.STRIPPED_COCONUT_LOG.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COCONUT_BUTTON.asItem(),
                1
            )
            .requires(ModBlocks.COCONUT_PLANKS.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);


        ShapedRecipeBuilder
            .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_DOOR.asItem(), 3)
            .pattern("AA")
            .pattern("AA")
            .pattern("AA")
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .group("wooden_doors")
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_FENCE.asItem(), 3)
            .pattern("ABA")
            .pattern("ABA")
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .define('B', Items.STICK)
            .group("wooden_fences")

            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COCONUT_FENCE_GATE.asItem(),
                1
            )
            .pattern("BAB")
            .pattern("BAB")
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .define('B', Items.STICK)
            .group("wooden_fence_gates")
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COCONUT_PLANKS.asItem(),
                4
            )
            .requires(ModTags.Items.COCONUT_LOGS.get())
            .group("planks")
            .unlockedBy("has_log", has(ModTags.Items.COCONUT_LOGS.get()))
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.REDSTONE,
                ModBlocks.COCONUT_PRESSURE_PLATE.asItem(),
                1
            )
            .pattern("AA")
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .group("wooden_pressure_plates")
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COCONUT_STAIRS.asItem(),
                4
            )
            .pattern("A  ")
            .pattern("AA ")
            .pattern("AAA")
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .group("wooden_stair")
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_SLAB.asItem(), 6)
            .pattern("AAA")
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .group("wooden_slabs")
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.COCONUT_TRAPDOOR.asItem(),
                2
            )
            .pattern("AAA")
            .pattern("AAA")
            .define('A', ModBlocks.COCONUT_PLANKS.asItem())
            .group("wooden_trap_doors")
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_PLANKS.asItem()),
                has(ModBlocks.COCONUT_PLANKS.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_WOOD.asItem(), 3)
            .pattern("AA")
            .pattern("AA")
            .define('A', ModBlocks.COCONUT_LOG.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_LOG.asItem()),
                has(ModBlocks.COCONUT_LOG.asItem())
            )
            .group("coconut_wood")
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.STRIPPED_COCONUT_WOOD.asItem(),
                3
            )
            .pattern("AA")
            .pattern("AA")
            .define('A', ModBlocks.STRIPPED_COCONUT_LOG.asItem())
            .unlockedBy(
                getHasName(ModBlocks.STRIPPED_COCONUT_LOG.asItem()),
                has(ModBlocks.STRIPPED_COCONUT_LOG.asItem())
            )
            .group("coconut_wood")
            .save(writer);

        //COTTON STUFF
        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, Blocks.WHITE_WOOL, 1)
            .pattern("AA")
            .pattern("AA")
            .define('A', ModBlocks.COTTON_CROP.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COTTON_CROP.asItem()),
                has(ModBlocks.COTTON_CROP.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, Items.STRING, 3)
            .pattern("AAA")
            .define('A', ModBlocks.COTTON_CROP.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COTTON_CROP.asItem()),
                has(ModBlocks.COTTON_CROP.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COTTON_BALE.asItem(), 1)
            .pattern("aaa")
            .pattern("aaa")
            .pattern("aaa")
            .define('a', ModBlocks.COTTON_CROP.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COTTON_CROP.asItem()),
                has(ModBlocks.COTTON_CROP.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, ModBlocks.COTTON_CROP.asItem(), 9)
            .requires(ModBlocks.COTTON_BALE.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COTTON_BALE.asItem()),
                has(ModBlocks.COTTON_BALE.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.COCONUT_CRATE.asItem(), 1)
            .pattern("aaa")
            .pattern("aaa")
            .pattern("aaa")
            .define('a', ModBlocks.COCONUT.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COCONUT.asItem()),
                has(ModBlocks.COCONUT.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, ModBlocks.COCONUT.asItem(), 9)
            .requires(ModBlocks.COCONUT_CRATE.asItem())
            .unlockedBy(
                getHasName(ModBlocks.COCONUT_CRATE.asItem()),
                has(ModBlocks.COCONUT.asItem())
            )
            .save(writer);

        ShapedRecipeBuilder
            .shaped(
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.RASPBERRY_CRATE.asItem(),
                1
            )
            .pattern("aaa")
            .pattern("aaa")
            .pattern("aaa")
            .define('a', ModBlocks.RASPBERRY_BUSH.asItem())
            .unlockedBy(
                getHasName(ModBlocks.RASPBERRY_BUSH.asItem()),
                has(ModBlocks.RASPBERRY_BUSH.asItem())
            )
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, ModBlocks.RASPBERRY_BUSH.asItem(), 9)
            .requires(ModBlocks.RASPBERRY_CRATE.asItem())
            .unlockedBy(
                getHasName(ModBlocks.RASPBERRY_CRATE.asItem()),
                has(ModBlocks.RASPBERRY_CRATE.asItem())
            )
            .save(writer);


        //ALTERNATE RECIPES
        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.REDSTONE, Blocks.STICKY_PISTON, 1)
            .requires(ModTags.Items.JAMS.get())
            .requires(Blocks.PISTON)
            .group("sticky_piston")
            .unlockedBy("has_jam", has(ModTags.Items.JAMS.get()))
            .save(writer, CozyLiving.loc("sticky_piston_from_jam"));

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.PINK_DYE, 1)
            .requires(ModBlocks.RASPBERRY_BUSH.asItem())
            .group("pink_dye")
            .unlockedBy(
                getHasName(ModBlocks.RASPBERRY_BUSH.asItem()),
                has(ModBlocks.RASPBERRY_BUSH.asItem())
            )
            .save(writer, CozyLiving.loc("pink_dye_from_raspberry"));

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.MILK_BUCKET, 1)
            .requires(Items.BUCKET)
            .requires(ModItems.Food.COCONUT_MILK.item(), 3)
            .group("milk_bucket")
            .unlockedBy(
                getHasName(ModItems.Food.COCONUT_MILK.item()),
                has(ModItems.Food.COCONUT_MILK.item())
            )
            .save(writer, CozyLiving.loc("milk_bucket_from_coconut_milk"));

        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, Items.ITEM_FRAME, 1)
            .pattern("aaa")
            .pattern("aba")
            .pattern("aaa")
            .define('a', Items.STICK)
            .define('b', Tags.Items.LEATHER)
            .unlockedBy("has_any_leather", has(Tags.Items.LEATHER))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.GLOW_ITEM_FRAME, 1)
            .requires(ModTags.Items.GLOWING_ITEMS.get())
            .requires(Items.ITEM_FRAME, 1)
            .unlockedBy("has_any_glowing_item", has(ModTags.Items.GLOWING_ITEMS.get()))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.BOOK, 1)
            .requires(Items.PAPER, 3)
            .requires(Tags.Items.LEATHER)
            .unlockedBy("has_any_leather", has(Tags.Items.LEATHER))
            .save(writer);

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.BLACK_DYE, 2)
            .requires(ModItems.CHARCOAL_INK.item())
            .unlockedBy(
                getHasName(ModItems.CHARCOAL_INK.item()),
                has(ModItems.CHARCOAL_INK.item())
            )
            .save(writer, CozyLiving.loc("black_dye_from_charcoal_ink"));

        ShapelessRecipeBuilder
            .shapeless(RecipeCategory.MISC, Items.WRITABLE_BOOK, 1)
            .requires(Items.BOOK)
            .requires(ModItems.CHARCOAL_INK.item())
            .requires(Items.FEATHER)
            .unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
            .save(writer, CozyLiving.loc("book_and_quill_from_charcoal_ink"));

        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, Items.BUNDLE, 1)
            .pattern("a")
            .pattern("b")
            .define('a', Items.STRING)
            .define('b', Tags.Items.LEATHER)
            .unlockedBy("has_any_leather", has(Tags.Items.LEATHER))
            .save(writer);


        //MISC
        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, ModItems.FLOWER_CROWN.item(), 1)
            .pattern("AAA")
            .pattern("A A")
            .pattern("AAA")
            .define('A', ItemTags.SMALL_FLOWERS)
            .unlockedBy("has_flowers", has(ItemTags.SMALL_FLOWERS))
            .save(writer);

    }

    public void createDonutRecipe(
        Consumer<FinishedRecipe> writer,
        ItemLike filling,
        ItemLike output
    ) {
        ShapedRecipeBuilder
            .shaped(RecipeCategory.MISC, output, 1)
            .pattern(" A ")
            .pattern("BCB")
            .pattern(" D ")
            .define('A', Items.SUGAR)
            .define('B', Items.WHEAT)
            .define('C', filling)
            .define('D', ModItems.Food.COCONUT_MILK.item())
            .unlockedBy("has_filling", has(filling))
            .save(writer);
    }


    public void createCookingRecipe(
        Consumer<FinishedRecipe> writer,
        ItemLike input,
        ItemLike output,
        float experience
    ) {
        SimpleCookingRecipeBuilder
            .generic(
                Ingredient.of(input),
                RecipeCategory.FOOD,
                output,
                experience,
                200,
                RecipeSerializer.SMELTING_RECIPE
            )
            .unlockedBy(getHasName(input), has(input))
            .save(writer, getSimpleRecipeName(input) + "_from_smelting");
        SimpleCookingRecipeBuilder
            .smoking(Ingredient.of(input), RecipeCategory.FOOD, output, experience, 100)
            .unlockedBy(getHasName(input), has(input))
            .save(writer, getSimpleRecipeName(input) + "_from_smoking");
        SimpleCookingRecipeBuilder
            .campfireCooking(
                Ingredient.of(input),
                RecipeCategory.FOOD,
                output,
                experience,
                600
            )
            .unlockedBy(getHasName(input), has(input))
            .save(writer, getSimpleRecipeName(input) + "_from_campfire_cooking");

    }

    public void createSmeltingRecipe(
        Consumer<FinishedRecipe> writer,
        ItemLike input,
        ItemLike output,
        float experience,
        String group
    ) {
        SimpleCookingRecipeBuilder
            .generic(
                Ingredient.of(input),
                RecipeCategory.FOOD,
                output,
                experience,
                200,
                RecipeSerializer.SMELTING_RECIPE
            )
            .unlockedBy(getHasName(input), has(input))
            .save(writer, getSimpleRecipeName(input) + "_from_smelting");
        SimpleCookingRecipeBuilder
            .blasting(
                Ingredient.of(input),
                RecipeCategory.FOOD,
                output,
                experience,
                100
            )
            .unlockedBy(getHasName(input), has(input))
            .save(writer, getSimpleRecipeName(input) + "_from_blasting");
    }

}

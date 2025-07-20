package net.zoey.cozyliving.teas;

import com.google.common.collect.Lists;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.zoey.cozyliving.item.ModItems;

import java.util.List;

public class TeaRecipes {

    public static final List<teaPotionRecipe> TEA_POTION_RECIPES = Lists.newArrayList();
    public static final List<teaItemRecipe> TEA_ITEM_RECIPES = Lists.newArrayList();

    public static void registerTeaRecipe(Potion input, Item item, Item output) {
        //Raspberry Tea
        TEA_POTION_RECIPES.add(new teaPotionRecipe(Potions.WATER, Ingredient.ofItems(ModItems.RASPBERRY), ModItems.RASPBERRY_TEA));
        //Gilded Tea
        TEA_ITEM_RECIPES.add(new teaItemRecipe(ModItems.RASPBERRY_TEA, Ingredient.ofItems(ModItems.GILDED_CINNAMON_STICK), ModItems.GILDED_TEA));
        //Herbal Tea
        TEA_POTION_RECIPES.add(new teaPotionRecipe(Potions.WATER, Ingredient.ofItems(ModItems.CINNAMON_STICK), ModItems.HERBAL_TEA));
    }



    public static class teaPotionRecipe {
        public final Potion input;
        public final Ingredient ingredient;
        final ItemConvertible output;

        public teaPotionRecipe(Potion input, Ingredient ingredient, ItemConvertible output) {
            this.input = input;
            this.ingredient = ingredient;
            this.output = output;
        }
    }

        public static class teaItemRecipe {
            public final Item input;
            public final Ingredient ingredient;
            public final ItemConvertible output;

            public teaItemRecipe(Item input, Ingredient ingredient, ItemConvertible output) {
                this.input = input;
                this.ingredient = ingredient;
                this.output = output;
            }
        }

}



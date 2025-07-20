package net.zoey.cozyliving.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.zoey.cozyliving.teas.TeaRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


import static net.zoey.cozyliving.teas.TeaRecipes.TEA_ITEM_RECIPES;
//import static net.zoey.cozyliving.teas.TeaRecipes.TEA_RECIPES;

@Mixin(BrewingRecipeRegistry.class)
public abstract class TeaRecipesMixin {


    @Inject(method = "craft", at = @At("RETURN"))
    private static void injectTeaRecipes(ItemStack ingredient, ItemStack input, CallbackInfoReturnable<ItemStack> cir){

        Item item = input.getItem();
         int i = 0;
         int j = 0;
        TeaRecipes.teaItemRecipe recipe;

        for(j = TEA_ITEM_RECIPES.size(); i < j; ++i) {
            recipe = TEA_ITEM_RECIPES.get(i);
            if (recipe.input == item && recipe.ingredient.test(ingredient)) {
                //return PotionUtil.setPotion(new ItemStack(item), recipe.output);
            }
        }
    }

}

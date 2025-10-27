package net.zoey.cozyliving.datagen;

import net.minecraft.data.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.*;
import net.minecraftforge.common.crafting.conditions.*;
import net.zoey.cozyliving.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> writer) {
    }


    protected static void oreSmelting(
        @NotNull Consumer<FinishedRecipe> consumer,
        List<ItemLike> ingredients,
        @NotNull RecipeCategory category,
        @NotNull ItemLike result,
        float xp,
        int cookTime,
        @NotNull String group
    ) {
        oreCooking(
            consumer,
            RecipeSerializer.SMELTING_RECIPE,
            ingredients,
            category,
            result,
            xp,
            cookTime,
            group,
            "_from_smelting"
        );
    }

    protected static void oreBlasting(
        @NotNull Consumer<FinishedRecipe> consumer,
        List<ItemLike> ingredients,
        @NotNull RecipeCategory category,
        @NotNull ItemLike result,
        float xp,
        int cookTime,
        @NotNull String group
    ) {
        oreCooking(
            consumer,
            RecipeSerializer.BLASTING_RECIPE,
            ingredients,
            category,
            result,
            xp,
            cookTime,
            group,
            "_from_blasting"
        );
    }

    protected static void oreCooking(
        @NotNull Consumer<FinishedRecipe> consumer,
        @NotNull RecipeSerializer<? extends AbstractCookingRecipe> serializer,
        List<ItemLike> ingredients,
        @NotNull RecipeCategory category,
        @NotNull ItemLike result,
        float xp,
        int cookTime,
        @NotNull String group,
        String recipeName
    ) {
        for (ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder
                .generic(
                    Ingredient.of(itemlike),
                    category,
                    result,
                    xp,
                    cookTime,
                    serializer
                )
                .group(group)
                .unlockedBy(getHasName(itemlike), has(itemlike))
                .save(
                    consumer,
                    CozyLiving.MODID + ":" + getItemName(result) + recipeName + "_"
                        + getItemName(itemlike)
                );
        }
    }
}

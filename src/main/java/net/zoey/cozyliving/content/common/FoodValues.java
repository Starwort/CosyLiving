package net.zoey.cozyliving.content.common;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.zoey.cozyliving.content.*;

public enum FoodValues {

    //TODO: Redo all food values with purpose

    CINNAMON_BUN(new net.minecraft.world.food.FoodProperties.Builder()
        .nutrition(5)
        .saturationMod(1)
        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 150, 0), 1f)
        .build()),

    COCONUT_MILK(new FoodProperties.Builder().nutrition(2).saturationMod(0.5f).build()),

    HEAVY_CREAM(new FoodProperties.Builder().nutrition(3).saturationMod(0.5f).build()),

    CANDY_APPLE(new FoodProperties.Builder().nutrition(5).saturationMod(1.5f).build()),

    GOLDEN_CANDY_APPLE(new FoodProperties.Builder()
        .nutrition(6)
        .saturationMod(3f)
        .alwaysEat()
        .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 3600), 1f)
        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 150, 1), 1f)
        .build()),

    ENCHANTED_GOLDEN_CANDY_APPLE(new FoodProperties.Builder()
        .nutrition(6)
        .saturationMod(3f)
        .alwaysEat()
        .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 3600, 3), 1f)
        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600, 1), 1f)
        .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 9000), 1f)
        .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 9000), 1f)
        .build()),

    ROASTED_SEEDS(new FoodProperties.Builder()
        .nutrition(2)
        .saturationMod(0.25f)
        .fast()
        .build()),

    GLOWBERRY_TART(new FoodProperties.Builder()
        .nutrition(6)
        .saturationMod(0.75f)
        .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 150), 1f)
        .build()),

    PIES(new FoodProperties.Builder().nutrition(6).saturationMod(0.75f).build()),

    APPLE_SAUCE(new FoodProperties.Builder()
        .nutrition(1)
        .saturationMod(1f)
        .fast()
        .build()),

    WATERMELON_POPSICLE(new FoodProperties.Builder()
        .nutrition(3)
        .saturationMod(0.75f)
        .alwaysEat()
        .build()),

    ICE_CREAMS(new FoodProperties.Builder()
        .nutrition(5)
        .saturationMod(0.5f)
        .alwaysEat()
        .build()),

    VILLAGER_STEW(new FoodProperties.Builder()
        .nutrition(10)
        .saturationMod(0.75f)
        .build()),

    TEAS(new FoodProperties.Builder()
        .nutrition(4)
        .saturationMod(1f)
        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 150), 1f)
        .build()),

    GILDED_TEA(new FoodProperties.Builder()
        .nutrition(10)
        .saturationMod(1f)
        .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 18000), 1f)
        .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 18000), 1f)
        .effect(() -> new MobEffectInstance(MobEffects.LUCK, 18000), 1f)
        .build()),

    HOT_CHOCOLATE(new FoodProperties.Builder()
        .nutrition(6)
        .saturationMod(1f)
        .effect(
            () -> new MobEffectInstance(MobEffects.LUCK, 12000, 0, false, false),
            1f
        )
        .build()),

    HOTTER_CHOCOLATE(new FoodProperties.Builder().nutrition(6).saturationMod(1f)
        // TODO: Shouldn't this grant Luck?
        .build()),

    SLEEPY_TEA(new FoodProperties.Builder().nutrition(1).saturationMod(1f).build()),

    GOOPY_CHORUS(new FoodProperties.Builder()
        .nutrition(1)
        .saturationMod(1f)
        .alwaysEat()
        .effect(
            () -> new MobEffectInstance(
                ModEffects.THIRD_EYE_OPEN.effect(),
                6000,
                0
            ),
            1f
        )
        // DebilitatingNauseaMixin means we have no real need to set vanilla nausea also
        //        .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 6000), 1f)
        .build()),

    MYCO_MEDLEY(new FoodProperties.Builder()
        .nutrition(8)
        .saturationMod(0.75f)
        .effect(
            () -> new MobEffectInstance(
                MobEffects.FIRE_RESISTANCE,
                18000,
                0,
                false,
                false
            ),
            1f
        )
        .build()),

    BERRY_BLEND_SMOOTHIE(new FoodProperties.Builder()
        .nutrition(7)
        .saturationMod(0.75f)
        .build()),

    PINA_GLOWADA(new FoodProperties.Builder()
        .nutrition(7)
        .saturationMod(1f)
        .alwaysEat()
        .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 150), 1f)
        .build()),

    JAMS(new FoodProperties.Builder().nutrition(3).saturationMod(0.67f).build()),

    GLOW_JAM(new FoodProperties.Builder()
        .nutrition(3)
        .saturationMod(0.67f)
        .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 150), 1f)
        .build()),

    DOUGHNUTS(new FoodProperties.Builder().nutrition(7).saturationMod(0.75f).build()),

    GLOWNUTS(new FoodProperties.Builder()
        .nutrition(7)
        .saturationMod(0.75f)
        .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 150), 1f)
        .build()),

    CHOCOLATE_BAR(new FoodProperties.Builder()
        .nutrition(7)
        .saturationMod(0.75f)
        .build()),

    RED_SUGAR(new FoodProperties.Builder()
        .nutrition(1)
        .saturationMod(0.5f)
        .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 40, 2), 1f)
        .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 600, 1), 1f)
        .build()),

    MAO_CROQUI(new FoodProperties.Builder()
        .nutrition(1)
        .saturationMod(0.5f)
        .fast()
        .build()),

    RASPBERRY(new FoodProperties.Builder()
        .nutrition(1)
        .saturationMod(0.5f)
        .fast()
        .build()),

    BUTTERSCOTCH_STAR(new FoodProperties.Builder()
        .nutrition(7)
        .saturationMod(0.75f)
        .build());


    public final FoodProperties value;

    FoodValues(FoodProperties properties) {
        value = properties;
    }

    public Item.Properties intoProperties() {
        return new Item.Properties().food(value);
    }
}

package net.zoey.cozyliving.foodComponents;


import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.effect.ModEffects;

import static net.minecraft.entity.effect.StatusEffects.*;

public class ModFoodComponents {
    public static final FoodComponent CINNAMON_BUN = new FoodComponent.Builder()
            .hunger(5)
            .saturationModifier(1)
            .statusEffect(new StatusEffectInstance(REGENERATION, 150, 0), 1)
            .build();

    public static final FoodComponent PINA_GLOWADA = new FoodComponent.Builder()
            .hunger(7)
            .saturationModifier(1)
            .alwaysEdible()
            .build();
    public static final FoodComponent APPLE = (new net.minecraft.item.FoodComponent.Builder()).hunger(4).saturationModifier(0.3F).build();

    public static final FoodComponent JAM = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.67f).build();

    public static final FoodComponent RASPBERRY = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(0.5f)
            .snack()
            .build();
    public static final FoodComponent CANDY_APPLE = new FoodComponent.Builder()
            .hunger(5)
            .saturationModifier(1.5f)
            .snack()
            .build();
    public static final FoodComponent COCONUT_MILK = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(0.5f)
            .build();
    public static final FoodComponent HEAVY_CREAM = new FoodComponent.Builder()
            .hunger(3)
            .saturationModifier(0.5f)
            .build();
    public static final FoodComponent ROASTED_SEEDS = new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(0.25f)
            .snack()
            .build();
    public static final FoodComponent GLOWBERRY_TART = new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(GLOWING, 150, 0), 1)
            .build();
    public static final FoodComponent RASPBERRY_PIE = new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(0.75f)
            .build();
    public static final FoodComponent CINNAMON_PIE = new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(0.75f)
            .build();
    public static final FoodComponent APPLE_SAUCE = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(1)
            .snack()
            .build();
    public static final FoodComponent WATERMELON_POPSICLE = new FoodComponent.Builder()
            .hunger(3)
            .saturationModifier(0.75f)
            .alwaysEdible()
            .build();
    public static final FoodComponent VILLAGER_STEW = new FoodComponent.Builder()
            .hunger(10)
            .saturationModifier(0.75f)
            .build();
    public static final FoodComponent RASPBERRY_TEA = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(REGENERATION, 150, 0), 100)
            .build();
    public static final FoodComponent HERBAL_TEA = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(REGENERATION, 150, 0), 100)
            .build();
    public static final FoodComponent GILDED_TEA = new FoodComponent.Builder()
            .hunger(10)
            .saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(HASTE, 18000, 0), 100)
            .statusEffect(new StatusEffectInstance(SPEED, 18000, 0), 100)
            .statusEffect(new StatusEffectInstance(LUCK, 18000, 0), 100)
            .build();
    public static final FoodComponent MYCO_MEDLEY = new FoodComponent.Builder()
            .hunger(8)
            .saturationModifier(0.75f)
            .statusEffect(new StatusEffectInstance(FIRE_RESISTANCE, 18000, 0, false, false), 100)
            .build();
    public static final FoodComponent HOT_CHOCOLATE = new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(LUCK, 12000, 0, false, false), 100)
            .build();
    public static final FoodComponent HOTTER_CHOCOLATE = new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(1f)
            .build();
    public static final FoodComponent ICE_CREAM = new FoodComponent.Builder()
            .hunger(5)
            .saturationModifier(0.5f)
            .alwaysEdible()
            .alwaysEdible()
            .build();
    public static final FoodComponent SLEEPY_TEA = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(1f)
            .build();
    public static final FoodComponent GOOPY_CHORUS = new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(1f)
            .alwaysEdible()
            .statusEffect(new StatusEffectInstance(ModEffects.THIRD_EYE_OPEN, 6000, 0), 100)
            .statusEffect(new StatusEffectInstance(NAUSEA, 6000, 0), 100)
            .build();
    public static final FoodComponent BERRY_BLEND_SMOOTHIE = new FoodComponent.Builder()
            .hunger(7)
            .saturationModifier(0.75f)
            .build();

    public static void registerModFoodComponents() {
        CozyLiving.LOGGER.info("Registering Mod Food Components for " + CozyLiving.MOD_ID);
    }
}

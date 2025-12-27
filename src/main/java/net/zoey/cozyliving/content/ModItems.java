package net.zoey.cozyliving.content;

import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.common.*;
import net.zoey.cozyliving.content.entity.*;
import net.zoey.cozyliving.content.item.*;
import net.zoey.cozyliving.content.item.BoatItem;
import net.zoey.cozyliving.content.item.food.*;
import org.jetbrains.annotations.*;

import javax.annotation.Nullable;
import java.util.function.*;

public enum ModItems {
    WAND_OF_HUNGER(
        "wand_of_hunger", () -> new TooltipItem(new Item.Properties().stacksTo(1)) {
        @Override
        public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level,
            @NotNull Player player,
            @NotNull InteractionHand hand
        ) {
            var foodData = player.getFoodData();
            foodData.setFoodLevel(1);
            foodData.setSaturation(1);
            player.playSound(SoundEvents.AMETHYST_BLOCK_STEP, 1f, 1f);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        }
    }
    ),

    RASPBERRY_RHODOLITE(
        "raspberry_rhodolite",
        () -> new TooltipItem(new Item.Properties().fireResistant())
    ),

    BENITOITE(
        "benitoite",
        () -> new TooltipItem(new Item.Properties().fireResistant())
    ),

    CINNAMON_STICK("cinnamon_stick", TooltipItem::new),

    GILDED_CINNAMON_STICK(
        "gilded_cinnamon_stick",
        () -> new TooltipItem(new Item.Properties().rarity(Rarity.RARE))
    ),

    CHARCOAL_INK("charcoal_ink", CharcoalInkItem::new),

    BUCKRAM("buckram", TooltipItem::new),

    COCONUT_SIGN(
        "coconut_sign", () -> new SignItem(
        new Item.Properties().stacksTo(16),
        ModBlocks.COCONUT_SIGN.block(),
        ModBlocks.COCONUT_WALL_SIGN.block()
    )
    ),

    COCONUT_HANGING_SIGN(
        "coconut_hanging_sign", () -> new HangingSignItem(
        ModBlocks.COCONUT_HANGING_SIGN.block(),
        ModBlocks.COCONUT_WALL_HANGING_SIGN.block(),
        new Item.Properties().stacksTo(16)
    )
    ),

    COCONUT_BOAT(
        "coconut_boat",
        () -> new BoatItem(false, CustomBoat.Type.COCONUT, new Item.Properties())
    ),

    COCONUT_CHEST_BOAT(
        "coconut_chest_boat",
        () -> new BoatItem(true, CustomBoat.Type.COCONUT, new Item.Properties())
    ),

    FLOWER_CROWN(
        "flower_crown",
        () -> new ArmorItem(
            ArmourMaterials.FLOWER_CROWN,
            ArmorItem.Type.HELMET,
            new Item.Properties()
        )
        {
            @Override
            public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType) {
                return 200;
            }
        }
    );

    public static void register(IEventBus modEventBus) {
        Food.register();
        CozyLiving.LOGGER.info("Found {} items", CozyLiving.ITEMS.getEntries().size());
        CozyLiving.ITEMS.register(modEventBus);
    }

    private final RegistryObject<Item> myValue;

    ModItems(String name, Supplier<Item> supplier) {
        myValue = CozyLiving.ITEMS.register(name, supplier);
    }

    public RegistryObject<Item> registryObject() {
        return myValue;
    }

    public Item item() {
        return myValue.get();
    }

    public enum Food {
        CINNAMON_BUN(
            "cinnamon_bun",
            () -> new TooltipItem(FoodValues.CINNAMON_BUN.intoProperties())
        ),

        COCONUT_MILK(
            "coconut_milk",
            () -> new BottledFoodItem(FoodValues.COCONUT_MILK.intoProperties(), 40)
        ),

        HEAVY_CREAM(
            "heavy_cream",
            () -> new BottledFoodItem(
                FoodValues.HEAVY_CREAM.intoProperties(),
                90,
                SoundEvents.HONEY_DRINK
            )
        ),

        CANDY_APPLE(
            "candy_apple",
            () -> new CandyAppleItem(FoodValues.CANDY_APPLE.intoProperties())
        ),

        GOLDEN_CANDY_APPLE(
            "golden_candy_apple",
            () -> new CandyAppleItem(FoodValues.GOLDEN_CANDY_APPLE.intoProperties())
        ),

        ENCHANTED_GOLDEN_CANDY_APPLE(
            "enchanted_golden_candy_apple",
            () -> new CandyAppleItem(FoodValues.ENCHANTED_GOLDEN_CANDY_APPLE.intoProperties())
        ),

        ROASTED_PUMPKIN_SEEDS(
            "roasted_pumpkin_seeds",
            () -> new TooltipItem(FoodValues.ROASTED_SEEDS.intoProperties())
        ),

        ROASTED_MELON_SEEDS(
            "roasted_melon_seeds",
            () -> new TooltipItem(FoodValues.ROASTED_SEEDS.intoProperties())
        ),

        GLOWBERRY_TART_SLICE(
            "glowberry_tart_slice",
            () -> new TooltipItem(FoodValues.GLOWBERRY_TART.intoProperties())
        ),

        GOLDEN_CARROT_CAKE_SLICE(
                "golden_carrot_cake_slice",
                () -> new TooltipItem(FoodValues.GOLDEN_CARROT_CAKE.intoProperties())
        ),

        RASPBERRY_PIE_SLICE(
            "raspberry_pie_slice",
            () -> new TooltipItem(FoodValues.PIES.intoProperties())
        ),

        CINNAMON_PIE_SLICE(
            "cinnamon_pie_slice",
            () -> new TooltipItem(FoodValues.PIES.intoProperties())
        ),

        APPLE_SAUCE(
            "apple_sauce",
            () -> new BottledFoodItem(
                FoodValues.APPLE_SAUCE.intoProperties(),
                15,
                SoundEvents.HONEY_DRINK
            )
        ),

        WATERMELON_POPSICLE(
            "watermelon_popsicle",
            () -> new FrozenFoodItem(
                FoodValues.WATERMELON_POPSICLE.intoProperties(),
                Items.STICK
            )
        ),

        HONEYCOMB_ICE_CREAM(
            "honeycomb_ice_cream",
            () -> new FrozenFoodItem(FoodValues.ICE_CREAMS.intoProperties())
        ),

        COCONUT_ICE_CREAM(
            "coconut_ice_cream",
            () -> new FrozenFoodItem(FoodValues.ICE_CREAMS.intoProperties())
        ),

        RASPBERRY_ICE_CREAM(
            "raspberry_ice_cream",
            () -> new FrozenFoodItem(FoodValues.ICE_CREAMS.intoProperties())
        ),

        TRIPLE_ICE_CREAM(
            "triple_ice_cream",
            () -> new FrozenFoodItem(FoodValues.ICE_CREAMS.intoProperties())
        ),

        VILLAGER_STEW(
            "villager_stew",
            () -> new ResidueFoodItem.Drink(
                FoodValues.VILLAGER_STEW.intoProperties(),
                Items.BOWL
            )
        ),

        RASPBERRY_TEA(
            "raspberry_tea",
            () -> new BottledFoodItem(FoodValues.TEAS.intoProperties(), 40)
        ),

        HERBAL_TEA(
            "herbal_tea",
            () -> new BottledFoodItem(FoodValues.TEAS.intoProperties(), 40)
        ),

        GILDED_TEA(
            "gilded_tea",
            () -> new BottledFoodItem(FoodValues.GILDED_TEA.intoProperties(), 40)
        ),

        HOT_CHOCOLATE(
            "hot_chocolate",
            () -> new BottledFoodItem(FoodValues.HOT_CHOCOLATE.intoProperties(), 45)
        ),

        HOTTER_CHOCOLATE(
            "hotter_chocolate",
            () -> new BottledFoodItem(
                FoodValues.HOTTER_CHOCOLATE.intoProperties(),
                45
            )
            {
                @Override
                public @NotNull ItemStack finishUsingItem(
                    @NotNull ItemStack stack,
                    @NotNull Level level,
                    @NotNull LivingEntity user
                ) {
                    if (!level.isClientSide) {
                        user.setSharedFlagOnFire(true);
                        user.setRemainingFireTicks(300);
                    }
                    user.playSound(SoundEvents.FIRECHARGE_USE, 1f, 1f);
                    return super.finishUsingItem(stack, level, user);
                }
            }
        ),

        SLEEPY_TEA(
            "sleepy_tea",
            () -> new BottledFoodItem(FoodValues.SLEEPY_TEA.intoProperties(), 45)
        ),

        GOOPY_CHORUS(
            "goopy_chorus",
            () -> new TooltipItem(FoodValues.GOOPY_CHORUS.intoProperties())
        ),

        MYCO_MEDLEY(
            "myco_medley",
            () -> new ResidueFoodItem.Drink(
                FoodValues.MYCO_MEDLEY.intoProperties(),
                Items.BOWL
            )
        ),

        BERRY_BLEND_SMOOTHIE(
            "berry_blend_smoothie",
            () -> new BottledFoodItem(
                FoodValues.BERRY_BLEND_SMOOTHIE.intoProperties(),
                45
            )
        ),

        PINA_GLOWADA(
            "pina_glowada",
            () -> new TooltipItem(FoodValues.PINA_GLOWADA.intoProperties()) {
                @Override
                public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
                    return UseAnim.DRINK;
                }

                @Override
                public @NotNull SoundEvent getDrinkingSound() {
                    return SoundEvents.GENERIC_DRINK;
                }

                @Override
                public int getUseDuration(@NotNull ItemStack stack) {
                    return 45;
                }
            }
        ),

        RASPBERRY_JAM(
            "raspberry_jam",
            () -> new BottledFoodItem(
                FoodValues.JAMS.intoProperties(),
                60,
                SoundEvents.HONEY_DRINK
            )
        ),

        APPLE_JAM(
            "apple_jam",
            () -> new BottledFoodItem(
                FoodValues.JAMS.intoProperties(),
                60,
                SoundEvents.HONEY_DRINK
            )
        ),

        SWEETBERRY_JAM(
            "sweetberry_jam",
            () -> new BottledFoodItem(
                FoodValues.JAMS.intoProperties(),
                60,
                SoundEvents.HONEY_DRINK
            )
        ),

        GLOWBERRY_JAM(
            "glowberry_jam",
            () -> new GlowBottledFoodItem(
                FoodValues.GLOW_JAM.intoProperties(),
                60,
                SoundEvents.HONEY_DRINK
            )
        ),

        RASPBERRY_JAM_DOUGHNUT(
            "raspberry_jam_doughnut",
            () -> new TooltipItem(FoodValues.DOUGHNUTS.intoProperties())
        ),

        GLOWBERRY_JAM_DOUGHNUT(
            "glowberry_jam_doughnut",
            () -> new TooltipItem(FoodValues.GLOWNUTS.intoProperties())
        ),

        APPLE_JAM_DOUGHNUT(
            "apple_jam_doughnut",
            () -> new TooltipItem(FoodValues.DOUGHNUTS.intoProperties())
        ),

        SWEETBERRY_JAM_DOUGHNUT(
            "sweetberry_jam_doughnut",
            () -> new TooltipItem(FoodValues.DOUGHNUTS.intoProperties())
        ),

        CREAM_DOUGHNUT(
            "cream_doughnut",
            () -> new TooltipItem(FoodValues.DOUGHNUTS.intoProperties())
        ),

        CHOCOLATE_BAR(
            "chocolate_bar",
            () -> new TooltipItem(FoodValues.CHOCOLATE_BAR.intoProperties())
        ),

        RED_SUGAR(
            "red_sugar",
            () -> new TooltipItem(FoodValues.RED_SUGAR.intoProperties())
        ),

        MAO_CROQUI("mao_croqui", MaoCroquiItem::new),

        BUTTERSCOTCH_STAR(
                "butterscotch_star",
                () -> new TooltipItem(FoodValues.BUTTERSCOTCH_STAR.intoProperties())
        ),

        ;

        public static void register() {
            // no-op, just forces the class to load
        }

        private final RegistryObject<Item> myValue;

        Food(String name, Supplier<Item> supplier) {
            myValue = CozyLiving.ITEMS.register(name, supplier);
        }

        public RegistryObject<Item> registryObject() {
            return myValue;
        }

        public Item item() {
            return myValue.get();
        }
    }
}

package net.zoey.cozyliving.content;

import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
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

public class ModItems {
    public static void register(IEventBus modEventBus) {
        Food.register();
        CozyLiving.LOGGER.info("Found {} items", REGISTER.getEntries().size());
        REGISTER.register(modEventBus);
    }

    public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS,
        CozyLiving.MODID
    );

    public static class Food {
        public static void register() {
            // no-op, just forces the class to load
        }

        public static final RegistryObject<Item> CINNAMON_BUN = REGISTER.register(
            "cinnamon_bun",
            () -> new TooltipItem(FoodValues.CINNAMON_BUN.intoProperties())
        );
        public static final RegistryObject<Item> COCONUT_MILK = REGISTER.register(
            "coconut_milk",
            () -> new BottledFoodItem(FoodValues.COCONUT_MILK.intoProperties(), 40)
        );
        public static final RegistryObject<Item> HEAVY_CREAM = REGISTER.register(
            "heavy_cream",
            () -> new BottledFoodItem(
                FoodValues.HEAVY_CREAM.intoProperties(),
                90,
                SoundEvents.HONEY_DRINK
            )
        );

        public static final RegistryObject<Item> CANDY_APPLE = REGISTER.register(
            "candy_apple",
            () -> new CandyAppleItem(FoodValues.CANDY_APPLE.intoProperties())
        );
        public static final RegistryObject<Item> GOLDEN_CANDY_APPLE = REGISTER.register(
            "golden_candy_apple",
            () -> new CandyAppleItem(FoodValues.GOLDEN_CANDY_APPLE.intoProperties())
        );
        public static final RegistryObject<Item> ENCHANTED_GOLDEN_CANDY_APPLE = REGISTER.register(
            "enchanted_golden_candy_apple",
            () -> new CandyAppleItem(FoodValues.ENCHANTED_GOLDEN_CANDY_APPLE.intoProperties())
        );
        public static final RegistryObject<Item> ROASTED_PUMPKIN_SEEDS = REGISTER.register(
            "roasted_pumpkin_seeds",
            () -> new TooltipItem(FoodValues.ROASTED_SEEDS.intoProperties())
        );
        public static final RegistryObject<Item> ROASTED_MELON_SEEDS = REGISTER.register(
            "roasted_melon_seeds",
            () -> new TooltipItem(FoodValues.ROASTED_SEEDS.intoProperties())
        );

        public static final RegistryObject<Item> GLOWBERRY_TART_SLICE = REGISTER.register(
            "glowberry_tart_slice",
            () -> new TooltipItem(FoodValues.GLOWBERRY_TART.intoProperties())
        );

        public static final RegistryObject<Item> RASPBERRY_PIE_SLICE = REGISTER.register(
            "raspberry_pie_slice",
            () -> new TooltipItem(FoodValues.PIES.intoProperties())
        );

        public static final RegistryObject<Item> CINNAMON_PIE_SLICE = REGISTER.register(
            "cinnamon_pie_slice",
            () -> new TooltipItem(FoodValues.PIES.intoProperties())
        );

        public static final RegistryObject<Item> APPLE_SAUCE = REGISTER.register(
            "apple_sauce",
            () -> new BottledFoodItem(
                FoodValues.APPLE_SAUCE.intoProperties(),
                15,
                SoundEvents.HONEY_DRINK
            )
        );

        public static final RegistryObject<Item> WATERMELON_POPSICLE = REGISTER.register(
            "watermelon_popsicle",
            () -> new FrozenFoodItem(
                FoodValues.WATERMELON_POPSICLE.intoProperties(),
                Items.STICK
            )
        );

        public static final RegistryObject<Item> HONEYCOMB_ICE_CREAM = REGISTER.register(
            "honeycomb_ice_cream",
            () -> new FrozenFoodItem(FoodValues.ICE_CREAMS.intoProperties())
        );

        public static final RegistryObject<Item> COCONUT_ICE_CREAM = REGISTER.register(
            "coconut_ice_cream",
            () -> new FrozenFoodItem(FoodValues.ICE_CREAMS.intoProperties())
        );

        public static final RegistryObject<Item> RASPBERRY_ICE_CREAM = REGISTER.register(
            "raspberry_ice_cream",
            () -> new FrozenFoodItem(FoodValues.ICE_CREAMS.intoProperties())
        );

        public static final RegistryObject<Item> TRIPLE_ICE_CREAM = REGISTER.register(
            "triple_ice_cream",
            () -> new FrozenFoodItem(FoodValues.ICE_CREAMS.intoProperties())
        );

        public static final RegistryObject<Item> VILLAGER_STEW = REGISTER.register(
            "villager_stew",
            () -> new ResidueFoodItem.Drink(
                FoodValues.VILLAGER_STEW.intoProperties(),
                Items.BOWL
            )
        );

        public static final RegistryObject<Item> RASPBERRY_TEA = REGISTER.register(
            "raspberry_tea",
            () -> new BottledFoodItem(FoodValues.TEAS.intoProperties(), 40)
        );

        public static final RegistryObject<Item> HERBAL_TEA = REGISTER.register(
            "herbal_tea",
            () -> new BottledFoodItem(FoodValues.TEAS.intoProperties(), 40)
        );

        public static final RegistryObject<Item> GILDED_TEA = REGISTER.register(
            "gilded_tea",
            () -> new BottledFoodItem(FoodValues.GILDED_TEA.intoProperties(), 40)
        );

        public static final RegistryObject<Item> HOT_CHOCOLATE = REGISTER.register(
            "hot_chocolate",
            () -> new BottledFoodItem(FoodValues.HOT_CHOCOLATE.intoProperties(), 45)
        );

        public static final RegistryObject<Item> HOTTER_CHOCOLATE = REGISTER.register(
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
        );

        public static final RegistryObject<Item> SLEEPY_TEA = REGISTER.register(
            "sleepy_tea",
            () -> new BottledFoodItem(FoodValues.SLEEPY_TEA.intoProperties(), 45)
        );

        public static final RegistryObject<Item> GOOPY_CHORUS = REGISTER.register(
            "goopy_chorus",
            () -> new BottledFoodItem(FoodValues.GOOPY_CHORUS.intoProperties(), 45)
        );

        public static final RegistryObject<Item> MYCO_MEDLEY = REGISTER.register(
            "myco_medley",
            () -> new ResidueFoodItem.Drink(
                FoodValues.MYCO_MEDLEY.intoProperties(),
                Items.BOWL
            )
        );

        public static final RegistryObject<Item> BERRY_BLEND_SMOOTHIE = REGISTER.register(
            "berry_blend_smoothie",
            () -> new BottledFoodItem(
                FoodValues.BERRY_BLEND_SMOOTHIE.intoProperties(),
                45
            )
        );

        public static final RegistryObject<Item> PINA_GLOWADA = REGISTER.register(
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
        );

        public static final RegistryObject<Item> RASPBERRY_JAM = REGISTER.register(
            "raspberry_jam",
            () -> new BottledFoodItem(
                FoodValues.JAMS.intoProperties(),
                60,
                SoundEvents.HONEY_DRINK
            )
        );

        public static final RegistryObject<Item> APPLE_JAM = REGISTER.register(
            "apple_jam",
            () -> new BottledFoodItem(
                FoodValues.JAMS.intoProperties(),
                60,
                SoundEvents.HONEY_DRINK
            )
        );

        public static final RegistryObject<Item> SWEETBERRY_JAM = REGISTER.register(
            "sweetberry_jam",
            () -> new BottledFoodItem(
                FoodValues.JAMS.intoProperties(),
                60,
                SoundEvents.HONEY_DRINK
            )
        );

        public static final RegistryObject<Item> GLOWBERRY_JAM = REGISTER.register(
            "glowberry_jam",
            () -> new GlowBottledFoodItem(
                FoodValues.GLOW_JAM.intoProperties(),
                60,
                SoundEvents.HONEY_DRINK
            )
        );

        public static final RegistryObject<Item> RASPBERRY_JAM_DOUGHNUT = REGISTER.register(
            "raspberry_jam_doughnut",
            () -> new TooltipItem(FoodValues.DOUGHNUTS.intoProperties())
        );

        public static final RegistryObject<Item> GLOWBERRY_JAM_DOUGHNUT = REGISTER.register(
            "glowberry_jam_doughnut",
            () -> new TooltipItem(FoodValues.GLOWNUTS.intoProperties())
        );

        public static final RegistryObject<Item> APPLE_JAM_DOUGHNUT = REGISTER.register(
            "apple_jam_doughnut",
            () -> new TooltipItem(FoodValues.DOUGHNUTS.intoProperties())
        );

        public static final RegistryObject<Item> SWEETBERRY_JAM_DOUGHNUT = REGISTER.register(
            "sweetberry_jam_doughnut",
            () -> new TooltipItem(FoodValues.DOUGHNUTS.intoProperties())
        );

        public static final RegistryObject<Item> CREAM_DOUGHNUT = REGISTER.register(
            "cream_doughnut",
            () -> new TooltipItem(FoodValues.DOUGHNUTS.intoProperties())
        );

        public static final RegistryObject<Item> CHOCOLATE_BAR = REGISTER.register(
            "chocolate_bar",
            () -> new TooltipItem(FoodValues.CHOCOLATE_BAR.intoProperties())
        );

        public static final RegistryObject<Item> RED_SUGAR = REGISTER.register("red_sugar",
            () -> new TooltipItem(FoodValues.RED_SUGAR.intoProperties())
        );

        public static final RegistryObject<Item> MAO_CROQUI = REGISTER.register(
            "mao_croqui",
            () -> new TooltipItem(FoodValues.MAO_CROQUI.intoProperties())
        );

        public static final RegistryObject<Item> RASPBERRY = REGISTER.register(
            "raspberry", () -> new ItemNameTooltipBlockItem(
                Blocks.AMETHYST_BLOCK, // TODO: Implement and use Raspberry Bush
                FoodValues.RASPBERRY.intoProperties()
            )
        );

        public static final RegistryObject<Item> GLOWBERRY_TART = REGISTER.register(
            "glowberry_tart", () -> new ItemNameTooltipBlockItem(Blocks.AMETHYST_BLOCK
                // TODO: Implement and use Glowberry Tart
            )
        );

        public static final RegistryObject<Item> RASPBERRY_PIE = REGISTER.register(
            "raspberry_pie", () -> new ItemNameTooltipBlockItem(Blocks.AMETHYST_BLOCK
                // TODO: Implement and use Raspberry Pie
            )
        );

        public static final RegistryObject<Item> CINNAMON_PIE = REGISTER.register(
            "cinnamon_pie", () -> new ItemNameTooltipBlockItem(Blocks.AMETHYST_BLOCK
                // TODO: Implement and use Cinnamon Pie
            )
        );
    }

    public static final RegistryObject<Item> WAND_OF_HUNGER = REGISTER.register(
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
    );

    public static final RegistryObject<Item> RASPBERRY_RHODOLITE = REGISTER.register(
        "raspberry_rhodolite",
        () -> new TooltipItem(new Item.Properties().fireResistant())
    );

    public static final RegistryObject<Item> BENITOITE = REGISTER.register(
        "benitoite",
        () -> new TooltipItem(new Item.Properties().fireResistant())
    );

    public static final RegistryObject<Item> CINNAMON_STICK = REGISTER.register("cinnamon_stick",
        TooltipItem::new
    );

    public static final RegistryObject<Item> GILDED_CINNAMON_STICK = REGISTER.register(
        "gilded_cinnamon_stick",
        () -> new TooltipItem(new Item.Properties().rarity(Rarity.RARE))
    );

    public static final RegistryObject<Item> CHARCOAL_INK = REGISTER.register("charcoal_ink",
        CharcoalInkItem::new
    );

    public static final RegistryObject<Item> BUCKRAM = REGISTER.register(
        "buckram",
        TooltipItem::new
    );

    public static final RegistryObject<Item> COTTON_BOLL = REGISTER.register(
        "cotton_boll", () -> new ItemNameTooltipBlockItem(Blocks.AMETHYST_BLOCK
            // TODO: Implement and use Cotton
        )
    );

    public static final RegistryObject<Item> COTTON_SHRUB = REGISTER.register(
        "cotton_shrub", () -> new TooltipBlockItem(Blocks.AMETHYST_BLOCK
            // TODO: Implement and use Cotton Shrub
        )
    );

    public static final RegistryObject<Item> COTTON_BALE = REGISTER.register(
        "cotton_bale", () -> new TooltipBlockItem(Blocks.AMETHYST_BLOCK
            // TODO: Implement and use Cotton Bale
        )
    );

    public static final RegistryObject<Item> COCONUT_CRATE = REGISTER.register(
        "coconut_crate", () -> new TooltipBlockItem(Blocks.AMETHYST_BLOCK
            // TODO: Implement and use Coconut Crate
        )
    );

    public static final RegistryObject<Item> RASPBERRY_CRATE = REGISTER.register(
        "raspberry_crate", () -> new TooltipBlockItem(Blocks.AMETHYST_BLOCK
            // TODO: Implement and use Raspberry Crate
        )
    );

    public static final RegistryObject<Item> COCONUT_SIGN = REGISTER.register(
        "coconut_sign", () -> new SignItem(
            new Item.Properties().stacksTo(16),
            ModBlocks.COCONUT_SIGN.get(),
            ModBlocks.COCONUT_WALL_SIGN.get()
        )
    );

    public static final RegistryObject<Item> COCONUT_HANGING_SIGN = REGISTER.register(
        "coconut_hanging_sign", () -> new HangingSignItem(
            ModBlocks.COCONUT_HANGING_SIGN.get(),
            ModBlocks.COCONUT_WALL_HANGING_SIGN.get(),
            new Item.Properties().stacksTo(16)
        )
    );

    public static final RegistryObject<Item> COCONUT_BOAT = REGISTER.register(
        "coconut_boat",
        () -> new BoatItem(false, CustomBoat.Type.COCONUT, new Item.Properties())
    );

    public static final RegistryObject<Item> COCONUT_CHEST_BOAT = REGISTER.register(
        "coconut_chest_boat",
        () -> new BoatItem(true, CustomBoat.Type.COCONUT, new Item.Properties())
    );

    public static final RegistryObject<Item> FLOWER_CROWN = REGISTER.register(
        "flower_crown",
        () -> new TooltipArmourItem(
            ArmourMaterials.FLOWER_CROWN,
            ArmorItem.Type.HELMET,
            new Item.Properties()
        )
    );
}

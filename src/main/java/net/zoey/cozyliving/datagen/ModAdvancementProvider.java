package net.zoey.cozyliving.datagen;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.network.chat.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.data.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class ModAdvancementProvider extends AdvancementProvider {
    public ModAdvancementProvider(
        PackOutput output,
        CompletableFuture<HolderLookup.Provider> lookupProvider,
        ExistingFileHelper existingFileHelper
    ) {
        super(output, lookupProvider, existingFileHelper, List.of(new ModAdvancementGenerator()));
    }

    private static final class ModAdvancementGenerator implements AdvancementProvider.AdvancementGenerator {
        private Consumer<AdvancementHolder> saver;
        private ExistingFileHelper existingFileHelper;

        private AdvancementHolder make(
            String name,
            ItemStack icon,
            @Nullable ResourceLocation background,
            AdvancementType type,
            boolean showToast,
            boolean announceChat,
            boolean hidden,
            Consumer<Advancement.Builder> build
        ) {
            var builder = Advancement.Builder.advancement().display(
                icon,
                Component.translatable("advancements.cozyliving." + name.replace("/", ".") + ".title"),
                Component.translatable("advancements.cozyliving." + name.replace("/", ".") + ".description"),
                background,
                type,
                showToast,
                announceChat,
                hidden
            );
            build.accept(builder);
            return builder.save(saver, CozyLiving.loc(name), existingFileHelper);
        }

        private AdvancementHolder make(
            String name,
            ItemStack icon,
            AdvancementType type,
            boolean showToast,
            boolean announceChat,
            boolean hidden,
            Consumer<Advancement.Builder> build
        ) {
            return make(name, icon, null, type, showToast, announceChat, hidden, build);
        }

        private AdvancementHolder make(
            String name,
            ItemStack icon,
            AdvancementType type,
            Consumer<Advancement.Builder> build
        ) {
            return make(name, icon, null, type, true, true, false, build);
        }

        private AdvancementHolder make(
            String name,
            ItemStack icon,
            Consumer<Advancement.Builder> build
        ) {
            return make(name, icon, AdvancementType.TASK, build);
        }

        private static Criterion<InventoryChangeTrigger.TriggerInstance> hasItem(Item item) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(item);
        }

        private static Criterion<InventoryChangeTrigger.TriggerInstance> hasItem(ModItems item) {
            return hasItem(item.item());
        }

        private static Criterion<InventoryChangeTrigger.TriggerInstance> hasItem(ModBlocks item) {
            return hasItem(item.item());
        }

        private static Criterion<InventoryChangeTrigger.TriggerInstance> hasItem(ModItems.Food item) {
            return hasItem(item.item());
        }

        private static Criterion<InventoryChangeTrigger.TriggerInstance> hasItem(TagKey<Item> item) {
            return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item()
                .of(item)
                .build());
        }

        private static Criterion<InventoryChangeTrigger.TriggerInstance> hasItem(ModTags.Items item) {
            return hasItem(item.get());
        }
        private static Criterion<ConsumeItemTrigger.TriggerInstance> ate(Item item) {
            return ConsumeItemTrigger.TriggerInstance.usedItem(item);
        }

        private static Criterion<ConsumeItemTrigger.TriggerInstance> ate(ModItems item) {
            return ate(item.item());
        }

        private static Criterion<ConsumeItemTrigger.TriggerInstance> ate(ModBlocks item) {
            return ate(item.item());
        }

        private static Criterion<ConsumeItemTrigger.TriggerInstance> ate(ModItems.Food item) {
            return ate(item.item());
        }

        private static Criterion<ConsumeItemTrigger.TriggerInstance> ate(TagKey<Item> item) {
            return ConsumeItemTrigger.TriggerInstance.usedItem(ItemPredicate.Builder.item()
                .of(item));
        }

        private static Criterion<ConsumeItemTrigger.TriggerInstance> ate(ModTags.Items item) {
            return ate(item.get());
        }

        @Override
        public void generate(
            @NotNull HolderLookup.Provider registries,
            @NotNull Consumer<AdvancementHolder> saver,
            @NotNull ExistingFileHelper existingFileHelper
        ) {
            this.saver = saver;
            this.existingFileHelper = existingFileHelper;
            var root = make(
                "root",
                new ItemStack(ModItems.Food.RASPBERRY_JAM.item()),
                CozyLiving.loc("textures/block/coconut_log_top.png"),
                AdvancementType.TASK,
                false,
                false,
                false,
                builder -> {
                    builder.addCriterion(
                        "got_any_trigger",
                        hasItem(ModTags.Items.TRIGGER_ROOT_ADVANCEMENT)
                    );
                    builder.requirements(AdvancementRequirements.allOf(List.of("got_any_trigger")));
                }
            );
            var getCotton = make(
                "get_cotton",
                new ItemStack(ModBlocks.COTTON_CROP.asItem()),
                builder -> {
                    builder.parent(root);
                    builder.addCriterion("got_cotton", hasItem(ModTags.Items.COTTON_ITEMS));
                    builder.requirements(AdvancementRequirements.allOf(List.of("got_cotton")));
                }
            );
            var negateFallDamageFromCottonBale = make(
                "negate_fall_damage_from_cotton_bale",
                new ItemStack(ModBlocks.COTTON_BALE.asItem()),
                builder -> {
                    builder.parent(getCotton);
                    builder.addCriterion("negated_fall_damage", ModAdvancementTriggers.NEGATE_FALL_DAMAGE_FROM_COTTON_BALE.getCriterion());
                    builder.requirements(AdvancementRequirements.allOf(List.of("negated_fall_damage")));
                }
            );
            var getRaspberry = make(
                "get_raspberry",
                new ItemStack(ModBlocks.RASPBERRY_BUSH.asItem()),
                builder -> {
                    builder.parent(root);
                    builder.addCriterion(
                        "got_raspberry",
                        hasItem(ModBlocks.RASPBERRY_BUSH)
                    );
                    builder.requirements(AdvancementRequirements.allOf(List.of("got_raspberry")));
                }
            );
            var inAJam = make(
                "in_a_jam",
                new ItemStack(ModItems.Food.GLOWBERRY_JAM.item()),
                AdvancementType.GOAL,
                builder -> {
                    builder.parent(getRaspberry);
                    builder.addCriterion(
                        "got_apple_jam",
                        hasItem(ModItems.Food.APPLE_JAM)
                    );
                    builder.addCriterion(
                        "got_glowberry_jam",
                        hasItem(ModItems.Food.GLOWBERRY_JAM)
                    );
                    builder.addCriterion(
                        "got_raspberry_jam",
                        hasItem(ModItems.Food.RASPBERRY_JAM)
                    );
                    builder.addCriterion(
                        "got_sweetberry_jam",
                        hasItem(ModItems.Food.SWEETBERRY_JAM)
                    );
                    builder.requirements(AdvancementRequirements.allOf(List.of(
                        "got_apple_jam",
                        "got_glowberry_jam",
                        "got_raspberry_jam",
                        "got_sweetberry_jam"
                    )));
                }
            );
            var doughnutter = make(
                "doughnutter",
                new ItemStack(ModItems.Food.GLOWBERRY_JAM.item()),
                AdvancementType.CHALLENGE,
                builder -> {
                    builder.parent(inAJam);
                    builder.addCriterion(
                        "got_apple_jam_doughnut",
                        hasItem(ModItems.Food.APPLE_JAM_DOUGHNUT)
                    );
                    builder.addCriterion(
                        "got_glowberry_jam_doughnut",
                        hasItem(ModItems.Food.GLOWBERRY_JAM_DOUGHNUT)
                    );
                    builder.addCriterion(
                        "got_raspberry_jam_doughnut",
                        hasItem(ModItems.Food.RASPBERRY_JAM_DOUGHNUT)
                    );
                    builder.addCriterion(
                        "got_sweetberry_jam_doughnut",
                        hasItem(ModItems.Food.SWEETBERRY_JAM_DOUGHNUT)
                    );
                    builder.addCriterion(
                        "got_cream_doughnut",
                        hasItem(ModItems.Food.CREAM_DOUGHNUT)
                    );
                    builder.requirements(AdvancementRequirements.allOf(List.of(
                        "got_apple_jam_doughnut",
                        "got_glowberry_jam_doughnut",
                        "got_raspberry_jam_doughnut",
                        "got_sweetberry_jam_doughnut",
                        "got_cream_doughnut"
                    )));
                    builder.rewards(AdvancementRewards.Builder.experience(750));
                }
            );
            var getCoconut = make(
                "get_coconut",
                new ItemStack(ModBlocks.COCONUT.asItem()),
                builder -> {
                    builder.parent(root);
                    builder.addCriterion("got_coconut", hasItem(ModBlocks.COCONUT));
                    builder.requirements(AdvancementRequirements.allOf(List.of("got_coconut")));
                }
            );
            var tripleBaka = make(
                "triple_baka",
                new ItemStack(ModItems.Food.TRIPLE_ICE_CREAM.item()),
                AdvancementType.CHALLENGE,
                builder -> {
                    builder.parent(getCoconut);
                    builder.addCriterion("ate_triple_ice_cream", ate(ModItems.Food.TRIPLE_ICE_CREAM));
                    builder.requirements(AdvancementRequirements.allOf(List.of("ate_triple_ice_cream")));
                    builder.rewards(AdvancementRewards.Builder.experience(500));
                }
            );
            var getCinnamon = make(
                "get_cinnamon",
                new ItemStack(ModItems.CINNAMON_STICK.item()),
                builder -> {
                    builder.parent(root);
                    builder.addCriterion("got_cinnamon", hasItem(ModItems.CINNAMON_STICK));
                    builder.requirements(AdvancementRequirements.allOf(List.of("got_cinnamon")));
                }
            );
            var drinkHotterChocolate = make(
                "drink_hotter_chocolate",
                new ItemStack(ModItems.Food.HOTTER_CHOCOLATE.item()),
                AdvancementType.CHALLENGE,
                builder -> {
                    builder.parent(root);
                    builder.addCriterion("drank_hotter_chocolate", ate(ModItems.Food.HOTTER_CHOCOLATE));
                    builder.requirements(AdvancementRequirements.allOf(List.of("drank_hotter_chocolate")));
                }
            );
            var eatGoopyChorus = make(
                "eat_goopy_chorus",
                new ItemStack(ModItems.Food.GOOPY_CHORUS.item()),
                AdvancementType.CHALLENGE,
                builder -> {
                    builder.parent(root);
                    builder.addCriterion("ate_goopy_chorus", ate(ModItems.Food.GOOPY_CHORUS));
                    builder.requirements(AdvancementRequirements.allOf(List.of("ate_goopy_chorus")));
                    builder.rewards(AdvancementRewards.Builder.experience(500));
                }
            );
            var eatCandyApple = make(
                "eat_candy_apple",
                new ItemStack(ModItems.Food.CANDY_APPLE.item()),
                builder -> {
                    builder.parent(root);
                    builder.addCriterion("ate_candy_apple", ate(ModItems.Food.CANDY_APPLE));
                    builder.requirements(AdvancementRequirements.allOf(List.of("ate_candy_apple")));
                }
            );
            var eatGoldenCandyApple = make(
                "eat_golden_candy_apple",
                new ItemStack(ModItems.Food.GOLDEN_CANDY_APPLE.item()),
                AdvancementType.GOAL,
                builder -> {
                    builder.parent(eatCandyApple);
                    builder.addCriterion("ate_candy_apple", ate(ModItems.Food.GOLDEN_CANDY_APPLE));
                    builder.requirements(AdvancementRequirements.allOf(List.of("ate_candy_apple")));
                    builder.rewards(AdvancementRewards.Builder.experience(500));
                }
            );
            var eatEnchantedGoldenCandyApple = make(
                "eat_enchanted_golden_candy_apple",
                new ItemStack(ModItems.Food.ENCHANTED_GOLDEN_CANDY_APPLE.item()),
                AdvancementType.CHALLENGE,
                // TODO: make this hidden? And XP reward?
                builder -> {
                    builder.parent(eatGoldenCandyApple);
                    builder.addCriterion("ate_candy_apple", ate(ModItems.Food.ENCHANTED_GOLDEN_CANDY_APPLE));
                    builder.requirements(AdvancementRequirements.allOf(List.of("ate_candy_apple")));
//                    builder.rewards(AdvancementRewards.Builder.experience(500));
                }
            );
        }
    }
}

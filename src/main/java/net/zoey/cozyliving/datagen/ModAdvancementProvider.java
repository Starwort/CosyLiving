package net.zoey.cozyliving.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.item.ModItems;
import net.zoey.cozyliving.util.ModTags;

import java.util.function.Consumer;

import static net.minecraft.data.server.recipe.RecipeProvider.conditionsFromTag;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {

        Advancement root = Advancement.Builder.create()
                .display(
                        ModItems.RASPBERRY_JAM, // The display icon
                        Text.translatable("advancements.cozyliving.root.title"), // The title
                        Text.translatable("advancements.cozyliving.root.description"), // The description
                        Identifier.of(CozyLiving.MOD_ID, "textures/block/coconut_wood/coconut_log_top.png"), // Background image for the tab in the advancements page, if this is a root advancement (has no parent)
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        false, // Show the toast when completing it
                        false, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                // "root" is the name referenced by other advancements when they want to have "requirements."
                .criterion("got_starter_item", conditionsFromTag(ModTags.Items.COZYLIVING_STARTER_ITEMS))
                //.criterion("got_coconut", InventoryChangedCriterion.Conditions.items(ModBlocks.COCONUT))
                // Give the advancement an id
                .build(consumer, CozyLiving.MOD_ID + ":root");

        Advancement get_raspberry = Advancement.Builder.create()
                .display(
                        ModItems.RASPBERRY, // The display icon
                        Text.translatable("advancements.cozyliving.get_raspberry.title"), // The title
                        Text.translatable("advancements.cozyliving.get_raspberry.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .parent(root)
                .criterion("got_raspberry", InventoryChangedCriterion.Conditions.items(ModItems.RASPBERRY))
                .build(consumer, CozyLiving.MOD_ID + ":get_raspberry");

        Advancement get_coconut = Advancement.Builder.create()
                .display(
                        ModBlocks.COCONUT, // The display icon
                        Text.translatable("advancements.cozyliving.get_coconut.title"), // The title
                        Text.translatable("advancements.cozyliving.get_coconut.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .parent(root)
                .criterion("got_coconut", InventoryChangedCriterion.Conditions.items(ModBlocks.COCONUT))
                .build(consumer, CozyLiving.MOD_ID + ":get_coconut");

        Advancement get_cotton = Advancement.Builder.create()
                .display(
                        ModItems.COTTON_BOLL, // The display icon
                        Text.translatable("advancements.cozyliving.get_cotton.title"), // The title
                        Text.translatable("advancements.cozyliving.get_cotton.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .parent(root)
                .criterion("got_cotton", conditionsFromTag(ModTags.Items.COTTON_ITEMS))
                .build(consumer, CozyLiving.MOD_ID + ":get_cotton");

        Advancement get_cinnamon = Advancement.Builder.create()
                .display(
                        ModItems.CINNAMON_STICK, // The display icon
                        Text.translatable("advancements.cozyliving.get_cinnamon.title"), // The title
                        Text.translatable("advancements.cozyliving.get_cinnamon.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .parent(root)
                .criterion("got_cinnamon_stick", InventoryChangedCriterion.Conditions.items(ModItems.CINNAMON_STICK))
                .build(consumer, CozyLiving.MOD_ID + ":get_cinnamon");

        Advancement triple_baka = Advancement.Builder.create()
                .display(
                        ModItems.TRIPLE_ICE_CREAM, // The display icon
                        Text.translatable("advancements.cozyliving.triple_baka.title"), // The title
                        Text.translatable("advancements.cozyliving.triple_baka.description"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .parent(get_coconut)
                .criterion("eaten_triple_ice_cream", ConsumeItemCriterion.Conditions.item(ModItems.TRIPLE_ICE_CREAM))
                .rewards(AdvancementRewards.Builder.experience(500))
                .build(consumer, CozyLiving.MOD_ID + ":triple_baka");

        //TODO: FIGURE OUT HOW TO MAKE COTTON BALE ADVANCEMENT WORK
        Advancement negate_fall_damage_from_cotton_bale = Advancement.Builder.create()
                .display(
                        ModItems.COTTON_BALE, // The display icon
                        Text.translatable("advancements.cozyliving.negate_fall_damage_from_cotton_bale.title"), // The title
                        Text.translatable("advancements.cozyliving.negate_fall_damage_from_cotton_bale.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                //.criterion("land_on_cotton_bale", AdvancementCriterion.fromJson())
                //.criterion("land_on_cotton_bale", conditionsFromStatistic)
                .parent(get_cotton)
                .build(consumer, CozyLiving.MOD_ID + ":negate_fall_damage_from_cotton_bale");

        Advancement drink_hotter_chocolate = Advancement.Builder.create()
                .display(
                        ModItems.HOTTER_CHOCOLATE, // The display icon
                        Text.translatable("advancements.cozyliving.drink_hotter_chocolate.title"), // The title
                        Text.translatable("advancements.cozyliving.drink_hotter_chocolate.description"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("drank_hotter_chocolate", ConsumeItemCriterion.Conditions.item(ModItems.HOTTER_CHOCOLATE))
                .parent(get_cinnamon)
                .build(consumer, CozyLiving.MOD_ID + ":drink_hotter_chocolate");

        Advancement eat_goopy_chorus = Advancement.Builder.create()
                .display(
                        ModItems.GOOPY_CHORUS, // The display icon
                        Text.translatable("advancements.cozyliving.eat_goopy_chorus.title"), // The title
                        Text.translatable("advancements.cozyliving.eat_goopy_chorus.description"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        true // Hide it in the advancement tab until it's achieved
                )
                .criterion("ate_goopy_chorus", ConsumeItemCriterion.Conditions.item(ModItems.GOOPY_CHORUS))
                .parent(root)
                .rewards(AdvancementRewards.Builder.experience(500))
                .build(consumer, CozyLiving.MOD_ID + ":eat_goopy_chorus");

        Advancement eat_candy_apple = Advancement.Builder.create()
                .display(
                        ModItems.CANDY_APPLE, // The display icon
                        Text.translatable("advancements.cozyliving.eat_candy_apple.title"), // The title
                        Text.translatable("advancements.cozyliving.eat_candy_apple.description"), // The description
                        null,
                        AdvancementFrame.TASK, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("ate_candy_apple", ConsumeItemCriterion.Conditions.item(ModItems.CANDY_APPLE))
                .parent(root)
                .build(consumer, CozyLiving.MOD_ID + ":eat_candy_apple");

        Advancement eat_golden_candy_apple = Advancement.Builder.create()
                .display(
                        ModItems.GOLDEN_CANDY_APPLE, // The display icon
                        Text.translatable("advancements.cozyliving.eat_golden_candy_apple.title"), // The title
                        Text.translatable("advancements.cozyliving.eat_golden_candy_apple.description"), // The description
                        null,
                        AdvancementFrame.GOAL, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .rewards(AdvancementRewards.Builder.experience(500))
                .criterion("ate_golden_candy_apple", ConsumeItemCriterion.Conditions.item(ModItems.GOLDEN_CANDY_APPLE))
                .parent(eat_candy_apple)
                .build(consumer, CozyLiving.MOD_ID + ":eat_golden_candy_apple");

        Advancement eat_enchanted_golden_candy_apple = Advancement.Builder.create()
                .display(
                        ModItems.ENCHANTED_GOLDEN_CANDY_APPLE, // The display icon
                        Text.translatable("advancements.cozyliving.eat_enchanted_golden_candy_apple.title"), // The title
                        Text.translatable("advancements.cozyliving.eat_enchanted_golden_candy_apple.description"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("ate_enchanted_golden_candy_apple", ConsumeItemCriterion.Conditions.item(ModItems.ENCHANTED_GOLDEN_CANDY_APPLE))
                .parent(eat_golden_candy_apple)
                .build(consumer, CozyLiving.MOD_ID + ":eat_enchanted_golden_candy_apple");

        Advancement in_a_jam = Advancement.Builder.create()
                .display(
                        ModItems.GLOWBERRY_JAM, // The display icon
                        Text.translatable("advancements.cozyliving.in_a_jam.title"), // The title
                        Text.translatable("advancements.cozyliving.in_a_jam.description"), // The description
                        null,
                        AdvancementFrame.GOAL, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("got_apple_jam", InventoryChangedCriterion.Conditions.items(ModItems.APPLE_JAM))
                .criterion("got_glowberry_jam", InventoryChangedCriterion.Conditions.items(ModItems.GLOWBERRY_JAM))
                .criterion("got_sweetberry_jam", InventoryChangedCriterion.Conditions.items(ModItems.SWEETBERRY_JAM))
                .criterion("got_raspberry_jam", InventoryChangedCriterion.Conditions.items(ModItems.RASPBERRY_JAM))
                .parent(get_raspberry)
                .build(consumer, CozyLiving.MOD_ID + ":in_a_jam");

        Advancement donutter = Advancement.Builder.create()
                .display(
                        ModItems.RASPBERRY_JAM_DONUT, // The display icon
                        Text.translatable("advancements.cozyliving.donutter.title"), // The title
                        Text.translatable("advancements.cozyliving.donutter.description"), // The description
                        null,
                        AdvancementFrame.CHALLENGE, // TASK, CHALLENGE, or GOAL
                        true, // Show the toast when completing it
                        true, // Announce it to chat
                        false // Hide it in the advancement tab until it's achieved
                )
                .criterion("got_apple_jam_donut", InventoryChangedCriterion.Conditions.items(ModItems.APPLE_JAM_DONUT))
                .criterion("got_glowberry_jam_donut", InventoryChangedCriterion.Conditions.items(ModItems.GLOWBERRY_JAM_DONUT))
                .criterion("got_sweetberry_jam_donut", InventoryChangedCriterion.Conditions.items(ModItems.SWEETBERRY_JAM_DONUT))
                .criterion("got_raspberry_jam_donut", InventoryChangedCriterion.Conditions.items(ModItems.RASPBERRY_JAM_DONUT))
                .criterion("got_cream_donut", InventoryChangedCriterion.Conditions.items(ModItems.CREAM_DONUT))
                .rewards(AdvancementRewards.Builder.experience(750))
                .parent(in_a_jam)
                .build(consumer, CozyLiving.MOD_ID + ":donutter");
    }


}

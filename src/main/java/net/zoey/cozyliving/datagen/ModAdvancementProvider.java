package net.zoey.cozyliving.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
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
                .parent(get_cotton)
                .build(consumer, CozyLiving.MOD_ID + ":negate_fall_damage_from_cotton_bale");

    }


}

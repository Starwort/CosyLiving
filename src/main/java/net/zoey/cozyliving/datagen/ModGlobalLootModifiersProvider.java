package net.zoey.cozyliving.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.ModBlocks;
import net.zoey.cozyliving.content.ModItems;
import net.zoey.cozyliving.util.AddItemModifier;

import static org.joml.Math.sqrt;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, CozyLiving.MODID);
    }

    @Override
    protected void start() {

        add("cinnamon_from_jungle_leaves", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.JUNGLE_LEAVES).build(),
                LootItemRandomChanceCondition.randomChance(0.186f).build()} //Drops from roughly one in 29 blocks (~twice per tree)
                //Probability gets squared for some reason, so the above float is actually sqrt(1/29).
                , ModItems.CINNAMON_STICK.item()));

        add("raspberry_from_grass", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
                LootItemRandomChanceCondition.randomChance(sqrt(0.008f)).build()} //Should drop one raspberry for roughly every sixteen seeds
                , ModBlocks.RASPBERRY_BUSH.asItem()));

        add("gilded_cinnamon_from_jungle_temple_chests", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build() }, ModItems.GILDED_CINNAMON_STICK.item()));
    }
}

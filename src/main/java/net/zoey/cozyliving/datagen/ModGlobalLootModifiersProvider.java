package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.common.loot.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.util.*;

import java.util.concurrent.*;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, CozyLiving.MODID);
    }

    @Override
    protected void start() {

        add("cinnamon_from_jungle_leaves", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.JUNGLE_LEAVES).build(),
                LootItemRandomChanceCondition.randomChance(0.186f).build()} //Drops from roughly one in 29 blocks (~twice per tree)
                //Probability gets squared for some reason, so the above float is actually sqrt(1/29).
                , ModItems.CINNAMON_STICK.item()));

        add("raspberry_from_grass", new AddItemModifier(new LootItemCondition[] {
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.SHORT_GRASS).build(),
                LootItemRandomChanceCondition.randomChance(Mth.sqrt(0.008f)).build()} //Should drop one raspberry for roughly every sixteen seeds
                , ModBlocks.RASPBERRY_BUSH.asItem()));

        add("gilded_cinnamon_from_jungle_temple_chests", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/jungle_temple")).build() }, ModItems.GILDED_CINNAMON_STICK.item()));
    }
}

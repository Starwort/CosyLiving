package net.zoey.cozyliving.util;

//import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.item.ModItems;

public class ModLootTableModifiers {

    private static final Identifier JUNGLE_TEMPLE_ID =
            new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier JUNGLE_LEAVES_ID =
            new Identifier("minecraft", "chests/jungle_temple");



    public static void modifyLootTables() {


        //MAKE JUNGLE LEAVES RARELY DROP CINNAMON
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(Blocks.JUNGLE_LEAVES.getLootTableId().equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.035f)) //Drops from roughly one in 29 blocks (~twice per tree)
                        .with(ItemEntry.builder(ModItems.CINNAMON_STICK))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());

                tableBuilder.pool(poolBuilder.build());
            }
        });

        //MAKE GRASS OCCASIONALLY DROP RASPBERRIES
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if((Blocks.GRASS.getLootTableId()).equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.008f)) //Should drop one raspberry for roughly every sixteen seeds
                        .with(ItemEntry.builder(ModItems.RASPBERRY))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        //MAKE GILDED CINNAMON DROP FROM JUNGLE TEMPLE CHESTS
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if((JUNGLE_TEMPLE_ID.equals(id))) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.8f)) //Guaranteed Drop
                        .with(ItemEntry.builder(ModItems.GILDED_CINNAMON_STICK))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,3.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        //MAKE REDSTONE ORE OCCASIONALLY DROP RED SUGAR
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(Blocks.REDSTONE_ORE.getLootTableId().equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.035f)) //Drops from roughly one in 30 blocks
                        .with(ItemEntry.builder(ModItems.RED_SUGAR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        //MAKE DEEPSLATE REDSTONE ORE OCCASIONALLY DROP RED SUGAR
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(Blocks.DEEPSLATE_REDSTONE_ORE.getLootTableId().equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.035f)) //Drops from roughly one in 30 blocks
                        .with(ItemEntry.builder(ModItems.RED_SUGAR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });
    }}
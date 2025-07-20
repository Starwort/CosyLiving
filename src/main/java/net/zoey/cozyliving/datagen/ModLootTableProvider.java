package net.zoey.cozyliving.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.CaveVines;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.block.custom.CoconutSaplingBlock;
import net.zoey.cozyliving.block.custom.CottonCropBlock;
import net.zoey.cozyliving.block.custom.RaspberryBushBlock;
import net.zoey.cozyliving.item.ModItems;

import static net.minecraft.state.property.Properties.AGE_4;



public class ModLootTableProvider extends FabricBlockLootTableProvider {

    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        //JUST DROP THEMSELVES

        addDrop(ModBlocks.BENITOITE_BLOCK);
        addDrop(ModBlocks.COCONUT);
        addDrop(ModBlocks.COCONUT_BUTTON);
    addDrop(ModBlocks.COCONUT_DOOR, doorDrops(ModBlocks.COCONUT_DOOR));
        addDrop(ModBlocks.COCONUT_FENCE);
        addDrop(ModBlocks.COCONUT_FENCE_GATE);
        addDrop(ModBlocks.COCONUT_LOG);
        addDrop(ModBlocks.COCONUT_LOG);
        addDrop(ModBlocks.COCONUT_PLANKS);
        addDrop(ModBlocks.COCONUT_PRESSURE_PLATE);
        addDrop(ModBlocks.COCONUT_SAPLING);
        addDrop(ModBlocks.COCONUT_SLAB);
        addDrop(ModBlocks.COCONUT_STAIRS);
        addDrop(ModBlocks.COCONUT_TRAPDOOR);
        addDrop(ModBlocks.COCONUT_WOOD);
        addDrop(ModBlocks.STRIPPED_COCONUT_LOG);
        addDrop(ModBlocks.STRIPPED_COCONUT_WOOD);
        addDrop(ModBlocks.RASPBERRY_RHODOLITE_BLOCK);
        addDrop(ModBlocks.TEST_BLOCK);
        addDrop(ModBlocks.COTTON_BALE, ModItems.COTTON_BALE);
        addDrop(ModBlocks.RASPBERRY_CRATE, ModItems.RASPBERRY_CRATE);
        addDrop(ModBlocks.COCONUT_CRATE, ModItems.COCONUT_CRATE);

        //PIES
        //addDrop(ModBlocks.CINNAMON_PIE); //
        //addDrop(ModBlocks.RASPBERRY_PIE); //
        //addDrop(ModBlocks.GLOWBERRY_TART); //

        //ORES
        addDrop(ModBlocks.RASPBERRY_RHODOLITE_ORE, oreDrops(ModBlocks.RASPBERRY_RHODOLITE_ORE, ModItems.RASPBERRY_RHODOLITE)); //
        addDrop(ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE, oreDrops(ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE, ModItems.RASPBERRY_RHODOLITE)); //
        addDrop(ModBlocks.DEEPSLATE_BENITOITE_ORE, oreDrops(ModBlocks.DEEPSLATE_BENITOITE_ORE, ModItems.BENITOITE)); //
        addDrop(ModBlocks.BENITOITE_ORE, oreDrops(ModBlocks.BENITOITE_ORE, ModItems.BENITOITE));

        //POTS
        addDrop(ModBlocks.POTTED_COCONUT_SAPLING, pottedPlantDrops(ModBlocks.COCONUT_SAPLING.asItem())); //

        //PLANTS
        addDrop(ModBlocks.RASPBERRY_BUSH, raspberryBushDrops());
        addDrop(ModBlocks.COTTON_SHRUB, cottonShrubDrops());
        addDrop(ModBlocks.COTTON_CROP, cottonCropDrops());
        //LEAVES
        addDrop(ModBlocks.COCONUT_LEAVES, leavesDrops(ModBlocks.COCONUT_LEAVES, ModBlocks.COCONUT_SAPLING, 0.15f)); //
        addDrop(ModBlocks.COCONUT_LEAVES_CORNER, leavesDrops(ModBlocks.COCONUT_LEAVES, ModBlocks.COCONUT_SAPLING, 0.15f)); //

        //RASPBERRY BUSH

    }

    public static LootTable.Builder raspberryBushDrops() {
        return LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ModItems.RASPBERRY)).conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.RASPBERRY_BUSH).properties(StatePredicate.Builder.create().exactMatch(RaspberryBushBlock.AGE, 4)))
                .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))
        );
    }


    public static final LootCondition.Builder FULLY_GROWN_COTTON = BlockStatePropertyLootCondition.builder(ModBlocks.COTTON_CROP).properties(StatePredicate.Builder.create().exactMatch(CottonCropBlock.AGE, 7));


    public static LootTable.Builder cottonCropDrops() {
        return LootTable.builder()

                //Pool for dropping one cotton shrub with silk or shears when plant is grown
                .pool(LootPool.builder().with(ItemEntry.builder(ModItems.COTTON_SHRUB).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .conditionally(WITH_SILK_TOUCH_OR_SHEARS.and(FULLY_GROWN_COTTON)))

                //Pool for dropping cotton bolls when plant is grown and not using shears
                .pool(LootPool.builder().with(ItemEntry.builder(ModItems.COTTON_BOLL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))).apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE)))
                        .conditionally(WITH_SILK_TOUCH_OR_SHEARS.invert().and(FULLY_GROWN_COTTON)))

                //Pool for always dropping one cotton boll if not fully grown
                .pool(LootPool.builder()
                        .with(ItemEntry.builder(ModItems.COTTON_BOLL).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .conditionally((FULLY_GROWN_COTTON.invert())));

    }

    public static LootTable.Builder cottonShrubDrops() {
        return LootTable.builder()
                //Pool for dropping one cotton shrub with silk or shears
                .pool(LootPool.builder().with(ItemEntry.builder(ModItems.COTTON_SHRUB).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1))))
                        .conditionally(WITH_SILK_TOUCH_OR_SHEARS))

                //Pool for dropping cotton bolls when plant is grown and not using shears
                .pool(LootPool.builder().with(ItemEntry.builder(ModItems.COTTON_BOLL).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 3.0F))).apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE)))
                        .conditionally(WITH_SILK_TOUCH_OR_SHEARS.invert()));

    }
                /*
                .pool(LootPool.builder()

                        //IF SILK/SHEARS AND AGE = 7, DROP SHRUB
                        .with(ItemEntry.builder(ModItems.COTTON_SHRUB)).conditionally(WITH_SILK_TOUCH_OR_SHEARS
                                .and(BlockStatePropertyLootCondition.builder(ModBlocks.COTTON_CROP).properties(StatePredicate.Builder.create().exactMatch(CottonCropBlock.AGE, 7))))

                        //IF AGE = 7, DROP BOLLS
                        .with(ItemEntry.builder(ModItems.COTTON_BOLL))
                        .conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.COTTON_CROP).properties(StatePredicate.Builder.create().exactMatch(CottonCropBlock.AGE, 7)))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F))).apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE))

                        //ELSE DROP ONE BOLL
                        .with(ItemEntry.builder(ModItems.COTTON_BOLL))

                );
                        //this is just making each .with drop randomly meshed together grrr

                 */

}

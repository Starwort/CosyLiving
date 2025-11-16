package net.zoey.cozyliving.datagen.loot;

import net.minecraft.advancements.critereon.*;
import net.minecraft.data.loot.*;
import net.minecraft.world.flag.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.block.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropOther(ModBlocks.COCONUT_SIGN.block(), ModItems.COCONUT_SIGN.item());
        dropOther(ModBlocks.COCONUT_WALL_SIGN.block(), ModItems.COCONUT_SIGN.item());
        dropOther(
            ModBlocks.COCONUT_HANGING_SIGN.block(),
            ModItems.COCONUT_HANGING_SIGN.item()
        );
        dropOther(
            ModBlocks.COCONUT_WALL_HANGING_SIGN.block(),
            ModItems.COCONUT_HANGING_SIGN.item()
        );

        dropSelf(ModBlocks.COCONUT_LOG.block());
        dropSelf(ModBlocks.COCONUT_WOOD.block());
        dropSelf(ModBlocks.STRIPPED_COCONUT_LOG.block());
        dropSelf(ModBlocks.STRIPPED_COCONUT_WOOD.block());
        dropSelf(ModBlocks.COCONUT_PLANKS.block());

        add(
            ModBlocks.COCONUT_LEAVES.block(), createLeavesDrops(
                ModBlocks.COCONUT_LEAVES.block(),
                ModBlocks.COCONUT_SAPLING.block(),
                0.15f
            )
        );

        add(ModBlocks.RASPBERRY_BUSH.block(), raspberryBushDrops());

        add(ModBlocks.COCONUT_PLANT.block(), noDrop());

        dropSelf(ModBlocks.COCONUT.block());

        add(ModBlocks.GLOWBERRY_TART.block(), noDrop());
        add(ModBlocks.RASPBERRY_PIE.block(), noDrop());
        add(ModBlocks.CINNAMON_PIE.block(), noDrop());

        add(ModBlocks.COTTON_SHRUB.block(), cottonShrubDrops());
        add(ModBlocks.COTTON_CROP.block(), cottonCropDrops());

        add(
            ModBlocks.POTTED_COTTON.block(),
            createPotFlowerItemTable(ModBlocks.COTTON_SHRUB.asItem())
        );
        add(
            ModBlocks.POTTED_COCONUT_SAPLING.block(),
            createPotFlowerItemTable(ModBlocks.COCONUT_SAPLING.asItem())
        );
        dropSelf(ModBlocks.COCONUT_SAPLING.block());
        dropSelf(ModBlocks.COTTON_BALE.block());
        dropSelf(ModBlocks.RASPBERRY_CRATE.block());
        dropSelf(ModBlocks.COCONUT_CRATE.block());

        dropSelf(ModBlocks.COCONUT_PRESSURE_PLATE.block());
        dropSelf(ModBlocks.COCONUT_TRAPDOOR.block());
        dropSelf(ModBlocks.COCONUT_STAIRS.block());
        dropSelf(ModBlocks.COCONUT_BUTTON.block());
        dropSelf(ModBlocks.COCONUT_FENCE.block());
        dropSelf(ModBlocks.COCONUT_FENCE_GATE.block());
        add(
            ModBlocks.COCONUT_DOOR.block(),
            createDoorTable(ModBlocks.COCONUT_DOOR.block())
        );
        add(
            ModBlocks.COCONUT_SLAB.block(),
            createSlabItemTable(ModBlocks.COCONUT_SLAB.block())
        );

        dropSelf(ModBlocks.RASPBERRY_RHODOLITE_BLOCK.block());
        simpleOre(ModBlocks.RASPBERRY_RHODOLITE_ORE, ModItems.RASPBERRY_RHODOLITE);
        simpleOre(
            ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE,
            ModItems.RASPBERRY_RHODOLITE
        );

        dropSelf(ModBlocks.BENITOITE_BLOCK.block());
        simpleOre(ModBlocks.BENITOITE_ORE, ModItems.BENITOITE);
        simpleOre(ModBlocks.DEEPSLATE_BENITOITE_ORE, ModItems.BENITOITE);
    }

    private void simpleOre(ModBlocks ore, ModItems drop) {
        add(ore.block(), createOreDrop(ore.block(), drop.item()));
    }

    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(
        HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    private static final LootItemCondition.Builder COTTON_FULLY_GROWN = LootItemBlockStatePropertyCondition
        .hasBlockStateProperties(ModBlocks.COTTON_CROP.block())
        .setProperties(StatePropertiesPredicate.Builder
            .properties()
            .hasProperty(CropBlock.AGE, 7));

    private LootTable.Builder cottonCropDrops() {
        return LootTable
            .lootTable()
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(ModBlocks.COTTON_SHRUB.asItem())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .when(HAS_SHEARS_OR_SILK_TOUCH.and(COTTON_FULLY_GROWN)))
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(ModBlocks.COTTON_CROP.asItem())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                        2,
                        5
                    )))
                    .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))
                .when(HAS_NO_SHEARS_OR_SILK_TOUCH.and(COTTON_FULLY_GROWN)))
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(ModBlocks.COTTON_CROP.asItem())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))))
                .when(COTTON_FULLY_GROWN.invert()));
    }

    private LootTable.Builder cottonShrubDrops() {
        return LootTable
            .lootTable()
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(ModBlocks.COTTON_SHRUB.asItem())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                    .when(HAS_SHEARS_OR_SILK_TOUCH)))
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(ModBlocks.COTTON_CROP.asItem())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(
                        2,
                        3
                    )))
                    .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                    .when(HAS_NO_SHEARS_OR_SILK_TOUCH)));
    }

    public static LootTable.Builder raspberryBushDrops() {
        return LootTable
            .lootTable()
            .withPool(LootPool
                .lootPool()
                .add(LootItem.lootTableItem(ModBlocks.RASPBERRY_BUSH.asItem()))
                .when(LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(ModBlocks.RASPBERRY_BUSH.block())
                    .setProperties(StatePropertiesPredicate.Builder
                        .properties()
                        .hasProperty(RaspberryBushBlock.AGE, 4)))
                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return CozyLiving.BLOCKS
            .getEntries()
            .stream()
            .map(RegistryObject::get)::iterator;
    }
}

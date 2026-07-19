package net.zoey.cozyliving.datagen.loot;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.loot.*;
import net.minecraft.world.flag.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.block.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider lookupProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), lookupProvider);
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

        add(
            ModBlocks.COCONUT_LEAVES_CORNER.block(), createLeavesDrops(
                ModBlocks.COCONUT_LEAVES.block(),
                ModBlocks.COCONUT_SAPLING.block(),
                0.15f
            )
        );

        add(ModBlocks.RASPBERRY_BUSH.block(), raspberryBushDrops());

        add(ModBlocks.COCONUT_PLANT.block(), coconutPlantDrops());

        dropSelf(ModBlocks.COCONUT.block());

        add(ModBlocks.GLOWBERRY_TART.block(), noDrop());
        add(ModBlocks.RASPBERRY_PIE.block(), noDrop());
        add(ModBlocks.CINNAMON_PIE.block(), noDrop());
        add(ModBlocks.GOLDEN_CARROT_CAKE.block(), noDrop());
        add(ModBlocks.RED_VELVET_CAKE.block(), noDrop());

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

        dropSelf(ModBlocks.TEST_BLOCK.block());

        add(
            ModBlocks.PINK_PAMPAS_GRASS.block(),
            createPampasGrassDrops(ModBlocks.PINK_PAMPAS_GRASS.block())
        );
        add(
            ModBlocks.WHITE_PAMPAS_GRASS.block(),
            createPampasGrassDrops(ModBlocks.WHITE_PAMPAS_GRASS.block())
        );

    }

    protected LootTable.Builder createPampasGrassDrops(Block pBlock) { //Mostly stolen from the tall grass dropping function lol
        LootPoolEntryContainer.Builder<?> builder = LootItem
            .lootTableItem(pBlock)
            .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
            .when(HAS_SHEARS)
            .otherwise(this.applyExplosionCondition(
                pBlock,
                LootItem
                    .lootTableItem(Items.FEATHER)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
            ));
        return LootTable
            .lootTable()
            .withPool(LootPool
                .lootPool()
                .add(builder)
                .when(LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(pBlock)
                    .setProperties(StatePropertiesPredicate.Builder
                        .properties()
                        .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)))
                .when(LocationCheck.checkLocation(
                    LocationPredicate.Builder
                        .location()
                        .setBlock(BlockPredicate.Builder
                            .block()
                            .of(pBlock)
                            .setProperties(StatePropertiesPredicate.Builder
                                .properties()
                                .hasProperty(
                                    DoublePlantBlock.HALF,
                                    DoubleBlockHalf.UPPER
                                )
                            )
                        ), new BlockPos(0, 1, 0)
                )))
            .withPool(LootPool
                .lootPool()
                .add(builder)
                .when(LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(pBlock)
                    .setProperties(StatePropertiesPredicate.Builder
                        .properties()
                        .hasProperty(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)))
                .when(LocationCheck.checkLocation(
                    LocationPredicate.Builder
                        .location()
                        .setBlock(BlockPredicate.Builder
                            .block()
                            .of(pBlock)
                            .setProperties(StatePropertiesPredicate.Builder
                                .properties()
                                .hasProperty(
                                    DoublePlantBlock.HALF,
                                    DoubleBlockHalf.LOWER
                                )
                            )
                        ), new BlockPos(0, -1, 0)
                )));
    }

    private void simpleOre(ModBlocks ore, ModItems drop) {
        add(ore.block(), createOreDrop(ore.block(), drop.item()));
    }

    private LootItemCondition.Builder hasShearsOrSilkTouch() {
        return HAS_SHEARS.or(this.hasSilkTouch());
    }

    private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
        return this.hasShearsOrSilkTouch().invert();
    }

    private Holder<Enchantment> fortune() {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return registrylookup.getOrThrow(Enchantments.FORTUNE);
    }

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
                .when(hasShearsOrSilkTouch().and(COTTON_FULLY_GROWN)))
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(ModBlocks.COTTON_CROP.asItem())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5)))
                    .apply(ApplyBonusCount.addUniformBonusCount(fortune())))
                .when(doesNotHaveShearsOrSilkTouch().and(COTTON_FULLY_GROWN)))
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
                    .when(hasShearsOrSilkTouch())))
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(ModBlocks.COTTON_CROP.asItem())
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 3)))
                    .apply(ApplyBonusCount.addUniformBonusCount(fortune()))
                    .when(doesNotHaveShearsOrSilkTouch())));
    }

    private LootTable.Builder pampasGrassDrops(Block block) {
        return LootTable
            .lootTable()
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(block.asItem())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                    .when(hasShearsOrSilkTouch()
                        .and(LootItemBlockStatePropertyCondition
                            .hasBlockStateProperties(block)
                            .setProperties(StatePropertiesPredicate.Builder
                                .properties()
                                .hasProperty(
                                    TallFlowerBlock.HALF,
                                    DoubleBlockHalf.LOWER
                                )))
                        .and(LocationCheck.checkLocation(
                            new LocationPredicate.Builder(),
                            BlockPos.ZERO.above()
                        )))))
            .withPool(LootPool
                .lootPool()
                .add(LootItem
                    .lootTableItem(Items.FEATHER)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2,3)))
                    .apply(ApplyBonusCount.addUniformBonusCount(fortune()))
                    .when(doesNotHaveShearsOrSilkTouch()
                        .and(LootItemBlockStatePropertyCondition
                            .hasBlockStateProperties(block)
                            .setProperties(StatePropertiesPredicate.Builder
                                .properties()
                                .hasProperty(
                                    TallFlowerBlock.HALF,
                                    DoubleBlockHalf.LOWER
                                )))
                        .and(LocationCheck.checkLocation(
                            new LocationPredicate.Builder(),
                            BlockPos.ZERO.above()
                        )))));
    }

    public LootTable.Builder raspberryBushDrops() {
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
                .apply(ApplyBonusCount.addUniformBonusCount(fortune())));
    }

    public static LootTable.Builder coconutPlantDrops() {
        return LootTable
            .lootTable()
            .withPool(LootPool
                .lootPool()
                .add(LootItem.lootTableItem(ModBlocks.COCONUT.asItem()))
                .when(LootItemBlockStatePropertyCondition
                    .hasBlockStateProperties(ModBlocks.COCONUT_PLANT.block())
                    .setProperties(StatePropertiesPredicate.Builder
                        .properties()
                        .hasProperty(CoconutPlantBlock.AGE, 2))
                    .or(LootItemBlockStatePropertyCondition
                        .hasBlockStateProperties(ModBlocks.COCONUT_PLANT.block())
                        .setProperties(StatePropertiesPredicate.Builder
                            .properties()
                            .hasProperty(CoconutPlantBlock.AGE, 3)))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return CozyLiving.BLOCKS
            .getEntries()
            .stream()
            .map(i -> (Block)i.get())::iterator;
    }
}

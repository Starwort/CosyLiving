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
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.block.*;
import org.jetbrains.annotations.*;

import java.util.*;

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
                // TODO: Implement and use Coconut sapling
                Blocks.AMETHYST_BLOCK, 0.15f
            )
        );

        add(ModBlocks.RASPBERRY_BUSH.block(), raspberryBushDrops());
    }

    public static LootTable.Builder raspberryBushDrops() {
        return LootTable
            .lootTable()
            .withPool(LootPool
                .lootPool()
                .add(LootItem.lootTableItem(ModItems.Food.RASPBERRY.item()))
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

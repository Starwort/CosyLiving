package net.zoey.cozyliving.datagen.loot;

import net.minecraft.data.loot.*;
import net.minecraft.world.flag.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
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
    }

    //    public static LootTable.Builder leavesDrops()

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return CozyLiving.BLOCKS
            .getEntries()
            .stream()
            .map(RegistryObject::get)::iterator;
    }
}

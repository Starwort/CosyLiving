package net.zoey.cozyliving.datagen.loot;

import net.minecraft.data.loot.*;
import net.minecraft.world.flag.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.content.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropOther(ModBlocks.COCONUT_SIGN.get(), ModItems.COCONUT_SIGN.get());
        dropOther(ModBlocks.COCONUT_WALL_SIGN.get(), ModItems.COCONUT_SIGN.get());
        dropOther(
            ModBlocks.COCONUT_HANGING_SIGN.get(),
            ModItems.COCONUT_HANGING_SIGN.get()
        );
        dropOther(
            ModBlocks.COCONUT_WALL_HANGING_SIGN.get(),
            ModItems.COCONUT_HANGING_SIGN.get()
        );

        dropSelf(ModBlocks.COCONUT_LOG.get());
        dropSelf(ModBlocks.COCONUT_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_COCONUT_LOG.get());
        dropSelf(ModBlocks.STRIPPED_COCONUT_WOOD.get());
        dropSelf(ModBlocks.COCONUT_PLANKS.get());

        add(
            ModBlocks.COCONUT_LEAVES.get(), createLeavesDrops(
                ModBlocks.COCONUT_LEAVES.get(),
                // TODO: Implement and use Coconut sapling
                Blocks.AMETHYST_BLOCK, 0.15f
            )
        );
    }

    //    public static LootTable.Builder leavesDrops()

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.REGISTER
            .getEntries()
            .stream()
            .map(RegistryObject::get)::iterator;
    }
}

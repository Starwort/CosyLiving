package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.data.*;
import net.minecraft.data.loot.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.zoey.cozyliving.datagen.loot.*;

import java.util.*;
import java.util.concurrent.*;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> holderLookup) {
        super(
            output,
            Set.of(),
            List.of(new LootTableProvider.SubProviderEntry(
                ModBlockLootTables::new,
                LootContextParamSets.BLOCK
            )),
            holderLookup
        );
    }
}

package net.zoey.cozyliving.datagen;

import net.minecraft.data.*;
import net.minecraft.data.loot.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import net.zoey.cozyliving.datagen.loot.*;

import java.util.*;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(
            output,
            Set.of(),
            List.of(new LootTableProvider.SubProviderEntry(
                ModBlockLootTables::new,
                LootContextParamSets.BLOCK
            ))
        );
    }
}

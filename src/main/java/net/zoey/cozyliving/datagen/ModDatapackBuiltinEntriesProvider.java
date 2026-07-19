package net.zoey.cozyliving.datagen;

import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.level.gen.*;

import java.util.*;
import java.util.concurrent.*;

public class ModDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {
    public ModDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(
            output,
            lookupProvider,
            new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
                .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap)
                .add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap),
            Set.of(CozyLiving.MODID)
        );
    }
}

package net.zoey.cozyliving.level.gen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.content.ModTags;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_RASPBERRY_RHODOLITE_ORE = registerKey("add_raspberry_rhodolite_ore");
    public static final ResourceKey<BiomeModifier> ADD_BENITOITE_ORE = registerKey("add_benitoite_ore");
    public static final ResourceKey<BiomeModifier> ADD_PATCH_RASPBERRIES = registerKey("add_patch_raspberries");
    public static final ResourceKey<BiomeModifier> ADD_PATCH_COTTON_SHRUB = registerKey("add_patch_cotton_shrub");
    public static final ResourceKey<BiomeModifier> ADD_PATCH_LUSH_COTTON_SHRUB = registerKey("add_patch_lush_cotton_shrub");


    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_RASPBERRY_RHODOLITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.RASPBERRY_RHODOLITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_BENITOITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BENITOITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_PATCH_RASPBERRIES, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.HAS_RASPBERRY_PATCHES.get()),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_RASPBERRIES_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_PATCH_COTTON_SHRUB, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(ModTags.Biomes.HAS_COTTON_SHRUB_PATCHES.get()),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_COTTON_SHRUB_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_PATCH_LUSH_COTTON_SHRUB, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.LUSH_CAVES)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_COTTON_SHRUB_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, name));
    }
}
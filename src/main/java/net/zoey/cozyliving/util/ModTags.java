package net.zoey.cozyliving.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.zoey.cozyliving.CozyLiving;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> COCONUT_LOGS = createTag("coconut_logs");


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(CozyLiving.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> COCONUT_LOGS = createTag("coconut_logs");
        public static final TagKey<Item> BREWING_STAND_INPUT_USEABLE = createTag("brewing_stand_input_useable");
        public static final TagKey<Item> BREWING_STAND_INGREDIENT_USEABLE = createTag("brewing_stand_ingredient_useable");
        public static final TagKey<Item> JAMS = createTag("jams");
        public static final TagKey<Item> COZYLIVING_STARTER_ITEMS = createTag("cozyliving_starter_items");
        public static final TagKey<Item> COTTON_ITEMS = createTag("cotton_items");
        public static final TagKey<Item> ICE_CREAMS = createTag("ice_creams");



        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(CozyLiving.MOD_ID, name));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> HAS_COTTON_SHRUB_PATCHES = createTag("has_cotton_shrub_patches");
        public static final TagKey<Biome> HAS_RASPBERRY_PATCHES = createTag("has_raspberry_patches");
    }
        //public static final TagKey<Biome> HAS_COTTON_SHRUB_PATCHES = TagKey.of(RegistryKeys.BIOME, new Identifier("has_cotton_shrub_patches"));
        //public static final TagKey<Biome> HAS_RASPBERRY_PATCHES = TagKey.of(RegistryKeys.BIOME, new Identifier("has_cotton_shrub_patches"));


        private static TagKey<Biome> createTag(String name) {
            return TagKey.of(RegistryKeys.BIOME, Identifier.of(CozyLiving.MOD_ID, name));
    }

    public static void registerModTags() {
        CozyLiving.LOGGER.info("Registering Mod Tags for " + CozyLiving.MOD_ID);
    }

}

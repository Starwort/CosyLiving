package net.zoey.cozyliving.content;

import net.minecraft.core.registries.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.*;
import net.zoey.cozyliving.CozyLiving;

public class ModTags {
    public enum Blocks {
        COCONUT_LOGS("coconut_logs"),

        PAMPAS_GRASSES("pampas_grasses"),

        ;

        public final TagKey<Block> myValue;

        Blocks(String id) {
            myValue = BlockTags.create(CozyLiving.loc(id));
        }

        public TagKey<Block> get() {
            return myValue;
        }
    }

    public enum Items {
        COCONUT_LOGS("coconut_logs"),

        BREWING_STAND_INPUT_USEABLE("brewing_stand_input_useable"),

        BREWING_STAND_INGREDIENT_USEABLE("brewing_stand_ingredient_useable"),

        JAMS("jams"),

        TRIGGER_ROOT_ADVANCEMENT("trigger_root_advancement"),

        COTTON_ITEMS("cotton_items"),

        ICE_CREAMS("ice_creams"),

        GLOWING_ITEMS("glowing_items"),

        PAMPAS_GRASSES("pampas_grasses"),
        ;

        private final TagKey<Item> myValue;

        Items(String id) {
            myValue = ItemTags.create(CozyLiving.loc(id));
        }

        public TagKey<Item> get() {
            return myValue;
        }
    }

    public enum Biomes {
        HAS_COTTON_SHRUB_PATCHES("has_cotton_shrub_patches"),

        HAS_RASPBERRY_PATCHES("has_raspberry_patches"),
        ;

        private final TagKey<Biome> myValue;

        Biomes(String id) {
            myValue = TagKey.create(Registries.BIOME, CozyLiving.loc(id));
        }

        public TagKey<Biome> get() {
            return myValue;
        }
    }
}

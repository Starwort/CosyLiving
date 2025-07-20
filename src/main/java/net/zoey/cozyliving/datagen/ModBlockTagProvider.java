package net.zoey.cozyliving.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider{

    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        //Mod tags up top, vanilla tags below

        getOrCreateTagBuilder(ModTags.Blocks.COCONUT_LOGS)
                .add(ModBlocks.COCONUT_LOG)
                .add(ModBlocks.COCONUT_WOOD)
                .add(ModBlocks.STRIPPED_COCONUT_LOG)
                .add(ModBlocks.STRIPPED_COCONUT_WOOD);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .addTag(ModTags.Blocks.COCONUT_LOGS)
                .add(ModBlocks.COCONUT)
                .add(ModBlocks.COCONUT_BUTTON)
                .add(ModBlocks.COCONUT_DOOR)
                .add(ModBlocks.COCONUT_FENCE)
                .add(ModBlocks.COCONUT_FENCE_GATE)
                .add(ModBlocks.COCONUT_PLANKS)
                .add(ModBlocks.COCONUT_PLANT)
                .add(ModBlocks.COCONUT_PRESSURE_PLATE)
                .add(ModBlocks.COCONUT_SLAB)
                .add(ModBlocks.COCONUT_STAIRS)
                .add(ModBlocks.COCONUT_TRAPDOOR)
                .add(ModBlocks.COCONUT_SIGN)
                .add(ModBlocks.COCONUT_HANGING_SIGN)
                .add(ModBlocks.COCONUT_WALL_SIGN)
                .add(ModBlocks.COCONUT_WALL_HANGING_SIGN);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.BENITOITE_BLOCK)
                .add(ModBlocks.BENITOITE_ORE)
                .add(ModBlocks.DEEPSLATE_BENITOITE_ORE)
                .add(ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE)
                .add(ModBlocks.RASPBERRY_RHODOLITE_BLOCK)
                .add(ModBlocks.RASPBERRY_RHODOLITE_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BENITOITE_BLOCK)
                .add(ModBlocks.BENITOITE_ORE)
                .add(ModBlocks.DEEPSLATE_BENITOITE_ORE)
                .add(ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE)
                .add(ModBlocks.RASPBERRY_RHODOLITE_BLOCK)
                .add(ModBlocks.RASPBERRY_RHODOLITE_ORE);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.BENITOITE_BLOCK)
                .add(ModBlocks.RASPBERRY_RHODOLITE_BLOCK);

        getOrCreateTagBuilder(BlockTags.CROPS)
                .add(ModBlocks.RASPBERRY_BUSH)
                .add(ModBlocks.COTTON_CROP);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.COCONUT_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_COCONUT_SAPLING);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.COCONUT_LEAVES)
                .add(ModBlocks.COCONUT_LEAVES_CORNER);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.COCONUT_LOGS);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.COCONUT_PLANKS);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.COCONUT_SAPLING);

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.COCONUT_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.COCONUT_WALL_HANGING_SIGN);
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.COCONUT_SIGN);
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(ModBlocks.COCONUT_WALL_SIGN);





        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.COCONUT_BUTTON);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.COCONUT_DOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.COCONUT_FENCE);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.COCONUT_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.COCONUT_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.COCONUT_STAIRS);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.COCONUT_TRAPDOOR);
    }
}

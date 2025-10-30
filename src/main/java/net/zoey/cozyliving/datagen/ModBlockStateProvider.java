package net.zoey.cozyliving.datagen;

import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.block.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CozyLiving.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        signBlock(
            (StandingSignBlock) ModBlocks.COCONUT_SIGN.block(),
            (WallSignBlock) ModBlocks.COCONUT_WALL_SIGN.block(),
            blockTexture(ModBlocks.COCONUT_PLANKS.block())
        );
        hangingSignBlock(
            ModBlocks.COCONUT_HANGING_SIGN.block(),
            ModBlocks.COCONUT_WALL_HANGING_SIGN.block(),
            blockTexture(ModBlocks.COCONUT_PLANKS.block())
        );

        logBlock((RotatedPillarBlock) ModBlocks.COCONUT_LOG.block());
        axisBlock(
            (RotatedPillarBlock) ModBlocks.COCONUT_WOOD.block(),
            blockTexture(ModBlocks.COCONUT_LOG.block()),
            blockTexture(ModBlocks.COCONUT_LOG.block())
        );
        axisBlock(
            (RotatedPillarBlock) ModBlocks.STRIPPED_COCONUT_LOG.block(),
            blockTexture(ModBlocks.STRIPPED_COCONUT_LOG.block()),
            ResourceLocation.fromNamespaceAndPath(
                CozyLiving.MODID,
                "block/stripped_coconut_log_top"
            )
        );
        axisBlock(
            (RotatedPillarBlock) ModBlocks.STRIPPED_COCONUT_WOOD.block(),
            blockTexture(ModBlocks.STRIPPED_COCONUT_LOG.block()),
            blockTexture(ModBlocks.STRIPPED_COCONUT_LOG.block())
        );

        blockItem(ModBlocks.COCONUT_LOG);
        blockItem(ModBlocks.COCONUT_WOOD);
        blockItem(ModBlocks.STRIPPED_COCONUT_LOG);
        blockItem(ModBlocks.STRIPPED_COCONUT_WOOD);

        blockWithItem(ModBlocks.COCONUT_PLANKS);

        leavesBlock(ModBlocks.COCONUT_LEAVES.registryObject());

        getVariantBuilder(ModBlocks.RASPBERRY_BUSH.block()).forAllStates(this::raspberryStates);
        getVariantBuilder(ModBlocks.COCONUT_PLANT.block()).forAllStates(state -> new ConfiguredModel[] {
            exactModel(switch (state.getValue(CoconutPlantBlock.AGE)) {
                case 0 -> "block/coconut_plant_small";
                case 1 -> "block/coconut_plant_medium";
                default -> "block/coconut_plant_large";
            })
        });

        simpleBlockWithItem(ModBlocks.COCONUT.block(), modelFile("block/coconut"));

        genericSliceableBlock(ModBlocks.RASPBERRY_PIE.block(), "raspberry_pie");
        genericSliceableBlock(ModBlocks.CINNAMON_PIE.block(), "cinnamon_pie");
        genericSliceableBlock(ModBlocks.GLOWBERRY_TART.block(), "glowberry_tart");
    }

    public void genericSliceableBlock(Block block, String name) {
        // TODO: Rotate the model based on FACING
        getVariantBuilder(block).forAllStates(state -> new ConfiguredModel[] {
            new ConfiguredModel(models()
                .withExistingParent(
                    name + "_" + state.getValue(SliceableFoodBlock.BITES),
                    modLoc("block/generic_sliceable_" + state.getValue(
                        SliceableFoodBlock.BITES))
                )
                .texture("inside", modLoc("block/" + name + "/inside"))
                .texture("outside", modLoc("block/" + name + "/outside"))
                .texture("top", modLoc("block/" + name + "/top"))
                .texture("bottom", modLoc("block/" + name + "/bottom"))
                .texture("particle", modLoc("block/" + name + "/top")))
        });
    }

    public ModelFile modelFile(String id) {
        return modelFile(ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, id));
    }

    public ModelFile modelFile(ResourceLocation location) {
        return models().getExistingFile(location);
    }

    public ConfiguredModel exactModel(
        String id
    ) {
        return new ConfiguredModel(modelFile(id));
    }

    public ConfiguredModel exactModel(
        ResourceLocation location
    ) {
        return new ConfiguredModel(modelFile(location));
    }

    private ConfiguredModel[] raspberryStates(
        BlockState state
    ) {
        if (state.getValue(RaspberryBushBlock.AGE) == 4) {
            return raspberryStatesAge4(state);
        } else {
            return new ConfiguredModel[] {
                raspberryState(state)
            };
        }
    }

    public ConfiguredModel tintedCross(
        String modelName,
        String mainTexture,
        String overlay
    ) {
        return tintedCross(
            modelName,
            ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, mainTexture),
            ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, overlay)
        );
    }

    public ConfiguredModel tintedCross(
        String modelName,
        ResourceLocation mainTexture,
        ResourceLocation overlay
    ) {
        return tintedCross(modelName, mainTexture, overlay, mainTexture);
    }

    public ConfiguredModel tintedCross(
        String modelName,
        ResourceLocation mainTexture,
        ResourceLocation overlay,
        ResourceLocation particle
    ) {
        return new ConfiguredModel(models()
            .withExistingParent(
                modelName,
                ResourceLocation.fromNamespaceAndPath(
                    CozyLiving.MODID,
                    "block/tinted_cross"
                )
            )
            .texture("0", mainTexture)
            .texture("overlay", overlay)
            .texture("particle", particle)
            .renderType("cutout"));
    }

    public ConfiguredModel cropTintedCross(
        String modelName,
        String mainTexture,
        String overlay
    ) {
        return cropTintedCross(
            modelName,
            ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, mainTexture),
            ResourceLocation.fromNamespaceAndPath(CozyLiving.MODID, overlay)
        );
    }

    public ConfiguredModel cropTintedCross(
        String modelName,
        ResourceLocation mainTexture,
        ResourceLocation overlay
    ) {
        return cropTintedCross(modelName, mainTexture, overlay, mainTexture);
    }

    public ConfiguredModel cropTintedCross(
        String modelName,
        ResourceLocation mainTexture,
        ResourceLocation overlay,
        ResourceLocation particle
    ) {
        return new ConfiguredModel(models()
            .withExistingParent(
                modelName,
                ResourceLocation.fromNamespaceAndPath(
                    CozyLiving.MODID,
                    "block/crop_tinted_cross"
                )
            )
            .texture("0", mainTexture)
            .texture("overlay", overlay)
            .texture("particle", particle)
            .renderType("cutout"));
    }

    private ConfiguredModel raspberryState(BlockState state) {
        var age = state.getValue(RaspberryBushBlock.AGE);
        var half = state.getValue(RaspberryBushBlock.HALF).equals(DoubleBlockHalf.UPPER)
            ? "upper"
            : "lower";

        return tintedCross(
            "raspberry_bush_" + half + "_" + age,
            "block/raspberry_bush_" + half + "_" + age,
            "block/transparent"
        );
    }

    private ConfiguredModel[] raspberryStatesAge4(BlockState state) {
        var half = state.getValue(RaspberryBushBlock.HALF).equals(DoubleBlockHalf.UPPER)
            ? "upper"
            : "lower";
        var models = new ConfiguredModel[4];
        for (var model = 0; model < 4; model++) {
            models[model] = tintedCross(
                "raspberry_bush_" + half + "_4",
                "block/raspberry_bush_" + half + "_3",
                "block/raspberry_bush_overlay_" + model
            );
        }
        return models;
    }

    public void hangingSignBlock(
        Block signBlock,
        Block wallSignBlock,
        ResourceLocation texture
    ) {
        var sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void leavesBlock(RegistryObject<Block> block) {
        simpleBlockWithItem(
            block.get(), models().singleTexture(
                name(block.get()),
                ResourceLocation.fromNamespaceAndPath("minecraft", "block/leaves"),
                "all",
                blockTexture(block.get())
            ).renderType("cutout")
        );
    }

    private void blockItem(ModBlocks block) {
        blockItem(block.registryObject());
    }

    private void blockItem(RegistryObject<Block> block) {
        simpleBlockItem(
            block.get(),
            new ModelFile.UncheckedModelFile(
                CozyLiving.MODID + ":block/" + name(block.get()))
        );
    }

    private void blockWithItem(ModBlocks block) {
        blockWithItem(block.registryObject());
    }

    private void blockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}

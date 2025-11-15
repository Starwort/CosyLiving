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
            modLoc("block/stripped_coconut_log_top")
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

        getVariantBuilder(ModBlocks.COTTON_CROP.block()).forAllStates(state -> {
            var age = state.getValue(CropBlock.AGE);
            return new ConfiguredModel[] {
                cropTintedCross(
                    "cotton_crop_" + age,
                    "block/cotton_crop_overlay_" + age,
                    "block/cotton_crop_" + age
                ),
            };
        });

        simpleBlock(
            ModBlocks.COTTON_SHRUB.block(),
            tintedCross(
                "cotton_shrub",
                "block/cotton_crop_overlay_7",
                "block/cotton_crop_7"
            )
        );
        simpleBlock(
            ModBlocks.POTTED_COTTON.block(),
            exactModel("block/potted_cotton_shrub")
        );

        simpleBlock(
            ModBlocks.POTTED_COCONUT_SAPLING.block(),
            models()
                .singleTexture(
                    "potted_coconut_sapling",
                    ResourceLocation.parse("flower_pot_cross"),
                    "plant",
                    blockTexture(ModBlocks.COCONUT_SAPLING.block())
                )
                .renderType("cutout")
        );

        axisBlock(
            ((RotatedPillarBlock) ModBlocks.COTTON_BALE.block()),
            modLoc("block/cotton_bale_side"),
            modLoc("block/cotton_bale_top")
        );
        blockItem(ModBlocks.COTTON_BALE);

        simpleBlockWithItem(
            ModBlocks.COCONUT_CRATE.block(), models().cubeBottomTop(
                "coconut_crate",
                modLoc("block/crate/coconut/side"),
                modLoc("block/crate/bottom"),
                modLoc("block/crate/coconut/top")
            )
        );
        simpleBlockWithItem(
            ModBlocks.RASPBERRY_CRATE.block(), models().cubeBottomTop(
                "raspberry_crate",
                modLoc("block/crate/raspberry/side"),
                modLoc("block/crate/bottom"),
                modLoc("block/crate/raspberry/top")
            )
        );
        saplingBlock(ModBlocks.COCONUT_SAPLING);
    }

    public void genericSliceableBlock(Block block, String name) {
        getVariantBuilder(block).forAllStates(state -> new ConfiguredModel[] {
            new ConfiguredModel(
                models()
                    .withExistingParent(
                        name + "_" + state.getValue(SliceableFoodBlock.BITES),
                        modLoc("block/generic_sliceable_" + state.getValue(
                            SliceableFoodBlock.BITES))
                    )
                    .texture("inside", modLoc("block/" + name + "/inside"))
                    .texture("outside", modLoc("block/" + name + "/outside"))
                    .texture("top", modLoc("block/" + name + "/top"))
                    .texture("bottom", modLoc("block/" + name + "/bottom"))
                    .texture("particle", modLoc("block/" + name + "/top")),
                0,
                switch (state.getValue(SliceableFoodBlock.FACING)) {
                    case DOWN, UP ->
                        throw new IllegalStateException("FACING cannot be up or down");
                    case NORTH -> 270;
                    case EAST -> 0;
                    case SOUTH -> 90;
                    case WEST -> 180;
                },
                false
            )
        });
    }

    public void saplingBlock(ModBlocks block) {
        simpleBlock(
            block.block(),
            models()
                .cross(block.id().getPath(), blockTexture(block.block()))
                .renderType("cutout")
        );
    }

    public ModelFile modelFile(String id) {
        return modelFile(modLoc(id));
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

    public ConfiguredModel raspberryBush(
        String modelName,
        String tinted,
        String untinted
    ) {
        return new ConfiguredModel(models()
            .withExistingParent(modelName, modLoc("block/raspberry_bush"))
            .texture("tinted", modLoc(tinted))
            .texture("untinted", modLoc(untinted))
            .texture("particle", modLoc(tinted))
            .renderType("cutout"));
    }

    public ConfiguredModel tintedCross(
        String modelName,
        String tinted,
        String untinted
    ) {
        return tintedCross(modelName, modLoc(tinted), modLoc(untinted));
    }

    public ConfiguredModel tintedCross(
        String modelName,
        ResourceLocation tinted,
        ResourceLocation untinted
    ) {
        return tintedCross(modelName, tinted, untinted, tinted);
    }

    public ConfiguredModel tintedCross(
        String modelName,
        ResourceLocation tinted,
        ResourceLocation untinted,
        ResourceLocation particle
    ) {
        return new ConfiguredModel(models()
            .withExistingParent(modelName, modLoc("block/tinted_cross"))
            .texture("tinted", tinted)
            .texture("untinted", untinted)
            .texture("particle", particle)
            .renderType("cutout"));
    }

    public ConfiguredModel cropTintedCross(
        String modelName,
        String tinted,
        String untinted
    ) {
        return cropTintedCross(modelName, modLoc(tinted), modLoc(untinted));
    }

    public ConfiguredModel cropTintedCross(
        String modelName,
        ResourceLocation tinted,
        ResourceLocation untinted
    ) {
        return cropTintedCross(modelName, tinted, untinted, tinted);
    }

    public ConfiguredModel cropTintedCross(
        String modelName,
        ResourceLocation tinted,
        ResourceLocation untinted,
        ResourceLocation particle
    ) {
        return new ConfiguredModel(models()
            .withExistingParent(modelName, modLoc("block/crop_tinted_cross"))
            .texture("tinted", tinted)
            .texture("untinted", untinted)
            .texture("particle", particle)
            .renderType("cutout"));
    }

    private ConfiguredModel raspberryState(BlockState state) {
        var age = state.getValue(RaspberryBushBlock.AGE);
        var half = state.getValue(RaspberryBushBlock.HALF).equals(DoubleBlockHalf.UPPER)
            ? "upper"
            : "lower";

        return raspberryBush(
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
            models[model] = raspberryBush(
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
        simpleBlockItem(block.get(), modelFile("block/" + name(block.get())));
    }

    private void blockWithItem(ModBlocks block) {
        blockWithItem(block.registryObject());
    }

    private void blockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}

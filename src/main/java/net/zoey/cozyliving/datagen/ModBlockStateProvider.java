package net.zoey.cozyliving.datagen;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.block.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CozyLiving.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        var planks = blockTexture(ModBlocks.COCONUT_PLANKS.block());
        signBlock(
            (StandingSignBlock) ModBlocks.COCONUT_SIGN.block(),
            (WallSignBlock) ModBlocks.COCONUT_WALL_SIGN.block(),
            planks
        );
        hangingSignBlock(
            ModBlocks.COCONUT_HANGING_SIGN.block(),
            ModBlocks.COCONUT_WALL_HANGING_SIGN.block(),
            planks
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

        leavesBlock(ModBlocks.COCONUT_LEAVES.holder());
        leavesCornerBlock(ModBlocks.COCONUT_LEAVES_CORNER.holder());

        getVariantBuilder(ModBlocks.RASPBERRY_BUSH.block()).forAllStates(this::raspberryStates);
        getVariantBuilder(ModBlocks.COCONUT_PLANT.block()).forAllStates(state -> new ConfiguredModel[]{
            exactModel(switch (state.getValue(CoconutPlantBlock.AGE)) {
                case 0 -> "block/coconut_plant_small";
                case 1 -> "block/coconut_plant_medium";
                default -> "block/coconut_plant_large";
            })
        });

        simpleBlockWithItem(ModBlocks.COCONUT.block(), modelFile("block/coconut"));

        sliceableBlock(ModBlocks.RASPBERRY_PIE.block(), "raspberry_pie", "generic");
        sliceableBlock(ModBlocks.CINNAMON_PIE.block(), "cinnamon_pie", "generic");
        sliceableBlock(ModBlocks.GLOWBERRY_TART.block(), "glowberry_tart", "generic");

        getVariantBuilder(ModBlocks.COTTON_CROP.block()).forAllStates(state -> {
            var age = state.getValue(CropBlock.AGE);
            return new ConfiguredModel[]{
                cropTintedCross(
                    "cotton_crop_" + age,
                    "block/cotton_crop_overlay_" + age,
                    "block/cotton_crop_" + age
                ),
            };
        });

        simpleBlock(
            ModBlocks.COTTON_SHRUB.block(),
            partiallyTintedCross(
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
            ModBlocks.POTTED_COCONUT_SAPLING.block(), models().singleTexture(
                "potted_coconut_sapling",
                ResourceLocation.parse("flower_pot_cross"),
                "plant",
                blockTexture(ModBlocks.COCONUT_SAPLING.block())
            ).renderType("cutout")
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
        simpleBlock(ModBlocks.TRELLISED_COCONUT_SAPLING.block(), modelFile(modLoc("block/trellised_coconut_sapling")));

        pressurePlateBlock(
            (PressurePlateBlock) ModBlocks.COCONUT_PRESSURE_PLATE.block(),
            planks
        );
        itemModels().pressurePlate("coconut_pressure_plate", planks);
        trapdoorBlockWithRenderType(
            (TrapDoorBlock) ModBlocks.COCONUT_TRAPDOOR.block(),
            modLoc("block/coconut_trapdoor"),
            true,
            "cutout"
        );
        itemModels()
            .trapdoorOrientableBottom(
                "coconut_trapdoor",
                modLoc("block/coconut_trapdoor")
            )
            .renderType("cutout");
        stairsBlock((StairBlock) ModBlocks.COCONUT_STAIRS.block(), planks);
        itemModels().stairs("coconut_stairs", planks, planks, planks);
        buttonBlock((ButtonBlock) ModBlocks.COCONUT_BUTTON.block(), planks);
        itemModels().buttonInventory("coconut_button", planks);
        slabBlock((SlabBlock) ModBlocks.COCONUT_SLAB.block(), planks, planks);
        itemModels().slab("coconut_slab", planks, planks, planks);
        fenceBlock((FenceBlock) ModBlocks.COCONUT_FENCE.block(), planks);
        itemModels().fenceInventory("coconut_fence", planks);
        fenceGateBlock((FenceGateBlock) ModBlocks.COCONUT_FENCE_GATE.block(), planks);
        itemModels().fenceGate("coconut_fence_gate", planks);
        doorBlockWithRenderType(
            (DoorBlock) ModBlocks.COCONUT_DOOR.block(),
            modLoc("block/coconut_door_bottom"),
            modLoc("block/coconut_door_top"),
            "cutout");


        blockWithItem(ModBlocks.RASPBERRY_RHODOLITE_BLOCK);
        blockWithItem(ModBlocks.RASPBERRY_RHODOLITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_RASPBERRY_RHODOLITE_ORE);

        blockWithItem(ModBlocks.BENITOITE_BLOCK);
        blockWithItem(ModBlocks.BENITOITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_BENITOITE_ORE);

        sliceableBlock(ModBlocks.GOLDEN_CARROT_CAKE.block(), "golden_carrot_cake", "large");
        sliceableBlock(ModBlocks.RED_VELVET_CAKE.block(), "red_velvet_cake", "large");

        getVariantBuilder(ModBlocks.PINK_PAMPAS_GRASS.block()).forAllStates(state -> new ConfiguredModel[]{
            switch (state.getValue(TallFlowerBlock.HALF)) {
                case UPPER -> partiallyTintedCross(
                    "pink_pampas_grass_upper",
                    "block/pampas_grass_upper",
                    "block/pink_pampas_grass_feathers"
                );
                default -> tintedCross(
                    "pampas_grass_lower",
                    "block/pampas_grass_lower"
                );
            }
        });

        getVariantBuilder(ModBlocks.WHITE_PAMPAS_GRASS.block()).forAllStates(state -> new ConfiguredModel[]{
            switch (state.getValue(TallFlowerBlock.HALF)) {
                case UPPER -> partiallyTintedCross(
                    "white_pampas_grass_upper",
                    "block/pampas_grass_upper",
                    "block/white_pampas_grass_feathers"
                );
                default -> tintedCross(
                    "pampas_grass_lower",
                    "block/pampas_grass_lower"
                );
            }
        });

    }

    public void sliceableBlock(Block block, String name, String size) {
        getVariantBuilder(block).forAllStates(state -> new ConfiguredModel[]{
            new ConfiguredModel(
                models()
                    .withExistingParent(
                        name + "_" + state.getValue(GenericSliceableFoodBlock.BITES),
                        modLoc("block/" + size + "_sliceable_" + state.getValue(
                            GenericSliceableFoodBlock.BITES))
                    )
                    .texture("inside", modLoc("block/" + name + "/inside"))
                    .texture("outside", modLoc("block/" + name + "/outside"))
                    .texture("top", modLoc("block/" + name + "/top"))
                    .texture("bottom", modLoc("block/" + name + "/bottom"))
                    .texture("particle", modLoc("block/" + name + "/top")),
                0,
                switch (state.getValue(GenericSliceableFoodBlock.FACING)) {
                    case DOWN, UP -> throw new IllegalStateException("FACING cannot be up or down");
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
            return new ConfiguredModel[]{
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

    public ConfiguredModel partiallyTintedCross(
        String modelName,
        String tinted,
        String untinted
    ) {
        return partiallyTintedCross(modelName, modLoc(tinted), modLoc(untinted));
    }

    public ConfiguredModel partiallyTintedCross(
        String modelName,
        ResourceLocation tinted,
        ResourceLocation untinted
    ) {
        return partiallyTintedCross(modelName, tinted, untinted, tinted);
    }

    public ConfiguredModel partiallyTintedCross(
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

    public ConfiguredModel tintedCross(
        String modelName,
        String tinted
    ) {
        return tintedCross(modelName, modLoc(tinted));
    }

    public ConfiguredModel tintedCross(
        String modelName,
        ResourceLocation tinted
    ) {
        return tintedCross(modelName, tinted, tinted);
    }

    public ConfiguredModel tintedCross(
        String modelName,
        ResourceLocation tinted,
        ResourceLocation particle
    ) {
        return new ConfiguredModel(models()
            .withExistingParent(modelName, ResourceLocation.withDefaultNamespace("block/tinted_cross"))
            .texture("cross", tinted)
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
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private void leavesBlock(DeferredHolder<Block, Block> block) {
        simpleBlockWithItem(
            block.get(), models().singleTexture(
                name(block.get()),
                ResourceLocation.fromNamespaceAndPath("minecraft", "block/leaves"),
                "all",
                blockTexture(block.get())
            ).renderType("cutout")
        );
    }

    private void leavesCornerBlock(DeferredHolder<Block, Block> block) {
        simpleBlockWithItem(
            block.get(), models().singleTexture(
                name(block.get()),
                ResourceLocation.fromNamespaceAndPath("minecraft", "block/leaves"),
                "all",
                blockTexture(ModBlocks.COCONUT_LEAVES.block())
            ).renderType("cutout")
        );
    }

    private void blockItem(ModBlocks block) {
        blockItem(block.holder());
    }

    private void blockItem(DeferredHolder<Block, Block> block) {
        simpleBlockItem(block.get(), modelFile("block/" + name(block.get())));
    }

    private void blockWithItem(ModBlocks block) {
        blockWithItem(block.holder());
    }

    private void blockWithItem(DeferredHolder<Block, Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}

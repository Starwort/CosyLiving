package net.zoey.cozyliving.block;

//import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
//import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
//import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
//import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
//import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.custom.*;
import net.zoey.cozyliving.foodComponents.ModFoodComponents;
import net.zoey.cozyliving.item.ModItems;
import net.zoey.cozyliving.item.custom.TooltipBlockItem;
import net.zoey.cozyliving.sound.ModSounds;
import net.zoey.cozyliving.world.ModConfiguredFeatures;
import net.zoey.cozyliving.world.gen.coconut_tree.CoconutSaplingGenerator;

import java.util.Optional;

public class ModBlocks {

    //Test blocks
    public static final Block TEST_BLOCK = registerBlock("test_block",
            new TestBlock(AbstractBlock.Settings.create()));

    //Plant blocks
    public static final Block RASPBERRY_BUSH = registerBlockWithoutItem("raspberry_bush",
            new RaspberryBushBlock(AbstractBlock.Settings.copy(Blocks.SWEET_BERRY_BUSH)));

    public static final Block COCONUT = registerToolTipBlock("coconut",
            new CoconutBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN).sounds(ModSounds.COCONUT_SOUNDS).nonOpaque().hardness(0.3f)),
            "coconut");

    public static final Block COCONUT_PLANT = registerBlockWithoutItem("coconut_plant",
            new CoconutPlantBlock(AbstractBlock.Settings.copy(COCONUT)));

    public static final Block COCONUT_SAPLING = registerBlock("coconut_sapling",
            new CoconutSaplingBlock(new CoconutSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.JUNGLE_SAPLING)));

    public static final Block COCONUT_LEAVES = registerBlock("coconut_leaves",
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.JUNGLE_LEAVES)));

    public static final Block COCONUT_LEAVES_CORNER = registerBlock("coconut_leaves_corner",
            new CoconutLeavesCornerBlock(AbstractBlock.Settings.copy(ModBlocks.COCONUT_LEAVES)));

    //COTTON BLOCKS
    public static final Block COTTON_SHRUB = registerBlockWithoutItem("cotton_shrub",
            new CottonShrubBlock(FabricBlockSettings.create().mapColor(MapColor.OFF_WHITE).
                    noCollision().breakInstantly().sounds(BlockSoundGroup.CROP)
                    .offset(AbstractBlock.OffsetType.XZ).burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block POTTED_COTTON = registerBlockWithoutItem("potted_cotton_shrub",
            new FlowerPotBlock(COTTON_SHRUB, FabricBlockSettings.copy(Blocks.POTTED_FERN).mapColor(MapColor.OFF_WHITE)));

    public static final Block COTTON_CROP = registerBlockWithoutItem("cotton_crop",
            new CottonCropBlock(FabricBlockSettings.create().mapColor(MapColor.OFF_WHITE).noCollision().ticksRandomly()
                    .breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).burnable()));

    public static final Block COTTON_BALE = registerBlockWithoutItem("cotton_bale",
            new CottonBaleBlock(FabricBlockSettings.create().mapColor(MapColor.OFF_WHITE).sounds(BlockSoundGroup.WOOL).burnable().strength(0.25F).instrument(Instrument.FLUTE).burnable()));

    //CRATE BLOCKS
    public static final Block COCONUT_CRATE = registerBlockWithoutItem("coconut_crate",
            new Block(FabricBlockSettings.create().mapColor(MapColor.BROWN).sounds(BlockSoundGroup.WOOD).burnable().strength(1F)));

    public static final Block RASPBERRY_CRATE = registerBlockWithoutItem("raspberry_crate",
            new Block(FabricBlockSettings.create().mapColor(MapColor.BRIGHT_RED).sounds(BlockSoundGroup.WOOD).burnable().strength(1F)));


    //Sliceable blocks
    public static final Block GLOWBERRY_TART = registerBlockWithoutItem("glowberry_tart",
            new SliceableBlock(ModItems.GLOWBERRY_TART_SLICE, ModFoodComponents.GLOWBERRY_TART,
                    AbstractBlock.Settings.copy(Blocks.CAKE).luminance(state -> 10)));

    public static final Block RASPBERRY_PIE = registerBlockWithoutItem("raspberry_pie",
            new SliceableBlock(ModItems.RASPBERRY_PIE_SLICE, ModFoodComponents.RASPBERRY_PIE,
                    AbstractBlock.Settings.copy(Blocks.CAKE)));

    public static final Block CINNAMON_PIE = registerBlockWithoutItem("cinnamon_pie",
            new SliceableBlock(ModItems.CINNAMON_PIE_SLICE, ModFoodComponents.CINNAMON_PIE,
                    AbstractBlock.Settings.copy(Blocks.CAKE)));

    //Coconut Block Set Type
    static BlockSetType COCONUT_BLOCK_SET_TYPE = new BlockSetType("coconut", true, BlockSoundGroup.WOOD, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
    //Coconut Wood Type
    static WoodType COCONUT_WOOD_TYPE = new WoodType("coconut", COCONUT_BLOCK_SET_TYPE);

    //Coconut wood blocks
    public static final Block COCONUT_PLANKS = registerBlock("coconut_planks",
            new Block(AbstractBlock.Settings.copy(Blocks.MANGROVE_PLANKS)));

    public static final Block COCONUT_LOG = registerBlock("coconut_log",
            new CoconutLogBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_LOG)));

    public static final Block COCONUT_WOOD = registerBlock("coconut_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_WOOD)));

    public static final Block STRIPPED_COCONUT_LOG = registerBlock("stripped_coconut_log",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_MANGROVE_LOG)));

    public static final Block STRIPPED_COCONUT_WOOD = registerBlock("stripped_coconut_wood",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_MANGROVE_WOOD)));

    public static final Block COCONUT_PRESSURE_PLATE = registerBlock("coconut_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.MANGROVE_PRESSURE_PLATE), COCONUT_BLOCK_SET_TYPE));

    public static final Block COCONUT_TRAPDOOR = registerBlock("coconut_trapdoor",
            new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_TRAPDOOR), COCONUT_BLOCK_SET_TYPE));

    public static final Block COCONUT_STAIRS = registerBlock("coconut_stairs",
            new StairsBlock(COCONUT_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(COCONUT_PLANKS)));

    public static final Block COCONUT_BUTTON = registerBlock("coconut_button",
            new ButtonBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_BUTTON), COCONUT_BLOCK_SET_TYPE, 30, true));

    public static final Block COCONUT_SLAB = registerBlock("coconut_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_SLAB)));

    public static final Block COCONUT_FENCE_GATE = registerBlock("coconut_fence_gate",
            new FenceGateBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_FENCE_GATE), COCONUT_WOOD_TYPE));

    public static final Block COCONUT_FENCE = registerBlock("coconut_fence",
            new FenceBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_FENCE)));

    public static final Block COCONUT_DOOR = registerBlock("coconut_door",
            new DoorBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_DOOR).nonOpaque(), COCONUT_BLOCK_SET_TYPE));

    //POTTED PLANTS
    public static final Block POTTED_COCONUT_SAPLING = registerBlock("potted_coconut_sapling", Blocks.createFlowerPotBlock(ModBlocks.COCONUT_SAPLING));

    //SIGN STUFF
    public static final Identifier COCONUT_SIGN_TEXTURE = Identifier.of(CozyLiving.MOD_ID, "entity/signs/coconut");
    public static final Identifier COCONUT_HANGING_SIGN_TEXTURE = Identifier.of(CozyLiving.MOD_ID, "entity/signs/hanging/coconut");
    public static final Identifier COCONUT_HANGING_GUI_SIGN_TEXTURE = Identifier.of(CozyLiving.MOD_ID, "textures/gui/hanging_signs/coconut");

    public static final Block COCONUT_SIGN = registerBlockWithoutItem("coconut_standing_sign",
            new TerraformSignBlock(COCONUT_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.MANGROVE_SIGN)));
    public static final Block COCONUT_WALL_SIGN = registerBlockWithoutItem("coconut_wall_sign",
            new TerraformWallSignBlock(COCONUT_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.MANGROVE_WALL_SIGN)));
    public static final Block COCONUT_HANGING_SIGN = registerBlockWithoutItem("coconut_hanging_sign",
            new TerraformHangingSignBlock(COCONUT_HANGING_SIGN_TEXTURE, COCONUT_HANGING_GUI_SIGN_TEXTURE,AbstractBlock.Settings.copy(Blocks.MANGROVE_HANGING_SIGN)));
    public static final Block COCONUT_WALL_HANGING_SIGN = registerBlockWithoutItem("coconut_wall_hanging_sign",
            new TerraformWallHangingSignBlock(COCONUT_HANGING_SIGN_TEXTURE, COCONUT_HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.copy(Blocks.MANGROVE_WALL_HANGING_SIGN)));

    public static final BlockFamily COCONUT_FAMILY = BlockFamilies.register(ModBlocks.COCONUT_PLANKS)
            .sign(ModBlocks.COCONUT_SIGN, ModBlocks.COCONUT_WALL_SIGN)
            .group("wooden").unlockCriterionName("has_planks").build();

    //Old gemstone blocks

    public static final Block RASPBERRY_RHODOLITE_BLOCK = registerBlock("raspberry_rhodolite_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.DARK_RED).requiresTool().strength(5.5F, 6.5F).sounds(BlockSoundGroup.METAL)));

    public static final Block RASPBERRY_RHODOLITE_ORE = registerBlock("raspberry_rhodolite_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(3.5F, 3.5F), UniformIntProvider.create(16, 24)));

    public static final Block DEEPSLATE_RASPBERRY_RHODOLITE_ORE = registerBlock("deepslate_raspberry_rhodolite_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(RASPBERRY_RHODOLITE_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(5.5F, 3.5F).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(16, 24)));

    public static final Block BENITOITE_BLOCK = registerBlock("benitoite_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BLUE).requiresTool().strength(5.5F, 6.5F).sounds(BlockSoundGroup.METAL)));

    public static final Block BENITOITE_ORE = registerBlock("benitoite_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(3.5F, 3.5F), UniformIntProvider.create(16, 24)));

    public static final Block DEEPSLATE_BENITOITE_ORE = registerBlock("deepslate_benitoite_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(BENITOITE_ORE).mapColor(MapColor.DEEPSLATE_GRAY).strength(5.5F, 3.5F).sounds(BlockSoundGroup.DEEPSLATE), UniformIntProvider.create(16, 24)));

    private static Block registerToolTipBlock(String name, Block block, String translationID) {
        registerToolTipBlockItem(name, block, translationID);
        return Registry.register(Registries.BLOCK, Identifier.of(CozyLiving.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CozyLiving.MOD_ID, name), block);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(CozyLiving.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CozyLiving.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    private static void registerToolTipBlockItem(String name, Block block, String translationID) {
        Registry.register(Registries.ITEM, Identifier.of(CozyLiving.MOD_ID, name),
                new TooltipBlockItem(block, new Item.Settings(), translationID));
    }

    public static void registerModBlocks() {
        CozyLiving.LOGGER.info("Registering Mod Blocks for " + CozyLiving.MOD_ID);
    }
}

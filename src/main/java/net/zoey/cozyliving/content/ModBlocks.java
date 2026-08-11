package net.zoey.cozyliving.content;

import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.tags.*;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.grower.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.advancement.*;
import net.zoey.cozyliving.content.block.*;
import net.zoey.cozyliving.content.block.entity.*;
import net.zoey.cozyliving.content.common.*;
import net.zoey.cozyliving.content.item.*;
import net.zoey.cozyliving.level.gen.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public enum ModBlocks {

    COCONUT_LOG(
        "coconut_log",
        () -> new CoconutLogBlock(BlockBehaviour.Properties
            .ofFullCopy(Blocks.MANGROVE_LOG)
            .strength(3f)) {

        }

    ),

    COCONUT_WOOD(
        "coconut_wood",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .ofFullCopy(Blocks.MANGROVE_WOOD)
            .strength(3f))
    ),

    STRIPPED_COCONUT_LOG(
        "stripped_coconut_log",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .ofFullCopy(Blocks.STRIPPED_MANGROVE_LOG)
            .strength(3f))
    ),

    STRIPPED_COCONUT_WOOD(
        "stripped_coconut_wood",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .ofFullCopy(Blocks.STRIPPED_MANGROVE_WOOD)
            .strength(3f))
    ),

    COCONUT_PLANKS(
        "coconut_planks",
        () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS)) {
            @Override
            public boolean isFlammable(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull Direction direction
            ) {
                return true;
            }

            @Override
            public int getFlammability(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull Direction direction
            ) {
                return 20;
            }

            @Override
            public int getFireSpreadSpeed(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull Direction direction
            ) {
                return 5;
            }
        }
    ),

    COCONUT_LEAVES(
        "coconut_leaves",
        () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_LEAVES)) {
            @Override
            public boolean isFlammable(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull Direction direction
            ) {
                return true;
            }

            @Override
            public int getFlammability(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull Direction direction
            ) {
                return 60;
            }

            @Override
            public int getFireSpreadSpeed(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull Direction direction
            ) {
                return 30;
            }
        }
    ),

    COCONUT_LEAVES_CORNER(
        "coconut_leaves_corner",
        () -> new CoconutLeavesCornerBlock(
            BlockBehaviour.Properties.ofFullCopy(ModBlocks.COCONUT_LEAVES.block())
        ),
        null
    ),

    COCONUT_SIGN(
        "coconut_sign",
        () -> new CustomStandingSignBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SIGN),
            ModWoodTypes.COCONUT
        ),
        null
    ),

    COCONUT_WALL_SIGN(
        "coconut_wall_sign",
        () -> new CustomWallSignBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WALL_SIGN),
            ModWoodTypes.COCONUT
        ),
        null
    ),

    COCONUT_HANGING_SIGN(
        "coconut_hanging_sign",
        () -> new CustomHangingSignBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_HANGING_SIGN),
            ModWoodTypes.COCONUT
        ),
        null
    ),

    COCONUT_WALL_HANGING_SIGN(
        "coconut_wall_hanging_sign",
        () -> new CustomWallHangingSignBlock(
            BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_WALL_HANGING_SIGN),
            ModWoodTypes.COCONUT
        ),
        null
    ),

    RASPBERRY_BUSH(
        "raspberry_bush",
        RaspberryBushBlock::new,
        "raspberry",
        ItemNameTooltipBlockItem::new,
        FoodValues.RASPBERRY.intoProperties()
    ),


    COCONUT_PLANT("coconut_plant", CoconutPlantBlock::new, null),

    COCONUT_SAPLING(
        "coconut_sapling",
        () -> new SaplingBlock(
            new TreeGrower("coconut", Optional.empty(), Optional.of(ModConfiguredFeatures.COCONUT_TREE_KEY), Optional.empty()),
            BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SAPLING)
        ) {
            @Override
            public boolean canSurvive(
                @NotNull BlockState state,
                @NotNull LevelReader level,
                @NotNull BlockPos pos
            ) {
                pos = pos.below();
                var floor = level.getBlockState(pos);
                return floor.is(BlockTags.SAND) || floor.is(BlockTags.DIRT) || floor.is(
                    Blocks.FARMLAND);
            }

            public @NotNull ItemInteractionResult useItemOn(
                @NotNull ItemStack used,
                @NotNull BlockState state,
                @NotNull Level level,
                @NotNull BlockPos pos,
                @NotNull Player player,
                @NotNull InteractionHand hand,
                @NotNull BlockHitResult hit
            ) {
                if (used.is(Items.STICK)) {
                    var currentStage = level.getBlockState(pos).getValue(SaplingBlock.STAGE);
                    level.setBlock(pos, ModBlocks.TRELLISED_COCONUT_SAPLING.block().defaultBlockState().setValue(STAGE, currentStage), Block.UPDATE_ALL);
                    used.consume(1, player);
                    level.playSound(
                        player,
                        pos.getX() + 0.5,
                        pos.getY() + (0.5),
                        pos.getZ() + 0.5,
                        SoundEvents.WOOD_PLACE,
                        SoundSource.BLOCKS,
                        1f,
                        0.8f + level.random.nextFloat() * 0.4f
                    );
                    return ItemInteractionResult.SUCCESS;
                }
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        },
        ItemNameTooltipBlockItem::new
    ),

    TRELLISED_COCONUT_SAPLING(
        "trellised_coconut_sapling",
        () -> new SaplingBlock(
            new TreeGrower("trellised_coconut", Optional.empty(), Optional.of(ModConfiguredFeatures.TRELLISED_COCONUT_TREE_KEY), Optional.empty()),
            BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_SAPLING)

        ) {
            @Override
            public boolean canSurvive(
                @NotNull BlockState state,
                @NotNull LevelReader level,
                @NotNull BlockPos pos
            ) {
                pos = pos.below();
                var floor = level.getBlockState(pos);
                return floor.is(BlockTags.SAND) || floor.is(BlockTags.DIRT) || floor.is(
                    Blocks.FARMLAND);
            }
        },
        null),

    COCONUT("coconut", CoconutBlock::new, TooltipBlockItem::new),

    GLOWBERRY_TART(
        "glowberry_tart",
        () -> new GenericSliceableFoodBlock(
            ModItems.Food.GLOWBERRY_TART_SLICE::item,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)
                .lightLevel((p_50874_) -> 7)
        ),
        ItemNameTooltipBlockItem::new
    ),

    GOLDEN_CARROT_CAKE(
        "golden_carrot_cake",
        () -> new LargeSliceableFoodBlock(
            ModItems.Food.GOLDEN_CARROT_CAKE_SLICE::item,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)
        ),
        ItemNameTooltipBlockItem::new
    ),

    RED_VELVET_CAKE(
        "red_velvet_cake",
        () -> new LargeSliceableFoodBlock(
            ModItems.Food.RED_VELVET_CAKE_SLICE::item,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)
        ),
        ItemNameTooltipBlockItem::new
    ),

    RASPBERRY_PIE(
        "raspberry_pie",
        () -> new GenericSliceableFoodBlock(
            ModItems.Food.RASPBERRY_PIE_SLICE::item,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)
        ),
        ItemNameTooltipBlockItem::new
    ),

    CINNAMON_PIE(
        "cinnamon_pie",
        () -> new GenericSliceableFoodBlock(
            ModItems.Food.CINNAMON_PIE_SLICE::item,
            BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE)
        ),
        ItemNameTooltipBlockItem::new
    ),

    COTTON_CROP(
        "cotton_crop",
        () -> new CropBlock(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.QUARTZ)
            .noCollission()
            .randomTicks()
            .instabreak()
            .sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY)
            .ignitedByLava()) {
            @Override
            protected @NotNull ItemLike getBaseSeedId() {
                return ModBlocks.COTTON_CROP.asItem();
            }
        },
        "cotton_boll",
        (block, props) -> new BurnableBlockItem(block, props, 67)
    ),

    COTTON_SHRUB(
        "cotton_shrub",
        CottonShrub::new,
        (block, props) -> new BurnableBlockItem(block, props, 200)

    ),

    POTTED_COCONUT_SAPLING(
        "potted_coconut_sapling", () -> new FlowerPotBlock(
        () -> ((FlowerPotBlock) Blocks.FLOWER_POT),
        COCONUT_SAPLING::block,
        BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_FERN)
    ), null
    ),

    POTTED_COTTON(
        "potted_cotton_shrub",
        () -> new FlowerPotBlock(
            () -> ((FlowerPotBlock) Blocks.FLOWER_POT),
            COTTON_SHRUB::block,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_FERN).mapColor(MapColor.QUARTZ)
        ),
        null
    ),

    COTTON_BALE(
        "cotton_bale",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.QUARTZ)
            .sound(SoundType.WOOL)
            .ignitedByLava()
            .strength(0.25f)
            .instrument(NoteBlockInstrument.FLUTE)) {


            @Override
            public int getFlammability(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull Direction direction
            ) {
                return 10;
            }

            @Override
            public int getFireSpreadSpeed(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull Direction direction
            ) {
                return 250;
            }

            @Override
            public void fallOn(
                @NotNull Level level,
                @NotNull BlockState state,
                @NotNull BlockPos pos,
                @NotNull Entity entity,
                float fallDistance
            ) {
                entity.causeFallDamage(fallDistance, 0f, level.damageSources().fall());
                if (fallDistance >= 4) {
                    Player entitySource = null;
                    if (entity instanceof Player player) {
                        entitySource = player;
                        player.awardStat(ModStatistics.LAND_ON_COTTON_BALE.asStat());
                        if (entity instanceof ServerPlayer serverPlayer) {
                            ((NegateFallDamageOnCottonBaleTrigger) ModAdvancementTriggers.NEGATE_FALL_DAMAGE_FROM_COTTON_BALE.get()).trigger(serverPlayer);
                        }
                    }
                    level.playSound(
                        entitySource,
                        pos,
                        SoundEvents.WOOL_FALL,
                        SoundSource.BLOCKS
                    );
                }
            }
        },

        (block, props) -> new BurnableBlockItem(block, props, 600)
    ),

    COCONUT_CRATE(
        "coconut_crate",
        () -> new Block(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.COLOR_BROWN)
            .sound(SoundType.WOOD)
            .ignitedByLava()
            .strength(1)),
        TooltipBlockItem::new
    ),

    RASPBERRY_CRATE(
        "raspberry_crate",
        () -> new Block(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.FIRE)
            .sound(SoundType.WOOD)
            .ignitedByLava()
            .strength(1)),
        TooltipBlockItem::new
    ),

    COCONUT_PRESSURE_PLATE(
        "coconut_pressure_plate", () -> new PressurePlateBlock(
        ModWoodTypes.COCONUT_BLOCK_SET_TYPE,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PRESSURE_PLATE)
    )
    ),

    COCONUT_TRAPDOOR(
        "coconut_trapdoor",
        () -> new TrapDoorBlock(
            ModWoodTypes.COCONUT_BLOCK_SET_TYPE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_TRAPDOOR)
        )
    ),

    COCONUT_STAIRS(
        "coconut_stairs",
        () -> new StairBlock(
            COCONUT_PLANKS.block().defaultBlockState(),
            BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_STAIRS)
        )
    ),

    COCONUT_BUTTON(
        "coconut_button", () -> new ButtonBlock(
        ModWoodTypes.COCONUT_BLOCK_SET_TYPE,
        30,
        BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_BUTTON)
    )
    ),

    COCONUT_SLAB(
        "coconut_slab",
        () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_SLAB))
    ),

    COCONUT_FENCE_GATE(
        "coconut_fence_gate",
        () -> new FenceGateBlock(
            ModWoodTypes.COCONUT,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE_GATE)
        )
    ),

    COCONUT_FENCE(
        "coconut_fence",
        () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_FENCE))
    ),

    COCONUT_DOOR(
        "coconut_door",
        () -> new DoorBlock(
            ModWoodTypes.COCONUT_BLOCK_SET_TYPE,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_DOOR).noOcclusion()
        )
    ),

    RASPBERRY_RHODOLITE_BLOCK(
        "raspberry_rhodolite_block",
        () -> new Block(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.NETHER)
            .requiresCorrectToolForDrops()
            .strength(5.5f, 6.5f)
            .sound(SoundType.METAL))
    ),

    RASPBERRY_RHODOLITE_ORE(
        "raspberry_rhodolite_ore", () -> new DropExperienceBlock(
        UniformInt.of(16, 24),
        BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(3.5f, 3.5f)
    )
    ),

    DEEPSLATE_RASPBERRY_RHODOLITE_ORE(
        "deepslate_raspberry_rhodolite_ore", () -> new DropExperienceBlock(
        UniformInt.of(16, 24),
        BlockBehaviour.Properties
            .ofFullCopy(RASPBERRY_RHODOLITE_ORE.block())
            .mapColor(MapColor.DEEPSLATE)
            .strength(5.5f, 3.5f)
            .sound(SoundType.DEEPSLATE)
    )
    ),

    BENITOITE_BLOCK(
        "benitoite_block",
        () -> new Block(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.TERRACOTTA_BLUE)
            .requiresCorrectToolForDrops()
            .strength(5.5f, 6.5f)
            .sound(SoundType.METAL))
    ),

    BENITOITE_ORE(
        "benitoite_ore", () -> new DropExperienceBlock(
        UniformInt.of(16, 24),
        BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(3.5f, 3.5f)
    )
    ),

    DEEPSLATE_BENITOITE_ORE(
        "deepslate_benitoite_ore", () -> new DropExperienceBlock(
        UniformInt.of(16, 24),
        BlockBehaviour.Properties
            .ofFullCopy(BENITOITE_ORE.block())
            .mapColor(MapColor.DEEPSLATE)
            .strength(5.5f, 3.5f)
            .sound(SoundType.DEEPSLATE)
    )
    ),

    TEST_BLOCK(
        "test_block", () -> new TestBlock(
        BlockBehaviour.Properties.of()
    )
    ),

    PINK_PAMPAS_GRASS(
        "pink_pampas_grass", () -> new TallFlowerBlock(
        BlockBehaviour.Properties
            .ofFullCopy(Blocks.LILAC)),
        "pink_pampas_grass",
        ItemNameTooltipBlockItem::new
    ),

    WHITE_PAMPAS_GRASS(
        "white_pampas_grass", () -> new TallFlowerBlock(
        BlockBehaviour.Properties
            .ofFullCopy(Blocks.AZURE_BLUET)),
        "white_pampas_grass",
        ItemNameTooltipBlockItem::new
    );


    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
            "Found {} blocks",
            CozyLiving.BLOCKS.getEntries().size()
        );
        CozyLiving.BLOCKS.register(modEventBus);

        Entities.register(modEventBus);
    }

    private final DeferredHolder<Block, Block> myValue;
    private final @Nullable DeferredHolder<Item, Item> myItem;

    ModBlocks(
        String name,
        Supplier<Block> supplier,
        String itemName,
        @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory,
        @Nullable Item.Properties properties
    ) {
        myValue = CozyLiving.BLOCKS.register(name, supplier);
        if (itemFactory != null) {
            myItem = CozyLiving.ITEMS.register(
                itemName, () -> itemFactory.apply(
                    myValue.get(),
                    properties == null ? new Item.Properties() : properties
                )
            );
        } else {
            myItem = null;
        }
    }

    @SuppressWarnings("unused")
    ModBlocks(
        String name,
        Supplier<Block> supplier,
        @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory,
        @Nullable Item.Properties properties
    ) {
        this(name, supplier, name, itemFactory, properties);
    }

    ModBlocks(
        String name,
        Supplier<Block> supplier,
        String itemName,
        @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory
    ) {
        this(name, supplier, itemName, itemFactory, null);
    }

    ModBlocks(
        String name,
        Supplier<Block> supplier,
        @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory
    ) {
        this(name, supplier, name, itemFactory);
    }

    ModBlocks(String name, Supplier<Block> supplier) {
        this(name, supplier, BlockItem::new);
    }

    public ResourceLocation id() {
        return myValue.getId();
    }

    @SuppressWarnings("unused")
    @Nullable
    public ResourceLocation itemId() {
        if (myItem == null) {
            return null;
        }
        return myItem.getId();
    }

    public DeferredHolder<Block, Block> holder() {
        return myValue;
    }
    @Nullable
    public DeferredHolder<Item, Item> itemHolder() {
        return myItem;
    }

    public Block block() {
        return myValue.get();
    }

    @Nullable
    public Item item() {
        if (myItem == null) {
            return null;
        }
        return myItem.get();
    }

    public Item asItem() {
        if (myItem == null) {
            return Items.AIR;
        }
        return myItem.get();
    }

    public ResourceLocation asItemId() {
        if (myItem == null) {
            return ResourceLocation.withDefaultNamespace("air");
        }
        return myItem.getId();
    }

    public boolean isFoodBlock() {
        return this == ModBlocks.GLOWBERRY_TART || this == ModBlocks.CINNAMON_PIE
            || this == ModBlocks.RASPBERRY_PIE || this == ModBlocks.RASPBERRY_BUSH
            || this == ModBlocks.GOLDEN_CARROT_CAKE || this == ModBlocks.RED_VELVET_CAKE;
    }

    public static class Entities {
        public static void register(IEventBus modEventBus) {
            REGISTER.register(modEventBus);
        }

        public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE,
            CozyLiving.MODID
        );

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CustomSignBE>> SIGN = REGISTER.register(
            "custom_sign", () -> BlockEntityType.Builder.of(
                CustomSignBE::new,
                ModBlocks.COCONUT_SIGN.block(),
                ModBlocks.COCONUT_WALL_SIGN.block()
            ).build(null) // TODO: this is annotated as NotNull?
        );

        public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CustomHangingSignBE>> HANGING_SIGN = REGISTER.register(
            "custom_hanging_sign", () -> BlockEntityType.Builder.of(
                CustomHangingSignBE::new,
                ModBlocks.COCONUT_HANGING_SIGN.block(),
                ModBlocks.COCONUT_WALL_HANGING_SIGN.block()
            ).build(null) // TODO: this is annotated as NotNull?
        );
    }

}

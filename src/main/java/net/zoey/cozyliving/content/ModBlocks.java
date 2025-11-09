package net.zoey.cozyliving.content;

import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.tags.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.shapes.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.block.*;
import net.zoey.cozyliving.content.block.entity.*;
import net.zoey.cozyliving.content.common.*;
import net.zoey.cozyliving.content.item.*;
import net.zoey.cozyliving.level.gen.*;
import org.jetbrains.annotations.*;

import javax.annotation.*;
import javax.annotation.Nullable;
import java.util.*;
import java.util.function.*;

public enum ModBlocks {
    COCONUT_LOG(
        "coconut_log",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .copy(Blocks.MANGROVE_LOG)
            .strength(3f))
    ),

    COCONUT_WOOD(
        "coconut_wood",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .copy(Blocks.MANGROVE_WOOD)
            .strength(3f))
    ),

    STRIPPED_COCONUT_LOG(
        "stripped_coconut_log",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .copy(Blocks.STRIPPED_MANGROVE_LOG)
            .strength(3f))
    ),

    STRIPPED_COCONUT_WOOD(
        "stripped_coconut_wood",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .copy(Blocks.STRIPPED_MANGROVE_WOOD)
            .strength(3f))
    ),

    COCONUT_PLANKS(
        "coconut_planks",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.MANGROVE_PLANKS)) {
            @Override
            public boolean isFlammable(
                BlockState state,
                BlockGetter level,
                BlockPos pos,
                Direction direction
            ) {
                return true;
            }

            @Override
            public int getFlammability(
                BlockState state,
                BlockGetter level,
                BlockPos pos,
                Direction direction
            ) {
                return 20;
            }

            @Override
            public int getFireSpreadSpeed(
                BlockState state,
                BlockGetter level,
                BlockPos pos,
                Direction direction
            ) {
                return 5;
            }
        }
    ),

    COCONUT_LEAVES(
        "coconut_leaves",
        () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_LEAVES)) {
            @Override
            public boolean isFlammable(
                BlockState state,
                BlockGetter level,
                BlockPos pos,
                Direction direction
            ) {
                return true;
            }

            @Override
            public int getFlammability(
                BlockState state,
                BlockGetter level,
                BlockPos pos,
                Direction direction
            ) {
                return 60;
            }

            @Override
            public int getFireSpreadSpeed(
                BlockState state,
                BlockGetter level,
                BlockPos pos,
                Direction direction
            ) {
                return 30;
            }
        }
    ),

    COCONUT_SIGN(
        "coconut_sign",
        () -> new CustomStandingSignBlock(
            BlockBehaviour.Properties.copy(Blocks.MANGROVE_SIGN),
            ModWoodTypes.COCONUT
        ),
        null
    ),

    COCONUT_WALL_SIGN(
        "coconut_wall_sign",
        () -> new CustomWallSignBlock(
            BlockBehaviour.Properties.copy(Blocks.MANGROVE_WALL_SIGN),
            ModWoodTypes.COCONUT
        ),
        null
    ),

    COCONUT_HANGING_SIGN(
        "coconut_hanging_sign",
        () -> new CustomHangingSignBlock(
            BlockBehaviour.Properties.copy(Blocks.MANGROVE_HANGING_SIGN),
            ModWoodTypes.COCONUT
        ),
        null
    ),

    COCONUT_WALL_HANGING_SIGN(
        "coconut_wall_hanging_sign",
        () -> new CustomWallHangingSignBlock(
            BlockBehaviour.Properties.copy(Blocks.MANGROVE_WALL_HANGING_SIGN),
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
            new CoconutTreeGrower(),
            BlockBehaviour.Properties.copy(Blocks.JUNGLE_SAPLING)
        )
        {
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
        }
    ),

    COCONUT("coconut", CoconutBlock::new, TooltipBlockItem::new),

    GLOWBERRY_TART(
        "glowberry_tart",
        () -> new SliceableFoodBlock(
            ModItems.Food.GLOWBERRY_TART_SLICE::item,
            BlockBehaviour.Properties.copy(Blocks.CAKE)
        ),
        ItemNameTooltipBlockItem::new
    ),

    RASPBERRY_PIE(
        "raspberry_pie",
        () -> new SliceableFoodBlock(
            ModItems.Food.RASPBERRY_PIE_SLICE::item,
            BlockBehaviour.Properties.copy(Blocks.CAKE)
        ),
        ItemNameTooltipBlockItem::new
    ),

    CINNAMON_PIE(
        "cinnamon_pie",
        () -> new SliceableFoodBlock(
            ModItems.Food.CINNAMON_PIE_SLICE::item,
            BlockBehaviour.Properties.copy(Blocks.CAKE)
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
            .ignitedByLava())
        {
            @Override
            protected @NotNull ItemLike getBaseSeedId() {
                return ModBlocks.COTTON_CROP.asItem();
            }
        },
        "cotton_boll",
        ItemNameTooltipBlockItem::new
    ),

    COTTON_SHRUB(
        "cotton_shrub",
        () -> new BushBlock(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.QUARTZ)
            .noCollission()
            .instabreak()
            .sound(SoundType.CROP)
            .offsetType(BlockBehaviour.OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
            .ignitedByLava())
        {
            @Override
            @SuppressWarnings("deprecation")
            public @NotNull VoxelShape getShape(
                @NotNull BlockState state,
                @NotNull BlockGetter level,
                @NotNull BlockPos pos,
                @NotNull CollisionContext context
            ) {
                return box(3, 0, 3, 13, 13, 13);
            }
        },
        TooltipBlockItem::new
    ),

    POTTED_COTTON(
        "potted_cotton_shrub",
        () -> new FlowerPotBlock(
            () -> ((FlowerPotBlock) Blocks.FLOWER_POT),
            COTTON_SHRUB::block,
            BlockBehaviour.Properties.copy(Blocks.POTTED_FERN).mapColor(MapColor.QUARTZ)
        ),
        null
    ),

    COTTON_BALE(
        "cotton_bale",
        () -> new RotatedPillarBlock(BlockBehaviour.Properties
            .of()
            .mapColor(MapColor.QUARTZ)
            .sound(SoundType.WOOL)
            .ignitedByLava()
            .strength(0.25f)
            .instrument(NoteBlockInstrument.FLUTE))
        {
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
                        // player.awardStat(ModStatistics.LAND_ON_COTTON_BALE);
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
        TooltipBlockItem::new
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
    ;

    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
            "Found {} blocks",
            CozyLiving.BLOCKS.getEntries().size()
        );
        CozyLiving.BLOCKS.register(modEventBus);

        Entities.register(modEventBus);
    }

    private final RegistryObject<Block> myValue;
    private final @Nullable RegistryObject<Item> myItem;

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

    @Nullable
    public ResourceLocation itemId() {
        if (myItem == null) {
            return null;
        }
        return myItem.getId();
    }

    public RegistryObject<Block> registryObject() {
        return myValue;
    }

    @Nullable
    public RegistryObject<Item> itemRegistryObject() {
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

    public boolean isFoodBlock() {
        return this == ModBlocks.GLOWBERRY_TART || this == ModBlocks.CINNAMON_PIE
            || this == ModBlocks.RASPBERRY_PIE || this == ModBlocks.RASPBERRY_BUSH;
    }

    public static class Entities {
        public static void register(IEventBus modEventBus) {
            REGISTER.register(modEventBus);
        }

        public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES,
            CozyLiving.MODID
        );

        public static final RegistryObject<BlockEntityType<CustomSignBE>> SIGN = REGISTER.register(
            "custom_sign", () -> BlockEntityType.Builder.of(
                CustomSignBE::new,
                ModBlocks.COCONUT_SIGN.block(),
                ModBlocks.COCONUT_WALL_SIGN.block()
            ).build(null) // TODO: this is annotated as NotNull?
        );

        public static final RegistryObject<BlockEntityType<CustomHangingSignBE>> HANGING_SIGN = REGISTER.register(
            "custom_hanging_sign", () -> BlockEntityType.Builder.of(
                CustomHangingSignBE::new,
                ModBlocks.COCONUT_HANGING_SIGN.block(),
                ModBlocks.COCONUT_WALL_HANGING_SIGN.block()
            ).build(null) // TODO: this is annotated as NotNull?
        );
    }
}

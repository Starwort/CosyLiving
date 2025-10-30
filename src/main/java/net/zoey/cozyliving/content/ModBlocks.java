package net.zoey.cozyliving.content;

import net.minecraft.core.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;
import net.zoey.cozyliving.content.block.*;
import net.zoey.cozyliving.content.block.entity.*;

import javax.annotation.*;
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

    RASPBERRY_BUSH("raspberry_bush", RaspberryBushBlock::new),
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
        @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory,
        @Nullable Item.Properties properties
    ) {
        myValue = CozyLiving.BLOCKS.register(name, supplier);
        if (itemFactory != null) {
            myItem = CozyLiving.ITEMS.register(
                name, () -> itemFactory.apply(
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
        @Nullable BiFunction<Block, Item.Properties, BlockItem> itemFactory
    ) {
        this(name, supplier, itemFactory, null);
    }

    ModBlocks(String name, Supplier<Block> supplier) {
        this(name, supplier, BlockItem::new);
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

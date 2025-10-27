package net.zoey.cozyliving.content;

import net.minecraft.core.*;
import net.minecraft.core.registries.*;
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

public class ModBlocks {
    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info("Found {} blocks", REGISTER.getEntries().size());
        REGISTER.register(modEventBus);

        Entities.register(modEventBus);
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
                ModBlocks.COCONUT_SIGN.get(),
                ModBlocks.COCONUT_WALL_SIGN.get()
            ).build(null) // TODO: this is annotated as NotNull?
        );

        public static final RegistryObject<BlockEntityType<CustomHangingSignBE>> HANGING_SIGN = REGISTER.register(
            "custom_hanging_sign", () -> BlockEntityType.Builder.of(
                CustomHangingSignBE::new,
                ModBlocks.COCONUT_HANGING_SIGN.get(),
                ModBlocks.COCONUT_WALL_HANGING_SIGN.get()
            ).build(null) // TODO: this is annotated as NotNull?
        );
    }

    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS,
        CozyLiving.MODID
    );

    public static final RegistryObject<Block> COCONUT_LOG = REGISTER.register(
        "coconut_log",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .copy(Blocks.OAK_LOG)
            .strength(3f))
    );

    public static final RegistryObject<Block> COCONUT_WOOD = REGISTER.register(
        "coconut_wood",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .copy(Blocks.OAK_WOOD)
            .strength(3f))
    );

    public static final RegistryObject<Block> STRIPPED_COCONUT_LOG = REGISTER.register(
        "stripped_coconut_log",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .copy(Blocks.STRIPPED_OAK_LOG)
            .strength(3f))
    );

    public static final RegistryObject<Block> STRIPPED_COCONUT_WOOD = REGISTER.register(
        "stripped_coconut_wood",
        () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties
            .copy(Blocks.STRIPPED_OAK_WOOD)
            .strength(3f))
    );

    public static final RegistryObject<Block> COCONUT_PLANKS = REGISTER.register(
        "coconut_planks",
        () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
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
    );

    public static final RegistryObject<Block> COCONUT_LEAVES = REGISTER.register(
        "coconut_leaves",
        () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {
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
    );

    public static final RegistryObject<Block> COCONUT_SIGN = REGISTER.register(
        "coconut_sign",
        () -> new CustomStandingSignBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_SIGN),
            ModWoodTypes.COCONUT
        )
    );
    public static final RegistryObject<Block> COCONUT_WALL_SIGN = REGISTER.register(
        "coconut_wall_sign",
        () -> new CustomWallSignBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN),
            ModWoodTypes.COCONUT
        )
    );

    public static final RegistryObject<Block> COCONUT_HANGING_SIGN = REGISTER.register(
        "coconut_hanging_sign",
        () -> new CustomHangingSignBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN),
            ModWoodTypes.COCONUT
        )
    );
    public static final RegistryObject<Block> COCONUT_WALL_HANGING_SIGN = REGISTER.register(
        "coconut_wall_hanging_sign",
        () -> new CustomWallHangingSignBlock(
            BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN),
            ModWoodTypes.COCONUT
        )
    );
}

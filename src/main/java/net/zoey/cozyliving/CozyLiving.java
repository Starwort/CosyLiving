package net.zoey.cozyliving;

import com.mojang.logging.LogUtils;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.entity.*;
import net.zoey.cozyliving.content.entity.client.*;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CozyLiving.MODID)
public class CozyLiving {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "cozyliving";
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
        MODID
    );
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
        MODID
    );
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
        MODID
    );
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
        MODID
    );
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public CozyLiving(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        LOGGER.info("Cosy Living starting up.");

        ModSounds.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);


        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            var pot = ((FlowerPotBlock) Blocks.FLOWER_POT);
            pot.addPlant(ModBlocks.COTTON_SHRUB.id(), ModBlocks.POTTED_COTTON::block);
            pot.addPlant(
                ModBlocks.COCONUT_SAPLING.id(),
                ModBlocks.POTTED_COCONUT_SAPLING::block
            );
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(
        modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT
    )
    public static class ClientModEvents {
        @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(
                ModModelLayers.COCONUT_BOAT_LAYER,
                BoatModel::createBodyModel
            );
            event.registerLayerDefinition(
                ModModelLayers.COCONUT_CHEST_BOAT_LAYER,
                ChestBoatModel::createBodyModel
            );
        }

        @SubscribeEvent
        public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
            event.register(
                (state, level, pos, tintIndex) -> {
                    if (level == null || pos == null) {
                        return GrassColor.get(0.5, 1.0);
                    }
                    return BiomeColors.getAverageGrassColor(level, pos);
                },
                ModBlocks.COTTON_CROP.block(),
                ModBlocks.COTTON_SHRUB.block(),
                ModBlocks.POTTED_COTTON.block(),
                ModBlocks.RASPBERRY_BUSH.block()
            );
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Setting up client...");
            EntityRenderers.register(
                ModEntities.CUSTOM_BOAT.get(),
                context -> new CustomBoatRenderer(context, false)
            );
            EntityRenderers.register(
                ModEntities.CUSTOM_CHEST_BOAT.get(),
                context -> new CustomBoatRenderer(context, true)
            );


            Sheets.addWoodType(ModWoodTypes.COCONUT);
        }

        @SubscribeEvent
        public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(
                ModBlocks.Entities.SIGN.get(),
                SignRenderer::new
            );
            event.registerBlockEntityRenderer(
                ModBlocks.Entities.HANGING_SIGN.get(),
                HangingSignRenderer::new
            );
        }
    }
}

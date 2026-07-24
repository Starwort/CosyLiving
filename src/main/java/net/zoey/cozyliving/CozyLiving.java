package net.zoey.cozyliving;

import com.mojang.logging.*;
import net.minecraft.*;
import net.minecraft.advancements.*;
import net.minecraft.client.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.core.registries.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.*;
import net.minecraft.sounds.*;
import net.minecraft.world.effect.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.api.distmarker.*;
import net.neoforged.bus.api.*;
import net.neoforged.fml.common.*;
import net.neoforged.fml.config.*;
import net.neoforged.fml.*;
import net.neoforged.fml.event.lifecycle.*;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.gui.*;
import net.neoforged.neoforge.common.*;
import net.neoforged.neoforge.event.entity.player.*;
import net.neoforged.neoforge.event.level.*;
import net.neoforged.neoforge.event.server.*;
import net.neoforged.neoforge.registries.*;
import net.zoey.cozyliving.content.*;
import net.zoey.cozyliving.content.common.ArmourMaterials;
import net.zoey.cozyliving.content.entity.*;
import net.zoey.cozyliving.content.entity.client.*;
import net.zoey.cozyliving.level.gen.*;
import net.zoey.cozyliving.util.*;
import org.slf4j.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CozyLiving.MODID)
public class CozyLiving {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "cozyliving";
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MODID);
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MODID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, MODID);
    public static final DeferredRegister<ArmorMaterial> ARMOUR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, MODID);
    public static final DeferredRegister<ResourceLocation> STATISTICS = DeferredRegister.create(Registries.CUSTOM_STAT, MODID);
    public static final DeferredRegister<CriterionTrigger<?>> TRIGGER_TYPES = DeferredRegister.create(Registries.TRIGGER_TYPE, MODID);

    public static final boolean DEBUG_MODE = false;
    public static final boolean ORE_GEN = false;

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation loc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public CozyLiving(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        LOGGER.info("Cosy Living starting up.");

        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModItems.register(modEventBus);
        ArmourMaterials.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModStatistics.register(modEventBus);
        ModCustomFeatures.RegisterFeatures(modEventBus);
        ModLootTableModifiers.register(modEventBus);
        ModAdvancementTriggers.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
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

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }

    @EventBusSubscriber(
        modid = MODID, value = Dist.CLIENT
    )
    public static class ClientNeoEvents {
        @SubscribeEvent
        public static void onTooltip(ItemTooltipEvent event) {
            var stack = event.getItemStack();
            var tooltip = event.getToolTip();

            if (stack.getItem() == ModItems.FLOWER_CROWN.item()) {
                // locate 'When worn on head:'
                for (var i = 0; i < tooltip.size(); i++) {
                    var line = tooltip.get(i);
                    if (line.contains(Component.translatable("item.modifiers.head"))) {
                        tooltip.add(
                            i + 1,
                            Component
                                .translatable("tooltip.cozyliving.flower_crown")
                                .withStyle(ChatFormatting.BLUE)
                        );
                        break;
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onOverlay(RenderGuiLayerEvent.Pre event) {
            // Skip Nether Portal overlay rendering if we have Third Eye Open
            // This is pretty much the same thing vanilla does if the player
            // has CONFUSION (Nausea) [n.b. both skip portal rendering even
            // if the player is standing in a portal]
            if (event.getName().equals(VanillaGuiLayers.CAMERA_OVERLAYS)) {
                var player = Minecraft.getInstance().player;
                if (player != null
                    && player.hasEffect(ModEffects.THIRD_EYE_OPEN.holder())) {
                    event.setCanceled(true);
                }
            }
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(
        modid = MODID, value = Dist.CLIENT
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
                ModBlocks.RASPBERRY_BUSH.block(),
                ModBlocks.PINK_PAMPAS_GRASS.block(),
                ModBlocks.WHITE_PAMPAS_GRASS.block()
            );
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Setting up client...");
            EntityRenderers.register(
                ModEntities.CUSTOM_BOAT.entity(),
                context -> new CustomBoatRenderer(context, false)
            );
            EntityRenderers.register(
                ModEntities.CUSTOM_CHEST_BOAT.entity(),
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

    @EventBusSubscriber(modid = MODID)
    public static class CommonForgeEvents {
        @SubscribeEvent
        public static void onNotePlay(NoteBlockEvent.Play event) {
            var level = event.getLevel();
            var pos = event.getPos();

            if (level.getBlockState(pos.above()).is(ModBlocks.COCONUT.block())) {
                event.setInstrument(NoteBlockInstrument.CUSTOM_HEAD);
                level.playSound(
                    null,
                    pos,
                    ModSounds.COCONUT_BONK.sound(),
                    SoundSource.RECORDS,
                    1.0f,
                    (float) Math.pow(2.0, (event.getVanillaNoteId() - 12.0) / 12)
                );
                event.setCanceled(true);
            }
        }
    }
}

package net.zoey.cozyliving.content;

import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.registries.*;
import net.zoey.cozyliving.*;

import java.util.function.*;

public enum ModCreativeTabs {
    MAIN(
        "main",
        () -> CreativeModeTab
            .builder()
            .title(Component.translatable("item_group." + CozyLiving.MODID + ".main"))
            .icon(() -> new ItemStack(ModItems.RASPBERRY_RHODOLITE.item()))
            .displayItems((displayContext, entries) -> {
                for (var item : ModItems.values()) {
                    entries.accept(item.item());
                }
                for (var block : ModBlocks.values()) {
                    if (!block.isFoodBlock() && block.asItem() != Items.AIR) {
                        entries.accept(block.asItem());
                    }
                }
            })
            .build()
    ),

    FOOD(
        "food",
        () -> CreativeModeTab
            .builder()
            .title(Component.translatable("item_group." + CozyLiving.MODID + ".food"))
            .icon(() -> new ItemStack(ModBlocks.RASPBERRY_BUSH.asItem()))
            .displayItems((displayContext, entries) -> {
                for (var item : ModItems.Food.values()) {
                    entries.accept(item.item());
                }
                for (var block : ModBlocks.values()) {
                    if (block.isFoodBlock() && block.asItem() != Items.AIR) {
                        entries.accept(block.asItem());
                    }
                }
            })
            .build()
    );

    private final RegistryObject<CreativeModeTab> myValue;

    ModCreativeTabs(String name, Supplier<CreativeModeTab> supplier) {
        myValue = CozyLiving.CREATIVE_TABS.register(name, supplier);
    }

    public RegistryObject<CreativeModeTab> registryObject() {
        return myValue;
    }

    public CreativeModeTab creativeModeTab() {
        return myValue.get();
    }

    public static void register(IEventBus modEventBus) {
        CozyLiving.LOGGER.info(
            "Found {} creative tabs",
            CozyLiving.CREATIVE_TABS.getEntries().size()
        );
        CozyLiving.CREATIVE_TABS.register(modEventBus);
    }
}

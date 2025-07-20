package net.zoey.cozyliving.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.item.ModItems;

public class ModBoats {
    public static final Identifier COCONUT_BOAT_ID = Identifier.of(CozyLiving.MOD_ID, "coconut_boat");
    public static final Identifier COCONUT_CHEST_BOAT_ID = Identifier.of(CozyLiving.MOD_ID, "coconut_chest_boat");

    public static final RegistryKey<TerraformBoatType> COCONUT_BOAT_KEY = TerraformBoatTypeRegistry.createKey(COCONUT_BOAT_ID);

    public static void registerBoats(){
        TerraformBoatType coconutBoat = new TerraformBoatType.Builder()
                .item(ModItems.COCONUT_BOAT)
                .chestItem(ModItems.COCONUT_CHEST_BOAT)
                .planks(ModBlocks.COCONUT_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, COCONUT_BOAT_KEY, coconutBoat);
    }
}

package net.zoey.cozyliving.statistic;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.zoey.cozyliving.CozyLiving;

public class ModStatistics {
    public static final Identifier LAND_ON_COTTON_BALE = Identifier.of(CozyLiving.MOD_ID, "land_on_cotton_bale");

    public static void registerModStatistics(){
        Registry.register(Registries.CUSTOM_STAT, "land_on_cotton_bale", LAND_ON_COTTON_BALE);
        Stats.CUSTOM.getOrCreateStat(LAND_ON_COTTON_BALE, StatFormatter.DEFAULT);
    }
}

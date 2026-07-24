package net.zoey.cozyliving;

import net.neoforged.neoforge.common.*;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.DoubleValue DEBILITATING_NAUSEA_FACTOR = BUILDER
        .comment("How debilitating to make Third Eye Open")
        .defineInRange("debilitatingNauseaFactor", 2, 1., Double.POSITIVE_INFINITY);
    static final ModConfigSpec SPEC = BUILDER.build();

    public static double debilitatingNauseaFactor() {
        return DEBILITATING_NAUSEA_FACTOR.getAsDouble();
    }
}

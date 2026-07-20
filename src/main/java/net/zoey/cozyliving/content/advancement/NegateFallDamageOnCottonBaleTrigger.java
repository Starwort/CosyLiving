package net.zoey.cozyliving.content.advancement;

import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.advancements.critereon.*;
import net.minecraft.server.level.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class NegateFallDamageOnCottonBaleTrigger extends SimpleCriterionTrigger<NegateFallDamageOnCottonBaleTrigger.Instance> {
    public static final NegateFallDamageOnCottonBaleTrigger INSTANCE = new NegateFallDamageOnCottonBaleTrigger();

    @Override
    public @NotNull Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, instance -> true);
    }

    public static Instance instance() {
        return new Instance(Optional.empty());
    }

    public record Instance(Optional<ContextAwarePredicate> player) implements SimpleInstance {
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player))
                .apply(instance, Instance::new)
        );
    }
}

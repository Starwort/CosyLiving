package net.zoey.cozyliving.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zoey.cozyliving.util.ModDamageTypes;

public class TestBlock extends Block {
    public TestBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity) {
            DamageSource damageSource = new DamageSource(
                    world.getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .entryOf(ModDamageTypes.COCONUT_BONK_DAMAGE_TYPE));
            entity.damage(damageSource, 5.0f);
        }
    }

}

package net.zoey.cozyliving.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.dedicated.Settings;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.zoey.cozyliving.content.ModDamageTypes;

public class TestBlock extends Block {

    public TestBlock(Properties pProperties) {
        super(pProperties);
    }

    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof LivingEntity) {
            pEntity.hurt(ModDamageTypes.COCONUT_BONK.asSource(pLevel), 5.0f);
            super.stepOn(pLevel, pPos, pState, pEntity);
        }

    }
}
package net.zoey.cozyliving.block.custom;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.zoey.cozyliving.datagen.ModAdvancementProvider;
import net.zoey.cozyliving.statistic.ModStatistics;

public class CottonBaleBlock extends PillarBlock {
    public CottonBaleBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, 0F, world.getDamageSources().fall());
        if (fallDistance >= 4){
            world.playSound(null, pos, SoundEvents.BLOCK_WOOL_FALL, SoundCategory.BLOCKS);
            if (entity.isPlayer()){
                PlayerEntity playerEntity = (PlayerEntity) entity;
                playerEntity.incrementStat(ModStatistics.LAND_ON_COTTON_BALE);

            }
        }

    }

}

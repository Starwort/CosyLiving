package net.zoey.cozyliving.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.zoey.cozyliving.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

public class LadyBeetleEntity extends AnimalEntity {
    public LadyBeetleEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals(){
        super.initGoals();
        //LOWER PRIORITY = SOONER EXECUTED
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new FollowParentGoal(this, 0.5f));
        this.goalSelector.add(2, new LookAtEntityGoal(this, PlayerEntity.class, 5f));
        this.goalSelector.add(3, new LookAtEntityGoal(this, LadyBeetleEntity.class, 4f));
        this.goalSelector.add(5, new LookAroundGoal(this));
        this.goalSelector.add(4, new WanderAroundGoal(this, 0.5f));

    }


    public static DefaultAttributeContainer.Builder createLadyBeetleAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 1)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2)
                .add(EntityAttributes.GENERIC_LUCK, 5);



    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.LADYBEETLE.create(world);
    }
}

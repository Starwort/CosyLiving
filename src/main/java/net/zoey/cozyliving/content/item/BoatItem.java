package net.zoey.cozyliving.content.item;

import net.minecraft.stats.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.gameevent.*;
import net.minecraft.world.phys.*;
import net.zoey.cozyliving.content.entity.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class BoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(
        Entity::isPickable);
    private final CustomBoat.Type type;
    private final boolean hasChest;

    public BoatItem(
        boolean has_chest,
        CustomBoat.Type type,
        Item.Properties properties
    ) {
        super(properties);
        this.hasChest = has_chest;
        this.type = type;
    }

    public @NotNull InteractionResultHolder<ItemStack> use(
        @NotNull Level level,
        Player player,
        @NotNull InteractionHand hand
    ) {
        ItemStack itemstack = player.getItemInHand(hand);
        HitResult hitresult = getPlayerPOVHitResult(
            level,
            player,
            ClipContext.Fluid.ANY
        );
        if (hitresult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            Vec3 vec3 = player.getViewVector(1.0F);
            double d0 = 5.0D;
            List<Entity> list = level.getEntities(
                player,
                player.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D),
                ENTITY_PREDICATE
            );
            if (!list.isEmpty()) {
                Vec3 vec31 = player.getEyePosition();

                for (Entity entity : list) {
                    AABB aabb = entity
                        .getBoundingBox()
                        .inflate((double) entity.getPickRadius());
                    if (aabb.contains(vec31)) {
                        return InteractionResultHolder.pass(itemstack);
                    }
                }
            }

            if (hitresult.getType() == HitResult.Type.BLOCK) {
                Boat boat = this.getBoat(level, hitresult);
                if (boat instanceof CustomBoat customBoat) {
                    customBoat.setVariant(type);
                } else if (boat instanceof CustomChestBoat chestBoat) {
                    chestBoat.setVariant(type);
                }
                boat.setYRot(player.getYRot());
                if (!level.noCollision(boat, boat.getBoundingBox())) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    if (!level.isClientSide) {
                        level.addFreshEntity(boat);
                        level.gameEvent(
                            player,
                            GameEvent.ENTITY_PLACE,
                            hitresult.getLocation()
                        );
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(
                        itemstack,
                        level.isClientSide()
                    );
                }
            } else {
                return InteractionResultHolder.pass(itemstack);
            }
        }
    }

    private Boat getBoat(Level level, HitResult hitResult) {
        return (
            this.hasChest ? new CustomChestBoat(
                level,
                hitResult.getLocation().x,
                hitResult.getLocation().y,
                hitResult.getLocation().z
            ) : new CustomBoat(
                level,
                hitResult.getLocation().x,
                hitResult.getLocation().y,
                hitResult.getLocation().z
            )
        );
    }
}

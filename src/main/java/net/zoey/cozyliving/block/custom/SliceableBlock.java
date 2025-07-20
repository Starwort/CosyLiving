package net.zoey.cozyliving.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.List;



public class SliceableBlock extends Block {
    public static final IntProperty BITES;
    public static final DirectionProperty FACING;
    public FoodComponent foodComponent;
    public final Item sliceItem;

    //public static List<FoodComponent.StatusEffectEntry> statusEffectEntryList;
    //public static List<StatusEffectInstance> statusEffectInstanceList = new ArrayList<>() {};

    public SliceableBlock(@NotNull Item sliceItem, @NotNull FoodComponent newFoodComponent, Settings settings) {
        super(settings);
        CozyLiving.LOGGER.info("Constructor start,  " + sliceItem);

        this.sliceItem = sliceItem;
        foodComponent = newFoodComponent;
        CozyLiving.LOGGER.info("Constructor end,  " + this.sliceItem);
    }

    private static final VoxelShape slice0 = Block.createCuboidShape(8, 0, 2, 14, 4, 8);
    private static final VoxelShape slice1 = Block.createCuboidShape(2, 0, 2, 8, 4, 8);
    private static final  VoxelShape slice2 = Block.createCuboidShape(2, 0, 8, 8, 4, 14);
    private static final VoxelShape slice3 = Block.createCuboidShape(8, 0, 8, 14, 4, 14);

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(BITES)){
            case 1 ->{
                switch (state.get(FACING)){
                    case EAST -> {return VoxelShapes.union(slice1,slice2,slice3);}
                    case SOUTH  -> {return VoxelShapes.union(slice0,slice1,slice2);}
                    case WEST -> {return VoxelShapes.union(slice0,slice1,slice3);}
                    case NORTH  -> {return VoxelShapes.union(slice0,slice2,slice3);}
                }
            }
            case 2 ->{
                switch (state.get(FACING)){
                    case EAST  -> {return VoxelShapes.union(slice1,slice2);}
                    case SOUTH -> {return VoxelShapes.union(slice0,slice1);}
                    case WEST  -> {return VoxelShapes.union(slice0,slice3);}
                    case NORTH -> {return VoxelShapes.union(slice2,slice3);}
                }
            }
            case 3 ->{
                switch (state.get(FACING)) {
                    case EAST -> {return slice1;}
                    case SOUTH ->  {return slice0;}
                    case WEST -> {return slice3;}
                    case NORTH ->  {return slice2;}
                }
            }
        }
        return Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 4.0, 14.0);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BITES);
        builder.add(FACING);
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(Hand.MAIN_HAND);

        if (itemStack.getItem() instanceof SwordItem) { //If the player is holding a sword:

            PlayerInventory inventory = player.getInventory();
            CozyLiving.LOGGER.info("SLICE ITEM IS " + this.sliceItem.toString());
            ItemStack sliceItemStack = new ItemStack(sliceItem);

            inventory.offerOrDrop(sliceItemStack);

            world.playSound(null, pos, SoundEvents.BLOCK_WOOL_BREAK,
                    SoundCategory.PLAYERS, 1f, 1f);
            if (state.get(BITES) < 3){
                world.setBlockState(pos, state.with(BITES, state.get(BITES) + 1), 3);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }
            return ActionResult.SUCCESS;
        }
        if (world.isClient || !(itemStack.getItem() instanceof DebugStickItem)) {

            if (tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }
        }
        return tryEat(world, pos, state, player);
    }

    protected ActionResult tryEat(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.eatFood(world, this.sliceItem.getDefaultStack());
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (state.get(BITES) < 3){
                world.setBlockState(pos, state.with(BITES, state.get(BITES) + 1), 3);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }
            return ActionResult.SUCCESS;
        }
    }

    static {
        BITES = IntProperty.of("bites", 0, 3);
        FACING = Properties.HORIZONTAL_FACING;
    }
}

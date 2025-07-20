package net.zoey.cozyliving.mixin;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.zoey.cozyliving.CozyLiving;
import net.zoey.cozyliving.block.ModBlocks;
import net.zoey.cozyliving.sound.ModSounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.block.NoteBlock.NOTE;
import static net.minecraft.block.NoteBlock.getNotePitch;

@Mixin(NoteBlock.class)
public class CoconutNoteBlockMixin extends Block {


    public CoconutNoteBlockMixin(Settings settings) {
        super(settings);
    }

    //Mkaes it emit a block event when a coconut is above
    @Inject(at = @At("TAIL"), method = "playNote")
    private void coconutPlayNote(Entity entity, BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        //CozyLiving.LOGGER.info("coconutPlayNote started");
        if(world.getBlockState(pos.up()).isOf(ModBlocks.COCONUT)){
            world.addSyncedBlockEvent(pos, this, 0, 0);
            world.emitGameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, pos);
            //CozyLiving.LOGGER.info("coconutPlayNote detected coconut");
        }
        //CozyLiving.LOGGER.info("coconutPlayNote ran");
    }


    //Hopefully makes noteblocks play coconut sounds lol
    @Inject(at = @At("HEAD"), method = "onSyncedBlockEvent", cancellable = true)
    public void coconutOnSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data, CallbackInfoReturnable<Boolean> cir) {
        //CozyLiving.LOGGER.info("coconutOnSyncedBlockEvent started");
        if (world.getBlockState(pos.up()).isOf(ModBlocks.COCONUT)){
            int i = (Integer)state.get(NOTE);
            float f = getNotePitch(i);
            world.addParticle(ParticleTypes.NOTE, (double)pos.getX() + 0.5, (double)pos.getY() + 1.7, (double)pos.getZ() + 0.5, (double)i / 24.0, 0.0, 0.0);
            RegistryEntry<SoundEvent> registryEntry = RegistryEntry.of(SoundEvent.of(ModSounds.COCONUT_BONK.getId()));
            world.playSound((PlayerEntity)null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, registryEntry, SoundCategory.RECORDS, 3.0F, f, world.random.nextLong());
            //CozyLiving.LOGGER.info("coconutOnSyncedBlockEvent ran successfully");
            cir.setReturnValue(true);

        }

    }


}

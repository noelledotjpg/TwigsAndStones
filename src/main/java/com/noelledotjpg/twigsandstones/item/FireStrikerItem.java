package com.noelledotjpg.twigsandstones.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import com.noelledotjpg.twigsandstones.sound.ModSounds;

public class FireStrikerItem extends Item {
    public FireStrikerItem(Item.Properties properties) { super(properties); }

public InteractionResult useOn(UseOnContext context) {
    Player player = context.getPlayer();
    Level level = context.getLevel();
    BlockPos blockPos = context.getClickedPos();
    BlockState blockState = level.getBlockState(blockPos);
    BlockState blockState1 = blockState.getToolModifiedState(context, ItemAbilities.FIRESTARTER_LIGHT, false);

    if (blockState1 == null) {
        BlockPos blockPos1 = blockPos.relative(context.getClickedFace());
        if (BaseFireBlock.canBePlacedAt(level, blockPos1, context.getHorizontalDirection())) {
            level.playSound(player, blockPos1, ModSounds.FIRE_STRIKER_USE.get(), SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            blockState1 = BaseFireBlock.getState(level, blockPos1);
            level.setBlock(blockPos1, blockState1, 11);
            level.gameEvent(player, GameEvent.BLOCK_PLACE, blockPos);

            ItemStack itemStack = context.getItemInHand();
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, blockPos1, itemStack);
                itemStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.FAIL;
        }
        } else {
            level.playSound(player, blockPos, ModSounds.FIRE_STRIKER_USE.get(), SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
            level.setBlock(blockPos, blockState1, 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
            if (player != null) {
                context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }

            return InteractionResult.sidedSuccess(level.isClientSide());
        }
    }

    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_FLINT_ACTIONS.contains(itemAbility);
    }
}

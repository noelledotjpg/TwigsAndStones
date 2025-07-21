package com.noelledotjpg.twigsandstones.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
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


public class FireStrikerItem extends Item {
    public FireStrikerItem(Item.Properties properties){
        super(properties);
    }
}

public InteractionResult useOn(UseOnContext context) {
    Player player = context.getPlayer();
    Level level = context.getLevel();
    BlockPos blockPos = context.getClickedPos();
    BlockState blockState = level.getBlockState(blockPos);
    BlockState blockState1 = blockState.getToolModifiedState(context, ItemAbilities.FIRESTARTER_LIGHT, false);
    if (blockState1 == null) {
        BlockPos blockPos1 = blockPos.relative(context.getClickedFace());
        if (BaseFireBlock.canBePlacedAt(level, blockPos1, context.getHorizontalDirection())) {
            level.playSound(player, blockPos1, SoundEvents.);
        }
    }
}

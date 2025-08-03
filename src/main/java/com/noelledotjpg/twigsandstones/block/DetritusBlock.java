package com.noelledotjpg.twigsandstones.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DetritusBlock extends CarpetBlock {
    public DetritusBlock(Properties properties) {
        super(properties);
    }


    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockpos = pos.below();
        BlockState belowBlockState = level.getBlockState(blockpos);
        return belowBlockState.isSolid();
    }
}

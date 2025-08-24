package com.noelledotjpg.twigsandstones.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class RedstoneDisplayBlock extends Block {

    public static final IntegerProperty POWER = IntegerProperty.create("power", 0, 15);

    public RedstoneDisplayBlock(BlockBehaviour.Properties properties) {
        super(properties.lightLevel((state) -> state.getValue(POWER) > 0 ? 6 : 0));
        this.registerDefaultState(this.defaultBlockState().setValue(POWER, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        int power = getExternalPower(context.getLevel(), context.getClickedPos());
        return this.defaultBlockState().setValue(POWER, power);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            int currentPower = state.getValue(POWER);
            int newPower = getExternalPower(level, pos);

            if (currentPower != newPower) {
                level.setBlock(pos, state.setValue(POWER, newPower), 2);
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int newPower = getExternalPower(level, pos);
        if (state.getValue(POWER) != newPower) {
            level.setBlock(pos, state.setValue(POWER, newPower), 2);
        }
    }

    private int getExternalPower(Level level, BlockPos pos) {
        int maxPower = 0;
        for (Direction dir : Direction.values()) {
            BlockPos neighbor = pos.relative(dir);
            BlockState neighborState = level.getBlockState(neighbor);

            // Ignore other display blocks
            if (neighborState.getBlock() instanceof RedstoneDisplayBlock) continue;

            maxPower = Math.max(maxPower, level.getSignal(neighbor, dir));
        }
        return maxPower;
    }

    @Override
    public boolean isSignalSource(BlockState state) {
        return false; // does not emit redstone
    }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return 0;
    }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) {
        return 0;
    }
}

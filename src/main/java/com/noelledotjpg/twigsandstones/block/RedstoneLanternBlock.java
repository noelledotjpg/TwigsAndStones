package com.noelledotjpg.twigsandstones.block;

import com.mojang.serialization.MapCodec;
import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class RedstoneLanternBlock extends Block {
    public static final MapCodec<RedstoneLanternBlock> CODEC = simpleCodec(RedstoneLanternBlock::new);
    public static final BooleanProperty LIT = BooleanProperty.create("lit");

    public RedstoneLanternBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(LIT, hasExternalPower(context.getLevel(), context.getClickedPos()));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!level.isClientSide) {
            boolean lit = state.getValue(LIT);
            boolean powered = hasExternalPower(level, pos);

            if (lit != powered) {
                if (lit) {
                    level.scheduleTick(pos, this, 4);
                } else {
                    level.setBlock(pos, state.setValue(LIT, true), 2);
                }
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT) && !hasExternalPower(level, pos)) {
            level.setBlock(pos, state.setValue(LIT, false), 2);
        }
    }

    private boolean hasExternalPower(Level level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockPos neighbor = pos.relative(dir);
            BlockState neighborState = level.getBlockState(neighbor);

            // Ignore other redstone lanterns
            if (neighborState.getBlock() instanceof RedstoneLanternBlock) continue;

            if (level.hasSignal(neighbor, dir)) return true;
        }
        return false;
    }

    // Prevent from emitting redstone
    @Override
    public boolean isSignalSource(BlockState state) { return false; }

    @Override
    public int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) { return 0; }

    @Override
    public int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction dir) { return 0; }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}


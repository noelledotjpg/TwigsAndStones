package com.noelledotjpg.twigsandstones.item;

import com.noelledotjpg.twigsandstones.TwigsStones;
import com.noelledotjpg.twigsandstones.block.DetritusBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TwigsStones.MODID);

    public static final DeferredBlock<Block> TWIG = BLOCKS.register("twig",
            () -> new DetritusBlock(BlockBehaviour.Properties.of().noCollission().instabreak()));

    public static final DeferredBlock<Block> PEBBLE = BLOCKS.register("pebble",
            () -> new DetritusBlock(BlockBehaviour.Properties.of().noCollission().instabreak()));

}

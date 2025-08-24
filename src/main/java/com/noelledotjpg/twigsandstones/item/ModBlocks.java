package com.noelledotjpg.twigsandstones.item;

import com.noelledotjpg.twigsandstones.TwigsStones;
import com.noelledotjpg.twigsandstones.block.DetritusBlock;
import com.noelledotjpg.twigsandstones.block.RedstoneDisplayBlock;
import com.noelledotjpg.twigsandstones.block.RedstoneLanternBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(TwigsStones.MODID);

    public static final DeferredBlock<Block> TWIG = BLOCKS.register("twig",
            () -> new DetritusBlock(BlockBehaviour.Properties.of().noCollission().instabreak()));

    public static final DeferredBlock<Block> PEBBLE = BLOCKS.register("pebble",
            () -> new DetritusBlock(BlockBehaviour.Properties.of().noCollission().instabreak()));

    public static final DeferredBlock<Block> REDSTONE_LANTERN = BLOCKS.register("redstone_lantern",
            () -> new RedstoneLanternBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3F)
                    .lightLevel(state -> state.getValue(RedstoneLanternBlock.LIT) ? 15 : 0)
                    .sound(SoundType.GLASS)
            ));

    public static final DeferredBlock<Block> REDSTONE_DISPLAY = BLOCKS.register("redstone_display",
            () -> new RedstoneDisplayBlock(BlockBehaviour.Properties
                    .of()
                    .strength(0.3F)
                    .sound(SoundType.GLASS)
            ));
}

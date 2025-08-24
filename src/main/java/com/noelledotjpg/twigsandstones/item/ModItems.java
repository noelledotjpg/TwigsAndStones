package com.noelledotjpg.twigsandstones.item;

import com.noelledotjpg.twigsandstones.TwigsStones;
import com.noelledotjpg.twigsandstones.sound.ModSounds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TwigsStones.MODID);

    public static final DeferredItem<Item> FIRE_STRIKER = ITEMS.register("fire_striker",
            () -> new FireStrikerItem(new Item.Properties().durability(10).stacksTo(1)));

    public static final DeferredItem<Item> TWIG = ITEMS.register("twig",
            () -> new BlockItem(ModBlocks.TWIG.get(), new Item.Properties()));

    public static final DeferredItem<Item> PEBBLE = ITEMS.register("pebble",
            () -> new PebbleItem(ModBlocks.PEBBLE.get(), new Item.Properties()));


    public static final DeferredItem<Item> RAINY_AMBIENCE_TAPE = ITEMS.register("rainy_ambience_tape",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.RAINY_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> SHORE_AMBIENCE_TAPE = ITEMS.register("shore_ambience_tape",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.SHORE_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> DROPLETS_AMBIENCE_TAPE = ITEMS.register("droplets_ambience_tape",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.DROPLETS_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> TICKTOCK_AMBIENCE_TAPE = ITEMS.register("tick-tock_ambience_tape",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.TICKTOCK_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> BREEZE_AMBIENCE_TAPE = ITEMS.register("breeze_ambience_tape",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BREEZE_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> BLAZE_AMBIENCE_TAPE = ITEMS.register("blaze_ambience_tape",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.BLAZE_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> CRICKETS_AMBIENCE_TAPE = ITEMS.register("crickets_ambience_tape",
            () -> new Item(new Item.Properties().jukeboxPlayable(ModSounds.CRICKETS_KEY).stacksTo(1).rarity(Rarity.UNCOMMON)));



    public static final DeferredItem<Item> REDSTONE_LANTERN = ITEMS.register("redstone_lantern",
            () -> new BlockItem(ModBlocks.REDSTONE_LANTERN.get(),
                    new BlockItem.Properties().stacksTo(64)));

    public static final DeferredItem<Item> REDSTONE_DISPLAY = ITEMS.register("redstone_display",
            () -> new BlockItem(ModBlocks.REDSTONE_DISPLAY.get(),
                    new BlockItem.Properties().stacksTo(64)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}

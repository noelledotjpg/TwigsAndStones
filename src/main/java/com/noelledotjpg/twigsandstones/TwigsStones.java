package com.noelledotjpg.twigsandstones;

import com.noelledotjpg.twigsandstones.entity.ModEntityTypes;
import com.noelledotjpg.twigsandstones.item.ModBlocks;
import com.noelledotjpg.twigsandstones.item.ModItems;
import com.noelledotjpg.twigsandstones.sound.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TwigsStones.MODID)
public class TwigsStones {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "twigsandstones";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public TwigsStones(IEventBus modEventBus, ModContainer modContainer) {

        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModBlocks.BLOCKS.register(modEventBus);

        ModItems.register(modEventBus);

        ModSounds.SOUND_EVENTS.register(modEventBus);

        ModEntityTypes.ENTITY_TYPES.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.FIRE_STRIKER);

            event.accept(ModItems.RAINY_AMBIENCE_TAPE);
            event.accept(ModItems.DROPLETS_AMBIENCE_TAPE);
            event.accept(ModItems.SHORE_AMBIENCE_TAPE);
            event.accept(ModItems.TICKTOCK_AMBIENCE_TAPE);
            event.accept(ModItems.BREEZE_AMBIENCE_TAPE);
            event.accept(ModItems.BLAZE_AMBIENCE_TAPE);
            event.accept(ModItems.CRICKETS_AMBIENCE_TAPE);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.TWIG);
            event.accept(ModItems.PEBBLE);
        }
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {

            event.accept(ModItems.REDSTONE_LANTERN);
            event.accept(ModItems.REDSTONE_DISPLAY);
        }
        if (event.getTabKey() == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.accept(ModItems.REDSTONE_LANTERN);
            event.accept(ModItems.REDSTONE_DISPLAY);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }
}

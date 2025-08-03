package com.noelledotjpg.twigsandstones.sound;

import com.noelledotjpg.twigsandstones.TwigsStones;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, TwigsStones.MODID);

    public static final Supplier<SoundEvent> FIRE_STRIKER_USE = registerSoundEvent("fire_striker_use");

    public static final Supplier<SoundEvent> PEBBLE_THROW = registerSoundEvent("pebble_throw");


    public static final Supplier<SoundEvent> RAINY = registerSoundEvent("rainy");
    public static final ResourceKey<JukeboxSong> RAINY_KEY = createSong("rainy");

    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(TwigsStones.MODID, name));
    }

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(TwigsStones.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

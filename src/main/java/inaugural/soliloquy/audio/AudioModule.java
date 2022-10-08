package inaugural.soliloquy.audio;

import com.google.inject.AbstractModule;
import inaugural.soliloquy.audio.entities.SoundsPlayingImpl;
import inaugural.soliloquy.audio.factories.SoundFactoryImpl;
import soliloquy.specs.audio.Audio;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.factories.RegistryFactory;
import soliloquy.specs.common.infrastructure.Registry;

import java.util.UUID;
import java.util.function.Supplier;

import static inaugural.soliloquy.tools.generic.Archetypes.generateSimpleArchetype;

public class AudioModule extends AbstractModule {
    private Audio _audio;

    public AudioModule(MapFactory mapFactory, RegistryFactory registryFactory) {
        Supplier<UUID> uuidFactory = UUID::randomUUID;

        UUID uuidArchetype = uuidFactory.get();

        Sound soundArchetype = generateSimpleArchetype(Sound.class);

        SoundsPlaying soundsPlaying =
                new SoundsPlayingImpl(mapFactory, uuidArchetype, soundArchetype);

        Registry<SoundType> soundTypeRegistry = registryFactory.make(generateSimpleArchetype(SoundType.class));

        SoundFactory soundFactory =
                new SoundFactoryImpl(soundTypeRegistry, soundsPlaying, uuidFactory);

        _audio = new AudioImpl(soundsPlaying, soundFactory, soundTypeRegistry);
    }

    @Override
    protected void configure() {
        bind(Audio.class).toInstance(_audio);
    }
}

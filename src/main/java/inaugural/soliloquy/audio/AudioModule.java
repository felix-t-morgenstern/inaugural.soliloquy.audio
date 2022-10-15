package inaugural.soliloquy.audio;

import com.google.inject.AbstractModule;
import inaugural.soliloquy.audio.entities.SoundsPlayingImpl;
import inaugural.soliloquy.audio.factories.SoundFactoryImpl;
import soliloquy.specs.audio.Audio;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.RegistryFactory;
import soliloquy.specs.common.infrastructure.Registry;

import static inaugural.soliloquy.tools.generic.Archetypes.generateSimpleArchetype;

public class AudioModule extends AbstractModule {
    private Audio audio;

    public AudioModule(RegistryFactory registryFactory) {
        SoundsPlaying soundsPlaying = new SoundsPlayingImpl();

        Registry<SoundType> soundTypeRegistry = registryFactory.make(generateSimpleArchetype(SoundType.class));

        SoundFactory soundFactory = new SoundFactoryImpl(soundTypeRegistry, soundsPlaying);

        audio = new AudioImpl(soundsPlaying, soundFactory, soundTypeRegistry);
    }

    @Override
    protected void configure() {
        bind(Audio.class).toInstance(audio);
    }
}

package inaugural.soliloquy.audio;

import com.google.inject.AbstractModule;
import inaugural.soliloquy.audio.archetypes.SoundArchetype;
import inaugural.soliloquy.audio.archetypes.SoundTypeArchetype;
import soliloquy.specs.audio.Audio;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.factories.RegistryFactory;
import soliloquy.specs.common.infrastructure.Registry;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class AudioModule extends AbstractModule {
	private Audio _audio;

	public AudioModule(EntityUuidFactory entityUuidFactory, MapFactory mapFactory,
                       RegistryFactory registryFactory) {
		
		EntityUuid entityUuidArchetype = entityUuidFactory.createRandomEntityUuid();
		
		Sound soundArchetype = new SoundArchetype();

		SoundsPlaying soundsPlaying = new SoundsPlayingImpl(mapFactory, entityUuidArchetype, soundArchetype);

		Registry<SoundType> soundTypeRegistry = registryFactory.make(new SoundTypeArchetype());

		SoundFactory soundFactory = new SoundFactoryImpl(soundTypeRegistry, soundsPlaying, entityUuidFactory);
		
		_audio = new AudioImpl(soundsPlaying, soundFactory, soundTypeRegistry);
	}
	
	@Override
	protected void configure() {
		bind(Audio.class).toInstance(_audio);
	}
}

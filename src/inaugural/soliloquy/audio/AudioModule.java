package inaugural.soliloquy.audio;

import com.google.inject.AbstractModule;
import soliloquy.specs.audio.entities.Audio;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.Map;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class AudioModule extends AbstractModule {
	private Audio _audio;

	public AudioModule(EntityUuidFactory entityUuidFactory, MapFactory mapFactory) {
		
		EntityUuid entityUuidArchetype = entityUuidFactory.createRandomEntityUuid();
		
		Sound soundArchetype = SoundFactoryImpl.makeSoundArchetype();

		SoundsPlaying soundsPlaying = new SoundsPlayingImpl(mapFactory, entityUuidArchetype, soundArchetype);
		
		Map<String,String> soundTypeFilenameMappings = mapFactory.make("", "");

		SoundFactory soundFactory = new SoundFactoryImpl(soundTypeFilenameMappings, soundsPlaying, entityUuidFactory);
		
		_audio = new AudioImpl(soundsPlaying, soundFactory);
	}
	
	@Override
	protected void configure() {
		bind(Audio.class).toInstance(_audio);
	}
}

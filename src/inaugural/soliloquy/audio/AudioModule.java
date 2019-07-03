package inaugural.soliloquy.audio;

import com.google.inject.AbstractModule;

import soliloquy.specs.audio.entities.IAudio;
import soliloquy.specs.audio.entities.ISound;
import soliloquy.specs.audio.entities.ISoundsPlaying;
import soliloquy.specs.audio.factories.ISoundFactory;
import soliloquy.specs.common.factories.IEntityUuidFactory;
import soliloquy.specs.common.factories.IMapFactory;
import soliloquy.specs.common.infrastructure.IMap;
import soliloquy.specs.common.valueobjects.IEntityUuid;

public class AudioModule extends AbstractModule {
	private IAudio _audio;

	public AudioModule(IEntityUuidFactory entityUuidFactory, IMapFactory mapFactory) {
		
		IEntityUuid entityUuidArchetype = entityUuidFactory.createRandomEntityUuid();
		
		ISound soundArchetype = SoundFactory.makeSoundArchetype();

		ISoundsPlaying soundsPlaying = new SoundsPlaying(mapFactory, entityUuidArchetype, soundArchetype);
		
		IMap<String,String> soundTypeFilenameMappings = mapFactory.make("", "");

		ISoundFactory soundFactory = new SoundFactory(soundTypeFilenameMappings, soundsPlaying, entityUuidFactory);
		
		_audio = new Audio(soundsPlaying, soundFactory);
	}
	
	@Override
	protected void configure() {
		bind(IAudio.class).toInstance(_audio);
	}
}

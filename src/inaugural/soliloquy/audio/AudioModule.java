package inaugural.soliloquy.audio;

import com.google.inject.AbstractModule;

import soliloquy.audio.specs.IAudio;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.IEntityUuid;
import soliloquy.common.specs.IEntityUuidFactory;
import soliloquy.common.specs.IMap;
import soliloquy.common.specs.IMapFactory;

public class AudioModule extends AbstractModule {
	private IAudio _audio;
	private ISoundFactory _soundFactory;
	private ISoundsPlaying _soundsPlaying;
	
	public AudioModule(IEntityUuidFactory entityUuidFactory, IMapFactory mapFactory) {
		
		IEntityUuid entityUuidArchetype = entityUuidFactory.createRandomEntityUuid();
		
		ISound soundArchetype = SoundFactory.makeSoundArchetype();
		
		_soundsPlaying = new SoundsPlaying(mapFactory, entityUuidArchetype, soundArchetype);
		
		IMap<String,String> soundTypeFilenameMappings = mapFactory.make("", "");
		
		_soundFactory = new SoundFactory(soundTypeFilenameMappings, _soundsPlaying, entityUuidFactory);
		
		_audio = new Audio(_soundsPlaying, _soundFactory);
	}
	
	@Override
	protected void configure() {
		bind(IAudio.class).toInstance(_audio);
	}
}

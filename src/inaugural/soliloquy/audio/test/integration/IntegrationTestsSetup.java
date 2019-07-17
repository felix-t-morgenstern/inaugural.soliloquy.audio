package inaugural.soliloquy.audio.test.integration;

import com.google.inject.Guice;
import com.google.inject.Injector;

import inaugural.soliloquy.audio.AudioModule;
import inaugural.soliloquy.common.CommonModule;
import soliloquy.specs.audio.entities.Audio;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.Map;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class IntegrationTestsSetup {
	private final MapFactory MAP_FACTORY;
	
	private final Audio AUDIO;
	
	public final static String SOUND_TYPE_1_ID = "SoundType1Id";
	public String _soundType1Filename;
	
	public IntegrationTestsSetup() throws URISyntaxException {
		_soundType1Filename = new File(String.valueOf(Paths.get(
				getClass().getClassLoader()
						.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toURI())
				.toFile())).getAbsolutePath();

		Injector commonInjector = Guice.createInjector(new CommonModule());
		
		EntityUuidFactory entityUuidFactory = commonInjector.getInstance(EntityUuidFactory.class);
		
		MAP_FACTORY = commonInjector.getInstance(MapFactory.class);
		
		Injector audioInjector = Guice.createInjector(new AudioModule(entityUuidFactory, MAP_FACTORY));
		
		AUDIO = audioInjector.getInstance(Audio.class);
	}
	
	public Audio audio() {
		return AUDIO;
	}
	
	MapFactory mapFactory() {
		return MAP_FACTORY;
	}
	
	public Map<String,String> sampleSoundTypeFilenameMappings() {
		Map<String,String> soundTypeFilenameMappings = mapFactory().make("", "");
		soundTypeFilenameMappings.put(SOUND_TYPE_1_ID, _soundType1Filename);
		return soundTypeFilenameMappings;
	}
	
	public Sound sampleSound() {
    	AUDIO.soundFactory().registerSoundTypes(sampleSoundTypeFilenameMappings());
    	
    	return AUDIO.soundFactory().make(SOUND_TYPE_1_ID);
	}
}

package inaugural.soliloquy.audio.test.integration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import inaugural.soliloquy.audio.AudioModule;
import inaugural.soliloquy.audio.SoundTypeImpl;
import inaugural.soliloquy.common.CommonModule;
import soliloquy.specs.audio.entities.Audio;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.factories.RegistryFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class IntegrationTestsSetup {

	private final Audio AUDIO;

	public final static String SOUND_TYPE_1_ID = "SoundType1Id";
	public final Integer SOUND_TYPE_DEFAULT_LOOPING_STOP_MS = 456;
	public final Integer SOUND_TYPE_DEFAULT_LOOPING_RESTART_MS = 123;

	private final static String RESOURCE = "Kevin_MacLeod_-_Living_Voyage.mp3";

	private String _soundType1Filename;
	
	@SuppressWarnings("ConstantConditions")
	public IntegrationTestsSetup() throws URISyntaxException {
		_soundType1Filename = new File(String.valueOf(
				Paths.get(getClass().getClassLoader().getResource(RESOURCE).toURI())))
				.getAbsolutePath();

		Injector commonInjector = Guice.createInjector(new CommonModule());
		
		EntityUuidFactory entityUuidFactory = commonInjector.getInstance(EntityUuidFactory.class);
		
		Injector audioInjector = Guice.createInjector(new AudioModule(entityUuidFactory,
				commonInjector.getInstance(MapFactory.class),
				commonInjector.getInstance(RegistryFactory.class)));
		
		AUDIO = audioInjector.getInstance(Audio.class);
	}
	
	public Audio audio() {
		return AUDIO;
	}
	
	public SoundType sampleSoundType() {
		return new SoundTypeImpl(SOUND_TYPE_1_ID, _soundType1Filename,
				SOUND_TYPE_DEFAULT_LOOPING_STOP_MS, SOUND_TYPE_DEFAULT_LOOPING_RESTART_MS);
	}
	
	Sound sampleSound() {
    	AUDIO.soundTypes().add(sampleSoundType());
    	
    	return AUDIO.soundFactory().make(SOUND_TYPE_1_ID);
	}
}

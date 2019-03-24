package inaugural.soliloquy.audio.test.integration;

import com.google.inject.Guice;
import com.google.inject.Injector;

import inaugural.soliloquy.audio.AudioModule;
import inaugural.soliloquy.audio.test.unit.SoundFactoryUnitTests;
import inaugural.soliloquy.common.CommonModule;
import soliloquy.audio.specs.IAudio;
import soliloquy.audio.specs.ISound;
import soliloquy.common.specs.IEntityUuidFactory;
import soliloquy.common.specs.IMap;
import soliloquy.common.specs.IMapFactory;

public class IntegrationTestsSetup {
	private final IMapFactory MAP_FACTORY;
	
	private final IAudio AUDIO;
	
	public final static String SOUND_TYPE_1_ID = "SoundType1Id";
	public final static String SOUND_TYPE_1_FILENAME = SoundFactoryUnitTests.class
			.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toString();
	
	public IntegrationTestsSetup() {
		Injector commonInjector = Guice.createInjector(new CommonModule());
		
		IEntityUuidFactory entityUuidFactory = commonInjector.getInstance(IEntityUuidFactory.class);
		
		MAP_FACTORY = commonInjector.getInstance(IMapFactory.class);
		
		Injector audioInjector = Guice.createInjector(new AudioModule(entityUuidFactory, MAP_FACTORY));
		
		AUDIO = audioInjector.getInstance(IAudio.class);
	}
	
	public IAudio audio() {
		return AUDIO;
	}
	
	IMapFactory mapFactory() {
		return MAP_FACTORY;
	}
	
	public IMap<String,String> sampleSoundTypeFilenameMappings() {
		IMap<String,String> soundTypeFilenameMappings = mapFactory().make("", "");
		soundTypeFilenameMappings.put(SOUND_TYPE_1_ID, SOUND_TYPE_1_FILENAME);
		return soundTypeFilenameMappings;
	}
	
	public ISound sampleSound() {
    	AUDIO.soundFactory().registerSoundTypes(sampleSoundTypeFilenameMappings());
    	
    	return AUDIO.soundFactory().make(SOUND_TYPE_1_ID);
	}
}

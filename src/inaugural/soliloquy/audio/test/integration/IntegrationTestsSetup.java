package inaugural.soliloquy.audio.test.integration;

import com.google.inject.Guice;
import com.google.inject.Injector;

import inaugural.soliloquy.audio.AudioModule;
import inaugural.soliloquy.common.CommonModule;
import soliloquy.audio.specs.IAudio;
import soliloquy.common.specs.IEntityUuidFactory;
import soliloquy.common.specs.IMapFactory;

class IntegrationTestsSetup {
	private final IMapFactory MAP_FACTORY;
	
	private final IAudio AUDIO;
	
	IntegrationTestsSetup()
	{
		Injector commonInjector = Guice.createInjector(new CommonModule());
		
		IEntityUuidFactory entityUuidFactory = commonInjector.getInstance(IEntityUuidFactory.class);
		
		MAP_FACTORY = commonInjector.getInstance(IMapFactory.class);
		
		Injector audioInjector = Guice.createInjector(new AudioModule(entityUuidFactory, MAP_FACTORY));
		
		AUDIO = audioInjector.getInstance(IAudio.class);
	}
	
	IAudio audio()
	{
		return AUDIO;
	}
	
	IMapFactory mapFactory()
	{
		return MAP_FACTORY;
	}
}

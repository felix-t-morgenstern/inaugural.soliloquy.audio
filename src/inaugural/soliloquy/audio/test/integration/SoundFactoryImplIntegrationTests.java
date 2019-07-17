package inaugural.soliloquy.audio.test.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.infrastructure.Map;

import static org.junit.jupiter.api.Assertions.*;

class SoundFactoryImplIntegrationTests {
	private SoundFactory _soundFactory;
	
	private Map<String,String> _soundTypeFilenameMappings;
	
    @BeforeEach
	void setUp() throws Exception
    {
    	IntegrationTestsSetup setup = new IntegrationTestsSetup();
    	
    	_soundFactory = setup.audio().soundFactory();
    	
    	_soundTypeFilenameMappings = setup.sampleSoundTypeFilenameMappings();
    }
    
    @Test
	void testGetInterfaceName()
    {
		assertEquals(SoundFactory.class.getCanonicalName(), _soundFactory.getInterfaceName());
    }

    @Test
	void testMake()
    {
    	_soundFactory.registerSoundTypes(_soundTypeFilenameMappings);
    	
    	Sound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

		assertEquals(sound.soundTypeId(), IntegrationTestsSetup.SOUND_TYPE_1_ID);
    }

    @Test
	void testMakeWithInvalidSoundTypeId()
    {
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make("InvalidSoundTypeId!"));
    }
}

package inaugural.soliloquy.audio.test.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.ISound;
import soliloquy.specs.audio.factories.ISoundFactory;
import soliloquy.specs.common.valueobjects.IMap;

import static org.junit.jupiter.api.Assertions.*;

class SoundFactoryIntegrationTests {
	private ISoundFactory _soundFactory;
	
	private IMap<String,String> _soundTypeFilenameMappings;
	
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
		assertEquals(ISoundFactory.class.getCanonicalName(), _soundFactory.getInterfaceName());
    }

    @Test
	void testMake()
    {
    	_soundFactory.registerSoundTypes(_soundTypeFilenameMappings);
    	
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

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

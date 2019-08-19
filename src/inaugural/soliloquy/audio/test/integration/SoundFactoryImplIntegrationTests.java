package inaugural.soliloquy.audio.test.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;

import static org.junit.jupiter.api.Assertions.*;

class SoundFactoryImplIntegrationTests {
	private SoundFactory _soundFactory;
	
    @BeforeEach
	void setUp() throws Exception
    {
    	IntegrationTestsSetup setup = new IntegrationTestsSetup();
    	
    	_soundFactory = setup.audio().soundFactory();
    	setup.audio().soundTypes().register(setup.sampleSoundType());
    }
    
    @Test
	void testGetInterfaceName()
    {
		assertEquals(SoundFactory.class.getCanonicalName(), _soundFactory.getInterfaceName());
    }

    @Test
	void testMake()
    {
    	Sound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

		assertEquals(sound.soundType().id(), IntegrationTestsSetup.SOUND_TYPE_1_ID);
    }

    @Test
	void testMakeWithInvalidSoundTypeId()
    {
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make("InvalidSoundTypeId!"));
    }
}

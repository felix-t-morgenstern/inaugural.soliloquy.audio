package inaugural.soliloquy.audio.test.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.infrastructure.Collection;

import static org.junit.jupiter.api.Assertions.*;

class SoundsPlayingImplIntegrationTests {
	private SoundsPlaying _soundsPlaying;
	private SoundFactory _soundFactory;
	
    @BeforeEach
	void setUp() throws Exception
    {
    	IntegrationTestsSetup setup = new IntegrationTestsSetup();
    	_soundsPlaying = setup.audio().soundsPlaying();
    	
    	_soundFactory = setup.audio().soundFactory();
    	_soundFactory.registerSoundTypes(setup.sampleSoundTypeFilenameMappings());
    }
    
    @Test
	void testGetInterfaceName()
    {
		assertEquals("soliloquy.audio.specs.ISoundsPlaying", _soundsPlaying.getInterfaceName());
    }

    @Test
	void testRegisterAndRemoveSound()
    {
    	Sound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	_soundsPlaying.registerSound(sound);
    	
    	assertTrue(_soundsPlaying.isPlayingSound(sound.id()));
    	
    	_soundsPlaying.removeSound(sound);

    	assertTrue(!_soundsPlaying.isPlayingSound(sound.id()));
    }

    @Test
	void testAllSoundsPlaying()
    {
    	Sound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	Collection<Sound> allSoundsPlaying1 = _soundsPlaying.allSoundsPlaying();
    	Collection<Sound> allSoundsPlaying2 = _soundsPlaying.allSoundsPlaying();

		assertNotSame(allSoundsPlaying1, allSoundsPlaying2);
		assertEquals(1, allSoundsPlaying1.size());
    	assertTrue(allSoundsPlaying1.contains(sound));
    }

    @Test
	void testGetSoundWithNullId()
    {
    	assertThrows(IllegalArgumentException.class, () -> _soundsPlaying.getSound(null));
    }

    @Test
	void testIsPlayingSoundWithNullId()
    {
		assertThrows(IllegalArgumentException.class, () -> _soundsPlaying.isPlayingSound(null));
    }

    @Test
	void testGetSound()
    {
    	Sound soundMade = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	Sound soundPlaying = _soundsPlaying.getSound(soundMade.id());

		assertSame(soundMade, soundPlaying);
    }
}

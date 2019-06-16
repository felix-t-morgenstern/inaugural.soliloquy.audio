package inaugural.soliloquy.audio.test.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.ISound;
import soliloquy.specs.audio.entities.ISoundsPlaying;
import soliloquy.specs.audio.factories.ISoundFactory;
import soliloquy.specs.common.valueobjects.ICollection;

import static org.junit.jupiter.api.Assertions.*;

class SoundsPlayingIntegrationTests {
	private ISoundsPlaying _soundsPlaying;
	private ISoundFactory _soundFactory;
	
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
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	_soundsPlaying.registerSound(sound);
    	
    	assertTrue(_soundsPlaying.isPlayingSound(sound.id()));
    	
    	_soundsPlaying.removeSound(sound);

    	assertTrue(!_soundsPlaying.isPlayingSound(sound.id()));
    }

    @Test
	void testAllSoundsPlaying()
    {
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	ICollection<ISound> allSoundsPlaying1 = _soundsPlaying.allSoundsPlaying();
    	ICollection<ISound> allSoundsPlaying2 = _soundsPlaying.allSoundsPlaying();

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
    	ISound soundMade = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	ISound soundPlaying = _soundsPlaying.getSound(soundMade.id());

		assertSame(soundMade, soundPlaying);
    }
}

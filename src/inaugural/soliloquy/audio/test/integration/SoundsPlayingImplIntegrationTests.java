package inaugural.soliloquy.audio.test.integration;

import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.infrastructure.ReadableCollection;

import java.util.ArrayList;

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
    	setup.audio().soundTypes().add(setup.sampleSoundType());
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
	void testSize() {
		Sound sound1 = new SoundStub(new EntityUuidStub());
		Sound sound2 = new SoundStub(new EntityUuidStub());
		Sound sound3 = new SoundStub(new EntityUuidStub());

		_soundsPlaying.registerSound(sound1);
		_soundsPlaying.registerSound(sound2);
		_soundsPlaying.registerSound(sound3);

		int size = _soundsPlaying.size();

		assertEquals(3, size);
	}

	@Test
	void testIterator() {
		Sound sound1 = new SoundStub(new EntityUuidStub());
		Sound sound2 = new SoundStub(new EntityUuidStub());
		Sound sound3 = new SoundStub(new EntityUuidStub());

		_soundsPlaying.registerSound(sound1);
		_soundsPlaying.registerSound(sound2);
		_soundsPlaying.registerSound(sound3);

		ArrayList<Sound> fromIterator = new ArrayList<>();

		_soundsPlaying.forEach(fromIterator::add);

		assertEquals(3, fromIterator.size());
		assertTrue(fromIterator.contains(sound1));
		assertTrue(fromIterator.contains(sound2));
		assertTrue(fromIterator.contains(sound3));
	}

    @Test
	void testRepresentation()
    {
    	Sound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	ReadableCollection<Sound> allSoundsPlaying1 = _soundsPlaying.representation();
		ReadableCollection<Sound> allSoundsPlaying2 = _soundsPlaying.representation();

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

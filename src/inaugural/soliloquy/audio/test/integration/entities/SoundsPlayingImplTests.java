package inaugural.soliloquy.audio.test.integration.entities;

import inaugural.soliloquy.audio.test.integration.IntegrationTestsSetup;
import inaugural.soliloquy.audio.test.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.fakes.FakeSound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.infrastructure.List;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SoundsPlayingImplTests {
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
    	
    	assertTrue(_soundsPlaying.isPlayingSound(sound.uuid()));
    	
    	_soundsPlaying.removeSound(sound);

		assertFalse(_soundsPlaying.isPlayingSound(sound.uuid()));
    }

	@Test
	void testSize() {
		Sound sound1 = new FakeSound(new EntityUuidStub());
		Sound sound2 = new FakeSound(new EntityUuidStub());
		Sound sound3 = new FakeSound(new EntityUuidStub());

		_soundsPlaying.registerSound(sound1);
		_soundsPlaying.registerSound(sound2);
		_soundsPlaying.registerSound(sound3);

		int size = _soundsPlaying.size();

		assertEquals(3, size);
	}

	@Test
	void testIterator() {
		Sound sound1 = new FakeSound(new EntityUuidStub());
		Sound sound2 = new FakeSound(new EntityUuidStub());
		Sound sound3 = new FakeSound(new EntityUuidStub());

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
    	
    	List<Sound> allSoundsPlaying1 = _soundsPlaying.representation();
		List<Sound> allSoundsPlaying2 = _soundsPlaying.representation();

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
    	
    	Sound soundPlaying = _soundsPlaying.getSound(soundMade.uuid());

		assertSame(soundMade, soundPlaying);
    }
}

package inaugural.soliloquy.audio.test.unit;

import inaugural.soliloquy.audio.SoundsPlayingImpl;
import inaugural.soliloquy.audio.test.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.fakes.FakeMapFactory;
import inaugural.soliloquy.audio.test.fakes.FakeSound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.List;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SoundsPlayingImplTests {
	private SoundsPlayingImpl _soundsPlaying;
	
	private final MapFactory MAP_FACTORY = new FakeMapFactory();
	private final EntityUuid ENTITY_UUID = new EntityUuidStub();
	private final Sound SOUND_ARCHETYPE = new FakeSound(ENTITY_UUID);
	
    @BeforeEach
	void setUp() {
    	_soundsPlaying = new SoundsPlayingImpl(MAP_FACTORY, ENTITY_UUID, SOUND_ARCHETYPE);
    }

    @Test
	void testConstructorWithInvalidParams() {
		assertThrows(IllegalArgumentException.class, () -> new SoundsPlayingImpl(null,
				ENTITY_UUID, SOUND_ARCHETYPE));
		assertThrows(IllegalArgumentException.class, () -> new SoundsPlayingImpl(MAP_FACTORY,
				null, SOUND_ARCHETYPE));
		assertThrows(IllegalArgumentException.class, () -> new SoundsPlayingImpl(MAP_FACTORY,
				ENTITY_UUID, null));
	}
    
    @Test
	void testGetInterfaceName() {
		assertEquals("soliloquy.audio.specs.ISoundsPlaying", _soundsPlaying.getInterfaceName());
    }

    @Test
	void testRegisterAndRemoveSound() {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	assertTrue(_soundsPlaying.isPlayingSound(SOUND_ARCHETYPE.id()));
    	
    	_soundsPlaying.removeSound(SOUND_ARCHETYPE);

		assertFalse(_soundsPlaying.isPlayingSound(SOUND_ARCHETYPE.id()));
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
	void testRepresentation() {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	List<Sound> allSoundsPlaying1 = _soundsPlaying.representation();
		List<Sound> allSoundsPlaying2 = _soundsPlaying.representation();

		assertNotSame(allSoundsPlaying1, allSoundsPlaying2);
		assertEquals(1, allSoundsPlaying1.size());
    	assertTrue(allSoundsPlaying1.contains(SOUND_ARCHETYPE));
    }

    @Test
	void testGetSoundWithNullId() {
    	assertThrows(IllegalArgumentException.class, () -> _soundsPlaying.getSound(null));
    }

    @Test
	void testIsPlayingSoundWithNullId() {
		assertThrows(IllegalArgumentException.class, () -> _soundsPlaying.isPlayingSound(null));
    }

    @Test
	void testGetSound() {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	Sound sound = _soundsPlaying.getSound(SOUND_ARCHETYPE.id());

		assertSame(sound, SOUND_ARCHETYPE);
    }

    @Test
	void testRegisterAndRemoveWithInvalidParams() {
		assertThrows(IllegalArgumentException.class, () -> _soundsPlaying.registerSound(null));
		assertThrows(IllegalArgumentException.class, () -> _soundsPlaying.removeSound(null));
	}
}

package inaugural.soliloquy.audio.test.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inaugural.soliloquy.audio.SoundsPlayingImpl;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.unit.stubs.MapFactoryStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundStub;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.Collection;
import soliloquy.specs.common.valueobjects.EntityUuid;

import static org.junit.jupiter.api.Assertions.*;

class SoundsPlayingImplUnitTests {
	private SoundsPlayingImpl _soundsPlaying;
	
	private final MapFactory MAP_FACTORY = new MapFactoryStub();
	private final EntityUuid ENTITY_UUID = new EntityUuidStub();
	private final Sound SOUND_ARCHETYPE = new SoundStub(ENTITY_UUID);
	
    @BeforeEach
	void setUp() {
    	_soundsPlaying = new SoundsPlayingImpl(MAP_FACTORY, ENTITY_UUID, SOUND_ARCHETYPE);
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

    	assertTrue(!_soundsPlaying.isPlayingSound(SOUND_ARCHETYPE.id()));
    }

    @Test
	void testAllSoundsPlaying() {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	Collection<Sound> allSoundsPlaying1 = _soundsPlaying.allSoundsPlaying();
    	Collection<Sound> allSoundsPlaying2 = _soundsPlaying.allSoundsPlaying();

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
}

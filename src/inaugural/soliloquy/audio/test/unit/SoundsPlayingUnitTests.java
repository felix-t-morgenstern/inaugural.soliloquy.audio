package inaugural.soliloquy.audio.test.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inaugural.soliloquy.audio.SoundsPlaying;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.unit.stubs.MapFactoryStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundStub;
import soliloquy.specs.audio.entities.ISound;
import soliloquy.specs.common.factories.IMapFactory;
import soliloquy.specs.common.infrastructure.ICollection;
import soliloquy.specs.common.valueobjects.IEntityUuid;

import static org.junit.jupiter.api.Assertions.*;

class SoundsPlayingUnitTests {
	private SoundsPlaying _soundsPlaying;
	
	private final IMapFactory MAP_FACTORY = new MapFactoryStub();
	private final IEntityUuid ENTITY_UUID = new EntityUuidStub();
	private final ISound SOUND_ARCHETYPE = new SoundStub(ENTITY_UUID);
	
    @BeforeEach
	void setUp() {
    	_soundsPlaying = new SoundsPlaying(MAP_FACTORY, ENTITY_UUID, SOUND_ARCHETYPE);
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
    	
    	ICollection<ISound> allSoundsPlaying1 = _soundsPlaying.allSoundsPlaying();
    	ICollection<ISound> allSoundsPlaying2 = _soundsPlaying.allSoundsPlaying();

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
    	
    	ISound sound = _soundsPlaying.getSound(SOUND_ARCHETYPE.id());

		assertSame(sound, SOUND_ARCHETYPE);
    }
}

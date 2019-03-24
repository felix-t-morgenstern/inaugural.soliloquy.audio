package inaugural.soliloquy.audio.test.unit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inaugural.soliloquy.audio.SoundsPlaying;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.unit.stubs.MapFactoryStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundStub;
import soliloquy.audio.specs.ISound;
import soliloquy.common.specs.ICollection;
import soliloquy.common.specs.IEntityUuid;
import soliloquy.common.specs.IMapFactory;

public class SoundsPlayingUnitTests {
	private SoundsPlaying _soundsPlaying;
	
	private final IMapFactory MAP_FACTORY = new MapFactoryStub();
	private final IEntityUuid ENTITY_UUID = new EntityUuidStub();
	private final ISound SOUND_ARCHETYPE = new SoundStub(ENTITY_UUID);
	
    @BeforeEach
    protected void setUp() throws Exception {
    	_soundsPlaying = new SoundsPlaying(MAP_FACTORY, ENTITY_UUID, SOUND_ARCHETYPE);
    }
    
    @Test
    public void testGetInterfaceName() {
    	assertTrue(_soundsPlaying.getInterfaceName().equals("soliloquy.audio.specs.ISoundsPlaying"));
    }

    @Test
    public void testRegisterAndRemoveSound() {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	assertTrue(_soundsPlaying.isPlayingSound(SOUND_ARCHETYPE.id()));
    	
    	_soundsPlaying.removeSound(SOUND_ARCHETYPE);

    	assertTrue(!_soundsPlaying.isPlayingSound(SOUND_ARCHETYPE.id()));
    }

    @Test
    public void testAllSoundsPlaying() {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	ICollection<ISound> allSoundsPlaying1 = _soundsPlaying.allSoundsPlaying();
    	ICollection<ISound> allSoundsPlaying2 = _soundsPlaying.allSoundsPlaying();
    	
    	assertTrue(allSoundsPlaying1 != allSoundsPlaying2);
    	assertTrue(allSoundsPlaying1.size() == 1);
    	assertTrue(allSoundsPlaying1.contains(SOUND_ARCHETYPE));
    }

    @Test
    public void testGetSoundWithNullId() {
    	try {
    		_soundsPlaying.getSound(null);
    		assertTrue(false);
    	} catch(IllegalArgumentException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    }

    @Test
    public void testIsPlayingSoundWithNullId() {
    	try {
    		_soundsPlaying.isPlayingSound(null);
    		assertTrue(false);
    	} catch(IllegalArgumentException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    }

    @Test
    public void testGetSound() {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	ISound sound = _soundsPlaying.getSound(SOUND_ARCHETYPE.id());
    	
    	assertTrue(sound == SOUND_ARCHETYPE);
    }
}

package inaugural.soliloquy.audio.test.unit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inaugural.soliloquy.audio.Sound;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.ICollection;
import soliloquy.common.specs.IEntityUuid;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class SoundUnitTests {
	private Sound _sound;
	
	private String _filename;
	
	private final String SOUND_TYPE_ID = "Kevin_MacLeod_-_Living_Voyage";
	
	private final IEntityUuid ENTITY_UUID = new EntityUuidStub();
	private final ISoundsPlaying SOUNDS_PLAYING = new SoundsPlayingStub();
	
	private static ISound _soundIdRemovedWithinSoundsPlaying;

	public SoundUnitTests() throws URISyntaxException {
	}

	@BeforeEach
    protected void setUp() throws Exception {
		_filename = new File(String.valueOf(Paths.get(
				getClass().getClassLoader()
						.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toURI())
				.toFile())).getAbsolutePath();

    	_sound = new Sound(ENTITY_UUID, SOUND_TYPE_ID, _filename, SOUNDS_PLAYING);
    	
    	_soundIdRemovedWithinSoundsPlaying = null;
    }
    
    @Test
    public void testId() {
    	assertTrue(_sound.id() == ENTITY_UUID);
    }

    @Test
    public void testGetInterfaceName() {
    	assertTrue(_sound.getInterfaceName().equals("soliloquy.audio.specs.ISound"));
    }

    @Test
    public void testIsPaused() {
    	assertTrue(_sound.isPaused());
    	
    	_sound.play();
    	
    	assertTrue(!_sound.isPaused());
    	
    	_sound.pause();
    	
    	assertTrue(_sound.isPaused());
    	
    	_sound.stop();
    	
    	assertTrue(!_sound.isPaused());
    }

    @Test
    public void testIsPlaying() {
    	assertTrue(!_sound.isPlaying());
    	
    	_sound.play();
    	
    	assertTrue(_sound.isPlaying());
    	
    	_sound.pause();
    	
    	assertTrue(!_sound.isPlaying());
    	
    	_sound.play();
    	
    	assertTrue(_sound.isPlaying());
    	
    	_sound.stop();
    	
    	assertTrue(!_sound.isPlaying());
    }

    @Test
    public void testIsMuted() {
    	assertTrue(!_sound.isMuted());
    	
    	_sound.mute();
    	
    	assertTrue(_sound.isMuted());
    	
    	_sound.unmute();
    	
    	assertTrue(!_sound.isMuted());
    }

    @Test
    public void testIsStopped() {
    	assertTrue(!_sound.isStopped());
    	
    	_sound.play();

    	assertTrue(!_sound.isStopped());
    	
    	_sound.mute();

    	assertTrue(!_sound.isStopped());
    	
    	_sound.stop();

    	assertTrue(_sound.isStopped());
    }

    @Test
    public void testGetVolume() {
    	assertTrue(_sound.getVolume() == 1.0);
    	
    	_sound.setVolume(0.5);

    	assertTrue(_sound.getVolume() == 0.5);
    	
    	_sound.mute();

    	assertTrue(_sound.getVolume() == 0.5);
    }

    @Test
    public void testGetMillisecondLength() {
    	int millisecondLength = -1;
		try {
			millisecondLength = _sound.getMillisecondLength();
		} catch (InterruptedException e) {
			assertTrue(false);
		}
    	
		// TODO: Determine whether an intermittent test failure occurs here
    	assertTrue(millisecondLength == 208174);
    }

    @Test
    public void testGetMillisecondPosition() throws InterruptedException {
    	final int timeToWait = 500;
    	
    	assertTrue(_sound.getMillisecondPosition() == 0);
    	
    	_sound.setVolume(0.0);
    	_sound.play();
    	Thread.sleep(timeToWait);
    	_sound.pause();
    	int msPosition = _sound.getMillisecondPosition();
    	// NB: When the Sound is not playing, this method should always return the same value.
    	int msPosition2 = _sound.getMillisecondPosition();
    	
    	// NB: At present, there is some delay between when _sound.pause() is called, and when the Sound actually successfully pauses
    	assertTrue(Math.abs(timeToWait-msPosition) <= 250);
    	assertTrue(msPosition == msPosition2);
    }

    @Test
    public void testIsLooping() {
    	_sound.setVolume(0.0);
    	
    	assertTrue(!_sound.getIsLooping());
    	
    	_sound.setIsLooping(true);
    	
    	assertTrue(_sound.getIsLooping());
    }

    @Test
    public void testStopRemovesSoundFromSoundsPlaying() {
    	_sound.stop();
    	
    	assertTrue(_soundIdRemovedWithinSoundsPlaying == _sound);
    }

    @Test
    public void testOperationsOnStoppedSound() {
    	_sound.stop();
    	
    	try {
    		_sound.play();
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    	
    	try {
    		_sound.pause();
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    	
    	try {
    		_sound.mute();
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    	
    	try {
    		_sound.unmute();
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    	
    	try {
    		_sound.setIsLooping(true);
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    	
    	try {
    		_sound.getMillisecondPosition();
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    	
    	try {
    		_sound.getVolume();
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    	
    	try {
    		_sound.setVolume(0.0);
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    	
    	try {
    		_sound.getIsLooping();
    		assertTrue(false);
    	} catch(UnsupportedOperationException e) {
    		assertTrue(true);
    	} catch(Exception e) {
    		assertTrue(false);
    	}
    }
    
    private class SoundsPlayingStub implements ISoundsPlaying {

		public String getInterfaceName() {
			// stub method
			throw new UnsupportedOperationException();
		}

		public ICollection<ISound> allSoundsPlaying() {
			// stub method
			throw new UnsupportedOperationException();
		}

		public boolean isPlayingSound(IEntityUuid soundId) throws IllegalArgumentException {
			// stub method
			throw new UnsupportedOperationException();
		}

		public ISound getSound(IEntityUuid soundId) throws IllegalArgumentException {
			// stub method
			throw new UnsupportedOperationException();
		}

		public void removeSound(ISound sound) throws IllegalArgumentException {
			SoundUnitTests._soundIdRemovedWithinSoundsPlaying = sound;
		}

		@Override
		public void registerSound(ISound sound) throws IllegalArgumentException {
			// stub method
			throw new UnsupportedOperationException();
		}
    	
    }
}

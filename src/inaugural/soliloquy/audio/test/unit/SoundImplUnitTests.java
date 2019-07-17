package inaugural.soliloquy.audio.test.unit;

import inaugural.soliloquy.audio.SoundImpl;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundsPlayingStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SoundImplUnitTests {
	private SoundImpl _sound;
	private String _filename;

	private final EntityUuid ENTITY_UUID = new EntityUuidStub();
	private final SoundsPlaying SOUNDS_PLAYING = new SoundsPlayingStub();
	private final String SOUND_TYPE_ID = "Kevin_MacLeod_-_Living_Voyage";

	@SuppressWarnings({"ConstantConditions", "SpellCheckingInspection"})
	@BeforeEach
	void setUp() throws Exception {
		_filename = new File(String.valueOf(Paths.get(
				getClass().getClassLoader()
						.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toURI())
				.toFile())).getAbsolutePath();
		_sound = new SoundImpl(ENTITY_UUID, SOUND_TYPE_ID, _filename, SOUNDS_PLAYING);
    }
    
    @Test
	void testId() {
		assertSame(_sound.id(), ENTITY_UUID);
    }

    @Test
	void testEquals() {
		Sound sound2 = new SoundImpl(ENTITY_UUID, SOUND_TYPE_ID, _filename, SOUNDS_PLAYING);
		assertEquals(_sound, sound2);
	}

    @Test
	void testGetInterfaceName() {
		assertEquals("soliloquy.audio.specs.ISound", _sound.getInterfaceName());
    }

    @Test
	void testIsPaused() {
    	assertTrue(_sound.isPaused());
    	
    	_sound.play();
    	
    	assertTrue(!_sound.isPaused());
    	
    	_sound.pause();
    	
    	assertTrue(_sound.isPaused());
    	
    	_sound.stop();
    	
    	assertTrue(!_sound.isPaused());
    }

    @Test
	void testIsPlaying() {
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
	void testIsMuted() {
    	assertTrue(!_sound.isMuted());
    	
    	_sound.mute();
    	
    	assertTrue(_sound.isMuted());
    	
    	_sound.unmute();
    	
    	assertTrue(!_sound.isMuted());
    }

    @Test
	void testIsStopped() {
    	assertTrue(!_sound.isStopped());
    	
    	_sound.play();

    	assertTrue(!_sound.isStopped());
    	
    	_sound.mute();

    	assertTrue(!_sound.isStopped());
    	
    	_sound.stop();

    	assertTrue(_sound.isStopped());
    }

    @Test
	void testGetVolume() {
		assertEquals(1.0, _sound.getVolume());
    	
    	_sound.setVolume(0.5);

		assertEquals(0.5, _sound.getVolume());
    	
    	_sound.mute();

		assertEquals(0.5, _sound.getVolume());
    }

    @Test
	void testGetMillisecondLength() {
    	int millisecondLength = -1;
		try {
			millisecondLength = _sound.getMillisecondLength();
		} catch (InterruptedException e) {
			fail("");
		}
    	
		// TODO: Determine whether an intermittent test failure occurs here
		assertTrue(millisecondLength == 208219 || millisecondLength == 208174);
    }

    @Test
	void testGetMillisecondPosition() throws InterruptedException {
    	final int timeToWait = 1000;

		assertEquals(0, _sound.getMillisecondPosition());
    	
    	_sound.setVolume(0.0);
    	_sound.play();
    	Thread.sleep(timeToWait);
    	_sound.pause();
    	int msPosition = _sound.getMillisecondPosition();
    	// NB: When the Sound is not playing, this method should always return the same value.
    	int msPosition2 = _sound.getMillisecondPosition();
    	
    	// NB: At present, there is some delay between when _sound.pause() is called, and when the Sound actually successfully pauses
    	assertTrue(Math.abs(timeToWait-msPosition) <= 250);
		assertEquals(msPosition, msPosition2);
    }

    @Test
	void testIsLooping() {
    	_sound.setVolume(0.0);
    	
    	assertTrue(!_sound.getIsLooping());
    	
    	_sound.setIsLooping(true);
    	
    	assertTrue(_sound.getIsLooping());
    }

    @Test
	void testStopRemovesSoundFromSoundsPlaying() {
    	_sound.stop();

		assertSame(((SoundsPlayingStub) SOUNDS_PLAYING).SoundsRemoved.get(0), _sound);
    }

    @Test
	void testEndOfSoundRemovesSoundFromSoundsPlaying() throws InterruptedException {
		_sound.setVolume(0);
		int msLength = _sound.getMillisecondLength();
		_sound.setMillisecondPosition(msLength - 10);
		_sound.play();
		Thread.sleep(3000);

		assertTrue(_sound.isStopped());
		assertSame(((SoundsPlayingStub) SOUNDS_PLAYING).SoundsRemoved.get(0), _sound);
	}

    @Test
	void testOperationsOnStoppedSound() {
    	_sound.stop();

    	assertThrows(UnsupportedOperationException.class, () -> _sound.play());
		assertThrows(UnsupportedOperationException.class, () -> _sound.pause());
		assertThrows(UnsupportedOperationException.class, () -> _sound.mute());
		assertThrows(UnsupportedOperationException.class, () -> _sound.unmute());
		assertThrows(UnsupportedOperationException.class, () -> _sound.getIsLooping());
		assertThrows(UnsupportedOperationException.class, () -> _sound.setIsLooping(true));
		assertThrows(UnsupportedOperationException.class, () -> _sound.getVolume());
		assertThrows(UnsupportedOperationException.class, () -> _sound.setVolume(0));
		assertThrows(UnsupportedOperationException.class, () -> _sound.getMillisecondPosition());
    }
}

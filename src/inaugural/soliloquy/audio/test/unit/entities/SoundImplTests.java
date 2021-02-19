package inaugural.soliloquy.audio.test.unit.entities;

import inaugural.soliloquy.audio.entities.SoundImpl;
import inaugural.soliloquy.audio.test.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.fakes.FakeSoundType;
import inaugural.soliloquy.audio.test.spydoubles.SoundsPlayingSpyDouble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.io.File;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SoundImplTests {
	private SoundImpl _sound;
	private SoundType _soundType;

	private final EntityUuid ENTITY_UUID = new EntityUuidStub();
	private final SoundsPlaying SOUNDS_PLAYING = new SoundsPlayingSpyDouble();

	@SuppressWarnings("ConstantConditions")
	@BeforeEach
	void setUp() throws Exception {
		String _filename = new File(String.valueOf(Paths.get(
				getClass().getClassLoader()
						.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toURI())
				.toFile())).getAbsolutePath();
		_soundType = new FakeSoundType(_filename);
		_sound = new SoundImpl(ENTITY_UUID, _soundType, SOUNDS_PLAYING);
    }

    @Test
	void testConstructorWithInvalidParams() {
		assertThrows(IllegalArgumentException.class, () -> new SoundImpl(null, _soundType,
				SOUNDS_PLAYING));
		assertThrows(IllegalArgumentException.class, () -> new SoundImpl(ENTITY_UUID, null,
				SOUNDS_PLAYING));
		assertThrows(IllegalArgumentException.class, () -> new SoundImpl(ENTITY_UUID, _soundType,
				null));
	}
    
    @Test
	void testId() {
		assertSame(_sound.id(), ENTITY_UUID);
    }

    @Test
	void testEquals() {
		Sound sound2 = new SoundImpl(ENTITY_UUID, _soundType, SOUNDS_PLAYING);
		assertEquals(_sound, sound2);
	}

    @Test
	void testGetInterfaceName() {
		assertEquals(Sound.class.getCanonicalName(), _sound.getInterfaceName());
    }

    @Test
	void testIsPaused() {
    	assertTrue(_sound.isPaused());
    	
    	_sound.play();

		assertFalse(_sound.isPaused());
    	
    	_sound.pause();
    	
    	assertTrue(_sound.isPaused());
    	
    	_sound.stop();

		assertFalse(_sound.isPaused());
    }

    @Test
	void testIsPlaying() {
		assertFalse(_sound.isPlaying());
    	
    	_sound.play();
    	
    	assertTrue(_sound.isPlaying());
    	
    	_sound.pause();

		assertFalse(_sound.isPlaying());
    	
    	_sound.play();
    	
    	assertTrue(_sound.isPlaying());
    	
    	_sound.stop();

		assertFalse(_sound.isPlaying());
    }

    @Test
	void testIsMuted() {
		assertFalse(_sound.isMuted());
    	
    	_sound.mute();
    	
    	assertTrue(_sound.isMuted());
    	
    	_sound.unmute();

		assertFalse(_sound.isMuted());
    }

    @Test
	void testIsStopped() {
		assertFalse(_sound.isStopped());
    	
    	_sound.play();

		assertFalse(_sound.isStopped());
    	
    	_sound.mute();

		assertFalse(_sound.isStopped());
    	
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
    	int millisecondLength = _sound.getMillisecondLength();
    	
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

		assertFalse(_sound.getIsLooping());
    	
    	_sound.setIsLooping(true);
    	
    	assertTrue(_sound.getIsLooping());
    }

    @Test
	void testSetLoopingStopAndRestartMs() throws InterruptedException {
		final int stopMs = 2000;
		final int restartMs = 1000;

		_sound.setLoopingStopMs(stopMs);
		_sound.setLoopingRestartMs(restartMs);
		_sound.setIsLooping(true);
		_sound.mute();
		_sound.play();

		// NB: I am aware that this test is not deterministic due to potential race conditions; I
		// am electing to test the side effects of this method in this way, because there are no
		// other ways to measure side effects.
		final int numberOfTestsToRun = 100;
		int numberOfTestsRan = 0;
		int numberOfLoopsRun = 0;
		int prevMsPosition = -1;
		while(numberOfTestsRan < numberOfTestsToRun) {
			int msPosition = _sound.getMillisecondPosition();
			if (msPosition < prevMsPosition) {
				numberOfLoopsRun++;
			}
			if (numberOfLoopsRun > 0) {
				assertTrue(msPosition >= restartMs - 5,
						"msPosition: " + msPosition + ", restartMs: " + restartMs + ", " +
								"numberOfTestsRan: " + numberOfTestsRan);
			}
			assertTrue(msPosition < stopMs + 250,
					"msPosition: " + msPosition + ", stopMs: " + stopMs + ", " +
					"numberOfTestsRan: " + numberOfTestsRan);
			Thread.sleep(100);
			numberOfTestsRan++;
			prevMsPosition = msPosition;
		}
		assertTrue(numberOfLoopsRun >= 6);
	}

	@Test
	void testSetLoopingStartOrStopInvalidValues() {
		final int soundLength = _sound.getMillisecondLength();

		assertThrows(IllegalArgumentException.class, () -> _sound.setLoopingStopMs(-1));
		assertThrows(IllegalArgumentException.class, () -> _sound.setLoopingRestartMs(-1));
		assertThrows(IllegalArgumentException.class,
				() -> _sound.setLoopingStopMs(soundLength + 1));
		assertThrows(IllegalArgumentException.class,
				() -> _sound.setLoopingRestartMs(soundLength + 1));

		final int stopMs = 456;
		final int restartMs = 123;

		_sound.setLoopingStopMs(stopMs);
		_sound.setLoopingRestartMs(restartMs);

		assertThrows(IllegalArgumentException.class, () -> _sound.setLoopingStopMs(restartMs));
		assertThrows(IllegalArgumentException.class, () -> _sound.setLoopingRestartMs(stopMs));
	}

    @Test
	void testStopRemovesSoundFromSoundsPlaying() {
    	_sound.stop();

		assertSame(((SoundsPlayingSpyDouble) SOUNDS_PLAYING).SoundsRemoved.get(0), _sound);
    }

    @Test
	void testEndOfSoundRemovesSoundFromSoundsPlaying() throws InterruptedException {
		_sound.setVolume(0);
		int msLength = _sound.getMillisecondLength();
		_sound.setMillisecondPosition(msLength - 10);
		_sound.play();
		Thread.sleep(3000);

		assertTrue(_sound.isStopped());
		assertSame(((SoundsPlayingSpyDouble) SOUNDS_PLAYING).SoundsRemoved.get(0), _sound);
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
		assertThrows(UnsupportedOperationException.class, () -> _sound.setLoopingStopMs(456));
		assertThrows(UnsupportedOperationException.class, () -> _sound.setLoopingRestartMs(123));
    }
}

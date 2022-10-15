package inaugural.soliloquy.audio.test.unit.entities;

import inaugural.soliloquy.audio.entities.SoundImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;

import java.util.UUID;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SoundImplTests {
    private final UUID UUID = java.util.UUID.randomUUID();

    @Mock private SoundType soundType;
    @Mock private Consumer<Sound> publishSoundStopped;

    private Sound sound;

    @BeforeEach
    void setUp() {
        String relativePath = "\\src\\test\\resources\\Kevin_MacLeod_-_Living_Voyage.mp3";
        soundType = mock(SoundType.class);
        when(soundType.relativePath()).thenReturn(relativePath);

        //noinspection unchecked
        publishSoundStopped = mock(Consumer.class);

        sound = new SoundImpl(UUID, soundType, publishSoundStopped);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> new SoundImpl(null, soundType, publishSoundStopped));
        assertThrows(IllegalArgumentException.class, () -> new SoundImpl(UUID, null, publishSoundStopped));
        assertThrows(IllegalArgumentException.class, () -> new SoundImpl(UUID, soundType, null));
    }

    @Test
    void testId() {
        assertSame(sound.uuid(), UUID);
    }

    @Test
    void testEquals() {
        Sound sound2 = new SoundImpl(UUID, soundType, publishSoundStopped);
        assertEquals(sound, sound2);
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(Sound.class.getCanonicalName(), sound.getInterfaceName());
    }

    @Test
    void testIsPaused() {
        assertTrue(sound.isPaused());

        sound.play();

        assertFalse(sound.isPaused());

        sound.pause();

        assertTrue(sound.isPaused());

        sound.stop();

        assertFalse(sound.isPaused());
    }

    @Test
    void testIsPlaying() {
        assertFalse(sound.isPlaying());

        sound.play();

        assertTrue(sound.isPlaying());

        sound.pause();

        assertFalse(sound.isPlaying());

        sound.play();

        assertTrue(sound.isPlaying());

        sound.stop();

        assertFalse(sound.isPlaying());
    }

    @Test
    void testIsMuted() {
        assertFalse(sound.isMuted());

        sound.mute();

        assertTrue(sound.isMuted());

        sound.unmute();

        assertFalse(sound.isMuted());
    }

    @Test
    void testIsStopped() {
        assertFalse(sound.isStopped());

        sound.play();

        assertFalse(sound.isStopped());

        sound.mute();

        assertFalse(sound.isStopped());

        sound.stop();

        assertTrue(sound.isStopped());
    }

    @Test
    void testGetVolume() {
        assertEquals(1.0, sound.getVolume());

        sound.setVolume(0.5);

        assertEquals(0.5, sound.getVolume());

        sound.mute();

        assertEquals(0.5, sound.getVolume());
    }

    @Test
    void testGetMillisecondLength() {
        int millisecondLength = sound.getMillisecondLength();

        assertEquals(208587, millisecondLength);
    }

    @Test
    void testGetMillisecondPosition() throws InterruptedException {
        final int timeToWait = 1000;

        assertEquals(0, sound.getMillisecondPosition());

        sound.setVolume(0.0);
        sound.play();
        Thread.sleep(timeToWait);
        sound.pause();
        int msPosition = sound.getMillisecondPosition();
        // NB: When the Sound is not playing, this method should always return the same value.
        int msPosition2 = sound.getMillisecondPosition();

        // NB: At present, there is some delay between when _sound.pause() is called, and when
        // the Sound actually successfully pauses
        assertTrue(Math.abs(timeToWait - msPosition) <= 250);
        assertEquals(msPosition, msPosition2);
    }

    @Test
    void testIsLooping() {
        sound.setVolume(0.0);

        assertFalse(sound.getIsLooping());

        sound.setIsLooping(true);

        assertTrue(sound.getIsLooping());
    }

    @Test
    void testGetAndSetLoopingStopAndRestartMs() {
        final int stopMs = 2000;
        final int restartMs = 1000;

        sound.setLoopingStopMs(stopMs);
        sound.setLoopingRestartMs(restartMs);

        assertEquals(stopMs, sound.getLoopingStopMs());
        assertEquals(restartMs, sound.getLoopingRestartMs());
    }

    @Test
    void testSetLoopingStartOrStopInvalidValues() {
        final int soundLength = sound.getMillisecondLength();

        assertThrows(IllegalArgumentException.class, () -> sound.setLoopingStopMs(-1));
        assertThrows(IllegalArgumentException.class, () -> sound.setLoopingRestartMs(-1));
        assertThrows(IllegalArgumentException.class,
                () -> sound.setLoopingStopMs(soundLength + 1));
        assertThrows(IllegalArgumentException.class,
                () -> sound.setLoopingRestartMs(soundLength + 1));

        final int stopMs = 456;
        final int restartMs = 123;

        sound.setLoopingStopMs(stopMs);
        sound.setLoopingRestartMs(restartMs);

        assertThrows(IllegalArgumentException.class, () -> sound.setLoopingStopMs(restartMs));
        assertThrows(IllegalArgumentException.class, () -> sound.setLoopingRestartMs(stopMs));
    }

    @Test
    void testStopRemovesSoundFromSoundsPlaying() {
        sound.stop();

        verify(publishSoundStopped, times(1)).accept(sound);
    }

    @Test
    void testEndOfSoundRemovesSoundFromSoundsPlaying() throws InterruptedException {
        sound.setVolume(0);
        int msLength = sound.getMillisecondLength();
        sound.setMillisecondPosition(msLength - 10);
        sound.play();
        Thread.sleep(3000);

        assertTrue(sound.isStopped());
        verify(publishSoundStopped, times(1)).accept(sound);
    }

    @Test
    void testOperationsOnStoppedSound() {
        sound.stop();

        assertThrows(UnsupportedOperationException.class, () -> sound.play());
        assertThrows(UnsupportedOperationException.class, () -> sound.pause());
        assertThrows(UnsupportedOperationException.class, () -> sound.mute());
        assertThrows(UnsupportedOperationException.class, () -> sound.unmute());
        assertThrows(UnsupportedOperationException.class, () -> sound.getIsLooping());
        assertThrows(UnsupportedOperationException.class, () -> sound.setIsLooping(true));
        assertThrows(UnsupportedOperationException.class, () -> sound.getVolume());
        assertThrows(UnsupportedOperationException.class, () -> sound.setVolume(0));
        assertThrows(UnsupportedOperationException.class, () -> sound.getMillisecondPosition());
        assertThrows(UnsupportedOperationException.class, () -> sound.setLoopingStopMs(456));
        assertThrows(UnsupportedOperationException.class, () -> sound.setLoopingRestartMs(123));
    }
}

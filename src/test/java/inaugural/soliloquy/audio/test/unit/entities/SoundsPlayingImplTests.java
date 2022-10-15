package inaugural.soliloquy.audio.test.unit.entities;

import inaugural.soliloquy.audio.entities.SoundsPlayingImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SoundsPlayingImplTests {
    private final UUID MOCK_SOUND_1_UUID = java.util.UUID.randomUUID();
    private final UUID MOCK_SOUND_2_UUID = java.util.UUID.randomUUID();
    private final UUID MOCK_SOUND_3_UUID = java.util.UUID.randomUUID();

    @Mock private Sound mockSound1;
    @Mock private Sound mockSound2;
    @Mock private Sound mockSound3;

    private SoundsPlayingImpl soundsPlaying;

    @BeforeEach
    void setUp() {
        mockSound1 = mock(Sound.class);
        when(mockSound1.uuid()).thenReturn(MOCK_SOUND_1_UUID);
        mockSound2 = mock(Sound.class);
        when(mockSound2.uuid()).thenReturn(MOCK_SOUND_2_UUID);
        mockSound3 = mock(Sound.class);
        when(mockSound3.uuid()).thenReturn(MOCK_SOUND_3_UUID);

        soundsPlaying = new SoundsPlayingImpl();
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundsPlaying.class.getCanonicalName(), soundsPlaying.getInterfaceName());
    }

    @Test
    void testRegisterAndRemoveSound() {
        soundsPlaying.registerSound(mockSound1);

        assertTrue(soundsPlaying.isPlayingSound(MOCK_SOUND_1_UUID));

        soundsPlaying.removeSound(mockSound1);

        assertFalse(soundsPlaying.isPlayingSound(MOCK_SOUND_1_UUID));
    }

    @Test
    void testIterator() {
        soundsPlaying.registerSound(mockSound1);
        soundsPlaying.registerSound(mockSound2);
        soundsPlaying.registerSound(mockSound3);

        ArrayList<Sound> fromIterator = new ArrayList<>(soundsPlaying.representation());

        assertEquals(3, fromIterator.size());
        assertTrue(fromIterator.contains(mockSound1));
        assertTrue(fromIterator.contains(mockSound2));
        assertTrue(fromIterator.contains(mockSound3));
    }

    @Test
    void testRepresentation() {
        soundsPlaying.registerSound(mockSound1);

        List<Sound> allSoundsPlaying1 = soundsPlaying.representation();
        List<Sound> allSoundsPlaying2 = soundsPlaying.representation();

        assertNotSame(allSoundsPlaying1, allSoundsPlaying2);
        assertEquals(1, allSoundsPlaying1.size());
        assertTrue(allSoundsPlaying1.contains(mockSound1));
    }

    @Test
    void testGetSoundWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> soundsPlaying.getSound(null));
    }

    @Test
    void testIsPlayingSoundWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> soundsPlaying.isPlayingSound(null));
    }

    @Test
    void testGetSound() {
        soundsPlaying.registerSound(mockSound1);

        Sound sound = soundsPlaying.getSound(MOCK_SOUND_1_UUID);

        assertSame(sound, mockSound1);
    }

    @Test
    void testRegisterAndRemoveWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> soundsPlaying.registerSound(null));
        assertThrows(IllegalArgumentException.class, () -> soundsPlaying.removeSound(null));
    }
}

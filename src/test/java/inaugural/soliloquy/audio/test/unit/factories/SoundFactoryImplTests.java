package inaugural.soliloquy.audio.test.unit.factories;

import inaugural.soliloquy.audio.entities.SoundImpl;
import inaugural.soliloquy.audio.factories.SoundFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.infrastructure.Registry;

import java.util.UUID;

import static inaugural.soliloquy.tools.random.Random.randomString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SoundFactoryImplTests {
    private final int DEFAULT_LOOPING_RESTART_MS = 123;
    private final int DEFAULT_LOOPING_STOP_MS = 456;
    private final String RELATIVE_PATH =
            "\\src\\test\\resources\\Kevin_MacLeod_-_Living_Voyage.mp3";

    @Mock private Registry<SoundType> mockSoundTypeRegistry;
    @Mock private SoundType mockSoundType;
    @Mock private SoundsPlaying mockSoundsPlaying;

    private SoundFactoryImpl soundFactory;

    @BeforeEach
    void setUp() {
        mockSoundType = mock(SoundType.class);
        when(mockSoundType.relativePath()).thenReturn(RELATIVE_PATH);
        when(mockSoundType.defaultLoopingStopMs()).thenReturn(DEFAULT_LOOPING_STOP_MS);
        when(mockSoundType.defaultLoopingRestartMs()).thenReturn(DEFAULT_LOOPING_RESTART_MS);

        //noinspection unchecked
        mockSoundTypeRegistry = mock(Registry.class);
        when(mockSoundTypeRegistry.get(anyString())).thenReturn(mockSoundType);

        mockSoundsPlaying = mock(SoundsPlaying.class);

        soundFactory = new SoundFactoryImpl(mockSoundTypeRegistry, mockSoundsPlaying);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new SoundFactoryImpl(null, mockSoundsPlaying));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundFactoryImpl(mockSoundTypeRegistry, null));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundFactory.class.getCanonicalName(), soundFactory.getInterfaceName());
    }

    @Test
    void testMake() {
        String soundTypeId = randomString();
        when(mockSoundTypeRegistry.contains(soundTypeId)).thenReturn(true);

        Sound sound = soundFactory.make(soundTypeId);

        assertNotNull(sound);
        assertTrue(sound instanceof SoundImpl);
        assertSame(mockSoundType, sound.soundType());
        assertEquals(DEFAULT_LOOPING_RESTART_MS, sound.getLoopingRestartMs());
        assertEquals(DEFAULT_LOOPING_STOP_MS, sound.getLoopingStopMs());
        verify(mockSoundTypeRegistry, times(1)).get(soundTypeId);
        verify(mockSoundsPlaying, times(1)).registerSound(sound);
    }

    @Test
    void testMakeWithUuid() {
        UUID uuid = UUID.randomUUID();
        String soundTypeId = randomString();
        when(mockSoundTypeRegistry.contains(soundTypeId)).thenReturn(true);

        Sound sound = soundFactory.make(soundTypeId, uuid);

        assertEquals(uuid, sound.uuid());
    }

    @Test
    void testMakeWithInvalidSoundTypeId() {
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(null));
        assertThrows(IllegalArgumentException.class,
                () -> soundFactory.make("InvalidSoundTypeId!"));
    }

    @Test
    void testMakeWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(null));
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(""));
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(RELATIVE_PATH, null));
        assertThrows(IllegalArgumentException.class,
                () -> soundFactory.make(null, java.util.UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class,
                () -> soundFactory.make("", java.util.UUID.randomUUID()));
    }
}

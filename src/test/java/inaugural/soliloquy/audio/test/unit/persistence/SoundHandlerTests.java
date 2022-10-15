package inaugural.soliloquy.audio.test.unit.persistence;

import com.google.gson.JsonSyntaxException;
import inaugural.soliloquy.audio.persistence.SoundHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.persistence.TypeHandler;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SuppressWarnings("FieldCanBeLocal")
class SoundHandlerTests {
    private final String UUID_STRING = "839f1134-3622-493f-ba19-7d7be392cd3b";
    private final String SOUND_TYPE_ID = "soundTypeId";
    private final boolean IS_PAUSED = true;
    private final boolean IS_MUTED = false;
    private final double VOLUME = 0.111d;
    private final int MS_POSITION = 222;
    private final boolean IS_LOOPING = true;
    private final int LOOPING_STOP_MS = 333;
    private final int LOOPING_RESTART_MS = 444;

    @Mock private SoundType mockSoundType;
    @Mock private Sound mockSound;
    @Mock private SoundFactory mockSoundFactory;

    private TypeHandler<Sound> soundHandler;

    private final static String DATA = "{\"uuid\":\"839f1134-3622-493f-ba19-7d7be392cd3b\"," +
            "\"type\":\"soundTypeId\",\"paused\":true,\"muted\":false,\"vol\":0.111," +
            "\"msPos\":222,\"looping\":true,\"loopingStopMs\":333,\"loopingRestartMs\":444}";

    @BeforeEach
    void setUp() {
        mockSoundType = mock(SoundType.class);
        when(mockSoundType.id()).thenReturn(SOUND_TYPE_ID);

        mockSound = mock(Sound.class);
        when(mockSound.uuid()).thenReturn(UUID.fromString(UUID_STRING));
        when(mockSound.soundType()).thenReturn(mockSoundType);
        when(mockSound.isPaused()).thenReturn(IS_PAUSED);
        when(mockSound.isMuted()).thenReturn(IS_MUTED);
        when(mockSound.getVolume()).thenReturn(VOLUME);
        when(mockSound.getMillisecondPosition()).thenReturn(MS_POSITION);
        when(mockSound.getIsLooping()).thenReturn(IS_LOOPING);
        when(mockSound.getLoopingStopMs()).thenReturn(LOOPING_STOP_MS);
        when(mockSound.getLoopingRestartMs()).thenReturn(LOOPING_RESTART_MS);

        mockSoundFactory = mock(SoundFactory.class);
        when(mockSoundFactory.make(anyString(), any())).thenReturn(mockSound);

        soundHandler = new SoundHandler(mockSoundFactory);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> new SoundHandler(null));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                        Sound.class.getCanonicalName() + ">",
                soundHandler.getInterfaceName());
    }

    @Test
    void testGetArchetype() {
        Sound archetype = soundHandler.getArchetype();

        assertNotNull(archetype);
        assertEquals(Sound.class.getCanonicalName(), archetype.getInterfaceName());
    }

    @Test
    void testWrite() {
        String writtenValue = soundHandler.write(mockSound);

        assertEquals(DATA, writtenValue);
    }

    @Test
    void testWriteInvalid() {
        assertThrows(IllegalArgumentException.class, () -> soundHandler.write(null));
    }

    @Test
    void testRead() {
        Sound readValue = soundHandler.read(DATA);

        assertNotNull(readValue);
        assertSame(mockSound, readValue);
        verify(mockSoundFactory, times(1)).make(eq(SOUND_TYPE_ID), eq(UUID.fromString(UUID_STRING)));
        verify(mockSound, times(1)).setIsLooping(IS_LOOPING);
        verify(mockSound, times(1)).setVolume(VOLUME);
        verify(mockSound, times(1)).unmute();
        verify(mockSound, times(1)).setMillisecondPosition(MS_POSITION);
        verify(mockSound, times(1)).pause();
    }

    @Test
    void testReadInvalid() {
        assertThrows(IllegalArgumentException.class, () -> soundHandler.read(null));
        assertThrows(IllegalArgumentException.class, () -> soundHandler.read(""));
        assertThrows(JsonSyntaxException.class, () ->
                soundHandler
                        .read("{\"soundTypeId\":\"SoundTypeId\",\"isPaused\":true," +
                                "\"isMuted\":true,\"volume\":0.5,\"msPosition\":100," +
                                "\"isLooping\":true"));
    }
}

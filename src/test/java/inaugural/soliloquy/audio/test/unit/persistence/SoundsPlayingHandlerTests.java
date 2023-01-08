package inaugural.soliloquy.audio.test.unit.persistence;

import inaugural.soliloquy.audio.persistence.SoundsPlayingHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.persistence.TypeHandler;

import java.util.ArrayList;

import static inaugural.soliloquy.tools.random.Random.randomString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SoundsPlayingHandlerTests {
    private final String SOUND_1 = randomString();
    private final String SOUND_2 = randomString();
    private final String SOUND_3 = randomString();

    private final String WRITTEN_VALUE =
            String.format("{\"soundDTOs\":[\"%s\",\"%s\",\"%s\"]}", SOUND_1, SOUND_2, SOUND_3);

    @Mock private SoundsPlaying mockSoundsPlaying;
    @Mock private Sound mockSound1;
    @Mock private Sound mockSound2;
    @Mock private Sound mockSound3;
    @Mock private TypeHandler<Sound> mockSoundHandler;

    private TypeHandler<SoundsPlaying> soundsPlayingHandler;

    @BeforeEach
    void setUp() {
        mockSound1 = mock(Sound.class);
        mockSound2 = mock(Sound.class);
        mockSound3 = mock(Sound.class);

        mockSoundsPlaying = mock(SoundsPlaying.class);

        when(mockSoundsPlaying.representation()).thenReturn(new ArrayList<>() {{
            add(mockSound1);
            add(mockSound2);
            add(mockSound3);
        }});

        //noinspection unchecked
        mockSoundHandler = mock(TypeHandler.class);
        when(mockSoundHandler.write(mockSound1)).thenReturn(SOUND_1);
        when(mockSoundHandler.write(mockSound2)).thenReturn(SOUND_2);
        when(mockSoundHandler.write(mockSound3)).thenReturn(SOUND_3);
        when(mockSoundHandler.read(SOUND_1)).thenReturn(mockSound1);
        when(mockSoundHandler.read(SOUND_2)).thenReturn(mockSound2);
        when(mockSoundHandler.read(SOUND_3)).thenReturn(mockSound3);

        soundsPlayingHandler = new SoundsPlayingHandler(mockSoundHandler, mockSoundsPlaying);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new SoundsPlayingHandler(null, mockSoundsPlaying));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundsPlayingHandler(mockSoundHandler, null));
    }

    @Test
    void testWrite() {
        String writtenValue = soundsPlayingHandler.write(mockSoundsPlaying);

        assertEquals(WRITTEN_VALUE, writtenValue);
    }

    @Test
    void testRead() {
        SoundsPlaying soundsPlaying = soundsPlayingHandler.read(WRITTEN_VALUE);

        assertNull(soundsPlaying);
        verify(mockSoundsPlaying, times(1)).removeSound(mockSound1);
        verify(mockSoundsPlaying, times(1)).removeSound(mockSound2);
        verify(mockSoundsPlaying, times(1)).removeSound(mockSound3);

        verify(mockSoundHandler, times(1)).read(SOUND_1);
        verify(mockSoundHandler, times(1)).read(SOUND_2);
        verify(mockSoundHandler, times(1)).read(SOUND_3);

        verify(mockSoundsPlaying, times(1)).registerSound(mockSound1);
        verify(mockSoundsPlaying, times(1)).registerSound(mockSound2);
        verify(mockSoundsPlaying, times(1)).registerSound(mockSound3);
    }

    @Test
    void testReadWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> soundsPlayingHandler.read(null));
        assertThrows(IllegalArgumentException.class, () -> soundsPlayingHandler.read(""));
    }

    @Test
    void testGetArchetype() {
        assertNotNull(soundsPlayingHandler.getArchetype());
        assertEquals(SoundsPlaying.class.getCanonicalName(),
                soundsPlayingHandler.getArchetype().getInterfaceName());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                        SoundsPlaying.class.getCanonicalName() + ">",
                soundsPlayingHandler.getInterfaceName());
    }
}

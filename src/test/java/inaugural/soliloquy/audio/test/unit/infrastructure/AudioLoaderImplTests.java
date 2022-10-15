package inaugural.soliloquy.audio.test.unit.infrastructure;

import inaugural.soliloquy.audio.infrastructure.AudioLoaderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundTypeFactory;
import soliloquy.specs.audio.infrastructure.AudioLoader;
import soliloquy.specs.common.infrastructure.Registry;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static inaugural.soliloquy.tools.files.Files.executionDirectory;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AudioLoaderImplTests {
    private final Map<String, String> IDS_FOR_FILENAMES = new HashMap<>();
    private final Map<String, Integer> DEFAULT_LOOPING_STOP_MS_FOR_IDS = new HashMap<>();
    private final Map<String, Integer> DEFAULT_LOOPING_RESTART_MS_FOR_IDS = new HashMap<>();

    private final String DIR_RELATIVE_PATH = "\\src\\test\\resources\\";

    private final String ID_1 = "ExitThePremises";
    private final String RELATIVE_PATH_1 =
            "exit-the-premises-by-kevin-macleod-from-filmmusic-io.mp3";
    private final Integer DEFAULT_LOOPING_STOP_MS_1 = 14850;
    private final Integer DEFAULT_LOOPING_RESTART_MS_1 = 7520;

    private final String ID_2 = "LivingVoyage";
    private final String RELATIVE_PATH_2 = "Kevin_MacLeod_-_Living_Voyage.mp3";
    private final Integer DEFAULT_LOOPING_STOP_MS_2 = null;
    private final Integer DEFAULT_LOOPING_RESTART_MS_2 = null;

    @Mock private Registry<SoundType> mockSoundTypesRegistry;
    @Mock private SoundTypeFactory mockSoundTypeFactory;
    @Mock private SoundType mockSoundType;

    private AudioLoader audioLoader;

    @SuppressWarnings("ConstantConditions")
    @BeforeEach
    void setUp() {
        IDS_FOR_FILENAMES.put(RELATIVE_PATH_1, ID_1);
        DEFAULT_LOOPING_STOP_MS_FOR_IDS.put(ID_1, DEFAULT_LOOPING_STOP_MS_1);
        DEFAULT_LOOPING_RESTART_MS_FOR_IDS.put(ID_1, DEFAULT_LOOPING_RESTART_MS_1);

        IDS_FOR_FILENAMES.put(RELATIVE_PATH_2, ID_2);
        DEFAULT_LOOPING_STOP_MS_FOR_IDS.put(ID_2, DEFAULT_LOOPING_STOP_MS_2);
        DEFAULT_LOOPING_RESTART_MS_FOR_IDS.put(ID_2, DEFAULT_LOOPING_RESTART_MS_2);

        //noinspection unchecked
        mockSoundTypesRegistry = mock(Registry.class);
        mockSoundType = mock(SoundType.class);
        mockSoundTypeFactory = mock(SoundTypeFactory.class);
        when(mockSoundTypeFactory.make(anyString(), anyString(), anyInt(), anyInt())).thenReturn(
                mockSoundType);

        audioLoader = new AudioLoaderImpl(mockSoundTypesRegistry, mockSoundTypeFactory);
        audioLoader.filetypes().addAll(Arrays.asList("mp3", "wav"));
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new AudioLoaderImpl(null, mockSoundTypeFactory));
        assertThrows(IllegalArgumentException.class,
                () -> new AudioLoaderImpl(mockSoundTypesRegistry, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testLoadFromDirectory() {
        audioLoader.setDefaultLoopingStopMsForIds(DEFAULT_LOOPING_STOP_MS_FOR_IDS);
        audioLoader.setDefaultLoopingRestartMsForIds(DEFAULT_LOOPING_RESTART_MS_FOR_IDS);

        audioLoader.loadFromDirectory(DIR_RELATIVE_PATH, IDS_FOR_FILENAMES);

        verify(mockSoundTypesRegistry, times(2)).add(any());
        verify(mockSoundTypeFactory, times(1)).make(ID_1,
                executionDirectory() + DIR_RELATIVE_PATH + RELATIVE_PATH_1,
                DEFAULT_LOOPING_STOP_MS_1, DEFAULT_LOOPING_RESTART_MS_1);
        verify(mockSoundTypeFactory, times(1)).make(ID_2,
                executionDirectory() + DIR_RELATIVE_PATH + RELATIVE_PATH_2,
                DEFAULT_LOOPING_STOP_MS_2, DEFAULT_LOOPING_RESTART_MS_2);
    }

    @Test
    void testLoadFromDirectoryWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> audioLoader.loadFromDirectory(null, IDS_FOR_FILENAMES));
        assertThrows(IllegalArgumentException.class,
                () -> audioLoader.loadFromDirectory("", IDS_FOR_FILENAMES));
        assertThrows(IllegalArgumentException.class,
                () -> audioLoader.loadFromDirectory(DIR_RELATIVE_PATH, null));
    }

    @Test
    void testSetDefaultLoopingStopMsForIdsWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> audioLoader.setDefaultLoopingStopMsForIds(null));
    }

    @Test
    void testSetDefaultLoopingRestartMsForIdsWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> audioLoader.setDefaultLoopingRestartMsForIds(null));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(AudioLoader.class.getCanonicalName(), audioLoader.getInterfaceName());
    }
}

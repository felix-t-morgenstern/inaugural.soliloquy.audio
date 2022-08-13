package inaugural.soliloquy.audio.test.unit.infrastructure;

import inaugural.soliloquy.audio.infrastructure.AudioLoaderImpl;
import inaugural.soliloquy.audio.test.fakes.FakeRegistry;
import inaugural.soliloquy.audio.test.fakes.FakeSoundTypeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundTypeFactory;
import soliloquy.specs.audio.infrastructure.AudioLoader;
import soliloquy.specs.common.infrastructure.Registry;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AudioLoaderImplTests {
    private AudioLoaderImpl _audioLoader;

    private final Registry<SoundType> SOUND_TYPES_REGISTRY = new FakeRegistry<>();
    private final SoundTypeFactory SOUND_TYPE_FACTORY = new FakeSoundTypeFactory();

    private final Map<String, String[]> IDS_FOR_FILENAMES = new HashMap<>();
    private final Map<String, Integer> DEFAULT_LOOPING_STOP_MS_FOR_IDS = new HashMap<>();
    private final Map<String, Integer> DEFAULT_LOOPING_RESTART_MS_FOR_IDS = new HashMap<>();

    private final String ID_1 = "ExitThePremises";
    private final String FILENAME_1 = "exit-the-premises-by-kevin-macleod-from-filmmusic-io.mp3";
    private final Integer DEFAULT_LOOPING_STOP_MS_1 = 14850;
    private final Integer DEFAULT_LOOPING_RESTART_MS_1 = 7520;

    private final String ID_2 = "LivingVoyage";
    private final String FILENAME_2 = "Kevin_MacLeod_-_Living_Voyage.mp3";
    private final Integer DEFAULT_LOOPING_STOP_MS_2 = null;
    private final Integer DEFAULT_LOOPING_RESTART_MS_2 = null;

    private final String ID_3 = "LivingVoyageLooping";
    private final Integer DEFAULT_LOOPING_STOP_MS_3 = 123;
    private final Integer DEFAULT_LOOPING_RESTART_MS_3 = 456;

    @SuppressWarnings("ConstantConditions")
    @BeforeEach
    void setUp() {
        IDS_FOR_FILENAMES.put(FILENAME_1, new String[]{ID_1});
        DEFAULT_LOOPING_STOP_MS_FOR_IDS.put(ID_1, DEFAULT_LOOPING_STOP_MS_1);
        DEFAULT_LOOPING_RESTART_MS_FOR_IDS.put(ID_1, DEFAULT_LOOPING_RESTART_MS_1);

        IDS_FOR_FILENAMES.put(FILENAME_2, new String[]{ID_2, ID_3});
        DEFAULT_LOOPING_STOP_MS_FOR_IDS.put(ID_2, DEFAULT_LOOPING_STOP_MS_2);
        DEFAULT_LOOPING_RESTART_MS_FOR_IDS.put(ID_2, DEFAULT_LOOPING_RESTART_MS_2);
        DEFAULT_LOOPING_STOP_MS_FOR_IDS.put(ID_3, DEFAULT_LOOPING_STOP_MS_3);
        DEFAULT_LOOPING_RESTART_MS_FOR_IDS.put(ID_3, DEFAULT_LOOPING_RESTART_MS_3);

        _audioLoader = new AudioLoaderImpl(SOUND_TYPES_REGISTRY, SOUND_TYPE_FACTORY);
        _audioLoader.filetypes().addAll(Arrays.asList("mp3", "wav"));
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new AudioLoaderImpl(null, SOUND_TYPE_FACTORY));
        assertThrows(IllegalArgumentException.class,
                () -> new AudioLoaderImpl(SOUND_TYPES_REGISTRY, null));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testLoadFromDirectory() {
        String soundsFolderRelativePath = "";

        _audioLoader.setDefaultLoopingStopMsForIds(DEFAULT_LOOPING_STOP_MS_FOR_IDS);
        _audioLoader.setDefaultLoopingRestartMsForIds(DEFAULT_LOOPING_RESTART_MS_FOR_IDS);

        _audioLoader.loadFromDirectory(soundsFolderRelativePath, IDS_FOR_FILENAMES);

        SoundType type1 = SOUND_TYPES_REGISTRY.get(ID_1);
        assertNotNull(type1);
        assertEquals(FILENAME_1, Paths.get(type1.absolutePath()).getFileName().toString());
        assertEquals(DEFAULT_LOOPING_STOP_MS_1, type1.defaultLoopingStopMs());
        assertEquals(DEFAULT_LOOPING_RESTART_MS_1, type1.defaultLoopingRestartMs());
        assertEquals(SoundType.class.getCanonicalName(), type1.getInterfaceName());

        SoundType type2 = SOUND_TYPES_REGISTRY.get(ID_2);
        assertNotNull(type2);
        assertEquals(FILENAME_2, Paths.get(type2.absolutePath()).getFileName().toString());
        assertEquals(DEFAULT_LOOPING_STOP_MS_2, type2.defaultLoopingStopMs());
        assertEquals(DEFAULT_LOOPING_RESTART_MS_2, type2.defaultLoopingRestartMs());
        assertEquals(SoundType.class.getCanonicalName(), type2.getInterfaceName());

        SoundType type3 = SOUND_TYPES_REGISTRY.get(ID_3);
        assertNotNull(type3);
        assertEquals(FILENAME_2, Paths.get(type3.absolutePath()).getFileName().toString());
        assertEquals(DEFAULT_LOOPING_STOP_MS_3, type3.defaultLoopingStopMs());
        assertEquals(DEFAULT_LOOPING_RESTART_MS_3, type3.defaultLoopingRestartMs());
        assertEquals(SoundType.class.getCanonicalName(), type3.getInterfaceName());
    }

    @Test
    void testSetDefaultLoopingStopMsForIdsWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> _audioLoader.setDefaultLoopingStopMsForIds(null));
    }

    @Test
    void testSetDefaultLoopingRestartMsForIdsWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> _audioLoader.setDefaultLoopingRestartMsForIds(null));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(AudioLoader.class.getCanonicalName(), _audioLoader.getInterfaceName());
    }
}

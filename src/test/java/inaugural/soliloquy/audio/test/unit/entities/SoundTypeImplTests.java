package inaugural.soliloquy.audio.test.unit.entities;

import inaugural.soliloquy.audio.entities.SoundTypeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.SoundType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SoundTypeImplTests {
    private SoundType _soundType;

    private final String ID = "SoundTypeId";
    private final String FILENAME = "SoundTypeFilename";
    private final Integer DEFAULT_LOOPING_STOP_MS = 456456;
    private final Integer DEFAULT_LOOPING_RESTART_MS = 123123;

    @BeforeEach
    void setUp() {
        _soundType = new SoundTypeImpl(ID, FILENAME, DEFAULT_LOOPING_STOP_MS,
                DEFAULT_LOOPING_RESTART_MS);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new SoundTypeImpl(null, FILENAME, DEFAULT_LOOPING_STOP_MS,
                        DEFAULT_LOOPING_RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundTypeImpl(ID, null, DEFAULT_LOOPING_STOP_MS,
                        DEFAULT_LOOPING_RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundTypeImpl(ID, "", DEFAULT_LOOPING_STOP_MS,
                        DEFAULT_LOOPING_RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundTypeImpl(ID, FILENAME, -DEFAULT_LOOPING_STOP_MS,
                        DEFAULT_LOOPING_RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundTypeImpl(ID, FILENAME, DEFAULT_LOOPING_STOP_MS,
                        -DEFAULT_LOOPING_RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundTypeImpl(ID, FILENAME, DEFAULT_LOOPING_RESTART_MS,
                        DEFAULT_LOOPING_STOP_MS));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundType.class.getCanonicalName(), _soundType.getInterfaceName());
    }

    @Test
    void testId() {
        assertEquals(ID, _soundType.id());
    }

    @Test
    void testRelativePath() {
        assertEquals(FILENAME, _soundType.relativePath());
    }

    @Test
    void testDefaultLoopingStopMs() {
        assertEquals(DEFAULT_LOOPING_STOP_MS, _soundType.defaultLoopingStopMs());
    }

    @Test
    void testDefaultLoopingRestartMs() {
        assertEquals(DEFAULT_LOOPING_RESTART_MS, _soundType.defaultLoopingRestartMs());
    }
}

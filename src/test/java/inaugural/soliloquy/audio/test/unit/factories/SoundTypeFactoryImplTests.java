package inaugural.soliloquy.audio.test.unit.factories;

import inaugural.soliloquy.audio.factories.SoundTypeFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundTypeFactory;

import static inaugural.soliloquy.tools.random.Random.randomIntWithInclusiveFloor;
import static inaugural.soliloquy.tools.random.Random.randomString;
import static org.junit.jupiter.api.Assertions.*;

class SoundTypeFactoryImplTests {
    private final String ID = randomString();
    private final String RELATIVE_PATH = randomString();
    private final int RESTART_MS = randomIntWithInclusiveFloor(1);
    private final int STOP_MS = randomIntWithInclusiveFloor(RESTART_MS);

    private SoundTypeFactory _soundTypeFactory;

    @BeforeEach
    void setUp() {
        _soundTypeFactory = new SoundTypeFactoryImpl();
    }

    @Test
    void testMake() {
        SoundType result = _soundTypeFactory.make(ID, RELATIVE_PATH, STOP_MS, RESTART_MS);

        assertNotNull(result);
        assertEquals(ID, result.id());
        assertEquals(RELATIVE_PATH, result.relativePath());
        assertEquals(STOP_MS, result.defaultLoopingStopMs());
        assertEquals(RESTART_MS, result.defaultLoopingRestartMs());
    }

    @Test
    void testMakeWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(null, RELATIVE_PATH, STOP_MS, RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make("", RELATIVE_PATH, STOP_MS, RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(ID, null, STOP_MS, RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(ID, "", STOP_MS, RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(ID, RELATIVE_PATH, RESTART_MS, RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(ID, RELATIVE_PATH, -STOP_MS, RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(ID, RELATIVE_PATH, STOP_MS, -RESTART_MS));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundTypeFactory.class.getCanonicalName(),
                _soundTypeFactory.getInterfaceName());
    }
}

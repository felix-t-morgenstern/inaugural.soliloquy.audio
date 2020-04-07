package inaugural.soliloquy.audio.test.unit;

import inaugural.soliloquy.audio.SoundTypeFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundTypeFactory;

import static org.junit.jupiter.api.Assertions.*;

class SoundTypeFactoryImplTests {
    private SoundTypeFactory _soundTypeFactory;

    @BeforeEach
    void setUp() {
        _soundTypeFactory = new SoundTypeFactoryImpl();
    }

    @Test
    void testMake() {
        final String id = "id";
        final String path = "path";
        final Integer stopMs = 123;
        final Integer restartMs = 456;

        SoundType result = _soundTypeFactory.make(id, path, stopMs, restartMs);

        assertNotNull(result);
        assertEquals(id, result.id());
        assertEquals(path, result.absolutePath());
        assertEquals(stopMs, result.defaultLoopingStopMs());
        assertEquals(restartMs, result.defaultLoopingRestartMs());
    }

    @Test
    void testMakeWithInvalidParams() {
        final String id = "id";
        final String path = "path";
        final Integer stopMs = 123;
        final Integer restartMs = 456;

        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(null, path, stopMs, restartMs));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make("", path, stopMs, restartMs));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(null, path, stopMs, restartMs));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(id, "", stopMs, restartMs));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(id, path, restartMs, stopMs));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(id, path, restartMs, restartMs));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(id, path, -stopMs, restartMs));
        assertThrows(IllegalArgumentException.class,
                () -> _soundTypeFactory.make(id, path, stopMs, -restartMs));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundTypeFactory.class.getCanonicalName(),
                _soundTypeFactory.getInterfaceName());
    }
}

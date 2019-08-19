package inaugural.soliloquy.audio.test.unit;

import inaugural.soliloquy.audio.SoundTypeImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.SoundType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SoundTypeImplUnitTests {
    private SoundType _soundType;

    private final String ID = "SoundTypeId";
    private final String FILENAME = "SoundTypeFilename";

    @BeforeEach
    void setUp() {
        _soundType = new SoundTypeImpl(ID, FILENAME);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> new SoundTypeImpl(null, FILENAME));
        assertThrows(IllegalArgumentException.class, () -> new SoundTypeImpl("", FILENAME));
        assertThrows(IllegalArgumentException.class, () -> new SoundTypeImpl(ID, null));
        assertThrows(IllegalArgumentException.class, () -> new SoundTypeImpl(ID, ""));
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
    void testFilename() {
        assertEquals(FILENAME, _soundType.filename());
    }
}

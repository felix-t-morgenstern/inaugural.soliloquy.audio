package inaugural.soliloquy.audio.test.unit.persistence;

import com.google.gson.JsonSyntaxException;
import inaugural.soliloquy.audio.persistence.SoundHandler;
import inaugural.soliloquy.audio.test.fakes.FakeSoundFactory;
import inaugural.soliloquy.audio.test.stubs.SoundStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.common.persistence.TypeHandler;

import static org.junit.jupiter.api.Assertions.*;

class SoundHandlerTests {
    private TypeHandler<Sound> _soundHandler;

    private final static String DATA = "{\"uuid\":\"839f1134-3622-493f-ba19-7d7be392cd3b\",\"type\":\"SoundTypeStubId\",\"paused\":true,\"muted\":true,\"vol\":0.5,\"msPos\":100,\"looping\":true}";

    @BeforeEach
    void setUp() {
        _soundHandler = new SoundHandler(new FakeSoundFactory());
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> new SoundHandler(null));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                Sound.class.getCanonicalName() + ">",
                _soundHandler.getInterfaceName());
    }

    @Test
    void testGetArchetype() {
        Sound archetype = _soundHandler.getArchetype();

        assertNotNull(archetype);
        assertEquals(Sound.class.getCanonicalName(), archetype.getInterfaceName());
    }

    @Test
    void testWrite() {
        String writtenValue = _soundHandler.write(new SoundStub());

        assertEquals(DATA, writtenValue);
    }

    @Test
    void testWriteInvalid() {
        assertThrows(IllegalArgumentException.class, () -> _soundHandler.write(null));
    }

    @Test
    void testRead() {
        Sound readValue = _soundHandler.read(DATA);

        assertNotNull(readValue);
        assertEquals(SoundStub.UUID, readValue.uuid());
        assertEquals(SoundStub.IS_PAUSED, readValue.isPaused());
        assertEquals(SoundStub.IS_MUTED, readValue.isMuted());
        assertEquals(SoundStub.IS_LOOPING, readValue.getIsLooping());
        assertEquals(SoundStub.VOLUME, readValue.getVolume());
        assertEquals(SoundStub.MS_POSITION, readValue.getMillisecondPosition());
    }

    @Test
    void testReadInvalid() {
        assertThrows(IllegalArgumentException.class, () -> _soundHandler.read(null));
        assertThrows(IllegalArgumentException.class, () -> _soundHandler.read(""));
        assertThrows(JsonSyntaxException.class, () ->
                _soundHandler.read("{\"soundTypeId\":\"SoundTypeId\",\"isPaused\":true,\"isMuted\":true,\"volume\":0.5,\"msPosition\":100,\"isLooping\":true"));
    }
}

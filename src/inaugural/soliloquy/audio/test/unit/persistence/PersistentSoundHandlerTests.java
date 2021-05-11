package inaugural.soliloquy.audio.test.unit.persistence;

import com.google.gson.JsonSyntaxException;
import inaugural.soliloquy.audio.persistence.PersistentSoundHandler;
import inaugural.soliloquy.audio.test.fakes.FakeEntityUuidFactory;
import inaugural.soliloquy.audio.test.fakes.FakeSoundFactory;
import inaugural.soliloquy.audio.test.stubs.SoundStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;

import static org.junit.jupiter.api.Assertions.*;

class PersistentSoundHandlerTests {
    private final EntityUuidFactory ENTITY_UUID_FACTORY = new FakeEntityUuidFactory();

    private PersistentValueTypeHandler<Sound> _persistentSoundHandler;

    private final static String DATA = "{\"id\":\"839f1134-3622-493f-ba19-7d7be392cd3b\",\"type\":\"SoundTypeStubId\",\"paused\":true,\"muted\":true,\"vol\":0.5,\"msPos\":100,\"looping\":true}";

    @BeforeEach
    void setUp() {
        _persistentSoundHandler = new PersistentSoundHandler(new FakeSoundFactory(),
                ENTITY_UUID_FACTORY);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> new PersistentSoundHandler(null,
                ENTITY_UUID_FACTORY));
        assertThrows(IllegalArgumentException.class,
                () -> new PersistentSoundHandler(new FakeSoundFactory(), null));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(PersistentValueTypeHandler.class.getCanonicalName() + "<" +
                Sound.class.getCanonicalName() + ">",
                _persistentSoundHandler.getInterfaceName());
    }

    @Test
    void testGetArchetype() {
        Sound archetype = _persistentSoundHandler.getArchetype();

        assertNotNull(archetype);
        assertEquals(Sound.class.getCanonicalName(), archetype.getInterfaceName());
    }

    @Test
    void testWrite() {
        String writtenValue = _persistentSoundHandler.write(new SoundStub());

        assertEquals(DATA, writtenValue);
    }

    @Test
    void testWriteInvalid() {
        assertThrows(IllegalArgumentException.class, () -> _persistentSoundHandler.write(null));
    }

    @Test
    void testRead() {
        Sound readValue = _persistentSoundHandler.read(DATA);

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
        assertThrows(IllegalArgumentException.class, () -> _persistentSoundHandler.read(null));
        assertThrows(IllegalArgumentException.class, () -> _persistentSoundHandler.read(""));
        assertThrows(JsonSyntaxException.class,
                () -> _persistentSoundHandler.read("{\"soundTypeId\":\"SoundTypeId\",\"isPaused\":true,\"isMuted\":true,\"volume\":0.5,\"msPosition\":100,\"isLooping\":true"));
    }
}

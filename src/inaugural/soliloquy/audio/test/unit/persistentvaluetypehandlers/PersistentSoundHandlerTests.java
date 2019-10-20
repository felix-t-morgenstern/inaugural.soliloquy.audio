package inaugural.soliloquy.audio.test.unit.persistentvaluetypehandlers;

import com.google.gson.JsonSyntaxException;
import inaugural.soliloquy.audio.persistentvaluetypehandlers.PersistentSoundHandler;
import inaugural.soliloquy.audio.test.fakes.FakeEntityUuidFactory;
import inaugural.soliloquy.audio.test.fakes.FakeSoundFactory;
import inaugural.soliloquy.audio.test.stubs.StubSound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.infrastructure.PersistentValueTypeHandler;

import static org.junit.jupiter.api.Assertions.*;

class PersistentSoundHandlerTests {
    private final EntityUuidFactory ENTITY_UUID_FACTORY = new FakeEntityUuidFactory();

    private PersistentValueTypeHandler<Sound> _persistentSoundHandler;

    private final static String DATA = "{\"soundId\":\"839f1134-3622-493f-ba19-7d7be392cd3b\",\"soundTypeId\":\"SoundTypeId\",\"isPaused\":true,\"isMuted\":true,\"volume\":0.5,\"msPosition\":100,\"isLooping\":true}";

    @BeforeEach
    void setUp() {
        _persistentSoundHandler = new PersistentSoundHandler(new FakeSoundFactory(),
                ENTITY_UUID_FACTORY);
    }

    @SuppressWarnings("ConstantConditions")
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
        String writtenValue = _persistentSoundHandler.write(new StubSound());

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
        assertEquals(StubSound.ID, readValue.id());
        assertEquals(StubSound.IS_PAUSED, readValue.isPaused());
        assertEquals(StubSound.IS_MUTED, readValue.isMuted());
        assertEquals(StubSound.IS_LOOPING, readValue.getIsLooping());
        assertEquals(StubSound.VOLUME, readValue.getVolume());
        assertEquals(StubSound.MS_POSITION, readValue.getMillisecondPosition());
    }

    @Test
    void testReadInvalid() {
        assertThrows(IllegalArgumentException.class, () -> _persistentSoundHandler.read(null));
        assertThrows(IllegalArgumentException.class, () -> _persistentSoundHandler.read(""));
        assertThrows(JsonSyntaxException.class,
                () -> _persistentSoundHandler.read("{\"soundTypeId\":\"SoundTypeId\",\"isPaused\":true,\"isMuted\":true,\"volume\":0.5,\"msPosition\":100,\"isLooping\":true"));
    }
}

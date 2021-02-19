package inaugural.soliloquy.audio.test.unit.persistence;

import inaugural.soliloquy.audio.persistence.PersistentSoundsPlayingHandler;
import inaugural.soliloquy.audio.test.fakes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.List;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PersistentSoundsPlayingHandlerTests {
    private PersistentValueTypeHandler<SoundsPlaying> _persistentSoundsPlayingHandler;
    private SoundsPlaying _soundsPlaying;

    private final ArrayList<String> ENTITY_UUIDS = new ArrayList<>(Arrays.asList(
            "654326eb-0adf-49ef-8c43-18209574d635",
            "384d4555-1a11-481f-8e3c-dfdf059bd110",
            "5fa15321-0a5f-4d3b-9a70-9e9ae7f3ce85"));
    private final String DATA = "{\"soundDTOs\":[\"{\\\"id\\\":\\\"654326eb-0adf-49ef-8c43-18209574d635\\\",\\\"type\\\":\\\"SoundTypeStubId\\\",\\\"paused\\\":false,\\\"muted\\\":false,\\\"vol\\\":0.0,\\\"msPos\\\":0,\\\"looping\\\":false}\",\"{\\\"id\\\":\\\"384d4555-1a11-481f-8e3c-dfdf059bd110\\\",\\\"type\\\":\\\"SoundTypeStubId\\\",\\\"paused\\\":false,\\\"muted\\\":false,\\\"vol\\\":0.0,\\\"msPos\\\":0,\\\"looping\\\":false}\",\"{\\\"id\\\":\\\"5fa15321-0a5f-4d3b-9a70-9e9ae7f3ce85\\\",\\\"type\\\":\\\"SoundTypeStubId\\\",\\\"paused\\\":false,\\\"muted\\\":false,\\\"vol\\\":0.0,\\\"msPos\\\":0,\\\"looping\\\":false}\"]}";

    @BeforeEach
    void setUp() {
        _soundsPlaying = new FakeSoundsPlaying();
        _persistentSoundsPlayingHandler = new PersistentSoundsPlayingHandler(
                new FakePersistentSoundHandler(), _soundsPlaying);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new PersistentSoundsPlayingHandler(null, _soundsPlaying));
        assertThrows(IllegalArgumentException.class,
                () -> new PersistentSoundsPlayingHandler(new FakePersistentSoundHandler(), null));
    }

    @Test
    void testWrite() {
        int id = 0;
        for(String entityUuid : ENTITY_UUIDS) {
            FakeSound soundToAdd = new FakeSound(new FakeSoundType("soundType" + id++ + "Id"));
            soundToAdd._id = new FakeEntityUuid(entityUuid);
            _soundsPlaying.registerSound(soundToAdd);
        }

        String writtenValue = _persistentSoundsPlayingHandler.write(_soundsPlaying);

        assertEquals(DATA, writtenValue);
    }

    @Test
    void testRead() {
        FakeSound previouslyPlayingSound = new FakeSound(new FakeSoundType("soundType4Id"));
        previouslyPlayingSound._id = new FakeEntityUuid("f23795c5-32fc-4df7-a936-7722311db17c");
        _soundsPlaying.registerSound(previouslyPlayingSound);

        _persistentSoundsPlayingHandler.read(DATA);

        List<Sound> soundsPlaying = _soundsPlaying.representation();

        assertEquals(3, soundsPlaying.size());
        soundsPlaying.forEach(sp -> assertTrue(ENTITY_UUIDS.contains(sp.id().toString())));
    }

    @Test
    void testReadWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> _persistentSoundsPlayingHandler.read(null));
        assertThrows(IllegalArgumentException.class,
                () -> _persistentSoundsPlayingHandler.read(""));
    }

    @Test
    void testArchetype() {
        assertNotNull(_persistentSoundsPlayingHandler.getArchetype());
        assertEquals(SoundsPlaying.class.getCanonicalName(),
                _persistentSoundsPlayingHandler.getArchetype().getInterfaceName());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(PersistentValueTypeHandler.class.getCanonicalName() + "<" +
                SoundsPlaying.class.getCanonicalName() + ">",
                _persistentSoundsPlayingHandler.getInterfaceName());
    }
}

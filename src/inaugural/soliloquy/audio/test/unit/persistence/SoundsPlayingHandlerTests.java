package inaugural.soliloquy.audio.test.unit.persistence;

import inaugural.soliloquy.audio.persistence.SoundsPlayingHandler;
import inaugural.soliloquy.audio.test.fakes.FakePersistentSoundHandler;
import inaugural.soliloquy.audio.test.fakes.FakeSound;
import inaugural.soliloquy.audio.test.fakes.FakeSoundType;
import inaugural.soliloquy.audio.test.fakes.FakeSoundsPlaying;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.List;
import soliloquy.specs.common.persistence.TypeHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SoundsPlayingHandlerTests {
    private TypeHandler<SoundsPlaying> _soundsPlayingHandler;
    private SoundsPlaying _soundsPlaying;

    private final ArrayList<String> UUIDS = new ArrayList<>(Arrays.asList(
            "654326eb-0adf-49ef-8c43-18209574d635",
            "384d4555-1a11-481f-8e3c-dfdf059bd110",
            "5fa15321-0a5f-4d3b-9a70-9e9ae7f3ce85"));
    private final String DATA =
            "{\"soundDTOs\":[\"{\\\"uuid\\\":\\\"654326eb-0adf-49ef-8c43-18209574d635\\\"," +
                    "\\\"type\\\":\\\"SoundTypeStubId\\\",\\\"paused\\\":false," +
                    "\\\"muted\\\":false,\\\"vol\\\":0.0,\\\"msPos\\\":0," +
                    "\\\"looping\\\":false}\",\"{\\\"uuid\\\":\\\"384d4555-1a11-481f-8e3c" +
                    "-dfdf059bd110\\\",\\\"type\\\":\\\"SoundTypeStubId\\\",\\\"paused\\\":false," +
                    "\\\"muted\\\":false,\\\"vol\\\":0.0,\\\"msPos\\\":0," +
                    "\\\"looping\\\":false}\",\"{\\\"uuid\\\":\\\"5fa15321-0a5f-4d3b-9a70" +
                    "-9e9ae7f3ce85\\\",\\\"type\\\":\\\"SoundTypeStubId\\\",\\\"paused\\\":false," +
                    "\\\"muted\\\":false,\\\"vol\\\":0.0,\\\"msPos\\\":0," +
                    "\\\"looping\\\":false}\"]}";

    @BeforeEach
    void setUp() {
        _soundsPlaying = new FakeSoundsPlaying();
        _soundsPlayingHandler = new SoundsPlayingHandler(
                new FakePersistentSoundHandler(), _soundsPlaying);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new SoundsPlayingHandler(null, _soundsPlaying));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundsPlayingHandler(new FakePersistentSoundHandler(), null));
    }

    @Test
    void testWrite() {
        int id = 0;
        for (String uuid : UUIDS) {
            FakeSound soundToAdd = new FakeSound(new FakeSoundType("soundType" + id++ + "Id"));
            soundToAdd._uuid = UUID.fromString(uuid);
            _soundsPlaying.registerSound(soundToAdd);
        }

        String writtenValue = _soundsPlayingHandler.write(_soundsPlaying);

        assertEquals(DATA, writtenValue);
    }

    @Test
    void testRead() {
        FakeSound previouslyPlayingSound = new FakeSound(new FakeSoundType("soundType4Id"));
        previouslyPlayingSound._uuid = UUID.fromString("f23795c5-32fc-4df7-a936-7722311db17c");
        _soundsPlaying.registerSound(previouslyPlayingSound);

        _soundsPlayingHandler.read(DATA);

        List<Sound> soundsPlaying = _soundsPlaying.representation();

        assertEquals(3, soundsPlaying.size());
        soundsPlaying.forEach(sp -> assertTrue(UUIDS.contains(sp.uuid().toString())));
    }

    @Test
    void testReadWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> _soundsPlayingHandler.read(null));
        assertThrows(IllegalArgumentException.class, () -> _soundsPlayingHandler.read(""));
    }

    @Test
    void testArchetype() {
        assertNotNull(_soundsPlayingHandler.getArchetype());
        assertEquals(SoundsPlaying.class.getCanonicalName(),
                _soundsPlayingHandler.getArchetype().getInterfaceName());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(TypeHandler.class.getCanonicalName() + "<" +
                        SoundsPlaying.class.getCanonicalName() + ">",
                _soundsPlayingHandler.getInterfaceName());
    }
}

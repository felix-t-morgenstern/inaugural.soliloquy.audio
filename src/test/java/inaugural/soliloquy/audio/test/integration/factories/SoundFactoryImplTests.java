package inaugural.soliloquy.audio.test.integration.factories;

import inaugural.soliloquy.audio.test.fakes.FakeSoundType;
import inaugural.soliloquy.audio.test.integration.IntegrationTestsSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SoundFactoryImplTests {
    private final String RELATIVE_PATH = "\\src\\test\\resources\\Kevin_MacLeod_-_Living_Voyage.mp3";

    private IntegrationTestsSetup _setup;
    private SoundFactory _soundFactory;

    @BeforeEach
    void setUp() throws Exception {
        _setup = new IntegrationTestsSetup();

        _soundFactory = _setup.audio().soundFactory();
        _setup.audio().soundTypes().add(_setup.sampleSoundType());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundFactory.class.getCanonicalName(), _soundFactory.getInterfaceName());
    }

    @Test
    void testMake() {
        Sound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

        assertEquals(sound.soundType().id(), IntegrationTestsSetup.SOUND_TYPE_1_ID);
    }

    @Test
    void testMakeWithUuid() {
        _setup.audio().soundTypes().add(new FakeSoundType(RELATIVE_PATH));
        UUID uuid = UUID.randomUUID();

        Sound sound = _soundFactory.make(FakeSoundType.ID, uuid);

        assertEquals(uuid, sound.uuid());
    }

    @Test
    void testMakeWithInvalidSoundTypeId() {
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
        assertThrows(IllegalArgumentException.class,
                () -> _soundFactory.make("InvalidSoundTypeId!"));
    }

    @Test
    void testMakeWithInvalidParams() {
        _setup.audio().soundTypes().add(new FakeSoundType(RELATIVE_PATH));

        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(""));
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(RELATIVE_PATH, null));
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null, UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make("", UUID.randomUUID()));
    }
}

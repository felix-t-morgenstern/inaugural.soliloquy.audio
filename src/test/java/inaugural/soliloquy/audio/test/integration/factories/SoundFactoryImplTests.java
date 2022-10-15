package inaugural.soliloquy.audio.test.integration.factories;

import inaugural.soliloquy.audio.test.integration.IntegrationTestsSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundFactory;

import java.util.UUID;

import static inaugural.soliloquy.tools.random.Random.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SoundFactoryImplTests {
    private final String SOUND_TYPE_ID = randomString();
    private final String RELATIVE_PATH = "\\src\\test\\resources\\Kevin_MacLeod_-_Living_Voyage.mp3";

    @Mock private SoundType mockSoundType;

    private IntegrationTestsSetup setup;
    private SoundFactory soundFactory;

    @BeforeEach
    void setUp() throws Exception {
        setup = new IntegrationTestsSetup();

        mockSoundType = mock(SoundType.class);
        when(mockSoundType.relativePath()).thenReturn(RELATIVE_PATH);
        when(mockSoundType.id()).thenReturn(SOUND_TYPE_ID);
        when(mockSoundType.defaultLoopingRestartMs()).thenReturn(null);
        when(mockSoundType.defaultLoopingStopMs()).thenReturn(null);

        soundFactory = setup.audio().soundFactory();
        setup.audio().soundTypes().add(setup.sampleSoundType());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundFactory.class.getCanonicalName(), soundFactory.getInterfaceName());
    }

    @Test
    void testMake() {
        Sound sound = soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

        assertEquals(sound.soundType().id(), IntegrationTestsSetup.SOUND_TYPE_1_ID);
    }

    @Test
    void testMakeWithUuid() {
        setup.audio().soundTypes().add(mockSoundType);
        UUID uuid = UUID.randomUUID();

        Sound sound = soundFactory.make(SOUND_TYPE_ID, uuid);

        assertEquals(uuid, sound.uuid());
    }

    @Test
    void testMakeWithInvalidSoundTypeId() {
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(null));
        assertThrows(IllegalArgumentException.class,
                () -> soundFactory.make("InvalidSoundTypeId!"));
    }

    @Test
    void testMakeWithInvalidParams() {
        setup.audio().soundTypes().add(mockSoundType);

        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(null));
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(""));
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(RELATIVE_PATH, null));
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make(null, UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class, () -> soundFactory.make("", UUID.randomUUID()));
    }
}

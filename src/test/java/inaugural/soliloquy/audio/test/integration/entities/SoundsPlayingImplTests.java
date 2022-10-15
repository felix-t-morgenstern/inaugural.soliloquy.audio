package inaugural.soliloquy.audio.test.integration.entities;

import inaugural.soliloquy.audio.test.integration.IntegrationTestsSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SoundsPlayingImplTests {
    @Mock private Sound sound1;
    @Mock private Sound sound2;
    @Mock private Sound sound3;
    private SoundsPlaying soundsPlaying;
    private SoundFactory soundFactory;

    @BeforeEach
    void setUp() throws Exception {
        sound1 = mock(Sound.class);
        sound2 = mock(Sound.class);
        sound3 = mock(Sound.class);
        when(sound1.uuid()).thenReturn(UUID.randomUUID());
        when(sound2.uuid()).thenReturn(UUID.randomUUID());
        when(sound3.uuid()).thenReturn(UUID.randomUUID());

        IntegrationTestsSetup setup = new IntegrationTestsSetup();
        soundsPlaying = setup.audio().soundsPlaying();

        soundFactory = setup.audio().soundFactory();
        setup.audio().soundTypes().add(setup.sampleSoundType());
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundsPlaying.class.getCanonicalName(), soundsPlaying.getInterfaceName());
    }

    @Test
    void testRegisterAndRemoveSound() {
        Sound sound = soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

        soundsPlaying.registerSound(sound);

        assertTrue(soundsPlaying.isPlayingSound(sound.uuid()));

        soundsPlaying.removeSound(sound);

        assertFalse(soundsPlaying.isPlayingSound(sound.uuid()));
    }

    @Test
    void testRepresentation() {
        Sound sound = soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

        List<Sound> allSoundsPlaying1 = soundsPlaying.representation();
        List<Sound> allSoundsPlaying2 = soundsPlaying.representation();

        assertNotSame(allSoundsPlaying1, allSoundsPlaying2);
        assertEquals(1, allSoundsPlaying1.size());
        assertTrue(allSoundsPlaying1.contains(sound));
    }

    @Test
    void testGetSoundWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> soundsPlaying.getSound(null));
    }

    @Test
    void testIsPlayingSoundWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> soundsPlaying.isPlayingSound(null));
    }

    @Test
    void testGetSound() {
        Sound soundMade = soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

        Sound soundPlaying = soundsPlaying.getSound(soundMade.uuid());

        assertSame(soundMade, soundPlaying);
    }

    @Test
    void testRegisterAndRemoveWithInvalidParams() {
        assertThrows(IllegalArgumentException.class, () -> soundsPlaying.registerSound(null));
        assertThrows(IllegalArgumentException.class, () -> soundsPlaying.removeSound(null));
    }
}

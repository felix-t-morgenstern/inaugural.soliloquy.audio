package inaugural.soliloquy.audio.test.unit.factories;

import inaugural.soliloquy.audio.factories.SoundFactoryImpl;
import inaugural.soliloquy.audio.test.fakes.FakeRegistry;
import inaugural.soliloquy.audio.test.fakes.FakeSoundType;
import inaugural.soliloquy.audio.test.spydoubles.SoundsPlayingSpyDouble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.infrastructure.Registry;

import java.util.UUID;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class SoundFactoryImplTests {
    private SoundFactoryImpl _soundFactory;

    private final UUID UUID = java.util.UUID.randomUUID();
    private final Supplier<UUID> UUID_FACTORY = () -> UUID;
    private final Registry<SoundType> SOUND_TYPE_REGISTRY = new FakeRegistry<>();
    private final SoundsPlaying SOUNDS_PLAYING = new SoundsPlayingSpyDouble();
    private final String RELATIVE_PATH = "\\src\\test\\resources\\Kevin_MacLeod_-_Living_Voyage.mp3";

    @BeforeEach
    void setUp() {
        _soundFactory = new SoundFactoryImpl(SOUND_TYPE_REGISTRY, SOUNDS_PLAYING, UUID_FACTORY);
    }

    @Test
    void testConstructorWithInvalidParams() {
        assertThrows(IllegalArgumentException.class,
                () -> new SoundFactoryImpl(null, SOUNDS_PLAYING,
                        UUID_FACTORY));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundFactoryImpl(SOUND_TYPE_REGISTRY, null,
                        UUID_FACTORY));
        assertThrows(IllegalArgumentException.class,
                () -> new SoundFactoryImpl(SOUND_TYPE_REGISTRY, SOUNDS_PLAYING,
                        null));
    }

    @Test
    void testGetInterfaceName() {
        assertEquals(SoundFactory.class.getCanonicalName(), _soundFactory.getInterfaceName());
    }

    @Test
    void testMake() {
        SOUND_TYPE_REGISTRY.add(new FakeSoundType(RELATIVE_PATH));

        Sound sound = _soundFactory.make(FakeSoundType.ID);

        // NB: The filename provided to Sound cannot be exposed by Sound without editing its
        // functionality;
        //     it is not responsible in any way for reporting its filename.
        //     Testing this functionality is reserved for behavioral integration testing.

        assertEquals(FakeSoundType.ID, sound.soundType().id());
        assertEquals(RELATIVE_PATH, sound.soundType().relativePath());
        assertEquals(sound.uuid(), UUID);
        assertTrue(sound.getIsLooping());
        assertThrows(IllegalArgumentException.class,
                () -> sound.setLoopingStopMs(FakeSoundType.DEFAULT_LOOPING_RESTART_MS));
        assertThrows(IllegalArgumentException.class,
                () -> sound.setLoopingRestartMs(FakeSoundType.DEFAULT_LOOPING_STOP_MS));
    }

    @Test
    void testMakeWithUuid() {
        SOUND_TYPE_REGISTRY.add(new FakeSoundType(RELATIVE_PATH));
        UUID uuid = java.util.UUID.randomUUID();

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
        SOUND_TYPE_REGISTRY.add(new FakeSoundType(RELATIVE_PATH));

        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(""));
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(RELATIVE_PATH, null));
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null, java.util.UUID.randomUUID()));
        assertThrows(IllegalArgumentException.class, () -> _soundFactory.make("", java.util.UUID.randomUUID()));
    }
}

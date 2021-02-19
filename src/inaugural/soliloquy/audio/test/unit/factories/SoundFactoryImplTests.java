package inaugural.soliloquy.audio.test.unit.factories;

import inaugural.soliloquy.audio.test.fakes.FakeRegistry;
import inaugural.soliloquy.audio.test.fakes.FakeSoundType;
import inaugural.soliloquy.audio.test.stubs.EntityUuidFactoryStub;
import inaugural.soliloquy.audio.test.spydoubles.SoundsPlayingSpyDouble;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inaugural.soliloquy.audio.factories.SoundFactoryImpl;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import inaugural.soliloquy.audio.test.stubs.EntityUuidStub;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.infrastructure.Registry;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SoundFactoryImplTests {
	private SoundFactoryImpl _soundFactory;
	
	private final EntityUuidFactory ENTITY_UUID_FACTORY = new EntityUuidFactoryStub();
	private final Registry<SoundType> SOUND_TYPE_REGISTRY = new FakeRegistry<>();
	private final SoundsPlaying SOUNDS_PLAYING = new SoundsPlayingSpyDouble();

	private static String SoundTypeFilename;
	
    @SuppressWarnings("ConstantConditions")
	@BeforeEach
	void setUp() throws URISyntaxException {
		SoundTypeFilename = new File(String.valueOf(Paths.get(
				getClass().getClassLoader()
						.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toURI())
				.toFile())).getAbsolutePath();
    	_soundFactory = new SoundFactoryImpl(SOUND_TYPE_REGISTRY, SOUNDS_PLAYING, ENTITY_UUID_FACTORY);
    }

    @Test
	void testConstructorWithInvalidParams() {
		assertThrows(IllegalArgumentException.class,
				() -> new SoundFactoryImpl(null, SOUNDS_PLAYING,
						ENTITY_UUID_FACTORY));
		assertThrows(IllegalArgumentException.class,
				() -> new SoundFactoryImpl(SOUND_TYPE_REGISTRY, null,
						ENTITY_UUID_FACTORY));
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
    	SOUND_TYPE_REGISTRY.add(new FakeSoundType(SoundTypeFilename));
    	
    	Sound sound = _soundFactory.make(FakeSoundType.ID);

    	// NB: The filename provided to Sound cannot be exposed by Sound without editing its functionality;
    	//     it is not responsible in any way for reporting its filename.
    	//     Testing this functionality is reserved for behavioral integration testing.

		assertEquals(FakeSoundType.ID, sound.soundType().id());
		assertEquals(SoundTypeFilename, sound.soundType().absolutePath());
		assertEquals(sound.id().getMostSignificantBits(), EntityUuidStub.MOST_SIGNIFICANT_BITS);
		assertTrue(sound.getIsLooping());
		assertThrows(IllegalArgumentException.class,
				() -> sound.setLoopingStopMs(FakeSoundType.DEFAULT_LOOPING_RESTART_MS));
		assertThrows(IllegalArgumentException.class,
				() -> sound.setLoopingRestartMs(FakeSoundType.DEFAULT_LOOPING_STOP_MS));
    }

	@Test
	void testMakeWithId() {
		SOUND_TYPE_REGISTRY.add(new FakeSoundType(SoundTypeFilename));
    	final String entityUuidString = "7272d87f-1443-4ed2-a17f-7ce1120eae19";
    	EntityUuid entityUuid = new inaugural.soliloquy.audio.test.fakes.FakeEntityUuid(entityUuidString);

    	Sound sound = _soundFactory.make(FakeSoundType.ID, entityUuid);

    	assertEquals(entityUuid, sound.id());
	}

    @Test
	void testMakeWithInvalidSoundTypeId() {
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make("InvalidSoundTypeId!"));
    }

    @Test
	void testMakeWithInvalidParams() {
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(""));
		SOUND_TYPE_REGISTRY.add(new FakeSoundType(SoundTypeFilename));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make(SoundTypeFilename, null));
		final String entityUuidString = "7272d87f-1443-4ed2-a17f-7ce1120eae19";
		EntityUuid entityUuid = new inaugural.soliloquy.audio.test.fakes.FakeEntityUuid(entityUuidString);
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null, entityUuid));
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make("", entityUuid));
	}
}

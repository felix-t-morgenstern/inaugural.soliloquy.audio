package inaugural.soliloquy.audio.test.unit;

import inaugural.soliloquy.audio.test.fakes.FakeEntityUuid;
import inaugural.soliloquy.audio.test.unit.stubs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inaugural.soliloquy.audio.SoundFactoryImpl;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.infrastructure.Registry;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SoundFactoryImplUnitTests {
	private SoundFactoryImpl _soundFactory;
	
	private final EntityUuidFactory ENTITY_UUID_FACTORY = new EntityUuidFactoryStub();
	private final Registry<SoundType> SOUND_TYPE_REGISTRY = new RegistryStub<>();
	private final SoundsPlaying SOUNDS_PLAYING = new SoundsPlayingStub();

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
	void testGetInterfaceName() {
		assertEquals(SoundFactory.class.getCanonicalName(), _soundFactory.getInterfaceName());
    }

    @Test
	void testMake() {
    	SOUND_TYPE_REGISTRY.register(new SoundTypeStub(SoundTypeFilename));
    	
    	Sound sound = _soundFactory.make(SoundTypeStub.ID);

    	// NB: The filename provided to Sound cannot be exposed by Sound without editing its functionality;
    	//     it is not responsible in any way for reporting its filename.
    	//     Testing this functionality is reserved for behavioral integration testing.

		assertEquals(SoundTypeStub.ID, sound.soundType().id());
		assertEquals(SoundTypeFilename, sound.soundType().filename());
		assertEquals(sound.id().getMostSignificantBits(), EntityUuidStub.MOST_SIGNIFICANT_BITS);
    }

	@Test
	void testMakeWithId() {
		SOUND_TYPE_REGISTRY.register(new SoundTypeStub(SoundTypeFilename));
    	final String entityUuidString = "7272d87f-1443-4ed2-a17f-7ce1120eae19";
    	EntityUuid entityUuid = new FakeEntityUuid(entityUuidString);

    	Sound sound = _soundFactory.make(SoundTypeStub.ID, entityUuid);

    	assertEquals(entityUuid, sound.id());
	}

    @Test
	void testMakeWithInvalidSoundTypeId() {
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make("InvalidSoundTypeId!"));
    }

    @Test
	void testMakeWithNullEntityUuid() {
		SOUND_TYPE_REGISTRY.register(new SoundTypeStub(SoundTypeFilename));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make(SoundTypeFilename, null));
	}
}

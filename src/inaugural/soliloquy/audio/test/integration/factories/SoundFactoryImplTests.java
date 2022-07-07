package inaugural.soliloquy.audio.test.integration.factories;

import inaugural.soliloquy.audio.test.fakes.FakeSoundType;
import inaugural.soliloquy.audio.test.integration.IntegrationTestsSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SoundFactoryImplTests {
	private IntegrationTestsSetup _setup;
	private SoundFactory _soundFactory;

	private static String SoundTypeFilename;
	
    @BeforeEach
	void setUp() throws Exception
    {
    	_setup = new IntegrationTestsSetup();
    	
    	_soundFactory = _setup.audio().soundFactory();
    	_setup.audio().soundTypes().add(_setup.sampleSoundType());

		SoundTypeFilename = new File(String.valueOf(Paths.get(
				getClass().getClassLoader()
						.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toURI())
				.toFile())).getAbsolutePath();
    }
    
    @Test
	void testGetInterfaceName()
    {
		assertEquals(SoundFactory.class.getCanonicalName(), _soundFactory.getInterfaceName());
    }

    @Test
	void testMake()
    {
    	Sound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);

		assertEquals(sound.soundType().id(), IntegrationTestsSetup.SOUND_TYPE_1_ID);
    }

	@Test
	void testMakeWithUuid() {
		_setup.audio().soundTypes().add(new FakeSoundType(SoundTypeFilename));
		final String uuidString = "7272d87f-1443-4ed2-a17f-7ce1120eae19";
		UUID uuid = UUID.fromString(uuidString);

		Sound sound = _soundFactory.make(FakeSoundType.ID, uuid);

		assertEquals(uuid, sound.uuid());
	}

    @Test
	void testMakeWithInvalidSoundTypeId()
    {
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make("InvalidSoundTypeId!"));
    }

	@Test
	void testMakeWithInvalidParams() {
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(""));
		_setup.audio().soundTypes().add(new FakeSoundType(SoundTypeFilename));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make(SoundTypeFilename, null));
		final String uuidString = "7272d87f-1443-4ed2-a17f-7ce1120eae19";
		UUID uuid = UUID.fromString(uuidString);
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null, uuid));
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make("", uuid));
	}
}

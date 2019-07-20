package inaugural.soliloquy.audio.test.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inaugural.soliloquy.audio.SoundFactoryImpl;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidFactoryStub;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundTypeFilenameMapStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundTypeFilenamesMapStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundsPlayingStub;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.infrastructure.Map;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class SoundFactoryImplUnitTests {
	private SoundFactoryImpl _soundFactory;
	
	private final EntityUuidFactory ENTITY_UUID_FACTORY = new EntityUuidFactoryStub();
	private final Map<String,String> SOUND_TYPE_FILENAMES = new SoundTypeFilenameMapStub();
	private final SoundsPlaying SOUNDS_PLAYING = new SoundsPlayingStub();

	public static String SoundTypeFilenameSearched;
	public static String SoundTypeFilenameReturned;
	public static Sound SoundRegistered;
	private static String SoundType1Filename;
	
	private final static String SOUND_TYPE_1_ID = "SoundType1Id";
	
    @BeforeEach
	void setUp() throws URISyntaxException {
    	SoundTypeFilenameSearched = "";
    	SoundTypeFilenameReturned = "";
    	SoundRegistered = null;
    	SoundType1Filename = new File(String.valueOf(Paths.get(
				getClass().getClassLoader()
						.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toURI())
				.toFile())).getAbsolutePath();
    	_soundFactory = new SoundFactoryImpl(SOUND_TYPE_FILENAMES, SOUNDS_PLAYING, ENTITY_UUID_FACTORY);
    }
    
    @Test
	void testGetInterfaceName() {
		assertEquals(SoundFactory.class.getCanonicalName(), _soundFactory.getInterfaceName());
    }

    @Test
	void testMake() {
    	_soundFactory.registerSoundTypes(new SoundTypeFilenamesMapStub(SOUND_TYPE_1_ID, SoundType1Filename));
    	
    	Sound sound = _soundFactory.make(SOUND_TYPE_1_ID);

    	// NB: The filename provided to Sound cannot be exposed by Sound without editing its functionality;
    	//     it is not responsible in any way for reporting its filename.
    	//     Testing this functionality is reserved for behavioral integration testing.

		assertEquals(SoundTypeFilenameSearched, SOUND_TYPE_1_ID);
		assertEquals(SoundTypeFilenameReturned, SoundType1Filename);
		assertSame(SoundRegistered, sound);
		assertEquals(sound.id().getMostSignificantBits(), EntityUuidStub.MOST_SIGNIFICANT_BITS);
		assertEquals(sound.soundTypeId(), SOUND_TYPE_1_ID);
    }

    @Test
	void testMakeWithInvalidSoundTypeId() {
		assertThrows(IllegalArgumentException.class, () -> _soundFactory.make(null));
		assertThrows(IllegalArgumentException.class,
				() -> _soundFactory.make("InvalidSoundTypeId!"));
    }
}
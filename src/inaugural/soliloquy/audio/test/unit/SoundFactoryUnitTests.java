package inaugural.soliloquy.audio.test.unit;

import inaugural.soliloquy.audio.SoundFactory;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidFactoryStub;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundTypeFilenameMapStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundTypeFilenamesMapStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundsPlayingStub;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.IEntityUuidFactory;
import soliloquy.common.specs.IMap;

public abstract class SoundFactoryUnitTests extends TestCase {
	private SoundFactory _soundFactory;
	
	private final IEntityUuidFactory ENTITY_UUID_FACTORY = new EntityUuidFactoryStub();
	private final IMap<String,String> SOUND_TYPE_FILENAMES = new SoundTypeFilenameMapStub();
	private final ISoundsPlaying SOUNDS_PLAYING = new SoundsPlayingStub();

	public static String SoundTypeFilenameSearched;
	public static String SoundTypeFilenameReturned;
	public static ISound SoundRegistered;
	
	private final static String SOUND_TYPE_1_ID = "SoundType1Id";
	private final static String SOUND_TYPE_1_FILENAME = SoundFactoryUnitTests.class.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toString();
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    protected SoundFactoryUnitTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    static Test suite()
    {
        return new TestSuite( SoundFactoryUnitTests.class );
    }
    
    @Override
    protected void setUp() throws Exception
    {
    	SoundTypeFilenameSearched = "";
    	SoundTypeFilenameReturned = "";
    	SoundRegistered = null;
    	_soundFactory = new SoundFactory(SOUND_TYPE_FILENAMES, SOUNDS_PLAYING, ENTITY_UUID_FACTORY);
    }
    
    public void testGetInterfaceName()
    {
    	assertTrue("soliloquy.audio.specs.ISoundFactory".equals(_soundFactory.getInterfaceName()));
    }
    
    public void testMake()
    {
    	_soundFactory.registerSoundTypes(new SoundTypeFilenamesMapStub(SOUND_TYPE_1_ID, SOUND_TYPE_1_FILENAME));
    	
    	ISound sound = _soundFactory.make(SOUND_TYPE_1_ID);

    	// NB: The filename provided to Sound cannot be exposed by Sound without editing its functionality;
    	//     it is not responsible in any way for reporting its filename.
    	//     Testing this functionality is reserved for behavioral integration testing.
    	
    	assertTrue(SoundTypeFilenameSearched.equals(SOUND_TYPE_1_ID));
    	assertTrue(SoundTypeFilenameReturned.equals(SOUND_TYPE_1_FILENAME));
    	assertTrue(SoundRegistered == sound);
    	assertTrue(sound.id().getMostSignificantBits() == EntityUuidStub.MOST_SIGNIFICANT_BITS);
    	assertTrue(sound.soundTypeId().equals(SOUND_TYPE_1_ID));
    }
    
    public void testMakeWithInvalidSoundTypeId()
    {
    	try
    	{
    		_soundFactory.make(null);
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_soundFactory.make("InvalidSoundTypeId!");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    }
}

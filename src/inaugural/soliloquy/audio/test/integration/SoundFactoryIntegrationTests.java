package inaugural.soliloquy.audio.test.integration;

import inaugural.soliloquy.audio.test.unit.SoundFactoryUnitTests;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.common.specs.IMap;

public class SoundFactoryIntegrationTests extends TestCase {
	private ISoundFactory _soundFactory;
	
	private IMap<String,String> _soundTypeFilenameMappings;
	
	private final static String SOUND_TYPE_1_ID = "SoundType1Id";
	private final static String SOUND_TYPE_1_FILENAME = SoundFactoryUnitTests.class.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toString();
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SoundFactoryIntegrationTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SoundFactoryIntegrationTests.class );
    }
    
    @Override
    protected void setUp() throws Exception
    {
    	IntegrationTestsSetup setup = new IntegrationTestsSetup();
    	_soundFactory = setup.audio().soundFactory();
    	
    	_soundTypeFilenameMappings = setup.mapFactory().make("", "");
    	_soundTypeFilenameMappings.put(SOUND_TYPE_1_ID, SOUND_TYPE_1_FILENAME);
    }
    
    public void testGetInterfaceName()
    {
    	assertTrue("soliloquy.audio.specs.ISoundFactory".equals(_soundFactory.getInterfaceName()));
    }
    
    public void testMake()
    {
    	_soundFactory.registerSoundTypes(_soundTypeFilenameMappings);
    	
    	ISound sound = _soundFactory.make(SOUND_TYPE_1_ID);
    	
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

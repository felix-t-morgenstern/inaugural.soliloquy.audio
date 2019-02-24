package inaugural.soliloquy.audio.test.integration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.common.specs.IMap;

public class SoundFactoryIntegrationTests extends TestCase {
	private ISoundFactory _soundFactory;
	
	private IMap<String,String> _soundTypeFilenameMappings;
	
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
    	
    	_soundTypeFilenameMappings = setup.sampleSoundTypeFilenameMappings();
    }
    
    public void testGetInterfaceName()
    {
    	assertTrue("soliloquy.audio.specs.ISoundFactory".equals(_soundFactory.getInterfaceName()));
    }
    
    public void testMake()
    {
    	_soundFactory.registerSoundTypes(_soundTypeFilenameMappings);
    	
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	assertTrue(sound.soundTypeId().equals(IntegrationTestsSetup.SOUND_TYPE_1_ID));
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

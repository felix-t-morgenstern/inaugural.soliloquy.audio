package inaugural.soliloquy.audio.test.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.common.specs.IMap;

public class SoundFactoryIntegrationTests {
	private ISoundFactory _soundFactory;
	
	private IMap<String,String> _soundTypeFilenameMappings;
	
    @BeforeEach
    protected void setUp() throws Exception
    {
    	IntegrationTestsSetup setup = new IntegrationTestsSetup();
    	
    	_soundFactory = setup.audio().soundFactory();
    	
    	_soundTypeFilenameMappings = setup.sampleSoundTypeFilenameMappings();
    }
    
    @Test
    public void testGetInterfaceName()
    {
    	assertTrue("soliloquy.audio.specs.ISoundFactory".equals(_soundFactory.getInterfaceName()));
    }

    @Test
    public void testMake()
    {
    	_soundFactory.registerSoundTypes(_soundTypeFilenameMappings);
    	
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	assertTrue(sound.soundTypeId().equals(IntegrationTestsSetup.SOUND_TYPE_1_ID));
    }

    @Test
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

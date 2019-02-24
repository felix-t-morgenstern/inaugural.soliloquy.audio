package inaugural.soliloquy.audio.test.integration;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.ICollection;

public class SoundsPlayingIntegrationTests extends TestCase {
	private ISoundsPlaying _soundsPlaying;
	private ISoundFactory _soundFactory;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SoundsPlayingIntegrationTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SoundsPlayingIntegrationTests.class );
    }
    
    @Override
    protected void setUp() throws Exception
    {
    	IntegrationTestsSetup setup = new IntegrationTestsSetup();
    	_soundsPlaying = setup.audio().soundsPlaying();
    	
    	_soundFactory = setup.audio().soundFactory();
    	_soundFactory.registerSoundTypes(setup.sampleSoundTypeFilenameMappings());
    }
    
    public void testGetInterfaceName()
    {
    	assertTrue(_soundsPlaying.getInterfaceName().equals("soliloquy.audio.specs.ISoundsPlaying"));
    }
    
    public void testRegisterAndRemoveSound()
    {
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	_soundsPlaying.registerSound(sound);
    	
    	assertTrue(_soundsPlaying.isPlayingSound(sound.id()));
    	
    	_soundsPlaying.removeSound(sound);

    	assertTrue(!_soundsPlaying.isPlayingSound(sound.id()));
    }
    
    public void testAllSoundsPlaying()
    {
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	ICollection<ISound> allSoundsPlaying1 = _soundsPlaying.allSoundsPlaying();
    	ICollection<ISound> allSoundsPlaying2 = _soundsPlaying.allSoundsPlaying();
    	
    	assertTrue(allSoundsPlaying1 != allSoundsPlaying2);
    	assertTrue(allSoundsPlaying1.size() == 1);
    	assertTrue(allSoundsPlaying1.contains(sound));
    }
    
    public void testGetSoundWithNullId()
    {
    	try
    	{
    		_soundsPlaying.getSound(null);
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
    
    public void testIsPlayingSoundWithNullId()
    {
    	try
    	{
    		_soundsPlaying.isPlayingSound(null);
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
    
    public void testGetSound()
    {
    	ISound soundMade = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	ISound soundPlaying = _soundsPlaying.getSound(soundMade.id());
    	
    	assertTrue(soundMade == soundPlaying);
    }
}

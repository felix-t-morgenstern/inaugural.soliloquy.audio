package inaugural.soliloquy.audio.test.integration;

import inaugural.soliloquy.audio.test.unit.SoundFactoryUnitTests;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.IMap;

public class SoundIntegrationTests extends TestCase {
	private ISound _sound;
	private ISoundsPlaying _soundsPlaying;
	
	private IMap<String,String> _soundTypeFilenameMappings;
	
	private final static String SOUND_TYPE_1_ID = "SoundType1Id";
	private final static String SOUND_TYPE_1_FILENAME = SoundFactoryUnitTests.class.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toString();
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SoundIntegrationTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SoundIntegrationTests.class );
    }
    
    @Override
    protected void setUp() throws Exception
    {
    	IntegrationTestsSetup setup = new IntegrationTestsSetup();
    	
    	_soundsPlaying = setup.audio().soundsPlaying();
    	
    	_soundTypeFilenameMappings = setup.mapFactory().make("", "");
    	_soundTypeFilenameMappings.put(SOUND_TYPE_1_ID, SOUND_TYPE_1_FILENAME);
    	
    	ISoundFactory soundFactory = setup.audio().soundFactory();
    	soundFactory.registerSoundTypes(_soundTypeFilenameMappings);
    	
    	_sound = soundFactory.make(SOUND_TYPE_1_ID);
    }
    
    public void testGetInterfaceName()
    {
    	assertTrue(_sound.getInterfaceName().equals("soliloquy.audio.specs.ISound"));
    }
    
    public void testIsPaused()
    {
    	assertTrue(_sound.isPaused());
    	
    	_sound.play();
    	
    	assertTrue(!_sound.isPaused());
    	
    	_sound.pause();
    	
    	assertTrue(_sound.isPaused());
    	
    	_sound.stop();
    	
    	assertTrue(!_sound.isPaused());
    }
    
    public void testIsPlaying()
    {
    	assertTrue(!_sound.isPlaying());
    	
    	_sound.play();
    	
    	assertTrue(_sound.isPlaying());
    	
    	_sound.pause();
    	
    	assertTrue(!_sound.isPlaying());
    	
    	_sound.play();
    	
    	assertTrue(_sound.isPlaying());
    	
    	_sound.stop();
    	
    	assertTrue(!_sound.isPlaying());
    }
    
    public void testIsMuted()
    {
    	assertTrue(!_sound.isMuted());
    	
    	_sound.mute();
    	
    	assertTrue(_sound.isMuted());
    	
    	_sound.unmute();
    	
    	assertTrue(!_sound.isMuted());
    }
    
    public void testIsStopped()
    {
    	assertTrue(!_sound.isStopped());
    	
    	_sound.play();

    	assertTrue(!_sound.isStopped());
    	
    	_sound.mute();

    	assertTrue(!_sound.isStopped());
    	
    	_sound.stop();

    	assertTrue(_sound.isStopped());
    }
    
    public void testGetVolume()
    {
    	assertTrue(_sound.getVolume() == 1.0);
    	
    	_sound.setVolume(0.5);

    	assertTrue(_sound.getVolume() == 0.5);
    	
    	_sound.mute();

    	assertTrue(_sound.getVolume() == 0.5);
    }
    
    public void testGetMillisecondLength()
    {    	
    	int millisecondLength = -1;
		try
		{
			millisecondLength = _sound.getMillisecondLength();
		}
		catch (InterruptedException e)
		{
			assertTrue(false);
		}
    	
    	assertTrue(millisecondLength == 208174);
    }
    
    public void testGetMillisecondPosition() throws InterruptedException
    {
    	final int timeToWait = 500;
    	
    	assertTrue(_sound.getMillisecondPosition() == 0);
    	
    	_sound.setVolume(0.0);
    	_sound.play();
    	Thread.sleep(timeToWait);
    	_sound.pause();
    	int msPosition = _sound.getMillisecondPosition();
    	// NB: When the Sound is not playing, this method should always return the same value.
    	int msPosition2 = _sound.getMillisecondPosition();
    	
    	// NB: At present, there is some delay between when _sound.pause() is called, and when the Sound actually successfully pauses
    	assertTrue(Math.abs(timeToWait-msPosition) <= 250);
    	assertTrue(msPosition == msPosition2);
    }
    
    public void testIsLooping()
    {
    	_sound.setVolume(0.0);
    	
    	assertTrue(!_sound.getIsLooping());
    	
    	_sound.setIsLooping(true);
    	
    	assertTrue(_sound.getIsLooping());
    }
    
    public void testStopRemovesSoundFromSoundsPlaying()
    {
    	assertTrue(_soundsPlaying.isPlayingSound(_sound.id()));
    	
    	_sound.stop();
    	
    	assertTrue(!_soundsPlaying.isPlayingSound(_sound.id()));
    }
    
    public void testOperationsOnStoppedSound()
    {
    	_sound.stop();
    	
    	try
    	{
    		_sound.play();
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_sound.pause();
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_sound.mute();
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_sound.unmute();
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_sound.setIsLooping(true);
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_sound.getMillisecondPosition();
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_sound.getVolume();
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_sound.setVolume(0.0);
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    	
    	try
    	{
    		_sound.getIsLooping();
    		assertTrue(false);
    	}
    	catch(UnsupportedOperationException e)
    	{
    		assertTrue(true);
    	}
    	catch(Exception e)
    	{
    		assertTrue(false);
    	}
    }
}

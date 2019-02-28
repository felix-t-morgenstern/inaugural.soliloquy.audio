package inaugural.soliloquy.audio.test.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.ICollection;

public class SoundsPlayingIntegrationTests {
	private ISoundsPlaying _soundsPlaying;
	private ISoundFactory _soundFactory;
	
    @BeforeEach
    protected void setUp() throws Exception
    {
    	IntegrationTestsSetup setup = new IntegrationTestsSetup();
    	_soundsPlaying = setup.audio().soundsPlaying();
    	
    	_soundFactory = setup.audio().soundFactory();
    	_soundFactory.registerSoundTypes(setup.sampleSoundTypeFilenameMappings());
    }
    
    @Test
    public void testGetInterfaceName()
    {
    	assertTrue(_soundsPlaying.getInterfaceName().equals("soliloquy.audio.specs.ISoundsPlaying"));
    }

    @Test
    public void testRegisterAndRemoveSound()
    {
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	_soundsPlaying.registerSound(sound);
    	
    	assertTrue(_soundsPlaying.isPlayingSound(sound.id()));
    	
    	_soundsPlaying.removeSound(sound);

    	assertTrue(!_soundsPlaying.isPlayingSound(sound.id()));
    }

    @Test
    public void testAllSoundsPlaying()
    {
    	ISound sound = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	ICollection<ISound> allSoundsPlaying1 = _soundsPlaying.allSoundsPlaying();
    	ICollection<ISound> allSoundsPlaying2 = _soundsPlaying.allSoundsPlaying();
    	
    	assertTrue(allSoundsPlaying1 != allSoundsPlaying2);
    	assertTrue(allSoundsPlaying1.size() == 1);
    	assertTrue(allSoundsPlaying1.contains(sound));
    }

    @Test
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

    @Test
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

    @Test
    public void testGetSound()
    {
    	ISound soundMade = _soundFactory.make(IntegrationTestsSetup.SOUND_TYPE_1_ID);
    	
    	ISound soundPlaying = _soundsPlaying.getSound(soundMade.id());
    	
    	assertTrue(soundMade == soundPlaying);
    }
}

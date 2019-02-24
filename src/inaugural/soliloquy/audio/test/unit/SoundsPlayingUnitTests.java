package inaugural.soliloquy.audio.test.unit;

import inaugural.soliloquy.audio.SoundsPlaying;
import inaugural.soliloquy.audio.test.unit.stubs.EntityUuidStub;
import inaugural.soliloquy.audio.test.unit.stubs.MapFactoryStub;
import inaugural.soliloquy.audio.test.unit.stubs.SoundStub;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import soliloquy.audio.specs.ISound;
import soliloquy.common.specs.ICollection;
import soliloquy.common.specs.IEntityUuid;
import soliloquy.common.specs.IMapFactory;

public class SoundsPlayingUnitTests extends TestCase {
	private SoundsPlaying _soundsPlaying;
	
	private final IMapFactory MAP_FACTORY = new MapFactoryStub();
	private final IEntityUuid ENTITY_UUID = new EntityUuidStub();
	private final ISound SOUND_ARCHETYPE = new SoundStub(ENTITY_UUID);
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SoundsPlayingUnitTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SoundsPlayingUnitTests.class );
    }
    
    @Override
    protected void setUp() throws Exception
    {
    	_soundsPlaying = new SoundsPlaying(MAP_FACTORY, ENTITY_UUID, SOUND_ARCHETYPE);
    }
    
    public void testGetInterfaceName()
    {
    	assertTrue(_soundsPlaying.getInterfaceName().equals("soliloquy.audio.specs.ISoundsPlaying"));
    }
    
    public void testRegisterAndRemoveSound()
    {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	assertTrue(_soundsPlaying.isPlayingSound(SOUND_ARCHETYPE.id()));
    	
    	_soundsPlaying.removeSound(SOUND_ARCHETYPE);

    	assertTrue(!_soundsPlaying.isPlayingSound(SOUND_ARCHETYPE.id()));
    }
    
    public void testAllSoundsPlaying()
    {
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	ICollection<ISound> allSoundsPlaying1 = _soundsPlaying.allSoundsPlaying();
    	ICollection<ISound> allSoundsPlaying2 = _soundsPlaying.allSoundsPlaying();
    	
    	assertTrue(allSoundsPlaying1 != allSoundsPlaying2);
    	assertTrue(allSoundsPlaying1.size() == 1);
    	assertTrue(allSoundsPlaying1.contains(SOUND_ARCHETYPE));
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
    	_soundsPlaying.registerSound(SOUND_ARCHETYPE);
    	
    	ISound sound = _soundsPlaying.getSound(SOUND_ARCHETYPE.id());
    	
    	assertTrue(sound == SOUND_ARCHETYPE);
    }
}

package inaugural.soliloquy.audio.test;

import java.util.Iterator;

import inaugural.soliloquy.audio.SoundFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.ICollection;
import soliloquy.common.specs.IEntityUuid;
import soliloquy.common.specs.IEntityUuidFactory;
import soliloquy.common.specs.IFunction;
import soliloquy.common.specs.IMap;
import soliloquy.common.specs.IPair;

public class SoundFactoryTests extends TestCase {
	private SoundFactory _soundFactory;
	
	private final IEntityUuidFactory ENTITY_UUID_FACTORY = new EntityUuidFactoryStub();
	private final IMap<String,String> SOUND_TYPE_FILENAMES = new SoundTypeFilenameMapStub();
	private final ISoundsPlaying SOUNDS_PLAYING = new SoundsPlayingStub();

	private static String SoundTypeFilenameSearched;
	private static String SoundTypeFilenameReturned;
	
	private final static String SOUND_TYPE_1_ID = "SoundType1Id";
	private final static String SOUND_TYPE_1_FILENAME = "SoundType1Filename";
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SoundFactoryTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SoundFactoryTests.class );
    }
    
    @Override
    protected void setUp() throws Exception
    {
    	SoundTypeFilenameSearched = "";
    	SoundTypeFilenameReturned = "";
    	_soundFactory = new SoundFactory(SOUND_TYPE_FILENAMES, SOUNDS_PLAYING, ENTITY_UUID_FACTORY);
    }
    
    public void testGetInterfaceName()
    {
    	assertTrue("soliloquy.audio.specs.ISoundFactory".equals(_soundFactory.getInterfaceName()));
    }
    
    public void testMake()
    {
    	ISound sound = _soundFactory.make(SOUND_TYPE_1_ID);

    	// NB: The filename provided to Sound cannot be exposed by Sound without editing its functionality;
    	//     it is not responsible in any way for reporting its filename.
    	//     Testing this functionality is reserved for behavioral integration testing.
    	
    	assertTrue(SoundTypeFilenameSearched.equals(SOUND_TYPE_1_ID));
    	assertTrue(SoundTypeFilenameReturned.equals(SOUND_TYPE_1_FILENAME));
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
    
    public class EntityUuidFactoryStub implements IEntityUuidFactory
    {

		public String getInterfaceName() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public IEntityUuid createFromLongs(long mostSignificantBits, long leastSignificantBits) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public IEntityUuid createFromString(String uuidString) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public IEntityUuid createRandomEntityUuid() {
			return new EntityUuidStub();
		}
    }
    
    public class EntityUuidStub implements IEntityUuid
    {
    	public final static long MOST_SIGNIFICANT_BITS = 7485613498651L;

		public String getInterfaceName() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public long getMostSignificantBits() {
			return MOST_SIGNIFICANT_BITS;
		}

		public long getLeastSignificantBits() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
			
		}
    }
    
    public class SoundTypeFilenameMapStub implements IMap<String,String>
    {

		public Iterator<IPair<String, String>> iterator() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public String getFirstArchetype() throws IllegalStateException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public String getSecondArchetype() throws IllegalStateException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public String getUnparameterizedInterfaceName() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public String getInterfaceName() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public IMap<String, String> makeClone() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public void clear() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public boolean containsKey(String key) {
			if (key.equals(SoundFactoryTests.SOUND_TYPE_1_ID))
			{
				return true;
			}
			return false;
		}

		public boolean containsValue(String value) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public boolean contains(IPair<String, String> item) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public boolean equals(ICollection<String> items) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public boolean equals(IMap<String, String> map) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public String get(String key) throws IllegalArgumentException, IllegalStateException {
			if (key.equals(SoundFactoryTests.SOUND_TYPE_1_ID))
			{
				SoundFactoryTests.SoundTypeFilenameSearched = SOUND_TYPE_1_ID;
				SoundFactoryTests.SoundTypeFilenameReturned = SOUND_TYPE_1_FILENAME;
				return SoundFactoryTests.SOUND_TYPE_1_FILENAME;
			}
			return null;
		}

		public ICollection<String> getKeys() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public ICollection<String> getValues() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public ICollection<String> indicesOf(String item) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public boolean isEmpty() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public boolean itemExists(String key) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public void put(String key, String value) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public void putAll(ICollection<IPair<String, String>> items) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public String removeByKey(String key) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public boolean removeByKeyAndValue(String key, String value) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public void setValidator(IFunction<IPair<String, String>, String> validator) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public int size() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}
    }
    
    private class SoundsPlayingStub implements ISoundsPlaying
    {

		public String getInterfaceName() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public ICollection<ISound> allSoundsPlaying() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public boolean isPlayingSound(IEntityUuid soundId) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		public ISound getSound(IEntityUuid soundId) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}
    }
}

package inaugural.soliloquy.audio.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import inaugural.soliloquy.audio.SoundFactory;
import inaugural.soliloquy.audio.test.stubs.EntityUuidFactoryStub;
import inaugural.soliloquy.audio.test.stubs.EntityUuidStub;
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
	private static ISound SoundRegistered;
	
	private final static String SOUND_TYPE_1_ID = "SoundType1Id";
	private final static String SOUND_TYPE_1_FILENAME = SoundFactoryTests.class.getResource("Kevin_MacLeod_-_Living_Voyage.mp3").toString();
	
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
    	SoundRegistered = null;
    	_soundFactory = new SoundFactory(SOUND_TYPE_FILENAMES, SOUNDS_PLAYING, ENTITY_UUID_FACTORY);
    }
    
    public void testGetInterfaceName()
    {
    	assertTrue("soliloquy.audio.specs.ISoundFactory".equals(_soundFactory.getInterfaceName()));
    }
    
    public void testMake()
    {
    	_soundFactory.registerSounds(new SoundTypeFilenamesMapStub());
    	
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
    
    public class SoundTypeFilenameMapStub implements IMap<String,String>
    {
    	private HashMap<String,String> _mappings = new HashMap<String,String>();

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
			return _mappings.containsKey(key);
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
			SoundTypeFilenameSearched = key;
			SoundTypeFilenameReturned = _mappings.get(key);
			return _mappings.get(key);
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
			_mappings.put(key, value);
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
    
    private class SoundTypeFilenamesMapStub implements IMap<String,String>
    {
    	private HashMap<String,String> _mappings;
    	
    	SoundTypeFilenamesMapStub()
    	{
    		_mappings = new HashMap<String,String>();
    		
    		_mappings.put(SOUND_TYPE_1_ID, SOUND_TYPE_1_FILENAME);
    	}
    	
		@Override
		public Iterator<IPair<String, String>> iterator() {
			return new SoundTypeFilenamesMapStubIterator(_mappings);
		}

		@Override
		public String getFirstArchetype() throws IllegalStateException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public String getSecondArchetype() throws IllegalStateException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public String getUnparameterizedInterfaceName() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public String getInterfaceName() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public IMap<String, String> makeClone() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public void clear() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsKey(String key) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean containsValue(String value) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean contains(IPair<String, String> item) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean equals(ICollection<String> items) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean equals(IMap<String, String> map) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public String get(String key) throws IllegalArgumentException, IllegalStateException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public ICollection<String> getKeys() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public ICollection<String> getValues() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public ICollection<String> indicesOf(String item) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean isEmpty() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean itemExists(String key) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public void put(String key, String value) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public void putAll(ICollection<IPair<String, String>> items) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public String removeByKey(String key) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean removeByKeyAndValue(String key, String value) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public void setValidator(IFunction<IPair<String, String>, String> validator) {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public int size() {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}
    	
		private class SoundTypeFilenamesMapStubIterator implements Iterator<IPair<String, String>>
		{
			private Iterator<Entry<String,String>> _iterator;
			
			SoundTypeFilenamesMapStubIterator(HashMap<String,String> map)
			{
				_iterator = map.entrySet().iterator();
			}

			@Override
			public boolean hasNext() {
				return _iterator.hasNext();
			}

			@Override
			public IPair<String, String> next() {
				Entry<String,String> entry = _iterator.next();
				return new PairStub(entry.getKey(), entry.getValue());
			}
			
			private class PairStub implements IPair<String,String>
			{
				private String _item1;
				private String _item2;
				
				PairStub(String item1, String item2)
				{
					_item1 = item1;
					_item2 = item2;
				}

				@Override
				public String getFirstArchetype() throws IllegalStateException {
					// Stub class; not implemented
					throw new UnsupportedOperationException();
				}

				@Override
				public String getSecondArchetype() throws IllegalStateException {
					// Stub class; not implemented
					throw new UnsupportedOperationException();
				}

				@Override
				public String getUnparameterizedInterfaceName() {
					// Stub class; not implemented
					throw new UnsupportedOperationException();
				}

				@Override
				public String getInterfaceName() {
					// Stub class; not implemented
					throw new UnsupportedOperationException();
				}

				@Override
				public String getItem1() {
					return _item1;
				}

				@Override
				public void setItem1(String item) throws IllegalArgumentException {
					// Stub class; not implemented
					throw new UnsupportedOperationException();
				}

				@Override
				public String getItem2() {
					return _item2;
				}

				@Override
				public void setItem2(String item) throws IllegalArgumentException {
					// Stub class; not implemented
					throw new UnsupportedOperationException();
				}
				
			}
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

		public void removeSound(ISound sound) throws IllegalArgumentException {
			// Stub class; not implemented
			throw new UnsupportedOperationException();
		}

		@Override
		public void registerSound(ISound sound) throws IllegalArgumentException {
			SoundRegistered = sound;
		}
    }
}

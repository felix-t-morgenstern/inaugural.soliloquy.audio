package inaugural.soliloquy.audio.test.unit.stubs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import soliloquy.specs.common.entities.Function;
import soliloquy.specs.common.infrastructure.*;

public class SoundTypeFilenamesMapStub implements Map<String,String> {
	private HashMap<String,String> _mappings;
	
	public SoundTypeFilenamesMapStub(String soundType1Id, String soundType1Filename) {
		_mappings = new HashMap<>();
		_mappings.put(soundType1Id, soundType1Filename);
	}
	
	@Override
	public Iterator<Pair<String, String>> iterator() {
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
	public Map<String, String> makeClone() {
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
	public boolean contains(Pair<String, String> item) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(ReadableCollection<String> items) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(ReadableMap<String, String> map) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public String get(String key) throws IllegalArgumentException, IllegalStateException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> getKeys() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> getValues() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<String> indicesOf(String item) {
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
	public void putAll(ReadableCollection<Pair<String, String>> items) throws IllegalArgumentException {
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
	public Collection<Function<Pair<String, String>, String>> validators() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public ReadableMap<String, String> readOnlyRepresentation() {
		return null;
	}

	@Override
	public int size() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}
	
	private class SoundTypeFilenamesMapStubIterator implements Iterator<Pair<String, String>> {
		private Iterator<Entry<String,String>> _iterator;
		
		SoundTypeFilenamesMapStubIterator(HashMap<String,String> map) {
			_iterator = map.entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return _iterator.hasNext();
		}

		@Override
		public Pair<String, String> next() {
			Entry<String,String> entry = _iterator.next();
			return new PairStub(entry.getKey(), entry.getValue());
		}
		
		private class PairStub implements Pair<String,String> {
			private String _item1;
			private String _item2;
			
			PairStub(String item1, String item2) {
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

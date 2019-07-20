package inaugural.soliloquy.audio.test.unit.stubs;

import java.util.HashMap;
import java.util.Iterator;

import inaugural.soliloquy.audio.test.unit.SoundFactoryImplUnitTests;
import soliloquy.specs.common.entities.Function;
import soliloquy.specs.common.infrastructure.*;

public class SoundTypeFilenameMapStub implements Map<String,String> {
	private HashMap<String,String> _mappings = new HashMap<>();

	@Override
	public Iterator<Pair<String, String>> iterator() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
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
		return _mappings.containsKey(key);
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
		SoundFactoryImplUnitTests.SoundTypeFilenameSearched = key;
		SoundFactoryImplUnitTests.SoundTypeFilenameReturned = _mappings.get(key);
		return _mappings.get(key);
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
		_mappings.put(key, value);
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
}

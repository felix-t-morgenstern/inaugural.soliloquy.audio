package inaugural.soliloquy.audio.test.unit.stubs;

import java.util.HashMap;
import java.util.Iterator;

import inaugural.soliloquy.audio.test.unit.SoundFactoryUnitTests;
import soliloquy.specs.common.entities.IFunction;
import soliloquy.specs.common.valueobjects.ICollection;
import soliloquy.specs.common.valueobjects.IMap;
import soliloquy.specs.common.valueobjects.IPair;

public class SoundTypeFilenameMapStub implements IMap<String,String> {
	private HashMap<String,String> _mappings = new HashMap<>();

	@Override
	public Iterator<IPair<String, String>> iterator() {
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
		return _mappings.containsKey(key);
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
		SoundFactoryUnitTests.SoundTypeFilenameSearched = key;
		SoundFactoryUnitTests.SoundTypeFilenameReturned = _mappings.get(key);
		return _mappings.get(key);
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
		_mappings.put(key, value);
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
	public ICollection<IFunction<IPair<String, String>, String>> validators() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}
}

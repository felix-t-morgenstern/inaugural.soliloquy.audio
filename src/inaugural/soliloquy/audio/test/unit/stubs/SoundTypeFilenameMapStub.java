package inaugural.soliloquy.audio.test.unit.stubs;

import java.util.HashMap;
import java.util.Iterator;

import inaugural.soliloquy.audio.test.unit.SoundFactoryUnitTests;
import soliloquy.common.specs.ICollection;
import soliloquy.common.specs.IFunction;
import soliloquy.common.specs.IMap;
import soliloquy.common.specs.IPair;

public class SoundTypeFilenameMapStub implements IMap<String,String> {
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
		SoundFactoryUnitTests.SoundTypeFilenameSearched = key;
		SoundFactoryUnitTests.SoundTypeFilenameReturned = _mappings.get(key);
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

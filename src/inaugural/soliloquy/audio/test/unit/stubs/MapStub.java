package inaugural.soliloquy.audio.test.unit.stubs;

import java.util.HashMap;
import java.util.Iterator;

import soliloquy.specs.common.entities.Function;
import soliloquy.specs.common.infrastructure.*;

public class MapStub<K,V> implements Map<K,V> {
	private HashMap<K,V> _map = new HashMap<>();

	@Override
	public Iterator<Pair<K, V>> iterator() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public K getFirstArchetype() throws IllegalStateException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public V getSecondArchetype() throws IllegalStateException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public String getInterfaceName() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<K, V> makeClone() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(K key) {
		return _map.containsKey(key);
	}

	@Override
	public boolean containsValue(V value) {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Pair<K, V> item) throws IllegalArgumentException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(ReadableCollection<V> items) throws IllegalArgumentException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(ReadableMap<K, V> map) throws IllegalArgumentException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(K key) throws IllegalArgumentException, IllegalStateException {
		return _map.get(key);
	}

	@Override
	public Collection<K> getKeys() {
		Collection<K> keys = new CollectionStub<>();
		for (K key : _map.keySet()) {
			keys.add(key);
		}
		return keys;
	}

	@Override
	public Collection<V> getValues() {
		Collection<V> values = new CollectionStub<>();
		for (V value : _map.values()) {
			values.add(value);
		}
		return values;
	}

	@Override
	public Collection<K> indicesOf(V item) {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean itemExists(K key) {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public void put(K key, V value) throws IllegalArgumentException {
		_map.put(key, value);
	}

	@Override
	public void putAll(ReadableCollection<Pair<K, V>> items) throws IllegalArgumentException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public V removeByKey(K key) {
		return _map.remove(key);
	}

	@Override
	public boolean removeByKeyAndValue(K key, V value) {
		return _map.remove(key, value);
	}

	@Override
	public Collection<Function<Pair<K, V>, String>> validators() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public ReadableMap<K, V> readOnlyRepresentation() {
		return null;
	}

	@Override
	public int size() {
		return _map.size();
	}

	@Override
	public String getUnparameterizedInterfaceName() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}
}

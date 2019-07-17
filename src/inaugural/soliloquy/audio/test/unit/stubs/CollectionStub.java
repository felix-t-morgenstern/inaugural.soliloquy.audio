package inaugural.soliloquy.audio.test.unit.stubs;

import java.util.ArrayList;
import java.util.Iterator;

import soliloquy.specs.common.entities.Function;
import soliloquy.specs.common.infrastructure.Collection;
import soliloquy.specs.common.infrastructure.ReadOnlyCollection;

public class CollectionStub<V> implements Collection<V> {
	private ArrayList<V> _collection = new ArrayList<V>();
	private V _archetype;
	
	public CollectionStub() {
	}
	
	private CollectionStub(ArrayList<V> collection) {
		_collection = collection;
	}
	
	public CollectionStub(V archetype) {
		_archetype = archetype;
	}

	@Override
	public Iterator<V> iterator() {
		return _collection.iterator();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> makeClone() {
		return new CollectionStub<V>((ArrayList<V>) _collection.clone());
	}

	@Override
	public V getArchetype() {
		return _archetype;
	}

	@Override
	public String getInterfaceName() {
		return "soliloquy.common.specs.ICollection<" + _archetype.getClass().getCanonicalName() + ">";
	}

	@Override
	public void add(V item) throws UnsupportedOperationException {
		_collection.add(item);
	}

	@Override
	public void addAll(Collection<? extends V> items) throws UnsupportedOperationException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public void addAll(V[] items) throws UnsupportedOperationException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() throws UnsupportedOperationException {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(V item) {
		return _collection.contains(item);
	}

	@Override
	public boolean equals(ReadOnlyCollection<V> readOnlyCollection) {
		return false;
	}

	@Override
	public V get(int index) {
		return _collection.get(index);
	}

	@Override
	public boolean isEmpty() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return _collection.size();
	}

	@Override
	public boolean removeItem(V item) throws UnsupportedOperationException {
		return _collection.remove(item);
	}

	@Override
	public Collection<Function<V, String>> validators() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}

	@Override
	public ReadOnlyCollection<V> readOnlyRepresentation() {
		return null;
	}

	@Override
	public String getUnparameterizedInterfaceName() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}
}

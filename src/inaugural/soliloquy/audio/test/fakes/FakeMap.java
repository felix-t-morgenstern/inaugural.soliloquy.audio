package inaugural.soliloquy.audio.test.fakes;

import java.util.HashMap;
import java.util.Iterator;

import soliloquy.specs.common.entities.Function;
import soliloquy.specs.common.infrastructure.*;

public class FakeMap<K,V> extends HashMap<K,V> implements Map<K,V> {
	public K KeyArchetype;
	public V ValueArchetype;

	public FakeMap() {

	}

	public FakeMap(K keyArchetype, V valueArchetype) {
		KeyArchetype = keyArchetype;
		ValueArchetype = valueArchetype;
	}

	public FakeMap(java.util.Map<K,V> map, K keyArchetype, V valueArchetype) {
		super(map);
		KeyArchetype = keyArchetype;
		ValueArchetype = valueArchetype;
	}

	@Override
	public List<V> getValuesList() {
		return new FakeList<>(values(), ValueArchetype);
	}

	@Override
	public Map<K, V> makeClone() {
		return new FakeMap<>(this, KeyArchetype, ValueArchetype);
	}

	@Override
	public K getFirstArchetype() throws IllegalStateException {
		return KeyArchetype;
	}

	@Override
	public V getSecondArchetype() throws IllegalStateException {
		return ValueArchetype;
	}

	@Override
	public String getInterfaceName() {
		return null;
	}
}

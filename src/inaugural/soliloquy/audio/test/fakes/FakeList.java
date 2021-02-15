package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.common.infrastructure.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class FakeList<V> extends ArrayList<V> implements List<V> {
	public V Archetype;

	FakeList() {
	}

	public FakeList(V archetype) {
		Archetype = archetype;
	}

	public FakeList(Collection<V> items, V archetype) {
		super(items);
		Archetype = archetype;
	}

	public FakeList(V[] items, V archetype) {
		addAll(Arrays.asList(items));
		Archetype = archetype;
	}

	@Override
	public List<V> makeClone() {
		return new FakeList<>(this, Archetype);
	}

	@Override
	public V getArchetype() {
		return Archetype;
	}

	@Override
	public String getInterfaceName() {
		return null;
	}
}

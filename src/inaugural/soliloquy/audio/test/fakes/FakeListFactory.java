package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.common.factories.ListFactory;
import soliloquy.specs.common.infrastructure.List;

import java.util.Collection;

public class FakeListFactory implements ListFactory {
    @Override
    public <V> List<V> make(V v) throws IllegalArgumentException {
        return new FakeList<>(v);
    }

    @Override
    public <V> List<V> make(V[] vs, V v) throws IllegalArgumentException {
        return new FakeList<>(vs, v);
    }

    @Override
    public <V> List<V> make(Collection<V> collection, V v) throws IllegalArgumentException {
        return new FakeList<>(collection, v);
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

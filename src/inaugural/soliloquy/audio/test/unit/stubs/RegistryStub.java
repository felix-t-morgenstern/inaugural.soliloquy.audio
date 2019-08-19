package inaugural.soliloquy.audio.test.unit.stubs;

import soliloquy.specs.common.infrastructure.Registry;
import soliloquy.specs.common.shared.HasId;

import java.util.HashMap;

public class RegistryStub<T extends HasId> implements Registry<T> {
    private final HashMap<String,T> REGISTRY = new HashMap<>();

    @Override
    public boolean contains(String id) {
        return REGISTRY.containsKey(id);
    }

    @Override
    public T get(String id) {
        return REGISTRY.get(id);
    }

    @Override
    public void register(T item) throws IllegalArgumentException {
        REGISTRY.put(item.id(), item);
    }

    @Override
    public boolean remove(String id) {
        return REGISTRY.remove(id) != null;
    }

    @Override
    public T getArchetype() {
        return null;
    }

    @Override
    public String getUnparameterizedInterfaceName() {
        return null;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

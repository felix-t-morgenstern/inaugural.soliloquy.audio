package inaugural.soliloquy.audio.test.unit.stubs;

import soliloquy.specs.common.factories.IMapFactory;
import soliloquy.specs.common.valueobjects.IMap;

public class MapFactoryStub implements IMapFactory {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <K, V> IMap<K, V> make(K archetype1, V archetype2) {
		return new MapStub();
	}    	

	@Override
	public String getInterfaceName() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}
}

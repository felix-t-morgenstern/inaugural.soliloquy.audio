package inaugural.soliloquy.audio.test.unit.stubs;

import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.Map;

public class MapFactoryStub implements MapFactory {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <K, V> Map<K, V> make(K archetype1, V archetype2) {
		return new MapStub();
	}    	

	@Override
	public String getInterfaceName() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}
}

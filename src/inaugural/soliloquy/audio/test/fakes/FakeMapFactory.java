package inaugural.soliloquy.audio.test.fakes;

import inaugural.soliloquy.audio.test.fakes.FakeMap;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.Map;

public class FakeMapFactory implements MapFactory {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <K, V> Map<K, V> make(K archetype1, V archetype2) {
		return new FakeMap();
	}    	

	@Override
	public String getInterfaceName() {
		// Stub method; unimplemented
		throw new UnsupportedOperationException();
	}
}

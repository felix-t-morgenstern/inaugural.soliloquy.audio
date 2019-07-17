package inaugural.soliloquy.audio.test.unit.stubs;

import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class EntityUuidFactoryStub implements EntityUuidFactory {
	public String getInterfaceName() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public EntityUuid createFromLongs(long mostSignificantBits, long leastSignificantBits) {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public EntityUuid createFromString(String uuidString) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public EntityUuid createRandomEntityUuid() {
		return new EntityUuidStub();
	}
}

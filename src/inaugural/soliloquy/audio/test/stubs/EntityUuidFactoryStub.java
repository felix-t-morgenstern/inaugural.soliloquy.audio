package inaugural.soliloquy.audio.test.stubs;

import soliloquy.common.specs.IEntityUuid;
import soliloquy.common.specs.IEntityUuidFactory;

public class EntityUuidFactoryStub implements IEntityUuidFactory
{
	public String getInterfaceName() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public IEntityUuid createFromLongs(long mostSignificantBits, long leastSignificantBits) {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public IEntityUuid createFromString(String uuidString) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public IEntityUuid createRandomEntityUuid() {
		return new EntityUuidStub();
	}
}

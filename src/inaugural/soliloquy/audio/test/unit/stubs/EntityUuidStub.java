package inaugural.soliloquy.audio.test.unit.stubs;

import soliloquy.common.specs.IEntityUuid;

public class EntityUuidStub implements IEntityUuid {
	public final static long MOST_SIGNIFICANT_BITS = 7485613498651L;

	public String getInterfaceName() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public long getMostSignificantBits() {
		return MOST_SIGNIFICANT_BITS;
	}

	public long getLeastSignificantBits() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}
}

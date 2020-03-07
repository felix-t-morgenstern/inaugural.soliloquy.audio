package inaugural.soliloquy.audio.test.stubs;

import soliloquy.specs.common.valueobjects.EntityUuid;

public class EntityUuidStub implements EntityUuid {
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

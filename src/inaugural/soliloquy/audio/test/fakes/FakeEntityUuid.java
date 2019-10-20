package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.common.valueobjects.EntityUuid;

import java.util.UUID;

public class FakeEntityUuid implements EntityUuid {
    private UUID UUID;

    public FakeEntityUuid() {
        UUID = java.util.UUID.randomUUID();
    }

    public FakeEntityUuid(String uuid) {
        UUID = java.util.UUID.fromString(uuid);
    }

    @Override
    public long getMostSignificantBits() {
        return UUID.getMostSignificantBits();
    }

    @Override
    public long getLeastSignificantBits() {
        return UUID.getLeastSignificantBits();
    }

    @Override
    public String toString() {
        return UUID.toString();
    }

    @Override
    public boolean equals(Object comparand) {
        return comparand instanceof EntityUuid
                && UUID.getMostSignificantBits() == ((EntityUuid) comparand).getMostSignificantBits()
                && UUID.getLeastSignificantBits() == ((EntityUuid) comparand).getLeastSignificantBits();
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

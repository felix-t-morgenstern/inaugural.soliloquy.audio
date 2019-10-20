package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class FakeEntityUuidFactory implements EntityUuidFactory {
    @Override
    public EntityUuid createFromLongs(long l, long l1) {
        return null;
    }

    @Override
    public EntityUuid createFromString(String s) throws IllegalArgumentException {
        return new FakeEntityUuid(s);
    }

    @Override
    public EntityUuid createRandomEntityUuid() {
        return new FakeEntityUuid();
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

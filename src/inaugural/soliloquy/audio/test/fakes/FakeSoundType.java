package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.audio.entities.SoundType;

public class FakeSoundType implements SoundType {
    public final static String ID = "SoundTypeStubId";
    public final String FILENAME;

    public FakeSoundType(String filename) {
        FILENAME = filename;
    }

    @Override
    public String filename() {
        return FILENAME;
    }

    @Override
    public String id() throws IllegalStateException {
        return ID;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

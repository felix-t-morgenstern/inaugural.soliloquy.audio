package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.audio.entities.SoundType;

public class FakeSoundType implements SoundType {
    private String _id;

    public FakeSoundType(String soundTypeId) {
        _id = soundTypeId;
    }

    @Override
    public String filename() {
        return null;
    }

    @Override
    public String id() throws IllegalStateException {
        return _id;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

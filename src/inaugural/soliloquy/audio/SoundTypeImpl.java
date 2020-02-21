package inaugural.soliloquy.audio;

import inaugural.soliloquy.common.Check;
import soliloquy.specs.audio.entities.SoundType;

public class SoundTypeImpl implements SoundType {
    private final String ID;
    private final String FILENAME;

    @SuppressWarnings("ConstantConditions")
    public SoundTypeImpl(String id, String filename) {
        ID = Check.ifNullOrEmpty(id, "SoundTypeImpl", null, "id");
        FILENAME = Check.ifNullOrEmpty(filename, "SoundTypeImpl", null, "filename");
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
        return SoundType.class.getCanonicalName();
    }
}

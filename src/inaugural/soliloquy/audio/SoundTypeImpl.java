package inaugural.soliloquy.audio;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.audio.entities.SoundType;

public class SoundTypeImpl implements SoundType {
    private final String ID;
    private final String FILENAME;

    public SoundTypeImpl(String id, String filename) {
        ID = Check.ifNullOrEmpty(id, "id");
        FILENAME = Check.ifNullOrEmpty(filename, "filename");
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

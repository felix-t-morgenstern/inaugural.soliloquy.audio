package inaugural.soliloquy.audio;

import soliloquy.specs.audio.entities.SoundType;

public class SoundTypeImpl implements SoundType {
    private final String ID;
    private final String FILENAME;

    @SuppressWarnings("ConstantConditions")
    public SoundTypeImpl(String id, String filename) {
        if (id == null) {
            throw new IllegalArgumentException("SoundTypeImpl: id must be non-null");
        }
        if (id.equals("")) {
            throw new IllegalArgumentException("SoundTypeImpl: id must be non-empty");
        }
        ID = id;
        if (filename == null) {
            throw new IllegalArgumentException("SoundTypeImpl: filename must be non-null");
        }
        if (filename.equals("")) {
            throw new IllegalArgumentException("SoundTypeImpl: filename must be non-empty");
        }
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
        return SoundType.class.getCanonicalName();
    }
}

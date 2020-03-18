package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.audio.entities.SoundType;

public class FakeSoundType implements SoundType {
    public final static String ID = "SoundTypeStubId";
    public final static Integer DEFAULT_LOOPING_STOP_MS = 456;
    public final static Integer DEFAULT_LOOPING_RESTART_MS = 123;

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
    public Integer defaultLoopingStopMs() throws IllegalStateException {
        return DEFAULT_LOOPING_STOP_MS;
    }

    @Override
    public Integer defaultLoopingRestartMs() throws IllegalStateException {
        return DEFAULT_LOOPING_RESTART_MS;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

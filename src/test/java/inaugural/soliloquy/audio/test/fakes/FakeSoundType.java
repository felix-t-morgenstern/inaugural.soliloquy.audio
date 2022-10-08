package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.audio.entities.SoundType;

public class FakeSoundType implements SoundType {
    public final static String ID = "SoundTypeStubId";
    public final static Integer DEFAULT_LOOPING_STOP_MS = 456;
    public final static Integer DEFAULT_LOOPING_RESTART_MS = 123;

    public final String RELATIVE_PATH;
    private String _id;
    private boolean _defaultLoopingAssigned;
    private Integer _defaultLoopingStopMs;
    private Integer _defaultLoopingRestartMs;

    public FakeSoundType(String absolutePath) {
        RELATIVE_PATH = absolutePath;
    }

    public FakeSoundType(String id, String relativePath, Integer defaultLoopingStopMs,
                         Integer defaultLoopingRestartMs) {
        _id = id;
        RELATIVE_PATH = relativePath;
        _defaultLoopingAssigned = true;
        _defaultLoopingStopMs = defaultLoopingStopMs;
        _defaultLoopingRestartMs = defaultLoopingRestartMs;
    }

    @Override
    public String relativePath() {
        return RELATIVE_PATH;
    }

    @Override
    public String id() throws IllegalStateException {
        return _id == null ? ID : _id;
    }

    @Override
    public Integer defaultLoopingStopMs() throws IllegalStateException {
        return _defaultLoopingAssigned ? _defaultLoopingStopMs : DEFAULT_LOOPING_STOP_MS;
    }

    @Override
    public Integer defaultLoopingRestartMs() throws IllegalStateException {
        return _defaultLoopingAssigned ? _defaultLoopingRestartMs : DEFAULT_LOOPING_RESTART_MS;
    }

    @Override
    public String getInterfaceName() {
        return SoundType.class.getCanonicalName();
    }
}

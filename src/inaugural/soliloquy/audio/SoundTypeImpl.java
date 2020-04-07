package inaugural.soliloquy.audio;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.audio.entities.SoundType;

public class SoundTypeImpl implements SoundType {
    private final String ID;
    private final String ABSOLUTE_PATH;
    private final Integer DEFAULT_LOOPING_STOP_MS;
    private final Integer DEFAULT_LOOPING_RESTART_MS;

    public SoundTypeImpl(String id, String absolutePath, Integer defaultLoopingStopMs,
                         Integer defaultLoopingRestartMs) {
        ID = Check.ifNullOrEmpty(id, "id");
        ABSOLUTE_PATH = Check.ifNullOrEmpty(absolutePath, "absolutePath");
        DEFAULT_LOOPING_STOP_MS = Check.ifNonNegative(defaultLoopingStopMs,
                "defaultLoopingStopMs");
        DEFAULT_LOOPING_RESTART_MS = Check.ifNonNegative(defaultLoopingRestartMs,
                "defaultLoopingRestartMs");
        if (DEFAULT_LOOPING_STOP_MS != null && DEFAULT_LOOPING_RESTART_MS != null
                && DEFAULT_LOOPING_STOP_MS <= DEFAULT_LOOPING_RESTART_MS) {
            throw new IllegalArgumentException(
                    "SoundTypeImpl: if defaultLoopingStopMs and defaultLoopingRestartMs are " +
                            "both specified, defaultLoopingStopMs must be greater");
        }
    }

    @Override
    public String absolutePath() {
        return ABSOLUTE_PATH;
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
        return SoundType.class.getCanonicalName();
    }
}

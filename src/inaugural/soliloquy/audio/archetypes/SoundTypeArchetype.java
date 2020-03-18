package inaugural.soliloquy.audio.archetypes;

import soliloquy.specs.audio.entities.SoundType;

public class SoundTypeArchetype implements SoundType {
    @Override
    public String filename() {
        return null;
    }

    @Override
    public String id() throws IllegalStateException {
        return null;
    }

    @Override
    public Integer defaultLoopingStopMs() throws IllegalStateException {
        return null;
    }

    @Override
    public Integer defaultLoopingRestartMs() throws IllegalStateException {
        return null;
    }

    @Override
    public String getInterfaceName() {
        return SoundType.class.getCanonicalName();
    }
}

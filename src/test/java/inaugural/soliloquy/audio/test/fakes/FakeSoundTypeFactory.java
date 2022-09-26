package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundTypeFactory;

public class FakeSoundTypeFactory implements SoundTypeFactory {
    @Override
    public SoundType make(String id, String absolutePath, Integer defaultLoopingStopMs,
                          Integer defaultLoopingRestartMs) throws IllegalArgumentException {
        return new FakeSoundType(id, absolutePath, defaultLoopingStopMs, defaultLoopingRestartMs);
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

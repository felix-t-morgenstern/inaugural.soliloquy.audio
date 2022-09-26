package inaugural.soliloquy.audio.factories;

import inaugural.soliloquy.audio.entities.SoundTypeImpl;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundTypeFactory;

public class SoundTypeFactoryImpl implements SoundTypeFactory {
    @Override
    public SoundType make(String id, String absolutePath, Integer defaultLoopingStopMs,
                          Integer defaultLoopingRestartMs) throws IllegalArgumentException {
        return new SoundTypeImpl(id, absolutePath, defaultLoopingStopMs, defaultLoopingRestartMs);
    }

    @Override
    public String getInterfaceName() {
        return SoundTypeFactory.class.getCanonicalName();
    }
}

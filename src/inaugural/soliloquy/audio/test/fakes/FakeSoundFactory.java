package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class FakeSoundFactory implements SoundFactory {
    @Override
    public Sound make(String soundTypeId) throws IllegalArgumentException {
        return new FakeSound(new FakeSoundType(soundTypeId));
    }

    @Override
    public Sound make(String soundTypeId, EntityUuid entityUuid) throws IllegalArgumentException {
        FakeSound newSound = new FakeSound(new FakeSoundType(soundTypeId));
        newSound._uuid = entityUuid;
        return newSound;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

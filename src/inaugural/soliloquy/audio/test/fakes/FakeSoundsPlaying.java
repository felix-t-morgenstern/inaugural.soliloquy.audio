package inaugural.soliloquy.audio.test.fakes;

import inaugural.soliloquy.audio.test.unit.stubs.CollectionStub;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.Collection;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.util.HashMap;

public class FakeSoundsPlaying implements SoundsPlaying {
    private final HashMap<EntityUuid,Sound> SOUNDS_PLAYING = new HashMap<>();

    @Override
    public Collection<Sound> allSoundsPlaying() {
        Collection<Sound> allSoundsPlaying = new CollectionStub<>();
        SOUNDS_PLAYING.values().forEach(allSoundsPlaying::add);
        return allSoundsPlaying;
    }

    @Override
    public boolean isPlayingSound(EntityUuid entityUuid) throws IllegalArgumentException {
        return SOUNDS_PLAYING.containsKey(entityUuid);
    }

    @Override
    public Sound getSound(EntityUuid entityUuid) throws IllegalArgumentException {
        return SOUNDS_PLAYING.get(entityUuid);
    }

    @Override
    public void registerSound(Sound sound) throws IllegalArgumentException {
        SOUNDS_PLAYING.put(sound.id(), sound);
    }

    @Override
    public void removeSound(Sound sound) throws IllegalArgumentException {
        SOUNDS_PLAYING.remove(sound.id());
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

package inaugural.soliloquy.audio.test.fakes;

import inaugural.soliloquy.audio.test.unit.stubs.CollectionStub;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.Collection;
import soliloquy.specs.common.infrastructure.ReadableCollection;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FakeSoundsPlaying implements SoundsPlaying {
    private final HashMap<EntityUuid,Sound> SOUNDS_PLAYING = new HashMap<>();
    private ArrayList<Sound> _soundsInOrder = new ArrayList<>();

    @Override
    public int size() {
        return SOUNDS_PLAYING.size();
    }

    @Override
    public ReadableCollection<Sound> representation() {
        Collection<Sound> allSoundsPlaying = new CollectionStub<>(null);
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
        _soundsInOrder.add(sound);
    }

    @Override
    public void removeSound(Sound sound) throws IllegalArgumentException {
        SOUNDS_PLAYING.remove(sound.id());
    }

    @Override
    public String getInterfaceName() {
        return null;
    }

    @Override
    public Iterator<Sound> iterator() {
        return _soundsInOrder.iterator();
    }
}
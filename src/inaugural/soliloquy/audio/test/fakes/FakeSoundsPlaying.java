package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class FakeSoundsPlaying implements SoundsPlaying {
    private final HashMap<UUID,Sound> SOUNDS_PLAYING = new HashMap<>();
    private final ArrayList<Sound> SOUNDS_IN_ORDER = new ArrayList<>();

    @Override
    public int size() {
        return SOUNDS_PLAYING.size();
    }

    @Override
    public List<Sound> representation() {
        List<Sound> allSoundsPlaying = new FakeList<>(null);
        allSoundsPlaying.addAll(SOUNDS_PLAYING.values());
        return allSoundsPlaying;
    }

    @Override
    public boolean isPlayingSound(UUID uuid) throws IllegalArgumentException {
        return SOUNDS_PLAYING.containsKey(uuid);
    }

    @Override
    public Sound getSound(UUID uuid) throws IllegalArgumentException {
        return SOUNDS_PLAYING.get(uuid);
    }

    @Override
    public void registerSound(Sound sound) throws IllegalArgumentException {
        SOUNDS_PLAYING.put(sound.uuid(), sound);
        SOUNDS_IN_ORDER.add(sound);
    }

    @Override
    public void removeSound(Sound sound) throws IllegalArgumentException {
        SOUNDS_PLAYING.remove(sound.uuid());
    }

    @Override
    public String getInterfaceName() {
        return null;
    }

    @Override
    public Iterator<Sound> iterator() {
        return SOUNDS_IN_ORDER.iterator();
    }
}

package inaugural.soliloquy.audio.archetypes;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.List;

import java.util.Iterator;
import java.util.UUID;

public class SoundsPlayingArchetype implements SoundsPlaying {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public List<Sound> representation() {
        return null;
    }

    @Override
    public boolean isPlayingSound(UUID uuid) throws IllegalArgumentException {
        return false;
    }

    @Override
    public Sound getSound(UUID uuid) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void registerSound(Sound sound) throws IllegalArgumentException {

    }

    @Override
    public void removeSound(Sound sound) throws IllegalArgumentException {

    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public Iterator<Sound> iterator() {
        return null;
    }

    @Override
    public String getInterfaceName() {
        return SoundsPlaying.class.getCanonicalName();
    }
}

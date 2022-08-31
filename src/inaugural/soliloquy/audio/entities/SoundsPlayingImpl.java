package inaugural.soliloquy.audio.entities;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.List;
import soliloquy.specs.common.infrastructure.Map;

import java.util.Iterator;
import java.util.UUID;

public class SoundsPlayingImpl implements SoundsPlaying {
    private final Map<UUID, Sound> SOUNDS_PLAYING;

    @SuppressWarnings("ConstantConditions")
    public SoundsPlayingImpl(MapFactory mapFactory, UUID uuidArchetype,
                             Sound soundArchetype) {
        SOUNDS_PLAYING = Check.ifNull(mapFactory, "mapFactory")
                .make(Check.ifNull(uuidArchetype, "uuidArchetype"),
                        Check.ifNull(soundArchetype, "soundArchetype"));
    }

    @Override
    public String getInterfaceName() {
        return SoundsPlaying.class.getCanonicalName();
    }

    @Override
    public int size() {
        return SOUNDS_PLAYING.size();
    }

    @Override
    public List<Sound> representation() {
        return SOUNDS_PLAYING.getValuesList();
    }

    @Override
    public boolean isPlayingSound(UUID soundId) throws IllegalArgumentException {
        return SOUNDS_PLAYING.containsKey(Check.ifNull(soundId, "soundId"));
    }

    @Override
    public Sound getSound(UUID soundId) throws IllegalArgumentException {
        return SOUNDS_PLAYING.get(Check.ifNull(soundId, "soundId"));
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void registerSound(Sound sound) throws IllegalArgumentException {
        SOUNDS_PLAYING.put(Check.ifNull(sound, "sound").uuid(),
                sound);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void removeSound(Sound sound) throws IllegalArgumentException {
        SOUNDS_PLAYING.remove(
                Check.ifNull(sound, "sound").uuid(),
                sound);
    }

    @Override
    public Iterator<Sound> iterator() {
        return SOUNDS_PLAYING.values().iterator();
    }
}

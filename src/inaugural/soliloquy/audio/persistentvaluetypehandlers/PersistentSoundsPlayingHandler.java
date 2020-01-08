package inaugural.soliloquy.audio.persistentvaluetypehandlers;

import com.google.gson.Gson;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.PersistentValueTypeHandler;

public class PersistentSoundsPlayingHandler implements PersistentValueTypeHandler<SoundsPlaying> {
    private final PersistentValueTypeHandler<Sound> PERSISTENT_SOUND_HANDLER;
    private final SoundsPlaying SOUNDS_PLAYING;

    @SuppressWarnings("ConstantConditions")
    public PersistentSoundsPlayingHandler(
            PersistentValueTypeHandler<Sound> persistentSoundHandler,
            SoundsPlaying soundsPlaying) {
        if (persistentSoundHandler == null) {
            throw new IllegalArgumentException(
                    "PersistentSoundsPlayingHandler: persistentSoundHandler cannot be null");
        }
        PERSISTENT_SOUND_HANDLER = persistentSoundHandler;
        if (soundsPlaying == null) {
            throw new IllegalArgumentException(
                    "PersistentSoundsPlayingHandler: soundsPlaying cannot be null");
        }
        SOUNDS_PLAYING = soundsPlaying;
    }

    @Override
    public SoundsPlaying read(String data) throws IllegalArgumentException {
        SOUNDS_PLAYING.forEach(SOUNDS_PLAYING::removeSound);

        SoundsPlayingDTO soundsPlayingDTO = new Gson().fromJson(data, SoundsPlayingDTO.class);
        for(String soundJson : soundsPlayingDTO.soundDTOs) {
            SOUNDS_PLAYING.registerSound(PERSISTENT_SOUND_HANDLER.read(soundJson));
        }

        return null;
    }

    @Override
    public String write(SoundsPlaying soundsPlaying) {
        SoundsPlayingDTO soundsPlayingDTO = new SoundsPlayingDTO();
        String[] jsonObjects = new String[soundsPlaying.size()];
        int index = 0;
        for(Sound soundPlaying : SOUNDS_PLAYING) {
            jsonObjects[index++] = PERSISTENT_SOUND_HANDLER.write(soundPlaying);
        }
        soundsPlayingDTO.soundDTOs = jsonObjects;
        return new Gson().toJson(soundsPlayingDTO);
    }

    @Override
    public SoundsPlaying getArchetype() {
        return null;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }

    private class SoundsPlayingDTO {
        String[] soundDTOs;
    }
}

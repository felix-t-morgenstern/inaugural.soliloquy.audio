package inaugural.soliloquy.audio.persistentvaluetypehandlers;

import com.google.gson.Gson;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.Collection;
import soliloquy.specs.common.infrastructure.PersistentValueTypeHandler;

import java.util.ArrayList;

public class PersistentSoundsPlayingHandler implements PersistentValueTypeHandler<SoundsPlaying> {
    private final PersistentValueTypeHandler<Sound> PERSISTENT_SOUND_HANDLER;
    private final SoundsPlaying SOUNDS_PLAYING;

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
        SOUNDS_PLAYING.allSoundsPlaying().forEach(SOUNDS_PLAYING::removeSound);

        SoundsPlayingDTO soundsPlayingDTO = new Gson().fromJson(data, SoundsPlayingDTO.class);
        for(String soundJson : soundsPlayingDTO.soundDTOs) {
            SOUNDS_PLAYING.registerSound(PERSISTENT_SOUND_HANDLER.read(soundJson));
        }

        return null;
    }

    @Override
    public String write(SoundsPlaying soundsPlaying) {
        SoundsPlayingDTO soundsPlayingDTO = new SoundsPlayingDTO();
        Collection<Sound> soundsPlayingCollection = SOUNDS_PLAYING.allSoundsPlaying();
        String[] jsonObjects = new String[soundsPlayingCollection.size()];
        int index = 0;
        for(Sound soundPlaying : soundsPlayingCollection) {
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

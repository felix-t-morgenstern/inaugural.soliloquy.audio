package inaugural.soliloquy.audio.persistence;

import inaugural.soliloquy.tools.Check;
import inaugural.soliloquy.tools.persistence.AbstractTypeHandler;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.persistence.TypeHandler;

import java.util.List;

import static inaugural.soliloquy.tools.generic.Archetypes.generateSimpleArchetype;

public class SoundsPlayingHandler extends AbstractTypeHandler<SoundsPlaying> {
    private final TypeHandler<Sound> PERSISTENT_SOUND_HANDLER;
    private final SoundsPlaying SOUNDS_PLAYING;

    public SoundsPlayingHandler(
            TypeHandler<Sound> persistentSoundHandler,
            SoundsPlaying soundsPlaying) {
        super(generateSimpleArchetype(SoundsPlaying.class));
        if (persistentSoundHandler == null) {
            throw new IllegalArgumentException(
                    "SoundsPlayingHandler: persistentSoundHandler cannot be null");
        }
        PERSISTENT_SOUND_HANDLER = persistentSoundHandler;
        if (soundsPlaying == null) {
            throw new IllegalArgumentException(
                    "SoundsPlayingHandler: soundsPlaying cannot be null");
        }
        SOUNDS_PLAYING = soundsPlaying;
    }

    @Override
    public SoundsPlaying read(String data) throws IllegalArgumentException {
        Check.ifNullOrEmpty(data, "data");

        SOUNDS_PLAYING.representation().forEach(SOUNDS_PLAYING::removeSound);

        SoundsPlayingDTO soundsPlayingDTO = JSON.fromJson(data, SoundsPlayingDTO.class);
        for (String soundJson : soundsPlayingDTO.soundDTOs) {
            SOUNDS_PLAYING.registerSound(PERSISTENT_SOUND_HANDLER.read(soundJson));
        }

        return null;
    }

    @Override
    public String write(SoundsPlaying soundsPlaying) {
        SoundsPlayingDTO soundsPlayingDTO = new SoundsPlayingDTO();
        List<Sound> listOfSoundsPlaying = SOUNDS_PLAYING.representation();
        String[] jsonObjects = new String[listOfSoundsPlaying.size()];
        int index = 0;
        for (Sound soundPlaying : listOfSoundsPlaying) {
            jsonObjects[index++] = PERSISTENT_SOUND_HANDLER.write(soundPlaying);
        }
        soundsPlayingDTO.soundDTOs = jsonObjects;
        return JSON.toJson(soundsPlayingDTO);
    }

    private static class SoundsPlayingDTO {
        String[] soundDTOs;
    }
}

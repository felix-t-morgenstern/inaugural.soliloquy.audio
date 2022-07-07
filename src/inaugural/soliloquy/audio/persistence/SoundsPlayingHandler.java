package inaugural.soliloquy.audio.persistence;

import inaugural.soliloquy.audio.archetypes.SoundsPlayingArchetype;
import inaugural.soliloquy.tools.persistence.AbstractTypeHandler;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.persistence.TypeHandler;

public class SoundsPlayingHandler extends AbstractTypeHandler<SoundsPlaying> {
    private final TypeHandler<Sound> PERSISTENT_SOUND_HANDLER;
    private final SoundsPlaying SOUNDS_PLAYING;

    private static final SoundsPlaying ARCHETYPE = new SoundsPlayingArchetype();

    @SuppressWarnings("ConstantConditions")
    public SoundsPlayingHandler(
            TypeHandler<Sound> persistentSoundHandler,
            SoundsPlaying soundsPlaying) {
        super(ARCHETYPE);
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
        if (data == null) {
            throw new IllegalArgumentException(
                    "SoundsPlayingHandler.read: data cannot be null");
        }
        if (data.equals("")) {
            throw new IllegalArgumentException(
                    "SoundsPlayingHandler.read: data cannot be empty");
        }

        SOUNDS_PLAYING.forEach(SOUNDS_PLAYING::removeSound);

        SoundsPlayingDTO soundsPlayingDTO = JSON.fromJson(data, SoundsPlayingDTO.class);
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
        return JSON.toJson(soundsPlayingDTO);
    }

    private static class SoundsPlayingDTO {
        String[] soundDTOs;
    }
}

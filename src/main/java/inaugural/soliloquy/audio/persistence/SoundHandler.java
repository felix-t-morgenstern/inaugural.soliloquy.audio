package inaugural.soliloquy.audio.persistence;

import inaugural.soliloquy.audio.archetypes.SoundArchetype;
import inaugural.soliloquy.tools.Check;
import inaugural.soliloquy.tools.persistence.AbstractTypeHandler;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;

import java.util.UUID;

public class SoundHandler extends AbstractTypeHandler<Sound> {
    private final static Sound ARCHETYPE = new SoundArchetype();
    private final SoundFactory SOUND_FACTORY;

    public SoundHandler(SoundFactory soundFactory) {
        super(ARCHETYPE);
        SOUND_FACTORY = Check.ifNull(soundFactory, "soundFactory");
    }

    @Override
    public Sound read(String data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(
                    "SoundHandler.read: data cannot be null.");
        }
        if (data.equals("")) {
            throw new IllegalArgumentException(
                    "SoundHandler.read: data cannot be empty.");
        }
        SoundDTO soundDTO = JSON.fromJson(data, SoundDTO.class);
        Sound readValue = SOUND_FACTORY.make(soundDTO.type, UUID.fromString(soundDTO.uuid));
        readValue.setIsLooping(soundDTO.looping);
        readValue.setVolume(soundDTO.vol);
        if (soundDTO.muted) {
            readValue.mute();
        }
        else {
            readValue.unmute();
        }
        readValue.setMillisecondPosition(soundDTO.msPos);
        if (soundDTO.paused) {
            readValue.pause();
        }
        else {
            readValue.play();
        }
        return readValue;
    }

    @Override
    public String write(Sound sound) {
        if (sound == null) {
            throw new IllegalArgumentException(
                    "SoundHandler.write: sound cannot be null");
        }
        SoundDTO soundDTO = new SoundDTO();
        soundDTO.uuid = sound.uuid().toString();
        soundDTO.type = sound.soundType().id();
        soundDTO.paused = sound.isPaused();
        soundDTO.muted = sound.isMuted();
        soundDTO.vol = sound.getVolume();
        soundDTO.msPos = sound.getMillisecondPosition();
        soundDTO.looping = sound.getIsLooping();
        return JSON.toJson(soundDTO);
    }

    private static class SoundDTO {
        String uuid;
        String type;
        boolean paused;
        boolean muted;
        double vol;
        int msPos;
        boolean looping;
    }
}

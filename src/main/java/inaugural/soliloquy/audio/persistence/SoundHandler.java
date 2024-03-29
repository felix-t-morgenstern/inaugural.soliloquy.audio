package inaugural.soliloquy.audio.persistence;

import inaugural.soliloquy.tools.Check;
import inaugural.soliloquy.tools.persistence.AbstractTypeHandler;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;

import java.util.UUID;

import static inaugural.soliloquy.tools.generic.Archetypes.generateSimpleArchetype;

public class SoundHandler extends AbstractTypeHandler<Sound> {
    private final SoundFactory SOUND_FACTORY;

    public SoundHandler(SoundFactory soundFactory) {
        super(generateSimpleArchetype(Sound.class));
        SOUND_FACTORY = Check.ifNull(soundFactory, "soundFactory");
    }

    @Override
    public Sound read(String data) throws IllegalArgumentException {
        Check.ifNullOrEmpty(data, "data");

        SoundDTO soundDTO = JSON.fromJson(data, SoundDTO.class);
        Sound sound = SOUND_FACTORY.make(soundDTO.type, UUID.fromString(soundDTO.uuid));
        sound.setIsLooping(soundDTO.looping);
        sound.setVolume(soundDTO.vol);
        if (soundDTO.muted) {
            sound.mute();
        }
        else {
            sound.unmute();
        }
        sound.setMillisecondPosition(soundDTO.msPos);
        if (soundDTO.paused) {
            sound.pause();
        }
        else {
            sound.play();
        }
        return sound;
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
        soundDTO.loopingRestartMs = sound.getLoopingRestartMs();
        soundDTO.loopingStopMs = sound.getLoopingStopMs();
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
        Integer loopingStopMs;
        Integer loopingRestartMs;
    }
}

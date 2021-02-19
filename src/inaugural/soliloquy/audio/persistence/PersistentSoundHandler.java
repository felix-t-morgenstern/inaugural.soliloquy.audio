package inaugural.soliloquy.audio.persistence;

import com.google.gson.Gson;
import inaugural.soliloquy.audio.archetypes.SoundArchetype;
import inaugural.soliloquy.tools.Check;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.persistence.PersistentValueTypeHandler;

public class PersistentSoundHandler implements PersistentValueTypeHandler<Sound> {
    private final static Sound ARCHETYPE = new SoundArchetype();
    private final SoundFactory SOUND_FACTORY;
    private final EntityUuidFactory ENTITY_UUID_FACTORY;

    public PersistentSoundHandler(SoundFactory soundFactory, EntityUuidFactory entityUuidFactory) {
        SOUND_FACTORY = Check.ifNull(soundFactory, "soundFactory");
        ENTITY_UUID_FACTORY = Check.ifNull(entityUuidFactory, "entityUuidFactory");
    }

    @Override
    public Sound read(String data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException(
                    "PersistentSoundHandler.read: data cannot be null.");
        }
        if (data.equals("")) {
            throw new IllegalArgumentException(
                    "PersistentSoundHandler.read: data cannot be empty.");
        }
        SoundDTO soundDTO = new Gson().fromJson(data, SoundDTO.class);
        Sound readValue = SOUND_FACTORY.make(soundDTO.type,
                ENTITY_UUID_FACTORY.createFromString(soundDTO.id));
        readValue.setIsLooping(soundDTO.looping);
        readValue.setVolume(soundDTO.vol);
        if (soundDTO.muted) {
            readValue.mute();
        } else {
            readValue.unmute();
        }
        readValue.setMillisecondPosition(soundDTO.msPos);
        if (soundDTO.paused) {
            readValue.pause();
        } else {
            readValue.play();
        }
        return readValue;
    }

    @Override
    public String write(Sound sound) {
        if (sound == null) {
            throw new IllegalArgumentException(
                    "PersistentSoundHandler.write: sound cannot be null");
        }
        SoundDTO soundDTO = new SoundDTO();
        soundDTO.id = sound.id().toString();
        soundDTO.type = sound.soundType().id();
        soundDTO.paused = sound.isPaused();
        soundDTO.muted = sound.isMuted();
        soundDTO.vol = sound.getVolume();
        soundDTO.msPos = sound.getMillisecondPosition();
        soundDTO.looping = sound.getIsLooping();
        return new Gson().toJson(soundDTO);
    }

    @Override
    public Sound getArchetype() {
        return ARCHETYPE;
    }

    @Override
    public String getInterfaceName() {
        return PersistentValueTypeHandler.class.getCanonicalName() + "<" +
                Sound.class.getCanonicalName() + ">";
    }

    private class SoundDTO {
        String id;
        String type;
        boolean paused;
        boolean muted;
        double vol;
        int msPos;
        boolean looping;
    }
}

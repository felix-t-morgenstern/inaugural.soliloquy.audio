package inaugural.soliloquy.audio.persistentvaluetypehandlers;

import com.google.gson.Gson;
import inaugural.soliloquy.audio.archetypes.SoundArchetype;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.infrastructure.PersistentValueTypeHandler;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class PersistentSoundHandler implements PersistentValueTypeHandler<Sound> {
    private final static Sound ARCHETYPE = new SoundArchetype();
    private final SoundFactory SOUND_FACTORY;
    private final EntityUuidFactory ENTITY_UUID_FACTORY;

    public PersistentSoundHandler(SoundFactory soundFactory, EntityUuidFactory entityUuidFactory) {
        if (soundFactory == null) {
            throw new IllegalArgumentException(
                    "PersistentSoundHandler: soundFactory must be non-null");
        }
        SOUND_FACTORY = soundFactory;
        if (entityUuidFactory == null) {
            throw new IllegalArgumentException(
                    "PersistentSoundHandler: entityUuidFactory must be non-null");
        }
        ENTITY_UUID_FACTORY = entityUuidFactory;
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
        Sound readValue = SOUND_FACTORY.make(soundDTO.soundTypeId,
                ENTITY_UUID_FACTORY.createFromString(soundDTO.soundId));
        readValue.setIsLooping(soundDTO.isLooping);
        readValue.setVolume(soundDTO.volume);
        if (soundDTO.isMuted) {
            readValue.mute();
        } else {
            readValue.unmute();
        }
        readValue.setMillisecondPosition(soundDTO.msPosition);
        if (soundDTO.isPaused) {
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
        soundDTO.soundId = sound.id().toString();
        soundDTO.soundTypeId = sound.soundType().id();
        soundDTO.isPaused = sound.isPaused();
        soundDTO.isMuted = sound.isMuted();
        soundDTO.volume = sound.getVolume();
        soundDTO.msPosition = sound.getMillisecondPosition();
        soundDTO.isLooping = sound.getIsLooping();
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
        String soundId;
        String soundTypeId;
        boolean isPaused;
        boolean isMuted;
        double volume;
        int msPosition;
        boolean isLooping;
    }
}

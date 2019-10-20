package inaugural.soliloquy.audio.test.fakes;

import com.google.gson.Gson;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.infrastructure.PersistentValueTypeHandler;

public class FakePersistentSoundHandler implements PersistentValueTypeHandler<Sound> {
    private final SoundFactory SOUND_FACTORY = new FakeSoundFactory();
    private final EntityUuidFactory ENTITY_UUID_FACTORY = new FakeEntityUuidFactory();

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
        return null;
    }

    @Override
    public String getInterfaceName() {
        return null;
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

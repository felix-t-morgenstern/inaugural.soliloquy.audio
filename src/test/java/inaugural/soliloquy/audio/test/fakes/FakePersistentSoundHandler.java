package inaugural.soliloquy.audio.test.fakes;

import com.google.gson.Gson;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.persistence.TypeHandler;

import java.util.UUID;

// TODO: Why does this class even exist?
public class FakePersistentSoundHandler implements TypeHandler<Sound> {
    private final SoundFactory SOUND_FACTORY = new FakeSoundFactory();

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
        SoundDTO soundDTO = new Gson().fromJson(data, SoundDTO.class);
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

package inaugural.soliloquy.audio.factories;

import inaugural.soliloquy.audio.entities.SoundImpl;
import inaugural.soliloquy.tools.Check;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.infrastructure.Registry;

import java.util.UUID;
import java.util.function.Supplier;

public class SoundFactoryImpl implements SoundFactory {
    private final Registry<SoundType> SOUND_TYPES_REGISTRY;
    private final SoundsPlaying SOUNDS_PLAYING;
    private final Supplier<UUID> UUID_FACTORY;

    public SoundFactoryImpl(Registry<SoundType> soundTypesRegistry, SoundsPlaying soundsPlaying,
                            Supplier<UUID> uuidFactory) {
        SOUND_TYPES_REGISTRY = Check.ifNull(soundTypesRegistry, "soundTypesRegistry");
        SOUNDS_PLAYING = Check.ifNull(soundsPlaying, "soundsPlaying");
        UUID_FACTORY = Check.ifNull(uuidFactory, "uuidFactory");
    }

    public Sound make(String soundTypeId) throws IllegalArgumentException {
        return make(soundTypeId, UUID_FACTORY.get());
    }

    @Override
    public Sound make(String soundTypeId, UUID uuid) throws IllegalArgumentException {
        Check.ifNullOrEmpty(soundTypeId, "soundTypeId");
        Check.ifNull(uuid, "uuid");
        if (!SOUND_TYPES_REGISTRY.contains(soundTypeId)) {
            throw new IllegalArgumentException(
                    "SoundFactoryImpl.make: Invalid soundTypeId provided");
        }
        SoundType soundType = SOUND_TYPES_REGISTRY.get(soundTypeId);
        if (soundType == null) {
            throw new IllegalArgumentException(
                    "SoundFactoryImpl.make: soundTypeId must correspond to a valid (i.e. " +
                            "registered) sound type id");
        }
        Sound sound = new SoundImpl(uuid, soundType, SOUNDS_PLAYING);
        if (soundType.defaultLoopingStopMs() != null) {
            sound.setIsLooping(true);
            sound.setLoopingStopMs(soundType.defaultLoopingStopMs());
        }
        if (soundType.defaultLoopingRestartMs() != null) {
            sound.setLoopingRestartMs(soundType.defaultLoopingRestartMs());
        }
        SOUNDS_PLAYING.registerSound(sound);
        return sound;
    }

    public String getInterfaceName() {
        return SoundFactory.class.getCanonicalName();
    }
}

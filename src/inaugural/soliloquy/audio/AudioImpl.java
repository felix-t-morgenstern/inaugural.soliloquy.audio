package inaugural.soliloquy.audio;

import soliloquy.specs.audio.entities.Audio;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.infrastructure.Registry;

public class AudioImpl implements Audio {
	private final SoundsPlaying SOUNDS_PLAYING;
	private final SoundFactory SOUND_FACTORY;
	private final Registry<SoundType> SOUND_TYPES_REGISTRY;
	
	public AudioImpl(SoundsPlaying soundsPlaying, SoundFactory soundFactory,
                     Registry<SoundType> soundTypesRegistry) {
		SOUNDS_PLAYING = soundsPlaying;
		SOUND_FACTORY = soundFactory;
		SOUND_TYPES_REGISTRY = soundTypesRegistry;
	}

	public SoundsPlaying soundsPlaying() {
		return SOUNDS_PLAYING;
	}

	public SoundFactory soundFactory() {
		return SOUND_FACTORY;
	}

    @Override
    public Registry<SoundType> soundTypes() {
        return SOUND_TYPES_REGISTRY;
    }
}

package inaugural.soliloquy.audio;

import soliloquy.specs.audio.entities.Audio;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;

public class AudioImpl implements Audio {
	private final SoundsPlaying SOUNDS_PLAYING;
	private final SoundFactory SOUND_FACTORY;
	
	public AudioImpl(SoundsPlaying soundsPlaying, SoundFactory soundFactory) {
		SOUNDS_PLAYING = soundsPlaying;
		SOUND_FACTORY = soundFactory;
	}

	public SoundsPlaying soundsPlaying() {
		return SOUNDS_PLAYING;
	}

	public SoundFactory soundFactory() {
		return SOUND_FACTORY;
	}
}

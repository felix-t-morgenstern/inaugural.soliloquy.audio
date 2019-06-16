package inaugural.soliloquy.audio;

import soliloquy.specs.audio.entities.IAudio;
import soliloquy.specs.audio.entities.ISoundsPlaying;
import soliloquy.specs.audio.factories.ISoundFactory;

public class Audio implements IAudio {
	private final ISoundsPlaying SOUNDS_PLAYING;
	private final ISoundFactory SOUND_FACTORY;
	
	public Audio(ISoundsPlaying soundsPlaying, ISoundFactory soundFactory) {
		SOUNDS_PLAYING = soundsPlaying;
		SOUND_FACTORY = soundFactory;
	}

	public ISoundsPlaying soundsPlaying() {
		return SOUNDS_PLAYING;
	}

	public ISoundFactory soundFactory() {
		return SOUND_FACTORY;
	}
}

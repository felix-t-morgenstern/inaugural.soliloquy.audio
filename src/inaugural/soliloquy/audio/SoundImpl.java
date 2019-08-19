package inaugural.soliloquy.audio;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.io.File;

public class SoundImpl implements Sound {
	private final EntityUuid ID;
	private final SoundType SOUND_TYPE;
	
	private final SoundsPlaying SOUNDS_PLAYING;
	
	private final MediaPlayer MEDIA_PLAYER;
	
	private boolean _isPaused;
	private boolean _isStopped;
	private boolean _isMuted;
	private boolean _isLooping;
	
	private boolean _isReady;
	
	private int _durationMs;
	
	private double _volume;
	
	public SoundImpl(EntityUuid id, SoundType soundType, SoundsPlaying soundsPlaying) {
		// TODO: Test to make sure that id is non-null
		ID = id;
		// TODO: Test to make sure that soundTypeId is non-null and non-empty
		SOUND_TYPE = soundType;
		
		SOUNDS_PLAYING = soundsPlaying;
		
		new JFXPanel();
		Media media = new Media(new File(soundType.filename()).toURI().toString());
		MEDIA_PLAYER = new MediaPlayer(media);
		
		_isPaused = true;
		_isStopped = false;
		_isMuted = false;
		_isLooping = false;
		_isReady = false;
		_volume = 1.0;
		
		MEDIA_PLAYER.setOnReady(() ->
		{
			_durationMs = (int) MEDIA_PLAYER.getTotalDuration().toMillis();
			_isReady = true;
			MEDIA_PLAYER.setOnEndOfMedia(this::stop);
		});
	}

	public EntityUuid id() throws IllegalStateException {
		return ID;
	}

	public String getInterfaceName() {
		return Sound.class.getCanonicalName();
	}

	public SoundType soundType() {
		return SOUND_TYPE;
	}

	public void play() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.play: Sound has already been stopped");
		}
		MEDIA_PLAYER.play();
		_isPaused = false;
	}

	public Runnable playTask() {
		return this::play;
	}

	public void pause() {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.pause: Sound has already been stopped");
		}
		MEDIA_PLAYER.pause();
		_isPaused = true;
	}

	public Runnable pauseTask() {
		return this::pause;
	}

	public boolean isPaused() {
		return _isPaused;
	}

	public boolean isPlaying() {
		return !_isPaused && !_isStopped;
	}

	public void stop() throws UnsupportedOperationException {
		MEDIA_PLAYER.stop();
		MEDIA_PLAYER.dispose();
		_isPaused = false;
		_isStopped = true;
		_isMuted = true;
		
		SOUNDS_PLAYING.removeSound(this);
	}

	public Runnable stopTask() {
		return this::stop;
	}

	public void mute() {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.mute: Sound has already been stopped");
		}
		MEDIA_PLAYER.setVolume(0.0);
		_isMuted = true;
	}

	public Runnable muteTask() {
		return this::mute;
	}

	public void unmute() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException(
					"Sound.unmute: Sound has already been stopped");
		}
		MEDIA_PLAYER.setVolume(_volume);
		_isMuted = false;
	}

	public Runnable unmuteTask() {
		return this::unmute;
	}

	public boolean isMuted() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException(
					"Sound.isMuted: Sound has already been stopped");
		}
		return _isMuted;
	}

	public boolean isStopped() {
		return _isStopped;
	}

	public double getVolume() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException(
					"Sound.getVolume: Sound has already been stopped");
		}
		return _volume;
	}

	public void setVolume(double volume)
			throws IllegalArgumentException, UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException(
					"Sound.getVolume: Sound has already been stopped");
		}
		if (!_isMuted) {
			MEDIA_PLAYER.setVolume(volume);
		}
		_volume = volume;
	}

	public Runnable setVolumeTask(double volume) throws IllegalArgumentException {
		return () -> setVolume(volume);
	}

	public int getMillisecondLength() throws InterruptedException {
		while (!_isReady) {
			Thread.sleep(10);
		}
		return _durationMs;
	}

	public int getMillisecondPosition() throws InterruptedException {
		if (_isStopped) {
			throw new UnsupportedOperationException(
					"Sound.getMillisecondPosition: Sound has already been stopped");
		}
		while (!_isReady) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return (int) MEDIA_PLAYER.getCurrentTime().toMillis();
	}

	public void setMillisecondPosition(int ms) throws IllegalArgumentException, UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.setMillisecondPosition: Sound has already been stopped");
		}
		MEDIA_PLAYER.seek(Duration.millis(ms));
	}

	public Runnable setMillisecondPositionTask(int ms) throws IllegalArgumentException {
		return () -> setMillisecondPosition(ms);
	}

	public boolean getIsLooping() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.getIsLooping: Sound has already been stopped");
		}
		return _isLooping;
	}

	public void setIsLooping(boolean isLooping) throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.setIsLooping: Sound has already been stopped");
		}
		if (isLooping) {
			MEDIA_PLAYER.setOnEndOfMedia(() -> setMillisecondPosition(0));
		} else {
			MEDIA_PLAYER.setOnEndOfMedia(this::stop);
		}
		_isLooping = isLooping;
	}

	public Runnable setIsLoopingTask(boolean isLooping) {
		return () -> setIsLooping(isLooping);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Sound)) {
			return false;
		}
		Sound sound = (Sound) o;
		return sound.id().equals(ID);
	}

}

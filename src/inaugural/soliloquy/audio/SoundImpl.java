package inaugural.soliloquy.audio;

import inaugural.soliloquy.tools.Check;
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

	private final Media MEDIA;
	private final MediaPlayer MEDIA_PLAYER;
	
	private boolean _isPaused;
	private boolean _isStopped;
	private boolean _isMuted;
	private boolean _isLooping;

	private int _loopStopMs;
	private int _loopRestartMs;
	
	private volatile boolean _isReady;
	
	private int _durationMs;
	
	private double _volume;
	
	@SuppressWarnings("ConstantConditions")
	public SoundImpl(EntityUuid id, SoundType soundType, SoundsPlaying soundsPlaying) {
		ID = Check.ifNull(id, "id");
		SOUND_TYPE = Check.ifNull(soundType, "soundType");
		SOUNDS_PLAYING = Check.ifNull(soundsPlaying, "soundsPlaying");
		
		new JFXPanel();
		MEDIA = new Media(new File(soundType.filename()).toURI().toString());
		MEDIA_PLAYER = new MediaPlayer(MEDIA);
		
		_isPaused = true;
		_isStopped = false;
		_isMuted = false;
		_isLooping = false;
		_isReady = false;
		_volume = 1.0;
		
		MEDIA_PLAYER.setOnReady(() ->
		{
			_loopStopMs = _durationMs = (int) MEDIA_PLAYER.getTotalDuration().toMillis();
			_isReady = true;
			MEDIA_PLAYER.setOnMarker(mediaMarkerEvent -> {
				setMillisecondPosition(_loopRestartMs+1);
			});
			MEDIA_PLAYER.setOnEndOfMedia(this::stop);
		});
		while (!_isReady) {
			Thread.onSpinWait();
		}
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
		throwWhenStopped("play");
		MEDIA_PLAYER.play();
		_isPaused = false;
	}

	public Runnable playTask() {
		return this::play;
	}

	public void pause() {
		throwWhenStopped("pause");
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
		throwWhenStopped("mute");
		MEDIA_PLAYER.setVolume(0.0);
		_isMuted = true;
	}

	public Runnable muteTask() {
		return this::mute;
	}

	public void unmute() throws UnsupportedOperationException {
		throwWhenStopped("unmute");
		MEDIA_PLAYER.setVolume(_volume);
		_isMuted = false;
	}

	public Runnable unmuteTask() {
		return this::unmute;
	}

	public boolean isMuted() throws UnsupportedOperationException {
		throwWhenStopped("isMuted");
		return _isMuted;
	}

	public boolean isStopped() {
		return _isStopped;
	}

	public double getVolume() throws UnsupportedOperationException {
		throwWhenStopped("getVolume");
		return _volume;
	}

	public void setVolume(double volume)
			throws IllegalArgumentException, UnsupportedOperationException {
		throwWhenStopped("setVolume");
		if (!_isMuted) {
			MEDIA_PLAYER.setVolume(volume);
		}
		_volume = volume;
	}

	public Runnable setVolumeTask(double volume) throws IllegalArgumentException {
		return () -> setVolume(volume);
	}

	public int getMillisecondLength(){
		while (!_isReady) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new IllegalThreadStateException();
			}
		}
		return _durationMs;
	}

	public int getMillisecondPosition() {
		throwWhenStopped("getMillisecondPosition");
		while (!_isReady) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				throw new IllegalThreadStateException();
			}
		}
		return (int) MEDIA_PLAYER.getCurrentTime().toMillis();
	}

	public void setMillisecondPosition(int ms)
			throws IllegalArgumentException, UnsupportedOperationException {
		throwWhenStopped("setMillisecondPosition");
		MEDIA_PLAYER.seek(Duration.millis(ms));
	}

	public Runnable setMillisecondPositionTask(int ms) throws IllegalArgumentException {
		return () -> setMillisecondPosition(ms);
	}

	public boolean getIsLooping() throws UnsupportedOperationException {
		throwWhenStopped("getIsLooping");
		return _isLooping;
	}

	public void setIsLooping(boolean isLooping) throws UnsupportedOperationException {
		throwWhenStopped("setIsLooping");
		if (isLooping) {
			MEDIA.getMarkers().put("", new Duration(_loopStopMs));
		} else {
			MEDIA_PLAYER.setOnEndOfMedia(this::stop);
		}
		_isLooping = isLooping;
	}

	public Runnable setIsLoopingTask(boolean isLooping) {
		return () -> setIsLooping(isLooping);
	}

	@Override
	public void setLoopingStopMs(Integer stopMs) throws IllegalArgumentException {
		throwWhenStopped("setLoopingStopMs");
		if (stopMs == null) {
			stopMs = _durationMs;
		}
		else if (stopMs > _durationMs) {
			throw new IllegalArgumentException(
					"SoundImpl.setLoopingStopMs: stopMs cannot exceed Sound duration");
		}
		else if (stopMs <= _loopRestartMs) {
			throw new IllegalArgumentException(
					"SoundImpl.setLoopingStopMs: stopMs cannot exceed restartMs");
		}
		_loopStopMs = Check.ifNonNegative(stopMs, "stopMs");
		if (_isLooping) {
			MEDIA.getMarkers().clear();
			MEDIA.getMarkers().put("", new Duration(_loopStopMs));
		}
	}

	@Override
	public Runnable setLoopingStopMsTask(Integer stopMs) throws IllegalArgumentException {
		return () -> setLoopingStopMs(stopMs);
	}

	@Override
	public void setLoopingRestartMs(int restartMs) throws IllegalArgumentException {
		throwWhenStopped("setLoopingRestartMs");
		if (restartMs > _durationMs) {
			throw new IllegalArgumentException(
					"SoundImpl.setLoopingRestartMs: restartMs cannot exceed Sound duration");
		}
		else if (restartMs >= _loopStopMs) {
			throw new IllegalArgumentException(
					"SoundImpl.setLoopingStopMs: restartMs cannot exceed stopMs");
		}
		_loopRestartMs = Check.ifNonNegative(restartMs, "restartMs");
	}

	@Override
	public Runnable setLoopingRestartMsTask(int restartMs) throws IllegalArgumentException {
		return () -> setLoopingRestartMs(restartMs);
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

	private void throwWhenStopped(String methodName) {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound." + methodName +
					": Sound has already been stopped");
		}
	}
}

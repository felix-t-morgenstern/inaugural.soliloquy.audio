package inaugural.soliloquy.audio;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.IEntityUuid;

public class Sound implements ISound {
	private final IEntityUuid ID;
	private final String SOUND_TYPE_ID;
	
	private final ISoundsPlaying SOUNDS_PLAYING;
	
	private final MediaPlayer MEDIA_PLAYER;
	
	private boolean _isPaused;
	private boolean _isStopped;
	private boolean _isMuted;
	private boolean _isLooping;
	
	private boolean _isReady;
	
	private int _durationMs;
	
	private double _volume;
	
	static final String INTERFACE_NAME = "soliloquy.audio.specs.ISound";
	
	public Sound(IEntityUuid id, String soundTypeId, String filename, ISoundsPlaying soundsPlaying) {
		// TODO: Test to make sure that id is non-null
		ID = id;
		// TODO: Test to make sure that soundTypeId is non-null and non-empty
		SOUND_TYPE_ID = soundTypeId;
		
		SOUNDS_PLAYING = soundsPlaying;
		
		new JFXPanel();
		Media media = new Media(filename);
		MEDIA_PLAYER = new MediaPlayer(media);
		
		_isPaused = true;
		_isStopped = false;
		_isMuted = false;
		_isLooping = false;
		_isReady = false;
		_volume = 1.0;
		
		MEDIA_PLAYER.setOnReady(() ->
		{
			_durationMs = (int) MEDIA_PLAYER.cycleDurationProperty().get().toMillis();
			_isReady = true;
		});
	}

	public IEntityUuid id() throws IllegalStateException {
		return ID;
	}

	public String getInterfaceName() {
		return INTERFACE_NAME;
	}

	public String soundTypeId() {
		return SOUND_TYPE_ID;
	}

	public void play() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.play: Sound has already been stopped");
		}
		MEDIA_PLAYER.play();
		_isPaused = false;
	}

	public Runnable playTask() {
		return () -> play();
	}

	public void pause() {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.pause: Sound has already been stopped");
		}
		MEDIA_PLAYER.pause();
		_isPaused = true;
	}

	public Runnable pauseTask() {
		return () -> pause();
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
		return () -> stop();
	}

	public void mute() {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.mute: Sound has already been stopped");
		}
		MEDIA_PLAYER.setVolume(0.0);
		_isMuted = true;
	}

	public Runnable muteTask() {
		return () -> mute();
	}

	public void unmute() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.unmute: Sound has already been stopped");
		}
		MEDIA_PLAYER.setVolume(_volume);
		_isMuted = false;
	}

	public Runnable unmuteTask() {
		return () -> unmute();
	}

	public boolean isMuted() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.isMuted: Sound has already been stopped");
		}
		return _isMuted;
	}

	public boolean isStopped() {
		return _isStopped;
	}

	public double getVolume() throws UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.getVolume: Sound has already been stopped");
		}
		return _volume;
	}

	public void setVolume(double volume) throws IllegalArgumentException, UnsupportedOperationException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.getVolume: Sound has already been stopped");
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
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return _durationMs;
	}

	public int getMillisecondPosition() throws InterruptedException {
		if (_isStopped) {
			throw new UnsupportedOperationException("Sound.getMillisecondPosition: Sound has already been stopped");
		}
		while (!_isReady) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw e;
			}
		}
		return (int) ((Duration)MEDIA_PLAYER.currentTimeProperty().getValue()).toMillis();
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
		}
		else {
			MEDIA_PLAYER.setOnEndOfMedia(() -> stop());
		}
		_isLooping = isLooping;
	}

	public Runnable setIsLoopingTask(boolean isLooping) {
		return () -> setIsLooping(isLooping);
	}

}

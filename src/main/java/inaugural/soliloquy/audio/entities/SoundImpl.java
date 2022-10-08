package inaugural.soliloquy.audio.entities;

import inaugural.soliloquy.tools.Check;
import inaugural.soliloquy.tools.CheckedExceptionWrapper;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;

import java.util.UUID;

import static inaugural.soliloquy.tools.files.Files.getLocalFile;

public class SoundImpl implements Sound {
    private final UUID UUID;
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

    public SoundImpl(UUID uuid, SoundType soundType, SoundsPlaying soundsPlaying) {
        UUID = Check.ifNull(uuid, "uuid");
        SOUND_TYPE = Check.ifNull(soundType, "soundType");
        SOUNDS_PLAYING = Check.ifNull(soundsPlaying, "soundsPlaying");

        new JFXPanel();
        MEDIA = new Media(getLocalFile(soundType.relativePath()).toURI().toString());
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
            MEDIA_PLAYER
                    .setOnMarker(mediaMarkerEvent -> setMillisecondPosition(_loopRestartMs + 1));
            MEDIA_PLAYER.setOnEndOfMedia(this::stop);
        });
        while (!_isReady) {
            Thread.onSpinWait();
        }
    }

    @Override
    public UUID uuid() throws IllegalStateException {
        return UUID;
    }

    @Override
    public String getInterfaceName() {
        return Sound.class.getCanonicalName();
    }

    @Override
    public SoundType soundType() {
        return SOUND_TYPE;
    }

    @Override
    public void play() throws UnsupportedOperationException {
        throwWhenStopped("play");
        MEDIA_PLAYER.play();
        _isPaused = false;
    }

    @Override
    public void pause() {
        throwWhenStopped("pause");
        MEDIA_PLAYER.pause();
        _isPaused = true;
    }

    @Override
    public boolean isPaused() {
        return _isPaused;
    }

    @Override
    public boolean isPlaying() {
        return !_isPaused && !_isStopped;
    }

    @Override
    public void stop() throws UnsupportedOperationException {
        MEDIA_PLAYER.stop();
        MEDIA_PLAYER.dispose();
        _isPaused = false;
        _isStopped = true;
        _isMuted = true;

        SOUNDS_PLAYING.removeSound(this);
    }

    @Override
    public void mute() {
        throwWhenStopped("mute");
        MEDIA_PLAYER.setVolume(0.0);
        _isMuted = true;
    }

    public void unmute() throws UnsupportedOperationException {
        throwWhenStopped("unmute");
        MEDIA_PLAYER.setVolume(_volume);
        _isMuted = false;
    }

    @Override
    public boolean isMuted() throws UnsupportedOperationException {
        throwWhenStopped("isMuted");
        return _isMuted;
    }

    @Override
    public boolean isStopped() {
        return _isStopped;
    }

    @Override
    public double getVolume() throws UnsupportedOperationException {
        throwWhenStopped("getVolume");
        return _volume;
    }

    @Override
    public void setVolume(double volume)
            throws IllegalArgumentException, UnsupportedOperationException {
        throwWhenStopped("setVolume");
        if (!_isMuted) {
            MEDIA_PLAYER.setVolume(volume);
        }
        _volume = volume;
    }

    @Override
    public int getMillisecondLength() {
        while (!_isReady) {
            CheckedExceptionWrapper.sleep(10);
        }
        return _durationMs;
    }

    @Override
    public int getMillisecondPosition() {
        throwWhenStopped("getMillisecondPosition");
        while (!_isReady) {
            CheckedExceptionWrapper.sleep(10);
        }
        return (int) MEDIA_PLAYER.getCurrentTime().toMillis();
    }

    @Override
    public void setMillisecondPosition(int ms)
            throws IllegalArgumentException, UnsupportedOperationException {
        throwWhenStopped("setMillisecondPosition");
        MEDIA_PLAYER.seek(Duration.millis(ms));
    }

    @Override
    public boolean getIsLooping() throws UnsupportedOperationException {
        throwWhenStopped("getIsLooping");
        return _isLooping;
    }

    @Override
    public void setIsLooping(boolean isLooping) throws UnsupportedOperationException {
        throwWhenStopped("setIsLooping");
        if (isLooping) {
            MEDIA.getMarkers().put("", new Duration(_loopStopMs));
        }
        else {
            MEDIA_PLAYER.setOnEndOfMedia(this::stop);
        }
        _isLooping = isLooping;
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
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Sound)) {
            return false;
        }
        Sound sound = (Sound) o;
        return sound.uuid().equals(UUID);
    }

    private void throwWhenStopped(String methodName) {
        if (_isStopped) {
            throw new UnsupportedOperationException("Sound." + methodName +
                    ": Sound has already been stopped");
        }
    }
}

package inaugural.soliloquy.audio.test.fakes;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;

import java.util.UUID;

public class FakeSound implements Sound {
    private final SoundType SOUND_TYPE;

    private boolean _isPaused;
    private boolean _isMuted;
    private int _msPosition;
    private double _volume;
    private boolean _isLooping;

    public UUID _uuid;

    public FakeSound(UUID uuid) {
        _uuid = uuid;
        SOUND_TYPE = null;
    }

    public FakeSound(SoundType soundType) {
        SOUND_TYPE = soundType;
    }

    @Override
    public SoundType soundType() {
        return SOUND_TYPE;
    }

    @Override
    public void play() throws UnsupportedOperationException {
        _isPaused = false;
    }

    @Override
    public Runnable playTask() {
        return null;
    }

    @Override
    public void pause() throws UnsupportedOperationException {
        _isPaused = true;
    }

    @Override
    public Runnable pauseTask() {
        return null;
    }

    @Override
    public boolean isPaused() {
        return _isPaused;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void stop() throws UnsupportedOperationException {

    }

    @Override
    public Runnable stopTask() {
        return null;
    }

    @Override
    public void mute() throws UnsupportedOperationException {
        _isMuted = true;
    }

    @Override
    public Runnable muteTask() {
        return null;
    }

    @Override
    public void unmute() throws UnsupportedOperationException {
        _isMuted = false;
    }

    @Override
    public Runnable unmuteTask() {
        return null;
    }

    @Override
    public boolean isMuted() throws UnsupportedOperationException {
        return _isMuted;
    }

    @Override
    public boolean isStopped() {
        return false;
    }

    @Override
    public double getVolume() throws UnsupportedOperationException {
        return _volume;
    }

    @Override
    public void setVolume(double v) throws IllegalArgumentException, UnsupportedOperationException {
        _volume = v;
    }

    @Override
    public Runnable setVolumeTask(double v) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int getMillisecondLength() {
        return 0;
    }

    @Override
    public int getMillisecondPosition() throws UnsupportedOperationException {
        return _msPosition;
    }

    @Override
    public void setMillisecondPosition(int i) throws IllegalArgumentException, UnsupportedOperationException {
        _msPosition = i;
    }

    @Override
    public Runnable setMillisecondPositionTask(int i) throws IllegalArgumentException {
        return null;
    }

    @Override
    public boolean getIsLooping() throws UnsupportedOperationException {
        return _isLooping;
    }

    @Override
    public void setIsLooping(boolean b) throws UnsupportedOperationException {
        _isLooping = b;
    }

    @Override
    public Runnable setIsLoopingTask(boolean b) {
        return null;
    }

    @Override
    public void setLoopingStopMs(Integer i) throws IllegalArgumentException {

    }

    @Override
    public Runnable setLoopingStopMsTask(Integer i) throws IllegalArgumentException {
        return null;
    }

    @Override
    public void setLoopingRestartMs(int i) throws IllegalArgumentException {

    }

    @Override
    public Runnable setLoopingRestartMsTask(int i) throws IllegalArgumentException {
        return null;
    }

    @Override
    public UUID uuid() {
        return _uuid;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

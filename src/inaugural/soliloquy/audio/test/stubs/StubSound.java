package inaugural.soliloquy.audio.test.stubs;

import inaugural.soliloquy.audio.test.fakes.FakeEntityUuid;
import inaugural.soliloquy.audio.test.fakes.FakeSoundType;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class StubSound implements Sound {
    private final static SoundType SOUND_TYPE = new FakeSoundType("SoundTypeId");

    public final static EntityUuid ID = new FakeEntityUuid("839f1134-3622-493f-ba19-7d7be392cd3b");
    public final static boolean IS_PAUSED = true;
    public final static boolean IS_MUTED = true;
    public final static boolean IS_LOOPING = true;
    public final static int MS_POSITION = 100;
    public final static double VOLUME = 0.5;

    @Override
    public SoundType soundType() {
        return SOUND_TYPE;
    }

    @Override
    public void play() throws UnsupportedOperationException {

    }

    @Override
    public Runnable playTask() {
        return null;
    }

    @Override
    public void pause() throws UnsupportedOperationException {

    }

    @Override
    public Runnable pauseTask() {
        return null;
    }

    @Override
    public boolean isPaused() {
        return IS_PAUSED;
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

    }

    @Override
    public Runnable muteTask() {
        return null;
    }

    @Override
    public void unmute() throws UnsupportedOperationException {

    }

    @Override
    public Runnable unmuteTask() {
        return null;
    }

    @Override
    public boolean isMuted() throws UnsupportedOperationException {
        return IS_MUTED;
    }

    @Override
    public boolean isStopped() {
        return false;
    }

    @Override
    public double getVolume() throws UnsupportedOperationException {
        return VOLUME;
    }

    @Override
    public void setVolume(double v) throws IllegalArgumentException, UnsupportedOperationException {

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
        return MS_POSITION;
    }

    @Override
    public void setMillisecondPosition(int i) throws IllegalArgumentException, UnsupportedOperationException {

    }

    @Override
    public Runnable setMillisecondPositionTask(int i) throws IllegalArgumentException {
        return null;
    }

    @Override
    public boolean getIsLooping() throws UnsupportedOperationException {
        return IS_LOOPING;
    }

    @Override
    public void setIsLooping(boolean b) throws UnsupportedOperationException {

    }

    @Override
    public Runnable setIsLoopingTask(boolean b) {
        return null;
    }

    @Override
    public EntityUuid id() {
        return ID;
    }

    @Override
    public String getInterfaceName() {
        return null;
    }
}

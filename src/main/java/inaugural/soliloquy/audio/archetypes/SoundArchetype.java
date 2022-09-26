package inaugural.soliloquy.audio.archetypes;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;

import java.util.UUID;

public class SoundArchetype implements Sound {

    @Override
    public UUID uuid() {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public String getInterfaceName() {
        return Sound.class.getCanonicalName();
    }

    @Override
    public SoundType soundType() {
        return null;
    }

    @Override
    public void play() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void pause() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPaused() {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPlaying() {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void stop() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void mute() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void unmute() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMuted() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isStopped() {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public double getVolume() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void setVolume(double volume)
            throws IllegalArgumentException, UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMillisecondLength() {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMillisecondPosition() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMillisecondPosition(int ms)
            throws IllegalArgumentException, UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean getIsLooping() throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIsLooping(boolean isLooping) throws UnsupportedOperationException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLoopingStopMs(Integer i) throws IllegalArgumentException {
        // stub method
        throw new UnsupportedOperationException();
    }

    @Override
    public void setLoopingRestartMs(int i) throws IllegalArgumentException {
        // stub method
        throw new UnsupportedOperationException();
    }

}

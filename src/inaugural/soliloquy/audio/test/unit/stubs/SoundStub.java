package inaugural.soliloquy.audio.test.unit.stubs;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class SoundStub implements Sound
{
	private final EntityUuid ENTITY_UUID;
	
	public SoundStub(EntityUuid entityUuid) {
		ENTITY_UUID = entityUuid;
	}

	@Override
	public EntityUuid id() {
		return ENTITY_UUID;
	}

	@Override
	public String getInterfaceName() {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public SoundType soundType() {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public void play() throws UnsupportedOperationException {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public Runnable playTask() {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public void pause() throws UnsupportedOperationException {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public Runnable pauseTask() {
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
	public Runnable stopTask() {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public void mute() throws UnsupportedOperationException {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public Runnable muteTask() {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public void unmute() throws UnsupportedOperationException {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public Runnable unmuteTask() {
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
	public void setVolume(double volume) throws IllegalArgumentException, UnsupportedOperationException {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public Runnable setVolumeTask(double volume) throws IllegalArgumentException {
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
	public void setMillisecondPosition(int ms) throws IllegalArgumentException, UnsupportedOperationException {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public Runnable setMillisecondPositionTask(int ms) throws IllegalArgumentException {
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
	public Runnable setIsLoopingTask(boolean isLooping) {
		// stub method
		throw new UnsupportedOperationException();
	}
}

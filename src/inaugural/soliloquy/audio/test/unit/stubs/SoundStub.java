package inaugural.soliloquy.audio.test.unit.stubs;

import soliloquy.audio.specs.ISound;
import soliloquy.common.specs.IEntityUuid;

public class SoundStub implements ISound
{
	private final IEntityUuid ENTITY_UUID;
	
	public SoundStub(IEntityUuid entityUuid) {
		ENTITY_UUID = entityUuid;
	}

	@Override
	public IEntityUuid id() {
		return ENTITY_UUID;
	}

	@Override
	public String getInterfaceName() {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public String soundTypeId() {
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
	public int getMillisecondLength() throws InterruptedException {
		// stub method
		throw new UnsupportedOperationException();
	}

	@Override
	public int getMillisecondPosition() throws InterruptedException, UnsupportedOperationException {
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

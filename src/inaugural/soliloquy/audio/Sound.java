package inaugural.soliloquy.audio;

import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.IEntityUuid;

public class Sound implements ISound {
	private final IEntityUuid ID;
	private final String SOUND_TYPE_ID;
	
	private final ISoundsPlaying SOUNDS_PLAYING;
	
	public Sound(IEntityUuid id, String soundTypeId, String filename, ISoundsPlaying soundsPlaying)
	{
		// TODO: Test to make sure that id is non-null
		ID = id;
		// TODO: Test to make sure that soundTypeId is non-null and non-empty
		SOUND_TYPE_ID = soundTypeId;
		
		SOUNDS_PLAYING = soundsPlaying;
	}

	public IEntityUuid id() throws IllegalStateException {
		// TODO Ensure that this is unit-tested
		return ID;
	}

	public String getInterfaceName() {
		// TODO Auto-generated method stub
		return null;
	}

	public String soundTypeId() {
		return SOUND_TYPE_ID;
	}

	public void play() {
		// TODO Auto-generated method stub
		
	}

	public Runnable playTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public void playAsClip() {
		// TODO Auto-generated method stub
		
	}

	public Runnable playAsClipTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public Runnable pauseTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return false;
	}

	public void stop() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	public Runnable stopTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public void mute() {
		// TODO Auto-generated method stub
		
	}

	public Runnable muteTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public void unmute() throws UnsupportedOperationException {
		// TODO Auto-generated method stub
		
	}

	public Runnable unmuteTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isMuted() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isStopped() {
		// TODO Auto-generated method stub
		return false;
	}

	public double getVolume() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setVolume(double volume) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	public Runnable setVolumeTask(double volume) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMillisecondLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getMillisecondPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setMillisecondPosition(int ms) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	public Runnable setMillisecondPositionTask(int ms) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isLooping() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setIsLooping(boolean isLooping) {
		// TODO Auto-generated method stub
		
	}

	public Runnable setIsLoopingTask(boolean isLooping) {
		// TODO Auto-generated method stub
		return null;
	}

}

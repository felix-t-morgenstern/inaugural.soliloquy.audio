package inaugural.soliloquy.audio;

import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.IEntityUuid;
import soliloquy.common.specs.IEntityUuidFactory;
import soliloquy.common.specs.IMap;
import soliloquy.common.specs.IPair;

public class SoundFactory implements ISoundFactory {
	private final IMap<String,String> SOUND_TYPE_FILENAMES;
	private final ISoundsPlaying SOUNDS_PLAYING;
	private final IEntityUuidFactory ENTITY_UUID_FACTORY;
	
	public SoundFactory(IMap<String,String> soundTypeFilenames, ISoundsPlaying soundsPlaying, IEntityUuidFactory entityUuidFactory) {
		SOUND_TYPE_FILENAMES = soundTypeFilenames;
		SOUNDS_PLAYING = soundsPlaying;
		ENTITY_UUID_FACTORY = entityUuidFactory;
	}
	
	SoundFactory() {
		SOUND_TYPE_FILENAMES = null;
		SOUNDS_PLAYING = null;
		ENTITY_UUID_FACTORY = null;
	}

	public ISound make(String soundTypeId) throws IllegalArgumentException {
		if (soundTypeId == null) {
			throw new IllegalArgumentException("SoundFactory.make: soundTypeId cannot be null");
		}
		if (!SOUND_TYPE_FILENAMES.containsKey(soundTypeId)) {
			throw new IllegalArgumentException("SoundFactory.make: Invalid soundTypeId provided");
		}
		String filename = SOUND_TYPE_FILENAMES.get(soundTypeId);
		if (filename == null) {
			throw new IllegalArgumentException("SoundFactory.make: soundTypeId must correspond to a valid (i.e. registered) sound type id");
		}
		IEntityUuid id = ENTITY_UUID_FACTORY.createRandomEntityUuid();
		ISound sound = new Sound(id, soundTypeId, filename, SOUNDS_PLAYING);
		SOUNDS_PLAYING.registerSound(sound);
		return sound;
	}

	public String getInterfaceName() {
		return "soliloquy.audio.specs.ISoundFactory";
	}

	@Override
	public void registerSoundTypes(IMap<String, String> soundTypesToFilenamesMap) throws IllegalArgumentException {
		for(IPair<String,String> mapping : soundTypesToFilenamesMap) {
			if (mapping.getItem1() == null) {
				throw new IllegalArgumentException("SoundFactory.registerSounds(): key cannot be null");
			}
			if (mapping.getItem1().equals("")) {
				throw new IllegalArgumentException("SoundFactory.registerSounds(): key cannot be empty");
			}
			if (mapping.getItem2() == null) {
				throw new IllegalArgumentException("SoundFactory.registerSounds(): value cannot be null");
			}
			if (mapping.getItem2().equals("")) {
				throw new IllegalArgumentException("SoundFactory.registerSounds(): value cannot be empty");
			}
		}
		for(IPair<String,String> mapping : soundTypesToFilenamesMap) {
			SOUND_TYPE_FILENAMES.put(mapping.getItem1(), mapping.getItem2());
		}
	}
	
	static ISound makeSoundArchetype() {
		SoundFactory nonfunctionalSoundFactory = new SoundFactory();
		ISound soundArchetype = nonfunctionalSoundFactory.new SoundArchetype();
		return soundArchetype;
	}
	
	public class SoundArchetype implements ISound {

		@Override
		public IEntityUuid id() {
			// stub method
			throw new UnsupportedOperationException();
		}

		@Override
		public String getInterfaceName() {
			return Sound.INTERFACE_NAME;
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
}

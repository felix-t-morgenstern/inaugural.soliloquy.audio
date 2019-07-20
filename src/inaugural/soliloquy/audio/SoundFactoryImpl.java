package inaugural.soliloquy.audio;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.infrastructure.Map;
import soliloquy.specs.common.infrastructure.Pair;
import soliloquy.specs.common.infrastructure.ReadableMap;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class SoundFactoryImpl implements SoundFactory {
	private final Map<String,String> SOUND_TYPE_FILENAMES;
	private final SoundsPlaying SOUNDS_PLAYING;
	private final EntityUuidFactory ENTITY_UUID_FACTORY;
	
	public SoundFactoryImpl(Map<String,String> soundTypeFilenames, SoundsPlaying soundsPlaying,
							EntityUuidFactory entityUuidFactory) {
		SOUND_TYPE_FILENAMES = soundTypeFilenames;
		SOUNDS_PLAYING = soundsPlaying;
		ENTITY_UUID_FACTORY = entityUuidFactory;
	}
	
	private SoundFactoryImpl() {
		SOUND_TYPE_FILENAMES = null;
		SOUNDS_PLAYING = null;
		ENTITY_UUID_FACTORY = null;
	}

	@SuppressWarnings("ConstantConditions")
	public Sound make(String soundTypeId) throws IllegalArgumentException {
		if (soundTypeId == null) {
			throw new IllegalArgumentException("SoundFactory.make: soundTypeId cannot be null");
		}
		if (!SOUND_TYPE_FILENAMES.containsKey(soundTypeId)) {
			throw new IllegalArgumentException("SoundFactory.make: Invalid soundTypeId provided");
		}
		String filename = SOUND_TYPE_FILENAMES.get(soundTypeId);
		if (filename == null) {
			throw new IllegalArgumentException(
					"SoundFactory.make: soundTypeId must correspond to a valid (i.e. registered) sound type id");
		}
		EntityUuid id = ENTITY_UUID_FACTORY.createRandomEntityUuid();
		Sound sound = new SoundImpl(id, soundTypeId, filename, SOUNDS_PLAYING);
		SOUNDS_PLAYING.registerSound(sound);
		return sound;
	}

	public String getInterfaceName() {
		return SoundFactory.class.getCanonicalName();
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void registerSoundTypes(ReadableMap<String, String> soundTypesToFilenamesMap)
			throws IllegalArgumentException {
		for(Pair<String,String> mapping : soundTypesToFilenamesMap) {
			if (mapping.getItem1() == null) {
				throw new IllegalArgumentException(
						"SoundFactory.registerSounds: key cannot be null");
			}
			if (mapping.getItem1().equals("")) {
				throw new IllegalArgumentException(
						"SoundFactory.registerSounds: key cannot be empty");
			}
			if (mapping.getItem2() == null) {
				throw new IllegalArgumentException(
						"SoundFactory.registerSounds: value cannot be null");
			}
			if (mapping.getItem2().equals("")) {
				throw new IllegalArgumentException(
						"SoundFactory.registerSounds: value cannot be empty");
			}
		}
		for(Pair<String,String> mapping : soundTypesToFilenamesMap) {
			SOUND_TYPE_FILENAMES.put(mapping.getItem1(), mapping.getItem2());
		}
	}
	
	static Sound makeSoundArchetype() {
		SoundFactoryImpl nonfunctionalSoundFactory = new SoundFactoryImpl();
		return nonfunctionalSoundFactory.new SoundArchetype();
	}

	// TODO: Move this class out to an archetypes namespace
	public class SoundArchetype implements Sound {

		@Override
		public EntityUuid id() {
			// stub method
			throw new UnsupportedOperationException();
		}

		@Override
		public String getInterfaceName() {
			return SoundImpl.INTERFACE_NAME;
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
}

package inaugural.soliloquy.audio;

import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundFactory;
import soliloquy.common.specs.IEntityUuid;
import soliloquy.common.specs.IEntityUuidFactory;
import soliloquy.common.specs.IMap;

public class SoundFactory implements ISoundFactory {
	private final IMap<String,String> SOUND_TYPE_FILENAMES;
	private final IEntityUuidFactory ENTITY_UUID_FACTORY;
	
	public SoundFactory(IMap<String,String> soundTypeFilenames, IEntityUuidFactory entityUuidFactory)
	{
		SOUND_TYPE_FILENAMES = soundTypeFilenames;
		ENTITY_UUID_FACTORY = entityUuidFactory;
	}

	public String getInterfaceName() {
		return "soliloquy.audio.specs.ISoundFactory";
	}

	public ISound make(String soundTypeId) throws IllegalArgumentException {
		if (soundTypeId == null)
		{
			throw new IllegalArgumentException("SoundFactory.make: soundTypeId cannot be null");
		}
		if (!SOUND_TYPE_FILENAMES.containsKey(soundTypeId))
		{
			throw new IllegalArgumentException("SoundFactory.make: Invalid soundTypeId provided");
		}
		String filename = SOUND_TYPE_FILENAMES.get(soundTypeId);
		IEntityUuid id = ENTITY_UUID_FACTORY.createRandomEntityUuid();
		return new Sound(id, soundTypeId, filename);
	}
}

package inaugural.soliloquy.audio;

import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.ICollection;
import soliloquy.common.specs.IEntityUuid;
import soliloquy.common.specs.IMap;
import soliloquy.common.specs.IMapFactory;

public class SoundsPlaying implements ISoundsPlaying {
	private final IMapFactory MAP_FACTORY;
	private final IEntityUuid ENTITY_UUID_ARCHETYPE;
	private final ISound SOUND_ARCHETYPE;
	
	private IMap<IEntityUuid,ISound> _soundsPlaying;
	
	public SoundsPlaying(IMapFactory mapFactory, IEntityUuid entityUuidArchetype, ISound soundArchetype)
	{
		MAP_FACTORY = mapFactory;
		ENTITY_UUID_ARCHETYPE = entityUuidArchetype;
		SOUND_ARCHETYPE = soundArchetype;
		
		_soundsPlaying = MAP_FACTORY.make(ENTITY_UUID_ARCHETYPE, SOUND_ARCHETYPE);
	}
	
	@Override
	public String getInterfaceName() {
		return "soliloquy.audio.specs.ISoundsPlaying";
	}

	@Override
	public ICollection<ISound> allSoundsPlaying() {
		return _soundsPlaying.getValues();
	}

	@Override
	public boolean isPlayingSound(IEntityUuid soundId) throws IllegalArgumentException {
		if (soundId == null)
		{
			throw new IllegalArgumentException("SoundsPlaying.isPlayingSound: soundId cannot be null");
		}
		return _soundsPlaying.containsKey(soundId);
	}

	@Override
	public ISound getSound(IEntityUuid soundId) throws IllegalArgumentException {
		if (soundId == null)
		{
			throw new IllegalArgumentException("SoundsPlaying.getSound: soundId cannot be null");
		}
		return _soundsPlaying.get(soundId);
	}

	@Override
	public void registerSound(ISound sound) throws IllegalArgumentException {
		_soundsPlaying.put(sound.id(), sound);
	}

	@Override
	public void removeSound(ISound sound) throws IllegalArgumentException {
		_soundsPlaying.removeByKeyAndValue(sound.id(), sound);
	}

}

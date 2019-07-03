package inaugural.soliloquy.audio;

import soliloquy.specs.audio.entities.ISound;
import soliloquy.specs.audio.entities.ISoundsPlaying;
import soliloquy.specs.common.factories.IMapFactory;
import soliloquy.specs.common.infrastructure.ICollection;
import soliloquy.specs.common.infrastructure.IMap;
import soliloquy.specs.common.valueobjects.IEntityUuid;

public class SoundsPlaying implements ISoundsPlaying {
	
	private IMap<IEntityUuid, ISound> _soundsPlaying;
	
	public SoundsPlaying(IMapFactory mapFactory, IEntityUuid entityUuidArchetype,
						 ISound soundArchetype) {
		_soundsPlaying = mapFactory.make(entityUuidArchetype, soundArchetype);
	}
	
	@Override
	public String getInterfaceName() {
		return "soliloquy.audio.specs.ISoundsPlaying";
	}

	@Override
	public ICollection<ISound> allSoundsPlaying() {
		return _soundsPlaying.getValues();
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public boolean isPlayingSound(IEntityUuid soundId) throws IllegalArgumentException {
		if (soundId == null) {
			throw new IllegalArgumentException(
					"SoundsPlaying.isPlayingSound: soundId cannot be null");
		}
		return _soundsPlaying.containsKey(soundId);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public ISound getSound(IEntityUuid soundId) throws IllegalArgumentException {
		if (soundId == null) {
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

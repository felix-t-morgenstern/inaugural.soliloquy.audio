package inaugural.soliloquy.audio;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.Collection;
import soliloquy.specs.common.infrastructure.Map;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class SoundsPlayingImpl implements SoundsPlaying {
	
	private Map<EntityUuid, Sound> _soundsPlaying;
	
	public SoundsPlayingImpl(MapFactory mapFactory, EntityUuid entityUuidArchetype,
							 Sound soundArchetype) {
		_soundsPlaying = mapFactory.make(entityUuidArchetype, soundArchetype);
	}
	
	@Override
	public String getInterfaceName() {
		return "soliloquy.audio.specs.ISoundsPlaying";
	}

	@Override
	public Collection<Sound> allSoundsPlaying() {
		return _soundsPlaying.getValues();
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public boolean isPlayingSound(EntityUuid soundId) throws IllegalArgumentException {
		if (soundId == null) {
			throw new IllegalArgumentException(
					"SoundsPlaying.isPlayingSound: soundId cannot be null");
		}
		return _soundsPlaying.containsKey(soundId);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public Sound getSound(EntityUuid soundId) throws IllegalArgumentException {
		if (soundId == null) {
			throw new IllegalArgumentException("SoundsPlaying.getSound: soundId cannot be null");
		}
		return _soundsPlaying.get(soundId);
	}

	@Override
	public void registerSound(Sound sound) throws IllegalArgumentException {
		_soundsPlaying.put(sound.id(), sound);
	}

	@Override
	public void removeSound(Sound sound) throws IllegalArgumentException {
		_soundsPlaying.removeByKeyAndValue(sound.id(), sound);
	}

}

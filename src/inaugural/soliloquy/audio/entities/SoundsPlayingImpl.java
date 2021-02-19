package inaugural.soliloquy.audio.entities;

import inaugural.soliloquy.tools.Check;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.List;
import soliloquy.specs.common.infrastructure.Map;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.util.Iterator;

public class SoundsPlayingImpl implements SoundsPlaying {
	private Map<EntityUuid, Sound> _soundsPlaying;
	
	@SuppressWarnings("ConstantConditions")
	public SoundsPlayingImpl(MapFactory mapFactory, EntityUuid entityUuidArchetype,
							 Sound soundArchetype) {
		_soundsPlaying = Check.ifNull(mapFactory, "mapFactory")
				.make(Check.ifNull(entityUuidArchetype, "entityUuidArchetype"),
						Check.ifNull(soundArchetype, "soundArchetype"));
	}
	
	@Override
	public String getInterfaceName() {
		return "soliloquy.audio.specs.ISoundsPlaying";
	}

	@Override
	public int size() {
		return _soundsPlaying.size();
	}

	@Override
	public List<Sound> representation() {
		return _soundsPlaying.getValuesList();
	}

	@Override
	public boolean isPlayingSound(EntityUuid soundId) throws IllegalArgumentException {
		return _soundsPlaying.containsKey(Check.ifNull(soundId, "soundId"));
	}

	@Override
	public Sound getSound(EntityUuid soundId) throws IllegalArgumentException {
		return _soundsPlaying.get(Check.ifNull(soundId, "soundId"));
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void registerSound(Sound sound) throws IllegalArgumentException {
		_soundsPlaying.put(Check.ifNull(sound, "sound").id(),
				sound);
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void removeSound(Sound sound) throws IllegalArgumentException {
		_soundsPlaying.remove(
				Check.ifNull(sound, "sound").id(),
				sound);
	}

	@Override
	public Iterator<Sound> iterator() {
		return _soundsPlaying.values().iterator();
	}
}

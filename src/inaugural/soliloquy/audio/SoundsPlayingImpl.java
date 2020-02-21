package inaugural.soliloquy.audio;

import inaugural.soliloquy.common.Check;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.infrastructure.Map;
import soliloquy.specs.common.infrastructure.ReadableCollection;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.util.Iterator;

public class SoundsPlayingImpl implements SoundsPlaying {
	
	private Map<EntityUuid, Sound> _soundsPlaying;
	
	public SoundsPlayingImpl(MapFactory mapFactory, EntityUuid entityUuidArchetype,
							 Sound soundArchetype) {
		_soundsPlaying = Check.ifNull(mapFactory, "SoundsPlayingImpl", null, "mapFactory")
				.make(Check.ifNull(entityUuidArchetype, "SoundsPlayingImpl", null,
						"entityUuidArchetype"),
						Check.ifNull(soundArchetype, "SoundsPlayingImpl", null, "soundArchetype"));
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
	public ReadableCollection<Sound> representation() {
		return _soundsPlaying.getValues().representation();
	}

	@Override
	public boolean isPlayingSound(EntityUuid soundId) throws IllegalArgumentException {
		return _soundsPlaying.containsKey(Check.ifNull(soundId, "SoundsPlayingImpl",
				"isPlayingSound", "soundId"));
	}

	@Override
	public Sound getSound(EntityUuid soundId) throws IllegalArgumentException {
		return _soundsPlaying.get(Check.ifNull(soundId, "SoundsPlayingImpl", "isPlayingSound",
				"soundId"));
	}

	@Override
	public void registerSound(Sound sound) throws IllegalArgumentException {
		_soundsPlaying.put(Check.ifNull(sound, "SoundsPlayingImpl", "registerSound", "sound").id(),
				sound);
	}

	@Override
	public void removeSound(Sound sound) throws IllegalArgumentException {
		_soundsPlaying.removeByKeyAndValue(
				Check.ifNull(sound, "SoundsPlayingImpl", "registerSound", "sound").id(),
				sound);
	}

	@Override
	public Iterator<Sound> iterator() {
		return _soundsPlaying.getValues().iterator();
	}
}

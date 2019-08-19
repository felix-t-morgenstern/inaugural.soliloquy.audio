package inaugural.soliloquy.audio;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.audio.factories.SoundFactory;
import soliloquy.specs.common.factories.EntityUuidFactory;
import soliloquy.specs.common.infrastructure.Registry;
import soliloquy.specs.common.valueobjects.EntityUuid;

public class SoundFactoryImpl implements SoundFactory {
	private final Registry<SoundType> SOUND_TYPES_REGISTRY;
	private final SoundsPlaying SOUNDS_PLAYING;
	private final EntityUuidFactory ENTITY_UUID_FACTORY;
	
	public SoundFactoryImpl(Registry<SoundType> soundTypesRegistry, SoundsPlaying soundsPlaying,
							EntityUuidFactory entityUuidFactory) {
		SOUND_TYPES_REGISTRY = soundTypesRegistry;
		SOUNDS_PLAYING = soundsPlaying;
		ENTITY_UUID_FACTORY = entityUuidFactory;
	}

	@SuppressWarnings("ConstantConditions")
	public Sound make(String soundTypeId) throws IllegalArgumentException {
		if (soundTypeId == null) {
			throw new IllegalArgumentException("SoundFactory.make: soundTypeId cannot be null");
		}
		if (!SOUND_TYPES_REGISTRY.contains(soundTypeId)) {
			throw new IllegalArgumentException("SoundFactory.make: Invalid soundTypeId provided");
		}
		SoundType soundType = SOUND_TYPES_REGISTRY.get(soundTypeId);
		if (soundType == null) {
			throw new IllegalArgumentException(
					"SoundFactory.make: soundTypeId must correspond to a valid (i.e. registered) sound type id");
		}
		EntityUuid id = ENTITY_UUID_FACTORY.createRandomEntityUuid();
		Sound sound = new SoundImpl(id, soundType, SOUNDS_PLAYING);
		SOUNDS_PLAYING.registerSound(sound);
		return sound;
	}

	public String getInterfaceName() {
		return SoundFactory.class.getCanonicalName();
	}
}

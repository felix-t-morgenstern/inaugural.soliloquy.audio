package inaugural.soliloquy.audio;

import inaugural.soliloquy.tools.Check;
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
		SOUND_TYPES_REGISTRY = Check.ifNull(soundTypesRegistry, "soundTypesRegistry");
		SOUNDS_PLAYING = Check.ifNull(soundsPlaying, "soundsPlaying");
		ENTITY_UUID_FACTORY = Check.ifNull(entityUuidFactory, "entityUuidFactory");
	}

	public Sound make(String soundTypeId) throws IllegalArgumentException {
		return make(soundTypeId, ENTITY_UUID_FACTORY.createRandomEntityUuid());
	}

	@Override
	public Sound make(String soundTypeId, EntityUuid entityUuid) throws IllegalArgumentException {
		Check.ifNullOrEmpty(soundTypeId, "soundTypeId");
		Check.ifNull(entityUuid, "entityUuid");
		if (!SOUND_TYPES_REGISTRY.contains(soundTypeId)) {
			throw new IllegalArgumentException("SoundFactoryImpl.make: Invalid soundTypeId provided");
		}
		SoundType soundType = SOUND_TYPES_REGISTRY.get(soundTypeId);
		if (soundType == null) {
			throw new IllegalArgumentException(
					"SoundFactoryImpl.make: soundTypeId must correspond to a valid (i.e. registered) sound type id");
		}
		Sound sound = new SoundImpl(entityUuid, soundType, SOUNDS_PLAYING);
		SOUNDS_PLAYING.registerSound(sound);
		return sound;
	}

	public String getInterfaceName() {
		return SoundFactory.class.getCanonicalName();
	}
}

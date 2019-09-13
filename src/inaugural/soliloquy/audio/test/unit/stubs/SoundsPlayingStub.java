package inaugural.soliloquy.audio.test.unit.stubs;

import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundsPlaying;
import soliloquy.specs.common.infrastructure.Collection;
import soliloquy.specs.common.valueobjects.EntityUuid;

import java.util.ArrayList;
import java.util.List;

public class SoundsPlayingStub implements SoundsPlaying {
	public List<Sound> SoundsRemoved = new ArrayList<>();
	public static Sound SoundRegistered;

	public String getInterfaceName() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public Collection<Sound> allSoundsPlaying() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public boolean isPlayingSound(EntityUuid soundId) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public Sound getSound(EntityUuid soundId) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public void removeSound(Sound sound) throws IllegalArgumentException {
		SoundsRemoved.add(sound);
	}

	@Override
	public void registerSound(Sound sound) throws IllegalArgumentException {
		SoundRegistered = sound;
	}
}

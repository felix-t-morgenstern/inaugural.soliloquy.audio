package inaugural.soliloquy.audio.test.unit.stubs;

import inaugural.soliloquy.audio.test.unit.SoundFactoryUnitTests;
import java.util.ArrayList;
import java.util.List;
import soliloquy.specs.audio.entities.ISound;
import soliloquy.specs.audio.entities.ISoundsPlaying;
import soliloquy.specs.common.infrastructure.ICollection;
import soliloquy.specs.common.valueobjects.IEntityUuid;

public class SoundsPlayingStub implements ISoundsPlaying {
	public List<ISound> SoundsRemoved = new ArrayList<>();

	public String getInterfaceName() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public ICollection<ISound> allSoundsPlaying() {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public boolean isPlayingSound(IEntityUuid soundId) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public ISound getSound(IEntityUuid soundId) throws IllegalArgumentException {
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	public void removeSound(ISound sound) throws IllegalArgumentException {
		SoundsRemoved.add(sound);
	}

	@Override
	public void registerSound(ISound sound) throws IllegalArgumentException {
		SoundFactoryUnitTests.SoundRegistered = sound;
	}
}

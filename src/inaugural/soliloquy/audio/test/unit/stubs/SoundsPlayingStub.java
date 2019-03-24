package inaugural.soliloquy.audio.test.unit.stubs;

import inaugural.soliloquy.audio.test.unit.SoundFactoryUnitTests;
import soliloquy.audio.specs.ISound;
import soliloquy.audio.specs.ISoundsPlaying;
import soliloquy.common.specs.ICollection;
import soliloquy.common.specs.IEntityUuid;

public class SoundsPlayingStub implements ISoundsPlaying {

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
		// Stub class; not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public void registerSound(ISound sound) throws IllegalArgumentException {
		SoundFactoryUnitTests.SoundRegistered = sound;
	}
}

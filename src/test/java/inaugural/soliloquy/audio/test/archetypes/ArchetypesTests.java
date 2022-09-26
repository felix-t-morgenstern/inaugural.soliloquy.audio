package inaugural.soliloquy.audio.test.archetypes;

import inaugural.soliloquy.audio.archetypes.SoundArchetype;
import inaugural.soliloquy.audio.archetypes.SoundTypeArchetype;
import inaugural.soliloquy.audio.archetypes.SoundsPlayingArchetype;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.entities.SoundsPlaying;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArchetypesTests {
    @Test
    void testSoundArchetype() {
        assertEquals(Sound.class.getCanonicalName(), new SoundArchetype().getInterfaceName());
    }

    @Test
    void testSoundsPlayingArchetype() {
        assertEquals(SoundsPlaying.class.getCanonicalName(),
                new SoundsPlayingArchetype().getInterfaceName());
    }

    @Test
    void testSoundTypeArchetype() {
        assertEquals(SoundType.class.getCanonicalName(),
                new SoundTypeArchetype().getInterfaceName());
    }
}

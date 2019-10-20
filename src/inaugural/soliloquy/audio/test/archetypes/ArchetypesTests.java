package inaugural.soliloquy.audio.test.archetypes;

import inaugural.soliloquy.audio.archetypes.SoundArchetype;
import inaugural.soliloquy.audio.archetypes.SoundTypeArchetype;
import org.junit.jupiter.api.Test;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArchetypesTests {
    @Test
    void testSoundArchetype() {
        assertEquals(Sound.class.getCanonicalName(), new SoundArchetype().getInterfaceName());
    }

    @Test
    void testSoundTypeArchetype() {
        assertEquals(SoundType.class.getCanonicalName(),
                new SoundTypeArchetype().getInterfaceName());
    }
}

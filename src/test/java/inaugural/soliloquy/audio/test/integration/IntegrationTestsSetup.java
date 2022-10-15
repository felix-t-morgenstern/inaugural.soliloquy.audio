package inaugural.soliloquy.audio.test.integration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import inaugural.soliloquy.audio.AudioModule;
import inaugural.soliloquy.audio.entities.SoundTypeImpl;
import inaugural.soliloquy.common.CommonModule;
import soliloquy.specs.audio.Audio;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.common.factories.RegistryFactory;

import java.net.URISyntaxException;

public class IntegrationTestsSetup {

    private final Audio AUDIO;

    public final static String SOUND_TYPE_1_ID = "SoundType1Id";

    public final static String SOUND_TYPE_2_ID = "SoundType2Id";
    public final Integer SOUND_TYPE_DEFAULT_LOOPING_STOP_MS = 14850;
    public final Integer SOUND_TYPE_DEFAULT_LOOPING_RESTART_MS = 7520;

    private final static String RESOURCES_PATH = "\\src\\test\\resources\\";
    private final static String RESOURCE = "Kevin_MacLeod_-_Living_Voyage.mp3";
    private final static String RESOURCE_LOOPING =
            "exit-the-premises-by-kevin-macleod-from-filmmusic-io.mp3";

    public IntegrationTestsSetup() throws URISyntaxException {
        Injector commonInjector = Guice.createInjector(new CommonModule());

        Injector audioInjector = Guice.createInjector(
                new AudioModule(commonInjector.getInstance(RegistryFactory.class)));

        AUDIO = audioInjector.getInstance(Audio.class);
    }

    public Audio audio() {
        return AUDIO;
    }

    public SoundType sampleSoundType() {
        return new SoundTypeImpl(SOUND_TYPE_1_ID, RESOURCES_PATH + RESOURCE, null, null);
    }

    public SoundType sampleLoopingSoundType() {
        return new SoundTypeImpl(SOUND_TYPE_2_ID, RESOURCES_PATH + RESOURCE_LOOPING,
                SOUND_TYPE_DEFAULT_LOOPING_STOP_MS, SOUND_TYPE_DEFAULT_LOOPING_RESTART_MS);
    }

    public Sound sampleSound() {
        AUDIO.soundTypes().add(sampleSoundType());

        return AUDIO.soundFactory().make(SOUND_TYPE_1_ID);
    }
}

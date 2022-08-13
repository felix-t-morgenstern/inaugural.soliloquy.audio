package inaugural.soliloquy.audio.test.integration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import inaugural.soliloquy.audio.AudioModule;
import inaugural.soliloquy.audio.entities.SoundTypeImpl;
import inaugural.soliloquy.common.CommonModule;
import soliloquy.specs.audio.Audio;
import soliloquy.specs.audio.entities.Sound;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.common.factories.MapFactory;
import soliloquy.specs.common.factories.RegistryFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class IntegrationTestsSetup {

    private final Audio AUDIO;

    public final static String SOUND_TYPE_1_ID = "SoundType1Id";

    public final static String SOUND_TYPE_2_ID = "SoundType2Id";
    public final Integer SOUND_TYPE_DEFAULT_LOOPING_STOP_MS = 14850;
    public final Integer SOUND_TYPE_DEFAULT_LOOPING_RESTART_MS = 7520;

    private final static String RESOURCE = "Kevin_MacLeod_-_Living_Voyage.mp3";
    private final static String RESOURCE_LOOPING =
            "exit-the-premises-by-kevin-macleod-from-filmmusic-io.mp3";

    private String _soundType1Filename;
    private String _soundType2Filename;

    @SuppressWarnings("ConstantConditions")
    public IntegrationTestsSetup() throws URISyntaxException {
        _soundType1Filename = new File(String.valueOf(
                Paths.get(getClass().getClassLoader().getResource(RESOURCE).toURI())))
                .getAbsolutePath();
        _soundType2Filename = new File(String.valueOf(
                Paths.get(getClass().getClassLoader().getResource(RESOURCE_LOOPING).toURI())))
                .getAbsolutePath();

        Injector commonInjector = Guice.createInjector(new CommonModule());

        Injector audioInjector = Guice.createInjector(new AudioModule(
                commonInjector.getInstance(MapFactory.class),
                commonInjector.getInstance(RegistryFactory.class)));

        AUDIO = audioInjector.getInstance(Audio.class);
    }

    public Audio audio() {
        return AUDIO;
    }

    public SoundType sampleSoundType() {
        return new SoundTypeImpl(SOUND_TYPE_1_ID, _soundType1Filename,
                null, null);
    }

    public SoundType sampleLoopingSoundType() {
        return new SoundTypeImpl(SOUND_TYPE_2_ID, _soundType2Filename,
                SOUND_TYPE_DEFAULT_LOOPING_STOP_MS, SOUND_TYPE_DEFAULT_LOOPING_RESTART_MS);
    }

    public Sound sampleSound() {
        AUDIO.soundTypes().add(sampleSoundType());

        return AUDIO.soundFactory().make(SOUND_TYPE_1_ID);
    }

    Sound sampleLoopingSound() {
        AUDIO.soundTypes().add(sampleLoopingSoundType());

        return AUDIO.soundFactory().make(SOUND_TYPE_2_ID);
    }
}

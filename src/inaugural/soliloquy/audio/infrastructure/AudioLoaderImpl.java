package inaugural.soliloquy.audio.infrastructure;

import inaugural.soliloquy.tools.Check;
import org.apache.commons.io.FilenameUtils;
import soliloquy.specs.audio.entities.SoundType;
import soliloquy.specs.audio.factories.SoundTypeFactory;
import soliloquy.specs.audio.infrastructure.AudioLoader;
import soliloquy.specs.common.infrastructure.Registry;

import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AudioLoaderImpl implements AudioLoader {
    private final Registry<SoundType> SOUND_TYPES_REGISTRY;
    private final SoundTypeFactory SOUND_TYPE_FACTORY;

    private final Set<String> FILETYPES;
    private final Map<String, Integer> DEFAULT_LOOPING_STOP_MS_FOR_IDS;
    private final Map<String, Integer> DEFAULT_LOOPING_RESTART_MS_FOR_IDS;

    public AudioLoaderImpl(Registry<SoundType> soundTypesRegistry,
                           SoundTypeFactory soundTypeFactory) {
        SOUND_TYPES_REGISTRY = Check.ifNull(soundTypesRegistry, "soundTypesRegistry");
        SOUND_TYPE_FACTORY = Check.ifNull(soundTypeFactory, "soundTypeFactory");

        FILETYPES = new HashSet<>();
        DEFAULT_LOOPING_STOP_MS_FOR_IDS = new HashMap<>();
        DEFAULT_LOOPING_RESTART_MS_FOR_IDS = new HashMap<>();
    }

    public Set<String> filetypes() {
        return FILETYPES;
    }

    @SuppressWarnings("ConstantConditions")
    public void setDefaultLoopingStopMsForIds(Map<String, Integer> defaultLoopingStopMsForIds) {
        DEFAULT_LOOPING_STOP_MS_FOR_IDS.putAll(
                Check.ifNull(defaultLoopingStopMsForIds, "defaultLoopingStopMsForIds"));
    }

    @SuppressWarnings("ConstantConditions")
    public void setDefaultLoopingRestartMsForIds(
            Map<String, Integer> defaultLoopingRestartMsForIds) {
        DEFAULT_LOOPING_RESTART_MS_FOR_IDS.putAll(
                Check.ifNull(defaultLoopingRestartMsForIds, "defaultLoopingRestartMsForIds"));
    }

    public void loadFromDirectory(String relativePath, Map<String, String[]> idsForFilenames) {
        String absolutePath = getLocalDirectory() + "\\" + relativePath.replace("\\", "/");
        File[] filesWithProperExtension =
                new File(absolutePath).listFiles(new SoundsLoaderFilenameFilter());
        assert filesWithProperExtension != null;
        for (File fileWithProperExtension : filesWithProperExtension) {
            String fileWithProperExtensionName = fileWithProperExtension.getName();
            if (idsForFilenames.containsKey(fileWithProperExtensionName)) {
                String[] idsForFilename = idsForFilenames.get(fileWithProperExtensionName);
                for (String idForFilename : idsForFilename) {
                    SOUND_TYPES_REGISTRY.add(SOUND_TYPE_FACTORY.make(idForFilename,
                            fileWithProperExtension.getAbsolutePath(),
                            DEFAULT_LOOPING_STOP_MS_FOR_IDS.get(idForFilename),
                            DEFAULT_LOOPING_RESTART_MS_FOR_IDS.get(idForFilename)));
                }
            }
        }
    }

    private String getLocalDirectory() {
        try {
            return new File(AudioLoaderImpl.class.getProtectionDomain().getCodeSource().getLocation()
                    .toURI()).getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public String getInterfaceName() {
        return AudioLoader.class.getCanonicalName();
    }

    private class SoundsLoaderFilenameFilter implements FileFilter {
        @Override
        public boolean accept(File file) {
            return !file.isDirectory() &&
                    FILETYPES.contains(FilenameUtils.getExtension(file.getName()));
        }
    }
}

package ru.colibri.ui.settings.loaders;

import lombok.extern.java.Log;
import org.springframework.beans.factory.InitializingBean;
import ru.colibri.ui.core.settings.TestSettings;
import ru.colibri.ui.settings.general.PropertyUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.apache.commons.io.FileUtils.copyURLToFile;

@Log
public abstract class AbsSettingsLoader implements ISettingsLoader, InitializingBean {
    protected static final String PATH_TEMPLATE = "src/test/resources/environment/%s/device.properties";
    protected static final String PATH_USER = "src/test/resources/users/%s.properties";
    private static final String TEST_TYPE_FILTER = "src/test/resources/planTestCycle/testCycle.properties";

    protected Map<String, String> convertPropertyToMap(Properties properties) {
        return properties.entrySet().stream().collect(
                Collectors.toMap(
                        e -> e.getKey().toString(),
                        e -> e.getValue().toString()
                )
        );
    }

    protected void takeArtifact(String from, String to) {
        try {
            copyURLToFile(new URL(from), new File(to));
        } catch (IOException e) {
            log.log(Level.WARNING, "Error loading file.", e);
        }

    }

    @Override
    public TestSettings loadTestSettings(String testType) {
        Properties props = PropertyUtils.readProperty(TEST_TYPE_FILTER);
        List<String> testCycleFilters = asList(props.getProperty(testType).split(","));
        return TestSettings.builder()
                .flagsMetaFilters(testCycleFilters)
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(format("Bean %s loaded", this.getClass().getSimpleName()));
    }
}

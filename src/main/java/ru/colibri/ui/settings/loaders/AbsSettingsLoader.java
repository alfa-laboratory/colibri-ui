package ru.colibri.ui.settings.loaders;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import ru.colibri.ui.core.exception.PropertyNotFoundException;
import ru.colibri.ui.core.settings.TestSettings;
import ru.colibri.ui.settings.general.PropertyUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static java.lang.String.format;
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
        if (StringUtils.isEmpty(testType))
            throw new IllegalArgumentException("Не задан тестовый цикл, проверьте данные");
        List<String> testCycleFilters = getTestCycleFilters(testType);
        return TestSettings.builder()
                .flagsMetaFilters(testCycleFilters)
                .build();
    }

    private List<String> getTestCycleFilters(String testType) {
        if (testType.matches(".*?[+-].*?")) {
            return getFreeTypeTestCycleFilters(testType);
        } else {
            return getTestCycleFiltersFromProperty(testType);
        }
    }

    private List<String> getTestCycleFiltersFromProperty(String testType) {
        Properties props = PropertyUtils.readProperty(TEST_TYPE_FILTER);
        String testCycle = props.getProperty(testType);
        if (StringUtils.isEmpty(testCycle)) {
            throw new PropertyNotFoundException(testType, TEST_TYPE_FILTER);
        }
        return getFreeTypeTestCycleFilters(testCycle);
    }

    private List<String> getFreeTypeTestCycleFilters(String testCycle) {
        List<String> testCycleFilters = Arrays.stream(testCycle.split(",")).collect(Collectors.toList());
        checkTestCycleFiltersSyntax(testCycleFilters);
        return testCycleFilters;
    }

    private void checkTestCycleFiltersSyntax(List<String> testCycleFilters) {
        boolean correctFiltersSyntax = testCycleFilters.stream()
                .allMatch(filter -> filter.matches("^[+-]\\w*"));
        if (!correctFiltersSyntax) {
            throw new IllegalArgumentException("Неправильный синтаксис фильтров тестового цикла, проверьте данные");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(format("Bean %s loaded", this.getClass().getSimpleName()));
    }
}
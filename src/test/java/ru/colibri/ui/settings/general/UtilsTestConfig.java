package ru.colibri.ui.settings.general;

import io.appium.java_client.AppiumDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.colibri.ui.core.builders.ElementBuilders;
import ru.colibri.ui.core.contexts.TestContext;
import ru.colibri.ui.core.settings.AppSettings;
import ru.colibri.ui.core.utils.FileUtils;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

@Configuration
public class UtilsTestConfig {

    @Bean
    public PropertyUtils getPropertyUtils() {
        return new PropertyUtils();
    }

    @Bean
    public AppSettings getFakeAppSettings() {
        return AppSettings.builder().packageName("ru.company.android").userProfile(createFakeUser()).build();
    }

    @Bean
    public AppiumDriver getAppiumDriver() {
        return mock(AppiumDriver.class);
    }

    @Bean
    public TestContext getTestContext() {
        return mock(TestContext.class);
    }

    @Bean
    public PagesLoader getPagesLoader() {
        return new PagesLoader();
    }

    @Bean
    public FileUtils getFileUtils() {
        return new FileUtils();
    }

    @Bean
    public ElementBuilders getElementBuilders() {
        return new ElementBuilders();
    }

    private Map<String, String> createFakeUser() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Иииигорь");
        return map;
    }


}

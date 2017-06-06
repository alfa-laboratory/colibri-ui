package ru.colibri.ui.settings.general;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.colibri.ui.core.settings.AppSettings;

import java.util.HashMap;
import java.util.Map;

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

    private Map<String, String> createFakeUser() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "Иииигорь");
        return map;
    }


}

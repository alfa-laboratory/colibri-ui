package ru.colibri.ui.settings.android;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.colibri.ui.core.settings.AppSettings;

@Configuration
public class AndroidTestConfig {

    @Bean
    public AppSettings getFakeAppSettings() {
        return AppSettings.builder().packageName("ru.alfabank.mobile.android").build();
    }

    @Bean
    public AndroidByFactory getAndroidByFactory() {
        return new AndroidByFactory();
    }
}

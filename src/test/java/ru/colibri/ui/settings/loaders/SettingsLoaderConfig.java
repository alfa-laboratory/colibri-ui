package ru.colibri.ui.settings.loaders;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SettingsLoaderConfig {

    @Bean
    ISettingsLoader loader() {
        return new AbsSettingsLoaderFake();
    }

}

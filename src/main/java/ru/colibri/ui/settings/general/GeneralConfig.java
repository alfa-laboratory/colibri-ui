package ru.colibri.ui.settings.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.colibri.ui.settings.server.AppiumServer;

@Configuration
@ComponentScan(basePackages = {"ru.colibri.ui.core", "ru.colibri.ui.settings.general", "ru.colibri.ui.steps.general", "ru.colibri.ui.settings.server"})
public class GeneralConfig {

    @Autowired
    private AppiumServer server;

    @Bean
    public AppiumServer getAppiumServer() {
        AppiumServer server = new AppiumServer();
        server.startServer();
        return server;
    }
}

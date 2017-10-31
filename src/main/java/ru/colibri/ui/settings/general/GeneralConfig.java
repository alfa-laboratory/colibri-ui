package ru.colibri.ui.settings.general;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.colibri.ui.core", "ru.colibri.ui.settings.general",
        "ru.colibri.ui.steps.general", "ru.colibri.ui.settings.server"})
public class GeneralConfig {
}
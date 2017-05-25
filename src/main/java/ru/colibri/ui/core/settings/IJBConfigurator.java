package ru.colibri.ui.core.settings;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.EmbedderControls;

public interface IJBConfigurator {
    Configuration createConfig();

    void configure(EmbedderControls embedderControls, DriversSettings driversSettings);
}

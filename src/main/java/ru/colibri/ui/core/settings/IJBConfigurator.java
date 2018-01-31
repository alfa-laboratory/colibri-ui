package ru.colibri.ui.core.settings;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.reporters.Format;

public interface IJBConfigurator {
    Configuration createConfig();

    Configuration createConfig(Format... formats);

    void configure(EmbedderControls embedderControls, DriversSettings driversSettings);
}

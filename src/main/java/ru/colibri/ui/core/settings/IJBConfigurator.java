package ru.colibri.ui.core.settings;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.ViewGenerator;

public interface IJBConfigurator {

    Configuration createConfig();

    Configuration createConfig(ViewGenerator viewGenerator, Format... formats);

    void configure(EmbedderControls embedderControls, DriversSettings driversSettings);
}

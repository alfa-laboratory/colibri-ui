package ru.colibri.ui.settings.general;

import com.epam.reportportal.jbehave.ReportPortalViewGenerator;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.EmbedderControls;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.ParameterConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.reporters.AllureReporter;
import ru.colibri.ui.core.settings.DriversSettings;
import ru.colibri.ui.core.settings.IJBConfigurator;

@Component
public class JBConfiguratorImpl implements IJBConfigurator {
    @Autowired
    private AllureReporter allureReporter;

    @Override
    public void configure(EmbedderControls embedderControls, DriversSettings driversSettings) {
        embedderControls.doBatch(false);
        embedderControls.doGenerateViewAfterStories(true);
        embedderControls.doIgnoreFailureInStories(false);
        embedderControls.doIgnoreFailureInView(false);
        embedderControls.doSkip(false);
        embedderControls.doVerboseFailures(false);
        embedderControls.doVerboseFiltering(false);
        embedderControls.useStoryTimeouts(driversSettings.getStoryTimeoutsInSeconds());
        embedderControls.useThreads(1);
    }

    @Override
    public Configuration createConfig() {
        Configuration configuration = new Configuration() {
        };
        configuration.useStoryReporterBuilder(
                new StoryReporterBuilder()
                        .withDefaultFormats()
                        .withFormats(Format.CONSOLE)
                        .withReporters(allureReporter));
        configuration.useStoryControls(new StoryControls().doResetStateBeforeScenario(true));
        configuration.parameterConverters().addConverters(new ParameterConverters.EnumConverter());
        return configuration;
    }

    @Override
    public Configuration createConfig(Format... formats) {
        Configuration configuration = new MostUsefulConfiguration();
        configuration.useStoryReporterBuilder(
                new StoryReporterBuilder()
                        .withFormats(formats))
                .useViewGenerator(new ReportPortalViewGenerator());
        configuration.useStoryControls(new StoryControls().doResetStateBeforeScenario(true));
        configuration.parameterConverters().addConverters(new ParameterConverters.EnumConverter());
        return configuration;
    }

}

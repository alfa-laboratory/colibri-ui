package ru.colibri.ui.core.reporters;

import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReportPortalFormat extends Format {

    @Autowired
    private ReportPortalStoryReporterDecorator reportPortalReporter;

    public ReportPortalFormat() {
        super("REPORT PORTAL");
    }

    @Override
    public StoryReporter createStoryReporter(FilePrintStreamFactory factory, StoryReporterBuilder storyReporterBuilder) {
        return reportPortalReporter;
    }

}

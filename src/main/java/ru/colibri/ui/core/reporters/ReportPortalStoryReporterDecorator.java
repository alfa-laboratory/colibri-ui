package ru.colibri.ui.core.reporters;

import com.epam.reportportal.jbehave.ReportPortalStoryReporter;
import com.epam.reportportal.service.ReportPortal;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Calendar;

@Component
public class ReportPortalStoryReporterDecorator extends ReportPortalStoryReporter {

    @Autowired
    private AppiumDriver driver;

    @Override
    public void failed(String step, Throwable cause) {
        File file = driver.getScreenshotAs(OutputType.FILE);
        ReportPortal.emitLog("", "INFO",
                Calendar.getInstance().getTime(),
                file);
        super.failed(step, cause);
    }
}

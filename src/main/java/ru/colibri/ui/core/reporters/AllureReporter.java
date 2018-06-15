package ru.colibri.ui.core.reporters;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.StringUtils;
import org.jbehave.core.model.*;
import org.jbehave.core.reporters.NullStoryReporter;
import org.jbehave.core.reporters.StoryReporter;
import org.openqa.selenium.OutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.config.AllureModelUtils;
import ru.yandex.qatools.allure.events.*;
import ru.yandex.qatools.allure.junit.AllureRunListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class AllureReporter extends NullStoryReporter {
    private final Map<String, String> suites = new HashMap<>();
    @Autowired
    private ApplicationContext applicationContext;
    private Allure allure = Allure.LIFECYCLE;
    private String uid;

    @Override
    public void beforeStory(Story story, boolean givenStory) {
        uid = generateSuiteUid(story);
        String path = story.getPath();
        int secondIndex = StringUtils.ordinalIndexOf(path, "/", 2);
        String subPath = path.substring(secondIndex + 1);
        TestSuiteStartedEvent event = new TestSuiteStartedEvent(uid, story.getName());
        event.withLabels(AllureModelUtils.createTestFrameworkLabel("JBehave"));
        event.withTitle(subPath);
        allure.fire(event);
    }

    @Override
    public void afterStory(boolean givenStory) {
        allure.fire(new TestSuiteFinishedEvent(uid));
    }

    @Override
    public void beforeScenario(Scenario scenario) {
        allure.fire(new TestCaseStartedEvent(uid, scenario.getTitle()));
        allure.fire(new ClearStepStorageEvent());
    }

    @Override
    public void beforeStep(String step) {
        allure.fire(new StepStartedEvent(step).withTitle(step));
    }

    @Override
    public void successful(String step) {
        allure.fire(new StepFinishedEvent());
    }

    @Override
    public void ignorable(String step) {
        allure.fire(new StepCanceledEvent());
    }

    @Override
    public void notPerformed(String step) {
        allure.fire(new StepCanceledEvent());
    }

    @Override
    public void failed(String step, Throwable cause) {
        takeScreenshot(step);
        allure.fire(new StepFinishedEvent());
        allure.fire(new StepFailureEvent().withThrowable(cause.getCause()));
        allure.fire(new TestCaseFailureEvent().withThrowable(cause.getCause()));

    }

    @Override
    public void pending(String step) {
        allure.fire(new StepCanceledEvent());
        allure.fire(new TestCasePendingEvent().withMessage("PENDING"));
    }

    @Override
    public void afterScenario() {
        allure.fire(new TestCaseFinishedEvent());
    }


    /**
     * Generate suite uid.
     *
     * @param story the story
     * @return the string
     */
    public String generateSuiteUid(Story story) {
        String uId = UUID.randomUUID().toString();
        synchronized (getSuites()) {
            getSuites().put(story.getPath(), uId);
        }
        return uId;
    }

    public Map<String, String> getSuites() {
        return suites;
    }

    public void takeScreenshot(String step) {
        if (applicationContext.getBean(AppiumDriver.class) != null) {
            Allure.LIFECYCLE.fire(new MakeAttachmentEvent((applicationContext.getBean(AppiumDriver.class)).getScreenshotAs(OutputType.BYTES), step, "image/png"));
        }
    }
}

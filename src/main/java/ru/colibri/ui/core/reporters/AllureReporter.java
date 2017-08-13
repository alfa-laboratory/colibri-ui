package ru.colibri.ui.core.reporters;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.StringUtils;
import org.jbehave.core.model.*;
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
public class AllureReporter extends AllureRunListener implements StoryReporter {
    private final Map<String, String> suites = new HashMap<>();
    @Autowired
    private ApplicationContext applicationContext;
    private Allure allure = Allure.LIFECYCLE;
    private String uid;

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

    public void afterStory(boolean givenStory) {
        allure.fire(new TestSuiteFinishedEvent(uid));
    }

    public void beforeScenario(String scenarioTitle) {
        allure.fire(new TestCaseStartedEvent(uid, scenarioTitle));
        allure.fire(new ClearStepStorageEvent());
    }

    public void beforeStep(String step) {
        allure.fire(new StepStartedEvent(step).withTitle(step));
    }

    public void successful(String step) {
        allure.fire(new StepFinishedEvent());
    }

    public void ignorable(String step) {
        allure.fire(new StepCanceledEvent());
    }

    @Override
    public void comment(String step) {

    }

    public void notPerformed(String step) {
        allure.fire(new StepCanceledEvent());
    }

    public void failed(String step, Throwable cause) {
        takeScreenshot(step);
        allure.fire(new StepFinishedEvent());
        allure.fire(new StepFailureEvent().withThrowable(cause.getCause()));
        allure.fire(new TestCaseFailureEvent().withThrowable(cause.getCause()));

    }


    public void pending(String step) {
        allure.fire(new StepCanceledEvent());
        allure.fire(new TestCasePendingEvent().withMessage("PENDING"));
    }


    public void afterScenario() {
        allure.fire(new TestCaseFinishedEvent());
    }


    public void storyNotAllowed(Story story, String filter) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }


    public void storyCancelled(Story story, StoryDuration storyDuration) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void narrative(Narrative narrative) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void lifecyle(Lifecycle lifecycle) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void scenarioNotAllowed(Scenario scenario, String filter) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void scenarioMeta(Meta meta) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void givenStories(GivenStories givenStories) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void givenStories(List<String> storyPaths) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void beforeExamples(List<String> steps, ExamplesTable table) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void example(Map<String, String> tableRow) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void afterExamples() {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void failedOutcomes(String step, OutcomesTable table) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void restarted(String step, Throwable cause) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void restartedStory(Story story, Throwable cause) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void dryRun() {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
    }

    @Override
    public void pendingMethods(List<String> methods) {
//        throw new NotImplementedException("Method not use. Allure doesn't support this");
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

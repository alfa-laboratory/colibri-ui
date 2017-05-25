package ru.colibri.ui.steps.general;

import org.jbehave.core.annotations.Given;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class AppSteps extends AbsSteps {

    @Step
    @Given("приложение запущено")
    public void applicationStarted() {
    }


    @Given("приложение перезапущено")
    public void applicationRestarted() {
        driver.closeApp();
        driver.launchApp();
    }
}
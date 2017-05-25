package ru.colibri.ui.steps.ios;

import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class TechnicalSteps extends AbsSteps {

    @Step
    @When("распечатать разметку экрана")
    public void printMarkup() {
        System.out.println(driver.getPageSource());
    }
}

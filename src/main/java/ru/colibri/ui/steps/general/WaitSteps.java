package ru.colibri.ui.steps.general;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class WaitSteps extends AbsSteps {

    @Step
    @When("ожидаем $timeInterval {секунд|секунду|секунды}")
    @Alias("подождать timeInterval {секунд|секунду|секунды}")
    public void waitSomeTime(int timeInterval) throws InterruptedException {
        Thread.sleep(timeInterval * 1000);
    }
}
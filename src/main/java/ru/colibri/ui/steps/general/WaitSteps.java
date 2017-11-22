package ru.colibri.ui.steps.general;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class WaitSteps extends AbsSteps {

    @Step
    @When("{ожидаем|подождать} $timeInterval {секунд|секунду|секунды}")
    public void waitSomeTime(@Named("$timeInterval") int timeInterval) throws InterruptedException {
        Thread.sleep(timeInterval * 1000);
    }
}
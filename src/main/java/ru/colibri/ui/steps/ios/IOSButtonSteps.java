package ru.colibri.ui.steps.ios;

import io.appium.java_client.MobileBy;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class IOSButtonSteps extends AbsSteps {

    private String readyButton = "name == 'Готово' or name == 'Done'";
    private String cancelButton = "name == 'Отмена' or name == 'Cancel'";

    @Step
    @When("нажата кнопка Готово")
    public void clickOnReadyButton() {
        driver.findElement(MobileBy.iOSNsPredicateString(readyButton)).click();
    }

    @Step
    @When("нажата кнопка Отмена")
    public void clickOnCancelButton() {
        driver.findElement(MobileBy.iOSNsPredicateString(cancelButton)).click();
    }
}
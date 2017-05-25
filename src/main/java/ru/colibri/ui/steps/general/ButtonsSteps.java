package ru.colibri.ui.steps.general;


import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class ButtonsSteps extends AbsSteps {

    @Step
    @When("выполнено нажатие на \"$button\"")
    public void buttonClick(@Named("$button") String button) {
        getWebElementByName(button).click();
    }

    @Step
    @When("(Optional) выполнено нажатие на \"$button\"")
    public void optionalButtonClick(@Named("$button") String button) {
        try {
            buttonClick(button);
        } catch (Exception ignored) {
            System.out.println("Не нажато");
        }

    }
}

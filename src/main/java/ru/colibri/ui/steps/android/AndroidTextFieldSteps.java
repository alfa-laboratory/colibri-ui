package ru.colibri.ui.steps.android;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import ru.colibri.ui.steps.general.TextFieldSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class AndroidTextFieldSteps extends TextFieldSteps {

    private static final By editTextLocator = By.xpath("//*[contains(@class,'EditText')]");

    @Step
    @When("ввод текста с клавиатуры \"$textOrKeyword\"")
    public void sendKeyboardText(@Named("$textOrKeyword") String textOrKeyword) {
        sendText(textOrKeyword, editTextLocator);
    }
}
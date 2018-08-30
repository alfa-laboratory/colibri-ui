package ru.colibri.ui.steps.android;

import lombok.extern.java.Log;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import ru.colibri.ui.steps.general.TextFieldSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Log
@Component
public class AndroidTextFieldSteps extends TextFieldSteps {

    private static final By editTextLocator = By.xpath("//*[contains(@class,'EditText')]");

    @Step
    @When("поле \"$field\" заполняется значением \"$valueOrKeyword\"")
    public void fillByValue(@Named("$field") String field, @Named("$valueOrKeyword") String valueOrKeyword) {
        sendKeys(field, valueOrKeyword);
    }

    @Step
    @When("(Optional) поле \"$field\" заполняется значением \"$valueOrKeyword\"")
    public void optionalFillByValue(@Named("$field") String field, @Named("$valueOrKeyword") String valueOrKeyword) {
        optionalSendKeys(field, valueOrKeyword);
    }

    @Step
    @When("ввод текста с клавиатуры \"$textOrKeyword\"")
    public void sendKeyboardText(@Named("$textOrKeyword") String textOrKeyword) {
        sendText(textOrKeyword, editTextLocator);
    }
}
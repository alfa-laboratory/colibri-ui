package ru.colibri.ui.steps.general;

import io.appium.java_client.MobileElement;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class TextFieldSteps extends AbsSteps {
    @Autowired
    private PropertyUtils propertyUtils;

    @Step
    @When("поле \"$field\" заполняется значением \"$valueOrKeyword\"")
    public void sendKeys(@Named("$field") String field, @Named("$valueOrKeyword") String valueOrKeyword) {
        WebElement webElement = getWebElementByName(field);
        String value = propertyUtils.injectProperties(valueOrKeyword);
        ((MobileElement) webElement).setValue(value);
    }


    @Step
    @When("(Optional) поле \"$field\" заполняется значением \"$valueOrKeyword\"")
    public void optionalSendKeys(@Named("$field") String field, @Named("$valueOrKeyword") String valueOrKeyword) {
        try {
            sendKeys(field, valueOrKeyword);
        } catch (Exception ignored) {
            System.out.println("Не удалось ввести");
        }
    }

    @Step
    @When("ввод текста с клавиатуры \"$textOrKeyword\"")
    public void sendText(@Named("$textOrKeyword") String textOrKeyword) {
        String text = propertyUtils.injectProperties(textOrKeyword);
        driver.getKeyboard().sendKeys(text);
    }

    @Step
    @When("очистить \"$fieldName\"")
    public void clearField(@Named("$fieldName") String fieldName) {
        WebElement webElement = getWebElementByName(fieldName);
        webElement.clear();
    }
}
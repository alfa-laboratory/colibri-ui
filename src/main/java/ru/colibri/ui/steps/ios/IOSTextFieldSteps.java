package ru.colibri.ui.steps.ios;

import io.appium.java_client.MobileBy;
import lombok.extern.java.Log;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.colibri.ui.steps.general.TextFieldSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Log
@Component
public class IOSTextFieldSteps extends TextFieldSteps {

    private static final By editTextLocator = MobileBy.iOSNsPredicateString("type like 'XCUIElementTypeTextField'");

    @Autowired
    private PropertyUtils propertyUtils;

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

    @Step
    @When("ввод текста \"$textOrKeyword\" в поле \"$field\"")
    public void sendTextToField(@Named("$textOrKeyword") String textOrKeyword, @Named("$field") String field) {
        String text = propertyUtils.injectProperties(textOrKeyword);

        String xpath = "//*[@type='XCUIElementTypeCell' and child::*[@name='Сумма']]";
        try {
            WebElement element = driver.findElementByXPath(xpath);
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Не удалось ввести текст: " + e);
        }
    }
}
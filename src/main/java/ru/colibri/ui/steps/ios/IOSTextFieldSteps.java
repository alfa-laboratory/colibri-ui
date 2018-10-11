package ru.colibri.ui.steps.ios;

import io.appium.java_client.MobileBy;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.colibri.ui.steps.general.TextFieldSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class IOSTextFieldSteps extends TextFieldSteps {

    private static final By editTextLocator = MobileBy.iOSNsPredicateString("type like 'XCUIElementTypeTextField'");

    @Autowired
    private PropertyUtils propertyUtils;

    @Step
    @When("ввод текста с клавиатуры \"$textOrKeyword\"")
    public void sendKeyboardText(@Named("$textOrKeyword") String textOrKeyword) {
        sendText(textOrKeyword, editTextLocator);
    }
}
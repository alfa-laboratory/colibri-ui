package ru.colibri.ui.steps.general;

import lombok.extern.java.Log;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.logging.Level;

@Log
@Component
public class TextFieldSteps extends AbsSteps {

    @Autowired
    private PropertyUtils propertyUtils;

    @When("очистить \"$fieldName\"")
    public void clearField(@Named("$fieldName") String fieldName) {
        WebElement webElement = getWebElementByName(fieldName);
        try {
            webElement.clear();
        } catch (WebDriverException ignored) {
        }
        for (int i = webElement.getText().length(); i > 0; i--) {
            webElement.sendKeys(Keys.BACK_SPACE);
        }
    }

    @Step
    @When("поле \"$field\" заполняется значением \"$valueOrKeyword\"")
    public void sendKeys(@Named("$field") String field, @Named("$valueOrKeyword") String valueOrKeyword) {
        WebElement webElement = getWebElementByName(field);
        String value = propertyUtils.injectProperties(valueOrKeyword);
        webElement.sendKeys(value);
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

    protected void sendText(String textOrKeyword, By editTextLocator) {
        String text = propertyUtils.injectProperties(textOrKeyword);
        List elements = driver.findElements(editTextLocator);
        WebElement activeElement = null;
        boolean isFocused = false;
        for (int i = 0; !isFocused && i < elements.size(); i++) {
            activeElement = (WebElement) elements.get(i);
            isFocused = Boolean.valueOf(activeElement.getAttribute("focused"));
        }
        if (isFocused) {
            activeElement.sendKeys(text);
        } else {
            log.log(Level.SEVERE, "Нет активных полей ввода!");
        }
    }
}
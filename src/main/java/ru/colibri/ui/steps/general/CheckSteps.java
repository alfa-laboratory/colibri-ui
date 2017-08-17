package ru.colibri.ui.steps.general;

import org.jbehave.core.annotations.Then;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Component
public class CheckSteps extends AbsSteps {
    @Autowired
    private PropertyUtils propertyUtils;

    @Step
    @Then("значение элемента \"$fieldName\" равно \"$expectedValueOrKeyword\"")
    public void stepCheckValue(String fieldName, String expectedValueOrKeyword) {
        WebElement webElement = getWebElementByName(fieldName);
        String actualElement = webElement.getText();
        String expectedValue = propertyUtils.injectProperties(expectedValueOrKeyword);
        assertEquals(format("Значение поля [%s] не соответствует ожидаемому [%s]", actualElement, expectedValue), expectedValue, actualElement);
    }

    @Step
    @Then("значение элемента \"$fieldName\" содержит \"$expectedValueOrKeyword\"")
    public void stepCheckContainsValue(String fieldName, String expectedValueOrKeyword) {
        WebElement webElement = getWebElementByName(fieldName);
        String actualElement = webElement.getText();
        String expectedValue = propertyUtils.injectProperties(expectedValueOrKeyword);
        assertTrue(format("Элемент [%s] не сожержит внутри себя такой элемент [%s]", actualElement, expectedValue), actualElement.contains(expectedValue));
    }

    @Step
    @Then("элемент \"$fieldName\" не найден на странице")
    public void stepCheckNotExistField(String fieldName) {
        IElement element = getCurrentPage().getElementByName(fieldName);
        decreaseImplicitlyWait();
        int sizeOfList = finder.findWebElements(element).size();
        increaseImplicitlyWait();
        assertEquals("Найден элемент на странице", 0, sizeOfList);
    }
}
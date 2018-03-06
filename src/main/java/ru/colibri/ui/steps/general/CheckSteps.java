package ru.colibri.ui.steps.general;

import org.hamcrest.CoreMatchers;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static java.lang.String.format;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

@Component
public class CheckSteps extends AbsSteps {
    @Autowired
    private PropertyUtils propertyUtils;

    @Step
    @Then("каждый элемент \"$elementName\" содержит значение \"$template\" без учета регистра")
    public void eachElementContainsValue(@Named("$elementName") String elementName, @Named("$template") String template) {
        IElement element = getCurrentPage().getElementByName(elementName);
        List<WebElement> elementsFound = finder.findWebElements(element);
        String expectedValue = propertyUtils.injectProperties(template);
        elementsFound.forEach(elem -> {
            assertThat("Элемент не содержит заданное значение", elem.getText().toLowerCase(), containsString(expectedValue.toLowerCase()));
        });
    }

    @Step
    @Then("каждый элемент \"$elementName\" содержит любое из значений \"$template1\" или \"$template2\" без учета регистра")
    public void eachElementContainsOneOfTwoValues(@Named("$elementName") String elementName, @Named("$template1") String templateOne, @Named("$template2") String templateTwo) {
        IElement element = getCurrentPage().getElementByName(elementName);
        List<WebElement> elementsFound = finder.findWebElements(element);
        String expectedValueOne = propertyUtils.injectProperties(templateOne);
        String expectedValueTwo = propertyUtils.injectProperties(templateTwo);
        elementsFound.forEach(elem -> {
            assertThat("Элемент не содержит ни одно из заданных значений", elem.getText().toLowerCase(), CoreMatchers.anyOf(
                    containsString(expectedValueOne.toLowerCase()),
                    containsString(expectedValueTwo.toLowerCase())
            ));

        });
    }

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
    @Then("элемент \"$fieldName\" присутствует на странице")
    public void stepCheckExistField(String fieldName) {
        assertTrue("Элемент не найден на странице", checkElementExist(fieldName));
    }

    @Step
    @Then("элемент \"$fieldName\" не найден на странице")
    public void stepCheckNotExistField(String fieldName) {
        assertFalse("Найден элемент на странице", checkElementExist(fieldName));
    }

    public boolean checkElementExist(String fieldName) {
        IElement element = getCurrentPage().getElementByName(fieldName);
        decreaseImplicitlyWait();
        int sizeOfList = finder.findWebElements(element).size();
        increaseImplicitlyWait();
        return sizeOfList > 0;
    }
}
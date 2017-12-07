package ru.colibri.ui.steps.general;

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
import static org.junit.Assert.assertTrue;

@Component
public class ListItemCheck extends AbsSteps {

    @Autowired
    private PropertyUtils propertyUtils;

    @Step
    @Then("количество найденных элементов \"$fieldName\" равно \"$quantityExpectedValue\"")
    public void listItemCheck(@Named("$fieldName") String fieldName, @Named("$quantityExpectedValue") String quantityExpectedValue) {
        IElement element = getCurrentPage().getElementByName(fieldName);
        List<WebElement> elementsFound = finder.findWebElements(element);
        int quantityActualElements = elementsFound.size();
        quantityExpectedValue = propertyUtils.injectProperties(quantityExpectedValue);
        assertTrue(format("количество найденных элементов [%s] не равно [%s]", quantityActualElements, quantityExpectedValue), quantityActualElements == Integer.parseInt(quantityExpectedValue));
    }
}

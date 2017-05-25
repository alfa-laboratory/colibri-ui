package ru.colibri.ui.steps.general;

import org.eclipse.jetty.util.annotation.Name;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.assertTrue;

@Component
public class ListItemCheck extends AbsSteps {

    @Step
    @Then("количество найденных элементов \"$fieldName\" равно \"$quantityExpectedValue\"")
    public void listItemCheck(@Name("$fieldName") String fieldName, @Name("$quantityExpectedValue") int quantityExpectedValue) {
        IElement element = getCurrentPage().getElementByName(fieldName);
        List<WebElement> elementsFound = finder.findWebElements(element);
        int quantityActualElements = elementsFound.size();
        assertTrue(format("количество найденных элементов [%s] не равно [%s]", quantityActualElements, quantityExpectedValue), quantityActualElements == quantityExpectedValue);
    }
}

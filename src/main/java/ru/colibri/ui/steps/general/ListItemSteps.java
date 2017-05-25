package ru.colibri.ui.steps.general;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

@Component
public class ListItemSteps extends AbsSteps {

    @Step
    @When("выполнено нажатие на элемент \"$fieldName\" с индексом \"$index\"")
    public void listItemClick(String fieldName, int index) {
        IElement element = getCurrentPage().getElementByName(fieldName);
        List<WebElement> elementsFound = finder.findWebElements(element);
        WebElement firstElement = elementsFound.get(index);
        firstElement.click();
    }

}

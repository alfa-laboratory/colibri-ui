package ru.colibri.ui.steps.general;


import io.appium.java_client.TouchAction;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

@Component
public class ButtonsSteps extends AbsSteps {

    @Step
    @When("выполнено нажатие на \"$button\"")
    public void buttonClick(@Named("$button") String button) {
        getWebElementByName(button).click();
    }

    @Step
    @When("(Optional) выполнено нажатие на \"$button\"")
    public void optionalButtonClick(@Named("$button") String button) {
        try {
            buttonClick(button);
        } catch (Exception ignored) {
            System.out.println("Не нажато");
        }

    }

    @Step
    @When("выполнен лонгтап на \"$button\"")
    public void buttonLongtap(@Named("$button") String button) {
        new TouchAction(driver).longPress(getWebElementByName(button)).release().perform();
    }

    @Step
    @When("выполнен лонгтап на элемент \"$fieldName\" с индексом \"$index\"")
    public void listItemLongtap(String fieldName, int index) {
        IElement element = getCurrentPage().getElementByName(fieldName);
        List<WebElement> elementsFound = finder.findWebElements(element);
        WebElement firstElement = elementsFound.get(index);
        new TouchAction(driver).longPress(firstElement).release().perform();
    }
}

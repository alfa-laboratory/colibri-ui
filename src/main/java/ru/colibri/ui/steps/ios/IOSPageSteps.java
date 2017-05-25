package ru.colibri.ui.steps.ios;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.exception.ElementFoundOnPageException;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class IOSPageSteps extends AbsSteps {


    @Step
    @Then("на экране нет \"$elementName\"")
    public void checkNoElement(@Named("$elementName") String elementName) {
        IElement element = getCurrentPage().getElementByName(elementName);
        try {
            finder.findWebElement(element);
            throw new ElementFoundOnPageException(elementName, getCurrentPage().getName());
        } catch (Exception ignored) {
        }
    }
}

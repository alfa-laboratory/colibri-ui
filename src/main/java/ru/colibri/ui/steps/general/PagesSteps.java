package ru.colibri.ui.steps.general;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.exception.ElementNotFoundException;
import ru.colibri.ui.core.exception.PageNoLoadException;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

@Component
public class PagesSteps extends AbsSteps {
    @Autowired
    private PropertyUtils propertyUtils;

    @Step
    @Then("загружена страница \"$screenName\"")
    public void pageLoaded(@Named("$screenName") String screenName) {
        List<IElement> elements = pageProvider.getPageByName(screenName).getSpecificElements();
        for (IElement element : elements) {
            WebElement webElement = finder.findWebElement(element);
            if (webElement == null)
                throw new PageNoLoadException(screenName);
        }
        testContext.setCurrentPageName(screenName);
    }

    @Step
    @Then("(Optional) загружена страница \"$screenName\"")
    public void optionalPageLoaded(@Named("$screenName") String screenName) {
        try {
            pageLoaded(screenName);
        } catch (Exception ignored) {

        }
    }

    @Step
    @Then("на экране есть \"$elementName\"")
    public void checkElement(@Named("$elementName") String elementName) {
        WebElement webElement = getWebElementByName(elementName);
        if (webElement == null) {
            throw new ElementNotFoundException(elementName, getCurrentPage().getName());
        }
    }
}
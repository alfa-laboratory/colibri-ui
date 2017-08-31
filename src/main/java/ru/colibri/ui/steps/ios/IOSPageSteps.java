package ru.colibri.ui.steps.ios;

import io.appium.java_client.MobileBy;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.exception.ElementFoundOnPageException;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;

@Component
public class IOSPageSteps extends AbsSteps {
    @Autowired
    private PropertyUtils propertyUtils;

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

    @Step
    @Then("на экране есть надпись \"$textOrKeyword\"")
    public void checkText(@Named("$textOrKeyword") String textOrKeyword) {
        String text = propertyUtils.injectProperties(textOrKeyword);
        driver.findElement(MobileBy.iOSNsPredicateString(format("name contains '%1$s' or value contains '%1$s' or label contains '%1$s'", text)));
    }
}

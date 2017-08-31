package ru.colibri.ui.steps.android;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.colibri.ui.settings.general.PropertyUtils;
import ru.yandex.qatools.allure.annotations.Step;

import static java.lang.String.format;

@Component
public class AndroidPageSteps extends AbsSteps {
    @Autowired
    private PropertyUtils propertyUtils;


    @Step
    @Then("на экране есть надпись \"$textOrKeyword\"")
    public void checkText(@Named("$textOrKeyword") String textOrKeyword) {
        String text = propertyUtils.injectProperties(textOrKeyword);
        driver.findElement(By.xpath(format("//*[contains(@name, '%1$s') or contains(@text,'%1$s') or contains(@content-desc,'%1$s')]", text)));
    }
}
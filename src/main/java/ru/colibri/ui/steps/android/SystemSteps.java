package ru.colibri.ui.steps.android;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class SystemSteps extends AbsSteps {

    @Step
    @When("вывести на консоль все элементы экрана")
    public void showElementsSteps() {
        System.out.printf("%-90s %-35s %-35s %-70s %-10s%n",
                "webElement",
                "text",
                "class",
                "resourceId",
                "contentDescription");
        for (Object we : driver.findElements(By.xpath(".//*"))) {
            WebElement webElement = (WebElement) we;
            System.out.printf("%-90s %-35s %-35s %-70s %-10s%n",
                    webElement,
                    webElement.getText(),
                    webElement.getTagName(),
                    webElement.getAttribute("resourceId"),
                    webElement.getAttribute("contentDescription"));
        }
    }
}

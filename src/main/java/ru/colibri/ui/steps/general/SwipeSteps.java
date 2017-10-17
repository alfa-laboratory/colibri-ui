package ru.colibri.ui.steps.general;

import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;

import java.time.Duration;

@Component
public class SwipeSteps extends AbsSteps {


    protected void verticalSwipe(String elementName, int swipeLength, int duration) {
        WebElement webElement = getWebElementByName(elementName);
        new TouchAction(driver)
                .press(webElement)
                .waitAction(Duration.ofSeconds(duration))
                .moveTo(0, swipeLength)
                .release().perform();

    }

    protected void horizontalSwipe(String elementName, int swipeLength, int duration) {
        WebElement webElement = getWebElementByName(elementName);
        new TouchAction(driver)
                .press(webElement)
                .waitAction(Duration.ofSeconds(duration))
                .moveTo(swipeLength, 0)
                .release().perform();
    }

}
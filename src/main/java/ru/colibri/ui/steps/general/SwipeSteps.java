package ru.colibri.ui.steps.general;

import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;

import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.ElementOption.point;

@Component
public class SwipeSteps extends AbsSteps {


    protected void verticalSwipe(String elementName, int swipeLength, int duration) {
        WebElement webElement = getWebElementByName(elementName);
        new TouchAction(driver)
                .press(element(webElement))
                .waitAction(waitOptions(Duration.ofSeconds(duration)))
                .moveTo(point(0, swipeLength))
                .release().perform();

    }

    protected void horizontalSwipe(String elementName, int swipeLength, int duration) {
        WebElement webElement = getWebElementByName(elementName);
        new TouchAction(driver)
                .press(element(webElement))
                .waitAction(waitOptions(Duration.ofSeconds(duration)))
                .moveTo(point(swipeLength, 0))
                .release().perform();
    }

}
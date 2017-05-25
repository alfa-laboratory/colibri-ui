package ru.colibri.ui.steps.general;

import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class SwipeSteps extends AbsSteps {
    private static final int SWIPE_DURATION = 2000;

    @Step
    @When("вертикальный свайп \"$elementName\" вниз")
    public void swipingDown(@Named("$elementName") String elementName) {
        WebElement webElement = getWebElementByName(elementName);
        ((MobileElement) webElement).swipe(SwipeElementDirection.DOWN, 1, 1, SWIPE_DURATION);
    }

    @Step
    @When("вертикальный свайп \"$elementName\" вверх")
    public void swipingUp(@Named("$elementName") String elementName) {
        WebElement webElement = getWebElementByName(elementName);
        ((MobileElement) webElement).swipe(SwipeElementDirection.UP, 1, 1, SWIPE_DURATION);
    }

    @Step
    @When("горизонтальный свайп \"$elementName\" вправо")
    public void swipingRight(@Named("$elementName") String elementName) {
        WebElement webElement = getWebElementByName(elementName);
        ((MobileElement) webElement).swipe(SwipeElementDirection.RIGHT, 1, 1, SWIPE_DURATION);
    }

    @Step
    @When("горизонтальный свайп \"$elementName\" влево")
    public void swipingLeft(@Named("$elementName") String elementName) {
        WebElement webElement = getWebElementByName(elementName);
        ((MobileElement) webElement).swipe(SwipeElementDirection.LEFT, 1, 1, SWIPE_DURATION);
    }
}
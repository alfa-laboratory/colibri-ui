package ru.colibri.ui.steps.ios;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.yandex.qatools.allure.annotations.Step;

import static io.appium.java_client.touch.offset.PointOption.point;

@Component
public class BaseIOSPickerWheelSteps extends AbsSteps {


    protected String pickerWheelXPath = "//XCUIElementTypePickerWheel";
    protected double stepToNext = 0.2;
    protected double stepToLast = 0.35;

    @Step
    @When("установить пикер в следующее значение")
    public void setNextPickerWheelValue() {
        WebElement webElement = driver.findElement(By.xpath(pickerWheelXPath));
        setNextPickerWheelValue(webElement);
    }

    @Step
    @When("установить пикер в предыдущее значение")
    public void setPrevPickerWheelValue() {
        WebElement webElement = driver.findElement(By.xpath(pickerWheelXPath));
        setPrevPickerWheelValue(webElement);
    }

    @Step
    @When("установить пикер в первое значение")
    public void setFirstPickerWheelValue() {
        WebElement webElement = driver.findElement(By.xpath(pickerWheelXPath));
        Point center = ((IOSElement) webElement).getCenter();
        Dimension size = webElement.getSize();
        int height = size.getHeight();
        Point target = new Point(center.getX(), center.getY() - (int) (height * stepToLast));
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(point(target.getX(), target.getY())).release();
        touchAction.perform();
    }

    @Step
    @When("установить пикер в последнее значение")
    public void setLastPickerWheelValue() {
        WebElement webElement = driver.findElement(By.xpath(pickerWheelXPath));
        Point center = ((IOSElement) webElement).getCenter();
        Dimension size = webElement.getSize();
        int height = size.getHeight();
        Point target = new Point(center.getX(), center.getY() + (int) (height * stepToLast));
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(point(target.getX(), target.getY())).release();
        touchAction.perform();
    }

    public void setNextPickerWheelValue(WebElement pickerWheelElement) {
        setNextPickerWheelValue(pickerWheelElement, stepToNext);
    }


    public void setPrevPickerWheelValue(WebElement pickerWheelElement) {
        setPrevPickerWheelValue(pickerWheelElement, stepToNext);
    }


    public void setNextPickerWheelValue(WebElement pickerWheelElement, double step) {
        Point center = ((IOSElement) pickerWheelElement).getCenter();
        Dimension size = pickerWheelElement.getSize();
        int height = size.getHeight();
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(point(center.getX(), center.getY() + (int) (height * step))).release();
        touchAction.perform();
    }


    public void setPrevPickerWheelValue(WebElement pickerWheelElement, double step) {
        Point center = ((IOSElement) pickerWheelElement).getCenter();
        Dimension size = pickerWheelElement.getSize();
        int height = size.getHeight();
        TouchAction touchAction = new TouchAction(driver);
        touchAction.press(point(center.getX(), center.getY() - (int) (height * step))).release();
        touchAction.perform();
    }
}

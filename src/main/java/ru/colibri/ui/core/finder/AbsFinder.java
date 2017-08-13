package ru.colibri.ui.core.finder;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.colibri.ui.core.settings.DriversSettings;

public abstract class AbsFinder<T extends AppiumDriver> implements IFinder {
    protected abstract T getDriver();

    protected abstract DriversSettings getDriversSettings();

    @Override
    public WebElement waitClickable(By by) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), getDriversSettings().getFindingTimeOutInSeconds());
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Override
    public WebElement waitVisible(By by) {
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), getDriversSettings().getFindingTimeOutInSeconds());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Override
    public WebElement findWebElement(By by) {
        return getDriver().findElement(by);
    }
}

package ru.colibri.ui.settings.ios;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.finder.AbsFinder;
import ru.colibri.ui.core.settings.DriversSettings;

import java.util.List;

@Component
@Qualifier("ios")
public class IOSFinder extends AbsFinder<IOSDriver> {
    @Autowired
    protected IOSByFactory byFactory;

    @Autowired(required = false)
    @Qualifier("ios")
    private DriversSettings driversSettings;

    @Autowired(required = false)
    @Qualifier("ios")
    private IOSDriver driver;

    @Override
    public WebElement findWebElement(IElement element) {
        return getDriver().findElement(byFactory.byElement(element));
    }

    @Override
    public void scrollTo(IElement targetListName, IElement toElementName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<WebElement> findNestedWebElements(IElement parent, IElement nested) {
        return getDriver().findElements(byFactory.getNestedElements(parent, nested));
    }

    @Override
    public List<WebElement> findWebElements(IElement element) {
        return getDriver().findElements(byFactory.byElement(element));
    }

    @Override
    public WebElement findNestedWebElement(IElement parent, IElement nested) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected IOSDriver getDriver() {
        return driver;
    }

    @Override
    protected DriversSettings getDriversSettings() {
        return driversSettings;
    }
}

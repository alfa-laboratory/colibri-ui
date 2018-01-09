package ru.colibri.ui.settings.android;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.finder.AbsFinder;
import ru.colibri.ui.core.settings.DriversSettings;

import java.util.List;

@Component
@Qualifier("android")
public class AndroidFinder extends AbsFinder<AndroidDriver> {

    @Autowired
    protected AndroidByFactory byFactory;

    @Autowired(required = false)
    @Qualifier("android")
    private AndroidDriver driver;

    @Autowired(required = false)
    @Qualifier("android")
    private DriversSettings driversSettings;


    @Override
    public WebElement findWebElement(IElement element) {
        return getDriver().findElement(byFactory.byElement(element));
    }

    @Override
    public List<WebElement> findWebElements(IElement element) {
        return getDriver().findElements(byFactory.byElement(element));
    }

    @Override
    public WebElement findNestedWebElement(IElement parent, IElement nested) {
        //driver.findElement(By)
        return getDriver().findElement(byFactory.getNestedElement(parent, nested));
    }

    @Override
    public void scrollTo(IElement targetListName, IElement toElementName) {
        //TODO add support content-desc
        String uiAutomatorString;
        if (StringUtils.isEmpty(toElementName.getId())) {
            uiAutomatorString = byFactory.scrollToUiAutomatorString(targetListName.getId(), toElementName.getText());
        } else {
            uiAutomatorString = byFactory.scrollToUiAutomatorStringId(targetListName.getId(), toElementName.getId());
        }
        getDriver().findElementByAndroidUIAutomator(uiAutomatorString);
    }

    @Override
    public List<WebElement> findNestedWebElements(IElement parent, IElement nested) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected AndroidDriver getDriver() {
        return driver;
    }

    @Override
    protected DriversSettings getDriversSettings() {
        return driversSettings;
    }
}

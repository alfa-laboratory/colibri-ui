package ru.colibri.ui.core.steps;

import io.appium.java_client.AppiumDriver;
import lombok.extern.java.Log;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import ru.colibri.ui.core.contexts.TestContext;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.finder.IFinder;
import ru.colibri.ui.core.pages.IPage;
import ru.colibri.ui.core.pages.IPageProvider;

@Log
public abstract class AbsSteps extends Steps implements InitializingBean {
    @Autowired
    protected TestContext testContext;

    @Autowired
    protected AppiumDriver driver;

    @Autowired(required = false)
    protected IFinder finder;

    @Autowired
    protected IPageProvider pageProvider;

    protected IPage getCurrentPage() {
        return pageProvider.getCurrentPage();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(String.format("Bean %s loaded", this.getClass().getSimpleName()));
    }

    protected WebElement getWebElementByName(String elementName) {
        IElement element = getCurrentPage().getElementByName(elementName);
        return finder.findWebElement(element);
    }
}

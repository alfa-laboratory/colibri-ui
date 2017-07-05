package ru.colibri.ui.settings.android;

import io.appium.java_client.android.AndroidDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.contexts.TestContext;
import ru.colibri.ui.core.pages.AbsPageProvider;
import ru.colibri.ui.core.pages.IPage;
import ru.colibri.ui.core.settings.AppSettings;

@Component
@Qualifier("android")
public class AndroidPageProvider extends AbsPageProvider {

    @Autowired
    private AppSettings appSettings;

    @Autowired
    private TestContext testContext;

    @Override
    public IPage getCurrentPage() {
        if (appSettings.isActivityUse()) {
            return getPageById(((AndroidDriver) driver).currentActivity());
        } else {
            return getPageByName(testContext.getCurrentPageName());
        }
    }
}

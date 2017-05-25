package ru.colibri.ui.settings.android;

import io.appium.java_client.android.AndroidDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.pages.AbsPageProvider;
import ru.colibri.ui.core.pages.IPage;

@Component
@Qualifier("android")
public class AndroidPageProvider extends AbsPageProvider {

    @Override
    public IPage getCurrentPage() {
        return getPageById(((AndroidDriver) driver).currentActivity());
    }
}

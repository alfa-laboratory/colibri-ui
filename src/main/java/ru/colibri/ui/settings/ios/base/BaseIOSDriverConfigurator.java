package ru.colibri.ui.settings.ios.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.colibri.ui.core.settings.AppSettings;
import ru.colibri.ui.core.settings.DriversSettings;
import ru.colibri.ui.settings.configurator.AbsDriverConfigurator;

import java.util.concurrent.TimeUnit;

public abstract class BaseIOSDriverConfigurator extends AbsDriverConfigurator {
    @Override
    public AppiumDriver createDriver(DriversSettings driversSettings, AppSettings appSettings) {
        DesiredCapabilities capabilities = createCapabilities(driversSettings);
        additionalCapabilities(driversSettings, capabilities);
        IOSDriver iosDriver = new IOSDriver(getRemoteAddress(driversSettings.getAppiumRemoteUrl()), capabilities);
        iosDriver.manage().timeouts().implicitlyWait(driversSettings.getImplicitlyWaitInSeconds(), TimeUnit.SECONDS);
        return iosDriver;
    }

    protected abstract void additionalCapabilities(DriversSettings driversSettings, DesiredCapabilities capabilities);

}

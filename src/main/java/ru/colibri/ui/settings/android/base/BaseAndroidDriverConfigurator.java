package ru.colibri.ui.settings.android.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.colibri.ui.core.settings.AppSettings;
import ru.colibri.ui.core.settings.DriversSettings;
import ru.colibri.ui.settings.configurator.AbsDriverConfigurator;

import java.util.concurrent.TimeUnit;

public abstract class BaseAndroidDriverConfigurator extends AbsDriverConfigurator {

    @Override
    public AppiumDriver createDriver(DriversSettings driversSettings, AppSettings appSettings) {
        DesiredCapabilities capabilities = createCapabilities(driversSettings);
        additionalAndroidCapabilities(appSettings, capabilities, driversSettings);
        AndroidDriver androidDriver = new AndroidDriver(getRemoteAddress(driversSettings.getAppiumRemoteUrl()), capabilities);
        androidDriver.manage().timeouts().implicitlyWait(driversSettings.getImplicitlyWaitInSeconds(), TimeUnit.SECONDS);
        return androidDriver;
    }

    protected abstract void additionalAndroidCapabilities(AppSettings appSettings, DesiredCapabilities capabilities, DriversSettings driversSettings);
}

package ru.colibri.ui.core.settings;

import io.appium.java_client.AppiumDriver;

public interface IDriverConfigurator {
    AppiumDriver createDriver(DriversSettings driversSettings, AppSettings appSettings);
}

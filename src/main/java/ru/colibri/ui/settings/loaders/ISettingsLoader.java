package ru.colibri.ui.settings.loaders;

import ru.colibri.ui.core.settings.AppSettings;
import ru.colibri.ui.core.settings.DriversSettings;
import ru.colibri.ui.core.settings.TestSettings;

public interface ISettingsLoader {

    AppSettings loadAppSettings(String userName);

    DriversSettings loadDriverSettings(String platformName);

    TestSettings loadTestSettings(String testType);
}

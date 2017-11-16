package ru.colibri.ui.settings.loaders;

import ru.colibri.ui.core.settings.AppSettings;
import ru.colibri.ui.core.settings.DriversSettings;

public class AbsSettingsLoaderFake extends AbsSettingsLoader {
    @Override
    public AppSettings loadAppSettings(String userName) {
        return null;
    }

    @Override
    public DriversSettings loadDriverSettings(String platformName) {
        return null;
    }
}

package ru.colibri.ui.settings.configurator;

import io.appium.java_client.remote.MobileCapabilityType;
import lombok.extern.java.Log;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import ru.colibri.ui.core.settings.DriversSettings;
import ru.colibri.ui.core.settings.IDriverConfigurator;
import ru.colibri.ui.core.utils.FileUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

@Log
public abstract class AbsDriverConfigurator implements IDriverConfigurator {
    @Autowired
    private FileUtils fileUtils;

    protected DesiredCapabilities createCapabilities(DriversSettings driversSettings) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, driversSettings.getDeviceName());
        capabilities.setCapability(MobileCapabilityType.UDID, driversSettings.getUDID());
        String absolutePath = fileUtils.relativeToAbsolutePath(driversSettings.getFilePath());
        capabilities.setCapability(MobileCapabilityType.APP, absolutePath);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, driversSettings.getNewCommandTimeoutInSeconds());
        return capabilities;
    }

    protected URL getRemoteAddress(String remoteUrl) {
        try {
            return new URL(remoteUrl);
        } catch (MalformedURLException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}

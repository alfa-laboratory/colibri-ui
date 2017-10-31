package ru.colibri.ui.settings.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppiumServer {

    @Autowired
    private IServerConfig config;


    private AppiumDriverLocalService service;


    public void startServer() {

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress(config.getHost());
        builder.usingPort(Integer.parseInt(config.getPort()));
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public void stopServer() {
        service.stop();
    }


}

package ru.colibri.ui.steps.general;

import ru.colibri.ui.core.settings.DriversSettings;
import ru.colibri.ui.core.steps.AbsSteps;

import java.util.concurrent.TimeUnit;

public abstract class AbsServiceSteps extends AbsSteps {

    private static final int TIMEOUT = 7;
    private int currentTimeout;

    public void goToMain(String screenName) {
        decreaseImplicitlyWait();
        returnCycle(screenName);
        increaseImplicitlyWait();

    }

    protected void decreaseImplicitlyWait() {
        currentTimeout = getDriversSettings().getImplicitlyWaitInSeconds();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    protected void increaseImplicitlyWait() {
        driver.manage().timeouts().implicitlyWait(currentTimeout, TimeUnit.SECONDS);
    }


    protected abstract void returnCycle(String screenName);

    protected abstract ISystemButtonsClick getISystemButtonsClickBean();

    protected abstract DriversSettings getDriversSettings();

    protected abstract String getAlertLocator();
}

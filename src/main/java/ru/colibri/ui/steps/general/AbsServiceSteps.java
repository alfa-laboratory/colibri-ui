package ru.colibri.ui.steps.general;

import ru.colibri.ui.core.settings.DriversSettings;
import ru.colibri.ui.core.steps.AbsSteps;

import java.util.concurrent.TimeUnit;

public abstract class AbsServiceSteps extends AbsSteps {

    public void goToMain(String screenName) {
        int implicitlyWaitInSecondsOld = getDriversSettings().getImplicitlyWaitInSeconds();
        int implicitlyWaitInSecondsNew = 10;
//        уменьшаем ожидалку
        driver.manage().timeouts().implicitlyWait(implicitlyWaitInSecondsNew, TimeUnit.SECONDS);
        returnCycle(screenName);
//        возвращаем ожидалку на место
        driver.manage().timeouts().implicitlyWait(implicitlyWaitInSecondsOld, TimeUnit.SECONDS);
    }


    protected abstract void returnCycle(String screenName);

    protected abstract ISystemButtonsClick getISystemButtonsClickBean();

    protected abstract DriversSettings getDriversSettings();

    protected abstract String getAlertLocator();
}

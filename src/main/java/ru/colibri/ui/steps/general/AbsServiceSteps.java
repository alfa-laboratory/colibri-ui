package ru.colibri.ui.steps.general;

import ru.colibri.ui.core.steps.AbsSteps;

public abstract class AbsServiceSteps extends AbsSteps {

    public void goToMain(String screenName) {
        decreaseImplicitlyWait();
        returnCycle(screenName);
        increaseImplicitlyWait();

    }

    protected abstract void returnCycle(String screenName);

    protected abstract ISystemButtonsClick getISystemButtonsClickBean();

    protected abstract String getAlertLocator();
}

package ru.colibri.ui.steps.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.steps.AbsSteps;
import ru.colibri.ui.steps.general.ISystemButtonsClick;
import ru.yandex.qatools.allure.annotations.Step;

@Component
@Qualifier("android")
public class BaseAndroidSystemButtonsSteps extends AbsSteps implements ISystemButtonsClick {

    @Step
    @When("выполнено нажатие на Меню")
    public void systemMenuClick() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.MENU));
    }

    @Step
    @When("выполнено нажатие на Хоум")
    public void systemHomeClick() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
    }

    @Override
    @Step
    @When("выполнено нажатие на Назад")
    public void systemBackClick() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
    }

    @Step
    @When("выполнено нажатие на Enter")
    public void systemEnterClick() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }
}

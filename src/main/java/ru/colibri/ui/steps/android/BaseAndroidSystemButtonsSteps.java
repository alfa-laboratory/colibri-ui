package ru.colibri.ui.steps.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
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
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.MENU);
    }

    @Step
    @When("выполнено нажатие на Хоум")
    public void systemHomeClick() {
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.HOME);
    }

    @Override
    @Step
    @When("выполнено нажатие на Назад")
    public void systemBackClick() {
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.BACK);
    }

    @Step
    @When("выполнено нажатие на Enter")
    public void systemEnterClick() {
        ((AndroidDriver) driver).pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
    }
}

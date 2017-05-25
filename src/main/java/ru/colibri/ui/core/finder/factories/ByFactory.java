package ru.colibri.ui.core.finder.factories;

import org.openqa.selenium.By;
import ru.colibri.ui.core.fields.IElement;

public abstract class ByFactory {

    public By byXpath(String xpath) {
        return By.xpath(xpath);
    }

    public By byId(String id) {
        return By.id(id);
    }

    public abstract By byElement(IElement element);
}




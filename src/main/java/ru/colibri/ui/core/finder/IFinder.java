package ru.colibri.ui.core.finder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.colibri.ui.core.fields.IElement;

import java.util.List;

public interface IFinder {
    WebElement findWebElement(IElement element);

    List<WebElement> findWebElements(IElement element);

    WebElement findNestedWebElement(IElement parent, IElement nested);

    WebElement waitClickable(By by);

    WebElement waitVisible(By by);

    WebElement findWebElement(By by);

    void scrollTo(IElement targetListName, IElement toElementName);

    List<WebElement> findNestedWebElements(IElement parent, IElement nested);
}

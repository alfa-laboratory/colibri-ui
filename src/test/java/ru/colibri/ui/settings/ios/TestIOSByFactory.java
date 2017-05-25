package ru.colibri.ui.settings.ios;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.colibri.ui.core.builders.ElementBuilders;
import ru.colibri.ui.core.fields.IElement;

import static java.lang.String.format;

public class TestIOSByFactory {

    private IOSByFactory byFactory = new IOSByFactory();
    private String XPATH_TEMPLATE = "//*[contains(@name,'%1$s') or contains(@value,'%1$s') or contains(@label,'%1$s')]";

    @Test
    public void testByID() {
        IElement element = ElementBuilders.element()
                .withName("elementByID")
                .withId("textFieldID")
                .please();
        By expected = By.id("textFieldID");

        By byElementFromFactory = byFactory.byElement(element);
        Assert.assertEquals("Результат работы IOSFactory по id некорректен", expected, byElementFromFactory);
    }

    @Test
    public void testByText() {
        IElement element = ElementBuilders.element()
                .withName("elementByText")
                .withText("visibleText")
                .please();
        String xpath = format(XPATH_TEMPLATE, element.getText());
        By expected = By.xpath(xpath);

        By byElementFromFactory = byFactory.byElement(element);
        Assert.assertEquals("Результат работы IOSFactory по text некорректен", expected, byElementFromFactory);
    }

    @Test
    public void testByXPath() {
        IElement element = ElementBuilders.element()
                .withName("elementByXPath")
                .withXPath("//*[@name = 'testXPath']")
                .please();
        By expected = By.xpath(element.getXpath());

        By byElementFromFactory = byFactory.byElement(element);
        Assert.assertEquals("Результат работы IOSFactory по xpath некорректен", expected, byElementFromFactory);
    }

    @Test
    public void testByNestedElement() {
        IElement parentXPath = ElementBuilders.element()
                .withName("elementParent")
                .withXPath("//*[@name = 'testXPath']")
                .please();
        IElement nestedXPath = ElementBuilders.element()
                .withName("elementNested")
                .withXPath("//*[@name = 'testXPath']")
                .please();
        By expected = By.xpath(parentXPath.getXpath() + nestedXPath.getXpath());

        By byElementFromFactory = byFactory.getNestedElements(parentXPath, nestedXPath);
        Assert.assertEquals("Результат работы IOSFactory по id некорректен", expected, byElementFromFactory);

        IElement parentID = ElementBuilders.element()
                .withName("elementByID")
                .withId("textFieldID")
                .please();
        IElement elementText = ElementBuilders.element()
                .withName("elementByText")
                .withText("visibleText")
                .please();
        String byID = format(XPATH_TEMPLATE, parentID.getId());
        String xpath = format(XPATH_TEMPLATE, elementText.getText());
        expected = By.xpath(byID + xpath);

        byElementFromFactory = byFactory.getNestedElements(parentID, elementText);
        Assert.assertEquals("Результат работы IOSFactory по nestedElement некорректен", expected, byElementFromFactory);
    }

}

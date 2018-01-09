package ru.colibri.ui.settings.android;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.colibri.ui.core.builders.ElementBuilders;
import ru.colibri.ui.core.fields.IElement;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AndroidTestConfig.class})
public class AndroidByFactoryTests {
    @Autowired
    private AndroidByFactory byFactory;

    @Test
    public void shouldFindElementById() {
        IElement element = ElementBuilders.element()
                .withId("textFieldID")
                .please();

        By foundElement = byFactory.byElement(element);

        assertEquals("Результат работы AndroidByFactory по id некорректен",
                By.id(formatResourceId(element)),
                foundElement);
    }

    @Test
    public void shouldFindElementByContentDescription() {
        IElement element = ElementBuilders.element()
                .withContentDesc("cont-desk")
                .please();

        By foundElement = byFactory.byElement(element);

        assertEquals("Результат работы AndroidByFactory по content-desc некорректен",
                By.xpath(format("//*[@content-desc='%s']", element.getContentDesc())),
                foundElement);
    }

    @Test
    public void shouldFindElementByText() {
        IElement element = ElementBuilders.element()
                .withText("textField")
                .please();

        By foundElement = byFactory.byElement(element);

        assertEquals("Результат работы AndroidByFactory по text некорректен",
                By.xpath(format("//*[@text='%s']", element.getText())),
                foundElement);
    }

    @Test
    public void shouldFindElementByXPath() {
        IElement element = ElementBuilders.element()
                .withXPath("//*[@customXPath='007']")
                .please();

        By foundElement = byFactory.byElement(element);

        assertEquals("Результат работы AndroidByFactory по xpath некорректен",
                By.xpath(element.getXpath()),
                foundElement);
    }

    @Test(expected = RuntimeException.class)
    public void throwExceptionIfEmptyElement() {
        IElement emptyElement = ElementBuilders.element().please();

        byFactory.byElement(emptyElement);
    }

    @Test
    public void shouldScrollToListItemElementWithText() {
        IElement listElement = ElementBuilders.element()
                .withId("list")
                .please();
        IElement itemElement = ElementBuilders.element()
                .withText("itemElement")
                .please();

        String actual = byFactory.scrollToUiAutomatorString(listElement.getId(), itemElement.getText());

        String expected = "new UiScrollable(new UiSelector()" +
                ".scrollable(true)" +
                ".resourceId(\"" + formatResourceId(listElement) + "\"))" +
                ".scrollIntoView(new UiSelector().text(\"" + itemElement.getText() + "\"))";
        assertEquals("Результат работы AndroidByFactory по скроллу до текста некорректен", expected, actual);
    }

    @Test
    public void shouldScrollToListItemElementWithId() {

        IElement listElement = ElementBuilders.element()
                .withId("list")
                .please();
        IElement itemElement = ElementBuilders.element()
                .withId("itemElement")
                .please();

        String actual = byFactory.scrollToUiAutomatorStringId(listElement.getId(), itemElement.getId());

        String expected = "new UiScrollable(new UiSelector()" +
                ".scrollable(true)" +
                ".resourceId(\"" + formatResourceId(listElement) + "\"))" +
                ".scrollIntoView(new UiSelector().resourceId(\"" + formatResourceId(itemElement) + "\"))";
        assertEquals("Результат работы AndroidByFactory по скроллу до элемента некорректен", expected, actual);
    }

    @Test
    public void testNested() {
        IElement parent = ElementBuilders.element()
                .withId("textFieldID")
                .please();
        IElement nested = ElementBuilders.element()
                .withXPath("//*[@someXPath='value']")
                .please();

        By byElementFromFactory = byFactory.getNestedElement(parent, nested);

        assertEquals("Результат работы AndroidByFactory по nestedElement некорректен",
                By.xpath(format("//*[@resource-id='%s']", formatResourceId(parent)) + nested.getXpath()),
                byElementFromFactory);
    }

    private String formatResourceId(IElement element) {
        return format("%s%s", byFactory.getAndroidIdPrefix(), element.getId());
    }
}

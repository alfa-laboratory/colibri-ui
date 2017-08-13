package ru.colibri.ui.settings.android;

import org.apache.http.util.TextUtils;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.finder.factories.ByFactory;
import ru.colibri.ui.core.settings.AppSettings;

import static java.lang.String.format;

@Component
public class AndroidByFactory extends ByFactory {

    @Autowired
    private AppSettings appSettings;
    private String scrollTemplate = "new UiScrollable(new UiSelector().scrollable(true).resourceId(\"%s\")).scrollIntoView(new UiSelector().%s)";

    protected String getAndroidIdPrefix() {
        return appSettings.getPackageName() + ":id/";
    }

    private String createSearchXpath(IElement element) {
        StringBuilder searchPattern = new StringBuilder()
                .append("//*[");
        if (!TextUtils.isEmpty(element.getId())) {
            searchPattern.append("@resource-id='").append(createFullElementId(element.getId())).append("']");
        } else if (!TextUtils.isEmpty(element.getContentDesc())) {
            searchPattern.append("@content-desc='").append(element.getContentDesc()).append("']");
        } else if (!TextUtils.isEmpty(element.getText())) {
            searchPattern.append("@text='").append(element.getText()).append("']");
        } else if (!TextUtils.isEmpty(element.getXpath())) {
            return element.getXpath();
        } else {
            throw new RuntimeException("No id for element: " + element);
        }
        return searchPattern.toString();
    }

    @Override
    public By byElement(IElement element) {
        return By.xpath(createSearchXpath(element));
    }

    public String scrollToUiAutomatorString(String listElementID, String toElementText) {
        return format(scrollTemplate, createFullElementId(listElementID), "text(\"" + toElementText + "\")");
    }

    public String scrollToUiAutomatorStringId(String listElementID, String toElementResourceId) {
        return format(scrollTemplate, createFullElementId(listElementID), "resourceId(\"" + createFullElementId(toElementResourceId) + "\")");
    }

    public By getNestedElement(IElement parent, IElement nested) {
        return By.xpath(createSearchXpath(parent) + createSearchXpath(nested));
    }

    private String createFullElementId(String elementId) {
        return getAndroidIdPrefix() + elementId;
    }
}

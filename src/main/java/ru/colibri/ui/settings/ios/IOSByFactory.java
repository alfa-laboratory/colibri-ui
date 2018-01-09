package ru.colibri.ui.settings.ios;

import io.appium.java_client.MobileBy;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.finder.factories.ByFactory;

import static java.lang.String.format;

@Component
public class IOSByFactory extends ByFactory {

    private static String IOSNSPREDICATE_TEMPLATE = "name contains '%1$s' or value contains '%1$s' or label contains '%1$s'";
    private String XPATH_TEMPLATE = "//*[contains(@name,'%1$s') or contains(@value,'%1$s') or contains(@label,'%1$s')]";

    public By byNameOrValueOrLabel(String label) {
        return byIOSNSPredicate(createIOSNSPredicateByNameOrValueOrLabel(label));
    }

    private String createXPathByNameOrValueOrLabel(String label) {
        return format(XPATH_TEMPLATE, label);
    }

    private String createIOSNSPredicateByNameOrValueOrLabel(String label) {
        return format(IOSNSPREDICATE_TEMPLATE, label);
    }

    @Override
    public By byElement(IElement element) {
        if (!StringUtils.isEmpty(element.getNSPredicate())) {
            return byIOSNSPredicate(element.getNSPredicate());
        }
        if (StringUtils.isEmpty(element.getXpath())) {
            if (StringUtils.isEmpty(element.getId())) {
                return byNameOrValueOrLabel(element.getText());
            } else {
                return byId(element.getId());
            }
        } else {
            return byXpath(element.getXpath());
        }
    }

    public By getNestedElements(IElement parent, IElement nested) {
        return By.xpath(createSearchXpath(parent) + createSearchXpath(nested));
    }

    private String createSearchXpath(IElement element) {
        if (!StringUtils.isEmpty(element.getXpath())) {
            return element.getXpath();
        } else {
            if (!StringUtils.isEmpty(element.getId())) {
                return createXPathByNameOrValueOrLabel(element.getId());
            } else {
                return createXPathByNameOrValueOrLabel(element.getText());
            }
        }
    }

    public MobileBy byIOSNSPredicate(String predicate) {
        return (MobileBy) MobileBy.iOSNsPredicateString(predicate);
    }
}

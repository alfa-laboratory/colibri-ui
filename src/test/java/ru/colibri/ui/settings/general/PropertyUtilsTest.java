package ru.colibri.ui.settings.general;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Properties;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UtilsTestConfig.class})
public class PropertyUtilsTest {

    @Autowired
    private PropertyUtils propertyUtils;

    private String ACTUAL_PROPERTY_PATH = "src/test/resources/test.properties";
    private String BAD_PROPERTY_PATH = "test.properties";
    private String TEMPLATE_TO_INJECT = "Здесь был #name#";
    private String BAD_TEMPLATE_TO_INJECT = "Здесь был #phone#";

    @Test
    public void readProperty() {
        Properties properties = propertyUtils.readProperty(ACTUAL_PROPERTY_PATH);

        Assert.assertEquals(properties.getProperty("name"), "Вася");
    }

    @Test
    public void badReadProperty() {
        Throwable throwable = null;

        try {
            propertyUtils.readProperty(BAD_PROPERTY_PATH);
        } catch (Exception e) {
            throwable = e;
        }

        assertNotNull(throwable);
        assertTrue(throwable.getMessage().contains("Ошибка загрузки настроек драйвера: "));
    }

    @Test
    public void injectProperties() throws Exception {
        String text = propertyUtils.injectProperties(TEMPLATE_TO_INJECT);
        assertFalse(text.contains("#name#"));
        assertTrue(text.contains("Иииигорь"));
        assertEquals(text, "Здесь был Иииигорь");
    }


    @Test
    public void notInjectProperties() throws Exception {
        String text = propertyUtils.injectProperties(BAD_TEMPLATE_TO_INJECT);

        assertTrue(text.contains("#phone#"));
        assertEquals(text, BAD_TEMPLATE_TO_INJECT);
    }


    @Test
    public void notFindPropertiesToInject() throws Exception {
        String text = propertyUtils.injectProperties(BAD_PROPERTY_PATH);

        assertEquals(text, BAD_PROPERTY_PATH);
    }

}
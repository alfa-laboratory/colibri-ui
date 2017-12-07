package ru.colibri.ui.settings.loaders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.colibri.ui.core.exception.PropertyNotFoundException;
import ru.colibri.ui.core.settings.TestSettings;

import static java.lang.String.format;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SettingsLoaderConfig.class})
public class AbsSettingsLoaderTest {

    private static final String TEST_TYPE_FILTER = "src/test/resources/planTestCycle/testCycle.properties";

    @Autowired
    private ISettingsLoader settingsLoader;


    @Test(expected = IllegalArgumentException.class)
    public void emptyTestType() {
        try {
            settingsLoader.loadTestSettings("");
        } catch (PropertyNotFoundException e) {
            Assert.assertEquals(e.getMessage(), "Не задан тестовый цикл, проверьте данные");
        }
    }

    @Test
    public void loadTestSettingsFromProperty() throws Exception {
        TestSettings testSettings = settingsLoader.loadTestSettings("actual.property");
        Assert.assertEquals(testSettings.getFlagsMetaFilters().size(), 2);
        Assert.assertEquals(testSettings.getFlagsMetaFilters().get(0), "hello");
    }


    @Test(expected = PropertyNotFoundException.class)
    public void loadTestSettingsFromBadProperty() throws Exception {
        settingsLoader.loadTestSettings("bad.property");
    }

    @Test
    public void loadTestSettingsFromBadPropertyWithText() throws Exception {
        String propertyName = "bad.property";
        try {
            settingsLoader.loadTestSettings(propertyName);
        } catch (PropertyNotFoundException e) {
            Assert.assertEquals(e.getMessage(), format("Property %s not found on file: %s", propertyName, TEST_TYPE_FILTER));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadTestSettingsFromEmptyProperty() throws Exception {
        settingsLoader.loadTestSettings("empty.property");
    }

    @Test
    public void loadTestSettingsFreeType() throws Exception {
        TestSettings testSettings = settingsLoader.loadTestSettings("freeType,+azaza,-ozozo");
        Assert.assertEquals(testSettings.getFlagsMetaFilters().size(), 2);
        Assert.assertEquals(testSettings.getFlagsMetaFilters().get(0), "+azaza");
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadTestSettingsWithBadFreeType() throws Exception {
        settingsLoader.loadTestSettings("azaza,ozozo");
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadTestSettingsWithBadFilters() throws Exception {
        settingsLoader.loadTestSettings("freeType,+azaza,ozozo");
    }

    @Test
    public void loadTestSettingsFreeTypeBadWithText() throws Exception {
        String freeType = "azaza,ozozo";
        try {
            settingsLoader.loadTestSettings(freeType);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), format("Неправильно задан тестовый цикл в свободной конфигурации, проверьте данные: %s", freeType));
        }
    }

}
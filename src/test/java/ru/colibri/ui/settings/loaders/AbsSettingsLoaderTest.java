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
        Assert.assertEquals(testSettings.getFlagsMetaFilters().get(0), "+hello");
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

    @Test
    public void loadTestSettingsBadPropertySyntax() throws Exception {
        String propertyName = "bad.syntax.property";
        try {
            settingsLoader.loadTestSettings(propertyName);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Неправильный синтаксис фильтров тестового цикла, проверьте данные");
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadTestSettingsFromEmptyProperty() throws Exception {
        settingsLoader.loadTestSettings("empty.property");
    }

    @Test
    public void loadTestSettingsFreeType() throws Exception {
        TestSettings testSettings = settingsLoader.loadTestSettings("+includeFilter,-excludeFilter");
        Assert.assertEquals(testSettings.getFlagsMetaFilters().size(), 2);
        Assert.assertEquals(testSettings.getFlagsMetaFilters().get(0), "+includeFilter");
    }

    @Test
    public void loadTestSettingsWithBadIncludeWithText() throws Exception {
        String freeType = "includeFilter,-excludeFilter";
        try {
            settingsLoader.loadTestSettings(freeType);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Неправильный синтаксис фильтров тестового цикла, проверьте данные");
        }
    }

    @Test
    public void loadTestSettingsBadExcludeSyntaxWithText() throws Exception {
        String freeType = "+includeFilter,excludeFilter";
        try {
            settingsLoader.loadTestSettings(freeType);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Неправильный синтаксис фильтров тестового цикла, проверьте данные");
        }
    }

    @Test
    public void loadTestSettingsFromType() throws Exception {
        String type = "includeFilter,excludeFilter";
        try {
            settingsLoader.loadTestSettings(type);
        } catch (PropertyNotFoundException e) {
            Assert.assertEquals(e.getMessage(), format("Property %s not found on file: %s", type, TEST_TYPE_FILTER));
        }
    }
}
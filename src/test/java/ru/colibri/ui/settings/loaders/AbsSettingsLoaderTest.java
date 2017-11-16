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

    @Test
    public void loadTestSettings() throws Exception {
        TestSettings testSettings = settingsLoader.loadTestSettings("actual.property");
        Assert.assertEquals(testSettings.getFlagsMetaFilters().size(), 2);
        Assert.assertEquals(testSettings.getFlagsMetaFilters().get(0), "hello");
    }


    @Test(expected = PropertyNotFoundException.class)
    public void loadTestSettingsBad() throws Exception {
        settingsLoader.loadTestSettings("bad.property");
    }

    @Test
    public void loadTestSettingsBadWithText() throws Exception {
        String propertyName = "bad.property";
        try {
            settingsLoader.loadTestSettings(propertyName);
        } catch (PropertyNotFoundException e) {
            Assert.assertEquals(e.getMessage(), format("Property %s not found on file: %s", propertyName, TEST_TYPE_FILTER));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadTestSettingsEmpty() throws Exception {
        settingsLoader.loadTestSettings("empty.property");
    }

}
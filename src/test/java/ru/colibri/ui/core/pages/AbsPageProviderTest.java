package ru.colibri.ui.core.pages;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.colibri.ui.core.exception.NoPageFoundException;
import ru.colibri.ui.core.exception.PageNameAlreadyUsedException;
import ru.colibri.ui.settings.general.UtilsTestConfig;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UtilsTestConfig.class})
public class AbsPageProviderTest {

    private AbsPageProvider pageProvider;

    private IPage fullPage = new Page("FullPage", "FullPageID");
    private IPage pageWithName = new Page("PageWithName", "");
    private IPage pageWithId = new Page("", "pageID");
    private IPage emptyPage = new Page("", "");

    @Before
    public void generateTestData() {
        pageProvider = new AbsPageProvider() {
            @Override
            public IPage getCurrentPage() {
                return null;
            }
        };
        fillPageProvider();
    }

    @Test
    public void addPageObject() throws Exception {
        Map<String, IPage> testMapName = new HashMap<>();
        testMapName.put(fullPage.getName().toLowerCase(), fullPage);
        testMapName.put(pageWithName.getName().toLowerCase(), pageWithName);
        testMapName.put(pageWithId.getName().toLowerCase(), pageWithId);
        testMapName.put(emptyPage.getName().toLowerCase(), emptyPage);

        Map<String, IPage> testMapID = new HashMap<>();
        testMapID.put(fullPage.getSystemId().toLowerCase(), fullPage);
        testMapID.put(pageWithName.getSystemId().toLowerCase(), pageWithName);
        testMapID.put(pageWithId.getSystemId().toLowerCase(), pageWithId);
        testMapID.put(emptyPage.getSystemId().toLowerCase(), emptyPage);

        assertEquals(pageProvider.pagesByName, testMapName);
        assertEquals(pageProvider.pagesByID, testMapID);

    }

    @Test(expected = PageNameAlreadyUsedException.class)
    public void addNotUniqueNamePageObject() throws Exception {
        pageProvider.addPageObject(pageWithName);

    }

    @Test(expected = PageNameAlreadyUsedException.class)
    public void addNotUniqueIDPageObject() throws Exception {
        pageProvider.addPageObject(pageWithId);
    }

    @Test
    public void addEmptyPageObject() throws Exception {
        pageProvider.addPageObject(emptyPage);
        pageProvider.addPageObject(emptyPage);
    }

    @Test
    public void getPageByName() throws Exception {
        IPage testPage = pageProvider.getPageByName(pageWithName.getName().toLowerCase());
        assertEquals(testPage, pageWithName);
    }

    @Test
    public void getPageById() throws Exception {
        IPage testPage = pageProvider.getPageById(pageWithId.getSystemId().toLowerCase());
        assertEquals(testPage, pageWithId);
    }

    @Test(expected = NoPageFoundException.class)
    public void getPageByNameWhatNotExist() throws Exception {
        pageProvider.getPageByName("azaza");

    }

    @Test(expected = NoPageFoundException.class)
    public void getPageByIdWhatNotExist() throws Exception {
        pageProvider.getPageById("azaza");
    }

    private void fillPageProvider() {
        pageProvider.addPageObject(this.fullPage);
        pageProvider.addPageObject(this.pageWithName);
        pageProvider.addPageObject(this.pageWithId);
        pageProvider.addPageObject(this.emptyPage);
    }

}
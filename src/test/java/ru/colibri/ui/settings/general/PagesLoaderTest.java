package ru.colibri.ui.settings.general;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.colibri.ui.core.exception.PageDescriptionException;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.pages.IPage;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UtilsTestConfig.class})
public class PagesLoaderTest {
    private final static String FILE_PATH = "src/test/resources/loadFiles/goodPage.csv";
    private final static String BAD_FILE_PATH = "src/test/resources/loadFiles/badPage.csv";
    private final static String BAD_FILE_PATH2 = "src/test/resources/loadFiles/badPage2.csv";
    private final static String DIR_PATH = "src/test/resources/loadFiles/loadDir";
    @Autowired
    private PagesLoader pagesLoader;

    @Test
    public void loadPageFromFile() throws Exception {
        IPage iPage = pagesLoader.loadPageFromFile(new File(FILE_PATH));
        IElement element = iPage.getElementByName("Тарифный план");
        Assert.assertTrue(element.isSpecific());
    }

    @Test(expected = PageDescriptionException.class)
    public void loadPageFromFileWithEmptyLines() throws Exception {
        pagesLoader.loadPageFromFile(new File(BAD_FILE_PATH));
    }

    @Test(expected = PageDescriptionException.class)
    public void loadPageFromFileWithOutCells() throws Exception {
        pagesLoader.loadPageFromFile(new File(BAD_FILE_PATH2));
    }

    @Test(expected = RuntimeException.class)
    public void loadPageFromNotExistFile() throws Exception {
        pagesLoader.loadPageFromFile(new File("not_exist.file"));
    }

    @Test
    public void loadPagesFromDirectory() throws Exception {
        List<IPage> iPages = pagesLoader.loadPagesFromDirectory(new File(DIR_PATH));
        assertTrue(iPages.size() == 2);
    }
}
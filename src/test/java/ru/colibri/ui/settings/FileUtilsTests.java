package ru.colibri.ui.settings;

import org.junit.Test;
import ru.colibri.ui.core.utils.FileUtils;

import static org.junit.Assert.assertEquals;

public class FileUtilsTests {

    @Test
    public void winPath() {
        FileUtils fileUtils = new FileUtils();
        String actualWinStylePath = "C:/am-ui-tests/src/test/resources";
        String path = "/" + actualWinStylePath;
        assertEquals(actualWinStylePath, fileUtils.transformPath(path));

    }

    @Test
    public void unixPath() {
        FileUtils fileUtils = new FileUtils();
        String actualUnixStylePath = "/home/user/git/project/src/test/resources";
        String path = actualUnixStylePath;
        assertEquals(actualUnixStylePath, fileUtils.transformPath(path));

    }
}

package ru.colibri.ui.core.pages;


import io.appium.java_client.AppiumDriver;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import ru.colibri.ui.core.exception.NoPageFoundException;

import java.util.HashMap;
import java.util.List;

@ToString
public abstract class AbsPageProvider implements IPageProvider {

    @Autowired
    protected AppiumDriver driver;

    protected HashMap<String, IPage> pages;

    public AbsPageProvider() {
        pages = new HashMap<>();
    }

    public void addPageObject(IPage pageObject) {
        pages.put(pageObject.getName().toLowerCase(), pageObject);
    }

    public IPage getPageByName(String pageName) {
        IPage result = pages.get(pageName.toLowerCase());
        if (result == null) {
            throw new NoPageFoundException(pageName);
        }
        return result;
    }

    public IPage getPageById(String pageId) {
        for (IPage page : pages.values()) {
            if (pageId.equalsIgnoreCase(page.getSystemId())) {
                return page;
            }
        }
        throw new NoPageFoundException(pageId);
    }

    @Override
    public void addPagesObject(List<IPage> pages) {
        pages.stream().forEach(this::addPageObject);
    }
}
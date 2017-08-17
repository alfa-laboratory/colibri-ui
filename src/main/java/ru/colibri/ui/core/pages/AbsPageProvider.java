package ru.colibri.ui.core.pages;


import io.appium.java_client.AppiumDriver;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import ru.colibri.ui.core.exception.NoPageFoundException;
import ru.colibri.ui.core.exception.PageNameAlreadyUsedException;

import java.util.HashMap;
import java.util.List;

@ToString
public abstract class AbsPageProvider implements IPageProvider {

    @Autowired
    protected AppiumDriver driver;

    protected HashMap<String, IPage> pagesByName;
    protected HashMap<String, IPage> pagesByID;

    public AbsPageProvider() {
        pagesByName = new HashMap<>();
        pagesByID = new HashMap<>();
    }

    public void addPageObject(IPage pageObject) throws PageNameAlreadyUsedException {
        String systemId = pageObject.getSystemId().toLowerCase();
        if (!systemId.isEmpty() && pagesByID.containsKey(systemId)) {
            throw new PageNameAlreadyUsedException(systemId);
        }
        String name = pageObject.getName().toLowerCase();
        if (!name.isEmpty() && pagesByName.containsKey(name)) {
            throw new PageNameAlreadyUsedException(name);
        }
        pagesByName.put(name, pageObject);
        pagesByID.put(systemId, pageObject);
    }

    public IPage getPageByName(String pageName) {
        return getIPage(pagesByName, pageName);
    }

    public IPage getPageById(String pageId) {
        return getIPage(pagesByID, pageId);
    }

    private IPage getIPage(HashMap<String, IPage> pages, String pageName) {
        IPage result = pages.get(pageName.toLowerCase());
        if (result == null) {
            throw new NoPageFoundException(pageName);
        }
        return result;
    }

    @Override
    public void addPagesObject(List<IPage> pages) {
        pages.forEach(this::addPageObject);
    }
}
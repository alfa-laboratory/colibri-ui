package ru.colibri.ui.core.pages;

import java.util.List;

public interface IPageProvider {
    void addPageObject(IPage pageObject);

    void addPagesObject(List<IPage> pages);

    IPage getPageByName(String pageName);

    IPage getPageById(String pageId);

    IPage getCurrentPage();
}

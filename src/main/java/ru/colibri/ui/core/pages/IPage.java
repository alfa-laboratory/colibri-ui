package ru.colibri.ui.core.pages;

import ru.colibri.ui.core.fields.IElement;

import java.util.List;

public interface IPage {
    String getName();

    String getSystemId();

    List<IElement> getSpecificElements();

    IElement getElementByName(String name);

    void addElement(IElement element);
}

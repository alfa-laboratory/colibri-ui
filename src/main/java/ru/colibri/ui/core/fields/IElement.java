package ru.colibri.ui.core.fields;


public interface IElement {
    String getName();

    String getContentDesc();

    String getId();

    String getText();

    String getXpath();

    String getNSPredicate();

    boolean isSpecific();
}

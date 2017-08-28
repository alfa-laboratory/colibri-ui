package ru.colibri.ui.core.fields;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Element implements IElement {
    private String name;
    private String contentDesc;
    private String id;
    private String text;
    private String xpath;
    private String nsPredicate;
    private boolean specific;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getContentDesc() {
        return contentDesc;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isSpecific() {
        return specific;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getXpath() {
        return xpath;
    }

    @Override
    public String getNSPredicate() {
        return nsPredicate;
    }
}


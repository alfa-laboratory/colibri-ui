package ru.colibri.ui.core.builders;

import ru.colibri.ui.core.fields.Element;
import ru.colibri.ui.core.fields.IElement;

public class ElementBuilder {
    private String name = "elementName";
    private String id;
    private String contentDesc;
    private String text;
    private String xpath;
    private String nsPredicate;
    private boolean specific;

    public ElementBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ElementBuilder withSpecific(boolean specific) {
        this.specific = specific;
        return this;
    }

    public ElementBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ElementBuilder withContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
        return this;
    }

    public ElementBuilder withText(String text) {
        this.text = text;
        return this;
    }

    public ElementBuilder withXPath(String xpath) {
        this.xpath = xpath;
        return this;
    }

    public ElementBuilder withNSPredicate(String nsPredicate) {
        this.nsPredicate = nsPredicate;
        return this;
    }

    public IElement please() {
        return new Element(name, contentDesc, id, text, xpath, nsPredicate, specific);
    }
}

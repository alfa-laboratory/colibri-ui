package ru.colibri.ui.core.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String elementName, String pageName) {
        super("Element \"" + elementName + "not found on  \"" + pageName + "\".");
    }
}
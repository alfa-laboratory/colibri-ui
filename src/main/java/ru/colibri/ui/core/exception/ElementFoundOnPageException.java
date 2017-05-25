package ru.colibri.ui.core.exception;

public class ElementFoundOnPageException extends RuntimeException {
    public ElementFoundOnPageException(String elementName, String pageName) {
        super("Element \"" + elementName + " found on  \"" + pageName + "\". But should not be there.");
    }
}
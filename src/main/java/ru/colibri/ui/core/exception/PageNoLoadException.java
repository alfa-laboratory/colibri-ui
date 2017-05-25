package ru.colibri.ui.core.exception;

import static java.lang.String.format;

public class PageNoLoadException extends RuntimeException {
    public PageNoLoadException(String pageName) {
        super(format("Page \"%s\" not load", pageName));
    }
}

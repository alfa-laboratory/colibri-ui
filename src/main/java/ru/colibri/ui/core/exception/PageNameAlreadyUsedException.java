package ru.colibri.ui.core.exception;

public class PageNameAlreadyUsedException extends RuntimeException {
    public PageNameAlreadyUsedException(String pageName) {
        super("Page name already used \"" + pageName + "\"");
    }
}

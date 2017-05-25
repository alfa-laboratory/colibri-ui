package ru.colibri.ui.core.exception;

public class NoPageFoundException extends RuntimeException {
    public NoPageFoundException(String pageName) {
        super("No page found \"" + pageName + "\"");
    }
}

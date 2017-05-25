package ru.colibri.ui.core.exception;

public class NotValueOnPickerException extends RuntimeException {
    public NotValueOnPickerException(String item) {
        super("Value not found on picker. Value: " + item);
    }
}
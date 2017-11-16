package ru.colibri.ui.core.exception;

import static java.lang.String.format;

public class PropertyNotFoundException extends IllegalArgumentException {
    public PropertyNotFoundException(String message) {
        super(message);
    }

    public PropertyNotFoundException(String propertyName, String propertyPath) {
        this(format("Property %s not found on file: %s", propertyName, propertyPath));
    }
}

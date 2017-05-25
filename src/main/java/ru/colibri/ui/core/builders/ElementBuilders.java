package ru.colibri.ui.core.builders;

import org.springframework.stereotype.Component;

@Component
public class ElementBuilders {

    public static ElementBuilder element() {
        return new ElementBuilder();
    }
}

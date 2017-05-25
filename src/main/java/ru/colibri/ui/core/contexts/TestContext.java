package ru.colibri.ui.core.contexts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class TestContext {
    private String currentPageName;
}

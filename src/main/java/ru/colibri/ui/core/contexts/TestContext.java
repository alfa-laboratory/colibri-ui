package ru.colibri.ui.core.contexts;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Getter
@Setter
public class TestContext {
    private String currentPageName;
    private Map<String, String> context = new HashMap<>();
}

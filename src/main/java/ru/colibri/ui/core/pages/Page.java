package ru.colibri.ui.core.pages;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.colibri.ui.core.fields.IElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
public class Page implements IPage {
    private String systemId;
    private String name;
    private Map<String, IElement> elements = new HashMap<>();

    public Page(String name, String systemId) {
        this.systemId = systemId;
        this.name = name;
    }

    @Override
    public void addElement(IElement element) {
        elements.put(elementNameToKey(element.getName()), element);
    }

    @Override
    public IElement getElementByName(String name) {
        return elements.get(elementNameToKey(name));
    }

    @Override
    public List<IElement> getSpecificElements() {
        return elements.entrySet().stream()
                .map(Map.Entry::getValue)
                .filter(IElement::isSpecific)
                .collect(Collectors.toList());
    }

    @Override
    public String getSystemId() {
        return systemId;
    }

    @Override
    public String getName() {
        return name;
    }

    private String elementNameToKey(String name) {
        return name.toLowerCase();
    }
}

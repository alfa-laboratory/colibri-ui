package ru.colibri.ui.steps.general;

import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.When;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.steps.AbsSteps;

@Component
public class ScrollSteps extends AbsSteps {

    @When("скролл внутри \"$targetListName\" до \"$toElementName\"")
    public void scrollTo(@Named("$targetListName") String targetListName, @Named("$toElementName") String toElementName) {
        IElement elementList = getCurrentPage().getElementByName(targetListName);
        IElement elementToScroll = getCurrentPage().getElementByName(toElementName);
        finder.scrollTo(elementList, elementToScroll);
    }
}



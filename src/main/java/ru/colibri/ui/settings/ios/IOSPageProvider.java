package ru.colibri.ui.settings.ios;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.contexts.TestContext;
import ru.colibri.ui.core.pages.AbsPageProvider;
import ru.colibri.ui.core.pages.IPage;

@Log
@Component
@Qualifier("ios")
public class IOSPageProvider extends AbsPageProvider {
    @Autowired
    private TestContext testContext;

    @Override
    public IPage getCurrentPage() {
        return getPageByName(testContext.getCurrentPageName());
    }
}
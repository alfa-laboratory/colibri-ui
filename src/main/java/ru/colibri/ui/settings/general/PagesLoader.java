package ru.colibri.ui.settings.general;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.colibri.ui.core.exception.PageDescriptionException;
import ru.colibri.ui.core.fields.IElement;
import ru.colibri.ui.core.pages.IPage;
import ru.colibri.ui.core.pages.Page;
import ru.colibri.ui.core.utils.FileUtils;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static ru.colibri.ui.core.builders.ElementBuilders.element;

@Component
public class PagesLoader {
    @Autowired
    private PropertyUtils propertyUtils;

    @Autowired
    private FileUtils fileUtils;

    public List<IPage> loadPagesFromDirectory(File directory) {
        return fileUtils.getAllFilesAtDirectory(directory).stream()
                .map(this::loadPageFromFile)
                .collect(Collectors.toList());
    }

    public IPage loadPageFromFile(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            CSVReader reader = new CSVReader(bufferedReader);
            String[] firstLine = reader.readNext();
            String pageName = firstLine[0];
            String pageId = firstLine[2];
            reader.readNext(); //skip line with column name
            IPage result = new Page(pageName, pageId);
            StreamSupport.stream(reader.spliterator(), false)
                    .map(cells -> getIElement(file, cells))
                    .forEach(result::addElement);
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Can't parse file " + file.getName(), e);
        }
    }

    private IElement getIElement(File file, String[] cells) {
        if (cells.length == 1 || cells.length != 6) {
            throw new PageDescriptionException(file);
        }
        String name = cells[0];
        String contentDesc = propertyUtils.injectProperties(cells[1]);
        String id = propertyUtils.injectProperties(cells[2]);
        String text = propertyUtils.injectProperties(cells[3]);
        String xpath = propertyUtils.injectProperties(cells[4]);
        Boolean specific = Boolean.valueOf(cells[5]);
        return element()
                .withName(name)
                .withContentDesc(contentDesc)
                .withId(id)
                .withText(text)
                .withXPath(xpath)
                .withSpecific(specific)
                .please();
    }
}


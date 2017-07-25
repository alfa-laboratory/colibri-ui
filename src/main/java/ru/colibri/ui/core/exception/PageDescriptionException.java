package ru.colibri.ui.core.exception;

import java.io.File;

import static java.lang.String.format;

public class PageDescriptionException extends RuntimeException {
    public PageDescriptionException(File file) {
        super(format("Ошибка в описании файла %s. %n " +
                "Проверьте файл на наличие пустых строк и корректность их заполнения", file.getAbsolutePath()));
    }
}

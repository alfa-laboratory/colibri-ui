package ru.colibri.ui.core.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class FileUtils {
    public String relativeToAbsolutePath(String relativePath) {
        String path = new File(relativePath).toURI().getPath();
        return transformPath(path);
    }

    public String transformPath(String path) {
        if (path.contains(":")) { //windows
            StringBuilder builder = new StringBuilder(path);
            builder.deleteCharAt(0);
            path = builder.toString();
        }
        return path;
    }

    public List<File> getAllFilesAtDirectory(File directory) {
        List<File> result = new ArrayList<>();
        if (!directory.isDirectory()) {
            result.add(directory);
        } else {
            File[] files = directory.listFiles();
            if (files != null && files.length > 0) {
                Collections.addAll(result, files);
            }
        }
        return result;
    }

    public File getFileByPath(String path) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        String fileUrl = classLoader.getResource(path).getFile();
        return new File(fileUrl);
    }
}
